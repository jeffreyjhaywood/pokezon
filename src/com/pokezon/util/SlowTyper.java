/**
 * SlowTyper has methods to display text in a slower, retro fashion.
 *
 * Makes it easier for the user to read dialogue.
 *
 * @author Jeffrey J. Haywood, Justin Lamb, Marcelo Nazario
 * @version 0.9
 * @since 2020-06-19
 */
package com.pokezon.util;

import java.util.Scanner;

public final class SlowTyper {
    private static final int PRINT_MILLIS = 0;
    private static final int LOADING_MILLIS = 0;
    private static Scanner enterToContinue = new Scanner(System.in);

    /**
     * Slowly prints a string character-by-character
     * Makes it so user needs to press enter to continue
     *
     * @param input The String to be slowly typed.
     */
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

    /**
     * Displays a loading bar.
     */
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
