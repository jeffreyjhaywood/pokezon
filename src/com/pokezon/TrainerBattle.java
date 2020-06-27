package com.pokezon;

/**
 * TrainerBattle are a sublcass of Battle where the player is battling
 * against another Trainer.
 *
 * @author Jeffrey J. Haywood, Justin Lamb, Marcelo Nazario
 * @version 0.9
 * @since 2020-06-19
 */
public class TrainerBattle extends Battle {
    // Instance Variables
    private Trainer enemyTrainer;

    // Constructors
    public TrainerBattle(Trainer player){
        super(player);
        setEnemyTrainer();
        setEnemyPokezon(enemyTrainer.choosePokezon(1));
    }

    public TrainerBattle(Trainer player, Trainer enemyTrainer) {
        super(player);
        setEnemyTrainer(enemyTrainer);
        setEnemyPokezon(enemyTrainer.choosePokezon(1));
    }

    // Business methods
    /**
     * Calculates the amount of xp player Pokezon will receive for winning.
     */
    @Override
    public void giveXpForWin() {
        setXpForWin(BASE_XP * 2.0);
    }

    // TrainerBattle Subclass Getters And Setters
    public Trainer getEnemyTrainer() {
        return enemyTrainer;
    }

    /**
     * Creates a random enemy trainer that player will battle.
     *
     */
    public void setEnemyTrainer() {
        // Generate Random enemyTrainer - this is just an example
        Pokezon tempSquirtle = new Pokezon("Squirtle", PokeType.WATER, new Move("Tackle", PokeType.NORMAL, 20));
        Pokezon tempBulbasaur = new Pokezon("Bulbasaur", PokeType.GRASS, new Move("Tackle", PokeType.NORMAL, 20));

        this.enemyTrainer = (Math.random() < .5) ? new Trainer("Bill", tempSquirtle) : new Trainer("Smith", tempBulbasaur);
    }

    public void setEnemyTrainer(Trainer enemyTrainer){
        this.enemyTrainer = enemyTrainer;
    }

    @Override
    public String toString() {
        return "TrainerBattle{" +
                "enemyTrainer=" + enemyTrainer +
                "} " + super.toString();
    }
}
