package com.example.api.service;

import com.example.api.enumerator.EnumFragmentosDigievolucao;
import com.example.api.model.entity.Cacada;
import com.example.api.model.response.ResponseResgatarRecompensaMissaoDTO;
import com.example.api.model.entity.Missao;
import com.example.api.repository.MissaoRepository;
import com.example.api.utils.Data;
import com.example.api.utils.Recompensas;
import com.example.api.utils.SorteioFragmentosDigievolucao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class MissaoService {

    @Autowired
    private MissaoRepository missaoRepository;
    @Autowired
    private LogService logService;
    @Autowired
    private DigimonService digimonService;
    @Autowired
    private InventarioService inventarioService;
    @Autowired
    private RegistroConquistasService registroConquistasService;

    public ResponseResgatarRecompensaMissaoDTO resgatarRecompensaMissao(Long idDigimon) {
        Missao missaoEmAndamento = verificarMissaoEmAndamento(idDigimon);

        if (missaoEmAndamento == null) {
            logService.logAction("Resgatar Recompensa Missao - Erro", "Não existe missão em andamento "
                    + "para este digimon");
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

        registroConquistasService.salvarRegistroCacadaMissao(digimonService.getDigimonById(idDigimon),
                recompensaBits, quantiaFragmentos, "MISSAO");

        digimonService.atualizarDigimonBits(idDigimon, recompensaBits);

        logService.logAction("Resgatar Recompensa Missao", "Missão de " + missaoEmAndamento.getQtHoras() +
                " horas resgatada para o digimon " + idDigimon);

        if (fragmentoSorteado != null) {
            return new ResponseResgatarRecompensaMissaoDTO(digimonService.getNomeDigimon(idDigimon), recompensaBits,fragmentoSorteado.getDescricao_item(), quantiaFragmentos);
        }

        return null;
    }

    @Transactional
    public Missao iniciarMissao(Missao missao) {

        if (verificarMissaoEmAndamento(missao.getIdDigimon()) != null) {
            logService.logAction("Iniciar Missao - Erro", "Já existe uma missão em andamento para " +
                    "este digimon");
            throw new RuntimeException("Já existe uma missão em andamento para este digimon");
        }

        logService.logAction("Iniciar Missao", "Missão de " + missao.getQtHoras() +
                "iniciada para o digimon " + missao.getIdDigimon());

        return salvarIniciarMissao(missao);

    }

    public Missao verificarMissaoEmAndamento(Long idDigimon) {
        Missao missao = missaoRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
        return missao;
    }

    public Missao salvarIniciarMissao(Missao missao) {
        return missaoRepository.save(new Missao(missao.getIdDigimon(), missao.getIdMissao(), missao.getQtHoras(),
                LocalDate.now(), LocalDateTime.now().plusHours(missao.getQtHoras()), false,
                LocalDateTime.now()));
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
}
