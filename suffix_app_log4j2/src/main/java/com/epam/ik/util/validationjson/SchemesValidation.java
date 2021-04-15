package com.epam.ik.util.validationjson;

import com.epam.ik.entity.LogJson;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.epam.ik.util.logging.MyLevels.*;
import static com.epam.ik.App.gson;

public class SchemesValidation
{
    private static final File SCHEMA_FILE_OUTPUT = new File(System.getProperty("outputSchemaJSON"));
    private static final File JSON_DATA_OUTPUT = new File(System.getProperty("outputJSON"));
    private static final File SCHEMA_FILE_CONFIG = new File(System.getProperty("configSchemaJson"));
    private static final File JSON_DATA_CONFIG = new File(System.getProperty("configJSON"));

    private static final Logger LOGGER = LogManager.getLogger(SchemesValidation.class.getName());

    public static void checkValidConfig()
            throws IOException, ProcessingException
    {
        if (ValidationUtils.isJsonValid(SCHEMA_FILE_CONFIG, JSON_DATA_CONFIG))
        {
            LOGGER.info(gson.toJson(
                    new LogJson(
                            String.format("%s VALID %s", SCHEMA_FILE_CONFIG.getName(), JSON_DATA_CONFIG.getName()),
                            INFO)
            ));
        }else{
            LOGGER.info(gson.toJson(
                    new LogJson(
                            String.format("%s NOT VALID %s", SCHEMA_FILE_CONFIG.getName(), JSON_DATA_CONFIG.getName()),
                            INFO)
            ));
        }
    }

    public static void checkValidOutput()
            throws IOException, ProcessingException
    {
        if (ValidationUtils.isJsonValid(SCHEMA_FILE_OUTPUT, JSON_DATA_OUTPUT))
        {
            LOGGER.info(gson.toJson(
                    new LogJson(
                            String.format("%s VALID %s", SCHEMA_FILE_OUTPUT.getName(),
                            JSON_DATA_OUTPUT.getName()),
                            INFO)
            ));
        }else{
            LOGGER.error(gson.toJson(
                    new LogJson(
                            String.format("%s NOT VALID %s", SCHEMA_FILE_OUTPUT.getName(),
                                    JSON_DATA_OUTPUT.getName()),
                            ERROR)));
        }
    }
}
