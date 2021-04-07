package com.epam.ik;

import org.jdom2.Document;
import org.jdom2.Element;

import java.util.List;
import java.time.LocalDateTime;

public class CreatorJDOM {

    private static int count;

    public CreatorJDOM() {
    }

    public Document createXMLDocument(List<FileDAO> data){
        
        LocalDateTime myDateObj = LocalDateTime.now();

        Document doc = new Document();
        Element root = new Element("suffixingApp");
        doc.addContent(root);
        for (FileDAO currentFile : data) {
            Element fileElement = new Element("file");
            root.addContent(fileElement.setAttribute("id", String.format("%s", ++count)));

            addChildElement(fileElement, FileDAO.OLD_NAME_TAG, currentFile.getOldName());
            addChildElement(fileElement, FileDAO.NEW_NAME_TAG, currentFile.getNewName());
            addChildElement(fileElement,"DateTime", String.valueOf(myDateObj));
            addChildElement(fileElement, "ConfigFileName", System.getProperty("configXML"));

        }
        return doc;
    }

    private void addChildElement(Element parent, String elementName, String textValue) {
        Element child = new Element(elementName);
        child.setText(textValue);
        parent.addContent(child);
    }
}
