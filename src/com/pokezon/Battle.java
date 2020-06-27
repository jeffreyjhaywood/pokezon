package com.pokezon;

/**
 * Battle is the "playing field" for every battle that happens.
 * There will always be 2 Pokezon battling each other (playerPokezon and enemyPokezon)
 *
 * @author Jeffrey J. Haywood, Justin Lamb, Marcelo Nazario
 * @version 0.9
 * @since 2020-06-19
 */
public abstract class Battle {
    // BATTLE INSTANCE VARIABLES
    private Trainer player;
    private double xpForWin;
    private boolean isBattleOver = false;
    private boolean didPlayerWin = false;
    private int numRounds;
    private Pokezon playerPokezon;
    private Pokezon enemyPokezon;

    // BATTLE CONSTANTS
    public final double BASE_XP = 10.0;

    // Constructor
    public Battle(Trainer player) {
        setPlayer(player);
        setPlayerPokezon(player.choosePokezon(1)); // Always send out first Pokezon at beginning of battle
    }
    // BATTLE BUSINESS METHODS

    /**
     * Randomly creates either a TrainerBattle or a WildBattle
     *
     * @return Randomly generated TrainerBattle or WildBattle
     */
    public static Battle randomBattle(Trainer player) {
        // Need to downcast in client to access subclass-only properties
        return (Math.random() < .5) ? new TrainerBattle(player) : new WildBattle(player);
    }

    /**
     * Calculates amount of xp for winning a battle.
     * Base amount if WildBattle, double base amount if Trainer battle.
     */
    public abstract void giveXpForWin();

    // BATTLE GETTERS AND SETTERS
    public Trainer getPlayer() {
        return player;
    }

    public void setPlayer(Trainer player) {
        this.player = player;
    }

    public double getXpForWin() {
        return xpForWin;
    }

    public void setXpForWin(double xpForWin) {
        this.xpForWin = xpForWin;
    }

    public boolean isBattleOver() {
        return isBattleOver;
    }

    public void setBattleOver(boolean isBattleOver) {
        this.isBattleOver = isBattleOver;
    }

    public boolean didPlayerWin() {
        return this.didPlayerWin;
    }

    public void setPlayerWin(boolean result) {
        didPlayerWin = result;
    }

    public int getNumRounds() {
        return numRounds;
    }

    public void setNumRounds(int numRounds) {
        this.numRounds = numRounds;
    }

    public Pokezon getPlayerPokezon() {
        return playerPokezon;
    }

    public void setPlayerPokezon(Pokezon playerPokezon) {
        this.playerPokezon = playerPokezon;
    }

    public Pokezon getEnemyPokezon() {
        return enemyPokezon;
    }

    public void setEnemyPokezon(Pokezon enemyPokezon) {
        this.enemyPokezon = enemyPokezon;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "player=" + player +
                ", xpForWin=" + xpForWin +
                ", isBattleOver=" + isBattleOver +
                ", didPlayerWin=" + didPlayerWin +
                ", numRounds=" + numRounds +
                ", playerPokezon=" + playerPokezon +
                ", enemyPokezon=" + enemyPokezon +
                '}';
    }
}
