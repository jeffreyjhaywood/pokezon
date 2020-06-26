/**
 * Game holds all of the game's story/sequence logic.
 * It calls methods in the game's sequential order.
 *
 * @author Jeffrey J. Haywood, Justin Lamb, Marcelo Nazario
 * @version 0.9
 * @since 2020-06-19
 */
package com.pokezon.gameclient;

import com.pokezon.*;
import com.pokezon.util.Dialogue;
import com.pokezon.util.Sound;

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
     * the player's given name
     */
    private void meetProfessor() {
        String playerName = Dialogue.introDialogue();
        player = new Trainer(playerName);
    }

    /**
     * Creates Pokezon object based upon player's
     * selection.
     */
    private void chooseFirstPokezon() {
        // Create player's Pokezon and assign its initial 2 moves to it
        Pokezon playerFirstPokezon;
        Move move1;
        Move move2 = new Move("Tackle", PokeType.NORMAL, 20);

        int pokezonChoice = Dialogue.chooseFirstPokezonDialogue();

        switch (pokezonChoice) {
            case 1:
                playerFirstPokezon = new Pokezon("Charmander", PokeType.FIRE);
                move1 = new Move("Ember", PokeType.FIRE, 20);
                playerFirstPokezon.setMove(move1);
                playerFirstPokezon.setMove(move2);
                break;

            case 2:
                playerFirstPokezon = new Pokezon("Bulbasaur", PokeType.GRASS);
                move1 = new Move("Vine Whip", PokeType.GRASS, 20);
                playerFirstPokezon.setMove(move1);
                playerFirstPokezon.setMove(move2);
                break;

            case 3:
                playerFirstPokezon = new Pokezon("Squirtle", PokeType.WATER);
                move1 = new Move("Water Gun", PokeType.WATER, 20);
                playerFirstPokezon.setMove(move1);
                playerFirstPokezon.setMove(move2);
                break;

            default:
                playerFirstPokezon = null;
        }

        // Add chosen Pokezon to player team
        player.addPokezonToTeam(playerFirstPokezon);
    }

    /**
     * Introduces player to their rival,
     * rival will always choose opposite Pokezon as player.
     */
    private void meetRival() {
        rivalPlayer = new Trainer("Jay");
        Pokezon rivalFirstPokezon = Dialogue.meetingRivalDialogue(player, rivalPlayer, player.choosePokezon(1));
        rivalFirstPokezon.setMove(new Move("Tackle", PokeType.NORMAL, 20));
        rivalPlayer.addPokezonToTeam(rivalFirstPokezon);
    }

    /**
     * The first battle of the game and against player's
     * rival.
     */
    private void firstBattle() {
        battle = new TrainerBattle(rivalPlayer);
        battle.setEnemyPokezon(rivalPlayer.choosePokezon(1));

        battle.setPlayer(player);
        battle.setPlayerPokezon(player.choosePokezon(1));

        battleLoop(battle);
    }

    /**
     * The final battle of the game against the player's
     * rival.
     */
    private void finalBattle() {
        battle = new TrainerBattle(rivalPlayer);

        battle.setPlayer(player);
        battle.setPlayerPokezon(player.choosePokezon(1));

        Pokezon finalBattlePokezon = rivalPlayer.choosePokezon(1);
        finalBattlePokezon.restoreHealth(); // Reset rival Pokezon's health to max
        finalBattlePokezon.setLevel(10);
        battle.setEnemyPokezon(finalBattlePokezon);

        Dialogue.finalBattleDialogue(battle, rivalPlayer);

        battleLoop(battle);
    }

    /**
     * Will create either a TrainerBattle or WildBattle for the
     * player to battle in.
     */
    private void battle() {
        battle = Battle.randomBattle();

        battle.setPlayer(player);
        battle.setPlayerPokezon(player.choosePokezon(1));

        boolean isTrainerBattle = (battle.getClass() == TrainerBattle.class);
        Pokezon enemyPokezon;
        if (isTrainerBattle) {
            enemyPokezon = ((TrainerBattle) battle).getEnemyTrainer().choosePokezon(1);
        }
        else {
            enemyPokezon = new Pokezon("Squirtle", PokeType.WATER); // Change to create random pokezon
            enemyPokezon.setMove(new Move("Water Gun", PokeType.WATER, 20));
        }
        battle.setEnemyPokezon(enemyPokezon);
        battle.getEnemyPokezon().setLevel(numWins + 1); // Increases the enemy Pokezon level further in story

        Dialogue.battleStartDialogue(battle);

        battleLoop(battle);
    }

    /**
     * Loops through a battle until either the player loses or
     * wins.
     *
     * @param battle The battle instance the player is battling in.
     */
    private void battleLoop(Battle battle) {
        while (!battle.isBattleOver()) {
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
        }
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
