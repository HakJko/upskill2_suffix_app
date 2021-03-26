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

### Module 3. Testing Quadratic Equation

Write and cover with tests a method that solves a quadratic equation.

##### Method specification

For the given quadratic equation coefficients (ax2 + bx + c = 0) return array with zero, one or two real roots
    of the equation. Roots in the array may be in any order. Specify behavior of infinite roots case by yourself.

##### Tests specification:

- Ensure that method works fine in general cases:
  - two existing roots
  - one exiting root
  - no roots
- Ensure method works correctly if some of coefficients equal zero
- Ensure method works correctly in special cases (like when all coefficients are zeros)

##### Steps:

- Complete the method
- Complete the tests (Tests may go first - TDD)

##### Launching
_
branch - module03 
folder -  testing-quadratic-equation
_

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

_branch - module04_ 

***

