package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter1;

import static be.dog.d.steven.exam1Z0_816and1Z0_817.chapter1.Season.*;

public class JavaFundamentals {

    // INNER CLASS

    private final String greeting = "Hi!";

    protected class Inner {
        public int repeat = 2;

        public void go() {
            for (int i = 0; i < repeat; i++) {
                System.out.println(greeting);
            }
        }
    }

    public void callInner() {
        Inner inner = new Inner();
        inner.go();
    }

    // STATIC NESTED CLASS

    private static class StaticNestedClass {
        private String staticNestedClassMember1 = "test";
    }

    // LOCAL CLASS

    public void holdsLocalClass() {
        String effectivelyFinalLocalVariable = "Message from a local class.";
        class LocalClass {
            public void message() {
                System.out.println(greeting + " " + effectivelyFinalLocalVariable);
            }
        }
        LocalClass localClass = new LocalClass();
        localClass.message();
    }

    // ANONYMOUS CLASS WITH ABSTRACT CLASS

    abstract class AbstractClass {
        abstract void toOverride();
    }

    public void holdsAnonymousInnerClass1() {
        AbstractClass abstractClass = new AbstractClass() {
            @Override
            void toOverride() {
                System.out.println("I'm anonymously created from an abstract class.");
            }
        };
        abstractClass.toOverride();
    }

    // ANONYMOUS CLASS WITH INTERFACE

    interface Interface {
        void toOverride();
    }


    public void holdsAnonymousInnerClass2() {
        Interface inYourFace = new Interface() {
            @Override
            public void toOverride() {
                System.out.println("I'm anonymously created from an interface.");
            }
        };
        inYourFace.toOverride();
    }

    // INLINE ANONYMOUS INNER CLASS

    interface ReturnString {
        String returnString();
    }

    public void inlineAnonymousInnerClass() {
        System.out.println(new ReturnString() {
            @Override
            public String returnString() {
                return "I'm anonymously created inline.";
            }
        }.returnString());
    }

    public static void main(String[] args) {

        // ENUM IN A SWITCH CASE

        Season season = Season.SUMMER;

        switch (season) {
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

        // INNER CLASS

        JavaFundamentals javaFundamentals = new JavaFundamentals();
        javaFundamentals.callInner();

        Inner inner = javaFundamentals.new Inner();
        inner.go();

        // STATIC NESTED CLASS

        StaticNestedClass staticNestedClass1 = new StaticNestedClass();
        System.out.println(staticNestedClass1.staticNestedClassMember1);

        // LOCAL CLASS

        javaFundamentals.holdsLocalClass();

        // ANONYMOUS INNER CLASSES
        javaFundamentals.holdsAnonymousInnerClass1();
        javaFundamentals.holdsAnonymousInnerClass2();
        javaFundamentals.inlineAnonymousInnerClass();

    }
}

interface Walk {
    default int getSpeed() {
        return 4;
    }
}

interface Run {
    default int getSpeed() {
        return 12;
    }
}

class HiddenMethods implements Run, Walk {
    @Override
    public int getSpeed() {
        return 6;
    }

    public int getWalkSpeed() {
        return Walk.super.getSpeed();
    }

    public int getRunSpeed() {
        return Run.super.getSpeed();
    }

    public static void main(String[] args) {
        HiddenMethods hiddenMethods = new HiddenMethods();
        System.out.println(hiddenMethods.getSpeed());
        System.out.println(hiddenMethods.getWalkSpeed());
        System.out.println(hiddenMethods.getRunSpeed());
        System.out.println(new Walk() {
            @Override
            public int getSpeed() {
                return 2;
            }
        }.getSpeed());
    }
}

// STATIC INTERFACE METHODS

interface Hop {
    static int getJumpHeight() {
        return 10;
    }
}

class StaticInterfaceMethod implements Hop {
    public void printDetails() {
        System.out.println(Hop.getJumpHeight());
    }

    public static void main(String[] args) {
        System.out.println(Hop.getJumpHeight());
    }
}

// PRIVATE (STATIC) INTERFACE METHODS

interface Tests {
    private void print(String toPrint) {
        System.out.println(toPrint);
    }

    private static void printStatic(String toPrint) {
        System.out.println(toPrint);
    }

    static void a() {
        printStatic("test");
    }

    default void b() {
        print("test");
        printStatic("test");
    }

    private void c() {
        print("test");
        printStatic("test");
    }
}