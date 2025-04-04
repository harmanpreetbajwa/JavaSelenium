package SeleniumByHarman.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.configExtentReports();
	
	public void onTestStart(ITestResult result) {
	  test = extent.createTest(result.getMethod().getMethodName());
	  
	}

  /**
   * Invoked each time a test succeeds.
   *
   * @param result <code>ITestResult</code> containing information about the run test
   * @see ITestResult#SUCCESS
   */
	public void onTestSuccess(ITestResult result) {
		// not implemented
		test.log(Status.PASS, "Test Passed");
	}

  /**
   * Invoked each time a test fails.
   *
   * @param result <code>ITestResult</code> containing information about the run test
   * @see ITestResult#FAILURE
   */
	public void onTestFailure(ITestResult result) {
    // not implemented
		test.fail(result.getThrowable());
		
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String screenshotPath = getScreenShot(result.getMethod().getMethodName(), driver);
			test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

  /**
   * Invoked each time a test is skipped.
   *
   * @param result <code>ITestResult</code> containing information about the run test
   * @see ITestResult#SKIP
   */
	public void onTestSkipped(ITestResult result) {
    // not implemented
	}

  /**
   * Invoked each time a method fails but has been annotated with successPercentage and this failure
   * still keeps it within the success percentage requested.
   *
   * @param result <code>ITestResult</code> containing information about the run test
   * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
   */
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    // not implemented
  }

  /**
   * Invoked each time a test fails due to a timeout.
   *
   * @param result <code>ITestResult</code> containing information about the run test
   */
  public void onTestFailedWithTimeout(ITestResult result) {
    onTestFailure(result);
  }

  /**
   * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt;
   * tag and calling all their Configuration methods.
   *
   * @param context The test context
   */
  public void onStart(ITestContext context) {
    // not implemented
  }

  /**
   * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have
   * run and all their Configuration methods have been called.
   *
   * @param context The test context
   */
  public void onFinish(ITestContext context) {
	  extent.flush();
	  }

}
