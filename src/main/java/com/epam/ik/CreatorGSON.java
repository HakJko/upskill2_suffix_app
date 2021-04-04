package com.epam.ik;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreatorGSON {

    public void writeJSON(List<FileDAO> data) throws IOException {

        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(System.getProperty("outputJSON"));

        for (FileDAO currentFile: data) {
            writer.write(gson.toJson(currentFile));
        }
        writer.close();
    }
}