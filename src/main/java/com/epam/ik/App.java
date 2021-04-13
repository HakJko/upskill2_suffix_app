package com.epam.ik;

import com.epam.ik.util.logging.MyLogger;
import com.epam.ik.util.validationjson.SchemesValidation;
import com.epam.ik.util.validationxml.ValidatorXML;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
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

import static com.epam.ik.util.logging.MyLogger.jsonWriter;

/**
 * developer Ihar Koshman
 *
 * 4/13/2021
 *
 * for epam UpSkill_part2, module 05
 */

public class App
{
    private static String oldNamesFiles = "";
    private static String newNamesFiles = "";
    private static final List<String> filePathArray = new ArrayList<>();
    private static String suffix = "";

    public static void main(String[] args)
    {
        startLogging();
        MyLogger.info("===================Suffixing App starts working===================");
        MyLogger.info(System.getProperties().toString());

        try {
            parsingXML();
        } catch (XMLStreamException e) {
            MyLogger.error("Occurrence of an exception when working with XMLStreamReader parser", e);
        } catch (FileNotFoundException e) {
            MyLogger.error(String.format("File not found - %s. Config cannot be read!", System.getProperty("configXML")), e);
        }

        MyLogger.info(String.format("%s was read", System.getProperty("configXML")));

        List<FileDAO> data = getData(filePathArray);
        CreatorJDOM creator = new CreatorJDOM();
        Document doc = creator.createXMLDocument(data);

        MyLogger.info("Start writing to the xml file");
        try {
            XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat().setEncoding("UTF-8"));
            FileWriter fileWriter = new FileWriter(System.getProperty("outputXML"));
            outputter.output(doc, fileWriter);
        } catch (IOException e) {
            MyLogger.error(String.format("Unable to create a file in a directory %s. Please create directory",
                    System.getProperty("outputXML")), e);
        }

        MyLogger.info("Start writing to the json file");
        CreatorGSON gsonCreator = new CreatorGSON();
        try {
            gsonCreator.writeJSON(data);
        } catch (IOException e) {
            MyLogger.error(String.format("Error in %s, check %s", "gsonCreator.writeJSON(data)",
                    System.getProperty("outputJSON")), e);
        }



        MyLogger.info("The renaming process is complete");
        MyLogger.info("FileController was terminated correctly");
        MyLogger.info(String.format("Summary information: renamed %s files, in DIRECTORY %s", filePathArray.size(),
                System.getProperty("directory")));

        MyLogger.info("ValidatorXML starts working with xml and xsd");
        try {
            ValidatorXML.validateConfig();
            MyLogger.info("Validate config was successful");
            ValidatorXML.validateOutput();
            MyLogger.info("Validate output file was successful");

        } catch (SAXException e) {
            MyLogger.error("Check ValidatorXML class, methods: newSchema and validate()", e);
        } catch (IOException e) {
            MyLogger.error("File not found", e);
        }

        MyLogger.info("Start validate json files");
        try {
            SchemesValidation.checkValidConfig();
            MyLogger.info("Validate config was successful");
            SchemesValidation.checkValidOutput();
            MyLogger.info("Validate output file was successful");

            closeJsonWriter();

            SchemesValidation.checkValidLog();
        } catch (IOException | ProcessingException e) {
            e.printStackTrace();
        }
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
                MyLogger.error(String.format("%s file didn't exist, file renaming process failed", oldFile.getName()),
                        new NullPointerException());
            }
        }
        return daoList;
    }

    public static void startLogging()
    {
        jsonWriter.setIndent("  ");
        try {
            jsonWriter.beginObject().name("log");
            jsonWriter.beginArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeJsonWriter()
    {
        try {
            jsonWriter.endArray();
            jsonWriter.endObject();
            jsonWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parsingXML()
            throws FileNotFoundException, XMLStreamException
    {
        MyLogger.info(String.format("Start reading and analyzing content of %s file", System.getProperty("configXML")));
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser;
        parser = factory.createXMLStreamReader(new FileInputStream(System.getProperty("configXML")));

        while (parser.hasNext()) {
            if (parser.next() == XMLStreamConstants.START_ELEMENT) {
                if (parser.getLocalName().equals("filePath")) {
                    filePathArray.add(parser.getElementText());
                }
                if (parser.getLocalName().equals("suffix")) {
                    suffix = parser.getElementText();
                }
            }
        }
    }

    private static File renameFiles(File oldFile, String currentPath)
    {
        MyLogger.info(oldFile.getName() + " exists" );
        oldNamesFiles = oldNamesFiles.concat(" ").concat(oldFile.getName());
        File newFileName = new File(currentPath.substring(0, currentPath.lastIndexOf('.')) +
                suffix + currentPath.substring(currentPath.lastIndexOf('.')));

        boolean success = oldFile.renameTo(newFileName);

        MyLogger.info("new name " + newFileName.getName() + " was generated from " +
                oldFile.getName() +  " because of the suffix -suffix");
        newNamesFiles = newNamesFiles.concat(" ").concat(newFileName.getName());

        if (success) {
            MyLogger.info(oldFile.getName() + " renamed to " + newFileName.getName());
        } else {
            MyLogger.error(String.format("%s didn't renamed successfully", oldFile.getName()),
                    new RuntimeException());
        }
        return newFileName;
    }
}