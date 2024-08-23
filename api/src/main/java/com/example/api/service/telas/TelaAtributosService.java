package com.example.api.service.telas;

import com.example.api.enumerator.EnumDigimonRookie;
import com.example.api.model.Atributos;
import com.example.api.model.entity.Digimon;
import com.example.api.model.request.RequestCarregarTelaAtributos;
import com.example.api.model.request.RequestSalvarAtributos;
import com.example.api.service.DigimonService;
import com.example.api.service.LogService;
import com.example.api.service.PremiumService;
import com.example.api.service.auxiliar.AuxDigimonService;
import com.example.api.service.auxiliar.AuxPremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TelaAtributosService {

    @Autowired
    private DigimonService digimonService;
    @Autowired
    private PremiumService premiumService;
    @Autowired
    private LogService logService;
    @Autowired
    private AuxPremiumService auxPremiumService;
    @Autowired
    private AuxDigimonService auxDigimonService;

    /**
     * Carrega as informações da tela de atributos para um Digimon específico.
     *
     * @param idDigimon o ID do Digimon
     * @return um mapa contendo as informações da tela de atributos
     */
    public Map<String,Object> carregarTelaAtributos(Long idDigimon){
        Map<String, Object> response = new LinkedHashMap<>();

        Digimon digimon = digimonService.getDigimonById(idDigimon);
        String imagem =  EnumDigimonRookie.getUrlImgById(digimon.getIdRookie()).toLowerCase();

        //CARREGAR INFORMACOES PREMIUM
        String statusPremium = premiumService.validarPremium(idDigimon);
        Map<String, Object> premium = premiumService.buscarPeriodoPremium(idDigimon);

        auxPremiumService.carregarDadosPremium(idDigimon, response);
        auxDigimonService.carregarDadosDigimonGeral(idDigimon, response);
        response.put("bits_digimon", digimon.getBits());
        response.put("atributo1_forca", digimon.getAtributos().getPontosForca());
        response.put("atributo2_inteligencia", digimon.getAtributos().getPontosInteligencia());
        response.put("atributo3_conhecimento", digimon.getAtributos().getPontosConhecimento());
        response.put("atributo4_agilidade", digimon.getAtributos().getPontosAgilidade());

        return response;
    }

    /**
     * Salva os novos atributos de um Digimon com base nas informações fornecidas.
     *
     * @param request o objeto de solicitação contendo os novos atributos e o ID do Digimon
     * @return um mapa contendo uma mensagem de sucesso
     */
    public Map<String,Object> salvarAtributos(RequestSalvarAtributos request){
        Digimon digimon = digimonService.getDigimonById(request.getIdDigimon());
        logService.logAction("Salvar Atributos", "Atributos alterados para o digimon " + digimon.getNome());

        //ATRIBUTOS ANTES DA ALTERACAO
        int forcaAntes = digimon.getAtributos().getPontosForca();
        int inteligenciaAntes = digimon.getAtributos().getPontosInteligencia();
        int conhecimentoAntes = digimon.getAtributos().getPontosConhecimento();
        int agilidadeAntes = digimon.getAtributos().getPontosAgilidade();
        logService.logAction("Salvar Atributos", "Atributos antigos "+ digimon.getNome()+": " + forcaAntes + " " + inteligenciaAntes + " " + conhecimentoAntes + " " + agilidadeAntes);

        //SETTANDO NOVOS ATRIBUTOS
        Atributos atributos = digimon.getAtributos();
        atributos.setPontosForca(request.getForca());
        atributos.setPontosInteligencia(request.getInteligencia());
        atributos.setPontosConhecimento(request.getConhecimento());
        atributos.setPontosAgilidade(request.getAgilidade());
        logService.logAction("Salvar Atributos", "Atributos novos "+ digimon.getNome()+": " + request.getForca() + " " + request.getInteligencia() + " " + request.getConhecimento() + " " + request.getAgilidade());

        System.out.println("Atributos antes: " + forcaAntes + " " + inteligenciaAntes + " " + conhecimentoAntes + " " + agilidadeAntes);
        System.out.println("Atributos depois: " + request.getForca() + " " + request.getInteligencia() + " " + request.getConhecimento() + " " + request.getAgilidade());
        System.out.println("Bits antes: " + digimon.getBits());
        System.out.println("Bits gastos: " + request.getCustoTotal());
        System.out.println("Bits depois: " + (digimon.getBits() - request.getCustoTotal()));

        digimon.setAtributos(atributos);

        //SUBTRAINDO OS BITS GASTOS
        logService.logAction("Salvar Atributos", "Bits antes "+ digimon.getNome()+": " + digimon.getBits());
        logService.logAction("Salvar Atributos", "Bits gastos "+ digimon.getNome()+": " + request.getCustoTotal());
        logService.logAction("Salvar Atributos", "Bits depois "+ digimon.getNome()+": " + (digimon.getBits() - request.getCustoTotal()));
        digimon.setBits(digimon.getBits() - request.getCustoTotal());

        System.out.println(digimon);
        digimonService.salvarDigimonTelaAtributos(digimon);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("mensagem", "Atributos salvos com sucesso!");

        return response;
    }

}
