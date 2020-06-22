package com.pokezon.gameclient;

import com.pokezon.Battle;
import com.pokezon.Pokezon;
import com.pokezon.Trainer;
import com.pokezon.TrainerBattle;

import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
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

        Pokezon[] pokezonTeam = new Pokezon[3];
        pokezonTeam[0] = pokezon;
        player.setPokezonTeam(pokezonTeam);

        player.setCurrentPokezon(pokezonTeam[0]);

        Pokezon[] rivalPokezonTeam = new Pokezon[3];

        rivalPokezonTeam[0] = Dialogue.meetingRivalDialogue(player, rivalPlayer);

        rivalPlayer.setPokezonTeam(rivalPokezonTeam);
        rivalPlayer.setCurrentPokezon(rivalPokezonTeam[0]);

        int numWins = 0;

        Battle battle;

        while (numWins < 5) {

            int numRounds = 0;
            while (true) { // Check to see if battle is still happening
                if (numRounds == 0) {
                    battle = new TrainerBattle(rivalPlayer);
                    battle.setPlayer(player);
                    Dialogue.battleStartDialogue(battle);
                } else {
                    battle = Battle.randomBattle();
                    Dialogue.battleStartDialogue(battle);
                }

                int choice = Dialogue.battleChoiceDialogue();
                if (choice == 1) {
                    //battle.getPlayer().getCurrentPokezon().useAttack(Dialogue.attackChoiceDialogue(battle));
                    System.out.println("Player chose attack " + Dialogue.attackChoiceDialogue(battle));
                } else if (choice == 2) {
//                    player.setCurrentPokezon(Dialogue.pokezonChoiceDialogue(battle));
                    Dialogue.pokezonChoiceDialogue(battle);
                    Pokezon newPokezon = new Pokezon();
                    newPokezon.setName("Charmander");
                    player.setCurrentPokezon(newPokezon);
                    System.out.println(player.getName() + " has chosen " + player.getCurrentPokezon().getName() + "!");
                }

                if (true) // Player won the battle
                {
                    numRounds++;
                } else { // Player lost the battle
                    Dialogue.lossDialogue();
                }

            }

        }
    }
}
