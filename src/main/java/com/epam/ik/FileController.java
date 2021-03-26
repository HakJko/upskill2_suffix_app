package com.epam.ik;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.*;

import static com.epam.ik.logging.LogMessages.*;

/**
 * developer Ihar Koshman
 *
 * 3/26/2021
 *
 * for epam UpSkill_part2, module 04
 */

public class FileController {

    private static final Path DIRECTORY = Paths.get("src/main/resources/");
    private static final Path CONFIG = DIRECTORY.resolve("config.json");
    private static final Logger LOGGER = Logger.getLogger(FileController.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration();
            Handler fileHandler = new FileHandler(DIRECTORY.resolve("myJavaLog.log").toString(), true);
            LOGGER.setUseParentHandlers(false);// or true for output with console
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(System.getProperty("java.util.logging.config.file"));
        LOGGER.log(Level.INFO, LOG_FILE_CONTROLLER_STARTED);

        Gson gson = new Gson();

        try (FileReader fileReader = new FileReader(CONFIG.toString())) {
            JsonReader jsonReader = new JsonReader(fileReader);
            Configuration configuration = gson.fromJson(jsonReader, Configuration.class);
            LOGGER.log(Level.INFO, LOG_CONFIG_READ);
            String suffix = configuration.getSuffix();
            String[] fileNames = configuration.getFileNames();

            if (checkFilesExisting(fileNames)) {
                System.out.println("All specified files are exist. Ready to work...");
            } else {
                LOGGER.throwing("FileController", "main", new FileNotFoundException("Specified file or files not found!"));
            }

            for (String fileName : fileNames) {
                renameFile(DIRECTORY.resolve(fileName), DIRECTORY.resolve(suffix.concat(fileName)));
            }
            LOGGER.log(Level.WARNING, LOG_RENAMED_FINISHED);
            LOGGER.log(Level.INFO, LOG_FILE_CONTROLLER_FINISHED);
        }
        catch (FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, LOG_FILE_NOT_FOUND);
        }
        catch (IOException ex) {
            LOGGER.log(Level.SEVERE, LOG_IO_EXCEPTION);
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
        LOGGER.log(Level.WARNING, LOG_RENAMED_STARTED);
        Files.move(currentName, targetName, StandardCopyOption.REPLACE_EXISTING);
        System.out.println(currentName + " -> " + targetName);
    }
}
