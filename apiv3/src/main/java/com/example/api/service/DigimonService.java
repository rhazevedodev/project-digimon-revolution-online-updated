package com.example.api.service;

import com.example.api.entity.Atributos;
import com.example.api.entity.AtributosElementos;
import com.example.api.entity.AtributosModificadores;
import com.example.api.entity.Digimon;
import com.example.api.enumerator.*;
import com.example.api.repository.DigimonRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DigimonService {

    private DigimonRepository digimonRepository;
    private JogadorService jogadorService;

    public DigimonService(DigimonRepository digimonRepository, JogadorService jogadorService) {
        this.digimonRepository = digimonRepository;
        this.jogadorService = jogadorService;
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
        digimonSelecionado.setAtributosModificadores(definirModificadorIniciais(atributosModificadores, rookie));

        AtributosElementos atributosElementos = new AtributosElementos();
        atributosElementos.setElementoPrimitivo(idElementoPrimitivoRookie);
        atributosElementos.setPontosElementoPrimitivo(1);
        digimonSelecionado.setAtributosElementos(atributosElementos);

        return digimonRepository.save(digimonSelecionado);
    }



    public AtributosModificadores definirModificadorIniciais(AtributosModificadores atributosModificadores, EnumDigimonRookie rookie) {
         switch(rookie){
            case AGUMON:
                atributosModificadores.setModificadorForca(3);
                atributosModificadores.setModificadorConhecimento(4);
                atributosModificadores.setModificadorInteligencia(1);
                atributosModificadores.setModificadorAgilidade(5);
                // Pode incluir modificador de Sorte se necessário, por exemplo:
                // atributosModificadores.setModificadorSorte(2);
                break;
             case GABUMON:
                 atributosModificadores.setModificadorForca(2);
                 atributosModificadores.setModificadorConhecimento(3);
                 atributosModificadores.setModificadorInteligencia(5);
                 atributosModificadores.setModificadorAgilidade(1);
                 // Atribui outros modificadores se necessário
                 break;
             case GOMAMON:
                 atributosModificadores.setModificadorForca(4);
                 atributosModificadores.setModificadorConhecimento(2);
                 atributosModificadores.setModificadorInteligencia(3);
                 atributosModificadores.setModificadorAgilidade(1);
                 break;
             case PALMON:
                 atributosModificadores.setModificadorForca(5);
                 atributosModificadores.setModificadorConhecimento(1);
                 atributosModificadores.setModificadorInteligencia(4);
                 atributosModificadores.setModificadorAgilidade(3);
                 break;
             case PATAMON:
                 atributosModificadores.setModificadorForca(1);
                 atributosModificadores.setModificadorConhecimento(5);
                 atributosModificadores.setModificadorInteligencia(2);
                 atributosModificadores.setModificadorAgilidade(4);
                 break;
             case PIYOMON:
                 atributosModificadores.setModificadorForca(3);
                 atributosModificadores.setModificadorConhecimento(5);
                 atributosModificadores.setModificadorInteligencia(2);
                 atributosModificadores.setModificadorAgilidade(1);
                 break;
             case TENTOMON:
                 atributosModificadores.setModificadorForca(4);
                 atributosModificadores.setModificadorConhecimento(2);
                 atributosModificadores.setModificadorInteligencia(5);
                 atributosModificadores.setModificadorAgilidade(3);
                 break;
         }
        return atributosModificadores;
    }

    public AtributosModificadores definirModificadoresChampion(AtributosModificadores atributosModificadores, EnumDigimonChampion champion) {
        switch (champion) {
            case GREYMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 7);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 9);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 5);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 10);
                break;
            case GARURUMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 6);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 8);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 10);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 5);
                break;
            case IKKAKUMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 8);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 6);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 7);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 5);
                break;
            case TOGEMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 10);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 5);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 9);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 8);
                break;
            case ANGEMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 5);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 10);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 6);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 9);
                break;
            case BIRDRAMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 7);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 10);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 6);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 5);
                break;
            case KABUTERIMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 8);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 6);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 10);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 7);
                break;
            case GATOMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 6);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 9);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 7);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 10);
                break;
            case EXVEEMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 10);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 5);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 9);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 6);
                break;
            case DEVIMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 5);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 7);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 6);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 9);
                break;
            case KYUBIMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 7);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 9);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 10);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 6);
                break;
            case GARGOMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 6);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 5);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 7);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 10);
                break;
            case STINGMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 5);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 10);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 6);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 8);
                break;
            case AQUILAMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 8);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 7);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 5);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 6);
                break;
            case ANKYLOMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 10);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 6);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 9);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 8);
                break;
            case GROWLMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 7);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 10);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 5);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 9);
                break;
            case ANTYLAMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 6);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 8);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 9);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 10);
                break;
            case HOOKMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 5);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 9);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 10);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 6);
                break;
            case GRIZZLYMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 8);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 6);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 7);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 10);
                break;
            case DINOREXMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 10);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 5);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 9);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 6);
                break;
            case STRIKEDRAMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 7);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 10);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 6);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 5);
                break;
            case MYOTISMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 6);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 9);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 7);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 10);
                break;
            case MEGASEADRAMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 10);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 6);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 5);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 8);
                break;
            case SKULLGREYMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 8);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 9);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 6);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 5);
                break;
            case RISEGREYMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 5);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 10);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 9);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 7);
                break;
            case BOLTMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 6);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 7);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 5);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 10);
                break;
            case DUSKMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 7);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 9);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 5);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 8);
                break;
        }
        return atributosModificadores;
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
}
