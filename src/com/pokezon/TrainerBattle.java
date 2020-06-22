package com.pokezon;

public class TrainerBattle extends Battle {
    // Instance Variables
    private Trainer enemyTrainer;
    private Pokezon[] enemyTrainerPokezons;

    // Constructor
    public TrainerBattle(){
        setEnemyTrainer();
        // setEnemyTrainerPokezons();
    }


    // Business methods
    @Override
    public void giveXpForWin(int xp) {
        setXpForWin(xp * 2); // Offer more xp
    }

    // TrainerBattle Subclass Getters And Setters

    public Trainer getEnemyTrainer() {
        return enemyTrainer;
    }

    public void setEnemyTrainer() {
        // Generate Random enemyTrainer - this is just an example
        this.enemyTrainer = (Math.random() < .5) ? new Trainer("Bill") : new Trainer("Smith");
    }

    public Pokezon[] getEnemyTrainerPokezons() {
        return enemyTrainerPokezons;
    }

    // Maybe create a random Pokezons generator method in Pokezons.java
    public void setEnemyTrainerPokezons(Pokezon[] enemyTrainerPokezons) {
        this.enemyTrainerPokezons = enemyTrainerPokezons;
    }

}
