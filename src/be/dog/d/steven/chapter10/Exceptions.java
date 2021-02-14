package be.dog.d.steven.chapter10;

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

    public int tryAnotherSomethingElse() throws ClassNotFoundException{ // Don't need to expect RuntimeExceptions
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
        } catch (CloneNotSupportedException e){ // We can chain catch blocks
            e.printStackTrace();
        } catch (SQLException | ClassNotFoundException f){ // We can use a multi-catch block
            f.printStackTrace();
        } finally { // Optionally we can add a finally block
            System.out.println("Exceptional!"); // This will print after try block executes OR fails to execute
        }
    }
}
