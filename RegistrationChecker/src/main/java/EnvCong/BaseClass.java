package main.java.EnvCong;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import main.java.Utilities.Driver;
import main.java.Utilities.GeneralCommonFunctions;
import main.java.Utilities.Parq;
import main.java.Utilities.PropertiesManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import static main.java.Utilities.PropertiesManager.getProperty;

public class BaseClass extends Instantiation {

    public WebDriver driver;

    protected GeneralCommonFunctions genComFunc;
    protected String currentTimeStamp, screenshotFileName;
    String projectPath = System.getProperty("user.dir");
    String screenshotPath = projectPath + "\\test-output\\Screenshots\\";

    public static ExtentReports report;
    protected static ExtentTest logger;

    @BeforeSuite
    public void initializeTestBaseSetup() throws IOException {
        System.out.println("begin");
        try {
            PropertiesManager.initializeProperties();
            driver = Driver.buildDriver(getProperty("browser.type"));
            driver.navigate().to(getProperty("car.url"));



           // driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
           // driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            Parq.setDriver(driver);
            Parq.focus(Parq.text("Accept all cookies"));
            Parq.touch();
        } catch (Exception e) {
            System.out.println("BaseClass : Error....." + e.getStackTrace());
        }

        genComFunc = new GeneralCommonFunctions(driver);
        currentTimeStamp = genComFunc.currentDateTimeStamp();



        report = new ExtentReports(projectPath + "/test-output/Reports/TestReport_" + reportName.get() +  "_" + currentTimeStamp + ".html");
        report.loadConfig(new File(projectPath + "/src/main/resources/extent-config.xml"));
        report.addSystemInfo("<b>CC Env", "<b>" + envName.get());
        report.addSystemInfo("<b>CC URL", "<b>" + ccURL.get());
        report.addSystemInfo("<b>Browser", "<b>" + browserType.get());
        //logger = report.startTest("Starting Test Suite.....");



    }

    @BeforeMethod
    public void cleanStart(Method testMethod) {
        System.out.println(testMethod.getName());
        logger = report.startTest(testMethod.getName());

        //String testName = testMethod.getName().substring(0, 7);
        //logger.assignCategory(getProperty(testName));

        System.out.println("------------xxxxxxxxxxxx-------------Start Test : " + testMethod.getName());
    }

    @AfterMethod
	public void tearDown(ITestResult testResult, Method testMethod) throws Exception {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "<b>TestScenario", testResult.getThrowable());
			String screenshotFileName = testResult.getName() + "_" + currentTimeStamp + ".jpg";
			logger.log(LogStatus.FAIL, "<b>TestScenario", "Screenshot location:-"+screenshotPath + screenshotFileName);
			logger.log(LogStatus.FAIL, "<b>TestScenario", "Screenshot attached:-"+logger.addScreenCapture(screenshotPath + screenshotFileName));
		}
		else if (testResult.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "<b>TestScenario", "<b>'"+testMethod.getName()+"' Successfully Passed");
		}

		report.endTest(logger);
		report.flush();
		System.out.println("------------xxxxxxxxxxxx-------------End Test : " + testMethod.getName());
	}

    @AfterClass
    public void endReport() throws InterruptedException {
      driver.quit();
      report.flush();

    }

    @AfterSuite
    public void AfterSuite() throws InterruptedException {
   	 report.flush();
     report.close();
    }

}