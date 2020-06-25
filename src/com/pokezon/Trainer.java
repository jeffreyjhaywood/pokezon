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

    public Trainer(String name, Pokezon currentPokezon) {
        this(name);
        addPokezonToTeam(currentPokezon);
    }

    // BUSINESS METHODS
    public Pokezon choosePokezon(int choice) {
        choice -= 1;
        return pokezonTeam[choice];
    }

    public void chooseMove(int choice, Pokezon playerPokezon, Pokezon enemy) {
        playerPokezon.useMove(choice, enemy);
    }

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