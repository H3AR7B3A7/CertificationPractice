package be.dog.d.steven.OracleProgrammingComplete.chapter10.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class ProductFactory {
    private final Map<Product, List<Review>> products = new HashMap<>();
    private ResourceFormatter formatter;

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
        reviews.add(new Review(rating, comment));
        int sum = 0;
        for (Review review : reviews) {
            sum += review.getRating().ordinal();
        }
        product = product.applyRating(Rateable.convert(Math.round((float) sum / reviews.size())));
        products.put(product, reviews);
        return product;
    }

    public Product reviewProduct(int id, Rating rating, String comment) {
        return reviewProduct(findProduct(id), rating, comment);
    }


    public void printProductReport(Product product) {
        List<Review> reviews = products.get(product);
        StringBuilder txt = new StringBuilder();
        txt.append(formatter.formatProduct(product));
        txt.append("\r\n");
        Collections.sort(reviews);
        for (Review review : reviews) {
            txt.append(formatter.formatReview(review));
            txt.append("\r\n");
        }
        if (reviews.isEmpty()) {
            txt.append(formatter.getText("no.review"));
            txt.append("\r\n");
        }
        System.out.println(txt);
    }

    public void printProductReport(int id) {
        printProductReport(findProduct(id));
    }

    public Product findProduct(int id) {
        Product result = null;
        for (Product p : products.keySet()) {
            if (p.getId() == id) {
                result = p;
                break;
            }
        }
        return result;
    }

    private static class ResourceFormatter {
        private final ResourceBundle resources;
        private final DateTimeFormatter dateFormat;
        private final NumberFormat moneyFormat;

        public ResourceFormatter(Locale locale) {
            resources = ResourceBundle.getBundle("be/dog/d/steven/OracleProgrammingComplete/chapter10/data/resources", locale);
            dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
            moneyFormat = NumberFormat.getCurrencyInstance(locale);
        }

        private String formatProduct(Product product) {
            return MessageFormat.format(resources.getString("product"),
                    product.getName(),
                    moneyFormat.format(product.getPrice()),
                    product.getRating().getStars(),
                    (product instanceof Food) ? dateFormat.format(((Food) product).getBestBefore()) : "");
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