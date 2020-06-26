/**
 * Pokezon is a command line version of Pokemon with an Amazonian twist.
 *
 * @author Jeffrey J. Haywood, Justin Lamb, Marcelo Nazario
 * @version 0.9
 * @since 2020-06-19
 */
package com.pokezon.gameclient;

public class GameClient {
    /**
     * This is the entry point for the Pokezon game.
     * Calls startGame() method in game to start the game.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Game.GAME_INSTANCE.startGame();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Game has crashed");
        }
    }
}
