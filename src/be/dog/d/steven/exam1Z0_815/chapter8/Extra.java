package be.dog.d.steven.exam1Z0_815.chapter8;

public class Extra {
}

class Bear {
    public CharSequence doSomething(){
        return "";
    }
}

    // Can't have new or broader exceptions
//class Panda extends Bear {
//    public String doSomething() throws Exception{
//        return "";
//    }
//}

    // Can't have different return type
//class Grizzly extends Bear {
//    public Integer doSomething() {
//        return 1;
//    }
//}

    // Can't hide instance method
//class BlackBear extends Bear {
//    public static String doSomething(){
//        return "";
//    }
//}

    // Can't be less accessible
//class KoalaBear extends Bear {
//    String doSomething(){
//        return "";
//    }
//}

    // Can return subtype of overridden methods return type
class SpectacledBear{
    public String doSomething() {
        return "";
    }
}

// OVERLOADING
class PolarBear extends Bear{
    public String doSomething(String something) {
        return something;
    }
}

// HIDING
class Dino {
    public static String doSomething(){
        return "";
    }
}

class Triceratops extends Dino {
    public static String doSomething() {
        return "triceratops";
    }
}