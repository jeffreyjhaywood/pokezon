package com.pokezon.gameclient;

import com.pokezon.*;

import java.sql.SQLOutput;
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
    public static int chooseFirstPokezonDialogue(){
        System.out.println("Would you like to choose your Pokezon?");
        System.out.println("1. Charmander");
        System.out.println("2. Bulbasaur");
        System.out.println("3. Squirtle");

        int choice = input.nextInt();
        return choice;
    }

    public static Pokezon meetingRivalDialogue(Trainer player, Trainer rival){
        System.out.println(player.getCurrentPokezon().getName() + " What a wonderful choice!");
        System.out.println("Who is that my grandson?");

        Pokezon pokezon = new Pokezon();

        switch (player.getCurrentPokezon().getName()){
            case "Bulbasaur":
                pokezon.setName("Charmander");
                break;
            case "Squirtle":
                pokezon.setName("Bulbasaur");
                break;
            case "Charmander":
                pokezon.setName("Squirtle");
                break;
            default:
        }
        System.out.println("Oh you chose " + player.getCurrentPokezon().getName()
                + ", I'm going to choose " + pokezon.getName());
        System.out.println("Lets battle!");
        return pokezon;
    }

    public static void battleStartDialogue(Battle battle) { // Pass in a Battle as a parameter
        if (battle.getClass() == TrainerBattle.class) {
            TrainerBattle trainerBattle = (TrainerBattle) battle;
            System.out.println(trainerBattle.getEnemyTrainer().getName() + " has started a battle!");
            System.out.println(trainerBattle.getEnemyTrainer().getName() + " sends out "
                    + trainerBattle.getEnemyTrainer().getCurrentPokezon().getName());
        }else {
            WildBattle wildBattle = (WildBattle) battle;
            System.out.println(wildBattle.getEnemyPokezon().getName() + " has started a battle!");
            }

        // if statement for special dialogue?
        System.out.println(battle.getPlayer().getName() + " sends out " + battle.getPlayer().getCurrentPokezon().getName());
    }

    public static int battleChoiceDialogue() { // Pass in a Battle as a parameter
        System.out.println("What would you like to do?");
        System.out.println("1. Attack");
        System.out.println("2. Switch Pokezon");
        System.out.println("3. Quit Game");

        return input.nextInt();
    }

    public static int attackChoiceDialogue(Battle battle) {
        System.out.println("Choose an attack!");
        System.out.println("1. Tackle");
        System.out.println("2. Water Gun");

//        for (Move move : battle.getPlayer().getCurrentPokezon().getKnownMoves()) {
//            System.out.println(move.getName());
//        }

        return input.nextInt();
    }

    public static int pokezonChoiceDialogue(Battle battle) {
        int i = 0;
        for (Pokezon pokezon : battle.getPlayer().getPokezonTeam()) {
            i++;
            System.out.println(i + ". " + pokezon.getName());
        }

        return input.nextInt();
    }

    public static void lossDialogue() {
        System.out.println("It seems like you don't have what it takes to be a Pokezon master...");
    }
}
