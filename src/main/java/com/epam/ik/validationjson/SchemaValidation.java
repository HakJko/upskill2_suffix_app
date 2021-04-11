package com.epam.ik.validationjson;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class SchemaValidation
{
    private static final File SCHEMA_FILE_OUTPUT = new File("src/main/resources/schemes/outputSchemaJSON.json");
    private static final File JSON_DATA_OUTPUT = new File("src/main/resources/output/suffixingApp.json");
    private static final File SCHEMA_FILE_CONFIG = new File("src/main/resources/schemes/configSchemaJSON.json");
    public static final File JSON_DATA_CONFIG = new File("src/main/resources/config.json");

    private static final Logger LOGGER = LogManager.getLogger(SchemaValidation.class.getName());

    public static void main(String[] args)
    {
        LOGGER.info("Start validate json files");
        try {
            checkValidConfig();
            LOGGER.info("Validate config was successful");
            checkValidOutput();
            LOGGER.info("Validate output file was successful");

        } catch (IOException | ProcessingException e) {
            LOGGER.error("Check valid json methods", e);
        }
    }

    private static void checkValidConfig()
            throws IOException, ProcessingException
    {
        if (ValidationUtils.isJsonValid(SCHEMA_FILE_CONFIG, JSON_DATA_CONFIG)){
            LOGGER.info(String.format("%s VALID %s", SCHEMA_FILE_CONFIG, JSON_DATA_CONFIG));
        }else{
            LOGGER.info(String.format("%s not valid %s", SCHEMA_FILE_CONFIG, JSON_DATA_CONFIG));
        }
    }

    private static void checkValidOutput()
            throws IOException, ProcessingException
    {
        if (ValidationUtils.isJsonValid(SCHEMA_FILE_OUTPUT, JSON_DATA_OUTPUT)){
            LOGGER.info(String.format("%s VALID %s", SCHEMA_FILE_OUTPUT, JSON_DATA_OUTPUT));
        }else{
            LOGGER.info(String.format("NOT valid! Please leave only one object in the {%s} file", JSON_DATA_OUTPUT));
        }
    }


}
