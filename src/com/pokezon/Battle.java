package com.pokezon;

public abstract class Battle {
    // BATTLE INSTANCE VARIABLES
    private Trainer player;
    private double xpForWin;
    // BATTLE CONSTANTS
    public final double BASE_XP = 10.0;

    // BATTLE BUSINESS METHODS
    public static Battle randomBattle(){
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

    @Override
    public String toString() {
        return "Battle{" +
                "player=" + player +
                ", xpForWin=" + xpForWin +
                '}';
    }
}
