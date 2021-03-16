package be.dog.d.steven.exam1Z0_815.chapter9;

import java.math.BigDecimal;

public final class Product {
    private static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);
    private static int numberOfProducts = 0;
    private final int id;
    private final String name;
    private final BigDecimal price;
    private final Rating rating;


    public Product(String name, BigDecimal price, Rating rating) {
        this.id = numberOfProducts++;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    private Product(String name, BigDecimal price){
        this(name, price, Rating.NOT_RATED);
    }

    public static Product createProduct(String name, BigDecimal price, Rating rating){
        return new Product(name, price, rating);
    }

    public static Product createProduct(String name, BigDecimal price){
        return new Product(name, price);
    }

    public static Product createProduct(Product product, Rating rating){
        return new Product(product.name, product.price, rating);
    }

    public Product changeRating(Rating rating){
        return createProduct(this, rating);
    }

    @Override
    public String toString() {
        return name + ", " + price + ", rating=" + rating.getStars();
    }

    // GETTERS

    public static BigDecimal getDiscountRate() {
        return DISCOUNT_RATE;
    }

    public static int getNumberOfProducts() {
        return numberOfProducts;
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

    public Rating getRating() {
        return rating;
    }
}

class Shop {
    public static void main(String[] args) {
        Product p1 = Product.createProduct("Water", BigDecimal.valueOf(1.20));
        Product p2 = Product.createProduct("ICED TEA",BigDecimal.valueOf(2.10), Rating.FIVE_STAR);
        Product p3 = Product.createProduct("Coke", BigDecimal.valueOf(1.80), Rating.ONE_STAR);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        p3 = p3.changeRating(Rating.THREE_STAR);

        System.out.println(p3);
    }
}
