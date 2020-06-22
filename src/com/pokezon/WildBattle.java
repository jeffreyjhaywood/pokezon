package com.pokezon;

public class WildBattle extends Battle{

    // WildBattle Instance Variables
    private Pokezon enemyPokezon;

    /* No Constructor to maintain loose coupling?
     * will assign properties using setters
     * If WildBattle instantiated
     */

    // WildBattle Business methods
    @Override
    public void giveXpForWin() {
        setXpForWin(BASE_XP); // Offer less xp
    }

    // WildBattle Subclass Getters And Setters
    public Pokezon getEnemyPokezon() {
        return enemyPokezon;
    }

    public void setEnemyPokezon(Pokezon randomPokezon) {
        this.enemyPokezon = randomPokezon;
    }

}
