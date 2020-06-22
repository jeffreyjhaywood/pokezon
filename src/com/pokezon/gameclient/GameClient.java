package com.pokezon.gameclient;

import com.pokezon.Battle;
import com.pokezon.Pokezon;
import com.pokezon.Trainer;

import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        Trainer player = new Trainer(Dialogue.introDialogue());
        Pokezon[] team = {new Pokezon(), new Pokezon(), new Pokezon()};
        team[0].setName("Charmander");
        team[1].setName("Bulbasaur");
        team[2].setName("Squirtle");
        player.setPokezonTeam(team);

        int numWins = 0;

        while (numWins < 5) {
            Battle battle = Battle.randomBattle();
            battle.setPlayer(player);

            int numRounds = 0;
            while (true) { // Check to see if battle is still happening
                if (numRounds == 0) {
                    Dialogue.battleStartDialogue(battle);
                }

                int choice = Dialogue.battleChoiceDialogue();
                if (choice == 1) {
                    //battle.getPlayer().getCurrentPokezon().useAttack(Dialogue.attackChoiceDialogue(battle));
                    System.out.println("Player chose attack " + Dialogue.attackChoiceDialogue(battle));
                }
                else if(choice == 2) {
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
