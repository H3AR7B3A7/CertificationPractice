package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter3;

// FUNCTIONAL INTERFACE

import java.util.*;
import java.util.function.*;

@FunctionalInterface
interface LearnToSpeak {
    void speak(String sound);
}

// METHOD THAT TAKES A FUNCTIONAL INTERFACE, LAMBDA OR METHOD REFERENCE

class SpeechTherapist {
    public static void teach1(String word, LearnToSpeak therapy) {
        therapy.speak(word);
    }
    public static void teach2(String word, Consumer<String> consumer) {
        consumer.accept(word);
    }
}

public class GenericsAndCollections {
    public static void main(String[] args) {

        // FUNCTIONAL INTERFACE

        SpeechTherapist.teach1("asparagus", new LearnToSpeak() {
            @Override
            public void speak(String sound) {
                System.out.println(sound);
            }
        });

        SpeechTherapist.teach2("ketchup", new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        // LAMBDA

        LearnToSpeak learnToSpeak1 = s -> System.out.println(s);
        SpeechTherapist.teach1("asparagus", learnToSpeak1);

        Consumer<String> consumer1 = s -> System.out.println(s);
        SpeechTherapist.teach2("ketchup", consumer1);

        // METHOD REFERENCE

        LearnToSpeak learnToSpeak2 = System.out::println;
        SpeechTherapist.teach1("asparagus", learnToSpeak2);

        Consumer<String> consumer2 = System.out::println;
        SpeechTherapist.teach2("ketchup", consumer2);

        // FOUR FORMATS:
        // STATIC METHODS

        Consumer<List<Integer>> methodReference1 = Collections::sort;
        Consumer<List<Integer>> lambda1 = s -> Collections.sort(s);

        // INSTANCE METHOD ON OBJECT

        String str = "abc";
        Predicate<String> methodReference2 = str::startsWith;
        Predicate<String> lambda2 = s -> s.startsWith(str);

        var random = new Random();
        Supplier<Integer> methodReference3 = random::nextInt;
        Supplier<Integer> lambda3 = () -> random.nextInt();

        // INSTANCE METHOD ON PARAMETER

        Predicate<String> methodReference4 = String::isEmpty;
        Predicate<String> lambda4 = s -> s.isEmpty();

        BiPredicate<String, String> methodReference5 = String::startsWith;
        BiPredicate<String, String> lambda5 = (s, p) -> s.startsWith(p);

        // CONSTRUCTORS

        Supplier<List<String>> methodReference6 = ArrayList::new;
        Supplier<List<String>> lambda6 = () -> new ArrayList<>();

        Function<Integer, List<String>> methodReference7 = ArrayList::new;
        Function<Integer, List<String>> lambda7 = s -> new ArrayList<>(s);

        // COLLECTIONS:

        // LIST

        List<String> list = new ArrayList<>();

        // SET

        Set<String> set = new HashSet<>();

        // MAP

        Map<String, String> map = new HashMap<>();

        // QUEUE

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println("Fifo Queue");
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        Queue<Integer> pq = new PriorityQueue<>();
        pq.add(2);
        pq.add(1);
        pq.add(3);
        System.out.println("Priority Queue:");
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        Queue<Integer> rpq = new PriorityQueue<>((a, b) -> b - a);
        rpq.add(2);
        rpq.add(1);
        rpq.add(3);
        System.out.println("Reverse Priority Queue:");
        System.out.println(rpq.poll());
        System.out.println(rpq.poll());
        System.out.println(rpq.poll());
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack:");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
