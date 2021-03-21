package be.dog.d.steven.OracleProgrammingComplete.chapter15.data;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductFactory {
    private Map<Product, List<Review>> products = new HashMap<>();
//    private ResourceFormatter formatter;

    private final ResourceBundle config = ResourceBundle.getBundle("be/dog/d/steven/OracleProgrammingComplete/chapter15/data/config");
    private final MessageFormat reviewFormat = new MessageFormat(config.getString("review.data.format"));
    private final MessageFormat productFormat = new MessageFormat(config.getString("product.data.format"));

    private final Path reportsFolder = Path.of(config.getString("reports.folder"));
    private final Path dataFolder = Path.of(config.getString("data.folder"));
    private final Path tempFolder = Path.of(config.getString("temp.folder"));

    private static final Logger LOGGER = Logger.getLogger(ProductFactory.class.getName());

    private static final Map<String, ResourceFormatter> FORMATTERS =
            Map.of(
                    "en-GB", new ResourceFormatter(Locale.UK),
                    "en-US", new ResourceFormatter(Locale.US),
                    "fr-FR", new ResourceFormatter(Locale.FRANCE),
                    "ru-RU", new ResourceFormatter(new Locale("ru", "RU")),
                    "zh-CN", new ResourceFormatter(Locale.CHINA)
            );

    private static final ProductFactory PRODUCT_FACTORY = new ProductFactory();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public static ProductFactory getInstance() {
        return PRODUCT_FACTORY;
    }

//    public ProductFactory(Locale locale) {
//        this(locale.toLanguageTag());
//    }

    private ProductFactory() {
//        changeLocale(languageTag);
        loadAllData();
    }

//    public void changeLocale(String languageTag) {
//        formatter = FORMATTERS.getOrDefault(languageTag, FORMATTERS.get("en-GB"));
//    }

    public static Set<String> getSupportedLocales() {
        return FORMATTERS.keySet();
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        Product product = null;
        try {
            writeLock.lock();
            product = new Food(id, name, price, rating, bestBefore);
            products.putIfAbsent(product, new ArrayList<>());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Error adding product");
        } finally {
            writeLock.unlock();
        }
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, LocalDate bestBefore) {
        Product product = null;
        try {
            writeLock.lock();
            product = new Food(id, name, price, bestBefore);
            products.putIfAbsent(product, new ArrayList<>());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Error adding product");
        } finally {
            writeLock.unlock();
        }
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        Product product = null;
        try {
            writeLock.lock();
            product = new Drink(id, name, price, rating);
            products.putIfAbsent(product, new ArrayList<>());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Error adding product");
        } finally {
            writeLock.unlock();
        }
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price) {
        Product product = null;
        try {
            writeLock.lock();
            product = new Drink(id, name, price);
            products.putIfAbsent(product, new ArrayList<>());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Error adding product");
        } finally {
            writeLock.unlock();
        }
        return product;
    }

    private Product reviewProduct(Product product, Rating rating, String comment) {
        List<Review> reviews = products.get(product);
        products.remove(product, reviews);
        reviews.add(new Review(rating, comment));
        product = product.applyRating(
                Rateable.convert(
                        (int) Math.round(
                                reviews.stream()
                                        .mapToInt(r -> r.getRating().ordinal())
                                        .average()
                                        .orElse(0)
                        )
                )
        );
        products.put(product, reviews);
        return product;
    }

    public Product reviewProduct(int id, Rating rating, String comment) {
        try {
            writeLock.lock();
            return reviewProduct(findProduct(id), rating, comment);
        } catch (ProductFactoryException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        } finally {
            writeLock.unlock();
        }
        return null;
    }

    private void printProductReport(Product product, String languageTag, String client) throws IOException {
        ResourceFormatter formatter = FORMATTERS.getOrDefault(languageTag, FORMATTERS.get("en-GB"));
        List<Review> reviews = products.get(product);
        Files.createDirectories(reportsFolder);
        Path productFile = reportsFolder.resolve(MessageFormat.format(config.getString("report.file"), product.getId(), client));
        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                        Files.newOutputStream(productFile, StandardOpenOption.CREATE)))) {
            out.append(formatter.formatProduct(product));
            out.append(System.lineSeparator());
            if (reviews.isEmpty()) {
                out.append(formatter.getText("no.review")).append(System.lineSeparator());
            } else {
                out.append(reviews.stream()
                        .sorted(Comparator.naturalOrder())
                        .map(r -> formatter.formatReview(r) + System.lineSeparator())
                        .collect(Collectors.joining()));
            }
        }
    }

    public void printProductReport(int id, String languageTag, String client) {
        try {
            readLock.lock();
            printProductReport(findProduct(id), languageTag, client);
        } catch (ProductFactoryException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error printing product report");
        } finally {
            readLock.unlock();
        }
    }

    public Product findProduct(int id) throws ProductFactoryException {
        try {
            readLock.lock();
            return products.keySet().stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new ProductFactoryException("Product with id: " + id + " not found."));

        } finally {
            readLock.unlock();
        }
    }

    public void printProducts(Comparator<Product> sorter, String languageTag) {
        try {
            readLock.lock();
            ResourceFormatter formatter = FORMATTERS.getOrDefault(languageTag, FORMATTERS.get("en-GB"));
            String txt = products.keySet().stream()
                    .sorted(sorter)
                    .map(p -> formatter.formatProduct(p) + "\r\n")
                    .collect(Collectors.joining());
            System.out.println(txt);
        } finally {
            readLock.unlock();
        }
    }

    public void printProducts(Predicate<Product> filter, Comparator<Product> sorter, String languageTag) {
        try {
            readLock.lock();
            ResourceFormatter formatter = FORMATTERS.getOrDefault(languageTag, FORMATTERS.get("en-GB"));
            String txt = products.keySet().stream()
                    .filter(filter)
                    .sorted(sorter)
                    .map(p -> formatter.formatProduct(p) + "\r\n")
                    .collect(Collectors.joining());
            System.out.println(txt);
        } finally {
            readLock.unlock();
        }
    }

    private List<Review> loadReviews(Product product) {
        List<Review> reviews = null;
        Path file = dataFolder.resolve(MessageFormat.format(config.getString("reviews.data.file"), product.getId()));
        if (Files.notExists(file)) {
            reviews = new ArrayList<>();
        } else {
            try {
                reviews = Files.lines(file, StandardCharsets.UTF_8)
                        .map(this::parseReview)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Error loading reviews");
            }
        }
        return reviews;
    }

    private Review parseReview(String text) {
        Review review = null;
        try {
            Object[] values = reviewFormat.parse(text);
            review = new Review(
                    Rateable.convert(Integer.parseInt((String) values[0])),
                    (String) values[1]
            );
        } catch (ParseException | NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Error parsing review: " + text);
        }
        return review;
    }

    private Product loadProduct(Path file) {
        Product product = null;
        try {
            product = parseProduct(Files.lines(file, StandardCharsets.UTF_8).findFirst().orElseThrow());
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error loading product");
        }
        return product;
    }

    private Product parseProduct(String text) {
        Product product = null;
        try {
            Object[] values = productFormat.parse(text);
            int id = Integer.parseInt((String) values[1]);
            String name = (String) values[2];
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble((String) values[3]));
            Rating rating = Rateable.convert(Integer.parseInt((String) values[4]));
            switch ((String) values[0]) {
                case "D" -> product = new Drink(id, name, price, rating);
                case "F" -> {
                    LocalDate date = LocalDate.parse((String) values[5]);
                    product = new Food(id, name, price, rating, date);
                }
            }
        } catch (ParseException | NumberFormatException | DateTimeException e) {
            LOGGER.log(Level.WARNING, "Error parsing product: " + text + " " + e.getMessage());
        }
        return product;
    }

    public void dumpData() {
        try {
            if (Files.notExists(tempFolder)) {
                Files.createDirectory(tempFolder);
            }
            Path tempFile = tempFolder.resolve(MessageFormat.format(config.getString("temp.file"), Instant.now().toString().replace(":", "-")));
            try (ObjectOutputStream out = new ObjectOutputStream(
                    Files.newOutputStream(tempFile))) {
                out.writeObject(products);
//                products = new HashMap<>();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error dumping data");
        }
    }

    @SuppressWarnings("unchecked")
    public void restoreData() {
        try {
            Path tempFile = Files.list(tempFolder)
                    .filter(path -> path.getFileName().toString().endsWith("tmp"))
                    .findFirst()
                    .orElseThrow();
            try (ObjectInputStream in = new ObjectInputStream(
                    Files.newInputStream(tempFile, StandardOpenOption.DELETE_ON_CLOSE))) {
                products = (HashMap) in.readObject();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error restoring data");
        }
    }

    private void loadAllData() {
        try {
            products = Files.list(dataFolder)
                    .filter(file -> file.getFileName().toString().startsWith("product"))
                    .map(this::loadProduct)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toMap(
                            product -> product,
                            this::loadReviews));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading data" + " " + e.getMessage());
        }
    }

    public Map<String, String> getDiscounts(String languageTag) {
        try {
            readLock.lock();
            ResourceFormatter formatter = FORMATTERS.getOrDefault(languageTag, FORMATTERS.get("en-GB"));
            return products.keySet().stream()
                    .collect(Collectors.groupingBy(
                            p -> p.getRating().getStars(),
                            Collectors.collectingAndThen(
                                    Collectors.summingDouble(
                                            p -> p.getDiscount().doubleValue()
                                    ),
                                    discount -> formatter.moneyFormat.format(discount)
                            )
                    ));
        } finally {
            readLock.unlock();
        }
    }

    private static class ResourceFormatter {
        private final ResourceBundle resources;
        private final DateTimeFormatter dateFormat;
        private final NumberFormat moneyFormat;

        public ResourceFormatter(Locale locale) {
            resources = ResourceBundle.getBundle("be/dog/d/steven/OracleProgrammingComplete/chapter15/data/resources", locale);
            dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
            moneyFormat = NumberFormat.getCurrencyInstance(locale);
        }

        private String formatProduct(Product product) {
            return MessageFormat.format(resources.getString("product"),
                    product.getName(),
                    moneyFormat.format(product.getPrice()),
                    product.getRating().getStars(),
                    (product instanceof Food) ? dateFormat.format(((Food) product).getBestBefore()) : "None");
        }

        private String formatReview(Review review) {
            return MessageFormat.format(resources.getString("review"),
                    review.getRating().getStars(),
                    review.getComment());
        }

        private String getText(String key) {
            return resources.getString(key);
        }
    }
}