package be.dog.d.steven.exam1Z0_815.chapter8;

import java.util.ArrayList;
import java.util.List;

class Organism {
    void makeNoise() {
        System.out.println("???");
    }

    static void makeNoise2() {
        System.out.println("???");
    }
}

class Eukaryote extends Organism {
    void makeNoise() {
        System.out.println("Eu?");
    }

    static void makeNoise2() {
        System.out.println("Eu?");
    }
}

class Metazoan extends Eukaryote {
    void makeNoise() {
        System.out.println("Meu?");
    }

    static void makeNoise2() {
        System.out.println("Meu?");
    }
}

class Vertebrate extends Metazoan {
    void makeNoise() {
        System.out.println("Verrr?");
    }

    static void makeNoise2() {
        System.out.println("Verrr?");
    }

    List<String> returnType() {
        return List.of("vertebrate");
    }

    static List<String> returnType2() {
        return List.of("vertebrate");
    }
}

class Tetrapod extends Vertebrate {
    void makeNoise() {
        System.out.println("Clap clap");
    }

    static void makeNoise2() {
        System.out.println("Clap clap");
    }

    ArrayList<String> returnType() {
        ArrayList<String> a = new ArrayList<>();
        a.add("tetrapod");
        return a;
    }

    static ArrayList<String> returnType2() {
        ArrayList<String> a = new ArrayList<>();
        a.add("tetrapod");
        return a;
    }
}

public class Overriding {
    public static void main(String[] args) {
        Tetrapod t = new Tetrapod();
        Vertebrate v = t;
        Metazoan m = v;
        Eukaryote e = m;
        Organism o = e;

        // OVERRIDDEN METHODS

        t.makeNoise();
        v.makeNoise();
        m.makeNoise();
        e.makeNoise();
        o.makeNoise();

        System.out.println(t.returnType());
        System.out.println(v.returnType());

        // HIDDEN METHODS

        t.makeNoise2();
        v.makeNoise2();
        m.makeNoise2();
        e.makeNoise2();
        o.makeNoise2();

        System.out.println(t.returnType2());
        System.out.println(v.returnType2());
    }
}
