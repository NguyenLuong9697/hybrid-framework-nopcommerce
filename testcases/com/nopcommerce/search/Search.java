package com.nopcommerce.search;

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
import payObjects.SearchPageObject;
import serverConfig.IServer;
import utilities.DataUtil;

public class Search extends BaseTest {

	WebDriver driver;
	IServer server;
	HomePageObject homePageObject;
	//RegisterPageObject registerPageObject;
	//LoginPageObject loginPageObject;
	SearchPageObject searchPageObject;
	DataUtil data;
	// String firstName, lastName, fullName, emailAddress, password,
	// confirmPassword;
	String searchKey, categorySearch, manufacturer;

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
		/*
		 * log.info("Precondition 02: Click to Register link");
		 * homePageObject.clickToHeaderLinkByLabelName(driver, "Register");
		 * registerPageObject = PageGeneratorManager.getRegisterPageObject(driver);
		 * 
		 * firstName=data.getFirstName();
		 * log.info("Precondition 03: Enter to First Name: "+ firstName);
		 * registerPageObject.enterToTextboxByID(driver, "FirstName", firstName);
		 * 
		 * lastName =data.getLastName();
		 * log.info("Precondition 04: Enter to Last Name: " + lastName);
		 * registerPageObject.enterToTextboxByID(driver, "LastName", lastName);
		 * 
		 * emailAddress=data.getEmailAddress();
		 * log.info("Precondition 05: Enter to Email: "+ emailAddress);
		 * registerPageObject.enterToTextboxByID(driver, "Email", emailAddress);
		 * 
		 * password="123456"; log.info("Precondition 06: Enter to Password: "+
		 * password); registerPageObject.enterToTextboxByID(driver, "Password",
		 * password);
		 * 
		 * confirmPassword="123456";
		 * log.info("Precondition 07: Enter to Confirm password: "+ confirmPassword);
		 * registerPageObject.enterToTextboxByID(driver, "ConfirmPassword",
		 * confirmPassword);
		 * 
		 * log.info("Precondition 08: Click to Register button");
		 * registerPageObject.clickToButtonByLabelName(driver, "Register");
		 * 
		 * log.info("Precondition 09: verify register message successful");
		 * verifyEquals(registerPageObject.getRegisterMessageSuccessfull()
		 * ,"Your registration completed");
		 */
	}

	@Test
	public void TC_01_Search_Empty_Data() {
		log.info("TC_01_Search_Empty_Data: Step 01: Click Search in footer");
		homePageObject.clickToFooterLinkByLabelName(driver, "Search");
		searchPageObject = PageGeneratorManager.getSearchPageObject(driver);

		log.info("TC_01_Search_Empty_Data: Step 01: Enter to textbox Search Keyword: Empty ");
		searchPageObject.enterToTextboxByID(driver, "q", "");

		log.info("TC_01_Search_Empty_Data: Step 01: Click search button ");
		searchPageObject.clickSearchButton();

		log.info("TC_01_Search_Empty_Data: Step 01: Verify error message ");
		verifyEquals(searchPageObject.getErrorMessageWhenSearchWithEmptyData(),
				"Search term minimum length is 3 characters");

	}

	@Test
	public void TC_02_Search_Data_Not_Exist() {
		log.info("TC_02_Search_Data_Not_Exist -  Step 01: Click Search in footer");
		searchPageObject.clickToFooterLinkByLabelName(driver, "Search");

		searchKey = "Macbook Pro 2050";
		log.info("TC_02_Search_Data_Not_Exist: Step 02: Enter to textbox Search Keyword: " + searchKey);
		searchPageObject.enterToTextboxByID(driver, "q", searchKey);

		log.info("TC_02_Search_Data_Not_Exist: Step 03: Click search button ");
		searchPageObject.clickSearchButton();

		log.info("TC_02_Search_Data_Not_Exist: Step 04: Verify error message ");
		verifyEquals(searchPageObject.getErrorMessageWhenSearchWithDataNotExist(),
				"No products were found that matched your criteria.");

	}

	@Test
	public void TC_03_Search_Relative_Data() {

		log.info("TC_03_Search_Relative_Data: Step 01: Click Search in footer");
		searchPageObject.clickToFooterLinkByLabelName(driver, "Search");

		searchKey = "Lenovo";
		log.info("TC_03_Search_Relative_Data: Step 02: Enter to textbox Search Keyword: " + searchKey);
		searchPageObject.enterToTextboxByID(driver, "q", searchKey);

		log.info("TC_03_Search_Relative_Data: Step 03: Click search button ");
		searchPageObject.clickSearchButton();

		log.info("TC_03_Search_Relative_Data: Step 04: Verify search result");
		verifyTrue(searchPageObject.verifyInformationAfterSearchWithRelativeData(searchKey));

	}

	@Test
	public void TC_04_Search_Absolute_Data() {
		
		log.info("TC_04_Search_Absolute_Data: Step 01: Click Search in footer");
		searchPageObject.clickToFooterLinkByLabelName(driver, "Search");
		
		searchKey = "Lenovo Thinkpad X1 Carbon Laptop";
		log.info("TC_04_Search_Absolute_Data: Step 02: Enter to textbox Search Keyword: " + searchKey);
		searchPageObject.enterToTextboxByID(driver, "q", searchKey);

		log.info("TC_04_Search_Absolute_Data: Step 03: Click search button ");
		searchPageObject.clickSearchButton();
		
		log.info("TC_04_Search_Absolute_Data: Step 04: Verify search result");
		verifyTrue(searchPageObject.verifyInformationAfterSearchWithAbsoluteData(searchKey));

	}
	
	@Test
	public void TC_05_AdvancedSearch_ParentCategories() {
		log.info("TC_05_AdvancedSearch_ParentCategories: Step 01: Click Search in footer");
		searchPageObject.clickToFooterLinkByLabelName(driver, "Search");
		
		searchKey = "Apple Macbook Pro";
		log.info("TC_05_AdvancedSearch_ParentCategories: Step 02: Enter to textbox Search Keyword: " + searchKey);
		searchPageObject.enterToTextboxByID(driver, "q", searchKey);
		
		log.info("TC_05_AdvancedSearch_ParentCategories: Step 03: Click to checkbox Advanced Search");
		searchPageObject.clickToCheckBoxByLabelName("Advanced search");

		categorySearch="Computers";
		log.info("TC_05_AdvancedSearch_ParentCategories: Step 04: Select category search:"+ categorySearch);
		searchPageObject.selectItemInDropDownByLabelName(driver, "Category:", categorySearch);
		
		log.info("TC_05_AdvancedSearch_ParentCategories: Step 05: Click search button ");
		searchPageObject.clickSearchButton();
		
		log.info("TC_05_AdvancedSearch_ParentCategories: Step 06: Verify error message");
		verifyEquals(searchPageObject.getErrorMessageWhenSearchWithDataNotExist(),
				"No products were found that matched your criteria.");
	}
	
	@Test
	public void TC_06_AdvancedSearch_SubCategories() {
		log.info(" TC_06_AdvancedSearch_SubCategories: Step 01: Click Search in footer");
		searchPageObject.clickToFooterLinkByLabelName(driver, "Search");
		
		searchKey = "Apple MacBook Pro";
		log.info("TC_06_AdvancedSearch_SubCategories: Step 02: Enter to textbox Search Keyword: " + searchKey);
		searchPageObject.enterToTextboxByID(driver, "q", searchKey);
		
		log.info("TC_06_AdvancedSearch_SubCategories: Step 03: Click to checkbox Advanced Search");
		searchPageObject.clickToCheckBoxByLabelName("Advanced search");

		categorySearch="Computers";
		log.info("TC_06_AdvancedSearch_SubCategories: Step 04: slect category search: "+ categorySearch);
		searchPageObject.selectItemInDropDownByLabelName(driver, "Category:", categorySearch);
		
		
		log.info("TC_06_AdvancedSearch_SubCategories: Step 05: Click to checkbox Automatically search sub categories");
		searchPageObject.clickToCheckBoxByLabelName("Automatically search sub categories");
		
		log.info("TC_06_AdvancedSearch_SubCategories: Step 06: Click search button ");
		searchPageObject.clickSearchButton();
		
		log.info("TC_06_AdvancedSearch_SubCategories: Step 07: Verify search result");
		verifyTrue(searchPageObject.verifyInformationAfterSearchSubCategories(searchKey));

	}
	
	@Test
	public void TC_07_AdvancedSearch_Incorrect_Manufacturer() {
		log.info("TC_07_AdvancedSearch_Incorrect_Manufacturer: Step 01: Click Search in footer");
		searchPageObject.clickToFooterLinkByLabelName(driver, "Search");
		
		searchKey = "Apple MacBook Pro";
		log.info("TC_07_AdvancedSearch_Incorrect_Manufacturer: Step 02: Enter to textbox Search Keyword: " + searchKey);
		searchPageObject.enterToTextboxByID(driver, "q", searchKey);
		
		log.info("TC_07_AdvancedSearch_Incorrect_Manufacturer: Step 03: Click to checkbox Advanced Search");
		searchPageObject.clickToCheckBoxByLabelName("Advanced search");

		categorySearch="Computers";
		log.info("TC_07_AdvancedSearch_Incorrect_Manufacturer: Step 04: select category search: "+categorySearch);
		searchPageObject.selectItemInDropDownByLabelName(driver, "Category:", categorySearch);
				
		log.info("TC_07_AdvancedSearch_Incorrect_Manufacturer: Step 05: Click to checkbox Automatically search sub categories");
		searchPageObject.clickToCheckBoxByLabelName("Automatically search sub categories");
		
		manufacturer="HP";
		log.info("TC_07_AdvancedSearch_Incorrect_Manufacturer: Step 06: select manufacturer");
		searchPageObject.selectItemInDropDownByLabelName(driver, "Manufacturer:", manufacturer);
		
		
		log.info("TC_07_AdvancedSearch_Incorrect_Manufacturer: Step 07: Click search button ");
		searchPageObject.clickSearchButton();
		
		log.info("TC_07_AdvancedSearch_Incorrect_Manufacturer: Step 08: Verify error message");
		verifyEquals(searchPageObject.getErrorMessageWhenSearchWithDataNotExist(),
				"No products were found that matched your criteria.");

				
	}
	
	@Test
	public void TC_08_AdvancedSearch_Correct_Manufacturer() {
		log.info("TC_08_AdvancedSearch_Correct_Manufacturer: Step 01: Click Search in footer");
		searchPageObject.clickToFooterLinkByLabelName(driver, "Search");
		
		searchKey = "Apple MacBook Pro";
		log.info("TC_08_AdvancedSearch_Correct_Manufacturer: Step 02: Enter to textbox Search Keyword: " + searchKey);
		searchPageObject.enterToTextboxByID(driver, "q", searchKey);
		
		log.info("TC_08_AdvancedSearch_Correct_Manufacturer: Step 03: Click to checkbox Advanced Search");
		searchPageObject.clickToCheckBoxByLabelName("Advanced search");

		categorySearch="Computers";
		log.info("TC_08_AdvancedSearch_Correct_Manufacturer: Step 04: select category search: "+categorySearch);
		searchPageObject.selectItemInDropDownByLabelName(driver, "Category:", categorySearch);
				
		log.info("TC_08_AdvancedSearch_Correct_Manufacturer: Step 05: Click to checkbox Automatically search sub categories");
		searchPageObject.clickToCheckBoxByLabelName("Automatically search sub categories");
		
		manufacturer="Apple";
		log.info("TC_08_AdvancedSearch_Correct_Manufacturer: Step 06: select manufacturer");
		searchPageObject.selectItemInDropDownByLabelName(driver, "Manufacturer:", manufacturer);
		
		
		log.info("TC_08_AdvancedSearch_Correct_Manufacturer: Step 07: Click search button ");
		searchPageObject.clickSearchButton();
		
		log.info("TC_08_AdvancedSearch_Correct_Manufacturer: Step 08: verify search result");
		verifyTrue(searchPageObject.verifyInformationAfterSearchCorrectManufacturer(searchKey));
		
	}
	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)

	public void afterClass(String browserName) {
		log.info("Postcondition: Close the browser:" + browserName);
		cleanBrowserAndDriver();
	}

}
