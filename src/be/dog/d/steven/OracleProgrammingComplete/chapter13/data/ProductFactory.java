package be.dog.d.steven.OracleProgrammingComplete.chapter13.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductFactory {
    private final Map<Product, List<Review>> products = new HashMap<>();
    private ResourceFormatter formatter;

    private final ResourceBundle config = ResourceBundle.getBundle("be/dog/d/steven/OracleProgrammingComplete/chapter13/data/config");
    private final MessageFormat reviewFormat = new MessageFormat(config.getString("review.data.format"));
    private final MessageFormat productFormat = new MessageFormat(config.getString("product.data.format"));

    private static final Logger LOGGER = Logger.getLogger(ProductFactory.class.getName());

    private static final Map<String, ResourceFormatter> FORMATTERS =
            Map.of(
                    "en-GB", new ResourceFormatter(Locale.UK),
                    "en-US", new ResourceFormatter(Locale.US),
                    "fr-FR", new ResourceFormatter(Locale.FRANCE),
                    "ru-RU", new ResourceFormatter(new Locale("ru", "RU")),
                    "zh-CN", new ResourceFormatter(Locale.CHINA)
            );

    public ProductFactory(Locale locale) {
        this(locale.toLanguageTag());
    }

    public ProductFactory(String languageTag) {
        changeLocale(languageTag);
    }

    public void changeLocale(String languageTag) {
        formatter = FORMATTERS.getOrDefault(languageTag, FORMATTERS.get("en-GB"));
    }

    public static Set<String> getSupportedLocales() {
        return FORMATTERS.keySet();
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        Product product = new Food(id, name, price, rating, bestBefore);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, LocalDate bestBefore) {
        Product product = new Food(id, name, price, bestBefore);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        Product product = new Drink(id, name, price, rating);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price) {
        Product product = new Drink(id, name, price);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product reviewProduct(Product product, Rating rating, String comment) {
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
            return reviewProduct(findProduct(id), rating, comment);
        } catch (ProductFactoryException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
        return null;
    }

    public void printProductReport(Product product) {
        List<Review> reviews = products.get(product);
        StringBuilder txt = new StringBuilder();
        txt.append(formatter.formatProduct(product));
        txt.append("\r\n");
        if (reviews.isEmpty()) {
            txt.append(formatter.getText("no.review")).append("\r\n");
        } else {
            txt.append(reviews.stream()
                    .sorted(Comparator.naturalOrder())
                    .map(r -> formatter.formatReview(r) + "\r\n")
                    .collect(Collectors.joining()));
        }
        System.out.println(txt);
    }

    public void printProductReport(int id) {
        try {
            printProductReport(findProduct(id));
        } catch (ProductFactoryException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }

    public Product findProduct(int id) throws ProductFactoryException {
        return products.keySet().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductFactoryException("Product with id: " + id + " not found."));
    }

    public void printProducts(Comparator<Product> sorter) {
        String txt = products.keySet().stream()
                .sorted(sorter)
                .map(p -> formatter.formatProduct(p) + "\r\n")
                .collect(Collectors.joining());
        System.out.println(txt);
    }

    public void printProducts(Predicate<Product> filter, Comparator<Product> sorter) {
        String txt = products.keySet().stream()
                .filter(filter)
                .sorted(sorter)
                .map(p -> formatter.formatProduct(p) + "\r\n")
                .collect(Collectors.joining());
        System.out.println(txt);
    }

    public void parseReview(String text) {
        try {
            Object[] values = reviewFormat.parse(text);
            reviewProduct(
                    Integer.parseInt((String) values[0]),
                    Rateable.convert(Integer.parseInt((String) values[1])),
                    (String) values[2]
            );
        } catch (ParseException | NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Error parsing review: " + text);
        }
    }

    public void parseProduct(String text) {
        try {
            Object[] values = productFormat.parse(text);
            int id = Integer.parseInt((String) values[1]);
            String name = (String) values[2];
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble((String) values[3]));
            switch ((String) values[0]) {
                case "D" -> createProduct(id, name, price);
                case "F" -> {
                    LocalDate date = LocalDate.parse((String) values[4]);
                    createProduct(id, name, price, date);
                }
            }
        } catch (ParseException | NumberFormatException | DateTimeException e) {
            LOGGER.log(Level.WARNING, "Error parsing review: " + text + " " + e.getMessage());
        }
    }

    public Map<String, String> getDiscounts() {
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
    }

    private static class ResourceFormatter {
        private final ResourceBundle resources;
        private final DateTimeFormatter dateFormat;
        private final NumberFormat moneyFormat;

        public ResourceFormatter(Locale locale) {
            resources = ResourceBundle.getBundle("be/dog/d/steven/OracleProgrammingComplete/chapter13/data/resources", locale);
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