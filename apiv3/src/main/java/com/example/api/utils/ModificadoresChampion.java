package com.example.api.utils;

import com.example.api.entity.AtributosModificadores;
import com.example.api.enumerator.EnumDigimonChampion;

public class ModificadoresChampion {

    public static AtributosModificadores definirModificadoresChampion(AtributosModificadores atributosModificadores, EnumDigimonChampion champion) {
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

}
