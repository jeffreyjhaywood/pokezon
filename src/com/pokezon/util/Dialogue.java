package com.pokezon.util;

import com.pokezon.*;

import java.util.Scanner;

public final class Dialogue {
    private static final int TEXT_SPEED = 0;
    private static final String PROFESSOR_NAME = "Prof. Bezos: ";
    static Scanner input = new Scanner(System.in);

    public static String introDialogue() {
        String name = null;

        SlowTyper.print(PROFESSOR_NAME + "Hello and welcome to the wonderful world of Pokezon!");
        SlowTyper.print(PROFESSOR_NAME + "I'm Prof. Bezos, what's your name?");

        boolean acceptedInput = false;
        while (!acceptedInput) {
            name = input.nextLine();
            if (name != null && !name.equals("")) {
                acceptedInput = true;
            }
            else {
                SlowTyper.print("Enter a valid name, dummy.");
            }
        }

        SlowTyper.print(PROFESSOR_NAME + name + "? What a wonderful name!");
        SlowTyper.print(PROFESSOR_NAME + "Hey... how would you like a Pokezon of your very own?");

        return name;
    }

    public static int chooseFirstPokezonDialogue() {
        SlowTyper.print(PROFESSOR_NAME + "You can choose one of these 3 starter Pokezon!");

        int choice = 0;
        boolean acceptedInput = false;
        while (!acceptedInput) {
            System.out.println("1. Charmander");
            System.out.println("2. Bulbasaur");
            System.out.println("3. Squirtle");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 3) {
                acceptedInput = true;
            }
            else {
                SlowTyper.print("Are you kidding me? I'm offering you a free Pokezon and you can't just give the correct input?");
            }
        }


        return choice;
    }

    public static Pokezon meetingRivalDialogue(Trainer player, Trainer rival) {
        System.out.println(PROFESSOR_NAME + player.getCurrentPokezon().getName() + " What a wonderful choice!");
        System.out.println(PROFESSOR_NAME + "Who is that my grandson?");

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

        System.out.println(rival.getName() + ": Oh you chose " + player.getCurrentPokezon().getName()
                + ", I'm going to choose " + pokezon.getName());
        System.out.println(rival.getName() + ": Lets battle!");

        return pokezon;
    }

    public static void battleStartDialogue(Battle battle) { // Pass in a Battle as a parameter
        if (battle.getClass() == TrainerBattle.class) {
            TrainerBattle trainerBattle = (TrainerBattle) battle;

            System.out.println(trainerBattle.getEnemyTrainer().getName() + " has started a battle!");
            System.out.println(trainerBattle.getEnemyTrainer().getName() + " sends out "
                    + trainerBattle.getEnemyTrainer().getCurrentPokezon().getName());

        }
        else {
            WildBattle wildBattle = (WildBattle) battle;

            System.out.println(wildBattle.getEnemyPokezon().getName() + " has started a battle!");
        }

        System.out.println(battle.getPlayer().getName() + " sends out " + battle.getPlayer().getCurrentPokezon().getName());
    }

    public static void battleDiagnosticsDialogue(Battle battle) {
        System.out.println(battle.getPlayerPokezon().getName()
                + " current HP: "
                + battle.getPlayerPokezon().getCurrentHealth());
        System.out.println(battle.getEnemyPokezon().getName()
                + " current HP: "
                + battle.getEnemyPokezon().getCurrentHealth());
    }

    public static int battleChoiceDialogue() { // Pass in a Battle as a parameter
        System.out.println("What would you like to do?");

        boolean acceptedInput = false;
        int choice = 0;
        while (!acceptedInput) {
            System.out.println("1. Attack");
            System.out.println("2. Switch Pokezon");
            System.out.println("3. Quit Game");
            choice = input.nextInt();

            if (choice >= 1 && choice <= 3) {
                acceptedInput = true;
            }
            else {
                SlowTyper.print("There are only three options...");
            }
        }

        return choice;
    }

    public static int attackChoiceDialogue(Battle battle) {
        System.out.println("Choose an attack!");

        int choice = 0;
        boolean acceptdInput = false;

        while (!acceptdInput) {
            int i = 0;
            for (Move move : battle.getPlayer().getCurrentPokezon().getKnownMoves()) {
                if (move != null) {
                    i++;
                    System.out.println(i + ". " + move.getName());
                }
            }
            choice = input.nextInt();
            if (choice >= 1 && choice <= i) {
                acceptdInput = true;
            }
            else {
                SlowTyper.print("Choose a valid selection!");
            }
        }

        return choice;
    }

    public static int pokezonChoiceDialogue(Battle battle) {
        System.out.println("Choose one of your Pokezon!");

        int choice = 0;
        boolean acceptedInput = false;

        while (!acceptedInput) {
            int i = 0;

            for (Pokezon pokezon : battle.getPlayer().getPokezonTeam()) {
                i++;
                if (pokezon != null) {
                    System.out.println(i + ". " + pokezon.getName());
                }
            }
            choice = input.nextInt();

            if (choice >= 1 && choice < i) {
                acceptedInput = true;
            }
            else {
                SlowTyper.print("Enter a valid input!");
            }
        }

        return choice;
    }

    public static void winDialogue() {
        System.out.println("You have won the battle!");
    }

    public static void lossDialogue() {
        System.out.println(PROFESSOR_NAME + "It seems like you don't have what it takes to be a Pokezon master...");
    }

    public static void quitGameDialogue() {
        System.out.println("Game is terminated, thank you for playing!");
    }

    public static void beatGameDialogue() {
        System.out.println(PROFESSOR_NAME + "WOW! You defeated everyone including your rival?!");
        System.out.println(PROFESSOR_NAME + "You truly are the Pokezon master.");
    }
}
