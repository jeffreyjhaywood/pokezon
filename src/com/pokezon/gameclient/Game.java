package com.pokezon.gameclient;

import com.pokezon.*;
import com.pokezon.util.Dialogue;
import com.pokezon.util.Sound;

/**
 * Game holds all of the game's story/sequence logic.
 * It calls methods in the game's sequential order.
 *
 * @author Jeffrey J. Haywood, Justin Lamb, Marcelo Nazario
 * @version 0.9
 * @since 2020-06-19
 */
public enum Game {
    GAME_INSTANCE;

    private final int WIN_CONDITION = 5;

    private int numWins = 0;

    private Trainer player;
    private Trainer rivalPlayer;
    private Battle battle;

    /**
     * Runs methods for game in sequential order.
     */
    public void startGame() {
        gameIntro();
        meetProfessor();
        chooseFirstPokezon();
        meetRival();
        firstBattle();

        while (numWins < WIN_CONDITION) {
            battle();
        }

        finalBattle();
        beatGame();
        quitGame();
    }

    /**
     * Displays the game's banner and plays a retro sound.
     */
    private void gameIntro() {
        Thread soundThread = new Thread(new Sound()); // will run at the same time in it's own thread
        soundThread.start();
        Dialogue.titleScreenDialogue();
    }

    /**
     * Introduces player to the Professor and sets
     * the player's given name.
     */
    private void meetProfessor() {
        String playerName = Dialogue.introDialogue();
        player = new Trainer(playerName);
    }

    /**
     * Creates Pokezon object based upon player's
     * selection and adds it to player's pokezonTeam.
     */
    private void chooseFirstPokezon() {
        Pokezon playerFirstPokezon = null;

        int pokezonChoice = Dialogue.chooseFirstPokezonDialogue();
        switch (pokezonChoice) {
            case 1:
                playerFirstPokezon = new Pokezon("Charmander", PokeType.FIRE,
                        new Move("Tackle", PokeType.NORMAL, 20),
                        new Move("Ember", PokeType.FIRE, 20));
                break;

            case 2:
                playerFirstPokezon = new Pokezon("Bulbasaur", PokeType.GRASS,
                        new Move("Tackle", PokeType.NORMAL, 20),
                        new Move("Vine Whip", PokeType.GRASS, 20));
                break;

            case 3:
                playerFirstPokezon = new Pokezon("Squirtle", PokeType.WATER,
                        new Move("Tackle", PokeType.NORMAL, 20),
                        new Move("Water Gun", PokeType.WATER, 20));
                break;

            default:
        }

        player.addPokezonToTeam(playerFirstPokezon);
    }

    /**
     * Introduces player to their rival,
     * rival will always choose opposite Pokezon as player.
     */
    private void meetRival() {
        rivalPlayer = new Trainer("Jay");
        Pokezon rivalFirstPokezon = Dialogue.meetingRivalDialogue(player, rivalPlayer);
        rivalPlayer.addPokezonToTeam(rivalFirstPokezon);
    }

    /**
     * The first battle of the game and against player's
     * rival.
     */
    private void firstBattle() {
        battle = new TrainerBattle(player, rivalPlayer);

        Dialogue.battleStartDialogue(battle);

        battleLoop(battle);

        rivalPlayer.choosePokezon(1).restoreHealth();
    }

    /**
     * The final battle of the game against the player's
     * rival.
     */
    private void finalBattle() {
        battle = new TrainerBattle(player, rivalPlayer);

        rivalPlayer.choosePokezon(1).setLevel(10); // Change rival Pokezon level to 10 on final battle.

        Dialogue.finalBattleDialogue(battle, rivalPlayer);
        Dialogue.battleStartDialogue(battle);

        battleLoop(battle);
    }

    /**
     * Will create either a TrainerBattle or WildBattle for the
     * player to battle in.
     */
    private void battle() {
        battle = Battle.randomBattle(player);

        battle.getEnemyPokezon().setLevel(numWins + 1); // Increases the enemy Pokezon level further in story

        Dialogue.battleStartDialogue(battle);

        battleLoop(battle);
    }

    /**
     * Loops through a battle until either the player wins or
     * loses.
     *
     * @param battle The battle instance the player is battling in.
     */
    private void battleLoop(Battle battle) {
        do {
            Dialogue.battleDiagnosticsDialogue(battle);

            int battleChoice = Dialogue.battleChoiceDialogue();

            switch (battleChoice) {
                case 1: // Player chose to attack
                    int moveChoice = Dialogue.attackChoiceDialogue(battle);
                    battle.getPlayer().chooseMove(moveChoice, battle.getPlayerPokezon(), battle.getEnemyPokezon());
                    break;

                case 2: // Player chose to switch to another Pokezon
                    int switchedPokezonChoice = Dialogue.pokezonChoiceDialogue(battle);
                    battle.setPlayerPokezon(player.choosePokezon(switchedPokezonChoice));
                    break;

                case 3: // Player chose to quit the game
                    quitGame();
                    break;

                default:
            }

            // Player won the battle
            if (battle.getEnemyPokezon().isFainted()) {
                battle.setBattleOver(true);
                battle.setPlayerWin(true);

                Dialogue.winDialogue();
                battle.giveXpForWin();
                Dialogue.xpGainedDialogue(battle);

                // Check to see if Pokezon level increased
                int prevLevel = battle.getPlayerPokezon().getLevel();
                battle.getPlayerPokezon().addXP(battle.getXpForWin());
                int postLevel = battle.getPlayerPokezon().getLevel();

                if (postLevel > prevLevel) {
                    Dialogue.levelUpDialogue(battle);
                }

                // Reset player Pokezon back to full health after every battle
                battle.getPlayerPokezon().restoreHealth();

                numWins++;
                break;
            }

            // Enemy attacks player
            if (!battle.isBattleOver()) {
                battle.getEnemyPokezon().useMove(1, battle.getPlayerPokezon());

                // Player was defeated
                if (battle.getPlayerPokezon().isFainted()) {
                    battle.setBattleOver(true);
                    battle.setPlayerWin(false);

                    Dialogue.lossDialogue();
                    quitGame();
                }
            }

        } while(!battle.isBattleOver());

    }

    /**
     * Displays dialogue when the game is beaten.
     */
    private void beatGame() {
        Dialogue.beatGameDialogue(player, rivalPlayer);
    }

    /**
     * Thanks the player for playing the game.
     */
    private void quitGame() {
        Dialogue.quitGameDialogue();
        System.exit(0);
    }
}
