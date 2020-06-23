package com.pokezon;

import java.util.Arrays;

public class Pokezon {
    // Constants
    private final double BASE_XP = 10;
    private final double XP_PCT = .25;

    // Class Variables
    private String name;
    private PokeType type;
    private PokeType weakness;
    private int level = 1;
    private double xp = 0;
    private double xpToNextLevel = 10;
    private double maxHealth = 20;
    private double currentHealth = maxHealth;
    private Move[] knownMoves = new Move[4];
    private Move[] learnableMoves;
    private boolean isCapturable = false;
    private boolean isFainted = false;


    // Business Methods
    public void addXP(int battleXP) {
        setXP(getXP() + battleXP);

        while (getXP() >= getXPToNextLevel()) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        setMaxHealth();
        setXPToNextLevel();
    }

    public void useMove(int choice, Pokezon enemy) {
        choice -= 1;
        double damage = knownMoves[choice].damageCalculator(enemy.getType());
        System.out.println(getName() + " used " + knownMoves[choice].getName() + "!");
        enemy.takeDamage(damage);
    }

    public void takeDamage(double incomingDamage) {
        double newHealthValue = getCurrentHealth() - incomingDamage;
        System.out.println(getName() + " took " + incomingDamage + " damage!");
        setCurrentHealth(newHealthValue);
    }

    // Constructors ( Coming Soon )
    public Pokezon() {

    }

    public Pokezon(String name, PokeType type, PokeType weakness) {
        setName(name);
        setType(type);
    }

    // Getters/Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokeType getType() {
        return this.type;
    }

    public void setType(PokeType type) {
        this.type = type;
        setWeakness();
    }

    public PokeType getWeakness() {
        return this.weakness;
    }

    private void setWeakness() {
        switch(getType()) {
            case GRASS:
                this.weakness = PokeType.FIRE;

            case WATER:
                this.weakness = PokeType.GRASS;

            case FIRE:
                this.weakness = PokeType.WATER;
        }
    }

    public int getLevel() {
        return this.level;
    }

    public double getXP() {
        return this.xp;
    }

    private void setXP(double xp) {
        this.xp = xp;
    }

    public double getXPToNextLevel() {
        return this.xpToNextLevel;
    }

    private void setXPToNextLevel() {
        int level = getLevel();
        this.xpToNextLevel = (level + (level * XP_PCT)) * 10;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }

    private void setMaxHealth() {
        this.maxHealth += 3;
    }

    public double getCurrentHealth() {
        return this.currentHealth;
    }

    private void setCurrentHealth(double currentHealth) {
        if (currentHealth <= 0) {
            this.currentHealth = 0;
            isFainted = true;
        }
        else {
            this.currentHealth = currentHealth;
        }
    }

    public void setMove(Move move) { // Add move to array to first empty element
        int i = 0;
        for (Move availableMove : knownMoves) {
            if (availableMove == null) {
                knownMoves[i] = move;
                return;
            }
            i++;
        }
    }

    public Move[] getKnownMoves() {
        return this.knownMoves;
    }

    public void setKnownMoves(Move[] knownMoves) {
        this.knownMoves = knownMoves;
    }

    public boolean isFainted() {
        return this.isFainted;
    }

    @Override
    public String toString() {
        return "Pokezon{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", weakness=" + weakness +
                ", level=" + level +
                ", xp=" + xp +
                ", xpToNextLevel=" + xpToNextLevel +
                ", maxHealth=" + maxHealth +
                ", currentHealth=" + currentHealth +
                ", knownMoves=" + Arrays.toString(knownMoves) +
                ", learnableMoves=" + Arrays.toString(learnableMoves) +
                ", isCapturable=" + isCapturable +
                ", isFainted=" + isFainted +
                '}';
    }
}
