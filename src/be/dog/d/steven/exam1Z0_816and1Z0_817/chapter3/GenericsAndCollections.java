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

        Integer[] array = new Integer[]{1, 2, 3};
        List<Integer> list = Arrays.asList(array);  // Array backed list
        System.out.println("array = " + Arrays.toString(array));
        System.out.println("list = " + list);
        array[0] = 8;
        System.out.println("array = " + Arrays.toString(array));
        System.out.println("list = " + list);
        list.set(2, 9);
        System.out.println("array = " + Arrays.toString(array));
        System.out.println("list = " + list);

        list = List.of(array);  // Immutable list
        list = List.copyOf(list);  // Immutable list

        // MAP METHODS

        map1.put("1", null);
        map1.put("1", "A");
        map1.put("1", "B");
        System.out.println(map1);
        map1.put("1", null);
        map1.putIfAbsent("1", "A");
        map1.putIfAbsent("1", "B");
        System.out.println(map1);

        BiFunction<String, String, String> merge1 = (s1, s2) -> s1.length() > s2.length() ? s1 : s2;
        map2.put("1", "1");
        map2.merge("1", "12", merge1);
        System.out.println(map2);

        // SORTING:
        // COMPARABLE

        List<BunnyRabbit> bunnies = new ArrayList<>();
        bunnies.add(new BunnyRabbit("puffball", 4)); // Comparable in BunnyRabbit ignores case
        bunnies.add(new BunnyRabbit("Fluffy", 3));
        bunnies.add(new BunnyRabbit("Fluffy", 1));
        bunnies.add(new BunnyRabbit("Snowwy", 3));
        bunnies.add(new BunnyRabbit("Whisker", 2));

        Collections.sort(bunnies);
        System.out.println(bunnies); // Print in BunnyRabbit prints name with capitalized first letter

        // COMPARATOR

        Comparator<BunnyRabbit> byAge1 = new Comparator<>() {  // FUNCTIONAL INTERFACE
            @Override
            public int compare(BunnyRabbit o1, BunnyRabbit o2) {
                if (o1.getAge() - o2.getAge() != 0){ // If age is not equal
                    return o1.getAge() - o2.getAge(); // Compare age
                }
                return o1.getName().compareToIgnoreCase(o2.getName());  // Else compare name
            }
        };

        Comparator<BunnyRabbit> byAge2 = (o1, o2) -> {  // LAMBDA
            if (o1.getAge() - o2.getAge() != 0){
                return o1.getAge() - o2.getAge();
            }
            return o1.getName().compareToIgnoreCase(o2.getName());
        };

        Collections.sort(bunnies, byAge1);  // SORT USING COLLECTIONS
        bunnies.sort(byAge1);  // SORT USING LIST
        System.out.println(bunnies);

        bunnies.sort(byAge1.reversed());  // Both age and name reversed
        System.out.println(bunnies);

        Comparator<BunnyRabbit> byAge3 = Comparator.comparingInt(BunnyRabbit::getAge).reversed().thenComparing(BunnyRabbit::getName); // METHOD REFERENCE
        Comparator<BunnyRabbit> byAge4 = Comparator.comparingInt(BunnyRabbit::getAge).thenComparing(BunnyRabbit::getName).reversed();
        Comparator<BunnyRabbit> byAge5 = Comparator.comparingInt(BunnyRabbit::getAge).reversed().thenComparing(BunnyRabbit::getName).reversed();

        bunnies.sort(byAge3);
        System.out.println(bunnies);  // Only age reversed
        bunnies.sort(byAge4);
        System.out.println(bunnies);  // Both age and name reversed
        bunnies.sort(byAge5);
        System.out.println(bunnies);  // Only name reversed (by calling reversed() twice)

        Comparator<BunnyRabbit> natural = Comparator.naturalOrder();  // Comparator using the Comparable interface
        Comparator<BunnyRabbit> reversed = Comparator.reverseOrder();

        Set<BunnyRabbit> rabbits = new TreeSet<>();
        rabbits.add(new BunnyRabbit("Splinter",50));
        Set<Turtle> ninjaTurtles = new TreeSet<>((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        ninjaTurtles.add(new Turtle("Raphael",18));

        // GENERICS

        Crate<BunnyRabbit> bunnyRabbitCrate = new Crate<>();
        bunnyRabbitCrate.loadCrate(new BunnyRabbit("Fluffy",1));
        BunnyRabbit bunnyRabbit = bunnyRabbitCrate.unloadCrate();
        System.out.println(bunnyRabbit);
    }
}

// IMPLEMENTING COMPARABLE: COMPARE TO AND EQUALS

class BunnyRabbit implements Comparable<BunnyRabbit> {
    private final String name;
    private final int age;

    public BunnyRabbit(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(BunnyRabbit o) {
        if (o == null) {
            throw new IllegalArgumentException("Poorly formed BunnyRabbit.");
        }
        if (this.name == null && o.name == null) {
            return 0;
        } else if (this.name == null) {
            return -1;
        } else if (o.name == null) {
            return 1;
        }
        if (name.compareToIgnoreCase(o.name) != 0) {  // If name is not equal
            return name.compareToIgnoreCase(o.name);  // Compare name
        }
        return this.age - o.age;  // Else compare age
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BunnyRabbit that = (BunnyRabbit) o;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return name.substring(0, 1).toUpperCase() + name.substring(1) + " (" + age + ")";
    }
}

// A CLASS NOT IMPLEMENTING COMPARABLE

class Turtle {
    private final String name;
    private final int age;

    public Turtle(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

// GENERICS

class Crate<T> {
    private T content;

    public void loadCrate(T content){
        this.content = content;
    }

    public T unloadCrate(){
        return content;
    }

    public static <T> Crate<T> ship(T t){
        return new Crate<T>();
    }
}

// WILDCARDS

class Wildcards {
    public static void printStuff(List<?> stuff) {  // UNBOUNDED
        for (Object o: stuff) {
            System.out.println(o);
        }
    }

    public static double sumAnything(List<? extends Number> stuff) {
        double sum = 0;
        for (Number n: stuff) {
            sum += n.doubleValue();
        }
        return Math.round(sum * 100.0) / 100.0;
    }

    public static void addSomething(List<? super String> someList) {
        someList.add("something");
    }

    public static void main(String[] args) {
        List<BunnyRabbit> anything = new ArrayList<>();
        anything.add(new BunnyRabbit("Fluffy", 3));
        printStuff(anything);

        List<Float> notLongs = new ArrayList<>();
        notLongs.add(1.12f);
        notLongs.add(1.01f);
        System.out.println(sumAnything(notLongs));

        List<String> stringList = new ArrayList<>();
        addSomething(stringList);
        System.out.println(stringList);
        List<Object> objectList = new ArrayList<>();
        addSomething(objectList);
        System.out.println(objectList);
    }
}