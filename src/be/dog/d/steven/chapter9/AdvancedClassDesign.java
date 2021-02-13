package be.dog.d.steven.chapter9;

public class AdvancedClassDesign {
    public static void main(String[] args) {

    }
}

// ABSTRACT CLASS & METHOD

abstract class Organism {
    abstract void getName();
}

class Eukaryote extends Organism {
    @Override
    void getName() {
        System.out.println("Eukaryote");
    }
}