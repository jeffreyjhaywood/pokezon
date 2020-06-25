package com.pokezon.util;

import java.util.Scanner;

public final class SlowTyper {

    public static void print(String input){
        for(int i = 0; i < input.length(); i++) {
            System.out.print(input.charAt(i));
            try
            {
                Thread.sleep(30);
            }
            catch (InterruptedException ie)
            {
                System.out.println("Interrupted Exception");
            }
        }
        System.out.print("\n");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void print(String input, int zeroForNoEnterWhenInputRequest){
        for(int i = 0; i < input.length(); i++) {
            System.out.print(input.charAt(i));
            try
            {
                Thread.sleep(30);
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
                Thread.sleep(115);
            }
            catch (InterruptedException ie)
            {
                System.out.println("Interrupted Exception");
            }
        }
        System.out.print("\n");
    }
}
