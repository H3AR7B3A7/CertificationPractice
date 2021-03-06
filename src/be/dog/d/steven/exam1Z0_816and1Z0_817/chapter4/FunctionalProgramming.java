package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter4;

import java.time.LocalDate;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

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

// OPTIONAL

class OptionalExample {
    public static Optional<Double> average1(int... scores) {
        if (scores.length == 0) {
            return Optional.empty();
        }
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return Optional.of((double) sum / scores.length);
    }

    public static Double average2(int... scores) {
        if (scores.length == 0) {
            return null;
        }
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.length;
    }

    public static void main(String[] args) {
        Optional<Double> average1 = OptionalExample.average1();
        Optional<Double> average2 = OptionalExample.average1(6, 14);
        if (average1.isPresent()) {  // Check
            System.out.println(average1.get());
        }
        average2.ifPresent(System.out::println);  // Functional expression

        Optional<Double> average3 = Optional.ofNullable(OptionalExample.average2());
        Optional<Double> average4 = Optional.ofNullable(OptionalExample.average2(9, 18));

        average4.ifPresent(System.out::println);
        System.out.println(average3.orElse(Double.NaN));
        System.out.println(average3.orElseGet(() -> Math.random() * 100));
        System.out.println(average3.orElseGet(Math::random) * 100);
        System.out.println(average3.orElseGet(() -> new Random().nextDouble() * 100));
        System.out.println(average3.orElseThrow());
        System.out.println(average3.orElseThrow(() -> new IllegalStateException()));
        System.out.println(average3.orElseThrow(IllegalStateException::new));

    }
}

// STREAMS

class StreamExample {
    public static void main(String[] args) {

        // SOURCES

        Stream<String> s1 = Stream.empty();
        Stream<Integer> s2 = Stream.of(1, 2, 3);
        Stream<Double> s3 = Stream.generate(Math::random);
        Stream<Integer> s4 = Stream.iterate(1, n -> n + 2);
        Stream<Integer> s5 = Stream.iterate(1, n -> n < 100, n -> n + 2);

        List<Integer> list = List.of(1, 2, 3);
        Stream<Integer> s6 = list.stream();
        Stream<Integer> s7 = list.parallelStream();

        // TERMINAL OPERATIONS
        Long count = s5.count();
        System.out.println(count);

        Stream<String> s8 = Stream.of("monkey", "ape", "gorilla", "bonobo");
        Optional<String> min = s8.min((o1, o2) -> o1.length() - o2.length());
        min.ifPresent(System.out::println);
        Stream<String> s9 = Stream.of("monkey", "ape", "gorilla", "bonobo");
        Optional<String> max = s9.max((o1, o2) -> o1.length() - o2.length());
        max.ifPresent(System.out::println);

        Stream<String> s10 = Stream.of("monkey", "ape", "gorilla", "bonobo");
        s10.findAny().ifPresent(System.out::println);
        s3.findAny().ifPresent(System.out::println);
        Stream<String> s11 = Stream.of("monkey", "ape", "gorilla", "bonobo");
        s11.findFirst().ifPresent(System.out::println);

        List<String> strings = List.of("monkey", "ape", "gorilla", "bonobo");
        Predicate<String> firstCharIsLetter = x -> Character.isLetter(x.charAt(0));
        System.out.println(strings.stream().anyMatch(firstCharIsLetter));
        System.out.println(strings.stream().allMatch(firstCharIsLetter));
        System.out.println(strings.stream().noneMatch(firstCharIsLetter));

        s2.forEach(System.out::println);

        System.out.println(strings.stream().reduce("", (s, c) -> s + c));
        System.out.println(strings.stream().reduce("", String::concat));
        Stream<Integer> s12 = Stream.of(1, 2, 3);
        Optional<Integer> result = s12.reduce((a, b) -> a * b);
        result.ifPresent(System.out::println);
        int length = strings.stream().reduce(0, (i, s) -> i + s.length(), Integer::sum);
        System.out.println(length);

        StringBuilder sb = strings.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        System.out.println(sb);
        TreeSet<String> treeSet1 = strings.stream().collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        System.out.println(treeSet1);
        TreeSet<String> treeSet2 = strings.stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println(treeSet2);
        Set<String> mostLikelyHashSet = strings.stream().collect(Collectors.toSet());
        System.out.println(mostLikelyHashSet);

        // INTERMEDIATE OPERATIONS

        List<String> animals = List.of("bunny", "monkey", "ape", "bird", "gorilla", "bonobo", "giraffe", "duck", "duck", "duck");
        animals.stream().filter(s -> s.startsWith("b")).forEach(s -> System.out.print(s + " "));
        System.out.println();

        animals.stream().distinct().forEach(s -> System.out.print(s + " "));
        System.out.println();

        animals.stream().skip(5).limit(3).forEach(s -> System.out.print(s + " "));
        System.out.println();

        animals.stream().map(String::length).forEach(s -> System.out.print(s + " "));
        System.out.println();

        List<String> zero = List.of();
        List<String> one = List.of("worm");
        List<String> two = List.of("snail", "beetle");
        List<List<String>> lists = List.of(zero, one, two);
        lists.stream().flatMap(l -> l.stream()).forEach(s -> System.out.print(s + " "));
        lists.stream().flatMap(Collection::stream).forEach(s -> System.out.print(s + " "));

        animals.stream().sorted(Comparator.reverseOrder()).forEach(s -> System.out.print(s + " "));
        System.out.println();

        Long numberOfWords = animals.stream().filter(s -> s.contains("b")).peek(System.out::println).count();
        System.out.println(numberOfWords);

        System.out.println(
                Stream.iterate(1, x -> x++) // Post-increment
                        .limit(5)
                        .map(x -> "" + x)
                        .collect(Collectors.joining())
        ); // 11111

        System.out.println(
                Stream.iterate(1, x -> ++x) // Pre-increment
                        .limit(5)
                        .map(x -> "" + x)
                        .collect(Collectors.joining())
        ); // 12345
    }
}

