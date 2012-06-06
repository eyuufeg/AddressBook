package com.ericsson.javatraining.implement;

import java.util.Scanner;

public class Input {

    public static String getString(String tip) {
        System.out.print(tip);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }


}
