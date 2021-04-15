package com.epam.ik;

import java.sql.Timestamp;

public class FileDAO
{
    public static final String OLD_NAME_TAG = "oldName";
    public static final String NEW_NAME_TAG = "newName";

    private String oldName;
    private String newName;
    private Timestamp myDateObj;
    private String configFileName;

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public Timestamp getMyDateObj() {
        return myDateObj;
    }

    public void setMyDateObj(Timestamp myDateObj) {
        this.myDateObj = myDateObj;
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    @Override
    public String toString() {
        return "File{" +
                "oldName= '" + oldName + '\'' +
                ", newName= '" + newName + '\'' +
                '}';
    }
}
