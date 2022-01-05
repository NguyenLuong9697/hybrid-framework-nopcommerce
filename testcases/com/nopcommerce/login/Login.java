package com.nopcommerce.login;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import common.BaseTest;
import payObjects.HomePageObject;
import payObjects.LoginPageObject;
import payObjects.PageGeneratorManager;
import payObjects.RegisterPageObject;
import serverConfig.IServer;
import utilities.DataUtil;

public class Login extends BaseTest {

	WebDriver driver;
	IServer server;
	HomePageObject homePageObject;
	LoginPageObject loginPageObject;
	RegisterPageObject registerPageObject;
	DataUtil data;
	String firstName, lastName, fullName, emailAddress, password, confirmPassword;

	@Parameters({ "environment", "server", "browser", "ipAddress", "portNumber", "osName", "osVersion" })
	@BeforeClass
	public void beforeClass(@Optional("local") String environment, @Optional("testing") String serverName,
			@Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("windows") String osName, @Optional("10") String osVersion) {

		ConfigFactory.setProperty("server", serverName);
		server = ConfigFactory.create(IServer.class);
		log.info("Preconditon 01: Open browser " + browserName + " and navigate to " + server.url());
		driver = getBrowserDriver(environment, server.url(), browserName, ipAddress, portNumber, osName, osVersion);
		data = DataUtil.getData();
		homePageObject = PageGeneratorManager.getHomePageObject(driver);

		log.info("Precondition 02: Click to Log in link");
		homePageObject.clickToHeaderLinkByLabelName(driver, "Log in");
		loginPageObject = PageGeneratorManager.getLoginPageObject(driver);
	}

	@Test
	public void TC_01_Login_Empty_Data() {
		log.info("TC_01_Login_Empty_Data - Step 01: Enter to email address: Empty data");
		loginPageObject.enterToTextboxByID(driver, "Email", "");

		log.info("TC_01_Login_Empty_Data - Step 02: Enter to password: Empty data");
		loginPageObject.enterToTextboxByID(driver, "Password", "");

		log.info("TC_01_Login_Empty_Data - Step 03: Click Log in button");
		loginPageObject.clickToButtonByLabelName(driver, "Log in");
		
		log.info("TC_01_Login_Empty_Data - Step 04: verify error message email  ");
		verifyEquals(loginPageObject.getErrorMessageByID(driver, "Email-error"), "Please enter your email");

	}

	@Test
	public void TC_02_Login_Invalid_Email() {

		log.info("TC_02_Login_Invalid_Email- Step 01: Click to Log in link");
		loginPageObject.clickToHeaderLinkByLabelName(driver, "Log in");

		emailAddress="123456";
		log.info("TC_02_Login_Invalid_Email - Step 02: Enter to Email: "+ emailAddress);
		loginPageObject.enterToTextboxByID(driver, "Email", emailAddress);

		log.info("TC_02_Login_Invalid_Email - Step 03: Enter to password: Empty data");
		loginPageObject.enterToTextboxByID(driver, "Password", "");
		
		log.info("TC_02_Login_Invalid_Email - Step 04: verify error message email ");
		verifyEquals(loginPageObject.getErrorMessageByID(driver, "Email-error"), "Wrong email");

}

