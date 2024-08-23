package com.example.api.service;

import com.example.api.enumerator.EnumFragmentosDigievolucao;
import com.example.api.model.response.ResponseResgatarRecompensaCacadaDTO;
import com.example.api.model.entity.Cacada;
import com.example.api.model.entity.TempoDisponivelCacada;
import com.example.api.repository.CacadaRepository;
import com.example.api.repository.TempoDisponivelCacadaRepository;
import com.example.api.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CacadaService {

    @Autowired
    private CacadaRepository cacadaRepository;
    @Autowired
    private TempoDisponivelCacadaRepository tempoDisponivelRepository;
    @Autowired
    private DigimonService digimonService;
    @Autowired
    private InventarioService inventarioService;
    @Autowired
    LogService logService;
    @Autowired
    RegistroConquistasService registroConquistasService;

    public boolean verificarCacadaEmAndamento(Long idDigimon) {
        return cacadaRepository.existsByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
    }

    public boolean validarResgateDisponivelCacada(Long idDigimon) {
        if (!verificarCacadaEmAndamento(idDigimon)) {
            return false;
        }
        Cacada cacada = cacadaRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
        LocalDateTime horaResgateDisponivel = cacada.getHoraResgateDisponivel();
        return LocalDateTime.now().isAfter(horaResgateDisponivel);
    }

    public String buscarTempoResgateCacadaEmAndamento(Long idDigimon) {
        if (!verificarCacadaEmAndamento(idDigimon)) {
            return "Não há caçada em andamento";
        }
        Cacada cacada = cacadaRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
        String dataFormatada = Data.formatarDataCadastroParaTelaStatus(cacada.getHoraResgateDisponivel());
        return dataFormatada;
    }

    public int verificarTempoDisponivelCacada(Long idDigimon) {
        if (!digimonService.verificarExistenciaDigimon(idDigimon)) {
            logService.logAction("Tempo Disponivel Cacada - Erro", "Digimon não encontrado");
            throw new RuntimeException("Digimon não encontrado");
        }
        LocalDate hoje = LocalDate.now();
        Optional<TempoDisponivelCacada> registro = tempoDisponivelRepository.findByIdDigimonAndDataCadastro(idDigimon, hoje);

        // Verifica se o registro está vazio ou se hoje é final de semana
        if (registro.isEmpty() || Data.validarFinalDeSemana(hoje)) {
            int tempoDisponivel = (Feriados.isFeriado(hoje) || Data.validarFinalDeSemana(hoje)) ? 120 : 60;
            logService.logAction("Tempo Disponivel Cacada", "Tempo disponível para caçada: " + tempoDisponivel + " minutos");

            // Salva o novo registro se necessário
            if (registro.isEmpty()) {
                tempoDisponivelRepository.save(new TempoDisponivelCacada(idDigimon, hoje, tempoDisponivel));
            }
            return tempoDisponivel;
        }

        // Retorna o tempo disponível do registro existente
        return registro.get().getTempoDisponivel();
    }

    public Cacada iniciarCacada(int minutosEscolhidos, Long idDigimon) {

        if (verificarCacadaEmAndamento(idDigimon)) {
            logService.logAction("Iniciar Cacada - Erro", "Já existe uma caçada em andamento para esse digimon");
            throw new RuntimeException("Já existe uma caçada em andamento para esse digimon");
        }
        if (minutosEscolhidos <= 0) {
            logService.logAction("Iniciar Cacada - Erro", "O tempo escolhido não pode ser menor ou igual a zero");
            throw new RuntimeException("O tempo escolhido não pode ser menor ou igual a zero");
        }
        if (minutosEscolhidos > verificarTempoDisponivelCacada(idDigimon)) {
            logService.logAction("Iniciar Cacada - Erro", "O tempo escolhido é maior que o tempo disponível");
            throw new RuntimeException("O tempo escolhido é maior que o tempo disponível");
        }

        logService.logAction("Tempo Disponivel Cacada", "O digimon " +
                digimonService.getNomeDigimon(idDigimon) + " tem " + verificarTempoDisponivelCacada(idDigimon) +
                " minutos disponíveis para caçada");

        TempoDisponivelCacada tempoDisponivelCacada =
                tempoDisponivelRepository.findTempoDisponivelCacadaByIdDigimonAndDataCadastro(idDigimon, LocalDate.now());
        tempoDisponivelCacada.setTempoDisponivel(tempoDisponivelCacada.getTempoDisponivel() - minutosEscolhidos);
        tempoDisponivelCacada.setDataUltimaAlteracao(LocalDateTime.now());
        tempoDisponivelRepository.save(tempoDisponivelCacada);

        logService.logAction("Iniciar Cacada", "Caçada iniciada para o digimon " +
                digimonService.getNomeDigimon(idDigimon) + " com duração de " + minutosEscolhidos + " minutos");
        return cacadaRepository.save(new Cacada(idDigimon, minutosEscolhidos,
                LocalDateTime.now().plusMinutes(minutosEscolhidos), false));
    }

    public ResponseResgatarRecompensaCacadaDTO resgatarRecompensa(Long idDigimon) {
        if (!verificarCacadaEmAndamento(idDigimon)) {
            logService.logAction("Resgatar Recompensa - Erro", "Não há caçada em andamento para esse digimon");
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
        registroConquistasService.salvarRegistroCacadaMissao(digimonService.getJogadorByDigimonId(idDigimon), recompensaBits, quantiaFragmentos, "CACADA");
        digimonService.atualizarDigimonExpBits(idDigimon, recompensaExp, recompensaBits);

        logService.logAction("Resgatar Recompensa", "Recompensa resgatada para o digimon " + digimonService.getNomeDigimon(idDigimon));

        System.out.println("--------------------");
        System.out.println("RESULTADO CAÇADA");
        System.out.println("TIER DIGIMON DA TROCA: " + digimonService.getProxTierDigimon(idDigimon));
        System.out.println("--------------------");
        System.out.println("DIGIMON: " + digimonService.getNomeDigimon(idDigimon));
        System.out.println("ID DIGIMON: " + idDigimon);
        System.out.println("RECOMPENSA BITS: " + recompensaBits);
        System.out.println("RECOMPENSA EXP: " + recompensaExp);
        System.out.println("TIER FRAGMENTO: " + fragmentoSorteado.getTier());
        System.out.println("QUANTIA FRAGMENTOS: " + quantiaFragmentos);
        System.out.println("FRAGMENTO: " + fragmentoSorteado.name());
        System.out.println("--------------------");

        return new ResponseResgatarRecompensaCacadaDTO(digimonService.getNomeDigimon(idDigimon), recompensaBits,
                recompensaExp, fragmentoSorteado.getDescricao_item(), quantiaFragmentos);

    }

    private void atualizarCacada(Cacada cacada) {
        cacada.setRecompensaResgatada(true);
        cacada.setUltimaAlteracao(LocalDateTime.now());
        cacadaRepository.save(cacada);
        logService.logAction("Caçada - Resgatar Recompensa", "Recompensa resgatada para o digimon " + digimonService.getNomeDigimon(cacada.getIdDigimon()));
    }


}
