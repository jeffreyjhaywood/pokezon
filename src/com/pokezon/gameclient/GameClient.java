package com.pokezon.gameclient;

public class GameClient {
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
