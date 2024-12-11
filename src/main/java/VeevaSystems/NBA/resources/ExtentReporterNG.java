package VeevaSystems.NBA.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	static ExtentReports extent;

	/**
	 * 
	 * Set the parameters like ReportName, Document Title, Tester Name
	 * @return
	 */
	public static ExtentReports getReportObject() {

		String path = System.getProperty("user.dir") + "\\reports\\VeevaSystemsAssignmentReports.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ragunath Chilkuru");
		return extent;

	}
}
