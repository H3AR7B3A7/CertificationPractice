package be.dog.d.steven.OracleProgrammingComplete.chapter6.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductFactory {
    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        return new Food(id, name, price, rating, bestBefore);
    }

    public Product createProduct(int id, String name, BigDecimal price, LocalDate bestBefore) {
        return new Food(id, name, price, bestBefore);
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        return new Drink(id, name, price, rating);
    }

    public Product createProduct(int id, String name, BigDecimal price) {
        return new Drink(id, name, price);
    }
}
