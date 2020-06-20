package com.pokezon;

import java.util.*;

public class Trainer {

    // INSTANCE VARIABLES
    private String name;
    private Pokezon[] pokezonTeam;
    private int numWins;
    private Pokezon currentPokezon;

    // CONSTRUCTORS

    // Create Trainer after user input name
    public Trainer(String name) {
        setName(name);
        //setRandomBattleType();
    }

    // BUSINESS METHODS
    public void choosePokezon(int choice){
        this.currentPokezon = pokezonTeam[choice];
    }

    //
    public void chooseMove(int choice){
        currentPokezon.useMove(choice);
    }

    // GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumWins() {
        return numWins;
    }

    public void setNumWins(int numWins) {
        this.numWins = numWins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return numWins == trainer.numWins &&
                name.equals(trainer.name) &&
                Arrays.equals(pokezonTeam, trainer.pokezonTeam);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, numWins);
        result = 31 * result + Arrays.hashCode(pokezonTeam);
        return result;
    }
}