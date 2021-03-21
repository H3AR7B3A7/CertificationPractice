package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Extra {

    private static final Path PATH1 = Path.of("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter9/extra");
    private static final Path PATH2 = Path.of("src/be/dog/d/steven/exam1Z0_816and1Z0_817/chapter9/zip");
    private static final Logger LOGGER = Logger.getLogger(Extra.class.getName());

    public static void main(String[] args) throws IOException {
        // DELETE NESTED FILE STRUCTURE
        createFileStructure(PATH1);
        Files.walk(PATH1)
                .sorted(Comparator.reverseOrder())
                .forEach(p -> {
                    try {
                        Files.delete(p);
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Error deleting file");
                    }
                });

        // ARCHIVE TO ZIP
        createFileStructure(PATH1);
        Files.createDirectories(PATH2);
        Files.deleteIfExists(PATH2.resolve("zipped.zip"));
        Files.createFile(PATH2.resolve("zipped.zip"));
        try (ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(PATH2.resolve("zipped.zip")))){
            out.setLevel(Deflater.BEST_COMPRESSION);
            Files.walk(PATH1).filter(p -> !Files.isDirectory(p))
                    .forEach(p -> {
                        ZipEntry zipEntry = new ZipEntry(PATH1.relativize(p).toString());
                        try {
                            out.putNextEntry(zipEntry);
                            out.write(Files.readAllBytes(p));
                            out.closeEntry();
                        } catch (IOException e) {
                            LOGGER.log(Level.SEVERE, "Error creating zip entry");
                        }
                    });
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error creating zip archive");
        }

        // REPRESENT ZIP AS FILESYSTEM (needs existing zip file - end header not found ???)
//        Files.deleteIfExists(PATH2.resolve("zipped.zip"));
//        Files.createFile(PATH2.resolve("zipped.zip"));
//
//        try (FileSystem fs = FileSystems.newFileSystem(PATH2.resolve("zipped.zip"))){
//            Files.walk(PATH1).forEach(p -> {
//                System.out.println(PATH1.relativize(p).toString());
//                try {
//                    Path target = fs.getPath(PATH1.relativize(p).toString());
//                    Files.copy(p,target);
//                } catch (IOException e) {
//                    LOGGER.log(Level.SEVERE, "Error archiving file", e);
//                }
//            });
//        } catch (IOException e) {
//            LOGGER.log(Level.SEVERE, "Error creating archive", e);
//        }
    }

    private static void createFileStructure(Path path) throws IOException {
        Files.createDirectories(path);
        Files.deleteIfExists(path.resolve("File1.txt"));
        Files.createFile(path.resolve("File1.txt"));
        Files.createDirectories(path.resolve("pictures"));
        Files.deleteIfExists(path.resolve("pictures/image.jpg"));
        Files.createFile(path.resolve("pictures/image.jpg"));
    }
}
