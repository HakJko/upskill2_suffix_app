package com.epam.ik;

import java.io.File;

public class AppConversely
{
    private static final File FILE_1 = new File("src/main/resources/targetFiles/first-suffix.txt");
    private static final File FILE_2 = new File("src/main/resources/targetFiles/second-suffix.txt");

    private static final File NEW_FILE1 = new File("src/main/resources/targetFiles/first.txt");
    public static final File NEW_FILE2 = new File("src/main/resources/targetFiles/second.txt");

    public static void main(String[] args)
    {
        FILE_1.renameTo(NEW_FILE1);
        FILE_2.renameTo(NEW_FILE2);
    }
}
