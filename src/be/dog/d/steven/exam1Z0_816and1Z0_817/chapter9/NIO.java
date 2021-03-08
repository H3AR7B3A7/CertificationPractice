package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter9;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class NIO {
    public static void main(String[] args) {
        Path path1 = Path.of("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter9/files/File1.txt");
        URI uri1 = null;
        try {
            uri1 = new URI("file://src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter8/files/File1.txt");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        URI uri2 = path1.toUri();
        if(uri1 != null) {
            Path path2 = Path.of(uri1);
        }

        System.out.println(uri2);
    }
}
