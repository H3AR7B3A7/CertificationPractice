package be.dog.d.steven.chapter5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CoreJavaAPIs {
    public static void main(String[] args) {

        // STRINGS

        String str1 = "abcde";
        str1 = str1.replace("bc","fg");
        System.out.println(str1); // afgde

        StringBuilder str2 = new StringBuilder("abcde");
        str2.replace(1,3, "fg");
        System.out.println(str2); // afgde

        String a = "Hello world";
        String b = "Hello" + " world";
        System.out.println(a == b); // true

        String c = "rat" + 1;
        String d = "r"+"a"+"t"+"1";
        System.out.println(c == d); // true

        String e = "Hello";
        e += " world";
        System.out.println(e == a); // false

        String f = "hello ".trim();
        String g = "hello";
        System.out.println(f == g); // false

        // INITIALIZING ARRAYS

        int[][] map, space[]; // map = 2D, space = 3D

        // SORTING, SEARCHING ARRAYS

        int[] example = new int[]{2,3,1};
        Arrays.sort(example);
        // Returns index of a match or negates the position to be inserted and subtracts 1
        Arrays.binarySearch(example, 2); // Has to be sorted first, or unpredictable.

        // COMPARING ARRAYS

        int[] one = {1,2};
        int[] two = {1,2,3,4,5}; // negative value because second param has more elements
        System.out.println(Arrays.compare(one,two)); // -3

        int[] three = {2,2}; // first different index has precedence over number of elements
        int[] four = {1,2,3,4,5};
        System.out.println(Arrays.compare(three,four)); // +1

        char[] five = {'a'}; // 'a' is smaller than 'b'
        char[] six = {'b'}; // 'b' is smaller than 'a'
        System.out.println(Arrays.compare(five,six)); // -1

        char[] seven = {'a'}; // Lowercase is bigger
        char[] eight = {'A'}; // Uppercase is smaller
        System.out.println(Arrays.compare(seven,eight)); // 32
        System.out.println((int)'a'); // 97
        System.out.println((int)'A'); // 65

        String[] nine = null; // null is always smaller, always 1/-1
        String[] ten = {"b","c"};
        System.out.println(Arrays.compare(nine,ten)); // -1

        // CONVERTING ARRAY <> LIST

        List<Integer> list = new ArrayList<>();
        Object[] array1 = list.toArray(); // Defaults to Object[]
        Integer[] array2 = list.toArray(new Integer[0]);

        // Returns fixed size, array backed list. Can't change size. Changes to array apply to list.
        List<Integer> backedList = Arrays.asList(array2);
        // Returns immutable list. Can't change size or values.
        List<Integer> immutableList = List.of(array2);
        // To be able to change size we need to create a new ArrayList
        List<Integer> newList1 = new ArrayList<>(List.of(array2));
        newList1.add(3);
        System.out.println(newList1); // [3]
        List<Integer> newList2 = new ArrayList<>(Arrays.asList(array2));
        newList2.add(3);
        System.out.println(newList2); // [3]

        // SORTING ARRAYLIST

        Collections.sort(newList1);

        // Math API

        int min = Math.min(1,2);
        int max = Math.max(1,2);
        int round1 = Math.round(1.6f);
        long round2 = Math.round(1.6);
        double floor = Math.floor(1.6);
        double cubed = Math.pow(2,3);
        double random = Math.random();

    }
}
