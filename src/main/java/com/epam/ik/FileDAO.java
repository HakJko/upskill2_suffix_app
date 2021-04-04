package com.epam.ik;

public class FileDAO {

    private String oldName;
    private String newName;

    public static final String OLD_NAME_TAG = "oldName";
    public static final String NEW_NAME_TAG = "newName";

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

    @Override
    public String toString() {
        return "File{" +
                "oldName= '" + oldName + '\'' +
                ", newName= '" + newName + '\'' +
                '}';
    }
}
