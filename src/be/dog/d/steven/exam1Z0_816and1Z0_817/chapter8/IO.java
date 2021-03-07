package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter8;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// Files

public class IO {
    public static void main(String[] args) {
        // Two ways to get system separator
        System.out.println(System.getProperty("file.separator"));
        System.out.println(File.separator);

        // These only create the file object, not the actual file
        var myFile1 = new File("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/File1.txt");
        var myFile2 = new File("src\\be\\dog\\d\\steven\\exam1Z0_816and1Z0_817\\chapter8\\files\\File2.txt");

        // The specified folder has to exist to be able to write to it
        var folder = new File(myFile1.getParent()); // Folders are handled pretty much the same as files in Java
        boolean success = folder.mkdir();
        System.out.println("Folder was created:" + success);

        System.out.println("File1 exists:" + myFile1.exists());
        System.out.println("File2 exists:" + myFile2.exists());

        //The files are created when we write to them
        try (FileOutputStream fileOutputStream = new FileOutputStream(myFile1); // Byte stream: used for pictures etc...
             Writer writer = new FileWriter(myFile2)) {  // Character stream: used for text
            fileOutputStream.write("test".getBytes());  // Takes bytes
            writer.write("test");  // Takes strings, chars ...
        } catch (IOException e) {
            e.printStackTrace();
        }

        // File methods
        if (myFile1.isFile()) {
            System.out.println("File1 stats:");
            System.out.println(myFile1.getAbsolutePath());
            System.out.println(myFile1.length());
            System.out.println(myFile1.lastModified());
        }

        // Folder methods
        if (folder.isDirectory()) {
            System.out.println("Folder 'files' content:");
            System.out.println(Arrays.toString(folder.listFiles()));
        }

        // Higher level streams
        IntStream intStream = "test".chars();
        Stream<Character> characterStream = "test".chars().mapToObj(i -> (char) i);
        Stream<String> stringStream = "test".codePoints().mapToObj(c -> String.valueOf((char) c));

        // Buffered reader / writer
        String text = "";
        try (var reader = new BufferedReader(new FileReader(myFile1))) {
            String nextLine = reader.readLine();
            while (nextLine != null) {
                text += nextLine;  // A couple of words don't need StringBuilder
                nextLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File1 content: " + text);
    }
}

// I/O STREAMS

class StreamsEverywhere {
    void copyStream(InputStream in, OutputStream out) throws IOException {
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
    }

    void copyStream(Reader in, Writer out) throws IOException {
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
    }

    public static void readData(InputStream input) throws IOException {
        System.out.print((char) input.read());
        if (input.markSupported()) {
            input.mark(100);  // Not all input streams support mark
            System.out.print(input.read());
            System.out.print((char) input.read());
            input.reset();  // Not all input streams support reset
        }
        System.out.print((char) input.read());
        System.out.print((char) input.read());
        System.out.print((char) input.read());
        System.out.println();
    }

    public static void readData(Reader input) throws IOException {
        System.out.print((char) input.read());
        if (input.markSupported()) {
            input.mark(100);  // Not all input streams support mark
            System.out.print(input.read());
            System.out.print((char) input.read());
            input.reset();  // Not all input streams support reset
        }
        System.out.print((char) input.read());
        System.out.print((char) input.read());
        System.out.print((char) input.read());
        System.out.println();
    }

    public static void main(String[] args) {
        // Calls close() three times
        try (var fis = new FileOutputStream("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/Output.txt");
             var bis = new BufferedOutputStream(fis);
             var ois = new ObjectOutputStream(bis)) {
            ois.writeObject("test");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Calls close() once
        try (var ois = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/Output.txt")))) {
            ois.writeObject("test");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Does not support mark / reset
        try (InputStream input = new FileInputStream("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/File1.txt")) {
            readData(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Does not support mark / reset
        try (InputStreamReader input = new InputStreamReader(
                new FileInputStream("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/File1.txt"),
                StandardCharsets.UTF_8)) {
            readData(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Does support mark / reset
        try (BufferedReader input = new BufferedReader(
                new FileReader("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/File1.txt"))) {
            readData(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream input = new FileInputStream("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/File1.txt")) {
            System.out.println("Skipping: " + input.skip(1));  // Skip works on any input stream
            int next = input.read();
            while (next != -1) {
                System.out.print((char) next);
                next = input.read();
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (var fos = new FileOutputStream("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/Output.txt")) {
            for (int i = 0; i < 1000; i++) {
                fos.write('a');
                if (i % 100 == 0) {
                    fos.flush();  // Writes fos to disk ever 100 chars
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// BUFFERED I/O STREAMS

class Buffers {
    public static void copyFileWithBuffer(File src, File dest) throws IOException {
        try (var in = new BufferedInputStream(new FileInputStream(src));
             var out = new BufferedOutputStream((new FileOutputStream(dest)))) {
            var buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
        }
    }

    public static void copyTextFileWithBuffer(File src, File dest) throws IOException {
        try (var reader = new BufferedReader(new FileReader(src));
             var writer = new BufferedWriter(new FileWriter(dest))) {
            String s;
            while ((s = reader.readLine()) != null) {
                writer.write(s);
                writer.newLine();
            }
        }
    }

    public static void main(String[] args) {
        var myFile = new File("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/File1.txt");
        var myFileCopy = new File("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/Copy.txt");

        try {
            copyFileWithBuffer(myFile, myFileCopy);
            copyTextFileWithBuffer(myFile, myFileCopy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// SERIALIZABLE

class Dog implements Serializable {
    private static final long serialVersionID = 1L;
    private String name;
    private String breed;
    private int age;
    private Boolean isFriendly;
    private transient String favoriteFood;

    public Dog(String name, String breed, int age, Boolean isFriendly, String favoriteFood) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.isFriendly = isFriendly;
        this.favoriteFood = favoriteFood;
    }

    public static long getSerialVersionID() {
        return serialVersionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getFriendly() {
        return isFriendly;
    }

    public void setFriendly(Boolean friendly) {
        isFriendly = friendly;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Kennel {
    public static void putInKennel(List<Dog> dogs, File dogKennel) throws IOException {
        try (var out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/Kennel.txt")))) {
            for (Dog dog : dogs) {
                out.writeObject(dog);
            }
        }
    }

    public static List<Dog> getOutOfKennel(File dogKennel) throws IOException, ClassNotFoundException {
        var dogs = new ArrayList<Dog>();
        try (var in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/Kennel.txt")))) {
            while (true) {
                var object = in.readObject();
                if (object instanceof Dog) {
                    dogs.add((Dog) object);
                }
            }
        } catch (EOFException e) {
            // File end reached
        }
        return dogs;
    }

    public static void main(String[] args) {
        Dog d = new Dog("Zorro", "Greater Dane", 7, true, "sausages");
        Dog e = new Dog("Baco","none", 5, true, "footballs");

        File kennel = new File("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/Kennel.txt");

        List<Dog> retrievedDogs = new ArrayList<>();

        try {
            putInKennel(List.of(d,e), kennel);
            retrievedDogs = getOutOfKennel(kennel);
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }

        System.out.println(retrievedDogs);
    }
}