package be.dog.d.steven.exam1Z0_815.chapter10;

import java.io.IOException;
import java.sql.SQLException;

public class Exceptions {

    public String tryAnything() throws Exception { // Expects any exception inheriting from Exception class
        throw new IOException(); // Creating an exception object requires the 'new' keyword
        // Any code coming after this would not be reachable
    }

    public int trySomething() throws CloneNotSupportedException { // Expects specific exception
        throw new CloneNotSupportedException();
    }

    public int trySomethingElse() throws SQLException {
        throw new SQLException();
    }

    public int tryAnotherSomethingElse() throws ClassNotFoundException { // Don't need to expect RuntimeExceptions
        throw new ClassNotFoundException();
    }

    public int tryAnotherSomethingElseEntirely() { // Don't need to expect RuntimeExceptions
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        Exceptions exceptions = new Exceptions();
        try {
            exceptions.trySomething(); // Execution of try block ends after first Exception is thrown
            exceptions.trySomethingElse();
            exceptions.tryAnotherSomethingElse();
            exceptions.tryAnotherSomethingElseEntirely(); // RuntimeException doesn't need to be handled
        } catch (CloneNotSupportedException e) { // We can chain catch blocks
            System.out.println("Handle this differently ...");
            e.printStackTrace(); // Curly braces are required even with just 1 statement
        } catch (SQLException | ClassNotFoundException f) { // We can use a multi-catch block
            System.out.println("Handle these in the same way ..."); // This block will not run because of CloneNotSupportedException
            f.printStackTrace();
        } finally { // Optionally we can add a finally block
            System.out.println("Exceptional!"); // This will print after try block executes OR fails to execute
        }
    }
}

// PRINTING EXCEPTIONS

class BumpedKneeException extends Exception {
    public BumpedKneeException(String message) {
        super(message);
    }
}

class Printing {

    private static void run() throws BumpedKneeException {
        throw new BumpedKneeException("It hurts!");
    }

    public static void main(String[] args) {
        try {
            Printing.run();
        } catch (BumpedKneeException b) {
            System.out.println(b);
            System.out.println(b.getMessage());
            b.printStackTrace();
        }
    }
}