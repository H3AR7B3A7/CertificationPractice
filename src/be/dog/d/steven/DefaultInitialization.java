package be.dog.d.steven;

public class DefaultInitialization {
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