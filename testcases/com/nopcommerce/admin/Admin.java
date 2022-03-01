package com.nopcommerce.admin;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import pageObjects.admin.CustomerDetailPageObject;
import pageObjects.admin.CustomerPageObject;
import pageObjects.admin.DashboardPageObject;
import pageObjects.admin.EditAddressCustomerPageObject;
import pageObjects.admin.LoginPageObject;
import pageObjects.admin.NewAddressCustomerPageObject;
import pageObjects.admin.NewCustomerPageObject;
import pageObjects.admin.PageGeneratorManager;
import pageObjects.admin.ProductDetailPageObject;
import pageObjects.admin.ProductPageObject;
import serverConfig.IServer;
import utilities.DataUtil;

public class Admin extends BaseTest {

	WebDriver driver;
	IServer server;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ProductPageObject productPage;
	ProductDetailPageObject productDetail;
	CustomerPageObject customerPage;
	NewCustomerPageObject newCustomerPage;
	CustomerDetailPageObject customerDetailPage;
	NewAddressCustomerPageObject newAddressCustomerPage;
	EditAddressCustomerPageObject editAddressCustomerPage;
	DataUtil data;
	String email, pass;
	String productName, category, manufacturer;
	String customerEmail,customerPassword, customerFirstName, customerLastName,gender,customerDateOfBirth,customerRole, customerCompany, customerFullName;
	String customerEditEmail, customerEditFirstName,customerEditDateOfBirth, customerEditCompany, customerEditFullName, adminComment;
	String addressFirstName, addressLastName, addressEmail,city, address_1, zipCode, phoneNumber, faxNumber;
	String editAddressFirstName, editAddressLastName, editAddressEmail,editCity, editAddress_1, editZipCode, editPhoneNumber, editFaxNumber;
	
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
		email=server.email();
		pass=server.pass();
		loginPage = PageGeneratorManager.getLoginPageObject(driver);

		log.info("Precondition 02: Input to email textbox with email:"+ email);
		loginPage.enterToTextboxByID(driver, "Email", email);
		
		log.info("Precondition 03: Input to Password textbox with password:"+pass);
		loginPage.enterToTextboxByID(driver, "Password", pass);
		
