package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter1;

import static be.dog.d.steven.exam1Z0_816and1Z0_817.chapter1.Season.*;

public class JavaFundamentals {

    // INNER CLASS

    private final String greeting = "Hi!";

    protected class Inner {
        public int repeat = 3;
        public void go(){
            for (int i = 0; i < repeat; i++) {
                System.out.println(greeting);
            }
        }
    }

    public void callInner() {
        Inner inner = new Inner();
        inner.go();
    }

    public static void main(String[] args) {

        JavaFundamentals javaFundamentals = new JavaFundamentals();
        javaFundamentals.callInner();

        // ENUM IN A SWITCH CASE

        Season season = Season.SUMMER;

        switch(season){
            case SUMMER:
                System.out.println(SUMMER.getVisitors());
                break;
            case FALL:
                System.out.println(FALL.getVisitors());
                break;
            case WINTER:
                System.out.println(WINTER.getVisitors());
                break;
            case SPRING:
                System.out.println(SPRING.getVisitors());
                break;
            default:
                System.out.println("none");
        }
    }
}
