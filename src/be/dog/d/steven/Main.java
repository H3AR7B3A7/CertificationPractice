package be.dog.d.steven;

import java.util.Arrays;

public class Main {

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
    }
}
