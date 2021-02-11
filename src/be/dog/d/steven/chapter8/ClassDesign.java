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

public class ClassDesign extends Design{
    String scope = "Child Scope";

    // THIS KEYWORD

    void setScope(String scope) {
        this.scope = scope;
    }

    // SUPER KEYWORD

    String getScope(){
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
    }
}

// CONSTRUCTOR OVERLOADING USING THIS()

class Rabbit {
    private final String color;
    private final int weight;

    private Rabbit(String color, int weight){ // Private constructors prevent other classes from creating rabbits
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
