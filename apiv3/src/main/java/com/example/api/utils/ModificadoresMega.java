package com.example.api.utils;

import com.example.api.entity.AtributosModificadores;
import com.example.api.enumerator.EnumDigimonMega;

public class ModificadoresMega {

    public static AtributosModificadores definirModificadoresMega(AtributosModificadores atributosModificadores, EnumDigimonMega mega) {
        switch (mega) {
            case WARGREYMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 18);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 19);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 15);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 20);
                break;
            case METALGARURUMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 17);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 18);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 20);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 15);
                break;
            case VIKEMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 19);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 16);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 17);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 15);
                break;
            case ROSEMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 20);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 15);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 19);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 18);
                break;
            case SERAPHIMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 15);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 20);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 16);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 19);
                break;
            case PHOENIXMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 18);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 20);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 16);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 15);
                break;
            case HERCULESKABUTERIMON:
                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 19);
                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 16);
                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 20);
                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 17);
                break;
//            case OPHANIMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 16);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 19);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 17);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 20);
//                break;
//            case IMPERIALDRAMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 20);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 15);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 19);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 16);
//                break;
//            case VENOMMYOTISMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 15);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 17);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 16);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 19);
//                break;
//            case SAKUYAMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 18);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 19);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 20);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 16);
//                break;
//            case MEGAGARGOMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 17);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 15);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 18);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 20);
//                break;
//            case GRANDISKOAGAMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 15);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 20);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 16);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 18);
//                break;
//            case VALKYRIMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 19);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 18);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 15);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 16);
//                break;
//            case GALLANTMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 18);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 20);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 15);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 19);
//                break;
//            case CHERUBIMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 17);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 18);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 19);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 20);
//                break;
//            case CANNONBEEMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 15);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 19);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 20);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 16);
//                break;
//            case PANDAMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 19);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 16);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 17);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 20);
//                break;
//            case TYRANNOMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 20);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 15);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 19);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 16);
//                break;
//            case JUSTIMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 18);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 20);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 16);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 15);
//                break;
//            case PIEDMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 17);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 19);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 18);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 20);
//                break;
//            case METALSEADRAMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 20);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 16);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 15);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 19);
//                break;
//            case BLACKWARGREYMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 19);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 18);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 16);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 15);
//                break;
//            case OMNIMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 15);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 20);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 19);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 17);
//                break;
//            case PLUTOMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 17);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 18);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 15);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 20);
//                break;
//            case TIGERVESPAMON:
//                atributosModificadores.setModificadorForca(atributosModificadores.getModificadorForca() + 18);
//                atributosModificadores.setModificadorConhecimento(atributosModificadores.getModificadorConhecimento() + 19);
//                atributosModificadores.setModificadorInteligencia(atributosModificadores.getModificadorInteligencia() + 15);
//                atributosModificadores.setModificadorAgilidade(atributosModificadores.getModificadorAgilidade() + 17);
//                break;
        }
        return atributosModificadores;
    }

}
