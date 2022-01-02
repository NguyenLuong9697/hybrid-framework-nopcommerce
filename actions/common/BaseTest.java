package common;

import java.util.concurrent.TimeUnit;
import org.testng.Reporter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
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
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
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
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	public WebDriver getDriver() {
		return this.driver;
	}
}
