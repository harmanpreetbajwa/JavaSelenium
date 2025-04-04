package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	

	public static ExtentReports configExtentReports() {
		
		String report_dir_path = System.getProperty("user.dir") + "//ExtentReports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(report_dir_path); // to config, like name etc.
		reporter.config().setDocumentTitle("Selenium Test Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
		
	}
}
