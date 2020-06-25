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
     * Calls begin() method in game to start the game.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.begin();
        }
        catch (Exception e) {
            System.out.println("Game has crashed. You probably gave a bad input.");
        }
    }
}
