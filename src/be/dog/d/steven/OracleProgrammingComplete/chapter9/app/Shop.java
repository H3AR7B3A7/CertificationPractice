package be.dog.d.steven.OracleProgrammingComplete.chapter9.app;

import be.dog.d.steven.OracleProgrammingComplete.chapter9.data.Product;
import be.dog.d.steven.OracleProgrammingComplete.chapter9.data.ProductFactory;
import be.dog.d.steven.OracleProgrammingComplete.chapter9.data.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

public class Shop {
    public static void main(String[] args) {
        var pf = new ProductFactory(Locale.getDefault());

        Product p1 = pf.createProduct(1,"Pizza",BigDecimal.valueOf(8.50), LocalDate.now().plusDays(2));
        p1 = pf.reviewProduct(p1, Rating.FIVE_STAR, "Perfect");
        p1 = pf.reviewProduct(p1, Rating.FOUR_STAR, "To lick your fingers");
        p1 = pf.reviewProduct(p1, Rating.THREE_STAR, "It was oké");
        p1 = pf.reviewProduct(p1, Rating.FOUR_STAR, "Tasted truly Italian");
        pf.printProductReport(p1);

        Product p2 = pf.createProduct(2, "Coffee", BigDecimal.valueOf(2.10));
        p2 = pf.reviewProduct(p2, Rating.ONE_STAR, "Tasted like water");
        p2 = pf.reviewProduct(p2, Rating.TWO_STAR, "Is this coffee? I ordered tea");
        p2 = pf.reviewProduct(p2, Rating.THREE_STAR, "It was oké");
        pf.printProductReport(p2);

        Product p3 = pf.createProduct(3, "Tea", BigDecimal.valueOf(1.80));
        p3 = pf.reviewProduct(p3, Rating.ONE_STAR, "Tasted like dish water");
        p3 = pf.reviewProduct(p3, Rating.ONE_STAR, "Is this tea? I ordered coffee");
        p3 = pf.reviewProduct(p3, Rating.THREE_STAR, "It was oké");
        pf.printProductReport(p3);

        Product p4 = pf.createProduct(4,"Cake",BigDecimal.valueOf(3.50), LocalDate.now().plusDays(2));
        p4 = pf.reviewProduct(p4, Rating.FIVE_STAR, "Perfect");
        p4 = pf.reviewProduct(p4, Rating.FOUR_STAR, "To lick your fingers");
        p4 = pf.reviewProduct(p4, Rating.THREE_STAR, "It was oké");
        p4 = pf.reviewProduct(p4, Rating.FIVE_STAR, "Best cake ever");
        pf.printProductReport(p4);

        Product p5 = pf.createProduct(5, "Cocktail", BigDecimal.valueOf(6.99));
        p5 = pf.reviewProduct(p5, Rating.FOUR_STAR, "Quite refreshing");
        p5 = pf.reviewProduct(p5, Rating.FOUR_STAR, "I'm drunk");
        p5 = pf.reviewProduct(p5, Rating.THREE_STAR, "It was oké");
        pf.printProductReport(p5);
    }
}
