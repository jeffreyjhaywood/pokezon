package com.pokezon;

public class WildBattle extends Battle{

    // WildBattle Instance Variables
    private Pokezon enemyPokezon;

    // Constructor
    public WildBattle(){
        // WildName argument should be replace with random Wild Pokezon method generator declare in Pokezon.java
        // setEnemyPokezon(new Pokezon("WildName"));
    }

    // WildBattle Business methods
    @Override
    public void giveXpForWin(int xp) {
        setXpForWin(xp); // Offer less xp
    }

    // WildBattle Subclass Getters And Setters
    public Pokezon getEnemyPokezon() {
        return enemyPokezon;
    }

    public void setEnemyPokezon(Pokezon enemyPokezon) {
        this.enemyPokezon = enemyPokezon;
    }

}
