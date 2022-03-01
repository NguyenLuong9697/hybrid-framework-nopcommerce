package com.nopcommerce.register;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import common.BaseTest;
import pageObjects.user.HomePageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.RegisterPageObject;
import serverConfig.IServer;
import utilities.DataUtil;

public class Register extends BaseTest {

	WebDriver driver;
	IServer server;
	HomePageObject homePageObject;
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

		log.info("Precondition 02: Click to Register link");
		homePageObject.clickToHeaderLinkByLabelName(driver, "Register");
		registerPageObject = PageGeneratorManager.getRegisterPageObject(driver);
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		log.info("TC_01_Register_Empty_Data - Step 01: Enter to First Name: Empty data");
		registerPageObject.enterToTextboxByID(driver, "FirstName", "");

		log.info("TC_01_Register_Empty_Data - Step 02: Enter to Last Name: Empty data");
		registerPageObject.enterToTextboxByID(driver, "LastName", "");

		log.info("TC_01_Register_Empty_Data - Step 03: Enter to Email: Empty data");
		registerPageObject.enterToTextboxByID(driver, "Email", "");

		log.info("TC_01_Register_Empty_Data - Step 04: Enter to Password: Empty data");
		registerPageObject.enterToTextboxByID(driver, "Password", "");

		log.info("TC_01_Register_Empty_Data - Step 05: Enter to Confirm password: Empty data");
		registerPageObject.enterToTextboxByID(driver, "ConfirmPassword", "");

		log.info("TC_01_Register_Empty_Data - Step 06: Click to Register button");
		registerPageObject.clickToButtonByLabelName(driver, "Register");

		log.info("TC_01_Register_Empty_Data - Step 07: verify error message in first name ");
		verifyEquals(registerPageObject.getErrorMessageByID(driver, "FirstName-error"), "First name is required.");

		log.info("TC_01_Register_Empty_Data - Step 08: verify error message in last name ");
		verifyEquals(registerPageObject.getErrorMessageByID(driver, "LastName-error"), "Last name is required.");

		log.info("TC_01_Register_Empty_Data - Step 09: verify error message email  ");
		verifyEquals(registerPageObject.getErrorMessageByID(driver, "Email-error"), "Email is required.");

		log.info("TC_01_Register_Empty_Data - Step 10: verify error message password  ");
		verifyEquals(registerPageObject.getErrorMessageByID(driver, "Password-error"), "Password is required.");

		log.info("TC_01_Register_Empty_Data - Step 11: verify error message confirm password  ");
		verifyEquals(registerPageObject.getErrorMessageByID(driver, "ConfirmPassword-error"), "Password is required.");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {

		log.info("TC_02_Register_Invalid_Email- Step 01: Click to Register link");
		registerPageObject.clickToHeaderLinkByLabelName(driver, "Register");

		firstName=data.getFirstName();
		log.info("TC_01_Register_Empty_Data - Step 02: Enter to First Name: "+ firstName);
		registerPageObject.enterToTextboxByID(driver, "FirstName", firstName);

		lastName =data.getLastName();
		log.info("TC_01_Register_Empty_Data - Step 03: Enter to Last Name: " + lastName);
		registerPageObject.enterToTextboxByID(driver, "LastName", lastName);

		emailAddress="123456";
		log.info("TC_01_Register_Empty_Data - Step 04: Enter to Email: "+ emailAddress);
		registerPageObject.enterToTextboxByID(driver, "Email", emailAddress);

		password="123456";
		log.info("TC_01_Register_Empty_Data - Step 05: Enter to Password: "+ password);
		registerPageObject.enterToTextboxByID(driver, "Password", password);

		confirmPassword="123456";
		log.info("TC_01_Register_Empty_Data - Step 06: Enter to Confirm password: "+confirmPassword);
		registerPageObject.enterToTextboxByID(driver, "ConfirmPassword", confirmPassword);

		log.info("TC_01_Register_Empty_Data - Step 07: Click to Register button");
		registerPageObject.clickToButtonByLabelName(driver, "Register");
	
		log.info("TC_01_Register_Empty_Data - Step 08: verify error message email ");
		verifyEquals(registerPageObject.getErrorMessageByID(driver, "Email-error"), "Wrong email");

}

