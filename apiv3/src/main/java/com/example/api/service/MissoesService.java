package com.example.api.service;

import com.example.api.entity.Digimon;
import com.example.api.entity.Missao;
import com.example.api.entity.dto.ResponseResgatarRecompensaMissaoDTO;
import com.example.api.enumerator.EnumFragmentosDigievolucao;
import com.example.api.enumerator.EnumMissoes;
import com.example.api.repository.MissaoRepository;
import com.example.api.utils.Data;
import com.example.api.utils.Recompensas;
import com.example.api.utils.SorteioFragmentosDigievolucao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MissoesService {

    private static final Logger logger = LoggerFactory.getLogger(MissoesService.class);

    private final DigimonService digimonService;
    private final JogadorService jogadorService;
    private final PremiumService premiumService;
    private final MissaoRepository missaoRepository;
    private final InventarioService inventarioService;

    public MissoesService(DigimonService digimonService, JogadorService jogadorService, PremiumService premiumService, MissaoRepository missaoRepository, InventarioService inventarioService) {
        this.digimonService = digimonService;
        this.jogadorService = jogadorService;
        this.premiumService = premiumService;
        this.missaoRepository = missaoRepository;
        this.inventarioService = inventarioService;
    }

    public Map<String, Object> carregarTelaMissoes(Long idDigimon){
        Map<String, Object> response = new LinkedHashMap<>();

        Digimon digimon = digimonService.getDigimonById(idDigimon);

        // Chama os métodos existentes e adiciona os resultados ao response
       response.putAll(carregarDadosMissoes(idDigimon, response));
//        response.put("nivel", digimon.getNivel());
//        response.put("vida", digimon.getAtributos().getPontosVida());
//        response.put("energia", digimon.getAtributos().getPontosEnergia());

        return response;
    }

    private Map<String, Object> carregarDadosMissoes(Long idDigimon, Map<String, Object> response) {
        // Implementação do método
        response.put("emAndamento", verificarMissaoEmAndamento(idDigimon) != null);
        response.put("horaResgate", buscarTempoResgateMissaoEmAndamento(idDigimon));
        response.put("resgateDisponivel", validarResgateDisponivelMissao(idDigimon));
        return response;
    }

    public Missao verificarMissaoEmAndamento(Long idDigimon) {
        Missao missao = missaoRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
        return missao;
    }

    public String buscarTempoResgateMissaoEmAndamento(Long idDigimon) {
        if(verificarMissaoEmAndamento(idDigimon) == null){
            return "Não há missão em andamento que possa ter recompensa resgatada";
        }
        Missao missao = missaoRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
        String dataFormatada = Data.formatarDataCadastroParaTelaStatus(missao.getHoraResgateDisponivel());
        return dataFormatada;
    }

    public boolean validarResgateDisponivelMissao(Long idDigimon) {
        if(verificarMissaoEmAndamento(idDigimon) == null){
            return false;
        }
        Missao missao = missaoRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
        LocalDateTime horaResgateDisponivel = missao.getHoraResgateDisponivel();
        return LocalDateTime.now().isAfter(horaResgateDisponivel);
    }

    @Transactional
    public Missao iniciarMissao(Missao missao) {
        if (verificarMissaoEmAndamento(missao.getIdDigimon()) != null) {
            throw new RuntimeException("Já existe uma missão em andamento para este digimon");
        }
        int nvMinimo = Integer.parseInt(EnumMissoes.getNvMinimoByIdMissao(missao.getIdMissao()));
       Digimon digimon = digimonService.getDigimonById(missao.getIdDigimon());
        if (digimon.getNivel() < nvMinimo) {
            System.out.println("Nível do digimon insuficiente para iniciar a missão");
            throw new RuntimeException("Nível do digimon insuficiente para iniciar a missão");
        }
        return salvarIniciarMissao(missao);
    }

    public Missao salvarIniciarMissao(Missao missao) {
        return missaoRepository.save(new Missao(missao.getIdDigimon(), missao.getIdMissao(), missao.getQtHoras(),
                LocalDate.now(), LocalDateTime.now().plusHours(missao.getQtHoras()), false,
                LocalDateTime.now()));
    }

    public ResponseResgatarRecompensaMissaoDTO resgatarRecompensaMissao(Long idDigimon) {
        Missao missaoEmAndamento = verificarMissaoEmAndamento(idDigimon);

        if (missaoEmAndamento == null) {
            throw new RuntimeException("Não existe missão em andamento para este digimon");
        }

        String tierRecompensa = digimonService.getProxTierDigimon(idDigimon);
        EnumFragmentosDigievolucao fragmentoSorteado;
        if (tierRecompensa.equals("Jogress")) {
            fragmentoSorteado = SorteioFragmentosDigievolucao.sortearFragmentoQualquerTier();
        } else {
            fragmentoSorteado = SorteioFragmentosDigievolucao.sortearFragmento(
                    tierRecompensa);
        }
        int quantiaFragmentos = Recompensas.gerarQuantidadeFragmentos1A5();
        int recompensaBits = Recompensas.gerarRecompensaBitsMissao(missaoEmAndamento.getIdMissao(),
                missaoEmAndamento.getQtHoras());
//        int recompensaExp = Recompensas.gerarRecompensaExpMissao(missaoEmAndamento.getIdMissao(),
//                missaoEmAndamento.getQtHoras());

        if (fragmentoSorteado != null) {
            inventarioService.adicionarFragmentoAoInventario(idDigimon, fragmentoSorteado, quantiaFragmentos);
        }

        missaoEmAndamento.setRecompensaResgatada(true);
        missaoEmAndamento.setUltimaAlteracao(LocalDateTime.now());

        missaoRepository.save(missaoEmAndamento);

//        registroConquistasService.salvarRegistroCacadaMissao(digimonService.getDigimonById(idDigimon),
//                recompensaBits, quantiaFragmentos, "MISSAO");

        Digimon digimon = digimonService.getDigimonById(idDigimon);
        digimonService.atualizarBitsDigimon(digimon, recompensaBits);

//        logService.logAction("Resgatar Recompensa Missao", "Missão de " + missaoEmAndamento.getQtHoras() +
//                " horas resgatada para o digimon " + idDigimon);

        if (fragmentoSorteado != null) {
            return new ResponseResgatarRecompensaMissaoDTO(digimonService.getNomeDigimonLong(idDigimon), recompensaBits,fragmentoSorteado.getDescricao_item(), quantiaFragmentos);
        }

        return null;
    }

}
