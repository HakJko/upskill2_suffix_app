package com.epam.ik;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.sql.Timestamp;
import java.util.*;
import java.io.*;

/**
 * developer Ihar Koshman
 *
 * 3/26/2021
 *
 * for epam UpSkill_part2, module 05
 */

public class App
{
    private static String oldNamesFiles = "";
    private static String newNamesFiles = "";
    private static final List<String> filePathArray = new ArrayList<>();
    private static String suffix = "";

    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());

    public static void main(String[] args)
    {
        StringBuilder myProperties = getMyProperties();
        LOGGER.info(String.format("\"App has been started with next properties: %s\"", myProperties));

        try {
            parsingXML();
        } catch (XMLStreamException e) {
            LOGGER.error("Occurrence of an exception when working with XMLStreamReader parser", e);
        } catch (FileNotFoundException e) {
            LOGGER.error(String.format("File not found - %s. Config cannot be read!", System.getProperty("configXML")), e);
        }

        LOGGER.info(String.format("%s was read", System.getProperty("configXML")));

        List<FileDAO> data = getData(filePathArray);
        CreatorJDOM creator = new CreatorJDOM();
        Document doc = creator.createXMLDocument(data);

        LOGGER.info("Start writing to the xml file");
        try {
            XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat().setEncoding("UTF-8"));
            FileWriter fileWriter = new FileWriter(System.getProperty("outputXML"));
            outputter.output(doc, fileWriter);
        } catch (IOException e) {
            LOGGER.error(String.format("Unable to create a file in a directory %s. Please create directory",
                    System.getProperty("outputXML")), e);
        }

        LOGGER.info("Start writing to the json file");
        CreatorGSON gsonCreator = new CreatorGSON();
        try {
            gsonCreator.writeJSON(data);
        } catch (IOException e) {
            LOGGER.error(String.format("Error in %s, check %s", "gsonCreator.writeJSON(data)",
                    System.getProperty("outputJSON")), e);
        }

        LOGGER.info("The renaming process is complete");
        LOGGER.info("FileController was terminated correctly");
        LOGGER.info(String.format("Summary information: renamed %s files, in DIRECTORY %s", filePathArray.size(),
                System.getProperty("directory")));

    }

    private static void parsingXML() throws FileNotFoundException, XMLStreamException {
        LOGGER.info(String.format("Start reading and analyzing content of %s file", System.getProperty("configXML")));
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

    public static List<FileDAO> getData(List<String> filePathArray)
    {
        List<FileDAO> daoList = new ArrayList<>();

        for (String currentPath : filePathArray) {
            File oldFile = new File(currentPath);

            if (oldFile.exists())
            {
                FileDAO myFileObj = new FileDAO();

                File newFileName = renameFiles(oldFile, currentPath);

                Timestamp myDateObj = new Timestamp(System.currentTimeMillis());
                String configPath = System.getProperty("configXML");

                myFileObj.setOldName(oldFile.getName());
                myFileObj.setNewName(newFileName.getName());
                myFileObj.setMyDateObj(myDateObj);
                myFileObj.setConfigFileName(configPath);

                daoList.add(myFileObj);

            } else {
                LOGGER.fatal(String.format("%s file didn't exist, file renaming process failed", oldFile.getName()));
            }
        }
        return daoList;
    }

    private static File renameFiles(File oldFile, String currentPath)
    {
        LOGGER.info(oldFile.getName() + " exists" );
        oldNamesFiles = oldNamesFiles.concat(" ").concat(oldFile.getName());
        File newFileName = new File(currentPath.substring(0, currentPath.lastIndexOf('.')) +
                suffix + currentPath.substring(currentPath.lastIndexOf('.')));

        boolean success = oldFile.renameTo(newFileName);

        LOGGER.info("new name " + newFileName.getName() + " was generated from " +
                oldFile.getName() +  " because of the suffix -suffix");
        newNamesFiles = newNamesFiles.concat(" ").concat(newFileName.getName());

        if (success) {
            LOGGER.info(oldFile.getName() + " renamed to " + newFileName.getName());
        } else {
            LOGGER.error(String.format("%s didn't renamed successfully", oldFile.getName()));
        }
        return newFileName;
    }

    private static StringBuilder getMyProperties()
    {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("log4j2Properties")))) {
            br.lines()
                    .forEach(sb::append);

        } catch (IOException e) {
            LOGGER.error(String.format("File not found %s", System.getProperty("log4j2Properties")), e);
        }
        return sb;
    }
}