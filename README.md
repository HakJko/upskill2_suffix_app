# Upskill_p2_suffix_app
-------------------
> The app is compiled by Ihar Koshman

***

##### Module 2. SuffixingApp With Maven

It is a java application that refers to a config file and renames a set of files and renames them adding a suffix 
    specified in the same config.

##### Details:

- Application reads a json-config file on the startup
- Then it renames each file adding a suffix from the config to its name if files are exist
- It throws FileNotFoundException in the case the file or files do not exist
- It prints the results of the renaming in the following way:

_old_name -> new_name_

##### Launching

_FileController.java_

***