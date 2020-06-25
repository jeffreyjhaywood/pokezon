/**
 * Dialogue is the game's prompter class. Whenever dialogue in the story is required, one
 * of the methods will get called to display the dialogue.
 *
 * Some of the methods will ask for user input and return data relevant to what was asked.
 *
 * @author Jeffrey J. Haywood, Justin Lamb, Marcelo Nazario
 * @version 0.9
 * @since 2020-06-19
 */
package com.pokezon.util;

import com.pokezon.*;

import java.util.Scanner;

public final class Dialogue {
    private static final String PROFESSOR_NAME = "Prof. Bezos: ";
    static Scanner input = new Scanner(System.in);

    /**
     * Displays the title screen banner and asks user to press Enter to continue.
     */
    public static void titleScreenDialogue() {
        try {
            Art.pokezonBanner();
            //SlowTyper.loadingBar();
        }
        catch (Exception e) {
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
        String name = null;

        SlowTyper.print(PROFESSOR_NAME + "Hello and welcome to the wonderful world of Pokezon!");
        SlowTyper.print(PROFESSOR_NAME + "I'm Prof. Bezos, what's your name?",0);

        boolean acceptedInput = false;
        while (!acceptedInput) {
            name = input.nextLine();
            if (name != null && !name.equals("")) {
                acceptedInput = true;
            }
            else {
                SlowTyper.print("Enter a valid name, dummy.",0);
            }
        }

        SlowTyper.print(PROFESSOR_NAME + name + "? What a wonderful name!");
        SlowTyper.print(PROFESSOR_NAME + "Hey... how would you like a Pokezon of your very own?");

        return name;
    }

    /**
     * Displays options for user to choose their first Pokezon and will ask for input
     * to choose their first Pokezon.
     *
     * @return Player's selection from the list of Pokezon.
     */
    public static int chooseFirstPokezonDialogue() {
        SlowTyper.print(PROFESSOR_NAME + "You can choose one of these 3 starter Pokezon!",0);

        int choice = 0;
        boolean acceptedInput = false;
        while (!acceptedInput) {
            SlowTyper.print("1. Charmander",0);
            SlowTyper.print("2. Bulbasaur",0);
            SlowTyper.print("3. Squirtle",0);
            choice = input.nextInt();
            if (choice >= 1 && choice <= 3) {
                acceptedInput = true;
            }
            else {
                SlowTyper.print("Are you kidding me? I'm offering you a free Pokezon and you can't just give the correct input?",0);
            }
        }

        return choice;
    }

    /**
     * Displays dialogue upon meeting player's rival for the first time.
     * Rival will always choose the Pokezon that is more effective against player Pokezon.
     *
     * @param player The person playing the game's trainer
     * @param rival The rival trainer
     * @param playerPokezon The player's pokezon
     * @return The rival's chosen Pokezon (always opposite of player Pokezon)
     */
    public static Pokezon meetingRivalDialogue(Trainer player, Trainer rival, Pokezon playerPokezon) {
        SlowTyper.print(PROFESSOR_NAME + playerPokezon.getName() + " What a wonderful choice!");
        SlowTyper.print(PROFESSOR_NAME + "Who is that my grandson?");

        Pokezon pokezon = null;

        switch (playerPokezon.getName()){
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

        SlowTyper.print(rival.getName() + ": Oh you chose " + playerPokezon.getName()
                + ", I'm going to choose " + pokezon.getName());
        SlowTyper.print(rival.getName() + ": Lets battle!");

        return pokezon;
    }

    /**
     * Displays dialogue when at the final battle against rival.
     * Final battle will always be against the rival.
     *
     * @param battle The instance of battle that player is currently fighting in.
     */
    public static void finalBattleDialogue(Battle battle) {
        SlowTyper.print(battle.getPlayer().getName() + "... We meet again.");
        SlowTyper.print("My " + battle.getEnemyPokezon().getName()
                + " is going to DESTROY your "
                + battle.getPlayerPokezon().getName());
        SlowTyper.print("Lets battle!");
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

            SlowTyper.print(trainerBattle.getEnemyTrainer().getName() + " has started a battle!");
            SlowTyper.print(trainerBattle.getEnemyTrainer().getName() + " sends out "
                    + trainerBattle.getEnemyPokezon().getName());

        }
        else {
            WildBattle wildBattle = (WildBattle) battle;

            SlowTyper.print(wildBattle.getEnemyPokezon().getName() + " has started a battle!");
        }

        SlowTyper.print(battle.getPlayer().getName() + " sends out " + battle.getPlayerPokezon().getName());
    }

    /**
     * Displays the name, current health, and level of each Pokezon currently battling.
     *
     * @param battle The instance of battle the player is currently fighting in.
     */
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

    /**
     * Displays options to the user to either Attack, Switch Pokezon, or Quit Game
     *
     * @return The option the player chose.
     */
    public static int battleChoiceDialogue() { // Pass in a Battle as a parameter
        SlowTyper.print("What would you like to do?",0);

        boolean acceptedInput = false;
        int choice = 0;
        while (!acceptedInput) {
            SlowTyper.print("1. Attack",0);
            SlowTyper.print("2. Switch Pokezon",0);
            SlowTyper.print("3. Quit Game",0);
            choice = input.nextInt();

            if (choice >= 1 && choice <= 3) {
                acceptedInput = true;
            }
            else {
                SlowTyper.print("There are only three options...",0);
            }
        }

        return choice;
    }

    /**
     * Displays the attack options for the player's Pokezon that is battling.
     *
     * @param battle The instance the player is currently battling in.
     * @return The attack choice the player selected.
     */
    public static int attackChoiceDialogue(Battle battle) {
        SlowTyper.print("Choose an attack!",0);

        int choice = 0;
        boolean acceptedInput = false;
        while (!acceptedInput) {
            int i = 0;
            for (Move move : battle.getPlayerPokezon().getKnownMoves()) {
                if (move != null) {
                    i++;
                    SlowTyper.print(i + ". " + move.getName(),0);
                }
            }
            choice = input.nextInt();
            if (choice >= 1 && choice <= i) {
                acceptedInput = true;
            }
            else {
                SlowTyper.print("Choose a valid selection!",0);
            }
        }

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
        SlowTyper.print("Choose one of your Pokezon!");

        int choice = 0;
        boolean acceptedInput = false;
        while (!acceptedInput) {
            int i = 0;

            for (Pokezon pokezon : battle.getPlayer().getPokezonTeam()) {
                i++;
                if (pokezon != null) {
                    SlowTyper.print(i + ". " + pokezon.getName(),0);
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
        SlowTyper.print(PROFESSOR_NAME + "It seems like you don't have what it takes to be a Pokezon master...");
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
        SlowTyper.print(PROFESSOR_NAME + "WOW! You defeated everyone including " + rival.getName() + "?");
        SlowTyper.print(PROFESSOR_NAME + "You truly are the Pokezon master.");
    }
}
