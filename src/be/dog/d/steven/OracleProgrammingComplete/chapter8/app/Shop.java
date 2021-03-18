package be.dog.d.steven.OracleProgrammingComplete.chapter8.app;

import be.dog.d.steven.OracleProgrammingComplete.chapter8.data.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

public class Shop {
    public static void main(String[] args) {
        var pf1 = new ProductFactory(Locale.getDefault());
        Product p1 = pf1.createProduct(1,"Pizza",BigDecimal.valueOf(8.50), LocalDate.now().plusDays(2));
        p1 = pf1.reviewProduct(p1, Rating.FIVE_STAR, "Perfect");
        p1 = pf1.reviewProduct(p1, Rating.FOUR_STAR, "To lick your fingers");
        p1 = pf1.reviewProduct(p1, Rating.THREE_STAR, "It was oké");
        p1 = pf1.reviewProduct(p1, Rating.FOUR_STAR, "Tasted truly Italian");
        pf1.printProductReport();

        var pf2 = new ProductFactory(Locale.getDefault());
        Product p2 = pf2.createProduct(2, "Coffee", BigDecimal.valueOf(2.10));
        p2 = pf2.reviewProduct(p2, Rating.ONE_STAR, "Tasted like water");
        p2 = pf2.reviewProduct(p2, Rating.TWO_STAR, "Is this coffee? I ordered tea");
        p2 = pf2.reviewProduct(p2, Rating.THREE_STAR, "It was oké");
        pf2.printProductReport();
    }
}
