package com.pokezon;

public abstract class Battle {
    // BATTLE INSTANCE VARIABLES
    private Trainer player;
    private double xpForWin;
    private boolean battleHappening;
    private boolean isBattleOver = false;
    private int numRounds;
    // BATTLE CONSTANTS
    public final double BASE_XP = 10.0;

    // BATTLE BUSINESS METHODS
    public static Battle randomBattle() {
        // Need to downcast in client to access subclass-only properties
        return (Math.random() < .5) ? new TrainerBattle() : new WildBattle();
    }

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

    public int getNumRounds() {
        return numRounds;
    }

    public void setNumRounds(int numRounds) {
        this.numRounds = numRounds;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "player=" + player +
                ", xpForWin=" + xpForWin +
                '}';
    }
}
