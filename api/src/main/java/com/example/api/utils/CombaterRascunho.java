package com.example.api.utils;

public class CombaterRascunho {

    public static CombateDigimonRascunho determineFirstCombatant(CombateDigimonRascunho digimon1, CombateDigimonRascunho digimon2) {
        return digimon1.getAgility() > digimon2.getAgility() ? digimon1 : digimon2;
    }


    public static void combat(CombateDigimonRascunho digimon1, CombateDigimonRascunho digimon2) {
        CombateDigimonRascunho firstCombatant = determineFirstCombatant(digimon1, digimon2);
        CombateDigimonRascunho secondCombatant = firstCombatant == digimon1 ? digimon2 : digimon1;

        System.out.println("-----------------------------------");
        System.out.println("Combate iniciado entre " + digimon1.getName() + " e " + digimon2.getName() + "!");
        System.out.println(firstCombatant.getName() + " ataca primeiro!");

        System.out.println(digimon1.getName() + " começa com " + digimon1.getHealth() + " de saúde.");
        System.out.println(digimon2.getName() + " começa com " + digimon2.getHealth() + " de saúde.");
        System.out.println("-----------------------------------");

        int turno = 1;
        while (digimon1.getHealth() > 0 && digimon2.getHealth() > 0) {
            // First combatant attacks second combatant
            int physicalDamage = firstCombatant.getTotalPhysicalAttack() - secondCombatant.getTotalPhysicalDefense();
            int elementalDamage = firstCombatant.getTotalElementalAttack() - secondCombatant.getTotalElementalDefense();
            int totalDamage = Math.max(physicalDamage, 0) + Math.max(elementalDamage, 0);
            secondCombatant.setHealth(secondCombatant.getHealth() - totalDamage);

            System.out.println(" ");
            System.out.println("Turno " + turno + ":");
            System.out.println(firstCombatant.getName() + " ataca " + secondCombatant.getName() + " causando " + totalDamage + " de dano!");
            System.out.println(secondCombatant.getName() + " tem agora " + secondCombatant.getHealth() + " de saúde.");

            if (secondCombatant.getHealth() <= 0) {
                System.out.println(secondCombatant.getName() + " foi derrotado! " + firstCombatant.getName() + " é o vencedor!");
                return;
            }
            turno++;

            // Second combatant attacks first combatant
            physicalDamage = secondCombatant.getTotalPhysicalAttack() - firstCombatant.getTotalPhysicalDefense();
            elementalDamage = secondCombatant.getTotalElementalAttack() - firstCombatant.getTotalElementalDefense();
            totalDamage = Math.max(physicalDamage, 0) + Math.max(elementalDamage, 0);
            firstCombatant.setHealth(firstCombatant.getHealth() - totalDamage);

            System.out.println(secondCombatant.getName() + " ataca " + firstCombatant.getName() + " causando " + totalDamage + " de dano!");
            System.out.println(firstCombatant.getName() + " tem agora " + firstCombatant.getHealth() + " de saúde.");

            if (firstCombatant.getHealth() <= 0) {
                System.out.println(firstCombatant.getName() + " foi derrotado! " + secondCombatant.getName() + " é o vencedor!");
                return;
            }
        }
    }


    public static void main(String[] args) {
        CombateDigimonRascunho agumon = new CombateDigimonRascunho("Agumon", 10, 1, 1, 50);
        CombateDigimonRascunho gabumon = new CombateDigimonRascunho("Gabumon", 7, 1, 1, 50);

        combat(agumon, gabumon);
    }
}
