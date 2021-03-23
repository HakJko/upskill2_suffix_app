# Upskill_p2_suffix_app
-------------------
> The app is compiled by Ihar Koshman

***

### Module 2. SuffixingApp With Maven

It is a java application that refers to a config file and renames a set of files and renames them adding a suffix 
    specified in the same config.

##### Details:

- Application reads a json-config file on the startup
- Then it renames each file adding a suffix from the config to its name if files are exist
- It throws FileNotFoundException in the case the file or files do not exist
- It prints the results of the renaming in the following way:

_old_name -> new_name_

##### Steps:

- Create a maven project and specify its GAV settings, encoding, language level, etc.
- Add a dependency to some library for reading and parsing JSON files. (for instance, GSON)
- Write the code implementing the app specification.
- Configure maven project to build a runnable jar containing application and its dependencies.
( add plugin -> maven-assembly-plugin , write in terminal -> mvn assembly:assembly )

##### Launching

_FileController.java
    or
 java -cp target/suffixing-app-1.0-SNAPSHOT-jar-with-dependencies.jar com.epam.ik.FileController (in terminal)_

***

