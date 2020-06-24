package com.pokezon.gameclient;

public class GameClient {
    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.begin();
        }
        catch (Exception e) {
            System.out.println("Game failed to start.");
        }
    }
}
