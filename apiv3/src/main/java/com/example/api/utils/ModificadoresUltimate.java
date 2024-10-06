package com.example.api.utils;

import com.example.api.entity.AtributosModificadores;
import com.example.api.enumerator.EnumDigimonUltimate;

public class ModificadoresUltimate {

    public static AtributosModificadores definirModificadoresUltimate(AtributosModificadores atributosModificadores, EnumDigimonUltimate ultimate) {
        switch (ultimate) {
            case METALGREYMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 13);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 14);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 10);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 15);
                break;
            case WEREGARURUMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 12);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 13);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 15);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 10);
                break;
            case ZUDOMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 14);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 11);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 12);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 10);
                break;
            case LILLIMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 15);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 10);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 14);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 13);
                break;
            case MAGNAANGEMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 10);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 15);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 11);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 14);
                break;
            case GARUDAMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 13);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 15);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 11);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 10);
                break;
            case MEGAKABUTERIMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 14);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 11);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 15);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 12);
                break;
//            case ANGEWOMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 11);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 14);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 12);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 15);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 10);
//                break;
//            case PAILDRAMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 15);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 10);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 14);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 11);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 13);
//                break;
//            case MYOTISMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 10);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 12);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 11);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 14);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 15);
//                break;
//            case TAOMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 13);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 14);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 15);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 11);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 10);
//                break;
//            case RAPIDMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 12);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 10);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 13);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 15);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 14);
//                break;
//            case DINOBEEMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 10);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 15);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 11);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 13);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 12);
//                break;
//            case SILPHYMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 14);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 13);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 10);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 11);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 15);
//                break;
//            case SHAKKOUMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 15);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 11);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 14);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 12);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 10);
//                break;
//            case WARGROWLMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 13);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 15);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 10);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 14);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 11);
//                break;
//            case CHERUBIMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 12);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 13);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 14);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 15);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 10);
//                break;
//            case CAPTAINHOOKMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 10);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 14);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 15);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 11);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 13);
//                break;
//            case GRIZZLYMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 14);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 11);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 12);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 15);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 10);
//                break;
//            case DINOREXMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 15);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 10);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 14);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 11);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 13);
//                break;
//            case CYBERDRAMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 13);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 15);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 11);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 10);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 14);
//                break;
//            case PIEDMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 12);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 14);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 13);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 15);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 10);
//                break;
//            case METALSEADRAMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 15);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 11);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 10);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 14);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 13);
//                break;
//            case BLACKWARGREYMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 14);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 13);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 11);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 10);
//                atributosModificadores.setModificadorSorte(atributosModificadores.getModificadorSorte() + 15);
//                break;
//            case SHINEGREYMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 10);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 15);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 14);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 12);
//                break;
//            case TITAMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 12);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 13);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 10);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 15);
//                break;
//            case RHINOKABUTERIMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 13);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 14);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 10);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 12);
//                break;
        }
        return atributosModificadores;
    }

}
