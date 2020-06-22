package com.pokezon.gameclient;

import com.pokezon.Trainer;

import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        Trainer player = new Trainer(Dialogue.introDialogue());

        int numWins = 0;

        while (numWins < 10) {
            // Battle battle = Battle.randomBattle();

            if (numWins == 0) {
                Dialogue.battleStartDialogue(); // Send in battle parameter
            }

            int choice = Dialogue.battleChoiceDialogue();

            switch (choice) {
                case 1:

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
