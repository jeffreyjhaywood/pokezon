package com.pokezon;

public class Move {
    private final int BASE_DAMAGE = 10;
    private String name;
    private PokeType type;
    private int mana = 20;
    private int currentMana;

    public double damageCalculator(PokeType enemyWeakness){
        double damage = BASE_DAMAGE;
        if (enemyWeakness == type){
            damage *= 2;

        }
        return damage;
    }

    public Move(String name, PokeType type, int mana) {
        setName(name);
        setType(type);
        setMana(mana);
        setCurrentMana(mana);
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






     

            






