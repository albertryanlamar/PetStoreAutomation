package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;


//import org.apache.poi.hpsf.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import io.restassured.response.Response;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.response.Response;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	private static ThreadLocal<Response> responseThreadLocal = new ThreadLocal<>();// ThreadLocal variable to store the response object
	
	public void onStart(ITestContext testContext)
	{

		SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy-MM-dd");
		repName = "Test - Report -" + timeStamp.format(new Date())+".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);//specify location of the report
		
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");//title of the report
		sparkReporter.config().setReportName("Pet Store User API");//name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setTimelineEnabled(false);
		sparkReporter.config().setReportName(repName);
		sparkReporter.config().setJs("document.getElementsByClassName('logo')[0].style.display='none'");
		sparkReporter.config().setDocumentTitle("Api Report");
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Albert");
		/*
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "bert");
		*/
	}
	
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void onTestSuccess(ITestResult result)
	{
		Response response = responseThreadLocal.get();
		//test = extent.createTest(result.getName()).assignAuthor("PTS Team");
		//test.createNode(result.getName());
		test = extent.createTest(result.getMethod().getMethodName());
		//test = extent.createTest(result.getInstance().getClass().getName());
		//test.assignCategory(result.getMethod().getGroups());
		test.assignCategory(result.getInstance().getClass().getName());
		test.log(Status.PASS,"Test Passed");
		ExtentTest scenario =test.createNode("Response Body Info");
		scenario.log(Status.INFO,MarkupHelper.createCodeBlock(response.asString(), CodeLanguage.JSON));
	
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
    public static void setResponse(Response response) {
        responseThreadLocal.set(response);
    }
}
