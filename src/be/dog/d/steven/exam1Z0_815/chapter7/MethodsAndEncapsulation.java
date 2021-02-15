package be.dog.d.steven.exam1Z0_815.chapter7;

public class MethodsAndEncapsulation {
    public static void main(String[] args) {

        // PASS - BY - VALUE

        int original1 = 1;
        change(original1);
        System.out.println(original1);

        String original2 = "Test";
        change(original2);
        System.out.println(original2);

        StringBuilder original3 = new StringBuilder("Test");
        edit(original3);
        System.out.println(original3);
    }

    private static void edit(StringBuilder original) {
        original.reverse(); // Can use method calls on argument
    }

    // METHOD OVERLOADING

    private static void change(int original) {
        original = 5; // Can't assign new values
    }

    private static void change(String original) {
        original = "Other string";
    }
}

// FINAL AND ACCESS MODIFIERS

class OrderOfModifiers {
    public final void writeA() { // The final modifier can be placed before or after the access modifier
        System.out.println("A");
    }

    final public void writeB() {
        System.out.println("B");
    }
}
