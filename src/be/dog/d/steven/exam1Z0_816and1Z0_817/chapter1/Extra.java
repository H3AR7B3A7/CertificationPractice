package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter1;

// MULTIPLE INHERITANCE OF METHODS

abstract class SuperExtra {
    public void someMethod() {
        System.out.println("I win...");
    }
}

interface MyInterface extends MySuperInterFace{
    default void someMethod() {
        System.out.println("I lose...");
    }
    default void someOtherMethod(){
        System.out.println("I win...");
    }
    default void someThirdMethod(){
        System.out.println("Tie...");
    }
}

interface MySuperInterFace{
    default void someOtherMethod(){
        System.out.println("I lose...");
    }
}

interface MyOtherInterface {
    default void someThirdMethod(){
        System.out.println("Tie...");
    }
}

public class Extra extends SuperExtra implements MyInterface, MyOtherInterface {
    public static void main(String[] args) {
        var x = new Extra();
        x.someMethod();  // superclass > interface default
        x.someOtherMethod();  // subtype interface > supertype interface
        x.someThirdMethod();  // collision of 2 interfaces
    }

    @Override
    public void someThirdMethod() {  // Has to be implemented because of collision!!!
        System.out.println("I win...");
    }
}

// MULTIPLE INHERITANCE OF VARIABLES

abstract class Test {
    int a = 5;
}

interface InYourFace {
    int a = 10; // static final
}

class MyClass extends Test implements InYourFace {  // Is allowed

    void print() {
        System.out.println(super.a);
    }

    void print2() {
        System.out.println(InYourFace.a);
    }

    public static void main(String[] args) {
        System.out.println(InYourFace.a);
        var mc = new MyClass();
        mc.print();
        mc.print2();
    }
}
