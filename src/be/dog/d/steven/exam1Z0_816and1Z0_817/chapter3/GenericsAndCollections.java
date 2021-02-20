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

        // COLLECTION INTERFACES:

        // LIST

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new LinkedList<>();
        List<String> list3 = new Stack<>();

        // SET

        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new LinkedHashSet<>();
        Set<String> set3 = new TreeSet<>();

        // MAP

        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new LinkedHashMap<>();
        Map<String, String> map3 = new TreeMap<>();

        // QUEUE

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println("Fifo Queue");
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        System.out.println("Lifo Queue - LinkedList");
        System.out.println(ll.pollLast());
        System.out.println(ll.pollLast());
        System.out.println(ll.pollLast());
        Deque<Integer> dq = new LinkedList<>();
        dq.add(1);
        dq.add(2);
        dq.add(3);
        System.out.println("Lifo Queue - Deque");
        System.out.println(dq.pollLast());
        System.out.println(dq.pollLast());
        System.out.println(dq.pollLast());
        ArrayDeque<Integer> adq = new ArrayDeque<>();
        adq.add(1);
        adq.add(2);
        adq.add(3);
        System.out.println("Lifo Queue - ArrayDeque");
        System.out.println(adq.pollLast());
        System.out.println(adq.pollLast());
        System.out.println(adq.pollLast());
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Lifo Queue - Stack:");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
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

        // COLLECTION METHODS

        list1.add("a");
        list1.remove("a");
        list1.isEmpty();
        list1.size();
        list1.clear();
        list1.contains("a");
        list1.removeIf(s -> s.startsWith("a"));
        list1.forEach(System.out::println);

        // CREATE LISTS WITH FACTORY METHODS

        Integer[] array = new Integer[]{1,2,3};
        List<Integer> list = Arrays.asList(array); // Array backed list
        System.out.println("array = " + Arrays.toString(array));
        System.out.println("list = " + list);
        array[0] = 8;
        System.out.println("array = " + Arrays.toString(array));
        System.out.println("list = " + list);
        list.set(2, 9);
        System.out.println("array = " + Arrays.toString(array));
        System.out.println("list = " + list);

        list = List.of(array); // Immutable list
        list = List.copyOf(list); // Immutable list
    }
}
