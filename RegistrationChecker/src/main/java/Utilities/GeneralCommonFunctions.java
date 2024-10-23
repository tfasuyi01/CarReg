package main.java.Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class GeneralCommonFunctions {
	
	public WebDriver driver;
	
	public GeneralCommonFunctions(WebDriver driver) {
		this.driver = driver;
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
		PageFactory.initElements(factory, this);
	}
	
	 public boolean isElementPresent(By locator) {
	     return driver.findElements(locator).size() > 0;
	 }
	 
	 public WebDriver getDriver(){
	        return driver;
	 }
	 
	 public void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
		 //Convert web driver object to TakeScreenshot
		 TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		 //Call getScreenshotAs method to create image file
		 File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		 //Move image file to new destination
		 File DestFile=new File(fileWithPath);
		 //Copy file at destination
		 FileUtils.copyFile(SrcFile, DestFile);
	 }
	 
	 public static String[] removeNullFromArray(String[] strArray) throws Exception{
		 System.out.println(Arrays.toString(strArray));
	        System.out.println(Arrays.toString(strArray) + "-Array after removing null and empty strings");

	        String[] removedNull = Arrays.stream(strArray)
	                .filter(value ->
	                        value != null && value.length() > 0
	                )
	                .toArray(size -> new String[size]);

	        System.out.println(Arrays.toString(removedNull));
	        return removedNull;
	 }
	 
	 public static String returnTimeStamp(){
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	     String strLong = Long.toString(timestamp.getTime());
	     return strLong;
	 }
	 
	 public String currentDateTimeStamp() {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
		 return dateFormat.format(new Date());
	 }
	 
	 public static String currentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(new Date());
	 }
	 
	 public static String currentSalesForceDate() {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(new Date());
	 }
	 
	 public static String addDaysToSalesForceDate(int numberOfDays) {
		 return LocalDate.now().plusDays(numberOfDays).toString();
	 }
	 
	 public String currentDateWithZeroTruncated() {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 String toDate = dateFormat.format(new Date());
		 String[] arrToDate = toDate.split("/");
		 if(arrToDate[0].startsWith("0")) {
			 arrToDate[0] = arrToDate[0].replaceAll("0", "");
		 }
		 if(arrToDate[1].startsWith("0")) {
			 arrToDate[1] = arrToDate[1].replaceAll("0", "");
		 }
		 return arrToDate[0]+"/"+arrToDate[1]+"/"+arrToDate[2];
		 
	 }
	 
	 public String currentMonthYear() {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yyyy");
		 return dateFormat.format(new Date());
	 }
	 
	 public String addDaysToDate(int numberOfDays) {
		 //return LocalDate.now().plusDays(numberOfDays);
		 String strDate = LocalDate.now().plusDays(120).toString();
		String[] splitDate = strDate.split("-");
		return(splitDate[2]+"/"+splitDate[1]+"/"+splitDate[0]);
	 }
	 
	 
	 public static String addDaysToDate_ddMMYYY(int numberOfDays) {
		 //return LocalDate.now().plusDays(numberOfDays);
		 String strDate = LocalDate.now().plusDays(numberOfDays).toString();
		String[] splitDate = strDate.split("-");
		return(splitDate[2]+"/"+splitDate[1]+"/"+splitDate[0]);
	 }


	public static void scrollUp() {
		//JavascriptExecutor js = ((JavascriptExecutor) Parq.getDriver());
		//js.executeScript("window.scrollTo(0, 0)");
	}

	public static String getResourcePath( String fileName ) {
		return new File("src/main/resources/"+fileName).getAbsolutePath();
	}

	public static String getEnclosingMethod( ) {
		StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
			return stackTraceElements[1].getMethodName();
	}
}