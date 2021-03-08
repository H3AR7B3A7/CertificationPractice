package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter9;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

// PATH

public class NIO {
    public static void main(String[] args) {
        // Path
        Path path1 = Path.of("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter9/files/File1.txt");

        // Paths
        Path path2 = Paths.get("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter9/files/File1.txt");

        // URI
        URI uri1 = null;
        try {
            uri1 = new URI("file://src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/File1.txt");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        URI uri2 = path1.toUri();
        Path path3;
        if(uri1 != null) {
            path3 = Path.of(uri1);
        }

        // FileSystem
        Path path4 = FileSystems.getDefault().getPath("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter9/files/File1.txt");

        // METHODS
        System.out.println(path1.getParent().normalize().toAbsolutePath()); // Method chaining, returning new path

        System.out.println(path1.toString());
        System.out.println(path1.getNameCount());
        System.out.println(path1.getName(4));


    }
}
