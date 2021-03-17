package be.dog.d.steven.OracleProgrammingComplete.chapter7.app;

import be.dog.d.steven.OracleProgrammingComplete.chapter7.data.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

public class Shop {
    public static void main(String[] args) {
        var productFactory = new ProductFactory(Locale.getDefault());

        Product p1 = productFactory.createProduct(1, "Tea", BigDecimal.valueOf(2.10));
        Product p2 = productFactory.createProduct(2, "Coffee", BigDecimal.valueOf(2.50), Rating.FOUR_STAR);
        Product p3 = productFactory.createProduct(3, "Cake", BigDecimal.valueOf(3.00), Rating.FIVE_STAR, LocalDate.now().plusDays(2));
        Object p5 = productFactory.createProduct(4,"Beer Sausage", BigDecimal.ONE, Rating.ONE_STAR, LocalDate.now().plusDays(2));

        Product p6 = productFactory.createProduct(5,"Chocolat",BigDecimal.valueOf(2.99), Rating.FIVE_STAR);
        Product p7 = productFactory.createProduct(5,"Chocolat",BigDecimal.valueOf(2.99), Rating.FIVE_STAR);
        Product p8 = productFactory.createProduct(5,"Chocolat",BigDecimal.valueOf(2.99), Rating.FIVE_STAR, LocalDate.now().plusDays(2));

        Product aboutToSpoil = productFactory.createProduct(4,"Beer sausage", BigDecimal.ONE, Rating.ONE_STAR, LocalDate.now());

        System.out.println(p1.getId() + ": " + p1.getName() + ", " + p1.getPrice() + " (-" + p1.getDiscount() + ") " + p1.getRating().getStars());
        System.out.println(p2.getId() + ": " + p2.getName() + ", " + p2.getPrice() + " (-" + p2.getDiscount() + ") " + p2.getRating().getStars());
        System.out.println(p3.getId() + ": " + p3.getName() + ", " + p3.getPrice() + " (-" + p3.getDiscount() + ") " + p3.getRating().getStars());

        p1 = p1.applyRating(Rating.THREE_STAR);
        p3 = p3.applyRating(Rating.THREE_STAR);
        System.out.println(p1.getId() + ": " + p1.getName() + ", " + p1.getPrice() + " (-" + p1.getDiscount() + ") " + p1.getRating().getStars());

        System.out.println();

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p5);
        System.out.println(p6);
        System.out.println(p7);
        System.out.println(p8);

        System.out.println(p6.equals(p6)); // True even without equals implemented
        System.out.println(p6.equals(p7)); // True with equals implemented and checking for food/drink class
        System.out.println(p6.equals(p8)); // True with class check just for product (not food / drink)

        System.out.println(p3);
        System.out.println(aboutToSpoil);

        if(p3 instanceof Food) {
            LocalDate bestBefore = ((Food) p3).getBestBefore();
            System.out.println(bestBefore);
        }

        Rateable<Product> p9 = productFactory.createProduct(6,"Potato Chips", BigDecimal.valueOf(1.60), Rating.THREE_STAR, LocalDate.now().plusDays(2));
        System.out.println(p9);

        productFactory.printProductReport();
    }
}
