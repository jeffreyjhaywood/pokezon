package com.pokezon.gameclient;

import com.pokezon.Move;

import java.util.Scanner;

public final class Dialogue {
    static Scanner input = new Scanner(System.in);

    public static String introDialogue() {
        String name;

        System.out.println("Hello and welcome to the wonderful world of Pokezon!");
        System.out.println("I'm Prof. Bezos, what's your name?");

        name = input.nextLine();

        return name;
    }

    public static void battleStartDialogue() { // Pass in a Battle as a parameter
        System.out.println("Enemy Name" + " has started a battle!");

        // if statement for special dialogue?

        System.out.println("Enemy Name" + " sends out " + "Enemy Pokezon Name");
        System.out.println("Player Name" + " sends out " + "Player Pokezon Name");
    }

    public static int battleChoiceDialogue() { // Pass in a Battle as a parameter
        System.out.println("What would you like to do?");
        System.out.println("1. Attack");
        System.out.println("2. Switch Pokezon");
        System.out.println("3. Quit Game");

        return Integer.parseInt(input.nextLine());
    }

    public static int attackChoiceDialogue() {
        System.out.println("Choose an attack!");

        for (Move move : battle.getTrainer().getCurrentPokezon().getKnownMoves()) {
            System.out.println(move.getName());
        }

        return Integer.parseInt(input.nextLine());
    }

    public static void lossDialogue() {
        System.out.println("It seems like you don't have what it takes to be a Pokezon master...");
    }
}
