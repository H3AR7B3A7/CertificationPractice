package be.dog.d.steven.OracleProgrammingComplete.chapter8.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public abstract class Product implements Rateable<Product> {
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

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDiscount() {
        return price.multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getDiscountRate() {
        return DISCOUNT_RATE;
    }

    @Override
    public Rating getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return id + ": " + name + ", " + price + " (-" + getDiscount() + ") " + rating.getStars();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
        if(!(o instanceof Product)) return false; // Check for product instead of food/drink (Chocolate drink = chocolate food)
        final Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
