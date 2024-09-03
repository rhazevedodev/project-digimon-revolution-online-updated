package com.example.api.utils;

import java.util.Random;

public class Randomizer {

    public static int gerarNumeroMinMax(int min, int max){
        Random random = new Random();
        return random.nextInt(min, max +1);
    }

}
