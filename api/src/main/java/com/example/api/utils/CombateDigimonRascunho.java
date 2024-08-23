package com.example.api.utils;

public class CombateDigimonRascunho {

    private String name;
    private int strength;
    private int intelligence;
    private int agility;
    private int health;

    public CombateDigimonRascunho(String name, int strength, int intelligence, int agility, int health) {
        this.name = name;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.health = health;
    }

    public int getTotalPhysicalAttack() {
        return this.strength * 2 + this.agility;
    }

    public int getTotalPhysicalDefense() {
        return (int) (this.strength * 1.5 + this.agility);
    }

    public int getTotalElementalAttack() {
        return this.intelligence * 2 + this.agility;
    }

    public int getTotalElementalDefense() {
        return (int) (this.intelligence * 1.5 + this.agility);
    }

    public int getAgility() {
        return this.agility;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return this.name;
    }
}
