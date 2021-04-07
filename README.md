# Upskill_p2_suffix_app
-------------------
> The app is compiled by Ihar Koshman

***

### Module 5. SuffixingApp Structured Logging Specification

Get Suffixing App project from Logging topic. Specify structure of its input and output

##### App Specification

It is a Suffixing App - a small java application that refers to a config file and renames a set of files 
    and renames them adding a suffix specified in the same config.

_Changes: config file now should be an XML file._
    
##### Details:

- Application should read a config file on the startup
- Then it should ensure that all of files from the config exist
- Then it should rename each file adding a suffix from the config to its name

##### Logging Specification:

- Application should log startup information.
- Application should log information on config read.
- Application should log renaming process information.
- Application should log summary information.
- Application should log shutdown information.
- Application should handle and log possible errors.

_Use different logging level. All log entries should contain a date and time information as well._

##### Changes:

- When renaming is finished the application should print a document of completed actions.
    Document should be XML-based. It should contain:
    - config file name
    - execution time
    - list of files with old and new names
- All the logging entries from previous exercise should become JSON document of some structure. They should contain:
    - date and time
    - message
    - severity label
    - error info, if its error

##### Steps:

- Specify structure of the following documents:
    - Config file
    - Completed actions document
    - Log entries.

##### Launching

VM options:  -Dlog4j2Properties=src/main/resources/log4j2.properties -Ddirectory=src/main/resources -DconfigXML=src/main/resources/config.xml -DoutputXML=src/main/resources/output/suffixingApp.xml -DoutputJSON=src/main/resources/output/suffixingApp.json -DschemaXML=src/main/resources/schemes/schemaXML.xsd

_branch - module05_ 

***

