package com.pokezon;

public class TrainerBattle extends Battle {
    // Instance Variables
    private Trainer enemyTrainer;

    // Constructor
    public TrainerBattle() {
        setEnemyTrainer();
    }

    public TrainerBattle(Trainer enemyTrainer){
        setEnemyTrainer(enemyTrainer);
    }


    // Business methods
    @Override
    public void giveXpForWin() {
        setXpForWin(BASE_XP * 2.0);
    }

    // TrainerBattle Subclass Getters And Setters

    public Trainer getEnemyTrainer() {
        return enemyTrainer;
    }

    public void setEnemyTrainer() {
        // Generate Random enemyTrainer - this is just an example
        this.enemyTrainer = (Math.random() < .5) ? new Trainer("Bill") : new Trainer("Smith");
    }

    public void setEnemyTrainer(Trainer enemyTrainer){
        this.enemyTrainer = enemyTrainer;
    }

}
