package com.epam.ik.util.logging;

import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;

import static com.epam.ik.util.logging.MyEnumLevels.*;

public class MyLogger
{
    private static final String LOG_FILE = "src/main/resources/logging/JSONLog.json";
    public static JsonWriter jsonWriter;

    static {
        try {
            jsonWriter = new JsonWriter(new FileWriter(LOG_FILE));//true in fileWriter for pre-recording
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void info(String message)
    {
        try {
            jsonWriter.beginObject();
            jsonWriter.name("dateTime").value(String.valueOf(getDateTime()));
            jsonWriter.name("message").value(message);
            jsonWriter.name("severityLabel").value(String.valueOf(INFO));
            jsonWriter.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void error(String message, Exception e)
    {
        try {
            jsonWriter.beginObject();
            jsonWriter.name("dateTime").value(String.valueOf(getDateTime()));
            jsonWriter.name("message").value(message);
            jsonWriter.name("severityLabel").value(String.valueOf(ERROR));
            jsonWriter.name("error").value(getStackTrace(e));
            jsonWriter.endObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void error(Exception e)
    {
        try {
            jsonWriter.beginObject();
            jsonWriter.name("dateTime").value(String.valueOf(getDateTime()));
            jsonWriter.name("message").value(e.getMessage());
            jsonWriter.name("severityLabel").value(String.valueOf(ERROR));
            jsonWriter.name("error").value(getStackTrace(e));
            jsonWriter.endObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static Timestamp getDateTime()
    {
        Timestamp dateTime = new Timestamp(System.currentTimeMillis());
        return dateTime;
    }

    private static String getStackTrace(final Throwable throwable)
    {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        System.out.println(sw.getBuffer().toString());
        return sw.getBuffer().toString();
    }

}
