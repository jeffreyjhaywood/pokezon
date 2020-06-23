package com.pokezon;

import java.util.*;

public class Trainer {

    // INSTANCE VARIABLES
    private String name;
    private Pokezon[] pokezonTeam;
    private Pokezon currentPokezon;
    private int numWins;

    // CONSTRUCTORS

    // Create Trainer after user input name
    public Trainer(String name) {
        setName(name);
    }

    // BUSINESS METHODS
    public void choosePokezon(int choice) {
        this.currentPokezon = pokezonTeam[choice];
    }

    //
    public void chooseMove(int choice) {
        //currentPokezon.useMove(choice);
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

    @Override
    public String toString() {
        return "Trainer{" +
                "name='" + name + '\'' +
                ", pokezonTeam=" + Arrays.toString(pokezonTeam) +
                ", numWins=" + numWins +
                ", currentPokezon=" + currentPokezon +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return numWins == trainer.numWins && name.equals(trainer.name) &&
                Arrays.equals(pokezonTeam, trainer.pokezonTeam);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, numWins);
        result = 31 * result + Arrays.hashCode(pokezonTeam);
        return result;
    }
}