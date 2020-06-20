package com.pokezon;

import java.util.*;

public class Trainer {

    // INSTANCE VARIABLES
    private String name;
    Pokezon[] pokezonTeam ={new Pokezon(), new Pokezon(), new Pokezon()};
    int numWins;

    // CONSTRUCTORS

    // Create Trainer after user input name
    public Trainer(String name) {
        setName();
        //setRandomBattleType();
        choosePokezon();
    }

    // BUSINESS METHODS - NEEDS LIST OF POKEZONS AND MOVES
    public void choosePokezon(){
        // GET LIST OF POKEZONS
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        List<Pokezon> pokezonList = Arrays.asList();
        // SAVE USER'S SELECTION
        int pokezonSelection = scanner.nextInt();
        // IF VALID POKEZON, ASSIGN TO USER INSTANCE TEAM
        /*scanner.nextInt()*/
        if (pokezonList.contains(pokezonSelection))
            // Need to find out how to add new Pokezon to array
            pokezonTeam = new Pokezon[0];
        else
            // something similar or loop until correct input
            //selectAnotherPokezon();
            return;
    }

    //
    public void chooseMove(Move name){
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        // GET LIST OF MOVES
        List<Move> moveList = Arrays.asList();
        // SAVE USER'S SELECTION
        int moveSelection = scanner.nextInt();
        // IF VALID MOVE, PERFORM MOVE
        if (moveList.contains(moveSelection))
            //executeMove();
            return;
        else
            // something similar or loop until correct input
            //executeAnotherMove();
            return;
    }

    // GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName() {
        System.out.println("What's your name?");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        this.name = scanner.nextLine();
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
