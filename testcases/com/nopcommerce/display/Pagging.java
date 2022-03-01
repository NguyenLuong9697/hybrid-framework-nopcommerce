package com.nopcommerce.display;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import common.BaseTest;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.ProductPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.SearchPageObject;
import serverConfig.IServer;
import utilities.DataUtil;

public class Pagging extends BaseTest {

	WebDriver driver;
	IServer server;
	HomePageObject homePageObject;
	DataUtil data;
	ProductPageObject productPageObject;	

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

	}

	@Test
	public void TC_01_Pagging_3_Product() {

		log.info("TC_01_Pagging_3_Product -Step 01: Click sub menu Notebooks");
		homePageObject.openSubMenuByLabelName(driver, "Computers", "Notebooks");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);

		log.info("TC_01_Pagging_3_Product -Step 02: Select value in drop down display: 3");
		productPageObject.selectItemInDropDownByName(driver, "products-pagesize", "3");

		log.info("TC_01_Pagging_3_Product: Step 03: verify in each page  only has less or equal 3 product");		
		verifyTrue(productPageObject.isNumberProductInPage(3));

		log.info("TC_01_Pagging_3_Product: Step 04: verify page 1 is active");
		verifyTrue(productPageObject.isPageNumberActive("1"));
		
		log.info("TC_01_Pagging_3_Product: Step 05: verify next page icon is display");
		verifyTrue(productPageObject.isNextPageIconDisplay());
		
		log.info("TC_01_Pagging_3_Product - step 05: click page 2");
		productPageObject.clickToPageNumber("2");
		
		log.info("TC_01_Pagging_3_Product: Step 06: verify page 2 is active");
		verifyTrue(productPageObject.isPageNumberActive("2"));
		
		log.info("TC_01_Pagging_3_Product: Step 07: verify in each page  only has less or equal 3 product");		
		verifyTrue(productPageObject.isNumberProductInPage(3));
		
		log.info("TC_01_Pagging_3_Product: Step 08: verify previous page icon is display");
		verifyTrue(productPageObject.isPreviousPageIconDisplay());
	}

	@Test
	public void TC_02_Pagging_6_Product() {

		log.info("TC_01_Pagging_3_Product -Step 01: Select value in drop down display: 6");
		productPageObject.selectItemInDropDownByName(driver, "products-pagesize", "6");

		log.info("TC_01_Pagging_3_Product: Step 02: verify in each page only has less or equal 9 product");		
		verifyTrue(productPageObject.isNumberProductInPage(6));
		
		log.info("TC_01_Pagging_3_Product: Step 03: verify pagging is not display");
		verifyTrue(productPageObject.isPaggingNotDisplay());
	}

	@Test
	public void TC_03_Pagging_9_Product() {
	
		log.info("TC_01_Pagging_3_Product -Step 01: Select value in drop down display: 9");
		productPageObject.selectItemInDropDownByName(driver, "products-pagesize", "9");

		log.info("TC_01_Pagging_3_Product: Step 02: verify in each page only has less or equal 9 product");		
		verifyTrue(productPageObject.isNumberProductInPage(9));
		
		log.info("TC_01_Pagging_3_Product: Step 03: verify pagging is not display");
		verifyTrue(productPageObject.isPaggingNotDisplay());
	}
	
	
	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)

	public void afterClass(String browserName) {
		log.info("Postcondition: Close the browser:" + browserName);
		//cleanBrowserAndDriver();
	}

}
