package com.epam.ik;

import org.jdom2.Document;
import org.jdom2.Element;

import java.util.List;

public class CreatorJDOM
{
    public CreatorJDOM() {
    }

    public Document createXMLDocument(List<FileDAO> data)
    {
        Document doc = new Document();
        Element root = new Element("suffixingApp");
        doc.addContent(root);
        for (FileDAO currentFile : data) {
            Element fileElement = new Element("file");
            root.addContent(fileElement);

            addChildElement(fileElement, FileDAO.OLD_NAME_TAG, currentFile.getOldName());
            addChildElement(fileElement, FileDAO.NEW_NAME_TAG, currentFile.getNewName());
            addChildElement(fileElement,"dateTime", currentFile.getMyDateObj().toString());
            addChildElement(fileElement, "configFileName", currentFile.getConfigFileName());

        }
        return doc;
    }

    private void addChildElement(Element root, String elementName, String textValue)
    {
        Element child = new Element(elementName);
        child.setText(textValue);
        root.addContent(child);
    }
}
