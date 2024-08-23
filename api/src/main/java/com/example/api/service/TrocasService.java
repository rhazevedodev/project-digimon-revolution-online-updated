package com.example.api.service;

import com.example.api.enumerator.EnumFragmentosDigievolucao;
import com.example.api.enumerator.EnumTrocaDeItems;
import com.example.api.model.entity.Digimon;
import com.example.api.model.entity.Inventario;
import com.example.api.model.request.RequestTrocarFragmentosPorBau;
import com.example.api.repository.DigimonRepository;
import com.example.api.repository.InventarioRepository;
import com.example.api.utils.Recompensas;
import com.example.api.utils.SorteioFragmentosDigievolucao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TrocasService {

    @Autowired
    InventarioRepository inventarioRepository;
    @Autowired
    DigimonRepository digimonRepository;
    @Autowired
    LogService logService;
    @Autowired
    InventarioService inventarioService;

    @Transactional
    public String trocarFragmentosPorBau(RequestTrocarFragmentosPorBau request) {
        //VERIFICACOES INICIAIS
        Inventario inventarioInicioTransacao = inventarioRepository
                .findByIdItemAndIdDigimonAndIsFragmentoTrue(request.getIdItem(), request.getIdDigimon())
                .orElseThrow(() -> new RuntimeException("Inventário não encontrado"));

        String idFragmentoInicioTransacao = String.valueOf(request.getIdItem());
        String tierFragmentoTransacao = EnumFragmentosDigievolucao
                .getEnumFragmentosDigievolucaoBy(request.getIdItem()).getTier();

        int quantiaFragmentosInicioTransacao = inventarioInicioTransacao.getQuantidade();
        int quantidadeMinimaParaTroca = EnumTrocaDeItems.getQuantidadeMinimaParaTrocaByTier(tierFragmentoTransacao);

        if (quantiaFragmentosInicioTransacao < quantidadeMinimaParaTroca) {
            throw new RuntimeException("Quantia insuficiente de fragmentos para troca");
        }

        //ATUALIZA INVENTARIO DE FRAGMENTOS
        int quantiaFragmentosFinalTransacao = quantiaFragmentosInicioTransacao - quantidadeMinimaParaTroca;

        if (quantiaFragmentosFinalTransacao == 0) {
            inventarioRepository.delete(inventarioInicioTransacao);

        } else {
            inventarioInicioTransacao.setQuantidade(quantiaFragmentosFinalTransacao);
            inventarioRepository.save(inventarioInicioTransacao);
        }

        //ATUALIZA BITS DO DIGIMON
        Optional<Digimon> optionalDigimon  = digimonRepository.findById(request.getIdDigimon());
        Digimon digimon = optionalDigimon.orElseThrow(() -> new RuntimeException("Digimon não encontrado"));

        int bitsDigimonInicioTransacao = digimon.getBits();
        int custoTroca = EnumTrocaDeItems.getCustoByTier(tierFragmentoTransacao);
        if (bitsDigimonInicioTransacao < custoTroca) {
            throw new RuntimeException("Bits insuficientes para troca");
        }
        digimon.setBits(bitsDigimonInicioTransacao - custoTroca);
        digimonRepository.save(digimon);

        //SORTEIA NOVO FRAGMENTO
        EnumFragmentosDigievolucao novoFragmento = SorteioFragmentosDigievolucao.sortearNovoFragmento
                (tierFragmentoTransacao, idFragmentoInicioTransacao);

        //REGISTRA A TROCA E RECOMPENSA
        int quantiaNovosFragmentos = Recompensas.gerarQuantidadeFragmentos5A10();

        //ADICIONA NOVO FRAGMENTO AO INVENTARIO DO DIGIMON
        inventarioService.adicionarFragmentoAoInventario(digimon.getId(), novoFragmento, quantiaNovosFragmentos);

        //LOGS
        String digimonFragmentoInicioTransacao = EnumFragmentosDigievolucao
                .getEnumFragmentosDigievolucaoBy(request.getIdItem()).toString();

        System.out.println("--------------------");
        System.out.println("RESULTADO TROCA");
        System.out.println("TIER DIGIMON DA TROCA: " + tierFragmentoTransacao);
        System.out.println("--------------------");
        System.out.println("DIGIMON FRAGMENTO INICIO TRANSACAO: " + digimonFragmentoInicioTransacao);
        System.out.println("ID FRAGMENTO INICIO TRANSACAO: " + idFragmentoInicioTransacao);
        System.out.println("QUANTIA FRAGMENTOS INICIO TRANSACAO: " + quantiaFragmentosInicioTransacao);
        System.out.println("--------------------");
        System.out.println("DIGIMON NOVO FRAGMENTO: " + novoFragmento.name());
        System.out.println("ID NOVO FRAGMENTO: " + novoFragmento.getId());
        System.out.println("QUANTIA NOVO FRAGMENTO: " + quantiaNovosFragmentos);
        System.out.println("--------------------");

        //REGISTRANDO LOGS
        if(quantiaFragmentosFinalTransacao == 0){
            logService.logAction("Troca Fragmento x Baú",
                    "Fragmento de digievolução de " + digimonFragmentoInicioTransacao +
                            " removido do inventário do digimon " + digimon.getNome());
        }
        logService.logAction("Troca Fragmento x Baú",
                    "O digimon " + digimon.getNome() +
                            " trocou 10 fragmentos de digievolução do digimon " + digimonFragmentoInicioTransacao +
                            " por um baú contendo "
                            + quantiaNovosFragmentos + " fragmentos de digievolução de " + novoFragmento.name());
        logService.logAction("Troca Fragmento x Baú", "Foram deduzidos "+custoTroca+" bits do digimon " + digimon.getNome());

        return "";

    }
}
