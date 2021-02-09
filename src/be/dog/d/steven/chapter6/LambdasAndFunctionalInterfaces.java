package be.dog.d.steven.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class LambdasAndFunctionalInterfaces {

    static List<String> listOfBunnies;

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

    static void populateListOfBunnies(){
        listOfBunnies = new ArrayList<>();
        listOfBunnies.add("Hopper");
        listOfBunnies.add("Happy");
        listOfBunnies.add("Long Ear");
        listOfBunnies.add("Floppy");
    }
}
