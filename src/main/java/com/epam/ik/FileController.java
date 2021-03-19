package com.epam.ik;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileController {

    private static final Path DIRECTORY = Paths.get("src/main/resources/");
    private static final Path CONFIG = DIRECTORY.resolve("config.json");

    public static void main(String[] args) {

        Gson gson = new Gson();

        try (FileReader fileReader = new FileReader(CONFIG.toString())) {
            JsonReader jsonReader = new JsonReader(fileReader);
            Configuration configuration = gson.fromJson(jsonReader, Configuration.class);
            String suffix = configuration.getSuffix();
            String[] fileNames = configuration.getFileNames();

            if (checkFilesExisting(fileNames)) {
                System.out.println("All specified files are exist. Ready to work...");
            } else {
                throw new FileNotFoundException("Specified file or files not found!");
            }
            for (String fileName : fileNames) {
                renameFile(DIRECTORY.resolve(fileName), DIRECTORY.resolve(suffix.concat(fileName)));
            }
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean checkFilesExisting(String[] fileNames) {
        for (String fileName : fileNames) {
            if (Files.notExists(DIRECTORY.resolve(fileName))) {
                return false;
            }
        }
        return true;
    }

    private static void renameFile(Path currentName, Path targetName) throws IOException {
        Files.move(currentName, targetName, StandardCopyOption.REPLACE_EXISTING);
        System.out.println(currentName + " -> " + targetName);
    }

    public static Path getDIRECTORY() {
        return DIRECTORY;
    }

    public static Path getCONFIG() {
        return CONFIG;
    }
}
