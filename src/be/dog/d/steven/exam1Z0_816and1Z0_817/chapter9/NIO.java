package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter9;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

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
        if (uri1 != null) {
            path3 = Path.of(uri1);
        }

        // FileSystem
        Path path4 = FileSystems.getDefault().getPath("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter9/files/File1.txt");
        System.out.println();

        // PATH METHODS
        System.out.println("PATH METHODS:");
        System.out.println(path1.getParent().normalize().toAbsolutePath()); // Method chaining, returning new path

        System.out.println("toString(): " + path1.toString());
        System.out.println("getNameCount(): " + path1.getNameCount());
        System.out.println("getName(4): " + path1.getName(4));
        System.out.println("subPath(3,6): " + path1.subpath(3, 6));
        System.out.println("getFileName(): " + path1.getFileName());
        System.out.println("getParent(): " + path1.getParent());
        System.out.println("relativePath.getRoot(): " + path1.getRoot());
        System.out.println("absolutePath.getRoot(): " + path1.toAbsolutePath().getRoot());
        System.out.println("isAbsolute(): " + path1.isAbsolute());
        System.out.println("toAbsolutePath(): " + path1.toAbsolutePath());
        System.out.println("resolve(relativePath): " + path1.resolve("test"));
        System.out.println("resolve(absolutePath): " + path1.resolve(path1.toAbsolutePath()));
        System.out.println("relativize(parent): " + path1.relativize(path1.getParent()));
        System.out.println("normalize(): " + path1.normalize());

        try {
            System.out.println("toRealPath(): " + path1.toRealPath(LinkOption.NOFOLLOW_LINKS));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Path.of(\".\").toRealPath(): " + Path.of(".").toRealPath(LinkOption.NOFOLLOW_LINKS));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

        // FILES METHODS

        System.out.println("FILES METHODS:");
        System.out.println("exists(path, linkOptions): " + Files.exists(path1, LinkOption.NOFOLLOW_LINKS));

        try {
            System.out.println("isSameFile(path, path2): " + Files.isSameFile(path1, path1));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.createDirectory(path1);
        } catch (IOException e) {
            System.err.println("createDirectory(path, fileAttributes) threw a FileAlreadyExistsException.");
        }

        try {
            System.out.println("createDirectories(path, fileAtrributes): " + Files.createDirectories(path1.getParent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.delete(path2);
            System.out.println("copy(path, path2, copyOptions): " + Files.copy(path1, path2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.delete(path2.getParent().resolve("destination/" + path2.getFileName()));
            System.out.println("copy(path, directory, copyOptions): " + Files.copy(path2, path2.getParent().resolve("destination/" + path2.getFileName())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (var reader = Files.newBufferedReader(path1)) {
            System.out.println("newBufferedReader(path): " + reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("readAllLines(path): ");
        List<String> strings = new ArrayList<>();
        try {
            strings = Files.readAllLines(path1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(strings);
    }
}

// FILE ATTRIBUTES

class Attributes {
    private static final Path PATH = Path.of("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter9/files/File1.txt");

    public static void main(String[] args) {
        System.out.println("isDirectory(path): " + Files.isDirectory(PATH));
        System.out.println("isSymbolicLink(path): " + Files.isSymbolicLink(PATH));
        System.out.println("isRegularFile(path): " + Files.isRegularFile(PATH));

        try {
            System.out.println("isHidden(path): " + Files.isHidden(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("isReadable(path): " + Files.isReadable(PATH));
        System.out.println("isWritable(path): " + Files.isWritable(PATH));
        System.out.println("isExecutable(path): " + Files.isExecutable(PATH));

        try {
            System.out.println("size(path): " + Files.size(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("getLastModified(path): " + Files.getLastModifiedTime(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

        // VIEWS

        try {
            BasicFileAttributes data = Files.readAttributes(PATH, BasicFileAttributes.class);
            System.out.println("Data on " + PATH.getFileName() + ":");
            System.out.println(data.isDirectory());
            System.out.println(data.isRegularFile());
            System.out.println(data.isSymbolicLink());
            System.out.println(data.size());
            System.out.println(data.lastModifiedTime());
            System.out.println(data.lastAccessTime());
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BasicFileAttributeView view = Files.getFileAttributeView(PATH, BasicFileAttributeView.class);

        BasicFileAttributes data = null;
        try {
            data = view.readAttributes();
            System.out.println("Data on " + PATH.getFileName() + ":");
            System.out.println(data.isDirectory());
            System.out.println(data.isRegularFile());
            System.out.println(data.isSymbolicLink());
            System.out.println(data.size());
            System.out.println(data.lastModifiedTime());
            System.out.println(data.lastAccessTime());
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            view.setTimes(null, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            view.setTimes(FileTime.fromMillis(data.lastModifiedTime().toMillis() + 10_000), null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// FUNCTIONAL PROGRAMMING WITH NIO

class Functional {
    private static final Path PATH = Path.of("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter9/files");
    private static final Path COPIES = Path.of("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter9/copies");

    public static void copyPath(Path source, Path target) {
        try {
            Files.copy(source, target);
            if (Files.isDirectory(source)) {
                try (Stream<Path> s = Files.list(source)) {
                    s.forEach(p -> copyPath(p, target.resolve(p.getFileName())));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deletePath(Path path) {
        if (Files.isDirectory(path)) {
            try (Stream<Path> s = Files.walk(path)) {
                s.sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int count = PATH.getNameCount();
        try (Stream<Path> s = Files.list(PATH)) {
            s.map(p -> p.subpath(count, count + 1)).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        deletePath(COPIES);
        copyPath(PATH, COPIES);
    }
}