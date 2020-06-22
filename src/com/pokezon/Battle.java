package com.pokezon;

public abstract class Battle {
    // BATTLE INSTANCE VARIABLES
    private Trainer player;
    private int xpForWin;

    // BATTLE BUSINESS METHODS
    public static Battle randomBattle(){
        // Need to downcast in client to access subclass-only properties
        return (Math.random() < .5) ? new TrainerBattle() : new WildBattle();
    }
    public abstract void giveXpForWin(int xp);

    // BATTLE GETTERS AND SETTERS
    public Trainer getPlayer() {
        return player;
    }

    public void setPlayer(Trainer player) {
        this.player = player;
    }

    public int getXpForWin() {
        return xpForWin;
    }

    public void setXpForWin(int xpForWin) {
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
