package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter4;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.*;

public class FunctionalProgramming {
    public static void main(String[] args) {

        // SUPPLIER

        Supplier<LocalDate> date1 = LocalDate::now;
        Supplier<LocalDate> date2 = () -> LocalDate.now();
        System.out.println(date1.get());

        Supplier<StringBuilder> builder1 = StringBuilder::new;
        Supplier<StringBuilder> builder2 = () -> new StringBuilder();
        System.out.println(builder1.get().append("abc"));

        // CONSUMER

        Consumer<String> print1 = System.out::println;
        Consumer<String> print2 = s -> System.out.println(s);
        print1.accept("test");

        var map = new HashMap<Integer, String>();
        BiConsumer<Integer, String> entry1 = map::put;
        BiConsumer<Integer, String> entry2 = (k, v) -> map.put(k, v);
        entry1.accept(1, "test");
        System.out.println(map);

        // PREDICATE

        Predicate<String> isEmpty1 = String::isEmpty;
        Predicate<String> isEmpty2 = s -> s.isEmpty();
        System.out.println(isEmpty1.test(""));

        BiPredicate<String, String> hasPrefix1 = String::startsWith;
        BiPredicate<String, String> hasPrefix2 = (s, p) -> s.startsWith(p);
        System.out.println(hasPrefix1.test("abc", "a"));

        // FUNCTION

        Function<String, Integer> length1 = String::length;
        Function<String, Integer> length2 = s -> s.length();
        System.out.println(length1.apply("abc"));

        BiFunction<String, String, String> concat1 = String::concat;
        BiFunction<String, String, String> concat2 = (s1, s2) -> s1.concat(s2);
        System.out.println(concat1.apply("abc", "def"));

        // UNARY- BINARY OPERATOR

        UnaryOperator<String> upper1 = String::toUpperCase;
        UnaryOperator<String> upper2 = s -> s.toUpperCase();
        System.out.println(upper1.apply("abc"));

        BinaryOperator<String> concat3 = String::concat;
        BinaryOperator<String> concat4 = (s1, s2) -> s1.concat(s2);
        System.out.println(concat3.apply("abc", "def"));

        // CONVENIENCE METHODS

        Predicate<String> egg = s -> s.contains("egg");
        Predicate<String> brown = s -> s.contains("brown");
        Predicate<String> brownEgg = egg.and(brown);
        System.out.println(brownEgg.test("brown egg"));

        Consumer<String> record1 = s -> System.out.println("1: " + s);
        Consumer<String> record2 = s -> System.out.println("2: " + s);
        Consumer<String> allRecords = record1.andThen(record2);
        allRecords.accept("Steven");

        Function<Integer, Integer> first = x -> x + 1;
        Function<Integer, Integer> next = x -> x * 2;
        Function<Integer, Integer> both = next.compose(first);
        System.out.println(both.apply(3));
    }
}
