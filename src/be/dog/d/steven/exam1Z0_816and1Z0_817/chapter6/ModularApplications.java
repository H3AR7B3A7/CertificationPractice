package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter6;

// CREATING THREADS

class PrintData implements Runnable { // Implementing Runnable
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Printing record: " + i);
        }
    }

    public static void main(String[] args) {
        new Thread(new PrintData()).start();
    }
}

class ReadInventoryThread extends Thread { // Extending Thread
    @Override
    public void run() {
        System.out.println("Printing inventory...");
    }

    public static void main(String[] args) {
        new ReadInventoryThread().start();
    }
}

public class ModularApplications {
    public static void main(String[] args) {
        Runnable thread = () -> System.out.println("Running thread..."); // Using Lambda
        thread.run();
    }
}
