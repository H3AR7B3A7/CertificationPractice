package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter7;

// CREATING THREADS

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
            if (executorService != null) {
                executorService.shutdown(); // Close the executor!!!
            }
        }
    }
}

// FUTURE

class SingleFuture {
    private static int COUNTER = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = null;
        try {
            System.out.println("Get ready...");
            executorService = Executors.newSingleThreadExecutor(); // One thread, tasks will complete in stated order.
            System.out.println("set...");
            Future<?> result = executorService.submit(() -> {
                System.out.println("go");
                for (int i = 0; i < 1_000_000; i++) {
                    COUNTER++;
                }
                return "finish";
            });
            while (COUNTER < 500_000) {
                System.out.println("Not Reached!!!");
                Thread.sleep(1);
            }
            System.out.println(result.get(1, TimeUnit.MILLISECONDS));
            if (COUNTER > 500_000) {
                System.out.println("Reached!!!");
            }
            System.out.println("END");
        } catch (TimeoutException e) {
            System.out.println("Not reached in time.");
        } finally {
            if (executorService != null) {
                executorService.shutdown(); // Close the executor!!!
            }
        }
    }
}

// WAITING FOR RESULTS

class FutureExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();
            Future<Integer> result = executorService.submit(() -> 20 + 22);
            System.out.println(result.get());
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }
}

// TASK COLLECTIONS

class ListOfFutures {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = null;
        System.out.println("START");
        Callable<String> task = () -> "bla";
        try {
            executorService = Executors.newSingleThreadExecutor();
            List<Future<String>> list = executorService.invokeAll(List.of(task, task, task)); // Executed synchronously
            for (Future<String> future : list) {
                System.out.println(future.get());
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
        System.out.println("END");
    }
}

// SCHEDULING

class Schedule {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService executorService = null;
        System.out.println("START");
        Callable<String> task = () -> "bla";
        ScheduledFuture<String> result;
        try {
            executorService = Executors.newSingleThreadScheduledExecutor();
            result = executorService.schedule(task, 10, TimeUnit.SECONDS); // Executed synchronously
            System.out.println(result.getDelay(TimeUnit.SECONDS));
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
        System.out.println("END");
        Thread.sleep(5000);
        System.out.println(result.getDelay(TimeUnit.SECONDS));
    }
}

class Schedule2 {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService executorService = null;
        System.out.println("START");
        Runnable task = () -> System.out.println("Bla");
        ScheduledFuture<?> result;
        try {
            executorService = Executors.newSingleThreadScheduledExecutor();
            result = executorService.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS); // Executed synchronously
            System.out.println(result.getDelay(TimeUnit.SECONDS));
        } finally {
            // Not closing thread
        }
        System.out.println("END");
        Thread.sleep(9000);
        System.out.println(result.getDelay(TimeUnit.MILLISECONDS) + "ms");
        executorService.shutdown();
        System.out.println(result.getDelay(TimeUnit.MILLISECONDS) + "ms");
    }
}

// THREAD POOLS

class SheepManager {
    private final AtomicInteger sheepCount = new AtomicInteger(0);

    private void incrementAndReport() {
        System.out.println(sheepCount.incrementAndGet() + " "); // No data will be lost, but will not print in order
    }

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            SheepManager manager = new SheepManager();
            for (int i = 0; i < 10; i++) {
                executorService.submit(manager::incrementAndReport);
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }
}

// SYNCHRONIZED BLOCK / MONITOR

class SheepManagerSynchronized {
    private final AtomicInteger sheepCount = new AtomicInteger(0);

    private void incrementAndReport() {
        synchronized (this) {
            System.out.println(sheepCount.incrementAndGet() + " "); // Synchronized execution
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            SheepManagerSynchronized manager = new SheepManagerSynchronized();
            for (int i = 0; i < 10; i++) {
                executorService.submit(manager::incrementAndReport);
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }
}

class SheepManagerSynchronized2 {
    private final AtomicInteger sheepCount = new AtomicInteger(0);
    private final static AtomicInteger WOLF_COUNT = new AtomicInteger(0);

    private synchronized void incrementAndReportSheep() {
        System.out.println(sheepCount.incrementAndGet() + " sheep, "); // Synchronized execution
    }

    private static synchronized void incrementAndReportWolves() {
        System.out.println(WOLF_COUNT.incrementAndGet() + " wolves, "); // Synchronized execution
    }

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(10);
            SheepManagerSynchronized2 manager = new SheepManagerSynchronized2();
            for (int i = 0; i < 10; i++) {
                executorService.submit(manager::incrementAndReportSheep);
                executorService.submit(SheepManagerSynchronized2::incrementAndReportWolves);
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }
}

// LOCK FRAMEWORK

class SheepManagerLocked {
    private Integer sheepCount = 0; // Need for atomic integer ???

    private void incrementAndReport(Lock lock) {
        try {
            lock.lock();
            System.out.println(++sheepCount + " "); // Synchronized execution
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            SheepManagerLocked manager = new SheepManagerLocked();
            Lock lock = new ReentrantLock();
            for (int i = 0; i < 10; i++) {
                executorService.submit(new Thread(() -> manager.incrementAndReport(lock)));
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }
}

class TryLock {
    public static void print(Lock lock) {
        try {
            lock.lock();
            int i;
            for (i = 0; i < 100000000; i++) {
                i++;
            }
            System.out.println(i);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread(() -> print(lock)).start();
        if (lock.tryLock()) {
            try {
                System.out.println("Lock obtained, entering protected code.");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Unable to acquire lock, doing something else.");
        }
    }
}

// CYCLIC BARRIER

class LionPenManager {
    private void removeLions() {
        System.out.println("Removing lions...");
    }

    private void cleanPen() {
        System.out.println("Cleaning pen...");
    }

    private void addLions() {
        System.out.println("Adding lions...");
    }

    public void performLionPenCleaning(CyclicBarrier barrier1, CyclicBarrier barrier2) {
        try {
            removeLions();
            barrier1.await();
            cleanPen();
            barrier2.await();
            addLions();
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(4);
            var manager = new LionPenManager();
            var b1 = new CyclicBarrier(4);
            var b2 = new CyclicBarrier(4, () -> System.out.println("*** PEN CLEANED!!! ***"));
            for (int i = 0; i < 4; i++) {
                executorService.submit(() -> manager.performLionPenCleaning(b1, b2));
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }
}

// CONCURRENT COLLECTIONS

class ConcurrentCollections {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("test1", "test");
        map.put("test2", "test");
        for (String key: map.keySet()) {
            map.remove(key); // This would throw an error on the second loop for a regular hashmap
        }


    }
}