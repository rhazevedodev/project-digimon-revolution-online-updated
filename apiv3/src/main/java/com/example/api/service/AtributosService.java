package com.example.api.service;

import com.example.api.entity.Atributos;
import com.example.api.entity.Digimon;
import com.example.api.entity.Jogador;
import com.example.api.entity.dto.RequestSalvarAtributos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AtributosService {

    private static final Logger logger = LoggerFactory.getLogger(AtributosService.class);

    private final DigimonService digimonService;
    private final JogadorService jogadorService;

    public AtributosService(DigimonService digimonService, JogadorService jogadorService) {
        this.digimonService = digimonService;
        this.jogadorService = jogadorService;
    }

    public Map<String, Object> carregarTelaAtributos(Long idDigimon){
        Map<String, Object> response = new LinkedHashMap<>();

        Digimon digimon = digimonService.getDigimonById(idDigimon);
        Jogador jogador = jogadorService.getJogadorById(digimon.getIdJogador());

        response.putAll(carregarAtributos(digimon));
        response.put("nivel", digimon.getNivel());

        return response;
    }

    public Map<String, Object> carregarAtributos(Digimon digimon){
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("digimonBits", digimon.getBits());
        response.put("forca", digimon.getAtributos().getPontosForca());
        response.put("inteligencia", digimon.getAtributos().getPontosInteligencia());
        response.put("conhecimento", digimon.getAtributos().getPontosConhecimento());
        response.put("agilidade", digimon.getAtributos().getPontosAgilidade());
        response.put("energia", digimon.getAtributos().getPontosEnergia());
        response.put("vida", digimon.getAtributos().getPontosVida());

        return response;
    }

    public Map<String,Object> salvarAtributos(RequestSalvarAtributos request){
        Digimon digimon = digimonService.getDigimonById(request.getIdDigimon());

        //ATRIBUTOS ANTES DA ALTERACAO
        int forcaAntes = digimon.getAtributos().getPontosForca();
        int inteligenciaAntes = digimon.getAtributos().getPontosInteligencia();
        int conhecimentoAntes = digimon.getAtributos().getPontosConhecimento();
        int agilidadeAntes = digimon.getAtributos().getPontosAgilidade();

        //SETTANDO NOVOS ATRIBUTOS
        Atributos atributos = digimon.getAtributos();
        atributos.setPontosForca(request.getForca());
        atributos.setPontosInteligencia(request.getInteligencia());
        atributos.setPontosConhecimento(request.getConhecimento());
        atributos.setPontosAgilidade(request.getAgilidade());

        System.out.println("Atributos antes: " + forcaAntes + " " + inteligenciaAntes + " " + conhecimentoAntes + " " + agilidadeAntes);
        System.out.println("Atributos depois: " + request.getForca() + " " + request.getInteligencia() + " " + request.getConhecimento() + " " + request.getAgilidade());
        System.out.println("Bits antes: " + digimon.getBits());
        System.out.println("Bits gastos: " + request.getCustoTotal());
        System.out.println("Bits depois: " + (digimon.getBits() - request.getCustoTotal()));

        digimon.setAtributos(atributos);

        //SUBTRAINDO OS BITS GASTOS
        digimon.setBits(digimon.getBits() - request.getCustoTotal());

        System.out.println(digimon);
        digimonService.salvarDigimonTelaAtributos(digimon);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("mensagem", "Atributos salvos com sucesso!");

        return response;
    }
}

