package com.epam.ik.util.validationxml;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidatorXML
{
    private static final String CONFIG_XML = System.getProperty("configXML");
    private static final String CONFIG_SCHEMA_XML = System.getProperty("configSchemaXML");
    private static final String OUTPUT_XML = System.getProperty("outputXML");
    private static final String OUTPUT_SCHEMA_XML = System.getProperty("outputSchemaXML");

    public static void validateConfig()
            throws SAXException, IOException
    {
        Source sourceXML = new StreamSource(new File(CONFIG_XML));
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(CONFIG_SCHEMA_XML));
        Validator validator = schema.newValidator();
        validator.validate(sourceXML);

    }

    public static void validateOutput()
            throws SAXException, IOException
    {
        Source sourceXML = new StreamSource(new File(OUTPUT_XML));
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(OUTPUT_SCHEMA_XML));
        Validator validator = schema.newValidator();
        validator.validate(sourceXML);
    }
}
