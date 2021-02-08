package be.dog.d.steven.chapter2;

public class JavaBuildingBlocks{
    public static void main(String[] args) {
        int i = 017; // Octal
        System.out.println(i); // 15

        int j = 0xF; // Hexadecimal
        System.out.println(j); // 15

        int k = 0b1111; // Binary
        System.out.println(k); // 15
    }
}

class OrderOfInitialization {
    private String name = "Fluffy"; // Initialized first
    {
        System.out.println("Initializing fields...");
    }

    public OrderOfInitialization() {
        this.name = "Tiny"; // Initialized second
        System.out.println("Constructing...");
    }

    public static void main(String[] args) {
        OrderOfInitialization ooi = new OrderOfInitialization(); // Execution starts here
        System.out.println(ooi.name); // Tiny
    }
}

class DefaultInitialization {
    private static int a; // Class variable: Defaults to 0
    private int b; // Instance variable: Defaults to 0
    private Object obj; // Defaults to null

    public DefaultInitialization() {
    }

    public static void main(String[] args) {
        System.out.println("class:"+a);
        DefaultInitialization test = new DefaultInitialization();
        System.out.println("instance:"+test.b);
        System.out.println("object:"+test.obj);
        test.write();
    }

    public void write(){
        int c = 0; // Local variable: Needs to be initialized
        System.out.println("local:"+c);
    }
}