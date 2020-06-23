package com.pokezon;

public class Move {
    private final int BASE_DAMAGE = 10;
    private String name;
    private PokeType type;
    private int mana;
    private int currentMana;

    public double damageCalculator(PokeType enemyWeakness){
        double damage = BASE_DAMAGE;
        if (enemyWeakness == type){
            damage *= 2;
        }
        return damage;
    }


    //move getters and setter
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

    public void setMana(int Mana) {
        this.mana = Mana;
    }

    public int getCurrentMana() {
        return this.currentMana;
    }

    public void setCurrentMana(int CurrentMana) {
        this.currentMana = CurrentMana;
    }











}






     

            






