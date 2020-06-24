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
    }
}
