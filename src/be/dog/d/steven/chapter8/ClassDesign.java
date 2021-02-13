package be.dog.d.steven.chapter8;

class Design {
    private String secret = "Shhhh..";
    String scope = "Parent Scope";

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}

// INHERITANCE

public class ClassDesign extends Design {
    String scope = "Child Scope";

    // THIS KEYWORD

    void setScope(String scope) {
        this.scope = scope;
    }

    // SUPER KEYWORD

    String getScope() {
        return super.scope + ": " + scope;
    }

    public static void main(String[] args) {
        ClassDesign cd1 = new ClassDesign();
        ClassDesign cd2 = new ClassDesign();

        cd1.setSecret("Wazah!");
        System.out.println(cd1.getSecret());
        System.out.println(cd2.getSecret());

        cd1.setScope("myScope");
        System.out.println(cd1.getScope());

        System.out.println(new ClassDesign().getScope());

        Rabbit.main(new String[]{});

        Hippo.main(new String[]{});

        Kangaroo.main(new String[]{});
    }
}

// CONSTRUCTOR OVERLOADING USING THIS()

class Rabbit {
    private final String color;
    private final int weight;

    private Rabbit(String color, int weight) { // Private constructors prevent other classes from creating rabbits
        this.color = color;
        this.weight = weight;
    }

    private Rabbit(int weight) {
        this("White", weight);
    }

    public static void main(String[] args) { // We can still create rabbits calling static methods in the class
        Rabbit r = new Rabbit(5);
        System.out.println(r.color + " rabbit, weight: " + r.weight);
    }
}

// ORDER OF INITIALIZATION

class Animal {
    static {
        System.out.println("Parent class initialized...");
    }
}

class Hippo extends Animal {
    static {
        System.out.println("Child class initialized...");
    }

    {
        System.out.println("Object gets instantiated...");
    }

    public static void main(String[] args) {
        System.out.println("Main gets executed:");
        new Hippo();
        new Hippo();
        new Hippo();
    }
}

// OVERRIDING VS HIDING

class Marsupial {
    int age = 2;

    public static boolean isBiped() {
        return false;
    }

    public boolean isFighter() {
        return false;
    }
}

class Kangaroo extends Marsupial {
    int age = 6;

    public static boolean isBiped() {
        return true;
    }

    @Override
    public boolean isFighter() {
        return true;
    }

    public static void main(String[] args) {
        Kangaroo joey = new Kangaroo();
        Marsupial moey = joey;

        System.out.println(joey.age); // 6 - Hidden
        System.out.println(moey.age); // 2
        System.out.println(joey.isBiped()); // True - Hidden
        System.out.println(moey.isBiped()); // False
        System.out.println(joey.isFighter()); // True - Overridden
        System.out.println(moey.isFighter()); // True
    }
}

// SUPER()

class Bird {
    int feathers;

    public Bird(int feathers) {
        this.feathers = feathers;
    }
}

class Parrot extends Bird {
    public Parrot() {
        super(10); // Mandatory because bird doesn't have NoArgument constructor!
    }
}

// THIS()

class Whale {
    private int weight;

    public Whale(int weight) {
        this.weight = weight;
    }

    public Whale(String name) {
        this(100);
    }
}