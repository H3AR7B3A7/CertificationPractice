package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter8;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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
        while ((b = in.read()) != -1){
            out.write(b);
        }
    }

    void copyStream(Reader in, Writer out) throws IOException {
        int b;
        while ((b = in.read()) != -1){
            out.write(b);
        }
    }

    public static void readData(InputStream input) throws IOException {
        System.out.print((char) input.read());
        if(input.markSupported()) {
            input.mark(100);
            System.out.print(input.read());
            System.out.print((char) input.read());
            input.reset();
        }
        System.out.print((char) input.read());
        System.out.print((char) input.read());
        System.out.print((char) input.read());
        System.out.println();
    }

    public static void readData(Reader input) throws IOException {
        System.out.print((char) input.read());
        if(input.markSupported()) {
            input.mark(100);
            System.out.print(input.read());
            System.out.print((char) input.read());
            input.reset();
        }
        System.out.print((char) input.read());
        System.out.print((char) input.read());
        System.out.print((char) input.read());
        System.out.println();
    }

    public static void main(String[] args) {  // Calls close() three times
        try (var fis = new FileOutputStream("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/Output.txt");
                var bis = new BufferedOutputStream(fis);
                var ois = new ObjectOutputStream(bis)){
            ois.writeObject("test");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (var ois = new ObjectOutputStream(  // Calls close() once
                new BufferedOutputStream(
                        new FileOutputStream("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/Output.txt")))){
            ois.writeObject("test");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Does not support mark
        try (InputStream input = new FileInputStream("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/File1.txt")){
            readData(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Does not support mark
        try (InputStreamReader input = new InputStreamReader(
                new FileInputStream("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/File1.txt"),
                StandardCharsets.UTF_8)){
            readData(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Does support mark
        try (BufferedReader input = new BufferedReader(
                new FileReader("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/File1.txt"))){
            readData(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}