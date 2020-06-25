/**
 * Trainer is what the user will be playing as.
 * A trainer has a team of Pokezon, and will choose what Moves for their Pokezon to use in battle.
 *
 * Can only have a maximum of 3 Pokezon.
 *
 * @author Jeffrey J. Haywood, Justin Lamb, Marcelo Nazario
 * @version 0.9
 * @since 2020-06-19
 */
package com.pokezon;

import java.util.*;

public class Trainer {
    // CONSTANTS
    private final int TEAM_SIZE = 3;

    // INSTANCE VARIABLES
    private String name;
    private Pokezon[] pokezonTeam = new Pokezon[TEAM_SIZE];
    private Pokezon currentPokezon;
    private int numWins;
    private int numFaintedPokezon = 0;

    // CONSTRUCTORS
    // Create Trainer after user input name
    public Trainer(String name) {
        setName(name);
    }

    /**
     * Constructor for Trainer.
     *
     * @param name The Trainer's name.
     * @param currentPokezon Pokezon to be added to the Trainer's team.
     */
    public Trainer(String name, Pokezon currentPokezon) {
        this(name);
        addPokezonToTeam(currentPokezon);
    }

    // BUSINESS METHODS

    /**
     * Send one of the Pokezon on the Trainer's team out to battle.
     *
     * @param choice Integer representation of the selected Pokezon to switch to.
     * @return The Pokezon at the chosen element of the array.
     */
    public Pokezon choosePokezon(int choice) {
        choice -= 1;
        return pokezonTeam[choice];
    }

    /**
     * Chooses a Move of the current battling Pokezon.
     *
     * @param choice Integer representation of selected Move
     * @param playerPokezon The Pokezon currently battling.
     * @param enemy The enemy Pokezon the move will be used against.
     */
    public void chooseMove(int choice, Pokezon playerPokezon, Pokezon enemy) {
        playerPokezon.useMove(choice, enemy);
    }

    /**
     * Adds given Pokezon to the Trainer's team.
     *
     * @param pokezon Pokezon to be added to the trainer's team.
     */
    public void addPokezonToTeam(Pokezon pokezon) {
        int i = 0;
        for (Pokezon availablePokezon : pokezonTeam) {
            if (availablePokezon == null) {
                pokezonTeam[i] = pokezon;
                return;
            }
            i++;
        }
        System.out.println("Pokezon team already full.");
    }

    // GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pokezon[] getPokezonTeam() {
        return pokezonTeam;
    }

    public void setPokezonTeam(Pokezon[] pokezonTeam) {
        this.pokezonTeam = pokezonTeam;
    }

    public Pokezon getCurrentPokezon() {
        return currentPokezon;
    }

    public void setCurrentPokezon(Pokezon currentPokezon) {
        this.currentPokezon = currentPokezon;
    }

    public void setCurrentPokezon(int choice) {
        this.currentPokezon = pokezonTeam[choice];
    }

    public int getNumWins() {
        return numWins;
    }

    public void setNumWins(int numWins) {
        this.numWins = numWins;
    }

    public int getNumFaintedPokezon() {
        return numFaintedPokezon;
    }

    public void setNumFaintedPokezon(int numFaintedPokezon) {
        this.numFaintedPokezon = numFaintedPokezon;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "name='" + name + '\'' +
                ", pokezonTeam=" + Arrays.toString(pokezonTeam) +
                ", currentPokezon=" + currentPokezon +
                ", numWins=" + numWins +
                ", numFaintedPokezon=" + numFaintedPokezon +
                '}';
    }
}