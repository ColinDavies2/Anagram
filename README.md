# Anagram

A Spring Boot application that provides an API which determines if two strings are anagrams

## Building and running
To run from the command line:

    1. Navigate to the project root folder
    2. mvn package
    3. java -jar target/anagram-0.0.1-SNAPSHOT.jar

Requests against the API can then be made using a browser, Postman or curl.

To run the unit tests from the command line:

    1. Navigate to the project root folder
    2. mvn test

After running the tests, a code coverage report can be viewed 
by opening /target/site/jacoco/index.html in a browser.

## API
**GET** http://localhost:8080/is-anagram

query parameters:

| Name               | Type                     | required |  default     |
| ------------------ | ------------------------ | -------- | ------------ |
| string-1           | String                   | Yes      | n/a          |
| string-2           | String                   | Yes      | n/a          |
| mode               | enum [<br>    alphabetical,<br>    alphanumeric,<br>    strict<br>] | No       | alphabetical |

Both strings are mandatory.  
*mode* is optional and will default to 'alphabetical' if not supplied  

## Anagram modes

__alphabetical:__ all non-alphabetical characters are removed and alphabetical characters are converted to lowercase before comparison.     
__alphanumeric:__ all non-alphanumeric characters are removed and alphabetical characters are converted to lowercase before comparison.  
__strict:__ no characters are removed or modified before comparison.  




