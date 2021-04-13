# Upskill_p2_suffix_app
-------------------
> The app is compiled by Ihar Koshman

***

### Module 5  . SuffixingApp Structured Logging Specification

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

VM options App:  
-Ddirectory=src/main/resources/targetFiles
-DconfigXML=src/main/resources/config.xml
-DconfigSchemaXML=src/main/resources/schemes/configSchemaXML.xsd
-DoutputXML=src/main/resources/output/suffixingApp.xml
-DoutputSchemaXML=src/main/resources/schemes/outputSchemaXML.xsd
-DoutputJSON=src/main/resources/output/suffixingApp.json
-DoutputSchemaJSON=src/main/resources/schemes/outputSchemaJSON.json
-DJSONLog=src/main/resources/logging/JSONLog.json
-DschemaLog=src/main/resources/schemes/JsonLogSchema.json
-DconfigJSON=src/main/resources/config.json
-DconfigSchemaJson=src/main/resources/schemes/configSchemaJSON.json
-DschemaJSONv4="http://json-schema.org/draft-04/schema#"
-DschemaJSONv4_Id_elem="$schema"

App - main class Suffixing app
AppConversely - inversely proportional App

***