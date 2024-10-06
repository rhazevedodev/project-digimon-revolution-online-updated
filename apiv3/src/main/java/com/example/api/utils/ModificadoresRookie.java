package com.example.api.utils;

import com.example.api.entity.AtributosModificadores;
import com.example.api.enumerator.EnumDigimonRookie;

public class ModificadoresRookie {

    public static AtributosModificadores definidirModificadoresRookies(AtributosModificadores atributosModificadores, EnumDigimonRookie rookie) {
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

}
