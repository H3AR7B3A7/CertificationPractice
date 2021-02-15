package be.dog.d.steven.exam1Z0_815.chapter7;

import java.util.ArrayList;
import java.util.List;

public class Overloading {
    public static void main(String[] args) {
        new Overloading().overloaded();
        System.out.println(new Overloading().overloaded(1));
        System.out.println(new Overloading().overloaded("test"));
        new Overloading().overloaded(List.of());
        new Overloading().overloaded(new ArrayList<>());
    }

    public void overloaded() {
        System.out.println("void - no params");
    }

    public String overloaded(int a) {
        return "string - int param";
    }

    public List<String> overloaded(String a) {
        return List.of("string", "list", "-", "string", "param");
    }

    public void overloaded(List<Integer> a) {
        System.out.println("void - list param");
    }

    public void overloaded(ArrayList<Integer> a) {
        System.out.println("void - arraylist param");
    }
}