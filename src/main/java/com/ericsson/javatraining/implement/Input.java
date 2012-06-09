package com.ericsson.javatraining.implement;

import java.util.Scanner;

/**
 * Input include a static function to receive commands.
 * 
 * @author eyuufeg
 */
public class Input {

    public static String getString(String tip) {
        System.out.print(tip);
        return (new Scanner(System.in)).nextLine();
    }

}
