/**
 * Art takes a given file and draws a banner.
 *
 * @author Jeffrey J. Haywood, Justin Lamb, Marcelo Nazario
 * @version 0.9
 * @since 2020-06-19
 */
package com.pokezon.util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Art {
    /**
     * Uses the given file and draws a banner using ASCII art.
     */
    public static void pokezonBanner() {
        try {
            File myLocalFile = new File("art.txt");
            Scanner scanner = new Scanner(myLocalFile);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
        catch (IOException ioe) {
            System.out.println("IOException");
        }
    }
}
