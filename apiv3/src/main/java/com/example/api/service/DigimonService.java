package com.example.api.service;

import com.example.api.entity.Atributos;
import com.example.api.entity.AtributosElementos;
import com.example.api.entity.AtributosModificadores;
import com.example.api.entity.Digimon;
import com.example.api.entity.dto.ResponseContinuarJornada;
import com.example.api.enumerator.*;
import com.example.api.repository.DigimonRepository;
import com.example.api.utils.ModificadoresRookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DigimonService {

    private static final Logger logger = LoggerFactory.getLogger(DigimonService.class);

    private DigimonRepository digimonRepository;
    private JogadorService jogadorService;
    private ConquistasService conquistasService;

    public DigimonService(DigimonRepository digimonRepository, JogadorService jogadorService, ConquistasService conquistasService) {
        this.digimonRepository = digimonRepository;
        this.jogadorService = jogadorService;
        this.conquistasService = conquistasService;
    }

    public boolean getDigimonByIdJogador(int idJogador) {
        return digimonRepository.existsByIdJogador(idJogador);

    }

    public Boolean verificarExistenciaDigimon(Long idDigimon) {
        return digimonRepository.existsById(idDigimon);
    }

    @Transactional
    public void salvarDigimonTelaAtributos(Digimon digimon){
        digimonRepository.save(digimon);
    }

    public String getIdByDescricao(String descricao) {
        return EnumDigimonRookie.getIdByDescricao(descricao);
    }

    public boolean getNomeDigimon(String nomeDigimon) {
        return digimonRepository.existsByNome(nomeDigimon);
    }

    public String getNomeDigimonLong(Long idDigimon) {
        return digimonRepository.findById(idDigimon)
                .map(Digimon::getNome)
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));
    }

    public Digimon selecionarDigimon(Digimon digimonSelecionado) {
        if (!jogadorService.verificarJogadorExistente(digimonSelecionado.getIdJogador())) {
            throw new RuntimeException("Jogador não encontrado");
        }
        if (digimonSelecionado.getIdRookie() <= 0) {
            throw new RuntimeException("o parametro idRookie precisa ser maior que 0");
        }

        EnumDigimonRookie rookie = EnumDigimonRookie.getEnumById(digimonSelecionado.getIdRookie());
        String nomeJogador = jogadorService.getNomeJogador(digimonSelecionado.getIdJogador());

        String elementoRookie = EnumDigimonRookie.getElementoByEnum(rookie);

        int idElementoPrimitivoRookie = EnumElementos.getIdElemento(elementoRookie);

        Atributos atributos = new Atributos();
        digimonSelecionado.setAtributos(atributos);

        AtributosModificadores atributosModificadores = new AtributosModificadores();
        digimonSelecionado.setAtributosModificadores(ModificadoresRookie.definidirModificadoresRookies(atributosModificadores, rookie));

        AtributosElementos atributosElementos = new AtributosElementos();
        atributosElementos.setElementoPrimitivo(idElementoPrimitivoRookie);
        atributosElementos.setPontosElementoPrimitivo(1);
        digimonSelecionado.setAtributosElementos(atributosElementos);

        //INICIALIZAR CONQUISTAS
        conquistasService.inicializarConquistas(digimonSelecionado);

        return digimonRepository.save(digimonSelecionado);
    }

    public List<ResponseContinuarJornada> carregarDigimonsContinuarJornada(String nomeUsuario){
        List<Digimon> digimons = getDigimonByUsuario(nomeUsuario);
        List<ResponseContinuarJornada> response = new ArrayList<>();
        for(Digimon digimon : digimons){
            Map<String, Object> urlImagemDigimon = carregarImagemDigimon(digimon.getId());
            // Assuming the correct constructor takes (Digimon, String)
            String urlImagem = (String) urlImagemDigimon.get("url_imagem_digimon");
            response.add(new ResponseContinuarJornada(digimon, urlImagem));
        }
        return response;
    }

    public Map<String, Object> carregarImagemDigimon(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();
        Digimon digimon = getDigimonById(idDigimon);
        String urlImg = null;

        if (digimon.getIdMega() != 0) {
            urlImg = EnumDigimonMega.getUrlImgById(digimon.getIdMega()).toLowerCase();
        } else if (digimon.getIdUltimate() != 0) {
            urlImg = EnumDigimonUltimate.getUrlImgById(digimon.getIdUltimate()).toLowerCase();
        } else if (digimon.getIdChampion() != 0) {
            urlImg = EnumDigimonChampion.getUrlImgById(digimon.getIdChampion()).toLowerCase();
        } else {
            urlImg = EnumDigimonRookie.getUrlImgById(digimon.getIdRookie()).toLowerCase();
        }

        response.put("url_imagem_digimon", urlImg);
        return response;
    }

    public List<Digimon> getDigimonByUsuario(String nomeUsuario) {
        return digimonRepository.getDigimonByIdJogador(jogadorService.getIdByUsuario(nomeUsuario));
    }

    public Digimon getDigimonById(Long idDigimon) {
        return digimonRepository.findById(idDigimon)
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));
    }

    public Long getIdJogadorByDigimonId(Long idDigimon) {
        return digimonRepository.findById(idDigimon)
                .map(Digimon::getIdJogador)
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

    public void atualizarDigimonExpBits(Long idDigimon, int recompensaExp, int recompensaBits) {
        Digimon digimon = getDigimonById(idDigimon);
        atualizarExpDigimon(digimon, recompensaExp);
        atualizarBitsDigimon(digimon, recompensaBits);
        digimon.setDataUltimaAlteracao(LocalDateTime.now());
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
    }

    @Transactional
    public void atualizarExpDigimon(Digimon digimon, int recompensaExp) {
        int expDigimon = digimon.getPontosExperiencia();
        if (expDigimon != 0) {
            digimon.setPontosExperiencia(expDigimon + recompensaExp);
        } else {
            digimon.setPontosExperiencia(recompensaExp);
        }

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
//            registroConquistasService.salvarRegistroSubirNivel(digimon);
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

    @Scheduled(fixedRate = 3600000)
    public void recuperarEnergiaVida(){
//        Digimon digimon = getDigimonById(idDigimon);
//        Atributos atributos = digimon.getAtributos();
//        atributos.setPontosVida(50 * digimon.getNivel());
//        atributos.setPontosEnergia(100);
//        digimon.setAtributos(atributos);
//        digimonRepository.save(digimon);
        digimonRepository.atualizarTodosAtributos();
        logger.info("Vida e energia de todos os digimons foram restaurados");
    }

    public void atualizarEnergiaDigimon(Digimon digimon, int i) {
        Atributos atributos = digimon.getAtributos();
        int energiaAtual = atributos.getPontosEnergia();
        atributos.setPontosEnergia(energiaAtual + i);
        digimon.setAtributos(atributos);
        digimonRepository.save(digimon);
    }

    @Transactional
    public void save(Digimon digimon) {
        digimonRepository.save(digimon);
    }


    public Map<String, Object> carregarVidaEnergia(Long idDigimonUsuario) {
        Map<String, Object> response = new LinkedHashMap<>();

        Digimon digimon = getDigimonById(idDigimonUsuario);
        response.put("nivel", digimon.getNivel());
        response.put("vida", digimon.getAtributos().getPontosVida());
        response.put("energia", digimon.getAtributos().getPontosEnergia());

        return response;
    }
}
