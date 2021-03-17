package be.dog.d.steven.OracleProgrammingComplete.chapter6.app;

import be.dog.d.steven.OracleProgrammingComplete.chapter6.data.Drink;
import be.dog.d.steven.OracleProgrammingComplete.chapter6.data.Food;
import be.dog.d.steven.OracleProgrammingComplete.chapter6.data.Product;
import be.dog.d.steven.OracleProgrammingComplete.chapter6.data.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Shop {
    public static void main(String[] args) {
        Product p1 = new Drink(1, "Tea", BigDecimal.valueOf(2.10));
        Product p2 = new Drink(2, "Coffee", BigDecimal.valueOf(2.50), Rating.FOUR_STAR);
        Product p3 = new Food(3, "Cake", BigDecimal.valueOf(3.00), Rating.FIVE_STAR);
        Product p4 = new Food();
        Object p5 = new Food(4,"Beer sausage", BigDecimal.ONE, Rating.ONE_STAR);

        Product p6 = new Food(5,"Chocolat",BigDecimal.valueOf(2.99),Rating.FIVE_STAR);
        Product p7 = new Food(5,"Chocolat",BigDecimal.valueOf(2.99),Rating.FIVE_STAR);
        Product p8 = new Drink(5,"Chocolat",BigDecimal.valueOf(2.99),Rating.FIVE_STAR);

        Product aboutToSpoil = new Food(4,"Beer sausage", BigDecimal.ONE, Rating.ONE_STAR, LocalDate.now());

        System.out.println(p1.getId() + ": " + p1.getName() + ", " + p1.getPrice() + " (-" + p1.getDiscount() + ") " + p1.getRating().getStars());
        System.out.println(p2.getId() + ": " + p2.getName() + ", " + p2.getPrice() + " (-" + p2.getDiscount() + ") " + p2.getRating().getStars());
        System.out.println(p3.getId() + ": " + p3.getName() + ", " + p3.getPrice() + " (-" + p3.getDiscount() + ") " + p3.getRating().getStars());
        System.out.println(p4.getId() + ": " + p4.getName() + ", " + p4.getPrice() + " (-" + p4.getDiscount() + ") " + p4.getRating().getStars());

        p1 = p1.applyRating(Rating.THREE_STAR);
        System.out.println(p1.getId() + ": " + p1.getName() + ", " + p1.getPrice() + " (-" + p1.getDiscount() + ") " + p1.getRating().getStars());

        System.out.println();

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);
        System.out.println(p6);
        System.out.println(p7);

        System.out.println(p6.equals(p6));
        System.out.println(p6.equals(p7));
        System.out.println(p6.equals(p8));

        System.out.println(aboutToSpoil);
    }
}
