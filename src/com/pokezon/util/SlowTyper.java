package com.pokezon.util;

public final class SlowTyper {
    public static void print(String input){
        for(int i = 0; i < input.length(); i++) {
            System.out.print(input.charAt(i));
            try
            {
                Thread.sleep(0);
            }
            catch (InterruptedException ie)
            {
                System.out.println("Interrupted Exception");
            }
        }
        System.out.print("\n");
    }
}
