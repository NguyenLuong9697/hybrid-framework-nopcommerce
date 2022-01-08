package common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;


import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import factoryEnvironment.BrowserStackFactory;
import factoryEnvironment.CrossbrowserFactory;
import factoryEnvironment.GridLocalFactory;
import factoryEnvironment.LocalFactory;
import factoryEnvironment.SauceLabFactory;

public class BaseTest {

	private WebDriver driver;
	protected final Log log;
	
	protected BaseTest() {
		log=LogFactory.getLog(getClass());
	}
	
	protected WebDriver getBrowserDriver(String envName, String urlValue, String browserName, String ipAddress, String  portNumber, String osName, String osVersion) {
		
		switch (envName) {
		case "local": 
			driver = new LocalFactory(browserName).createDriver();
			break;
			
		case "grid":
			driver = new GridLocalFactory(browserName, ipAddress, portNumber).createDriver();
			break;
		
		case "browserStack":
			driver = new BrowserStackFactory(browserName, osName, osVersion).createDriver();
			break;
			
		case "saucelab":
			driver = new SauceLabFactory(browserName, osName).createDriver();
			break;
		
		case "cross":
			driver =new CrossbrowserFactory(browserName, osName).createDriver();
			break;
		
		default:
			driver = new LocalFactory(browserName).createDriver();
			break;
		
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(urlValue);
		return driver;
		
	}
	public WebDriver getDriver() {
		return this.driver;
	}
	protected void cleanBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + osName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}
			//Browser
			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info("Close browser and clean excutable driver:"+e.getMessage());
		} finally {
			try {
				//Excutable driver
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int randomNumber() {
		Random rd=new Random();
		return rd.nextInt(1000);
		
	}
	public String getDatTimeNow() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
		int day = now.get(Calendar.DAY_OF_MONTH);
		return month+"/"+day+"/"+year;
	}
	private boolean checkTrue(boolean condition) {
		
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
				//ExtentTestManager.getTest().log(LogStatus.INFO, "-------------------------- PASSED -------------------------- ");
			} else {
				//log4j
				log.info(" -------------------------- FAILED -------------------------- ");
				attachScreenShotToReportNG();
				//extent
				//ExtentTestManager.getTest().log(LogStatus.INFO, "-------------------------- FAILED -------------------------- ");
				//tu them
				//attchScreenShotToExtentReport();
				
				//
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			//tu them
			//attachScreenShotToReport();
			//
			attachScreenShotToReportNG();
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
				//ExtentTestManager.getTest().log(LogStatus.INFO, "-------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
				attachScreenShotToReportNG();
				//ExtentTestManager.getTest().log(LogStatus.INFO, "-------------------------- FAILED -------------------------- ");
				//tu them
				//attchScreenShotToExtentReport();
				//tu them
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			//tu them
			//attachScreenShotToReport();
			//
			attachScreenShotToReportNG();
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
			//ExtentTestManager.getTest().log(LogStatus.INFO, "-------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			attachScreenShotToReportNG();
			//ExtentTestManager.getTest().log(LogStatus.INFO, "-------------------------- FAILED -------------------------- ");
			//tu them
			//attchScreenShotToExtentReport();
			
			//
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			//tu them
			//attachScreenShotToReport();
			//
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	public void attachScreenShotToReportNG() {
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		String screenshotPath = captureScreenshot(driver,"FAIL");
		//Reporter.getCurrentTestResult();
		Reporter.log("<br><a target=\"_blank\" href=\"file:///" + screenshotPath + "\">" + "<img src=\"file:///" + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
		//Reporter.setCurrentTestResult(null);
	}
	public String captureScreenshot(WebDriver driver, String screenshotName) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenPath = System.getProperty("user.dir") + "\\screenshotReportNG\\" + screenshotName + "_" + formater.format(calendar.getTime()) + ".png";
			FileUtils.copyFile(source, new File(screenPath));
			return screenPath;
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
			return e.getMessage();
		}
	}
	@BeforeTest
	public void deleteAllFilesInReportNGScreenshot() {
		log.info("---------- START delete file in folder ----------");
		deleteAllFileInFolder();
		log.info("---------- END delete file in folder ----------");
	}

	public void deleteAllFileInFolder() {
		try {
			String workingDir = System.getProperty("user.dir");
			String pathFolderDownload = workingDir + "\\screenshotReportNG";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					log.info(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}



}
