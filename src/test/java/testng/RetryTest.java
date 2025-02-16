package testng;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult iTestResult){
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount ++;
            return true;
        }
        return false;
    }
}
