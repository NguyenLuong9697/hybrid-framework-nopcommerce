package com.nopcommerce.sort;

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
import payObjects.ProductPageObject;
import payObjects.RegisterPageObject;
import payObjects.SearchPageObject;
import serverConfig.IServer;
import utilities.DataUtil;

public class Sort extends BaseTest {

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
	public void TC_01_SortName_A_To_Z() {

		log.info("TC_01_SortName_A_To_Z -Step 01: Click sub menu Notebooks");
		homePageObject.openSubMenuByLabelName(driver, "Computers", "Notebooks");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);

		log.info("TC_01_SortName_A_To_Z -Step 02: Select value in drop down sort by: Name: A to Z");
		productPageObject.selectItemInDropDownByName(driver, "products-orderby", "Name: A to Z");

		log.info("TC_01_SortName_A_To_Z: Step 03: verify selected value  in drop down is 'Name: A to Z' ");
		verifyEquals(productPageObject.getSelectedValueInDropdrownByName(driver, "products-orderby"), "Name: A to Z");

		log.info("TC_01_SortName_A_To_Z: Step 04: verify list produts are sorted with name ascensding");
		verifyTrue(productPageObject.isProductNameSortAscending());
	}

	@Test
	public void TC_02_SortName_Z_To_A() {

		log.info("TC_02_SortName_Z_To_A -Step 01: Select value in drop down sort by: 'Name: Z to A'");
		productPageObject.selectItemInDropDownByName(driver, "products-orderby", "Name: Z to A");

		log.info("TC_02_SortName_Z_To_A: Step 02: verify selected value  in drop down is 'Name: Z to A' ");
		verifyEquals(productPageObject.getSelectedValueInDropdrownByName(driver, "products-orderby"), "Name: Z to A");

		log.info("TC_02_SortName_Z_To_A: Step 03: verify list produts are sorted with name descensding");
		verifyTrue(productPageObject.isProductNameSortDescending());
	}

	@Test
	public void TC_03_SortPrice_Low_To_High() {
	
		log.info("TC_03_SortPrice_Low_To_High -Step 01: Select value in drop down sort by: 'Price: Low to High'");
		productPageObject.selectItemInDropDownByName(driver, "products-orderby", "Price: Low to High");

		log.info("TC_03_SortPrice_Low_To_High: Step 02: verify selected value  in drop down is 'Price: Low to High' ");
		verifyEquals(productPageObject.getSelectedValueInDropdrownByName(driver, "products-orderby"), "Price: Low to High");

		log.info("TC_03_SortPrice_Low_To_High: Step 03: verify list products are sorted with price ascensding");
		verifyTrue(productPageObject.isProductPriceSortAscending());
	}
	
	@Test
	public void TC_04_SortPrice_High_To_Low() {
	
		log.info("TC_04_SortPrice_High_To_Low -Step 01: Select value in drop down sort by: 'Price: High to Low'");
		productPageObject.selectItemInDropDownByName(driver, "products-orderby", "Price: High to Low");

		log.info("TC_04_SortPrice_High_To_Low- Step 02: verify selected value  in drop down is 'Price: Low to High' ");
		verifyEquals(productPageObject.getSelectedValueInDropdrownByName(driver, "products-orderby"), "Price: High to Low");

		log.info("TC_04_SortPrice_High_To_Low- Step 03: verify list products are sorted with price descensding");
		verifyTrue(productPageObject.isProductPriceSortDescending());
	}
	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)

	public void afterClass(String browserName) {
		log.info("Postcondition: Close the browser:" + browserName);
		cleanBrowserAndDriver();
	}

}
