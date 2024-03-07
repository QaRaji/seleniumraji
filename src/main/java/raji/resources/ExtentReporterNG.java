package raji.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportNG()
	{
		//ExtentReports,ExtentSparkReporter
		String path=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter =new ExtentSparkReporter(path);
		//reporter.config().getDocumentTitle();
		reporter.config().setReportName("web automation results");
		reporter.config().setDocumentTitle("test results");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("tester","raji");
		return extent;
		
		
		
	}
}
