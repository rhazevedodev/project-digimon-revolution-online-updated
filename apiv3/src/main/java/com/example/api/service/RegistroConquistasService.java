package com.example.api.service;

import com.example.api.entity.RegistroConquistas;
import com.example.api.repository.RegistroConquistasRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RegistroConquistasService {

    private final RegistroConquistasRepository registroConquistasRepository;

    public RegistroConquistasService(RegistroConquistasRepository registroConquistasRepository) {
        this.registroConquistasRepository = registroConquistasRepository;
    }

    public Map<String, Object> carregarConquistas(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();
        RegistroConquistas registro = registroConquistasRepository.findByIdDigimon(idDigimon);

        if (registro == null) {
            registro = new RegistroConquistas();
        }

        int bitsObtidos = registro.getBitsObtidos() ;
        int experienciaObtida = registro.getExpObtida();
        int cacadasConcluidas = registro.getCacadasConcluidas() ;
        int missoesConcluidas = registro.getMissoesConcluidas() ;

        preencherResponseConquistas(response, bitsObtidos, experienciaObtida, cacadasConcluidas, missoesConcluidas);
        return response;
    }

    private void preencherResponseConquistas(Map<String, Object> response, int bitsObtidos, int experienciaObtida, int cacadasConcluidas, int missoesConcluidas) {
        response.put("bitsObtidos", bitsObtidos);
        response.put("experienciaObtida", experienciaObtida);
        response.put("cacadasConcluidas", cacadasConcluidas);
        response.put("missoesConcluidas", missoesConcluidas);
    }
}
