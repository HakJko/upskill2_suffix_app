package com.epam.ik;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidatorXML {

    public static void validate() throws SAXException, IOException {
        Source sourceXML = new StreamSource(new File(System.getProperty("configXML")));
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(System.getProperty("schemaXML")));
        Validator validator = schema.newValidator();
        validator.validate(sourceXML);

    }

}
