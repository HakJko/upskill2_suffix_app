package com.epam.ik;

import com.epam.ik.entity.FileDAO;
import com.epam.ik.entity.LogJson;
import com.epam.ik.entity.LogJsonExc;
import com.epam.ik.service.CreatorGSON;
import com.epam.ik.service.CreatorJDOM;
import com.epam.ik.util.validationjson.SchemesValidation;
import com.epam.ik.util.validationxml.ValidatorXML;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.sql.Timestamp;
import java.util.*;
import java.io.*;

import static com.epam.ik.util.logging.MyLevels.*;

/**
 * @author
 * Ihar Koshman
 *
 * 4/14/2021
 *
 * for epam UpSkill_part2
 *
 * @version
 * module 06
 */

public class App
{
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());
    private static final List<String> FILE_PATH_ARRAY = new ArrayList<>();
    private static String oldNamesFiles = "";
    private static String newNamesFiles = "";
    private static String suffix = "";

    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss.S").create();

    public static void main(String[] args)
    {
        LOGGER.info(gson.toJson(new LogJson(
                "______________________Suffixing App starts working______________________",
                INFO)));
        LOGGER.info(gson.toJson(new LogJson(System.getProperties().toString(), INFO)));

        try {
            parsingXML();

            LOGGER.info(gson.toJson(
                    new LogJson(
                            String.format("%s was read", System.getProperty("configXML")),
                            INFO)));

            List<FileDAO> data = getData(FILE_PATH_ARRAY);
            CreatorJDOM creator = new CreatorJDOM();
            Document doc = creator.createXMLDocument(data);

            LOGGER.info(gson.toJson(
                    new LogJson(
                            "Start writing to the xml file",
                            INFO)));
            XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat().setEncoding("UTF-8"));
            FileWriter fileWriter = new FileWriter(System.getProperty("outputXML"));
            outputter.output(doc, fileWriter);

            LOGGER.info(gson.toJson(
                    new LogJson(
                            "Start writing to the json file",
                            INFO)));
            CreatorGSON gsonCreator = new CreatorGSON();
            try {
                gsonCreator.writeJSON(data);
            } catch (IOException e) {
                LOGGER.error(gson.toJson(
                        new LogJsonExc(
                                String.format("Error in %s, check %s", "gsonCreator.writeJSON(data)",
                                        System.getProperty("outputJSON")),
                                INFO,
                                e)
                ));
            }
            validateXMLFiles();
            validateJsonFiles();

        } catch (XMLStreamException e) {
            LOGGER.error(gson.toJson(
                    new LogJsonExc(
                            "Occurrence of an exception when working with XMLStreamReader parser",
                            ERROR,
                            e)
            ));

        } catch (FileNotFoundException e) {
            LOGGER.error(gson.toJson(
                    new LogJsonExc(
                            String.format("File not found - %s. Config cannot be read!",
                                    System.getProperty("configXML")),
                            ERROR,
                            e)
            ));
        } catch (IOException e) {
            LOGGER.error(gson.toJson(
                    new LogJsonExc(
                            String.format("Unable to create a file in a directory %s. Please create directory",
                                    System.getProperty("outputXML")),
                            ERROR,
                            e)
            ));
        }

        summaryLog();

    }

    public static List<FileDAO> getData(List<String> filePathArray)
    {
        List<FileDAO> daoList = new ArrayList<>();

        for (String currentPath : filePathArray) {
            File oldFile = new File(currentPath);

            if (oldFile.exists())
            {
                FileDAO fileDAO = new FileDAO();
                File newFileName = renameFiles(oldFile, currentPath);
                Timestamp myDateObj = new Timestamp(System.currentTimeMillis());
                String configPath = System.getProperty("configXML");

                fileDAO.setOldName(oldFile.getName());
                fileDAO.setNewName(newFileName.getName());
                fileDAO.setMyDateObj(myDateObj);
                fileDAO.setConfigFileName(configPath);

                daoList.add(fileDAO);

            } else {
                try {
                    throw new FileNotFoundException();
                } catch (FileNotFoundException e) {
                    LOGGER.error(gson.toJson(
                            new LogJsonExc(
                                    String.format("%s file didn't exist, file renaming process failed",
                                            oldFile.getName()),
                                    ERROR,
                                    e)
                    ));
                    System.exit(0);
                }
            }
        }
        return daoList;
    }

    private static void parsingXML()
            throws FileNotFoundException, XMLStreamException
    {
        LOGGER.info(gson.toJson(
                new LogJson(
                        String.format("Start reading and analyzing content of %s file", System.getProperty("configXML")),
                        INFO)));

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser;
        parser = factory.createXMLStreamReader(new FileInputStream(System.getProperty("configXML")));

        while (parser.hasNext()) {
            if (parser.next() == XMLStreamConstants.START_ELEMENT)
            {
                if (parser.getLocalName().equals("filePath"))
                {
                    FILE_PATH_ARRAY.add(parser.getElementText());
                }
                if (parser.getLocalName().equals("suffix"))
                {
                    suffix = parser.getElementText();
                }
            }
        }
    }

    private static File renameFiles(File oldFile, String currentPath)
    {
        LOGGER.info(gson.toJson(
                new LogJson(
                        oldFile.getName() + " exists",
                        INFO)));

        oldNamesFiles = oldNamesFiles.concat(" ").concat(oldFile.getName());
        File newFileName = new File(currentPath.substring(0, currentPath.lastIndexOf('.')) +
                suffix + currentPath.substring(currentPath.lastIndexOf('.')));

        boolean success = oldFile.renameTo(newFileName);

        LOGGER.info(gson.toJson(
                new LogJson(
                        "new name " + newFileName.getName() + " was generated from " + oldFile.getName() +
                                " because of the suffix -suffix",
                        INFO)));

        newNamesFiles = newNamesFiles.concat(" ").concat(newFileName.getName());

        if (success)
        {
            LOGGER.info(gson.toJson(
                    new LogJson(oldFile.getName() + " renamed to " + newFileName.getName(), INFO)));
        } else {
            try {
                throw new RuntimeException();
            } catch (RuntimeException e) {
                LOGGER.error(gson.toJson(
                        new LogJsonExc(
                                String.format("%s did not renamed successfully", oldFile.getName()),
                                ERROR,
                                e)
                ));
            }
            System.exit(0);
        }
        return newFileName;
    }

    private static void validateXMLFiles()
    {
        LOGGER.info(gson.toJson(
                new LogJson("ValidatorXML starts working with xml and xsd", INFO)));
        try {
            ValidatorXML.validateConfig();
            LOGGER.info(gson.toJson(
                    new LogJson("Validate configXML was successful", INFO)));

            ValidatorXML.validateOutput();
            LOGGER.info(gson.toJson(
                    new LogJson("Validate output xml file was successful", INFO)));

        } catch (SAXException e) {
            LOGGER.error(gson.toJson(
                    new LogJsonExc("Check ValidatorXML class, methods: newSchema and validate()", ERROR, e)));
        } catch (IOException e) {
            LOGGER.error(gson.toJson(
                    new LogJsonExc("File not found", ERROR, e)));
        }
    }

    private static void validateJsonFiles()
    {
        LOGGER.info(gson.toJson(
                new LogJson("Start validate json files", INFO)));
        try {
            SchemesValidation.checkValidConfig();
            SchemesValidation.checkValidOutput();
        } catch (IOException | ProcessingException e) {
            LOGGER.error(gson.toJson(
                    new LogJsonExc(
                            "Check methods and files for json",
                            ERROR,
                            e)
            ));
        }
    }

    private static void summaryLog()
    {
        LOGGER.info(gson.toJson(
                new LogJson("The renaming process is complete", INFO)));
        LOGGER.info(gson.toJson(
                new LogJson("App was terminated correctly", INFO)));
        LOGGER.info(gson.toJson(
                new LogJson(
                        String.format("Summary information: renamed %s files, in DIRECTORY %s", FILE_PATH_ARRAY.size(),
                                System.getProperty("directory")),
                        INFO)));
    }
}