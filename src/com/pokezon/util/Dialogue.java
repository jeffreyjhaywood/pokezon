package com.pokezon.util;

import com.pokezon.*;

import java.util.Scanner;

public final class Dialogue {
    private static final String PROFESSOR_NAME = "Prof. Bezos: ";
    static Scanner input = new Scanner(System.in);

    public static void titleScreenDialogue() {
        try {
            Art.pokezonBanner();
        }
        catch (Exception e) {
        }
        System.out.println("Press Enter to continue...");
        input.nextLine();
    }

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
            SlowTyper.print("1. Charmander");
            SlowTyper.print("2. Bulbasaur");
            SlowTyper.print("3. Squirtle");
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
        SlowTyper.print(PROFESSOR_NAME + player.getCurrentPokezon().getName() + " What a wonderful choice!");
        SlowTyper.print(PROFESSOR_NAME + "Who is that my grandson?");

        Pokezon pokezon = null;

        switch (player.getCurrentPokezon().getName()){
            case "Bulbasaur":
                pokezon = new Pokezon("Charmander", PokeType.FIRE);
                break;

            case "Squirtle":
                pokezon = new Pokezon("Bulbasaur", PokeType.GRASS);
                break;

            case "Charmander":
                pokezon = new Pokezon("Squirtle", PokeType.WATER);
                break;

            default:
        }

        SlowTyper.print(rival.getName() + ": Oh you chose " + player.getCurrentPokezon().getName()
                + ", I'm going to choose " + pokezon.getName());
        SlowTyper.print(rival.getName() + ": Lets battle!");

        return pokezon;
    }

    public static void finalBattleDialogue(Battle battle) {
        SlowTyper.print(battle.getPlayer().getName() + "... We meet again.");
        SlowTyper.print("My " + battle.getEnemyPokezon().getName()
                + " is going to DESTROY your "
                + battle.getPlayerPokezon().getName());
        SlowTyper.print("Lets battle!");
    }

    public static void battleStartDialogue(Battle battle) { // Pass in a Battle as a parameter
        if (battle.getClass() == TrainerBattle.class) {
            TrainerBattle trainerBattle = (TrainerBattle) battle;

            SlowTyper.print(trainerBattle.getEnemyTrainer().getName() + " has started a battle!");
            SlowTyper.print(trainerBattle.getEnemyTrainer().getName() + " sends out "
                    + trainerBattle.getEnemyTrainer().getCurrentPokezon().getName());

        }
        else {
            WildBattle wildBattle = (WildBattle) battle;

            SlowTyper.print(wildBattle.getEnemyPokezon().getName() + " has started a battle!");
        }

        SlowTyper.print(battle.getPlayer().getName() + " sends out " + battle.getPlayer().getCurrentPokezon().getName());
    }

    public static void battleDiagnosticsDialogue(Battle battle) {
        SlowTyper.print(battle.getPlayerPokezon().getName() + " | "
                + "Level: "
                + battle.getPlayerPokezon().getLevel()
                + " | Current HP: "
                + battle.getPlayerPokezon().getCurrentHealth());
        SlowTyper.print(battle.getEnemyPokezon().getName() + " | "
                + "Level: "
                + battle.getEnemyPokezon().getLevel()
                + " | current HP: "
                + battle.getEnemyPokezon().getCurrentHealth());
    }

    public static int battleChoiceDialogue() { // Pass in a Battle as a parameter
        SlowTyper.print("What would you like to do?");

        boolean acceptedInput = false;
        int choice = 0;
        while (!acceptedInput) {
            SlowTyper.print("1. Attack");
            SlowTyper.print("2. Switch Pokezon");
            SlowTyper.print("3. Quit Game");
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
        SlowTyper.print("Choose an attack!");

        int choice = 0;
        boolean acceptedInput = false;
        while (!acceptedInput) {
            int i = 0;
            for (Move move : battle.getPlayer().getCurrentPokezon().getKnownMoves()) {
                if (move != null) {
                    i++;
                    SlowTyper.print(i + ". " + move.getName());
                }
            }
            choice = input.nextInt();
            if (choice >= 1 && choice <= i) {
                acceptedInput = true;
            }
            else {
                SlowTyper.print("Choose a valid selection!");
            }
        }

        return choice;
    }

    public static int pokezonChoiceDialogue(Battle battle) {
        SlowTyper.print("Choose one of your Pokezon!");

        int choice = 0;
        boolean acceptedInput = false;
        while (!acceptedInput) {
            int i = 0;

            for (Pokezon pokezon : battle.getPlayer().getPokezonTeam()) {
                i++;
                if (pokezon != null) {
                    SlowTyper.print(i + ". " + pokezon.getName());
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
        SlowTyper.print("You have won the battle!");
    }

    public static void xpGainedDialogue(Battle battle) {
        SlowTyper.print(battle.getPlayerPokezon().getName() + " has earned "
                + battle.getXpForWin()
                + " XP for winning.");
    }

    public static void levelUpDialogue(Battle battle) {
        SlowTyper.print(battle.getPlayerPokezon().getName() + " has leveled up to level " + battle.getPlayerPokezon().getLevel());
    }

    public static void lossDialogue() {
        SlowTyper.print(PROFESSOR_NAME + "It seems like you don't have what it takes to be a Pokezon master...");
    }

    public static void quitGameDialogue() {
        SlowTyper.print("Game is terminated, thank you for playing!");
    }

    public static void beatGameDialogue(Trainer player, Trainer rival) {
        SlowTyper.print(rival.getName() + ": I can't believe I lost... again...");
        SlowTyper.print(rival.getName() + ": I underestimated you, " + player.getName());
        SlowTyper.print(rival.getName() + ": I'm going to go back to training my Pokezon and beat you next time!");
        SlowTyper.print(PROFESSOR_NAME + "WOW! You defeated everyone including " + rival.getName() + "?");
        SlowTyper.print(PROFESSOR_NAME + "You truly are the Pokezon master.");
    }
}
