package be.dog.d.steven.chapter1; // Package declaration: before import statements

import java.lang.*; // Import statement: java.lang is redundant, always implicitly imported

// After imports: The class declaration is the only required element in a file
class WelcomeToJava { // Only one public class, if present it always uses file name
    private static int a; // The order of elements within the class is irrelevant
    private int b; // Field and method declarations have to be wrapped by a class

    public static void main(String[] args) {
        // Main method is the 'driver', code execution starts here
        String arg = args[0]; // Using command line arguments
    }

    public void read() {
    }

    private static String c;
    private String d;

    public void write() {
    }
}

/**
 * Javadoc
 *
 * @author steven.dhondt@kenze.be
 */
class anotherClass {

    /* Multiple
     * line comment
     */
    public static void main(String... args) {
        // Single line comment
    }
}

class andAnother {

    public static void main(String args[]) {

    }
}
