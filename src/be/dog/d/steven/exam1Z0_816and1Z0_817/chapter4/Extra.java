package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter4;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Extra {
    public static List<String> COLLECTION = List.of("Baboon", "Gorilla", "Tamarin", "Marmoset", "Capuchin", "Orangutan", "Macaque", "Gibbon", "Mandrill", "Chimpanzee", "Hamadryad");
    public static List<Product> PRODUCTS = Product.getListOfProducts();
    public static int[] INTS = new int[]{1,2,3,4,5,6,7,8,9};
    public static double[] DOUBLES = new double[]{.1,.2,.3,.4,.5,.6,.7,.8,.9};
    public static long[] LONGS = new long[]{1L,2L,3L,4L,5L,6L,7L,8L,9L};

    public static void main(String[] args) {
        Stream<String> monkeys = Stream.of("Baboon", "Gorilla", "Tamarin", "Marmoset", "Capuchin", "Orangutan", "Macaque", "Gibbon", "Mandrill", "Chimpanzee", "Hamadryad");
        IntStream ints = IntStream.of(1,2,3,4,5,6,7,8,9);
        DoubleStream doubles = DoubleStream.of(.1,.2,.3,.4,.5,.6,.7,.8,.9);
        LongStream longs = LongStream.of(1L,2L,3L,4L,5L,6L,7L,8L,9L);


    }


}

class Product {
    private final int id;
    private final String name;
    private final BigDecimal price;
    private final LocalDate expiryDate;

    private Product(int id, String name, BigDecimal price, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    public static List<Product> getListOfProducts(){
        return List.of(
                new Product(1,"Cookies", BigDecimal.valueOf(2.50), LocalDate.now().plusDays(7)),
                new Product(2,"Chips", BigDecimal.valueOf(2.50), LocalDate.now().plusDays(2)),
                new Product(3,"Chocolate", BigDecimal.valueOf(3.00), LocalDate.now().plusDays(3)),
                new Product(4,"Caramel", BigDecimal.valueOf(1.50), LocalDate.now().plusDays(8)),
                new Product(5,"Fruit", BigDecimal.valueOf(1.50), LocalDate.now().plusDays(5)),
                new Product(6,"Waffle", BigDecimal.valueOf(2.15), LocalDate.now().plusDays(1)),
                new Product(7,"Sweets", BigDecimal.valueOf(2.00), LocalDate.now().plusDays(6)),
                new Product(8,"Gum", BigDecimal.valueOf(1.00), LocalDate.now().plusDays(4)),
                new Product(9,"Donut", BigDecimal.valueOf(1.50), LocalDate.now().plusDays(9))
            );
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
