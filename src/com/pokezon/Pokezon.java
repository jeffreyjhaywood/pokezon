/**
 * Pokezon will be used to battle against each other. A Trainer will
 * choose Moves that their Pokezon knows to attack the enemy Pokezon.
 *
 * If Pokezon's currentHealth reaches 0, they will faint and not be usable in battle.
 *
 *
 * @author Jeffrey J. Haywood, Justin Lamb, Marcelo Nazario
 * @version 0.9
 * @since 2020-06-19
 */
package com.pokezon;

import com.pokezon.util.Dialogue;
import com.pokezon.util.SlowTyper;

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

    /**
     * Constructor for Pokezon
     *
     * @param name The name of the Pokezon
     * @param type The type of the Pokezon
     */
    public Pokezon(String name, PokeType type) {
        setName(name);
        setType(type);
    }

    // Business Methods

    /**
     * Adds xp to the Pokezon who won the battle.
     *
     * When the amount of xp received exceeds xpToNextLevel
     * the Pokezon will level up.
     *
     * @param battleXP The amount of xp being added
     */
    public void addXP(double battleXP) {
        setXP(getXP() + battleXP);

        while (getXP() >= getXPToNextLevel()) {
            levelUp();
        }
    }

    /**
     * Adds one level to the Pokezon's level.
     *
     * Increases maxHealth by 3.
     *
     * Increases amount of xp to get to the next level.
     */
    private void levelUp() {
        level++;
        setMaxHealth();
        setXPToNextLevel();
    }

    /**
     * Trainer will choose a move for the Pokezon to use against enemy Pokezon,
     * choice will be the element of the knownMoves array.
     *
     * @param choice Trainer move choice.
     * @param enemy The enemy Pokezon the Move is being used against.
     */
    public void useMove(int choice, Pokezon enemy) {
        choice -= 1;
        Dialogue.attackUsedDialog(getName(), knownMoves[choice].getName());
        double damage = knownMoves[choice].damageCalculator(enemy.getWeakness());
        enemy.takeDamage(damage);
    }

    /**
     * Pokezon will take damage.
     * The currentHealth - incomingDamage will be the Pokezon's new currentHealth.
     *
     * @param incomingDamage Amount of damage Pokezon will receive.
     */
    public void takeDamage(double incomingDamage) {
        double newHealthValue = getCurrentHealth() - incomingDamage;
        Dialogue.tookDamageDialog(getName(), incomingDamage);
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

    /**
     * Sets the type of the Pokezon,
     * Will call setWeakness() to automatically set the Pokezon's weakness.
     *
     * @param type The Pokezon's type
     */
    public void setType(PokeType type) {
        this.type = type;
        setWeakness();
    }

    public PokeType getWeakness() {
        return this.weakness;
    }

    /**
     * Uses the Pokezon's type to set the weakness.
     */
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

    /**
     * Will increase the amount of xp it takes to level up everytime Pokezon levels up.
     */
    private void setXPToNextLevel() {
        int level = getLevel();
        this.xpToNextLevel = (level + (level * XP_PCT)) * 10;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * Adds 3 to Pokezon maxHealth
     */
    private void setMaxHealth() {
        this.maxHealth += 3;
    }

    public double getCurrentHealth() {
        return this.currentHealth;
    }

    /**
     * Assigns the currentHealth to be the amount passed in.
     *
     * If the new health value is less than 0, the Pokezon is considered to be fainted and unusable.
     *
     * @param newCurrentHealth The value currentHealth will be set to.
     */
    public void setCurrentHealth(double newCurrentHealth) {
        if (newCurrentHealth <= 0) {
            this.currentHealth = 0;
            isFainted = true;
        }
        else {
            this.currentHealth = newCurrentHealth;
        }
    }

    /**
     * Will add a move to the knownMoves array.
     *
     * Will add the move to the first empty element in the array.
     *
     * @param move New move to be added to the array.
     */
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
