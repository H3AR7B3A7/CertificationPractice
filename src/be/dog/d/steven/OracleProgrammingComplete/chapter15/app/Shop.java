package be.dog.d.steven.OracleProgrammingComplete.chapter15.app;

import be.dog.d.steven.OracleProgrammingComplete.chapter15.data.Product;
import be.dog.d.steven.OracleProgrammingComplete.chapter15.data.ProductFactory;
import be.dog.d.steven.OracleProgrammingComplete.chapter15.data.Rating;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Shop {
    private static final Logger LOGGER = Logger.getLogger(Shop.class.getName());

    public static void main(String[] args) {

        AtomicInteger clientCount = new AtomicInteger(0);
        var pf = ProductFactory.getInstance();

        Callable<String> client = () -> {
            String clientId = "Client " + clientCount.incrementAndGet();
            String threadName = Thread.currentThread().getName();
            int productId = ThreadLocalRandom.current().nextInt(3) + 1;
            String languageTag = ProductFactory.getSupportedLocales().stream()
                    .skip(ThreadLocalRandom.current().nextInt(4))
                    .findFirst().get();
            StringBuilder log = new StringBuilder();
            log.append(clientId).append(" ").append(threadName).append("\r\n-\tstart of log\t-\r\n");
            log.append(pf.getDiscounts(languageTag).entrySet().stream()
                    .map(entry -> entry.getKey() + "\t" + entry.getValue())
                    .collect(Collectors.joining("\n"))
            );
            Product product = pf.reviewProduct(4, Rating.FIVE_STAR, "Good stuff");
            log.append((product != null) ? "\r\nProduct " + productId + " reviewed\r\n" : "\r\nProduct " + productId + " not reviewed\r\n");
            pf.printProductReport(productId, languageTag, clientId);
            log.append("\r\n-\tend of log\t-\r\n");
            return log.toString();
        };

        List<Callable<String>> clients = Stream.generate(() -> client).limit(5).collect(Collectors.toList());

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            List<Future<String>> results = executorService.invokeAll(clients);
            executorService.shutdown();
            results.stream().forEach(result -> {
                try {
                    System.out.println(result.get());
                } catch (InterruptedException | ExecutionException e) {
                    LOGGER.log(Level.SEVERE, "Error retrieving client log");
                }
            });
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Error invoking client");
        }
    }
}
