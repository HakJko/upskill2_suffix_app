package com.epam.ik.util.validationjson;

import com.epam.ik.util.logging.MyLogger;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;

import java.io.File;
import java.io.IOException;

public class SchemesValidation
{
    private static final File SCHEMA_FILE_OUTPUT = new File(System.getProperty("outputSchemaJSON"));
    private static final File JSON_DATA_OUTPUT = new File(System.getProperty("outputJSON"));
    private static final File SCHEMA_FILE_CONFIG = new File(System.getProperty("configSchemaJson"));
    private static final File JSON_DATA_CONFIG = new File(System.getProperty("configJSON"));
    private static final File SCHEMA_LOG = new File(System.getProperty("schemaLog"));
    private static final File JSON_DATA_LOG = new File(System.getProperty("JSONLog"));

    public static void checkValidConfig()
            throws IOException, ProcessingException
    {
        if (ValidationUtils.isJsonValid(SCHEMA_FILE_CONFIG, JSON_DATA_CONFIG))
        {
            MyLogger.info(String.format("%s VALID %s", SCHEMA_FILE_CONFIG.getName(),
                    JSON_DATA_CONFIG.getName()));
        }else{
            MyLogger.info(String.format("%s NOT VALID %s", SCHEMA_FILE_CONFIG.getName(),
                                                           JSON_DATA_CONFIG.getName()));
        }
    }

    public static void checkValidOutput()
            throws IOException, ProcessingException
    {
        if (ValidationUtils.isJsonValid(SCHEMA_FILE_OUTPUT, JSON_DATA_OUTPUT))
        {
            MyLogger.info(String.format("%s VALID %s", SCHEMA_FILE_OUTPUT.getName(),
                    JSON_DATA_OUTPUT.getName()));
        }else{
            MyLogger.info(String.format("%s NOT VALID %s", SCHEMA_FILE_OUTPUT.getName(),
                            JSON_DATA_OUTPUT.getName()));
        }
    }

    public static void checkValidLog()
            throws IOException, ProcessingException
    {
        if (ValidationUtils.isJsonValid(SCHEMA_LOG, JSON_DATA_LOG))
        {
            System.out.printf("%s VALID %s", SCHEMA_LOG.getName(), JSON_DATA_LOG.getName());
        }else{
            System.out.printf("%s NOT VALID %s", SCHEMA_LOG.getName(), JSON_DATA_LOG.getName());
        }
    }
}
