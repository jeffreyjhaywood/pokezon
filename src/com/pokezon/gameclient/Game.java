package com.pokezon.gameclient;

import com.pokezon.*;
import com.pokezon.util.Dialogue;
import com.pokezon.util.Sound;

public class Game {
    private static final int WIN_CONDITION = 5;

    public void begin() {
//        Thread soundThread = new Thread(new Sound()); // will run at the same time in it's own thread
//        soundThread.start();
        Dialogue.titleScreenDialogue();

        // Ask player their name and instantiate their trainer and enemy rival objects
        String playerName = Dialogue.introDialogue();
        Trainer player = new Trainer(playerName);
        Trainer rivalPlayer = new Trainer("Jay");

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
//        Pokezon[] pokezonTeam = new Pokezon[3];
//        pokezonTeam[0] = pokezon;
        player.addPokezonToTeam(playerFirstPokezon);
//        player.setPokezonTeam(pokezonTeam);
//        player.setCurrentPokezon(pokezonTeam[0]);

        // Add rival's chosen Pokezen to rival team
//        Pokezon[] rivalPokezonTeam = new Pokezon[3];
        Pokezon rivalFirstPokezon = Dialogue.meetingRivalDialogue(player, rivalPlayer, playerFirstPokezon);
        rivalFirstPokezon.setMove(move2);
        rivalPlayer.addPokezonToTeam(rivalFirstPokezon);
//        rivalPokezonTeam[0].setMove(move2);
//        rivalPlayer.setPokezonTeam(rivalPokezonTeam);
//        rivalPlayer.setCurrentPokezon(rivalPokezonTeam[0]);

        int numWins = 0;
        Battle battle;

        while (numWins <= WIN_CONDITION) { // Game win condition is to win 5 battles, loop until 5 wins or player loses.

            if (numWins == 0 || numWins == WIN_CONDITION) { // Always fight rival at the beginning of game
                battle = new TrainerBattle(rivalPlayer);
                battle.setEnemyPokezon(rivalPlayer.choosePokezon(1));
            }
            else {
                battle = Battle.randomBattle();
            }
            battle.setPlayer(player);
            battle.setPlayerPokezon(player.choosePokezon(1));

            boolean isTrainerBattle = battle.getClass() == TrainerBattle.class;
            if (isTrainerBattle) {
                Pokezon enemyPokezon = ((TrainerBattle) battle).getEnemyTrainer().choosePokezon(1);
                battle.setEnemyPokezon(enemyPokezon);
            }
            else {
                Pokezon enemyPokezon = new Pokezon("Squirtle", PokeType.WATER);
                enemyPokezon.setMove(new Move("Water Gun", PokeType.WATER, 20));
                battle.setEnemyPokezon(enemyPokezon); // Change to create random pokezon
            }
            battle.getEnemyPokezon().setLevel(numWins + 1);

            if (numWins == WIN_CONDITION) {
                rivalPlayer.choosePokezon(1).setCurrentHealth(rivalPlayer.choosePokezon(1).getMaxHealth());
                Dialogue.finalBattleDialogue(battle);
            }

            Dialogue.battleStartDialogue(battle);

            while (!battle.isBattleOver()) { // Keep looping until the battle is over.
                Dialogue.battleDiagnosticsDialogue(battle);

                int battleChoice = Dialogue.battleChoiceDialogue();
                switch (battleChoice) {
                    case 1: // Player chose to attack
                        int moveChoice = Dialogue.attackChoiceDialogue(battle);
                        battle.getPlayer().chooseMove(moveChoice, battle.getPlayerPokezon(), battle.getEnemyPokezon());
//                        battle.getPlayerPokezon().useMove(moveChoice, battle.getEnemyPokezon());
                        break;

                    case 2: // Player chose to switch to another Pokezon
                        int switchedPokezonChoice = Dialogue.pokezonChoiceDialogue(battle);
//                        player.setCurrentPokezon(switchedPokezonChoice); // Add public Pokezon getPokezon(int choice) to Trainer
                        battle.setPlayerPokezon(player.choosePokezon(switchedPokezonChoice));
//                        System.out.println(player.getName() + " has chosen " + player.getCurrentPokezon().getName() + "!");
                        break;

                    case 3: // Player chose to quit the game
                        Dialogue.quitGameDialogue();
                        System.exit(0);
                        break;

                    default:
                }

                if (battle.getEnemyPokezon().isFainted()) {
                    battle.setBattleOver(true);
                    battle.setPlayerWin(true);
                    Dialogue.winDialogue();
                    battle.giveXpForWin();
                    Dialogue.xpGainedDialogue(battle);

                    int prevLevel = battle.getPlayerPokezon().getLevel();
                    battle.getPlayerPokezon().addXP(battle.getXpForWin());
                    int postLevel = battle.getPlayerPokezon().getLevel();

                    if (postLevel > prevLevel) {
                        Dialogue.levelUpDialogue(battle);
                    }

                    battle.getPlayerPokezon().setCurrentHealth(battle.getPlayerPokezon().getMaxHealth());
                }

                // Enemy attacks player
                if (!battle.isBattleOver()) {
                    battle.getEnemyPokezon().useMove(1, battle.getPlayerPokezon());
                }

                if (battle.getPlayerPokezon().isFainted()) {
                    battle.setBattleOver(true);
                    battle.setPlayerWin(false);
                }

                if (battle.isBattleOver() && battle.didPlayerWin()) { // Condition that player won the battle
                    numWins++;
                    break;
                }
                else if (battle.isBattleOver() && !battle.didPlayerWin()) { // Player lost the battle
                    Dialogue.lossDialogue();
                    Dialogue.quitGameDialogue();
                    System.exit(0); // Quits the game
                }

            }
        }
        Dialogue.beatGameDialogue(player, rivalPlayer);
        Dialogue.quitGameDialogue();
    }
}