		log.info("Precondition 04:Click button Log  in");
		loginPage.clickToButtonByLabelName(driver, "Log in");
		dashboardPage = PageGeneratorManager.getDashboardObject(driver);
		dashboardPage.isJQueryLoadSuccess(driver);
	}

	@Test
	public void TC_01_Search_Product_With_Product_Name() {
		log.info("TC_01_Search_Product_With_Product_Name -Step 01: Click sub menu Product by menu Catalog");
		dashboardPage.openSubMenuThroughMenuByName(driver,"Catalog","Products");
		productPage=PageGeneratorManager.getProductPageObject(driver);
		
		log.info("TC_01_Search_Product_With_Product_Name -Step 02: Click to expand search panel");
		productPage.clickToExpandSearchPanel();
	  	productPage.isJQueryLoadSuccess(driver);
	  	
	  	productName="Lenovo IdeaCentre 600 All-in-One PC";
	  	log.info("TC_01_Search_Product_With_Product_Name -Step 03: Enter to textbox 'Product name' with value: "+ productName);
	  	productPage.enterToTextboxByID(driver, "SearchProductName",productName);
	  	
	  	log.info("TC_01_Search_Product_With_Product_Name -Step 04: Click button 'Search'");
	  	productPage.clickToButtonByID(driver, "search-products");
	  	productPage.isJQueryLoadSuccess(driver);
	  	
	  	log.info("TC_01_Search_Product_With_Product_Name -Step 05: Verify only 1 product is display");
	  	verifyEquals(productPage.getNumberProductDisplay(), 1);
	  	
	  	log.info("TC_01_Search_Product_With_Product_Name -Step 06: Verify product :"+ productName+" is display");
	  	verifyTrue(productPage.isProductDisplayed(driver, productName, "LE_IC_600"));
	  		  	
	}
	@Test
	public void TC_02_Search_Product_With_ProductName_ParentCategory_Uncheck() {
		
	  	productName="Nikon D5500 DSLR";
	  	log.info("TC_02_Search_Product_With_ProductName_ParentCategory_Uncheck -Step 01: Enter to textbox 'Product name' with value: "+ productName);
	  	productPage.enterToTextboxByID(driver, "SearchProductName",productName);
	  	
	  	category="Electronics";
	  	log.info("TC_02_Search_Product_With_ProductName_ParentCategory_Uncheck -Step 02: Select value in dropdown 'Category' with value:"+ category );
	  	productPage.selectItemInDropDownByName(driver,"SearchCategoryId", category);
	  	
		log.info("TC_02_Search_Product_With_ProductName_ParentCategory_Uncheck -Step 03: Verify selected value of dropdown 'Category' is: "+category );
	  	verifyEquals(productPage.getSelectedValueInDropDownByName(driver,"SearchCategoryId"), category);
	  	
	  	log.info("TC_02_Search_Product_With_ProductName_ParentCategory_Uncheck -Step 04: verify check box 'Search subcategories' is not checked");
	  	verifyFalse(productPage.isCheckboxSelectedByID(driver, "SearchIncludeSubCategories"));
	  	
	  
		log.info("TC_02_Search_Product_With_ProductName_ParentCategory_Uncheck -Step 05: Click button 'Search'");
	  	productPage.clickToButtonByID(driver, "search-products");
	  	productPage.isJQueryLoadSuccess(driver);
	  	
	  	log.info("TC_02_Search_Product_With_ProductName_ParentCategory_Uncheck -Step 06: Verify message 'No data available in table' is display");
	  	verifyTrue(productPage.isMessageInTableByIDTableDisplay(driver,"products-grid","No data available in table"));
	}
	
	@Test
	public void TC_03_Search_Product_With_ProductName_ParentCategory_Check() {
		
	  	log.info("TC_03_Search_Product_With_ProductName_ParentCategory_Check -Step 01: Enter to textbox 'Product name' with value: "+ productName);
	  	productPage.enterToTextboxByID(driver, "SearchProductName",productName);
	  	
	  	log.info("TC_03_Search_Product_With_ProductName_ParentCategory_Check -Step 02: Select value in dropdown 'Category' with value:"+ category );
	  	productPage.selectItemInDropDownByName(driver,"SearchCategoryId", category);
	  	
	  	log.info("TC_03_Search_Product_With_ProductName_ParentCategory_Check -Step 03: Verify selected value of dropdown 'Category' is: "+category );
	  	verifyEquals(productPage.getSelectedValueInDropDownByName(driver,"SearchCategoryId"), category);
	  		  	
	  	log.info("TC_03_Search_Product_With_ProductName_ParentCategory_Check -Step 04: Check to check box 'Search subcategories' "); 
	  	productPage.clickToCheckboxByID(driver,"SearchIncludeSubCategories");
	  	
	  	log.info("TC_03_Search_Product_With_ProductName_ParentCategory_Check -Step 05: verify check box 'Search subcategories' is checked");
	  	verifyTrue(productPage.isCheckboxSelectedByID(driver, "SearchIncludeSubCategories"));
	  	
		log.info("TC_03_Search_Product_With_ProductName_ParentCategory_Check -Step 06: Click button 'Search'");
	  	productPage.clickToButtonByID(driver, "search-products");
	  	productPage.isJQueryLoadSuccess(driver);	  	

	  	log.info("TC_03_Search_Product_With_ProductName_ParentCategory_Check -Step 07: Verify only 1 product is display");
	  	verifyEquals(productPage.getNumberProductDisplay(), 1);
	  	
	  	log.info("TC_03_Search_Product_With_ProductName_ParentCategory_Check -Step 08: Verify product :"+ productName+" is display");
	  	verifyTrue(productPage.isProductDisplayed(driver, productName, "N5500DS_0"));
	  		  	
	  	
	}
	
	@Test
	public void TC_04_Search_Product_With_ProductName_ChildCategory() {
		productName="Lenovo IdeaCentre 600 All-in-One PC";
		log.info("TC_04_Search_Product_With_ProductName_ChildCategory -Step 01: Enter to textbox 'Product name' with value: "+ productName);
	  	productPage.enterToTextboxByID(driver, "SearchProductName",productName);
	  	
	  	category="Computers >> Desktops";
	  	log.info("TC_04_Search_Product_With_ProductName_ChildCategory -Step 02: Select value in dropdown 'Category' with value:"+ category );
	  	productPage.selectItemInDropDownByName(driver,"SearchCategoryId", category);
	  	
	  	log.info("TC_04_Search_Product_With_ProductName_ChildCategory -Step 03: Verify selected value of dropdown 'Category' is: "+category );
	  	verifyEquals(productPage.getSelectedValueInDropDownByName(driver,"SearchCategoryId"), category);
	  	
		log.info("TC_04_Search_Product_With_ProductName_ChildCategory -Step 04: Uncheck to check box 'Search subcategories' "); 
	  	productPage.clickToUnCheckboxByID(driver,"SearchIncludeSubCategories");
	  	
	  	log.info("TC_04_Search_Product_With_ProductName_ChildCategory -Step 05: verify check box 'Search subcategories' is not checked");
	  	verifyFalse(productPage.isCheckboxSelectedByID(driver, "SearchIncludeSubCategories"));
	  	
		log.info("TC_04_Search_Product_With_ProductName_ChildCategory -Step 06: Click button 'Search'");
	  	productPage.clickToButtonByID(driver, "search-products");
	  	productPage.isJQueryLoadSuccess(driver);	  	

	  	log.info("TC_04_Search_Product_With_ProductName_ChildCategory -Step 07: Verify only 1 product is display");
	  	verifyEquals(productPage.getNumberProductDisplay(), 1);
	  	
	  	log.info("TC_04_Search_Product_With_ProductName_ChildCategory -Step 08: Verify product :"+ productName+" is display");
	  	verifyTrue(productPage.isProductDisplayed(driver, productName, "LE_IC_600"));  	 
	  	
	}
	
	@Test
	public void TC_05_Search_Product_With_ProductName_Manufacturer() {
		productName="Lenovo IdeaCentre 600 All-in-One PC";
		log.info("TC_05_Search_Product_With_ProductName_Manufacturer -Step 01: Enter to textbox 'Product name' with value: "+ productName);
	  	productPage.enterToTextboxByID(driver, "SearchProductName",productName);
	  	
	  	category="All";
	  	log.info("TC_05_Search_Product_With_ProductName_Manufacturer -Step 02: Select value in dropdown 'Category' with value:"+ category );
	  	productPage.selectItemInDropDownByName(driver,"SearchCategoryId", category);
	  			
	  	log.info("TC_05_Search_Product_With_ProductName_Manufacturer -Step 03: Verify selected value of dropdown 'Category' is: "+category );
	  	verifyEquals(productPage.getSelectedValueInDropDownByName(driver,"SearchCategoryId"), category);
	  	
	  	log.info("TC_05_Search_Product_With_ProductName_Manufacturer -Step 04: verify check box 'Search subcategories' is not checked");
	  	verifyFalse(productPage.isCheckboxSelectedByID(driver, "SearchIncludeSubCategories"));
	  			
		manufacturer="Apple";
	  	log.info("TC_05_Search_Product_With_ProductName_Manufacturer -Step 05: Select value in dropdown 'Manufacturer' with value:"+ manufacturer );
	  	productPage.selectItemInDropDownByName(driver,"SearchManufacturerId",manufacturer);
	  	
		log.info("TC_05_Search_Product_With_ProductName_Manufacturer -Step 06: Verify selected value of dropdown 'Manufacturer' is: "+manufacturer);
	  	verifyEquals(productPage.getSelectedValueInDropDownByName(driver,"SearchManufacturerId"), manufacturer);
	  	
		log.info("TC_05_Search_Product_With_ProductName_Manufacturer -Step 07: Click button 'Search'");
	  	productPage.clickToButtonByID(driver, "search-products");
	  	productPage.isJQueryLoadSuccess(driver);	  	

		log.info("TC_05_Search_Product_With_ProductName_Manufacturer -Step 08: Verify message 'No data available in table' is display");
	  	verifyTrue(productPage.isMessageInTableByIDTableDisplay(driver,"products-grid","No data available in table"));
	}
	
	@Test
	public void TC_06_Go_Directly_To_Product_Sku() {
		productName="";
		log.info("TC_06_Go_Directly_To_Product_Sku -Step 01: Delete data in textbox 'Product name'");
	  	productPage.enterToTextboxByID(driver, "SearchProductName",productName);
	  	
	  	category="All";
	  	log.info("TC_06_Go_Directly_To_Product_Sku -Step 02: Select value in dropdown 'Category' with value:"+ category );
	  	productPage.selectItemInDropDownByName(driver,"SearchCategoryId", category);
	  			
	  	log.info("TC_06_Go_Directly_To_Product_Sku -Step 03: Verify selected value of dropdown 'Category' is: "+category );
	  	verifyEquals(productPage.getSelectedValueInDropDownByName(driver,"SearchCategoryId"), category);
	  	
	  	log.info("TC_06_Go_Directly_To_Product_Sku -Step 04: verify check box 'Search subcategories' is not checked");
	  	verifyFalse(productPage.isCheckboxSelectedByID(driver, "SearchIncludeSubCategories"));
	  			
		manufacturer="All";
	  	log.info("TC_06_Go_Directly_To_Product_Sku -Step 05: Select value in dropdown 'Manufacturer' with value:"+ manufacturer );
	  	productPage.selectItemInDropDownByName(driver,"SearchManufacturerId",manufacturer);
	  	
		log.info("TC_06_Go_Directly_To_Product_Sku -Step 06: Verify selected value of dropdown 'Manufacturer' is: "+manufacturer);
	  	verifyEquals(productPage.getSelectedValueInDropDownByName(driver,"SearchManufacturerId"), manufacturer);
	  	
	  	log.info("TC_06_Go_Directly_To_Product_Sku -Step 07: Delete data in textbox 'Go directly to product SKU ' with value: LE_IC_600");
	  	productPage.enterToTextboxByID(driver, "GoDirectlyToSku","LE_IC_600");
	  	
	  	log.info("TC_06_Go_Directly_To_Product_Sku -Step 08: Click button 'Go'");
	  	productPage.clickToButtonByID(driver, "go-to-product-by-sku");
	  	productDetail = PageGeneratorManager.getProductDetailPageObject(driver);
	  	productDetail.isJQueryLoadSuccess(driver);	  	
	  	
	  	log.info("TC_06_Go_Directly_To_Product_Sku -Step 09: Click to expand panel by card name 'Product info'");
	  	productDetail.clickToExpandPanelByCardName(driver, "Product info");
	  	
		log.info("TC_06_Go_Directly_To_Product_Sku -Step 10: verify product name : Lenovo IdeaCentre 600 All-in-One PC is display");
		verifyEquals(productDetail.getAttributeTextboxByID(driver, "value","Name"),"Lenovo IdeaCentre 600 All-in-One PC");
		
		log.info("TC_06_Go_Directly_To_Product_Sku -Step 11: verify sku : LE_IC_600is display");
		verifyEquals(productDetail.getAttributeTextboxByID(driver, "value","Sku"),"LE_IC_600");
	}
	
	@Test
	public void TC_07_Create_New_Customer() {
		log.info("TC_07_Create_New_Customer -Step 01: Click sub menu Customers by menu Customers");
		dashboardPage.openSubMenuThroughMenuByName(driver,"Customers","Customers");
		customerPage=PageGeneratorManager.getCustomerPageObject(driver);
		customerPage.isJQueryLoadSuccess(driver);	
		
		log.info("TC_07_Create_New_Customer -Step 02:Click link 'Add new'");
		customerPage.clickHeaderLinkByText(driver,"Add new");
		newCustomerPage = PageGeneratorManager.getNewCustomerPageObject(driver);
		newCustomerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_07_Create_New_Customer -Step 03:Click to expand panel Customer info");
		newCustomerPage.clickToExpandPanelByCardName(driver,"Customer info");
		newCustomerPage.isJQueryLoadSuccess(driver);
		
		customerEmail= data.getEmailAddress();
		log.info("TC_07_Create_New_Customer -Step 04: Enter to email textbox with value: "+ customerEmail);
		newCustomerPage.enterToTextboxByID(driver, "Email", customerEmail);
		newCustomerPage.isJQueryLoadSuccess(driver);
		
		customerPassword="123456";
		log.info("TC_07_Create_New_Customer -Step 05: Enter to password textbox with value: "+ customerPassword);
		newCustomerPage.enterToTextboxByID(driver, "Password", customerPassword);
		newCustomerPage.isJQueryLoadSuccess(driver);
		
		customerFirstName ="Henry";
		log.info("TC_07_Create_New_Customer -Step 06: Enter to first name textbox with value: "+ customerFirstName);
		newCustomerPage.enterToTextboxByID(driver, "FirstName", customerFirstName);
		newCustomerPage.isJQueryLoadSuccess(driver);
		
		customerLastName ="Canady"+ randomNumber();
		log.info("TC_07_Create_New_Customer -Step 07: Enter to last name textbox with value: "+ customerLastName);
		newCustomerPage.enterToTextboxByID(driver, "LastName", customerLastName);
		newCustomerPage.isJQueryLoadSuccess(driver);
		
		gender="Male";
		log.info("TC_07_Create_New_Customer -Step 08: Click to radio button: "+ gender);
		newCustomerPage.clickToRadioButtonByID(driver, "Gender_Male");
		newCustomerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_07_Create_New_Customer - Step 09: verify radio button:"+ gender+" is selected");
		verifyTrue(newCustomerPage.isRadioButtonSelectedByID(driver, "Gender_Male"));	
		newCustomerPage.isJQueryLoadSuccess(driver);
		
		customerDateOfBirth="2/11/1997";
		log.info("TC_07_Create_New_Customer -Step 10:Enter to textbox 'Date Of Birth' with value: "+ customerDateOfBirth);
		newCustomerPage.enterToTextboxByID(driver,"DateOfBirth", customerDateOfBirth);			
		newCustomerPage.isJQueryLoadSuccess(driver);
		
		customerCompany =data.getCompanyName();
		log.info("TC_07_Create_New_Customer -Step 11:Enter to textbox 'Company' with value: "+ customerCompany);
		newCustomerPage.enterToTextboxByID(driver,"Company", customerCompany);			
		newCustomerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_07_Create_New_Customer -Step 12:Delete customer role: Registered");
		newCustomerPage.clickToDeleteCustomerRoleByName(driver, "Registered");
		newCustomerPage.isJQueryLoadSuccess(driver);
		
		customerRole="Guests";
		log.info("TC_07_Create_New_Customer -Step 13:Select customer role: "+ customerRole);
		newCustomerPage.selectItemInDropDownCustomerRole(driver, "Customer roles",customerRole);
		newCustomerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_07_Create_New_Customer -Step 14:Verify value of 'Customer Role' is: "+ customerRole);
		verifyTrue(newCustomerPage.isSelectedValueOfCustomerRoleDisplayByText(driver,customerRole));
		
		log.info("TC_07_Create_New_Customer -Step 15:Verify checkbox 'Active' is checked");
		verifyTrue(newCustomerPage.isCheckboxSelectedByID(driver, "Active"));
		
		log.info("TC_07_Create_New_Customer -Step 16:Click button 'Save' ");
		newCustomerPage.clickToButtonByAttributeName(driver, "save");
		customerPage= PageGeneratorManager.getCustomerPageObject(driver);
		customerPage.isJQueryLoadSuccess(driver);		
		
		log.info("TC_07_Create_New_Customer -Step 17:verify message 'The new customer has been added successfully.' is display");
		verifyTrue(customerPage.isSuccessMessageDisplay(driver,"The new customer has been added successfully."));
	
		//log.info("TC_01_Search_With_Product_Name -Step 01:Enter to 'Email' textbox with value: "+ customerEmail+" to search customer");
		//customerPage.enterToTextboxByID(driver, "SearchEmail", customerEmail);
		
		log.info("TC_07_Create_New_Customer -Step 18:Delete customer role: Registered");
		customerPage.clickToDeleteCustomerRoleByName(driver,"Registered");
		customerPage.isJQueryLoadSuccess(driver);
		
		customerRole="Guests";
		log.info("TC_07_Create_New_Customer -Step 19:Select customer role: "+ customerRole);
		customerPage.selectItemInDropDownCustomerRole(driver, "Customer roles",customerRole);
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_07_Create_New_Customer -Step 20:Verify value of 'Customer Role' is: "+ customerRole);
		verifyTrue(newCustomerPage.isSelectedValueOfCustomerRoleDisplayByText(driver,customerRole));
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_07_Create_New_Customer -Step 21:Click button 'Search'");
		customerPage.clickToButtonByID(driver, "search-customers");
		customerPage.isJQueryLoadSuccess(driver);
		
		customerFullName = customerFirstName +" " + customerLastName;
		log.info("TC_07_Create_New_Customer -Step 22:verify information customer is display");
		verifyTrue(customerPage.isInformationCustomerDisplay(driver, "Guest", customerFullName, customerRole, customerCompany, "Active"));
		
	}
	
	@Test
	public void TC_08_Search_Customer_With_Email() {
		log.info("TC_08_Search_Customer_With_Email -Step 01:Delete customer role: "+ customerRole);
		customerPage.clickToDeleteCustomerRoleByName(driver,customerRole);
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_08_Search_Customer_With_Email -Step 02:Click button 'Search'");
		customerPage.clickToButtonByID(driver, "search-customers");
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_08_Search_Customer_With_Email -Step 03:verify more than 1 customer is returned");
		verifyTrue(customerPage.getNumberCustomer()>1);
		
		log.info("TC_08_Search_Customer_With_Email -Step 04:Enter to 'Email' textbox with value: "+ customerEmail+" to search customer");
		customerPage.enterToTextboxByID(driver, "SearchEmail", customerEmail);
		
		log.info("TC_08_Search_Customer_With_Email -Step 05:verify data in textbox 'Email' is: "+ customerEmail );
		verifyEquals(customerPage.getTextInTextBoxByIDThroughJS(driver,"SearchEmail"), customerEmail);		
		
		log.info("TC_08_Search_Customer_With_Email -Step 06:Click button 'Search'");
		customerPage.clickToButtonByID(driver, "search-customers");
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_08_Search_Customer_With_Email -Step 07:verify only 1 customer is returned");
		verifyEquals(customerPage.getNumberCustomer(),1 );		
		 
		log.info("TC_08_Search_Customer_With_Email -Step 08:verify information customer is display");
		verifyTrue(customerPage.isInformationCustomerDisplay(driver, "Guest", customerFullName, customerRole, customerCompany, "Active"));
		
	}
	
	@Test
	public void TC_09_Search_Customer_With_FirstName_And_LastName() {	
		
		log.info("TC_09_Search_Customer_With_FirstName_And_LastName -Step 01:Remove data in 'Email' textbox ");
		customerPage.enterToTextboxByID(driver, "SearchEmail", "");
		
		log.info("TC_09_Search_Customer_With_FirstName_And_LastName -Step 02:verify data in textbox 'Email' is removed");
		verifyEquals(customerPage.getTextInTextBoxByIDThroughJS(driver,"SearchEmail"), "");		
		
		log.info("TC_09_Search_Customer_With_FirstName_And_LastName -Step 03:Click button 'Search'");
		customerPage.clickToButtonByID(driver, "search-customers");
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_09_Search_Customer_With_FirstName_And_LastName -Step 04:verify more than 1 customer is returned");
		verifyTrue(customerPage.getNumberCustomer()>1);
		
		log.info("TC_09_Search_Customer_With_FirstName_And_LastName -Step 05:Enter to 'FirstName' textbox ");
		customerPage.enterToTextboxByID(driver, "SearchFirstName",customerFirstName); 
		
		log.info("TC_09_Search_Customer_With_FirstName_And_LastName -Step 06:verify data in textbox 'FirstName' is:" + customerFirstName);
		verifyEquals(customerPage.getTextInTextBoxByIDThroughJS(driver,"SearchFirstName"), customerFirstName);			
		
		log.info("TC_09_Search_Customer_With_FirstName_And_LastName -Step 07:Enter to 'LastName' textbox ");
		customerPage.enterToTextboxByID(driver, "SearchLastName",customerLastName); 
		
		log.info("TC_09_Search_Customer_With_FirstName_And_LastName -Step 08:verify data in textbox 'LastName' is:" + customerLastName);
		verifyEquals(customerPage.getTextInTextBoxByIDThroughJS(driver,"SearchLastName"), customerLastName);		
		
		log.info("TC_09_Search_Customer_With_FirstName_And_LastName -Step 09:Click button 'Search'");
		customerPage.clickToButtonByID(driver, "search-customers");
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_09_Search_Customer_With_FirstName_And_LastName -Step 10:verify only 1 customer is returned");
		verifyEquals(customerPage.getNumberCustomer(),1 );
		
		log.info("TC_09_Search_Customer_With_FirstName_And_LastName -Step 11:verify information customer is display");
		verifyTrue(customerPage.isInformationCustomerDisplay(driver, "Guest", customerFullName, customerRole, customerCompany, "Active"));
		
	}
	
	@Test
	public void TC_10_Search_Customer_With_CompanyName_And_Role() {
		log.info("TC_10_Search_Customer_With_CompanyName_And_Role -Step 01:Remove data in 'FirstName' textbox ");
		customerPage.enterToTextboxByID(driver, "SearchFirstName", "");
		
		log.info("TC_10_Search_Customer_With_CompanyName_And_Role -Step 02:verify data in textbox 'FirstName' is removed");
		verifyEquals(customerPage.getTextInTextBoxByIDThroughJS(driver,"SearchFirstName"), "");				
		
		log.info("TC_10_Search_Customer_With_CompanyName_And_Role -Step 03:Remove data in 'LastName' textbox ");
		customerPage.enterToTextboxByID(driver, "SearchLastName", "");
		
		log.info("TC_10_Search_Customer_With_CompanyName_And_Role -Step 04:verify data in textbox 'LastName' is removed");
		verifyEquals(customerPage.getTextInTextBoxByIDThroughJS(driver,"SearchLastName"), "");				
		
		log.info("TC_10_Search_Customer_With_CompanyName_And_Role -Step 05:Click button 'Search'");
		customerPage.clickToButtonByID(driver, "search-customers");
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_10_Search_Customer_With_CompanyName_And_Role -Step 06:verify more than 1 customer is returned");
		verifyTrue(customerPage.getNumberCustomer()>1);
		
		log.info("TC_10_Search_Customer_With_CompanyName_And_Role -Step 07:Enter to 'Company' textbox with value: "+customerCompany);
		customerPage.enterToTextboxByID(driver, "SearchCompany",customerCompany); 
		
		log.info("TC_10_Search_Customer_With_CompanyName_And_Role -Step 08:verify data in textbox 'Company' is:" + customerCompany);
		verifyEquals(customerPage.getTextInTextBoxByIDThroughJS(driver,"SearchCompany"), customerCompany);			
		
		log.info("TC_10_Search_Customer_With_CompanyName_And_Role -Step 09:Select customer role: "+ customerRole);
		customerPage.selectItemInDropDownCustomerRole(driver, "Customer roles",customerRole);
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_10_Search_Customer_With_CompanyName_And_Role -Step 10:Verify value of 'Customer Role' is: "+ customerRole);
		verifyTrue(newCustomerPage.isSelectedValueOfCustomerRoleDisplayByText(driver,customerRole));
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_09_Search_With_FirstName_And_LastName -Step 11:Click button 'Search'");
		customerPage.clickToButtonByID(driver, "search-customers");
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_09_Search_With_FirstName_And_LastName -Step 12:verify only 1 customer is returned");
		verifyEquals(customerPage.getNumberCustomer(),1 );
		
		
		log.info("TC_09_Search_With_FirstName_And_LastName -Step 13:verify information customer is display");
		verifyTrue(customerPage.isInformationCustomerDisplay(driver, "Guest", customerFullName, customerRole, customerCompany, "Active"));
		
	}
	
	@Test
	public void TC_11_Search_Customer_With_Full_Data() {
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 01:Remove data in 'SearchCompany' textbox ");
		customerPage.enterToTextboxByID(driver, "SearchCompany", "");
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 02:Delete customer role: "+ customerRole);
		customerPage.clickToDeleteCustomerRoleByName(driver, customerRole);
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 03:Click button 'Search'");
		customerPage.clickToButtonByID(driver, "search-customers");
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 04:verify more than 1 customer is returned");
		verifyTrue(customerPage.getNumberCustomer()>1);
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 05:Enter to 'Email' textbox with value: "+ customerEmail+" to search customer");
		customerPage.enterToTextboxByID(driver, "SearchEmail", customerEmail);
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 06:verify data in textbox 'Email' is: "+ customerEmail );
		verifyEquals(customerPage.getTextInTextBoxByIDThroughJS(driver,"SearchEmail"), customerEmail);		
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 07:Enter to 'FirstName' textbox ");
		customerPage.enterToTextboxByID(driver, "SearchFirstName",customerFirstName); 
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 08:verify data in textbox 'FirstName' is:" + customerFirstName);
		verifyEquals(customerPage.getTextInTextBoxByIDThroughJS(driver,"SearchFirstName"), customerFirstName);			
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 09:Enter to 'LastName' textbox ");
		customerPage.enterToTextboxByID(driver, "SearchLastName",customerLastName); 
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 10:verify data in textbox 'LastName' is:" + customerLastName);
		verifyEquals(customerPage.getTextInTextBoxByIDThroughJS(driver,"SearchLastName"), customerLastName);		
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 11:select item in dropdown month with value:" + customerDateOfBirth.split("/")[0]);
		customerPage.selectItemInDropDownByName(driver, "SearchMonthOfBirth", customerDateOfBirth.split("/")[0]);
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 12:verify item in dropdown month is:" + customerDateOfBirth.split("/")[0]);
		verifyEquals(customerPage.getSelectedValueInDropDownByName(driver, "SearchMonthOfBirth"),customerDateOfBirth.split("/")[0]);
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 13:select item in dropdown day with value:" +customerDateOfBirth.split("/")[1]);
		customerPage.selectItemInDropDownByName(driver, "SearchDayOfBirth", customerDateOfBirth.split("/")[1]);
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 14:verify item in dropdown day is:" + customerDateOfBirth.split("/")[1]);
		verifyEquals(customerPage.getSelectedValueInDropDownByName(driver, "SearchDayOfBirth"),customerDateOfBirth.split("/")[1]);
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 15:Enter to 'Company' textbox with value: "+customerCompany);
		customerPage.enterToTextboxByID(driver, "SearchCompany",customerCompany); 
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 16:verify data in textbox 'Company' is:" + customerCompany);
		verifyEquals(customerPage.getTextInTextBoxByIDThroughJS(driver,"SearchCompany"), customerCompany);			
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 17:Select customer role: "+ customerRole);
		customerPage.selectItemInDropDownCustomerRole(driver, "Customer roles",customerRole);
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 18:Verify value of 'Customer Role' is: "+ customerRole);
		verifyTrue(newCustomerPage.isSelectedValueOfCustomerRoleDisplayByText(driver,customerRole));
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 19:Click button 'Search'");
		customerPage.clickToButtonByID(driver, "search-customers");
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 20:verify only 1 customer is returned");
		verifyEquals(customerPage.getNumberCustomer(),1 );		
		
		log.info("TC_11_Search_Customer_With_Full_Data -Step 21:verify information customer is display");
		verifyTrue(customerPage.isInformationCustomerDisplay(driver, "Guest", customerFullName, customerRole, customerCompany, "Active"));
		
	}
	
	@Test
	public void TC_12_Edit_Customer() {
		
		log.info("TC_12_Edit_Customer -Step 01:Click icon 'Edit'");
		customerDetailPage=customerPage.clickIconEditAtCustomerName(customerFullName);
		customerDetailPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_12_Edit_Customer -Step 02:verify data in textbox 'Email' is:" + customerEmail);
		verifyEquals(customerDetailPage.getAttributeTextboxByID(driver,"value", "Email"), customerEmail);		
		
		customerEditEmail="edit_"+ customerEmail;
		log.info("TC_12_Edit_Customer -Step 03:Enter to 'Email' textbox with value:"+ customerEditEmail);
		customerDetailPage.enterToTextboxByID(driver, "Email",customerEditEmail); 
		
		log.info("TC_12_Edit_Customer -Step 04:verify data in textbox 'FirstName' is:" + customerFirstName);
		verifyEquals(customerDetailPage.getAttributeTextboxByID(driver,"value", "FirstName"), customerFirstName);	
		
		customerEditFirstName="edit_"+ customerFirstName;
		log.info("TC_12_Edit_Customer -Step 05:Enter to 'FirstName' textbox with value:"+ customerEditFirstName);
		customerDetailPage.enterToTextboxByID(driver, "FirstName",customerEditFirstName);
		
		log.info("TC_12_Edit_Customer -Step 06:verify data in textbox 'LastName' is:" + customerLastName);
		verifyEquals(customerDetailPage.getAttributeTextboxByID(driver,"value", "LastName"), customerLastName);	
	
		log.info("TC_12_Edit_Customer -Step 07:verify radio button '" + gender+"' is selected");
		verifyTrue(customerDetailPage.isRadioButtonSelectedByID(driver,"Gender_Male"));
	
		log.info("TC_12_Edit_Customer -Step 08:verify data in textbox 'Date of birth' is:" + customerDateOfBirth);
		verifyEquals(customerDetailPage.getAttributeTextboxByID(driver,"value", "DateOfBirth"), customerDateOfBirth);	
		
		customerEditDateOfBirth ="3/12/1996";
		log.info("TC_12_Edit_Customer -Step 09:Enter to 'DateOfBirth' textbox with value:"+ customerEditDateOfBirth);
		customerDetailPage.enterToTextboxByID(driver, "DateOfBirth",customerEditDateOfBirth);
		
		log.info("TC_12_Edit_Customer -Step 10:verify data in textbox 'Company name' is:" + customerCompany);
		verifyEquals(customerDetailPage.getAttributeTextboxByID(driver,"value", "Company"), customerCompany);	
		
		customerEditCompany ="edit_"+customerCompany;
		log.info("TC_12_Edit_Customer -Step 11:Enter to 'Company name' textbox with value:"+ customerEditCompany);
		customerDetailPage.enterToTextboxByID(driver, "Company",customerEditCompany);
		
		log.info("TC_12_Edit_Customer -Step 12:Verify value of 'Customer Role' is: "+ customerRole);
		verifyTrue(customerDetailPage.isSelectedValueOfCustomerRoleDisplayByText(driver,customerRole));
		
		log.info("TC_12_Edit_Customer -Step 13:Verify checkbox 'Active' is checked");
		verifyTrue(customerDetailPage.isCheckboxSelectedByID(driver, "Active"));
		
		adminComment="Edit Customer";
		log.info("TC_12_Edit_Customer -Step 14:Enter to 'Admin comment' text area with value:"+ adminComment);
		customerDetailPage.enterToTextAreaboxByID(driver, "AdminComment",adminComment);
		
		log.info("TC_12_Edit_Customer -Step 15:Click button 'Save' ");
		customerDetailPage.clickToButtonByAttributeName(driver, "save");
		customerPage= PageGeneratorManager.getCustomerPageObject(driver);
		customerPage.isJQueryLoadSuccess(driver);	
		
		log.info("TC_12_Edit_Customer -Step 16:verify message 'The customer has been updated successfully.' is display");
		verifyTrue(customerPage.isSuccessMessageDisplay(driver,"The customer has been updated successfully."));
	
		log.info("TC_12_Edit_Customer -Step 17:Delete customer role: Registered");
		customerPage.clickToDeleteCustomerRoleByName(driver,"Registered");
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_12_Edit_Customer -Step 18:Select customer role: "+ customerRole);
		customerPage.selectItemInDropDownCustomerRole(driver, "Customer roles",customerRole);
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_12_Edit_Customer -Step 19:Verify value of 'Customer Role' is: "+ customerRole);
		verifyTrue(newCustomerPage.isSelectedValueOfCustomerRoleDisplayByText(driver,customerRole));
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_12_Edit_Customer -Step 20:Enter to 'Email' textbox with value: "+ customerEditEmail+" to search customer");
		customerPage.enterToTextboxByID(driver, "SearchEmail", customerEditEmail);
		
		log.info("TC_12_Edit_Customer -Step 21:verify data in textbox 'Email' is: "+ customerEditEmail );
		verifyEquals(customerPage.getTextInTextBoxByIDThroughJS(driver,"SearchEmail"), customerEditEmail);		
		
		log.info("TC_12_Edit_Customer -Step 22:Click button 'Search'");
		customerPage.clickToButtonByID(driver, "search-customers");
		customerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_12_Edit_Customer -Step 23:verify only 1 customer is returned");
		verifyEquals(customerPage.getNumberCustomer(),1 );		
		 
		customerEditFullName= customerEditFirstName +" "+ customerLastName;
		log.info("TC_12_Edit_Customer -Step 24:verify information customer is display");
		verifyTrue(customerPage.isInformationCustomerDisplay(driver, "Guest", customerEditFullName, customerRole, customerEditCompany, "Active"));
		
	}
	
	@Test
	public void TC_13_Add_New_Address_In_Customer_Detail() {
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 01:Click icon 'Edit' at customer name is : " + customerEditFullName);
		customerDetailPage=customerPage.clickIconEditAtCustomerName(customerEditFullName);
		customerDetailPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 02:Click to expand panel 'Address'");
		customerDetailPage.clickToExpandPanelByCardName(driver,"Address");
		customerDetailPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 03:Click to button 'Add new address'");
		customerDetailPage.clickToButtonByLabelName(driver, "Add new address");
		newAddressCustomerPage = PageGeneratorManager.getNewAddressCustomerPageObject(driver);
		newAddressCustomerPage.isJQueryLoadSuccess(driver);
		
		addressFirstName="Auto";
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 04:Enter to 'First name' textbox with value: "+ addressFirstName);
		newAddressCustomerPage.enterToTextboxByID(driver, "Address_FirstName", addressFirstName);
		
		addressLastName="FC";
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 05:Enter to 'Last name' textbox with value: "+ addressLastName);
		newAddressCustomerPage.enterToTextboxByID(driver, "Address_LastName", addressLastName);
		
		addressEmail = data.getEmailAddress();
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 06:Enter to 'Email' textbox with value: "+ addressEmail);
		newAddressCustomerPage.enterToTextboxByID(driver, "Address_Email", addressEmail);
		
		city="Washington";
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 07:Enter to 'City' textbox with value: "+ city);
		newAddressCustomerPage.enterToTextboxByID(driver, "Address_City", city);
		
		address_1="PVI";
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 08:Enter to 'Address 1' textbox with value: "+ address_1);
		newAddressCustomerPage.enterToTextboxByID(driver, "Address_Address1", address_1);
		
		zipCode =data.getZipCode();
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 09:Enter to 'Zip/ postal code' textbox with value: "+ zipCode);
		newAddressCustomerPage.enterToTextboxByID(driver, "Address_ZipPostalCode", zipCode);
		
		phoneNumber= data.getPhoneNumber();
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 10:Enter to 'Phone number' textbox with value: "+ phoneNumber);
		newAddressCustomerPage.enterToTextboxByID(driver, "Address_PhoneNumber", phoneNumber);
		
		faxNumber="84 (8) 3823 3318";
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 11:Enter to 'Fax number' textbox with value: "+ faxNumber);
		newAddressCustomerPage.enterToTextboxByID(driver, "Address_FaxNumber", faxNumber);
		
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 12:Click button 'Save' ");
		newAddressCustomerPage.clickToButtonByLabelName(driver, "Save");
		editAddressCustomerPage= PageGeneratorManager.getEditAddressCustomerPageObject(driver);
		editAddressCustomerPage.isJQueryLoadSuccess(driver);	
		
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 13:verify message 'The new address has been added successfully. ' is display");
		verifyTrue(editAddressCustomerPage.isSuccessMessageDisplay(driver,"The new address has been added successfully."));
			
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 14:verify data in textbox 'First name' is:" + addressFirstName);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_FirstName"), addressFirstName);	
		
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 15:verify data in textbox 'Last name' is:" + addressLastName);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_LastName"), addressLastName);	
		
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 16:verify data in textbox 'Email' is:" + addressEmail);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_Email"), addressEmail);	
		
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 17:verify data in textbox 'City' is:" + city);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_City"), city);	
		
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 18:verify data in textbox 'Address 1' is:" + address_1);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_Address1"), address_1);	
		
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 19:verify data in textbox 'Zip/ postal code' is:" + zipCode);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_ZipPostalCode"), zipCode);	
		
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 20:verify data in textbox 'Phone Number' is:" + phoneNumber);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_PhoneNumber"), phoneNumber);	
				
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 21:verify data in textbox 'Fax Number' is:" + faxNumber);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_FaxNumber"), faxNumber);	

		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 22:Click to link 'back to customer detail' ");
		editAddressCustomerPage.clickToLinkByText(driver,"back to customer details");
		customerDetailPage= PageGeneratorManager.getCustomerDetailPageObject(driver);
		customerDetailPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 23: Verify 'First name' of address is: "+ addressFirstName);		
		verifyEquals(customerDetailPage.getRowValueByColumn("1","First name"),addressFirstName);
		
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 24: Verify 'Last name' of address is: "+ addressLastName);		
		verifyEquals(customerDetailPage.getRowValueByColumn("1","Last name"),addressLastName);
		
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 25: Verify 'Email' of address is: "+ addressEmail);		
		verifyEquals(customerDetailPage.getRowValueByColumn("1","Email"),addressEmail);
		
		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 26: Verify 'Phone number' of address is: "+ phoneNumber);		
		verifyEquals(customerDetailPage.getRowValueByColumn("1","Phone number"),phoneNumber);

		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 27: Verify 'Fax number' of address is: "+ faxNumber);		
		verifyEquals(customerDetailPage.getRowValueByColumn("1","Fax number"),faxNumber);

		log.info("TC_13_Add_New_Address_In_Customer_Detail -Step 28: Verify 'Address' of address is: "+ address_1+"\n"+city+","+zipCode);		
		verifyEquals(customerDetailPage.getRowValueByColumn("1","Address"), address_1+"\n"+city+","+zipCode);
		
	}
	@Test
	public void TC_14_Edit_Address_In_Customer_Detail() {
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 01: Click Edit button at row have email:"+ addressEmail);		
		editAddressCustomerPage=customerDetailPage.clickIconEditAtEmailAddress(addressEmail);
		editAddressCustomerPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 02:verify data in textbox 'First name' is:" + addressFirstName);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_FirstName"), addressFirstName);	
		
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 03:verify data in textbox 'Last name' is:" + addressLastName);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_LastName"), addressLastName);	
		
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 04:verify data in textbox 'Email' is:" + addressEmail);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_Email"), addressEmail);	
		
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 05:verify data in textbox 'City' is:" + city);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_City"), city);	
		
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 06:verify data in textbox 'Address 1' is:" + address_1);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_Address1"), address_1);	
		
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 07:verify data in textbox 'Zip/ postal code' is:" + zipCode);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_ZipPostalCode"), zipCode);	
		
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 08:verify data in textbox 'Phone Number' is:" + phoneNumber);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_PhoneNumber"), phoneNumber);	
				
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 09:verify data in textbox 'Fax Number' is:" + faxNumber);
		verifyEquals(editAddressCustomerPage.getAttributeTextboxByID(driver,"value", "Address_FaxNumber"), faxNumber);	
		editAddressCustomerPage.sleepInSecond(5);
		
		editAddressFirstName="edit_"+ addressFirstName;
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 10:Enter to 'First name' textbox with value: "+ editAddressFirstName);
		editAddressCustomerPage.enterToTextboxByID(driver, "Address_FirstName", editAddressFirstName);
		
		editAddressLastName="edit_"+ addressLastName;
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 11:Enter to 'Last name' textbox with value: "+ editAddressLastName);
		editAddressCustomerPage.enterToTextboxByID(driver, "Address_LastName", editAddressLastName);
				
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 12:Click button 'Save' ");
		editAddressCustomerPage.clickToButtonByAttributeName(driver, "save");
		editAddressCustomerPage.isJQueryLoadSuccess(driver);	
		
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 13:verify message 'The address has been updated successfully. ' is display");
		verifyTrue(editAddressCustomerPage.isSuccessMessageDisplay(driver,"The address has been updated successfully."));
			
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 14:Click to link 'back to customer detail' ");
		editAddressCustomerPage.clickToLinkByText(driver,"back to customer details");
		customerDetailPage= PageGeneratorManager.getCustomerDetailPageObject(driver);
		customerDetailPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 15: Verify 'First name' of address is: "+ editAddressFirstName);		
		verifyEquals(customerDetailPage.getRowValueByColumn("1","First name"),editAddressFirstName);
		
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 16: Verify 'Last name' of address is: "+ editAddressLastName);		
		verifyEquals(customerDetailPage.getRowValueByColumn("1","Last name"),editAddressLastName);
		
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 17: Verify 'Email' of address is: "+ addressEmail);		
		verifyEquals(customerDetailPage.getRowValueByColumn("1","Email"),addressEmail);
		
		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 18: Verify 'Phone number' of address is: "+ phoneNumber);		
		verifyEquals(customerDetailPage.getRowValueByColumn("1","Phone number"),phoneNumber);

		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 19: Verify 'Fax number' of address is: "+ faxNumber);		
		verifyEquals(customerDetailPage.getRowValueByColumn("1","Fax number"),faxNumber);

		log.info("TC_14_Edit_Address_In_Customer_Detail -Step 20: Verify 'Address' of address is: "+ address_1+"\n"+city+","+zipCode);		
		verifyEquals(customerDetailPage.getRowValueByColumn("1","Address"), address_1+"\n"+city+","+zipCode);
		
	}
	
	@Test
	public void TC_15_Delete_Address_In_Customer_Detail() {
		log.info("TC_15_Delete_Address_In_Customer_Detail -Step 01:Click delete button at row have email:"+ addressEmail);		
		customerDetailPage.clickIconDeletAtEmailAddress(addressEmail);
		customerDetailPage.acceptAlert(driver);
		customerDetailPage.isJQueryLoadSuccess(driver);
		
		log.info("TC_15_Delete_Address_In_Customer_Detail -Step 02:verify message 'No data available in table' is display");		
		verifyTrue(customerDetailPage.isMessageNoDataInPanelAddressDisplay());
		
	}
	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName) {
		log.info("Postcondition: Close the browser:" + browserName);
		cleanBrowserAndDriver();
	}

}
