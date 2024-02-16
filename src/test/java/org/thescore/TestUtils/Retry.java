package org.thescore.TestUtils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//This Class allows failed tests to be retried to a specified number. i.e. in the case of flaky tests

public class Retry implements IRetryAnalyzer {
    int count = 0; // Initialize a counter to keep track of retry attempts
    int maxTry = 2; // Define the maximum number of retries allowed

    @Override
    public boolean retry(ITestResult result) {
        // Retry the test if the count is less than the maximum number of retries allowed
        if (count < maxTry) {
            count++; // Increment the retry count
            return true; // Allow retrying the test method
        }
        return false; // Stop retrying the test method if the maximum number of retries is reached
    }
}
