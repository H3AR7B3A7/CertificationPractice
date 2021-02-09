package be.dog.d.steven.chapter6;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdasAndFunctionalInterfaces {

    static List<String> listOfBunnies;

    static void populateListOfBunnies(){
        listOfBunnies = new ArrayList<>();
        listOfBunnies.add("Hopper");
        listOfBunnies.add("Happy");
        listOfBunnies.add("Long Ear");
        listOfBunnies.add("Floppy");
    }

    public static void main(String[] args) {

        // Predicate
        populateListOfBunnies();
        listOfBunnies.removeIf(s -> s.charAt(0) != 'H');
        System.out.println(listOfBunnies);

        // Consumer
        populateListOfBunnies();
        listOfBunnies.forEach(s -> System.out.println(s));

        // Supplier
        Supplier<Integer> numberGenerator = () -> new Random().nextInt(100);
        System.out.println(numberGenerator.get());

        // Comparator
        populateListOfBunnies();
        listOfBunnies.sort((o1, o2) -> o1.compareTo(o2));
        System.out.println(listOfBunnies);

    }
}

// Predicate
class Panda {
    int age;

    public Panda(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Panda panda = new Panda(1);
        check(panda, p -> p.age < 3);
    }
    private static void check(Panda p, Predicate<Panda> predicate) {
        String result = predicate.test(p) ? "match" : "not match";
        System.out.println(result);
    }
}
