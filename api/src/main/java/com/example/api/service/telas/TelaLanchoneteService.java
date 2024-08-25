package com.example.api.service.telas;

import com.example.api.model.entity.Digimon;
import com.example.api.model.request.RequestFazerLanche;
import com.example.api.service.DigimonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelaLanchoneteService {

    @Autowired
    private DigimonService digimonService;

    public void fazerLanche(RequestFazerLanche request) {
        Digimon digimon = digimonService.getDigimonById(Long.valueOf(request.getIdDigimon()));
        if(digimon.getBits() < 1000) {
            throw new RuntimeException("Você não tem bits suficientes para fazer um lanche");
        }
        digimonService.recuperarTodoHp(Long.valueOf(request.getIdDigimon()));
        digimonService.atualizarBitsDigimon(digimon, -1000);
    }
}
