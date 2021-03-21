package be.dog.d.steven.OracleProgrammingComplete.chapter13.app;

import be.dog.d.steven.OracleProgrammingComplete.chapter13.data.ProductFactory;
import be.dog.d.steven.OracleProgrammingComplete.chapter13.data.Rating;

import java.math.BigDecimal;
import java.util.Comparator;

public class Shop {
    public static void main(String[] args) {
        var pf = new ProductFactory("en-GB");

        pf.createProduct(5,"Daiquiri", BigDecimal.valueOf(5.99));
        pf.reviewProduct(5, Rating.FIVE_STAR,"Good stuff");

        pf.dumpData();
        pf.restoreData();

        // EXISTING PRODUCTS
        pf.printProductReport(1);
        pf.printProductReport(2);
        pf.printProductReport(3);
        pf.printProductReport(4);

        // NEWLY CREATED PRODUCT
        pf.printProductReport(5);

        pf.printProducts(Comparator.comparingInt(p -> p.getRating().ordinal()));
    }
}
