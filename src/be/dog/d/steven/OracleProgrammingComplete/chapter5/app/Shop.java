package be.dog.d.steven.OracleProgrammingComplete.chapter5.app;

import be.dog.d.steven.OracleProgrammingComplete.chapter5.data.Product;
import be.dog.d.steven.OracleProgrammingComplete.chapter5.data.Rating;

import java.math.BigDecimal;

public class Shop {
    public static void main(String[] args) {
        Product p1 = new Product(1, "Tea", BigDecimal.valueOf(2.10));
        Product p2 = new Product(2, "Coffee", BigDecimal.valueOf(2.50), Rating.FOUR_STAR);
        Product p3 = new Product(3, "Cake", BigDecimal.valueOf(3.00), Rating.FIVE_STAR);
        Product p4 = new Product();

        System.out.println(p1.getId() + ": " + p1.getName() + ", " + p1.getPrice() + " (-" + p1.getDiscount() + ") " + p1.getRating().getStars());
        System.out.println(p2.getId() + ": " + p2.getName() + ", " + p2.getPrice() + " (-" + p2.getDiscount() + ") " + p2.getRating().getStars());
        System.out.println(p3.getId() + ": " + p3.getName() + ", " + p3.getPrice() + " (-" + p3.getDiscount() + ") " + p3.getRating().getStars());
        System.out.println(p4.getId() + ": " + p4.getName() + ", " + p4.getPrice() + " (-" + p4.getDiscount() + ") " + p4.getRating().getStars());

        p1 = p1.applyRating(Rating.THREE_STAR);
        System.out.println(p1.getId() + ": " + p1.getName() + ", " + p1.getPrice() + " (-" + p1.getDiscount() + ") " + p1.getRating().getStars());


    }
}
