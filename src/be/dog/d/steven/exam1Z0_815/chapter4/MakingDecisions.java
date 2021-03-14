package be.dog.d.steven.exam1Z0_815.chapter4;

import java.util.ArrayList;
import java.util.List;

public class MakingDecisions {
    static int a;
    static int b;
    static String s;
    static List<Integer> list = new ArrayList<>();

    // FLOW CONTROL

    public static void main(String[] args) {
        // IF / ELSE
        if (a<b) {
            // Code
        } else if (a==b) {
            // Code
        } else {
            // Code
        }

        // WHILE
        while (a<b){
            // Code
        }

        // FOR
        for (int i = 0; i < a; i++) {
            // Code
        }

        // ADVANCED FOR
        for (int i: list) {
            // Code
        }

        // SWITCH
        switch (a){ // byte, short, char, int, String or enum
            case 1:
                // Code
                break;
            case 2:
                // Code
                break;
            case 3:
                // Code
                break;
            default:
                // Code
                break;
        }

        switch (s) {
            case "us" -> System.out.println("United States");
            case "de" -> System.out.println("Germany");
            case "sk" -> System.out.println("Slovakia");
            case "hu" -> System.out.println("Hungary");
            default -> System.out.println("Unknown");
        }
    }
}
