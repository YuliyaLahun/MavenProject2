package classwork_day23;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;

public class MyListener implements ITestListener {

    private static final Logger LOGGER = LogManager.getLogger(MyListener.class);

    @Override
    public void onTestSuccess(ITestResult result) {

        String caseId = result.getMethod().getDescription();

        LOGGER.info(String.format("Test %s passed", caseId));
        LOGGER.info(String.format("Test passed for test" + result.getTestName()));

        MyRailReporter.report(caseId, 1, "Test passed at " +
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getEndMillis()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String caseId = result.getMethod().getDescription();

        LOGGER.info(String.format("Test %s failed", caseId));
        LOGGER.info(String.format("Test failed for test" + result.getTestName()));

        MyRailReporter.report(caseId, 5, "Test failed at " +
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getEndMillis()));

    }
}
