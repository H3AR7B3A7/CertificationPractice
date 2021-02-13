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

    void printDescription() {
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

// INTERFACES

abstract interface canFly {  // Abstract modifier is implicit | Can also be public when declared in its own file

    public abstract Integer getDistance(); // Both modifiers are implicit

    Float getSpeed(); // Also public abstract

    int getHeight();

    public static final Integer MAX_DISTANCE = 5000; // All three modifiers are implicit
    Float MAX_SPEED = 40.0f; // Also public static final
    int MAX_HEIGHT = 150;

}

interface canDive {  // Also abstract
    Boolean isDiver();
}

interface canFloat {
    Boolean isFloater();
}

interface canSwim extends canDive, canFloat { // Can extend multiple interfaces
    Float getSwimSpeed();
}

class Duck implements canFly, canSwim {
    @Override
    public Integer getDistance() {
        return MAX_DISTANCE;
    }

    @Override
    public Float getSpeed() {
        return MAX_SPEED;
    }

    @Override
    public int getHeight() {
        return MAX_HEIGHT;
    }

    @Override
    public Float getSwimSpeed() {
        return 20.0f;
    }

    @Override
    public Boolean isDiver() {
        return true;
    }

    @Override
    public Boolean isFloater() {
        return true;
    }
}