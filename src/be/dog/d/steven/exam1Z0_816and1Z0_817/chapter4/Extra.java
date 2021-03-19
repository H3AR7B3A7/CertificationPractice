package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter4;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Extra {
    public static final List<String> MONKEYS = List.of("Baboon", "Gorilla", "Tamarin", "Marmoset", "Capuchin", "Orangutan", "Macaque", "Gibbon", "Mandrill", "Chimpanzee", "Hamadryad");
    public static final int[] INTS = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static final double[] DOUBLES = new double[]{.1, .2, .3, .4, .5, .6, .7, .8, .9};
    public static final long[] LONGS = new long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L};

    public static void main(String[] args) {
        Stream<String> monkeys = Stream.of("Baboon", "Gorilla", "Tamarin", "Marmoset", "Capuchin", "Orangutan", "Macaque", "Gibbon", "Mandrill", "Chimpanzee", "Hamadryad");
        IntStream ints = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        DoubleStream doubles = DoubleStream.of(.1, .2, .3, .4, .5, .6, .7, .8, .9);
        LongStream longs = LongStream.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L);

        Product.getListOfProducts().stream()
                .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(2)) < 0)
                .peek(p -> System.out.println(p.getName()))
                .map(Product::getExpiryDate)
                .forEach(d -> System.out.println(d.plusDays(2)));
        System.out.println(" " + MONKEYS.stream()
                .mapToInt(String::length)
                .peek(System.out::print)
                .filter(i -> i > 6)
                .sum());

        Consumer<Product> expireProduct = product -> product.setExpiryDate(LocalDate.now());
        Consumer<Product> discountProdict = product -> product.setPrice(product.getPrice().subtract(BigDecimal.ONE));

        Product.getListOfProducts().stream()
                .peek(expireProduct.andThen(discountProdict))
                .filter(p -> p.getPrice().compareTo(BigDecimal.ONE) > 0)
                .forEach(System.out::println);

        System.out.println();

        Predicate<Product> priceFilter = p -> p.getPrice().compareTo(BigDecimal.valueOf(2.50)) < 0;
        Predicate<Product> dateFilter = p -> p.getExpiryDate().isBefore(LocalDate.now().plusDays(3));

        Product.getListOfProducts().stream()
                .filter(priceFilter.negate().or(dateFilter))
                .forEach(System.out::println);

        System.out.println();

        Product.getListOfProducts().stream().filter(Predicate.isEqual(new Product(1, "Cookies", BigDecimal.valueOf(2.50), LocalDate.now().plusDays(7))))
                .forEach(System.out::println);

        Function<Product, String> nameMapper = p -> p.getName();
        UnaryOperator<String> trimMapper = n -> n.trim();
        ToIntFunction<String> lengthMapper = n -> n.length();

        System.out.println(Product.getListOfProducts().stream()
                .map(nameMapper.andThen(trimMapper))
                .mapToInt(lengthMapper)
                .sum());

        MONKEYS.stream()
                .distinct()
                .sorted()
                .skip(7)
                .forEach(s -> System.out.println(s.toUpperCase()));

        MONKEYS.stream()
                .takeWhile(s -> !s.startsWith("O"))
                .dropWhile(s -> !s.startsWith("G"))
                .limit(4)
                .forEach(System.out::println);

        Stream.generate(() -> (int) (Math.random() * 10))
                .takeWhile(i -> i < 5)
                .peek(System.out::print)
                .map(i -> i * 2)
                .forEach(System.out::println);

        DoubleSummaryStatistics stats = Product.getListOfProducts()
                .stream().collect(Collectors.summarizingDouble(p -> p.getPrice().doubleValue()));
        System.out.println("max: " + stats.getMax());
        System.out.println("average: " + stats.getAverage());
        System.out.println("min: " + stats.getMin());

        String joined = Product.getListOfProducts().stream()
                .collect(Collectors.mapping(p -> p.getName(), Collectors.joining(", ")));
        System.out.println(joined);

        List<Product> cheap = Product.getListOfProducts().stream()
                .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(1.50)) < 0)
                .collect(Collectors.toList());
        System.out.println(cheap);

        NumberFormat myFormat = NumberFormat.getCurrencyInstance(Locale.UK);
        String price = Product.getListOfProducts().stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.averagingDouble(
                                p -> p.getPrice().doubleValue()),
                        n -> myFormat.format(n)
                        )
                );
        System.out.println("average: " + price);

        Map<BigDecimal, Set<Product>> priceCats = Product.getListOfProducts().stream()
                .collect(Collectors.groupingBy(p -> p.getPrice(),
                        Collectors.filtering(p -> p.getExpiryDate().isBefore(LocalDate.now().plusDays(4)),
                                Collectors.toSet())));
        System.out.println(priceCats);

        MONKEYS.stream()
                .parallel().sequential()
                .parallel().sequential()
                .parallel()
                .filter(s -> s.startsWith("O"))
                .forEach(System.out::println);

        Map<String, BigDecimal> priceList = Product.getListOfProducts().stream()
                .parallel()
                .collect(Collectors.toConcurrentMap(
                        p -> p.getName(),
                        p -> p.getPrice())
                );
        System.out.println(priceList);
    }
}


class Product {
    private final int id;
    private final String name;
    private BigDecimal price;
    private LocalDate expiryDate;

    Product(int id, String name, BigDecimal price, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    public static List<Product> getListOfProducts() {
        return List.of(
                new Product(1, "Cookies", BigDecimal.valueOf(2.50), LocalDate.now().plusDays(7)),
                new Product(2, "Chips", BigDecimal.valueOf(2.50), LocalDate.now().plusDays(2)),
                new Product(3, "Chocolate", BigDecimal.valueOf(3.00), LocalDate.now().plusDays(3)),
                new Product(4, "Caramel", BigDecimal.valueOf(1.50), LocalDate.now().plusDays(8)),
                new Product(5, "Fruit", BigDecimal.valueOf(1.50), LocalDate.now().plusDays(5)),
                new Product(6, "Waffle", BigDecimal.valueOf(2.15), LocalDate.now().plusDays(1)),
                new Product(7, "Sweets", BigDecimal.valueOf(2.00), LocalDate.now().plusDays(6)),
                new Product(8, "Gum", BigDecimal.valueOf(1.00), LocalDate.now().plusDays(4)),
                new Product(9, "Donut", BigDecimal.valueOf(1.50), LocalDate.now().plusDays(9))
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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
