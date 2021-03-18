package be.dog.d.steven.OracleProgrammingComplete.chapter10.app;

import be.dog.d.steven.OracleProgrammingComplete.chapter10.data.Product;
import be.dog.d.steven.OracleProgrammingComplete.chapter10.data.ProductFactory;
import be.dog.d.steven.OracleProgrammingComplete.chapter10.data.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

public class Shop {
    public static void main(String[] args) {
        var pf = new ProductFactory(Locale.getDefault());

        pf.createProduct(1, "Pizza", BigDecimal.valueOf(8.50), LocalDate.now().plusDays(2));
        pf.reviewProduct(1, Rating.FIVE_STAR, "Perfect");
        pf.reviewProduct(1, Rating.FOUR_STAR, "To lick your fingers");
        pf.reviewProduct(1, Rating.THREE_STAR, "It was oké");
        pf.reviewProduct(1, Rating.FOUR_STAR, "Tasted truly Italian");
        pf.printProductReport(1);

        pf.createProduct(2, "Coffee", BigDecimal.valueOf(2.10));
        pf.reviewProduct(2, Rating.ONE_STAR, "Tasted like water");
        pf.reviewProduct(2, Rating.TWO_STAR, "Is this coffee? I ordered tea");
        pf.reviewProduct(2, Rating.THREE_STAR, "It was oké");
        pf.printProductReport(2);

        pf.createProduct(3, "Tea", BigDecimal.valueOf(1.80));
        pf.reviewProduct(3, Rating.ONE_STAR, "Tasted like dish water");
        pf.reviewProduct(3, Rating.ONE_STAR, "Is this tea? I ordered coffee");
        pf.reviewProduct(3, Rating.THREE_STAR, "It was oké");
        pf.printProductReport(3);

        pf.createProduct(4, "Cake", BigDecimal.valueOf(3.50), LocalDate.now().plusDays(2));
        pf.reviewProduct(4, Rating.FIVE_STAR, "Perfect");
        pf.reviewProduct(4, Rating.FOUR_STAR, "To lick your fingers");
        pf.reviewProduct(4, Rating.THREE_STAR, "It was oké");
        pf.reviewProduct(4, Rating.FIVE_STAR, "Best cake ever");
        pf.printProductReport(4);

        Product p5 = pf.createProduct(5, "Cocktail", BigDecimal.valueOf(6.99));
        p5 = pf.reviewProduct(p5, Rating.FOUR_STAR, "Quite refreshing");
        p5 = pf.reviewProduct(p5, Rating.FOUR_STAR, "I'm drunk");
        p5 = pf.reviewProduct(p5, Rating.THREE_STAR, "It was oké");
        pf.printProductReport(p5);

        pf.createProduct(6, "Biscuit", BigDecimal.valueOf(1.50), LocalDate.now().plusDays(2));
        pf.printProductReport(6);


        // ANONYMOUS INNER CLASS
        System.out.println(new Product(Integer.MAX_VALUE, "Toy", BigDecimal.ZERO, Rating.FIVE_STAR) {
            @Override
            public Product applyRating(Rating rating) {
                return this;
            }
        });
    }
}
