package com.example.api.utils;

import com.example.api.enumerator.EnumMissoes;

public class Recompensas {

    public static int gerarBitsCacada() {
        return Randomizer.gerarNumeroMinMax(100, 500);
    }

    public static int gerarExpCacada() {
        return Randomizer.gerarNumeroMinMax(1, 20);
    }

//    public static String gerarFragmento() {
//        String[] fragmentos = {"Fragmento de Fogo", "Fragmento de Água", "Fragmento de Terra", "Fragmento de Vento"};
//        return fragmentos[Randomizer.gerarNumeroMinMax(0, 3)];
//    }

    public static int gerarQuantidadeFragmentos1A5() {
        return Randomizer.gerarNumeroMinMax(1, 5);
    }

    public static int gerarQuantidadeFragmentos5A10() {
        return Randomizer.gerarNumeroMinMax(5, 10);
    }

//    public static int gerarRecompensaExpMissao(int idMissao, int horas) {
//
//        EnumMissoes missao = EnumMissoes.valueOf("MISSAO" + idMissao);
//
//        if (missao != null) {
//            int expHora = Integer.parseInt(missao.getExpHora());
//            int recompensaExp = expHora * horas;
//            System.out.println("Recompensa de Exp: " + recompensaExp);
//            return recompensaExp;
//        } else {
//            throw new IllegalArgumentException("Missão não encontrada para o ID: " + idMissao);
//        }
//    }

    public static int gerarRecompensaBitsMissao(int idMissao, int horas) {

        EnumMissoes missao = EnumMissoes.valueOf("MISSAO" + idMissao);

        if (missao != null) {
            int bitsHora = Integer.parseInt(missao.getBitsHora());
            int recompensaBits = bitsHora * horas;
            System.out.println("Recompensa de Bits: " + recompensaBits);
            return recompensaBits;
        } else {
            throw new IllegalArgumentException("Missão não encontrada para o ID: " + idMissao);
        }
    }

}
