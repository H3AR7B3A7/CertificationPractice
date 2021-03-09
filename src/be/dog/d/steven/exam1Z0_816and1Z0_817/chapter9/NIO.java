package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter9;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;

// PATH

public class NIO {
    public static void main(String[] args) {
        // Path
        Path path1 = Path.of("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter9/files/File1.txt");

        // Paths
        Path path2 = Paths.get("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter9/files/Copy.txt");

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
        System.out.println();

        // PATH METHODS
        System.out.println("PATH METHODS:");
        System.out.println(path1.getParent().normalize().toAbsolutePath()); // Method chaining, returning new path

        System.out.println("toString(): "+path1.toString());
        System.out.println("getNameCount(): "+path1.getNameCount());
        System.out.println("getName(4): "+path1.getName(4));
        System.out.println("subPath(3,6): "+path1.subpath(3,6));
        System.out.println("getFileName(): "+path1.getFileName());
        System.out.println("getParent(): "+path1.getParent());
        System.out.println("relativePath.getRoot(): "+path1.getRoot());
        System.out.println("absolutePath.getRoot(): "+path1.toAbsolutePath().getRoot());
        System.out.println("isAbsolute(): "+path1.isAbsolute());
        System.out.println("toAbsolutePath(): "+path1.toAbsolutePath());
        System.out.println("resolve(relativePath): "+path1.resolve("test"));
        System.out.println("resolve(absolutePath): "+path1.resolve(path1.toAbsolutePath()));
        System.out.println("relativize(parent): "+path1.relativize(path1.getParent()));
        System.out.println("normalize(): "+path1.normalize());

        try {
            System.out.println("toRealPath(): "+path1.toRealPath(LinkOption.NOFOLLOW_LINKS));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Path.of(\".\").toRealPath(): "+Path.of(".").toRealPath(LinkOption.NOFOLLOW_LINKS));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

        // FILES METHODS

        System.out.println("FILES METHODS:");
        System.out.println("exists(path, linkOptions): "+Files.exists(path1, LinkOption.NOFOLLOW_LINKS));

        try {
            System.out.println("isSameFile(path, path2): "+ Files.isSameFile(path1, path1));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.createDirectory(path1);
        } catch (IOException e) {
            System.err.println("createDirectory(path, fileAttributes) threw a FileAlreadyExistsException.");
        }

        try {
            System.out.println("createDirectories(path, fileAtrributes): "+Files.createDirectories(path1.getParent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("copy(path, path2, copyOptions): "+Files.copy(path1, path2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
