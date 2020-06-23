package com.pokezon.gameclient;

import com.pokezon.Battle;
import com.pokezon.Pokezon;
import com.pokezon.Trainer;
import com.pokezon.TrainerBattle;

public class GameClient {
    public static void main(String[] args) throws Exception {
        Trainer player = new Trainer(Dialogue.introDialogue());
        Trainer rivalPlayer = new Trainer("Jay");

        Pokezon pokezon = new Pokezon();

        switch (Dialogue.chooseFirstPokezonDialogue()) {
            case 1:
                pokezon.setName("Charmander");
                break;

            case 2:
                pokezon.setName("Bulbasaur");
                break;

            case 3:
                pokezon.setName("Squirtle");
                break;

            default:
        }

        // Add chosen Pokezon to player team
        Pokezon[] pokezonTeam = new Pokezon[3];
        pokezonTeam[0] = pokezon;
        player.setPokezonTeam(pokezonTeam);
        player.setCurrentPokezon(pokezonTeam[0]);

        // Add rival's chosen Pokezen to rival team
        Pokezon[] rivalPokezonTeam = new Pokezon[3];
        rivalPokezonTeam[0] = Dialogue.meetingRivalDialogue(player, rivalPlayer);
        rivalPlayer.setPokezonTeam(rivalPokezonTeam);
        rivalPlayer.setCurrentPokezon(rivalPokezonTeam[0]);

        int numWins = 0;

        Battle battle;

        while (numWins < 5) { // Game win condition is to win 5 battles, loop until 5 wins or player loses.
            int numRounds = 0;

            while (true) { // Keep looping until the battle is over.

                if (numWins == 0) { // Always fight rival at the beginning of game
                    battle = new TrainerBattle(rivalPlayer);
                }
                else {
                    battle = Battle.randomBattle();
                }
                battle.setPlayer(player);

                if (numRounds == 0) { // At the beginning of a battle
                    Dialogue.battleStartDialogue(battle);
                }

                switch (Dialogue.battleChoiceDialogue()) {
                    case 1:
//                        battle.getPlayer().getCurrentPokezon().useMove(Dialogue.attackChoiceDialogue(battle));
                        System.out.println("Player chose attack " + Dialogue.attackChoiceDialogue(battle));
//                        battle.getPlayerPokezon().useMove(Dialogue.attackChoiceDialogue(battle), battle.getEnemyPokezon());
                        break;

                    case 2:
//                        player.setCurrentPokezon(Dialogue.pokezonChoiceDialogue(battle));
                        Dialogue.pokezonChoiceDialogue(battle);
                        System.out.println(player.getName() + " has chosen " + player.getCurrentPokezon().getName() + "!");
                        break;

                    case 3:
                        Dialogue.quitGameDialogue();
                        System.exit(0);
                        break;

                    default:
                }

                if (false) { // Condition that player won the battle
                    numWins++;
                    break;
                }
                else if (false) { // Player lost the battle
                    Dialogue.lossDialogue();
                    System.exit(0); // Quits the game
                }

                numRounds++;
            }
        }
    }
}
