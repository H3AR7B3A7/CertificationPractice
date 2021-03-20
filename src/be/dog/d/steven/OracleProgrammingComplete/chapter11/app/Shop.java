package be.dog.d.steven.OracleProgrammingComplete.chapter11.app;

import be.dog.d.steven.OracleProgrammingComplete.chapter11.data.Product;
import be.dog.d.steven.OracleProgrammingComplete.chapter11.data.ProductFactory;
import be.dog.d.steven.OracleProgrammingComplete.chapter11.data.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Predicate;

public class Shop {
    public static void main(String[] args) {
        System.out.println(ProductFactory.getSupportedLocales());
        var pf = new ProductFactory("fr-FR");

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
//        pf.printProductReport(2);

        pf.changeLocale("ru-RU");

        pf.createProduct(3, "Tea", BigDecimal.valueOf(1.80));
        pf.reviewProduct(3, Rating.ONE_STAR, "Tasted like dish water");
        pf.reviewProduct(3, Rating.ONE_STAR, "Is this tea? I ordered coffee");
        pf.reviewProduct(3, Rating.THREE_STAR, "It was oké");
//        pf.printProductReport(3);

        pf.createProduct(4, "Cake", BigDecimal.valueOf(3.50), LocalDate.now());
        pf.reviewProduct(4, Rating.FIVE_STAR, "Perfect");
        pf.reviewProduct(4, Rating.FOUR_STAR, "To lick your fingers");
        pf.reviewProduct(4, Rating.THREE_STAR, "It was oké");
        pf.reviewProduct(4, Rating.FIVE_STAR, "Best cake ever");
//        pf.printProductReport(4);

        pf.changeLocale("zh-CN");

        Product p5 = pf.createProduct(5, "Cocktail", BigDecimal.valueOf(6.99));
        p5 = pf.reviewProduct(p5, Rating.FOUR_STAR, "Quite refreshing");
        p5 = pf.reviewProduct(p5, Rating.FOUR_STAR, "I'm drunk");
        p5 = pf.reviewProduct(p5, Rating.THREE_STAR, "It was oké");
//        pf.printProductReport(p5);

        pf.createProduct(6, "Biscuit", BigDecimal.valueOf(1.50), LocalDate.now());
//        pf.printProductReport(6);

        pf.changeLocale("en-GB");
        pf.printProducts((p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal());
        pf.printProducts((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
        pf.printProducts(Comparator.comparing(Product::getPrice));

        Comparator<Product> ratingSorter = (p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal();
        Comparator<Product> priceSorter = Comparator.comparing(Product::getPrice);

        pf.printProducts(ratingSorter);
        pf.printProducts(priceSorter.reversed());
        pf.printProducts(priceSorter.thenComparing(ratingSorter));

        Predicate<Product> cheap = p -> p.getPrice().compareTo(BigDecimal.valueOf(2)) < 0;

        System.out.println("Filtered (price < 2):");
        pf.printProducts(cheap, ratingSorter);

        pf.getDiscounts().forEach((rating, discount) -> System.out.println(rating + "\t" + discount));

        // ANONYMOUS INNER CLASS
        System.out.println(new Product(Integer.MAX_VALUE, "Toy", BigDecimal.ZERO, Rating.FIVE_STAR) {
            @Override
            public Product applyRating(Rating rating) {
                return this;
            }
        });
    }
}
