package be.dog.d.steven.exam1Z0_815.chapter9;

public class AdvancedClassDesign {
    public static void main(String[] args) {

        Eukaryote.main(new String[]{});

        CircleOrRectangle.main(new String[]{});

        Zoo.main(new String[]{});
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
    default Boolean isFloater() { // Default methods can have a body | They do not HAVE TO be overridden
        return true;
    }
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
    public Float getSpeed() { // Is assumed public in the interface, thus has to be public in implementation (Overriding rules!)
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
}

// DUPLICATE METHODS

interface Circle {
    String getName();

    double getSurfaceArea(int radius);

    String getColor();
}

interface Rectangle {
    // No conflict
    String getName();

    // Overload
    double getSurfaceArea(int width, int height);

    // Overridden because String and CharSequence are covariant
    CharSequence getColor();
}

class CircleOrRectangle implements Circle, Rectangle {
    @Override
    public String getName() {
        return "Rectircle";
    }

    @Override
    public double getSurfaceArea(int radius) {
        return Math.pow(radius, 2) * Math.PI;
    }

    @Override
    public double getSurfaceArea(int width, int height) {
        return width * height;
    }

    @Override
    public String getColor() {
        return "Red";
    }

    public static void main(String[] args) {
        System.out.println(new CircleOrRectangle().getSurfaceArea(3));
    }
}

// MEMBER INNER CLASS

class Zoo {
    private interface Paper { // Inner interface
        String getId();
    }

    public class Ticket implements Paper { // Inner class
        private final String serialNumber;

        public Ticket(String serialNumber) {
            this.serialNumber = serialNumber;
        }

        @Override
        public String getId() {
            return serialNumber;
        }
    }

    public Ticket sellTicket(String serialNumber) {
        return new Ticket(serialNumber);
    }

    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        Ticket ticket = zoo.sellTicket("ab123");
        System.out.println("Ticket with id: " + ticket.getId() + " was sold.");
    }
}