	@Test
	public void TC_03_Register_Password_Less_6_Characters() {
		
		log.info("TC_03_Register_Password_Less_6_Characters- Step 01: Click to Register link");
		registerPageObject.clickToHeaderLinkByLabelName(driver, "Register");

		firstName=data.getFirstName();
		log.info("TC_03_Register_Password_Less_6_Characters - Step 02: Enter to First Name: "+ firstName);
		registerPageObject.enterToTextboxByID(driver, "FirstName", firstName);

		lastName =data.getLastName();
		log.info("TC_03_Register_Password_Less_6_Characters - Step 03: Enter to Last Name: " + lastName);
		registerPageObject.enterToTextboxByID(driver, "LastName", lastName);

		emailAddress=data.getEmailAddress();
		log.info("TC_03_Register_Password_Less_6_Characters - Step 04: Enter to Email: "+ emailAddress);
		registerPageObject.enterToTextboxByID(driver, "Email", emailAddress);

		password="123";
		log.info("TC_03_Register_Password_Less_6_Characters - Step 05: Enter to Password: "+ password);
		registerPageObject.enterToTextboxByID(driver, "Password", password);
	
		log.info("TC_01_Register_Empty_Data - Step 06: Enter to Confirm password: Empty data");
		registerPageObject.enterToTextboxByID(driver, "ConfirmPassword", "");

		log.info("TC_03_Register_Password_Less_6_Characters - Step 07: verify error message password ");
		verifyEquals(registerPageObject.getErrorMessageByID(driver, "Password-error"),"Password must meet the following rules:\nmust have at least 6 characters");
	}
	@Test
	public void TC_04_Register_Wrong_ConfirmPassword() {
		log.info("TC_04_Register_Wrong_ConfirmPassword- Step 01: Click to Register link");
		registerPageObject.clickToHeaderLinkByLabelName(driver, "Register");

		firstName=data.getFirstName();
		log.info("TC_04_Register_Wrong_ConfirmPassword - Step 02: Enter to First Name: "+ firstName);
		registerPageObject.enterToTextboxByID(driver, "FirstName", firstName);

		lastName =data.getLastName();
		log.info("TC_04_Register_Wrong_ConfirmPassword - Step 03: Enter to Last Name: " + lastName);
		registerPageObject.enterToTextboxByID(driver, "LastName", lastName);

		emailAddress=data.getEmailAddress();
		log.info("TC_04_Register_Wrong_ConfirmPassword - Step 04: Enter to Email: "+ emailAddress);
		registerPageObject.enterToTextboxByID(driver, "Email", emailAddress);

		password="123456";
		log.info("TC_04_Register_Wrong_ConfirmPassword - Step 05: Enter to Password: "+ password);
		registerPageObject.enterToTextboxByID(driver, "Password", password);
	
		confirmPassword="123";
		log.info("TC_04_Register_Wrong_ConfirmPassword - Step 06: Enter to Confirm password: "+ confirmPassword);
		registerPageObject.enterToTextboxByID(driver, "ConfirmPassword", confirmPassword);
		
		log.info("TC_04_Register_Wrong_ConfirmPassword - Step 07: Click to Register button");
		registerPageObject.clickToButtonByLabelName(driver, "Register");	

		log.info("TC_04_Register_Wrong_ConfirmPassword - Step 08: verify error message in confirm password ");
		verifyEquals(registerPageObject.getErrorMessageByID(driver, "ConfirmPassword-error"),"The password and confirmation password do not match.");
	}
	@Test
	public void TC_05_Register_Valid_Information() {
		log.info("TC_05_Register_Valid_Information- Step 01: Click to Register link");
		registerPageObject.clickToHeaderLinkByLabelName(driver, "Register");

		firstName=data.getFirstName();
		log.info("TC_05_Register_Valid_Information- Step 02: Enter to First Name: "+ firstName);
		registerPageObject.enterToTextboxByID(driver, "FirstName", firstName);

		lastName =data.getLastName();
		log.info("TC_05_Register_Valid_Information - Step 03: Enter to Last Name: " + lastName);
		registerPageObject.enterToTextboxByID(driver, "LastName", lastName);

		emailAddress=data.getEmailAddress();
		log.info("TC_05_Register_Valid_Information - Step 04: Enter to Email: "+ emailAddress);
		registerPageObject.enterToTextboxByID(driver, "Email", emailAddress);

		password="123456";
		log.info("TC_05_Register_Valid_Information - Step 05: Enter to Password: "+ password);
		registerPageObject.enterToTextboxByID(driver, "Password", password);
	
		confirmPassword="123456";
		log.info("TC_05_Register_Valid_Information - Step 06: Enter to Confirm password: "+ confirmPassword);
		registerPageObject.enterToTextboxByID(driver, "ConfirmPassword", confirmPassword);
		
		log.info("TC_05_Register_Valid_Information - Step 07: Click to Register button");
		registerPageObject.clickToButtonByLabelName(driver, "Register");	

		log.info("TC_05_Register_Valid_Information - Step 08: verify register message successful");
		verifyEquals(registerPageObject.getRegisterMessageSuccessfull(),"Your registration completed");
		
		log.info("TC_05_Register_Valid_Information - Step 09: Click to Logout");
		registerPageObject.clickToHeaderLinkByLabelName(driver, "Log out");
		homePageObject = PageGeneratorManager.getHomePageObject(driver);
	}
	@Test
	public void TC_06_Register_Existed_Email() {
		log.info("TC_06_Register_Existed_Email- Step 01: Click to Register link");
		homePageObject.clickToHeaderLinkByLabelName(driver, "Register");
		registerPageObject = PageGeneratorManager.getRegisterPageObject(driver);
		
		firstName=data.getFirstName();
		log.info("TC_06_Register_Existed_Email- Step 02: Enter to First Name: "+ firstName);
		registerPageObject.enterToTextboxByID(driver, "FirstName", firstName);

		lastName =data.getLastName();
		log.info("TC_06_Register_Existed_Email - Step 03: Enter to Last Name: " + lastName);
		registerPageObject.enterToTextboxByID(driver, "LastName", lastName);

		log.info("TC_06_Register_Existed_Email - Step 04: Enter to Email: "+ emailAddress);
		registerPageObject.enterToTextboxByID(driver, "Email", emailAddress);

		password="123456";
		log.info("TC_06_Register_Existed_Email - Step 05: Enter to Password: "+ password);
		registerPageObject.enterToTextboxByID(driver, "Password", password);
	
		confirmPassword="123456";
		log.info("TC_06_Register_Existed_Email - Step 06: Enter to Confirm password: "+ confirmPassword);
		registerPageObject.enterToTextboxByID(driver, "ConfirmPassword", confirmPassword);
		
		log.info("TC_06_Register_Existed_Email - Step 07: Click to Register button");
		registerPageObject.clickToButtonByLabelName(driver, "Register");	

		log.info("TC_06_Register_Existed_Email - Step 08: verify register message successful");
		verifyEquals(registerPageObject.getErrorMessageWithExistedEmail(),"The specified email already exists");
		
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName) {
		log.info("Postcondition: Close the browser:" + browserName);
		cleanBrowserAndDriver();
	}

}
