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

    // Constructors
    public Pokezon() {
    }

    public Pokezon(String name, PokeType type) {
        setName(name);
        setType(type);
    }

    // Business Methods
    public void addXP(double battleXP) {
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
        double damage = knownMoves[choice].damageCalculator(enemy.getWeakness());
        System.out.println(getName() + " used " + knownMoves[choice].getName() + "!");
        enemy.takeDamage(damage);
    }

    public void takeDamage(double incomingDamage) {
        double newHealthValue = getCurrentHealth() - incomingDamage;
        System.out.println(getName() + " took " + incomingDamage + " damage!");
        setCurrentHealth(newHealthValue);
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
        switch(this.type) {
            case GRASS:
                this.weakness = PokeType.FIRE;
                break;

            case WATER:
                this.weakness = PokeType.GRASS;
                break;

            case FIRE:
                this.weakness = PokeType.WATER;
                break;
        }
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public void setCurrentHealth(double currentHealth) {
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

    public void setFainted(boolean fainted) {
        this.isFainted = fainted;
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
