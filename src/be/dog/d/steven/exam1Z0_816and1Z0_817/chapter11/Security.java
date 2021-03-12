package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter11;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// BASIC PRINCIPLES

public final class Security {  // Limit extensibility
    private Map<String, String> comboLock;  // Limit access

    public boolean isComboValid(String safe, String combo) {
        var correctCombo = comboLock.get(safe);
        return combo.equals(correctCombo);
    }
}

// IMMUTABLE OBJECT

final class Animal implements Cloneable {
    private final ArrayList<String> favoriteFoods;

    public Animal(ArrayList<String> favoriteFoods) {
        if (favoriteFoods == null)
            throw new RuntimeException("Favorite foods are required");
        this.favoriteFoods = (ArrayList<String>) favoriteFoods.clone();  // Prevents someone from holding a reference and changing values after 'Animal' creation
    }

    // Don't return mutable objects
//    public ArrayList<String> getFavoriteFoods() {
//        return favoriteFoods;
//    }

    public int getFavoriteFoodCount() {  // Use delegates to return immutable information about mutable objects
        return favoriteFoods.size();
    }

    public String getFavoriteFoodsElement(int index) {
        return favoriteFoods.get(index);
    }

    public List<String> getFavoriteFoodsCopy() {  // Return a copy instead of the actual object to prevent changing values after 'Animal' creation
        return new ArrayList<>(favoriteFoods);
    }

    public ArrayList<String> getFavoriteFoodsClone() {
        return (ArrayList<String>) favoriteFoods.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        ArrayList<String> food = new ArrayList<>();
        food.add("grass");

        Animal cow = new Animal(food);
        Animal clone = (Animal) cow.clone();

        // By default clone makes a shallow copy
        System.out.println(cow == clone);  // False
        System.out.println(cow.equals(clone));  // False

        System.out.println(cow.favoriteFoods == clone.favoriteFoods);  // True, but will be 'false' when overridden deep copy clone() method is implemented
        System.out.println(cow.favoriteFoods.equals(clone.favoriteFoods));  // True
    }

    // Deep copy clone
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        ArrayList<String> favFoodClone = (ArrayList<String>) favoriteFoods.clone();
//        return new Animal(favFoodClone);
//    }
}

// CUSTOM SERIALIZATION

class Employee implements Serializable {
    private String name;
    private String ssn;
    private int age;

    public Employee(String name, String ssn, int age) {
        this.name = name;
        this.ssn = ssn;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSsn() {
        return ssn;
    }

    public int getAge() {
        return age;
    }

    @Serial
    private static final ObjectStreamField[] serialPersistentFields =
            {new ObjectStreamField("name", String.class),
                    new ObjectStreamField("ssn", String.class)};

    private static String encrypt(String input) {
        // Encryption
        String encrypted = input;
        return encrypted;
    }

    private static String decrypt(String input) {
        // Encryption
        String decrypted = input;
        return decrypted;
    }

    @Serial
    private void writeObject(ObjectOutputStream s) throws IOException {
        ObjectOutputStream.PutField fields = s.putFields();
        fields.put("name", name);
        fields.put("ssn", encrypt(ssn));
        s.writeFields();
    }

    @Serial
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = s.readFields();
        this.name = (String) fields.get("name", null);
        this.ssn = decrypt((String) fields.get("ssn", null));
    }
}

// SERIALIZATION PROCESSING

class Member implements Serializable {
    private String name;

    private Member() {
    }

    private static final Map<String, Member> memberList = new ConcurrentHashMap<>();

    public synchronized static Member getMember(String name) {
        if (memberList.get(name) == null) {
            var m = new Member();
            m.name = name;
            memberList.put(name, m);
        }
        return memberList.get(name);
    }

    // POST SERIALIZATION PROCESSING

    @Serial
    public synchronized Object readResolve() throws ObjectStreamException {
        var existingMember = memberList.get(name);
        if (existingMember == null) {
            // New member not in list
            memberList.put(name, this);
            return this;
        } else {
            // Existing member in list
            existingMember.name = this.name;
            return existingMember;
        }
    }

    // PRE SERIALIZATION PROCESSING

    @Serial
    public Object writeReplace() throws ObjectStreamException {
        var m = memberList.get(name);
        return m != null ? m : this;
    }
}