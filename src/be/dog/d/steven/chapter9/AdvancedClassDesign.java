package be.dog.d.steven.chapter9;

public class AdvancedClassDesign {
    public static void main(String[] args) {

    }
}

// ABSTRACT CLASS & METHOD

abstract class Organism { // Can not be private or protected | Nor final
    protected abstract String getName(); // Can be protected, but not private | Nor final or static
    abstract public String getDescription(); // The abstract modifier can be placed before or after the access modifier (like the final modifier)
    void printName() {
        System.out.println(getName());
    }
    void printDescription(){
        System.out.println(getDescription());
    }

    public Organism() {
        System.out.println("Some organism was constructed!");
    }
}

class Eukaryote extends Organism {
    @Override
    public String getName() {
        return "Eukaryote";
    }

    @Override
    public String getDescription() {
        return "Organism whose cells have a nucleus enclosed within a nuclear envelope.";
    }

    public static void main(String[] args) {
        // The compiler will insert a NoArg-constructor in Eukaryote that calls super(), like with a regular class
        var e = new Eukaryote();
        e.printName();
        e.printDescription();
    }
}