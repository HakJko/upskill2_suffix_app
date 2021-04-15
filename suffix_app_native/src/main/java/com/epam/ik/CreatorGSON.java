package com.epam.ik;

import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.util.List;

public class CreatorGSON
{
    public CreatorGSON() {
    }

    public void writeJSON(List<FileDAO> data)//Streaming API: pluses performance and memory, disadvantage hard to use
            throws IOException
    {
        JsonWriter writer = new JsonWriter(new FileWriter(System.getProperty("outputJSON")));
        writer.setIndent("  ");
        writer.beginObject().name("suffixingApp").beginArray();

        for (FileDAO currentFile: data) {
            writer.beginObject();
            writer.name("oldName").value(currentFile.getOldName());
            writer.name("newName").value(currentFile.getNewName());
            writer.name("dateTime").value(String.valueOf(currentFile.getMyDateObj()));
            writer.name("configFileName").value(currentFile.getConfigFileName());
            writer.endObject();
        }
        writer.endArray().endObject();
        writer.close();
    }

}