package be.dog.d.steven.OracleProgrammingComplete.chapter6.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Food extends Product {
    private final LocalDate bestBefore;

    public Food(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        super(id, name, price, rating);
        this.bestBefore = bestBefore;
    }

    public Food(int id, String name, BigDecimal price, LocalDate bestBefore) {
        super(id, name, price);
        this.bestBefore = bestBefore;
    }

    public Food(int id, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);
        this.bestBefore = LocalDate.now().plusDays(2);
    }

    public Food(int id, String name, BigDecimal price) {
        super(id, name, price);
        this.bestBefore = LocalDate.now().plusDays(2);
    }

    public Food() {
        this.bestBefore = LocalDate.now().plusDays(2);
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    @Override
    public Product applyRating(Rating newRating) {
        return new Food(getId(), getName(), getPrice(), newRating);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + bestBefore;
    }

    @Override
    public BigDecimal getDiscount() {
        return (bestBefore.isEqual(LocalDate.now())) ? super.getDiscount() : BigDecimal.ZERO;
    }
}