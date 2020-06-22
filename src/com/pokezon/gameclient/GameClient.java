package com.pokezon.gameclient;

import com.pokezon.Battle;
import com.pokezon.Trainer;

import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        Trainer player = new Trainer(Dialogue.introDialogue());

        int numWins = 0;

        while (numWins < 10) {
            Battle battle = Battle.randomBattle();
            battle.setPlayer(player);

            if (numWins == 0) {
                Dialogue.battleStartDialogue(battle); // Send in battle parameter
            }

            int choice = Dialogue.battleChoiceDialogue();

            if (choice == 1) {
                //battle.getPlayer().getCurrentPokezon().useAttack(Dialogue.attackChoiceDialogue(battle));
                System.out.println("Player chose attack " + Dialogue.attackChoiceDialogue(battle));
            }

            if (true) // Player won the battle
            {
                numWins++;
            }
            else { // Player lost the battle
                Dialogue.lossDialogue();
            }

        }
    }
}
