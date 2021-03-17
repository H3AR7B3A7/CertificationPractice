package be.dog.d.steven.OracleProgrammingComplete.chapter6.data;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Drink extends Product{
    public Drink(int id, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);
    }

    public Drink(int id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public Product applyRating(Rating newRating) {
        return new Drink(getId(), getName(), getPrice(), newRating);
    }

    @Override
    public BigDecimal getDiscount() {

        if(LocalTime.now())
        return super.getDiscount();
    }
}