	@Test
	public void TC_03_Login_Unregistered_Email() {
		
		log.info("TC_03_Login_Unregistered_Email- Step 01: Click to Log in link");
		loginPageObject.clickToHeaderLinkByLabelName(driver, "Log in");

		emailAddress= data.getEmailAddress();
		log.info("TC_03_Login_Unregistered_Email - Step 02: Enter to Email: "+ emailAddress);
		loginPageObject.enterToTextboxByID(driver, "Email", emailAddress);

		password="123456";
		log.info("TC_03_Login_Unregistered_Email - Step 03: Enter to password: " + password);
		loginPageObject.enterToTextboxByID(driver, "Password", password);
		
		log.info("TC_03_Login_Unregistered_Email - Step 04: Click Log in button");
		loginPageObject.clickToButtonByLabelName(driver, "Log in");
		
		log.info("TC_03_Login_Unregistered_Email - Step 05: verify error message ");
		verifyEquals(loginPageObject.getErrorMessage(),"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	@Test
	public void TC_04_Login_Valid_Email_EmptyPassword() {
		log.info("TC_04_Login_Valid_Email_EmptyPassword- Precondition 01: Click to Register link");
		loginPageObject.clickToHeaderLinkByLabelName(driver, "Register");
		registerPageObject = PageGeneratorManager.getRegisterPageObject(driver);
		
		firstName=data.getFirstName();
		log.info("TC_04_Login_Valid_Email_EmptyPassword- Precondition 02: Enter to First Name: "+ firstName);
		registerPageObject.enterToTextboxByID(driver, "FirstName", firstName);

		lastName =data.getLastName();
		log.info("TC_04_Login_Valid_Email_EmptyPassword - Precondition 03: Enter to Last Name: " + lastName);
		registerPageObject.enterToTextboxByID(driver, "LastName", lastName);

		emailAddress=data.getEmailAddress();
		log.info("TC_04_Login_Valid_Email_EmptyPassword - Precondition 04: Enter to Email: "+ emailAddress);
		registerPageObject.enterToTextboxByID(driver, "Email", emailAddress);

		password="123456";
		log.info("TC_04_Login_Valid_Email_EmptyPassword - Precondition 05: Enter to Password: "+ password);
		registerPageObject.enterToTextboxByID(driver, "Password", password);
	
		confirmPassword="123456";
		log.info("TC_04_Login_Valid_Email_EmptyPassword - Precondition 06: Enter to Confirm password: "+ confirmPassword);
		registerPageObject.enterToTextboxByID(driver, "ConfirmPassword", confirmPassword);
		
		log.info("TC_04_Login_Valid_Email_EmptyPassword - Precondition 07: Click to Register button");
		registerPageObject.clickToButtonByLabelName(driver, "Register");	

		log.info("TC_04_Login_Valid_Email_EmptyPassword - Precondition 08: verify register message successful");
		verifyEquals(registerPageObject.getRegisterMessageSuccessfull(),"Your registration completed");
		
		log.info("TC_04_Login_Valid_Email_EmptyPassword - Precondition 09: Click to Logout");
		registerPageObject.clickToHeaderLinkByLabelName(driver, "Log out");
		homePageObject = PageGeneratorManager.getHomePageObject(driver);
		
		log.info("TC_04_Login_Valid_Email_EmptyPassword- Precondition 10: Click to Log in link");
		homePageObject.clickToHeaderLinkByLabelName(driver, "Log in");
		loginPageObject = PageGeneratorManager.getLoginPageObject(driver);
		
		log.info("TC_04_Login_Valid_Email_EmptyPassword - Step 01: Enter to Email: "+ emailAddress);
		loginPageObject.enterToTextboxByID(driver, "Email", emailAddress);

		
		log.info("TC_04_Login_Valid_Email_EmptyPassword - Step 04: Enter to password:Empty ");
		loginPageObject.enterToTextboxByID(driver, "Password","");
		
		log.info("TC_04_Login_Valid_Email_EmptyPassword - Step 05: Click Log in button");
		loginPageObject.clickToButtonByLabelName(driver, "Log in");
		
		log.info("TC_04_Login_Valid_Email_EmptyPassword  - Step 06: verify error message ");
		verifyEquals(loginPageObject.getErrorMessage(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
	}
	@Test
	public void TC_05_Login_With_Wrong_Password() {
		log.info("TC_05_Login_With_Wrong_Password- Step 01: Click to Log in link");
		homePageObject.clickToHeaderLinkByLabelName(driver, "Log in");
		loginPageObject = PageGeneratorManager.getLoginPageObject(driver);
		
		log.info("TC_05_Login_With_Wrong_Password - Step 02: Enter to Email: "+ emailAddress);
		loginPageObject.enterToTextboxByID(driver, "Email", emailAddress);

		password="123";
		log.info("TC_05_Login_With_Wrong_Password - Step 03: Enter to password: "+password);
		loginPageObject.enterToTextboxByID(driver, "Password",password);
		
		log.info("TC_05_Login_With_Wrong_Password - Step 04: Click Log in button");
		loginPageObject.clickToButtonByLabelName(driver, "Log in");
		
		log.info("TC_05_Login_With_Wrong_Password  - Step 05: verify error message ");
		verifyEquals(loginPageObject.getErrorMessage(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
	}
	@Test
	public void TC_06_Login_Success() {
		log.info("TC_06_Login_Success- Step 01: Click to Log in link");
		homePageObject.clickToHeaderLinkByLabelName(driver, "Log in");
		loginPageObject = PageGeneratorManager.getLoginPageObject(driver);
		
		log.info("TC_06_Login_Success - Step 02: Enter to Email: "+ emailAddress);
		loginPageObject.enterToTextboxByID(driver, "Email", emailAddress);

		password="123456";
		log.info("TC_06_Login_Success - Step 03: Enter to password: "+password);
		loginPageObject.enterToTextboxByID(driver, "Password",password);
		
		log.info("TC_06_Login_Success- Step 05: Click Log in button");
		loginPageObject.clickToButtonByLabelName(driver, "Log in");
		homePageObject = PageGeneratorManager.getHomePageObject(driver);
		
		log.info("TC_06_Login_Success: Step 06: Verfify link My account display");
		verifyTrue(homePageObject.isHeaderLinkDisplayByLabelName(driver,"My account"));
		
		log.info("TC_06_Login_Success: Step 07: Verfify link Log out display");
		verifyTrue(homePageObject.isHeaderLinkDisplayByLabelName(driver,"Log out"));
		
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName) {
		log.info("Postcondition: Close the browser:" + browserName);
		cleanBrowserAndDriver();
	}

}
