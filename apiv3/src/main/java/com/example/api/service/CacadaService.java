package com.example.api.service;

import com.example.api.entity.Cacada;
import com.example.api.entity.Digimon;
import com.example.api.entity.Jogador;
import com.example.api.entity.TempoDisponivelCacada;
import com.example.api.entity.dto.ResponseResgatarRecompensaCacadaDTO;
import com.example.api.enumerator.EnumFragmentosDigievolucao;
import com.example.api.repository.CacadaRepository;
import com.example.api.repository.TempoDisponivelCacadaRepository;
import com.example.api.utils.Data;
import com.example.api.utils.Feriados;
import com.example.api.utils.Recompensas;
import com.example.api.utils.SorteioFragmentosDigievolucao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CacadaService {

    private static final Logger logger = LoggerFactory.getLogger(CacadaService.class);

    private final DigimonService digimonService;
    private final JogadorService jogadorService;
    private final RegistroConquistasService registroConquistasService;
    private final PremiumService premiumService;
    private final TempoDisponivelCacadaRepository tempoDisponivelRepository;
    private final CacadaRepository cacadaRepository;
    private final InventarioService inventarioService;

    public CacadaService(DigimonService digimonService, JogadorService jogadorService, RegistroConquistasService registroConquistasService, PremiumService premiumService, TempoDisponivelCacadaRepository tempoDisponivelRepository, CacadaRepository cacadaRepository, InventarioService inventarioService) {
        this.digimonService = digimonService;
        this.jogadorService = jogadorService;
        this.registroConquistasService = registroConquistasService;
        this.premiumService = premiumService;
        this.tempoDisponivelRepository = tempoDisponivelRepository;
        this.cacadaRepository = cacadaRepository;
        this.inventarioService = inventarioService;
    }

    public Map<String, Object> carregarTelaCacada(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();

        Digimon digimon = digimonService.getDigimonById(idDigimon);
        Jogador jogador = jogadorService.getJogadorById(digimon.getIdJogador());

        // Chama os métodos existentes e adiciona os resultados ao response
        response.putAll(digimonService.carregarImagemDigimon(idDigimon));
        response.putAll(premiumService.carregarInformacoesPremium(idDigimon));
        response.putAll(carregarDadosCacada(idDigimon));
        response.put("nivel", digimon.getNivel());
        response.put("vida", digimon.getAtributos().getPontosVida());
        response.put("energia", digimon.getAtributos().getPontosEnergia());

        return response;
    }

    public Map<String, Object> carregarDadosCacada(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("tempoDisponivel", verificarTempoDisponivelCacada(idDigimon));
        response.put("emAndamento", verificarCacadaEmAndamento(idDigimon));
        response.put("horaResgate", buscarTempoResgateCacadaEmAndamento(idDigimon));
        response.put("resgateDisponivel", validarResgateDisponivelCacada(idDigimon));

        return response;
    }

    public int verificarTempoDisponivelCacada(Long idDigimon) {
        if (!digimonService.verificarExistenciaDigimon(idDigimon)) {
            throw new RuntimeException("Digimon não encontrado");
        }
        LocalDate hoje = LocalDate.now();
        Optional<TempoDisponivelCacada> registro = tempoDisponivelRepository.findByIdDigimonAndDataCadastro(idDigimon, hoje);

        // Verifica se o registro está vazio ou se hoje é final de semana
        if (registro.isEmpty() || Data.validarFinalDeSemana(hoje)) {
            int tempoDisponivel = (Feriados.isFeriado(hoje) || Data.validarFinalDeSemana(hoje)) ? 120 : 60;

            // Salva o novo registro se necessário
            if (registro.isEmpty()) {
                tempoDisponivelRepository.save(new TempoDisponivelCacada(idDigimon, hoje, tempoDisponivel));
            }
            return tempoDisponivel;
        }

        // Retorna o tempo disponível do registro existente
        return registro.get().getTempoDisponivel();
    }

    public boolean verificarCacadaEmAndamento(Long idDigimon) {
        return cacadaRepository.existsByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
    }

    public String buscarTempoResgateCacadaEmAndamento(Long idDigimon) {
        if (!verificarCacadaEmAndamento(idDigimon)) {
            return "Não há caçada em andamento";
        }
        Cacada cacada = cacadaRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
        String dataFormatada = Data.formatarDataCadastroParaTelaStatus(cacada.getHoraResgateDisponivel());
        return dataFormatada;
    }

    public boolean validarResgateDisponivelCacada(Long idDigimon) {
        if (!verificarCacadaEmAndamento(idDigimon)) {
            return false;
        }
        Cacada cacada = cacadaRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
        LocalDateTime horaResgateDisponivel = cacada.getHoraResgateDisponivel();
        return LocalDateTime.now().isAfter(horaResgateDisponivel);
    }

    public Cacada iniciarCacada(int minutosEscolhidos, Long idDigimon) {

        if (verificarCacadaEmAndamento(idDigimon)) {
            throw new RuntimeException("Já existe uma caçada em andamento para esse digimon");
        }
        if (minutosEscolhidos <= 0) {
            throw new RuntimeException("O tempo escolhido não pode ser menor ou igual a zero");
        }
        if (minutosEscolhidos > verificarTempoDisponivelCacada(idDigimon)) {
            throw new RuntimeException("O tempo escolhido é maior que o tempo disponível");
        }

        TempoDisponivelCacada tempoDisponivelCacada =
                tempoDisponivelRepository.findTempoDisponivelCacadaByIdDigimonAndDataCadastro(idDigimon, LocalDate.now());
        tempoDisponivelCacada.setTempoDisponivel(tempoDisponivelCacada.getTempoDisponivel() - minutosEscolhidos);
        tempoDisponivelCacada.setDataUltimaAlteracao(LocalDateTime.now());
        tempoDisponivelRepository.save(tempoDisponivelCacada);
        digimonService.atualizarBitsDigimon(digimonService.getDigimonById(idDigimon), -10);

        return cacadaRepository.save(new Cacada(idDigimon, minutosEscolhidos,
                LocalDateTime.now().plusMinutes(minutosEscolhidos), false));
    }

    public ResponseResgatarRecompensaCacadaDTO resgatarRecompensa(Long idDigimon) {
        if (!verificarCacadaEmAndamento(idDigimon)) {
            throw new RuntimeException("Não há caçada em andamento para esse digimon");
        }

        Cacada cacada = cacadaRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);

        String tierRecompensa = digimonService.getProxTierDigimon(idDigimon);
        EnumFragmentosDigievolucao fragmentoSorteado;
        if (tierRecompensa.equals("Jogress")) {
            fragmentoSorteado = SorteioFragmentosDigievolucao.sortearFragmentoQualquerTier();
        } else {
            fragmentoSorteado = SorteioFragmentosDigievolucao.sortearFragmento(
                    tierRecompensa);
        }
        int quantiaFragmentos = Recompensas.gerarQuantidadeFragmentos1A5();
        int recompensaBits = Recompensas.gerarBitsCacada();
        int recompensaExp = Recompensas.gerarExpCacada();

        inventarioService.adicionarFragmentoAoInventario(idDigimon, fragmentoSorteado, quantiaFragmentos);
        atualizarCacada(cacada);
//        registroConquistasService.salvarRegistroCacadaMissao(digimonService.getJogadorByDigimonId(idDigimon), recompensaBits, quantiaFragmentos, "CACADA");
        digimonService.atualizarDigimonExpBits(idDigimon, recompensaExp, recompensaBits);

        return new ResponseResgatarRecompensaCacadaDTO(digimonService.getNomeDigimonLong(idDigimon), recompensaBits,
                recompensaExp, fragmentoSorteado.getDescricao_item(), quantiaFragmentos);

    }

    private void atualizarCacada(Cacada cacada) {
        cacada.setRecompensaResgatada(true);
        cacada.setUltimaAlteracao(LocalDateTime.now());
        cacadaRepository.save(cacada);
    }
}
