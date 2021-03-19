package be.dog.d.steven.OracleProgrammingComplete.chapter8.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProductFactory {
    private final Locale locale;
    private final ResourceBundle resources;
    private final DateTimeFormatter dateFormat;
    private final NumberFormat moneyFormat;

    private Product product;
    private Review[] reviews = new Review[5];

    public ProductFactory(Locale locale) {
        this.locale = locale;
        resources = ResourceBundle.getBundle("be/dog/d/steven/OracleProgrammingComplete/chapter8/data/resources", locale);
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        product = new Food(id, name, price, rating, bestBefore);
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, LocalDate bestBefore) {
        product = new Food(id, name, price, bestBefore);
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        product = new Drink(id, name, price, rating);
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price) {
        product = new Drink(id, name, price);
        return product;
    }

    public Product reviewProduct(Product product, Rating rating, String comment) {
        if (reviews[reviews.length - 1] != null) {
            reviews = Arrays.copyOf(reviews, reviews.length * 2);
        }
        int sum = 0, i = 0;
        boolean reviewed = false;
        while (i < reviews.length && !reviewed) {
            if (reviews[i] == null) {
                reviews[i] = new Review(rating, comment);
                reviewed = true;
            }
            sum += reviews[i].getRating().ordinal();
            i++;
        }
        this.product = product.applyRating(Rateable.convert(Math.round((float) sum / i)));
        return this.product;
    }

    public void printProductReport() {
        StringBuilder txt = new StringBuilder();
        txt.append(MessageFormat.format(resources.getString("product"),
                product.getName(),
                moneyFormat.format(product.getPrice()),
                product.getRating().getStars(),
                (product instanceof Food) ? dateFormat.format(((Food) product).getBestBefore()) : ""));
        txt.append("\r\n");
        for (Review review : reviews) {
            if (review != null) {
                txt.append(MessageFormat.format(resources.getString("review"),
                        review.getRating().getStars(),
                        review.getComment()));
            } else {
                break;
            }
            txt.append("\r\n");
        }
        if (reviews[0] == null) {
            txt.append(resources.getString("no.review"));
            txt.append("\r\n");
        }
        System.out.println(txt);
    }
}