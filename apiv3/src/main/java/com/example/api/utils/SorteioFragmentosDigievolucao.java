package com.example.api.utils;

import com.example.api.enumerator.EnumFragmentosDigievolucao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SorteioFragmentosDigievolucao {

    public static EnumFragmentosDigievolucao sortearFragmento(String tier) {
        List<EnumFragmentosDigievolucao> fragmentosDisponiveis = new ArrayList<>();
        // Adicionar os fragmentos disponíveis baseado no tier especificado
        for (EnumFragmentosDigievolucao fragmento : EnumFragmentosDigievolucao.values()) {
            if (fragmento.getTier().equalsIgnoreCase(tier)) {
                fragmentosDisponiveis.add(fragmento);
            }
        }
        // Verificar se há fragmentos disponíveis para o tier especificado
        if (!fragmentosDisponiveis.isEmpty()) {
            Random random = new Random();
            int indexSorteado = random.nextInt(fragmentosDisponiveis.size());
            return fragmentosDisponiveis.get(indexSorteado);
        }
        return null; // Retorna null se não houver fragmentos disponíveis para o tier especificado
    }

    public static EnumFragmentosDigievolucao sortearNovoFragmento(String tier, String idFragmentoInicio) {
        EnumFragmentosDigievolucao novoSorteioFragmento;
        do {
            novoSorteioFragmento = SorteioFragmentosDigievolucao.sortearFragmento(tier);
        } while (novoSorteioFragmento.getId().equals(idFragmentoInicio));
        return novoSorteioFragmento;
    }

    public static EnumFragmentosDigievolucao sortearFragmentoQualquerTier() {
        String[] tiers = {"Champion","Ultimate","Mega"};
        Random random = new Random();
        String tierSorteado = tiers[random.nextInt(tiers.length)];

        List<EnumFragmentosDigievolucao> fragmentosDisponiveis = new ArrayList<>();
        // Adicionar os fragmentos disponíveis baseado no tier especificado
        for (EnumFragmentosDigievolucao fragmento : EnumFragmentosDigievolucao.values()) {
            if (fragmento.getTier().equalsIgnoreCase(tierSorteado)) {
                fragmentosDisponiveis.add(fragmento);
            }
        }
        // Verificar se há fragmentos disponíveis para o tier especificado
        if (!fragmentosDisponiveis.isEmpty()) {
            int indexSorteado = random.nextInt(fragmentosDisponiveis.size());
            return fragmentosDisponiveis.get(indexSorteado);
        }
        return null; // Retorna null se não houver fragmentos disponíveis para o tier especificado
    }

}

