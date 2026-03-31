package Selenium_sauce_project.Sauce_website;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	public static ExtentReports set() {
		String path=System.getProperty("user.dir")+"//TestResults//TestReports.html";
		ExtentSparkReporter spark=new ExtentSparkReporter(path);
		spark.config().setReportName("Sauce Automation Test");
		spark.config().setDocumentTitle("Test Results");
		
		ExtentReports report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Tester","Waries");
		return report;
		
		
	}
}
