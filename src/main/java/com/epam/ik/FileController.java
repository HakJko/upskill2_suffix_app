package com.epam.ik;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.*;

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
            Handler fileHandler = new FileHandler(DIRECTORY.resolve("myJavaLog.log").toString());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            LOGGER.severe(String.format("Exception for FileHandler: %s", DIRECTORY.resolve("myJavaLog.log").toString()));
        }

        StringBuilder myProperties = getMyProperties();
        LOGGER.info(String.format("FileController has been started with next properties: %s", myProperties));

        Gson gson = new Gson();

        try (FileReader fileReader = new FileReader(CONFIG.toString())) {
            JsonReader jsonReader = new JsonReader(fileReader);
            Configuration configuration = gson.fromJson(jsonReader, Configuration.class);

            LOGGER.info(String.format("'%s' has been read", CONFIG.toString()));

            String suffix = configuration.getSuffix();
            String[] fileNames = configuration.getFileNames();

            if (checkFilesExisting(fileNames)) {
                LOGGER.info("All specified files are exist. Ready to work...");
            } else {
                LOGGER.warning(String.format("Specified file or files not found in %s!", DIRECTORY));
            }

            for (String fileName : fileNames) {
                renameFile(DIRECTORY.resolve(fileName), DIRECTORY.resolve(suffix.concat(fileName)));
            }

            LOGGER.info("The renaming process is complete");
            LOGGER.info("FileController was terminated correctly");

            LOGGER.info(String.format("Summary information: renamed %s files, in DIRECTORY %s", fileNames.length, DIRECTORY));

        } catch (FileNotFoundException e) {
            LOGGER.severe("File not found" + CONFIG.toString());
        } catch (IOException e) {
            LOGGER.severe("Files cannot be renamed in method renameFile" );
        }

    }

    private static boolean checkFilesExisting(String[] fileNames) {
        LOGGER.info("Checking for files in the method checkFilesExisting has started");
        for (String fileName : fileNames) {
            if (Files.notExists(DIRECTORY.resolve(fileName))) {
                return false;
            }
        }
        return true;
    }

    private static void renameFile(Path currentName, Path targetName) throws IOException {
        LOGGER.info("The renaming process has begun");
        Files.move(currentName, targetName, StandardCopyOption.REPLACE_EXISTING);
        LOGGER.info(currentName + " -> " + targetName);
    }

    private static StringBuilder getMyProperties(){
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(DIRECTORY.resolve("log.properties").toString()))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("  ");
            }
        } catch (IOException e) {
            LOGGER.severe("File not found" + DIRECTORY.resolve("log.properties").toString());
        }
        return sb;
    }
}
