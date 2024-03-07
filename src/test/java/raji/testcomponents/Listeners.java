package raji.testcomponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import raji.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
     ExtentReports extent=ExtentReporterNG.getReportNG();
          ExtentTest test;
          ThreadLocal<ExtentTest> extenttest=new ThreadLocal<ExtentTest>();//thread safe

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestStart(result);
		test=extent.createTest(result.getMethod().getMethodName());
		extenttest.set(test);//unique thread(errorvalidationtest)-->test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSuccess(result);
		extenttest.get().log(Status.PASS, "test success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailure(result);
		extenttest.get().fail(result.getThrowable());//it throws error msg on which method the test fails.
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			//gettestclass-->check the class name in xml page,getrealclass checks in corresponding javaclass,
			//getfield return the public member of javaclass ,get return the value of the field,
			//result.getinstance returns the instance of the running method
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filepath =null;
		//take scrnshot
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(),driver);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extenttest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());//(string path,string title)
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onFinish(context);
		extent.flush();
	}

}
