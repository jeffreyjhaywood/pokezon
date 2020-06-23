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
        Pokezon tempSquirtle = new Pokezon("Squirtle", PokeType.WATER, PokeType.GRASS);
        Pokezon tempBulbasaur = new Pokezon("Bulbasaur", PokeType.GRASS, PokeType.FIRE);
        Move move = new Move("Tackle", PokeType.NORMAL, 20);
        tempSquirtle.setMove(move);
        tempBulbasaur.setMove(move);
        this.enemyTrainer = (Math.random() < .5) ? new Trainer("Bill", tempSquirtle) : new Trainer("Smith", tempBulbasaur);
    }

    public void setEnemyTrainer(Trainer enemyTrainer){
        this.enemyTrainer = enemyTrainer;
    }

}
