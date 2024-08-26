package com.example.api.service;

import com.example.api.enumerator.EnumDigimonRookie;
import com.example.api.enumerator.EnumNivelDigimon;
import com.example.api.model.Atributos;
import com.example.api.model.entity.Digimon;
import com.example.api.repository.DigimonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DigimonService {

    @Autowired
    private DigimonRepository digimonRepository;

    @Autowired
    LogService logService;
    @Autowired
    JogadorService jogadorService;
    @Autowired
    RegistroConquistasService registroConquistasService;

    public List<Digimon> getDigimonByUsuario(String nomeUsuario) {
        return digimonRepository.getDigimonByIdJogador(jogadorService.getIdByUsuario(nomeUsuario));
    }

    public void recuperarTodoHp(Long idDigimon){
        Digimon digimon = getDigimonById(idDigimon);
        Atributos atributos = digimon.getAtributos();
        atributos.setPontosVida(50 * (digimon.getNivel()));
        digimon.setAtributos(atributos);
        digimonRepository.save(digimon);
    }

    public Digimon selecionarDigimon(Digimon digimonSelecionado) throws Exception {
        if (!jogadorService.verificarJogadorExistente(digimonSelecionado.getIdJogador())) {
            logService.logAction("Digimon selecionado - Erro", "Jogador não encontrado");
            throw new RuntimeException("Jogador não encontrado");
        }
        if (digimonSelecionado.getIdRookie() <= 0) {
            logService.logAction("Digimon selecionado - Erro", "o parametro idRookie precisa ser maior que 0");
            throw new RuntimeException("o parametro idRookie precisa ser maior que 0");
        }
//        if (digimonRepository.existsByNome(digimonSelecionado.getNome())) {
//            logService.logAction("Digimon selecionado - Erro", "Esse nome de digimon já foi escolhido");
//            throw new RuntimeException("Esse nome de digimon já foi escolhido");
//        }

        EnumDigimonRookie rookie = EnumDigimonRookie.getEnumById(digimonSelecionado.getIdRookie());
        String nomeJogador = jogadorService.getNomeJogador(digimonSelecionado.getIdJogador());

        Atributos atributos = new Atributos();
        digimonSelecionado.setAtributos(atributos);

        logService.logAction("Digimon selecionado", "Jogador " + nomeJogador + " selecionou o digimon " + rookie);
        return digimonRepository.save(digimonSelecionado);
    }

    public Boolean verificarExistenciaDigimon(Long idDigimon) {
        return digimonRepository.existsById(idDigimon);
    }

    public String getNomeDigimon(Long idDigimon) {
        return digimonRepository.findById(idDigimon)
                .map(Digimon::getNome)
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));
    }

    public String getProxTierDigimon(Long idDigimon) {
        Digimon digimon = digimonRepository.findById(idDigimon)
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));
        String tier;
        if(digimon.getIdMega() != 0){
            tier = "Jogress";
        }
        else if (digimon.getIdUltimate() != 0) {
            tier = "Mega";
        }
        else if (digimon.getIdChampion() != 0) {
            tier = "Ultimate";
        }
        else {
            tier = "Champion";
        }
        return tier;
    }

    public Digimon getDigimonById(Long idDigimon) {
        return digimonRepository.findById(idDigimon)
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));
    }

    public void atualizarDigimonExpBits(Long idDigimon, int recompensaExp, int recompensaBits) {
        Digimon digimon = getDigimonById(idDigimon);
        atualizarExpDigimon(digimon, recompensaExp);
        atualizarBitsDigimon(digimon, recompensaBits);
        digimon.setDataUltimaAlteracao(LocalDateTime.now());
        digimonRepository.save(digimon);
    }

    public void atualizarDigimonBits(Long idDigimon, int recompensaBits) {
        Digimon digimon = getDigimonById(idDigimon);
        atualizarBitsDigimon(digimon, recompensaBits);
        digimon.setDataUltimaAlteracao(LocalDateTime.now());
        digimonRepository.save(digimon);
    }

    @Transactional
    public void salvarDigimonTelaAtributos(Digimon digimon){
        digimonRepository.save(digimon);
    }

    @Transactional
    public void atualizarBitsDigimon(Digimon digimon, int recompensaBits) {
        int bitsDigimon = digimon.getBits();
        if (bitsDigimon != 0) {
            digimon.setBits(bitsDigimon + recompensaBits);
        } else {
            digimon.setBits(recompensaBits);
        }
        logService.logAction("Bits Digimon", "O Digimon " + digimon.getNome() + " recebeu " + recompensaBits + " bits");
    }

    @Transactional
    public void atualizarExpDigimon(Digimon digimon, int recompensaExp) {
        int expDigimon = digimon.getPontosExperiencia();
        if (expDigimon != 0) {
            digimon.setPontosExperiencia(expDigimon + recompensaExp);
        } else {
            digimon.setPontosExperiencia(recompensaExp);
        }
        logService.logAction("Experiência Digimon", "O Digimon " + digimon.getNome() + " recebeu " + recompensaExp + " pontos de experiência");

        int nivelAtual = digimon.getNivel();
        if (nivelAtual != 100) {
            int novoNivel = verificarNivel(digimon);
            if (novoNivel > nivelAtual) {
                digimon.setNivel(novoNivel);
                digimon.setPontosExperiencia(0);
            }
        }
    }

    public int verificarNivel(Digimon digimon) {
        int nivelAtual = digimon.getNivel();
        int experienciaAtual = digimon.getPontosExperiencia();
        int experienciaNecessaria = EnumNivelDigimon.getExperienciaNecessaria(nivelAtual);
        while (experienciaAtual >= experienciaNecessaria) {
            nivelAtual++;
            experienciaAtual -= experienciaNecessaria;
            experienciaNecessaria = EnumNivelDigimon.getExperienciaNecessaria(nivelAtual);
            atualizarVidaDigimon(digimon);
            logService.logAction("Subida de Nível", "O Digimon " + digimon.getNome() + " subiu para o nível " + nivelAtual + " com sucesso.");
            registroConquistasService.salvarRegistroSubirNivel(digimon);
        }
        return nivelAtual;
    }

    public Digimon atualizarVidaDigimon(Digimon digimon){
        System.out.println("Atualizar Vida Digimon");
        Atributos atributos = digimon.getAtributos();
        int vidaAtual = atributos.getPontosVida();
        int vidaNova = 50 * (digimon.getNivel()+1);
        atributos.setPontosVida(vidaNova);
        digimon.setAtributos(atributos);
        return digimonRepository.save(digimon);
    }

    public Long getIdJogadorByDigimonId(Long idDigimon) {
        return digimonRepository.findById(idDigimon)
                .map(Digimon::getIdJogador)
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));
    }

    public Digimon getJogadorByDigimonId(Long idDigimon) {
        return digimonRepository.findById(idDigimon)
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));
    }


    public boolean getDigimonByIdJogador(int idJogador) {
        return digimonRepository.existsByIdJogador(idJogador);

    }

    public String getIdByDescricao (String descricao){
        return EnumDigimonRookie.getIdByDescricao(descricao);
    }


}
