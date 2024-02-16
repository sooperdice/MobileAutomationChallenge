# The Score QA Automation Engineer Mobile Challenge

##### Author: [Ashwin Gavai]
...

## Project Overview


This project automates testing for The Score android native application, covering user onboarding, team discovery and page navigation scenarios.

It adopts a robust testing approach, integrating key methodologies like the Page Object Model (POM), inheritance, encapsulation, and thorough end-to-end and negative testing. POM ensures effective organization and abstraction of UI complexities, enhancing maintainability and readability. Inheritance, exemplified by the BaseTest and AppiumUtils classes, fosters code reuse and consistency across tests, while encapsulation in POM classes promotes clean separation of concerns for easier maintenance. Additionally, interfaces (Listeners) augment flexibility and extensibility, facilitating seamless integration of new functionalities and test cases. TestNG and Extent Reports are instrumental in executing tests efficiently and generating comprehensive reports, providing detailed insights into results and expediting issue resolution.

## Test Specification

A  test specification for the main scenario implemented can be found [here](docs/Test_Specification.md).

## Rationale and Coverage Assessment

The testing approach aims for comprehensive coverage while ensuring maintainability and reliability. Leveraging POM, inheritance, encapsulation, and interfaces promotes code reuse, consistency, and flexibility.
Tests are parameterized to run with different data sets, enhancing coverage by testing various scenarios and inputs. This approach validates the application's behavior under diverse conditions, ensuring its robustness.
End-to-end testing validates functionality across multiple components, while specific bug tests highlight areas for improvement and ensure thorough quality assurance.


## Prerequisites

### System Requirements:

*	node: '18.18.2'
*	npm: '9.8.1',
*	node: '18.18.2'
*	appium: 2.2.1 with uiautomator2 2.32.3
*	Java21 (should work with Java 11)
*	Android Debug Bridge version 1.0.41
*	Android Device Emulator 

### Tools Used:

*  	Android Studio  
*  	Appium Inspector
*  	Eclipse


## Setup and Installation

### Instructions for setting up and running the project :

1. Clone the repository: git clone <repository-url>
2. Update data.properties file: Navigate to the src/main/java directory and open the data.properties file. Update the file with the appropriate values for your environment. This file contains configurations such as IP address, port, Android device name, and Appium JS file path.




To run in command line(maven)
1. Open Terminal/Command prompt
2. Navigate to project directory e.g C:\Users\bungh\eclipse-workspace\TheScore\TheScoreTest
3. Run the following command:

```bash
mvn test -PRegression
```



To run in eclipse or other IDE
1. Right Click TheScoreTest\testng.xml > run as TestNG Suite
2. Select "Run As" > "TestNG Suite"


## 	Results and Reports

### TestNG Results Report:

To view TestNG results, open the following project file in a browser:
/TheScoreTest/test-output/emailable-report.html

[Or click here](target/surefire-reports/emailable-report.html)



### ExtentReports Results Report:

To view ExtentReports, open the following project file in a browser:
/TheScoreTest/reports/index.html
Click On test case to view test specification.

[Or Click here](reports/index.html)
