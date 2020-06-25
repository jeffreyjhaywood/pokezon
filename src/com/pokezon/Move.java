package com.pokezon;

import com.pokezon.util.Dialogue;

public class Move {
    // Constants
    private final int BASE_DAMAGE = 10;

    // Instance Variables
    private String name;
    private PokeType type;
    private int mana = 20;
    private int currentMana;

    // Constructors
    public Move(String name, PokeType type, int mana) {
        setName(name);
        setType(type);
        setMana(mana);
        setCurrentMana(mana);
    }

    // Business Methods
    public double damageCalculator(PokeType enemyWeakness){
        double damage = BASE_DAMAGE;
        if (enemyWeakness == type){
            damage *= 2;
            Dialogue.superEffectiveDialogue();
        }
        return damage;
    }

    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokeType getType() {
        return this.type;
    }

    public void setType(PokeType type) {
        this.type = type;
    }

    public int getMana() {
        return this.mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getCurrentMana() {
        return this.currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    @Override
    public String toString() {
        return "Move{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", mana=" + mana +
                ", currentMana=" + currentMana +
                '}';
    }
}






     

            






