# Upskill_p2_suffix_app
-------------------
> The app is compiled by Ihar Koshman

***

### Module 4. SuffixingApp Logging

Get Suffixing App project from Maven topic. Add logging.

##### App Specification

It is a Suffixing App - a small java application that refers to a config file and renames a set of 
    files and renames them adding a suffix specified in the same config.
    
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

##### Steps:

- Complete the project to meet specifications.

##### Launching

VM options: -Djava.util.logging.config.file=/upskill2_suffix_app/src/main/resources/log.properties

_branch - module04_ 

***