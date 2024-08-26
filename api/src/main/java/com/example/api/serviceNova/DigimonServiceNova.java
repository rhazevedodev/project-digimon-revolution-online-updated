package com.example.api.serviceNova;

import com.example.api.enumerator.EnumDigimonRookie;
import com.example.api.model.Atributos;
import com.example.api.model.entity.Digimon;
import com.example.api.repository.DigimonRepository;
import com.example.api.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigimonServiceNova {

    @Autowired
    private DigimonRepository digimonRepository;

    @Autowired
    JogadorService jogadorService;

    public boolean getDigimonByIdJogador(int idJogador) {
        return digimonRepository.existsByIdJogador(idJogador);

    }

    public List<Digimon> getDigimonByUsuario(String nomeUsuario) {
        return digimonRepository.getDigimonByIdJogador(jogadorService.getIdByUsuario(nomeUsuario));
    }

    public Digimon selecionarDigimon(Digimon digimonSelecionado) throws Exception {
        if (!jogadorService.verificarJogadorExistente(digimonSelecionado.getIdJogador())) {
            throw new RuntimeException("Jogador não encontrado");
        }
        if (digimonSelecionado.getIdRookie() <= 0) {
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

        return digimonRepository.save(digimonSelecionado);
    }

    public String getIdByDescricao (String descricao){
        return EnumDigimonRookie.getIdByDescricao(descricao);
    }

    public boolean getNomeDigimon(String nomeDigimon){
        return digimonRepository.existsByNome(nomeDigimon);
    }

}
