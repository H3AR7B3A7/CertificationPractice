package be.dog.d.steven.OracleProgrammingComplete.chapter5.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);
    private final int id;
    private final String name;
    private final BigDecimal price;
    private final Rating rating;

    public Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public Product(int id, String name, BigDecimal price) {
        this(id, name, price, Rating.NOT_RATED);
    }

    public Product() {
        this(0, "Product", BigDecimal.ZERO);
    }

    public int getId() {
        return id;
    }

//    public void setId(final int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

//    public void setName(final String name) {
//        this.name = name;
//    }

    public BigDecimal getPrice() {
        return price;
    }

//    public void setPrice(final BigDecimal price) {
//        this.price = price;
//    }

    public BigDecimal getDiscount() {
        return price.multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getDiscountRate() {
        return DISCOUNT_RATE;
    }

    public Rating getRating() {
        return rating;
    }

//    public void setRating(Rating rating) {
//        this.rating = rating;
//    }

    public Product applyRating(Rating newRating) {
        return new Product(id, name, price, newRating);
    }
}
