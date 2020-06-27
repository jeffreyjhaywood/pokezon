package com.pokezon.util;

import com.pokezon.*;

import java.util.Scanner;

/**
 * Dialogue is the game's prompter class. Whenever dialogue in the story is required, one
 * of the methods will get called to display the dialogue.
 *
 * Some of the methods will ask for user input and return data relevant to what was asked.
 *
 * nextLine() is called at various  points in Dialogue to have the user have to press enter
 * to continue.
 *
 * @author Jeffrey J. Haywood, Justin Lamb, Marcelo Nazario
 * @version 0.9
 * @since 2020-06-19
 */
public final class Dialogue {
    private static final String PROFESSOR_NAME = "Prof. Bezos: ";
    private static final Scanner input = new Scanner(System.in);

    /**
     * Displays the title screen banner and asks user to press Enter to continue.
     */
    public static void titleScreenDialogue() {
        try {
            Art.pokezonBanner();
            SlowTyper.loadingBar();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Press Enter to continue...");
        input.nextLine();
    }

    /**
     * Displays dialogue at the beginning of the game that introduces the Professor and
     * user will enter their name.
     *
     * @return The player's name that they entered.
     */
    public static String introDialogue() {
        String name;

        SlowTyper.print(PROFESSOR_NAME + ": "  + "Hello and welcome to the wonderful world of Pokezon!");
        SlowTyper.print(PROFESSOR_NAME + ": "  + "I'm " + PROFESSOR_NAME + " what's your name?",0);

        boolean acceptedInput = false;

        do {
            name = input.nextLine();

            if (name != null && !name.equals("")) {
                acceptedInput = true;
            }
            else {
                System.out.println("Enter a valid name, dummy.");
            }

        } while(!acceptedInput);

        SlowTyper.print(PROFESSOR_NAME + ": "  + name + "? What a wonderful name!");
        SlowTyper.print(PROFESSOR_NAME + ": "  + "Hey... how would you like a Pokezon of your very own?");

        return name;
    }

    /**
     * Displays options for user to choose their first Pokezon and will ask for input
     * to choose their first Pokezon.
     *
     * @return Player's selection from the list of Pokezon.
     */
    public static int chooseFirstPokezonDialogue() {
        SlowTyper.print(PROFESSOR_NAME + ": "  + "You can choose one of these 3 starter Pokezon!",0);

        int choice = 0;
        boolean acceptedInput = false;

        do {
            System.out.println("1. Charmander");
            System.out.println("2. Bulbasaur");
            System.out.println("3. Squirtle");

            if (!input.hasNextInt()) {
                input.next();
            }
            else {
                choice = input.nextInt();
            }

            if (choice >= 1 && choice <= 3) {
                acceptedInput = true;
            }
            else {
                System.out.println("Please choose one of the given options.");
            }

        } while (!acceptedInput);

        return choice;
    }

    /**
     * Displays dialogue upon meeting player's rival for the first time.
     * Rival will always choose the Pokezon that is more effective against player Pokezon.
     *
     * @param player The person playing the game's trainer
     * @param rival The rival trainer
     * @return The rival's chosen Pokezon (always opposite of player Pokezon)
     */
    public static Pokezon meetingRivalDialogue(Trainer player, Trainer rival) {
        Pokezon playerPokezon = player.choosePokezon(1);
        Pokezon rivalPokezon = null;

        SlowTyper.print(PROFESSOR_NAME + ": "  + playerPokezon.getName() + " What a wonderful choice!");
        SlowTyper.print("???: .....");
        SlowTyper.print(PROFESSOR_NAME + ": "  + "Who is that my grandson?");

        switch (playerPokezon.getName()){
            case "Bulbasaur":
                rivalPokezon = new Pokezon("Charmander", PokeType.FIRE,
                        new Move("Scratch", PokeType.NORMAL, 20));
                break;

            case "Squirtle":
                rivalPokezon = new Pokezon("Bulbasaur", PokeType.GRASS,
                        new Move("Tackle", PokeType.NORMAL, 20));
                break;

            case "Charmander":
                rivalPokezon = new Pokezon("Squirtle", PokeType.WATER,
                        new Move("Tackle", PokeType.NORMAL, 20));
                break;

            default:
        }

        SlowTyper.print(rival.getName() + ": Oh you chose " + playerPokezon.getName()
                + ", I'm going to choose " + rivalPokezon.getName());
        SlowTyper.print(rival.getName() + ": Lets battle!");

        return rivalPokezon;
    }

    /**
     * Displays dialogue when at the final battle against rival.
     * Final battle will always be against the rival.
     *
     * @param battle The instance of battle that player is currently fighting in.
     */
    public static void finalBattleDialogue(Battle battle, Trainer rivalPlayer) {
        SlowTyper.print(rivalPlayer.getName() + ": "
                + battle.getPlayer().getName()
                + "... We meet again.");

        SlowTyper.print(rivalPlayer.getName() + ": "
                + "My " + battle.getEnemyPokezon().getName()
                + " is going to DESTROY your "
                + battle.getPlayerPokezon().getName());

        SlowTyper.print(rivalPlayer.getName() + ": Lets battle!");
    }

    /**
     * Dialogue for the beginning of a battle.
     * Will show which Pokezon each side chooses to battle.j
     *
     * If it is a TrainerBattle it will display enemy trainer's name,
     * if WildBattle, it will only show the wild Pokezon's name.
     *
     * @param battle The instance of batle that player is currently fighting in.
     */
    public static void battleStartDialogue(Battle battle) { // Pass in a Battle as a parameter
        if (battle.getClass() == TrainerBattle.class) {
            TrainerBattle trainerBattle = (TrainerBattle) battle;

            SlowTyper.print(trainerBattle.getEnemyTrainer().getName()
                    + " has started a battle!");

            SlowTyper.print(trainerBattle.getEnemyTrainer().getName()
                    + " sends out "
                    + trainerBattle.getEnemyPokezon().getName());
        }
        else {
            WildBattle wildBattle = (WildBattle) battle;

            SlowTyper.print(wildBattle.getEnemyPokezon().getName() + " has started a battle!");
        }

        SlowTyper.print(battle.getPlayer().getName()
                + " sends out "
                + battle.getPlayerPokezon().getName());
    }

    /**
     * Displays the name, current health, and level of each Pokezon currently battling.
     *
     * @param battle The instance of battle the player is currently fighting in.
     */
    public static void battleDiagnosticsDialogue(Battle battle) {
        System.out.println(battle.getPlayerPokezon().getName()
                + " | Level: "
                + battle.getPlayerPokezon().getLevel()
                + " | Current HP: "
                + battle.getPlayerPokezon().getCurrentHealth());

        System.out.println(battle.getEnemyPokezon().getName()
                + " | Level: "
                + battle.getEnemyPokezon().getLevel()
                + " | current HP: "
                + battle.getEnemyPokezon().getCurrentHealth());
    }

    /**
     * Displays options to the user to either Attack, Switch Pokezon, or Quit Game
     *
     * @return The option the player chose.
     */
    public static int battleChoiceDialogue() { // Pass in a Battle as a parameter
        System.out.println("What would you like to do?");

        int choice = 0;
        boolean acceptedInput = false;

        do {
            System.out.println("1. Attack");
            System.out.println("2. Switch Pokezon");
            System.out.println("3. Quit Game");

            if (!input.hasNextInt()) {
                input.next();
            }
            else {
                choice = input.nextInt();
            }

            if (choice >= 1 && choice <= 3) {
                acceptedInput = true;
            }
            else {
                System.out.println("Please choose one of the given options.");
            }

        } while (!acceptedInput);

        return choice;
    }

    /**
     * Displays the attack options for the player's Pokezon that is battling.
     *
     * @param battle The instance the player is currently battling in.
     * @return The attack choice the player selected.
     */
    public static int attackChoiceDialogue(Battle battle) {
        System.out.println("Choose an attack!");

        int choice = 0;
        boolean acceptedInput = false;

        do {
            int i = 0;

            for (Move move : battle.getPlayerPokezon().getKnownMoves()) {
                if (move != null) {
                    i++;
                    System.out.println(i + ". " + move.getName());
                }
            }

            if (!input.hasNextInt()) {
                input.next();
            }
            else {
                choice = input.nextInt();
            }

            if (choice >= 1 && choice <= i) {
                acceptedInput = true;
            }
            else {
                System.out.println("Please choose one of the given options.");
            }

        } while(!acceptedInput);

        return choice;
    }

    /**
     * Displays when a Pokezon uses an attack.
     *
     * @param pokezonName Name of the Pokezon using the attack
     * @param attackName Name of the attack
     */
    public static void attackUsedDialog(String pokezonName, String attackName){
        SlowTyper.print(pokezonName + " used " + attackName + "!");
    }

    /**
     * Displays when a Pokezon's move is super effective against the enemy.
     * (FIRE > GRASS, WATER > FIRE, GRASS > WATER)
     */
    public static void superEffectiveDialogue() {
        SlowTyper.print("Super Effective!");
    }

    /**
     * Displays the amount of damage a Pokezon takes when attacked.
     *
     * @param pokezonName Name of the Pokezon being attacked
     * @param incomingDamage Amount of damage Pokezon being attacked is receiving
     */
    public static void tookDamageDialog(String pokezonName, double incomingDamage){
        SlowTyper.print(pokezonName + " took " + incomingDamage + " damage!");
    }

    /**
     * Displays options to switch to a different pokezon on player team
     *
     * @param battle The instance of battle player is currently battling in.
     * @return The choice of what Pokezon player wants to switch to.
     */
    public static int pokezonChoiceDialogue(Battle battle) {
        System.out.println("Choose one of your Pokezon!");

        int choice = 0;
        boolean acceptedInput = false;

        do {
            int i = 0;

            for (Pokezon pokezon : battle.getPlayer().getPokezonTeam()) {
                if (pokezon != null) {
                    i++;
                    System.out.println(i + ". " + pokezon.getName());
                }
            }

            if (!input.hasNextInt()) {
                input.next();
            }
            else {
                choice = input.nextInt();
            }

            if (choice >= 1 && choice <= i) {
                acceptedInput = true;
            }
            else {
                System.out.println("Please choose one of the given options.");
            }

        } while(!acceptedInput);

        return choice;
    }

    /**
     * Displays dialogue when Pokezon faints.
     *
     * @param pokezonName Name of Pokezon that fainted.
     */
    public static void faintedDialogue(String pokezonName) {
        SlowTyper.print(pokezonName + " has fainted.");
    }

    /**
     * Displays dialogue when battle is won.
     */
    public static void winDialogue() {
        SlowTyper.print("You have won the battle!");
    }

    /**
     * Displays the amount of xp Pokezon received after winning a battle.
     *
     * @param battle The instance of battle player is currently battling in.
     */
    public static void xpGainedDialogue(Battle battle) {
        SlowTyper.print(battle.getPlayerPokezon().getName() + " has earned "
                + battle.getXpForWin()
                + " XP for winning.");
    }

    /**
     * Displays whenever a Pokezon levels up.
     *
     * @param battle The instance of battle player is currently battling in.
     */
    public static void levelUpDialogue(Battle battle) {
        SlowTyper.print(battle.getPlayerPokezon().getName() + " has leveled up to level " + battle.getPlayerPokezon().getLevel());
    }

    /**
     * Displays whenever the player loses a battle.
     */
    public static void lossDialogue() {
        SlowTyper.print(PROFESSOR_NAME + ": "  + "It seems like you don't have what it takes to be a Pokezon master...");
    }

    /**
     * Displays whenever the game is terminated.
     */
    public static void quitGameDialogue() {
        SlowTyper.print("Thank you for playing!");
    }

    /**
     * Dialogue after defeating rival in the final battle.
     *
     * @param player The user playing the game.
     * @param rival The player's rival.
     */
    public static void beatGameDialogue(Trainer player, Trainer rival) {
        SlowTyper.print(rival.getName() + ": I can't believe I lost... again...");
        SlowTyper.print(rival.getName() + ": I underestimated you, " + player.getName());
        SlowTyper.print(rival.getName() + ": I'm going to go back to training my Pokezon and beat you next time!");
        SlowTyper.print(PROFESSOR_NAME + ": "  + "WOW! You defeated everyone including " + rival.getName() + "?");
        SlowTyper.print(PROFESSOR_NAME + ": "  + "You truly are the Pokezon master.");
    }
}