// PRIMITIVE STREAMS

class PrimitiveStreamExample {
    public static void main(String[] args) {
        IntStream intStream = IntStream.of(1, 2, 3);
        OptionalDouble avg = intStream.average();
        avg.ifPresent(System.out::println);
        System.out.println(avg.getAsDouble());

        LongStream longStream = LongStream.empty();

        DoubleStream doubleStream1 = DoubleStream.generate(Math::random);
        DoubleStream doubleStream2 = DoubleStream.iterate(1, x -> x / 2);
        DoubleStream doubleStream3 = new Random().doubles();

        doubleStream1.limit(3).forEach(System.out::println);
        doubleStream2.limit(3).forEach(System.out::println);
        doubleStream3.limit(3).forEach(System.out::println);

        IntStream range = IntStream.range(1, 4);
        IntStream rangeClosed = IntStream.rangeClosed(1, 4);
        range.forEach(s -> System.out.print(s + " "));
        System.out.println();
        rangeClosed.forEach(s -> System.out.print(s + " "));
        System.out.println();

        Stream<String> animals = Stream.of("bunny", "monkey", "ape", "bird", "gorilla", "bonobo", "giraffe", "duck", "duck", "duck");
        IntStream lengths = animals.mapToInt(String::length);
        lengths.forEach(s -> System.out.print(s + " "));
        System.out.println();

        List<Integer> intList = List.of(1, 2, 3);
        IntStream ints1 = intList.stream().flatMapToInt(IntStream::of);
        ints1.forEach(s -> System.out.print(s + " "));
        System.out.println();

        IntStream ints2 = intList.stream().flatMapToInt(IntStream::of);
        Stream<Integer> objStream1 = ints2.mapToObj(i -> i);
        IntStream ints3 = intList.stream().flatMapToInt(IntStream::of);
        Stream<Integer> objStream2 = ints2.boxed();
    }
}

// SUMMARY STATISTICS

class SummaryStatisticsExample {
    private static int max(IntStream ints) {
        OptionalInt optional = ints.max();
        return optional.orElseThrow(RuntimeException::new);
    }

    private static int range(IntStream ints) {
        IntSummaryStatistics stats = ints.summaryStatistics();
        if (stats.getCount() == 0) {
            throw new RuntimeException();
        }
        return stats.getMax() - stats.getMin();
    }

    private static void printStats(IntStream ints) {
        IntSummaryStatistics stats = ints.summaryStatistics();
        if (stats.getCount() == 0) {
            throw new RuntimeException();
        }
        System.out.println("max = " + stats.getMax());
        System.out.println("min = " + stats.getMin());
        System.out.println("avg = " + stats.getAverage());
        System.out.println("count = " + stats.getCount());
        System.out.println("sum = " + stats.getSum());
    }

    public static void main(String[] args) {
        IntStream ints1 = IntStream.rangeClosed(5, 10);
        System.out.println(range(ints1));

        IntStream ints2 = IntStream.rangeClosed(5, 10);
        printStats(ints2);


    }
}

// COLLECTING

class CollectorExamples {
    public static void main(String[] args) {
        var stream = Stream.of("lions", "tigers", "bear");
        String result = stream.collect(Collectors.joining(", "));
        System.out.println(result);

        List<String> list = List.of("lions", "tigers", "bears");
        Double averageLength = list.stream().collect(Collectors.averagingInt(String::length));
        System.out.println(averageLength);

        TreeSet<String> treeSet = list.stream()
                .filter(s -> s.startsWith("t"))
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(treeSet);

        Map<String, Integer> map1 = list.stream().collect(Collectors.toMap(s -> s, String::length));
        System.out.println(map1);

        Map<Integer, String> map2 = list.stream().collect(Collectors.toMap(String::length, s -> s, (s1, s2) -> s1 + "," + s2));
        System.out.println(map2); // 3d param handles collisions

        Map<Integer, List<String>> map3 = list.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(map3);

        Map<Integer, Set<String>> map4 = list.stream().collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        System.out.println(map4);

        TreeMap<Integer, Set<String>> map5 = list.stream()
                .collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet()));
        System.out.println(map5);

        Map<Boolean, List<String>> map6 = list.stream().collect(Collectors.partitioningBy(s -> s.length() < 6));
        System.out.println(map6);

        Map<Boolean, Set<String>> map7 = list.stream().collect(Collectors.partitioningBy(s -> s.length() < 7, Collectors.toSet()));
        System.out.println(map7);

        Map<Integer, Long> map8 = list.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(map8);

        Map<Integer, Optional<Character>> map9 = list.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.mapping(s -> s.charAt(0), Collectors.minBy((a, b) -> a - b))));
        System.out.println(map9); // 3d param handles collisions, picking smallest char if present
    }
}