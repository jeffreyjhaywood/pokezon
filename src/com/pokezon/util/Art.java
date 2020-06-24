package com.pokezon.util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Art {
    public static void pokezonBanner() throws IOException {
        File myLocalFile = new File("art.txt");
        Scanner scanner = new Scanner(myLocalFile);
        while (scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }

        String input;
        do {
            System.out.println("Type Enter to begin or Exit to terminate");
            Scanner scanner1 = new Scanner(System.in);
            input = scanner1.nextLine();
            input = input.toLowerCase();
            switch (input){
                case "exit":
                    return;
                case "enter":
                    break;
                default:
                    System.out.println("Check your spelling");
            }
        }while (!input.equals("enter"));
    }
}
