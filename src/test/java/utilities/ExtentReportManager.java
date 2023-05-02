package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public  class ExtentReportManager implements ITestListener

{
 public ExtentSparkReporter SparkReporter;
  public ExtentReports extent;
  public ExtentTest test;
  
  String repName;
  
  
  public void onStart(ITestContext testContext)
  {
	  String timeStamp = new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
	  
	  repName="Test-Report-"+timeStamp+".html";
	  
	  SparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); // Specify location of the report
	  
	  SparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // title of the report
	  SparkReporter.config().setReportName("Pet Store User API"); // Name of the Report
	  SparkReporter.config().setTheme(Theme.DARK);
	  
	  extent = new ExtentReports();
	  
	  extent.attachReporter(SparkReporter);
	  extent.setSystemInfo("Application", "Pet Store User API");
	  extent.setSystemInfo("Operating System", System.getProperty("os.name"));
	  extent.setSystemInfo("User Name", System.getProperty("user.name"));
	  extent.setSystemInfo("Environment","QA");
	  
	  extent.setSystemInfo("User","Rajeev");
	  
  }

public void onTestStart(ITestResult result) {
	
	
	
}

public void onTestSuccess(ITestResult result) {
	
	// TODO Auto-generated method stub
	
		
		 test=extent.createTest(result.getName());
		  test.assignCategory(result.getMethod().getGroups());
		  test.createNode(result.getName());
		  test.log(Status.PASS,"Test Passed");
	
}

public void onTestFailure(ITestResult result) {
	// TODO Auto-generated method stub
	
	test=extent.createTest(result.getName());
	  test.assignCategory(result.getMethod().getGroups());
	  test.createNode(result.getName());
	   
	  test.log(Status.FAIL,"Test Failed");
	  test.log(Status.FAIL, result.getThrowable().getMessage());
	
}

public void onTestSkipped(ITestResult result) {
	// TODO Auto-generated method stub
	
	test=extent.createTest(result.getName());
	  test.assignCategory(result.getMethod().getGroups());
	  test.createNode(result.getName());
	   
	  test.log(Status.SKIP,"Test Skipped");
	  test.log(Status.SKIP, result.getThrowable().getMessage());

	
}

public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	// TODO Auto-generated method stub
	
}


public void onFinish(ITestContext context) {
	// TODO Auto-generated method stub
	
	 extent.flush();
	
}
  
   
}
  
  

