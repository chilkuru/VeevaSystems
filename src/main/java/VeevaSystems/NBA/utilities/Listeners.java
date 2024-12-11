package VeevaSystems.NBA.utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import VeevaSystems.NBA.resources.ExtentReporterNG;
import VeevaSystems.NBA.resources.base;

public class Listeners extends base implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Passed");
		
	}
	

	/**
	 *  Upon Test failure update the test report with stack trace and append the screenshot with the failure.  
	 */
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		// Screenshot
		extentTest.get().fail(result.getThrowable());
		WebDriver driver = null;
		String testMethodName = result.getMethod().getMethodName();

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			log.error(
					"Exception in capturing the driver instance on test failure and the trace is " + e.getStackTrace());
		}
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName, driver),
					result.getMethod().getMethodName());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Exception in capturing the screenshot on test failure and the trace is " + e.getStackTrace());

		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		// System.out.println(context.getAllTestMethods().toString());

//		for (ITestNGMethod s : context.getAllTestMethods()) {
//			//System.out.println(s.getMethodName());
//
//			if (s.getMethodName() == "fillJacketsDataForCoreProduct") {
				String csvFilePath = System.getProperty("user.dir") + "\\target\\data.csv";
				test.info("Download the file: <a href='" + csvFilePath + "'>data.csv</a>");
//				break;
//			}
//		}

		extent.flush();
	}

}
