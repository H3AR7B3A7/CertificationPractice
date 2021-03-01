package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter7;

// CREATING THREADS

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

public class Concurrency {
    public static void main(String[] args) { // The order of execution is unknown until runtime
        System.out.println("Begin main thread");
        new ReadInventoryThread().start();
        new Thread(new PrintData()).start();
        new ReadInventoryThread().start();
        Runnable thread = () -> System.out.println("Running on main thread..."); // Using Lambda
        thread.run(); // Does not 'start' a new thread!!!
        new Thread(() -> System.out.println("Running on new thread...")).start();
        System.out.println("End main thread");
    }
}


// POLLING WITH SLEEP

class PollingWithSleepExample {
    private static int COUNTER = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                COUNTER++;
            }
        }).start();
        while (COUNTER < 100) {
            System.out.println("Not reached...");
            Thread.sleep(1000); // Implements polling to prevent locking up our program.
        }
        System.out.println("Reached!!!");
    }
}

// CONCURRENCY API WITH EXECUTOR SERVICE

class Single {
    public static void main(String[] args) {
        ExecutorService executorService = null;
        Runnable task1 = () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Printing record" + i);
            }
        };
        Runnable task2 = () -> System.out.println("Printing inventory...");
        try {
            executorService = Executors.newSingleThreadExecutor(); // One thread, tasks will complete in stated order.
            System.out.println("Begin...");
            executorService.execute(task2);
            executorService.execute(task1);
            executorService.execute(task2);
            System.out.println("End");
        } finally {
            if(executorService != null){
                executorService.shutdown(); // Close the executor!!!
            }
        }
    }
}

