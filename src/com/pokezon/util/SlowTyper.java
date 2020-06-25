package com.pokezon.util;

import java.util.Scanner;

public final class SlowTyper {
    private static final int PRINT_MILLIS = 0;
    private static final int LOADING_MILLIS = 115;
    private static Scanner enterToContinue = new Scanner(System.in);

    public static void print(String input){
        for(int i = 0; i < input.length(); i++) {
            System.out.print(input.charAt(i));
            try
            {
                Thread.sleep(PRINT_MILLIS);
            }
            catch (InterruptedException ie)
            {
                System.out.println("Interrupted Exception");
            }
        }
        System.out.print("\n");
        enterToContinue.nextLine();
    }

    public static void print(String input, int zeroForNoEnterWhenInputRequest){
        for(int i = 0; i < input.length(); i++) {
            System.out.print(input.charAt(i));
            try
            {
                Thread.sleep(PRINT_MILLIS);
            }
            catch (InterruptedException ie)
            {
                System.out.println("Interrupted Exception");
            }
        }
        System.out.print("\n");
    }

    public static void loadingBar(){
        String bar = "|||||||||||||||||||||||||||||||||||||||||||||:100%";
        for(int i = 0; i < bar.length(); i++) {
            System.out.print(bar.charAt(i));
            try
            {
                Thread.sleep(LOADING_MILLIS);
            }
            catch (InterruptedException ie)
            {
                System.out.println("Interrupted Exception");
            }
        }
        System.out.print("\n");
    }
}
