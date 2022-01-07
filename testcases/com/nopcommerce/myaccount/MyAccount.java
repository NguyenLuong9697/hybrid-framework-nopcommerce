package com.nopcommerce.myaccount;

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
import payObjects.MyAccountPageObject;
import payObjects.PageGeneratorManager;
import payObjects.ProductDetailPageObject;
import payObjects.ProductPageObject;
import payObjects.ProductReviewPageObject;
import payObjects.RegisterPageObject;
import serverConfig.IServer;
import utilities.DataUtil;

public class MyAccount extends BaseTest {

	WebDriver driver;
	IServer server;
	HomePageObject homePageObject;
	LoginPageObject loginPageObject;
	RegisterPageObject registerPageObject;
	MyAccountPageObject myAccountPageObject;
	ProductPageObject productPageObject;
	ProductDetailPageObject productDetailPageObject;
	ProductReviewPageObject productReviewPageObject;
	DataUtil data;
	String firstName, lastName, fullName, emailAddress, password, confirmPassword, newPassword, confirmNewPassword;
	String editFirstName, editLastName, companyName, date, month, year;
	String addressFirstName, addressLastName, addressEmail, company,country,state,city, address_1, zipCode, phoneNumber;

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
		
		firstName=data.getFirstName();
		log.info("Precondition 03: Enter to First Name: "+ firstName);
		registerPageObject.enterToTextboxByID(driver, "FirstName", firstName);

		lastName =data.getLastName();
		log.info("Precondition 04: Enter to Last Name: " + lastName);
		registerPageObject.enterToTextboxByID(driver, "LastName", lastName);

		emailAddress=data.getEmailAddress();
		log.info("Precondition 05: Enter to Email: "+ emailAddress);
		registerPageObject.enterToTextboxByID(driver, "Email", emailAddress);

		password="123456";
		log.info("Precondition 06: Enter to Password: "+ password);
		registerPageObject.enterToTextboxByID(driver, "Password", password);
	
		confirmPassword="123456";
		log.info("Precondition 07: Enter to Confirm password: "+ confirmPassword);
		registerPageObject.enterToTextboxByID(driver, "ConfirmPassword", confirmPassword);
		
		log.info("Precondition 08: Click to Register button");
		registerPageObject.clickToButtonByLabelName(driver, "Register");	

		log.info("Precondition 09: verify register message successful");
		verifyEquals(registerPageObject.getRegisterMessageSuccessfull(),"Your registration completed");
			
	}

	@Test
	public void TC_01_Update_Customer_Info() {
		log.info("TC_01_Update_Customer_Info - Step 01: Click My Account Link");
		registerPageObject.clickToHeaderLinkByLabelName(driver, "My account");
		myAccountPageObject = PageGeneratorManager.getMyAccountPageObject(driver);

		log.info("TC_01_Update_Customer_Info - Step 02: Verify First Name before edit");
		verifyEquals(myAccountPageObject.getDataInTextboxByID(driver,"FirstName"), firstName);
		
		log.info("TC_01_Update_Customer_Info - Step 03: Verify Last Name before edit");
		verifyEquals(myAccountPageObject.getDataInTextboxByID(driver,"LastName"), lastName);
		
		log.info("TC_01_Update_Customer_Info - Step 04: Click to Female radio button");
		myAccountPageObject.clickToRadioButtonByLabelName(driver,"Female");
		
		editFirstName = data.getFirstName() ;
		log.info("TC_01_Update_Customer_Info - Step 05: Enter to FirstName textbox");
		myAccountPageObject.enterToTextboxByID(driver,"FirstName",editFirstName);
		
		editLastName = data.getLastName();
		log.info("TC_01_Update_Customer_Info - Step 06: Enter to LastName textbox");
		myAccountPageObject.enterToTextboxByID(driver,"LastName",editLastName);
		
		date="9";
		log.info("TC_01_Update_Customer_Info - Step 07: Slect Day in dropdown");
		myAccountPageObject.selectItemInDropDownByName(driver,"DateOfBirthDay",date);
		
		month="June";
		log.info("TC_01_Update_Customer_Info - Step 08: Slect month in dropdown");
		myAccountPageObject.selectItemInDropDownByName(driver,"DateOfBirthMonth",month);
		
		year="1997";
		log.info("TC_01_Update_Customer_Info - Step 09: Slect year in dropdown");
		myAccountPageObject.selectItemInDropDownByName(driver,"DateOfBirthYear",year);
		
		companyName="Automation";
		log.info("TC_01_Update_Customer_Info - Step 10: Enter to Company textbox");
		myAccountPageObject.enterToTextboxByID(driver,"Company", companyName);
		
		log.info("TC_01_Update_Customer_Info- Step 11: Click Save button");
		myAccountPageObject.clickToButtonByLabelName(driver, "Save");
		
		log.info("TC_01_Update_Customer_Info - Step 12: verify radio button fmale is slected  ");
		verifyTrue(myAccountPageObject.isRadioButtonSelectedByLabelName(driver, "Female"));

		log.info("TC_01_Update_Customer_Info - Step 13: Verify First Name after edit");
		verifyEquals(myAccountPageObject.getDataInTextboxByID(driver,"FirstName"), editFirstName);
		
		log.info("TC_01_Update_Customer_Info - Step 14: Verify Last Name after edit");
		verifyEquals(myAccountPageObject.getDataInTextboxByID(driver,"LastName"), editLastName);
		
		log.info("TC_01_Update_Customer_Info - Step 15: verify selected date in dropdown list ");
		verifyEquals(myAccountPageObject.getSelectedValueInDropdrownByName(driver,"DateOfBirthDay"),date);
		
		log.info("TC_01_Update_Customer_Info - Step 16: verify selected month in dropdown list ");
		verifyEquals(myAccountPageObject.getSelectedValueInDropdrownByName(driver,"DateOfBirthMonth"),month);
		
		log.info("TC_01_Update_Customer_Info - Step 17: verify selected year in dropdown list ");
		verifyEquals(myAccountPageObject.getSelectedValueInDropdrownByName(driver,"DateOfBirthYear"),year);

		log.info("TC_01_Update_Customer_Info - Step 18: Verify Company name ");
		verifyEquals(myAccountPageObject.getDataInTextboxByID(driver,"Company"), companyName);
		
	}
	
	@Test
	public void TC_02_Add_Address() {
		log.info("TC_02_Add_Address - Step 01: Click Address in left menu");
		myAccountPageObject.clickToPageInLeftMenuByLabelName(driver,"Addresses");
		
		log.info("TC_02_Add_Address - Step 02: Click button Add new ");
		myAccountPageObject.clickToButtonByLabelName(driver,"Add new");
		
		addressFirstName =data.getFirstNameAddress();
		log.info("TC_02_Add_Address - Step 03: Enter to First Name:  "+ addressFirstName);
		myAccountPageObject.enterToTextboxByID(driver, "Address_FirstName", addressFirstName);
		
		addressLastName =data.getLastNameAddress();
		log.info("TC_02_Add_Address - Step 04: Enter to First Name:  "+ addressLastName);
		myAccountPageObject.enterToTextboxByID(driver, "Address_LastName", addressLastName);
		
		addressEmail  =data.getEmailAddress();
		log.info("TC_02_Add_Address - Step 05: Enter to Email:  "+ addressEmail);
		myAccountPageObject.enterToTextboxByID(driver, "Address_Email", addressEmail);
		
		company = data.getCompanyName();
		log.info("TC_02_Add_Address - Step 06: Enter to Company:  "+ company);
		myAccountPageObject.enterToTextboxByID(driver, "Address_Company", company);
		
		country ="United States";
		log.info("TC_02_Add_Address - Step 07: Slect country in dropdown");
		myAccountPageObject.selectItemInDropDownByName(driver,"Address.CountryId",country);
		
		state ="Alabama";
		log.info("TC_02_Add_Address - Step 08: Slect state in dropdown");
		myAccountPageObject.selectItemInDropDownByName(driver,"Address.StateProvinceId",state);
		
		city = data.getCityName();
		log.info("TC_02_Add_Address - Step 09: Enter to City:  "+ city);
		myAccountPageObject.enterToTextboxByID(driver, "Address_City", city);
		
		address_1= data.getFullAddress();
		log.info("TC_02_Add_Address - Step 10: Enter to Address 1:  "+ address_1);
		myAccountPageObject.enterToTextboxByID(driver, "Address_Address1", address_1);
		
		zipCode= data.getZipCode();
		log.info("TC_02_Add_Address - Step 11: Enter to Zip/ postal code:  "+ zipCode);
		myAccountPageObject.enterToTextboxByID(driver, "Address_ZipPostalCode", zipCode);
		
		phoneNumber= data.getPhoneNumber();
		log.info("TC_02_Add_Address - Step 12: Enter to phone number:  "+ phoneNumber);
		myAccountPageObject.enterToTextboxByID(driver, "Address_PhoneNumber", phoneNumber);
		
		log.info("TC_02_Add_Address - Step 13: Click to Save button");
		myAccountPageObject.clickToButtonByLabelName(driver, "Save");	
		
		log.info("TC_02_Add_Address- Step 14: Verify first name and last name of address");
		verifyEquals(myAccountPageObject.getInformationOfAddressByClassName(driver,"name"), addressFirstName+" "+ addressLastName);
		
		log.info("TC_02_Add_Address - Step 15: Verify email of address");		
		verifyEquals(myAccountPageObject.getInformationOfAddressByClassName(driver,"email"), "Email: "+ addressEmail);
		
		log.info("TC_02_Add_Address -Step 16: Verify phone number of address");		
		verifyEquals(myAccountPageObject.getInformationOfAddressByClassName(driver,"phone"), "Phone number: "+ phoneNumber);
		
		log.info("TC_02_Add_Address -Step 17: Verify fax number of address");		
		verifyEquals(myAccountPageObject.getInformationOfAddressByClassName(driver,"fax"), "Fax number:");
		
		log.info("TC_02_Add_Address - Step 18: Verify phone company of address");		
		verifyEquals(myAccountPageObject.getInformationOfAddressByClassName(driver,"company"), company);
		
		log.info("TC_02_Add_Address - Step 19: Verify phone address 1 of address");		
		verifyEquals(myAccountPageObject.getInformationOfAddressByClassName(driver,"address1"),address_1);
		
		log.info("TC_02_Add_Address - Step 20: Verify phone state of address");		
		verifyEquals(myAccountPageObject.getInformationOfAddressByClassName(driver,"city-state-zip"),city+", "+ state+", "+zipCode);
		
		log.info("TC_02_Add_Address - Step 21: Verify phone country of address");		
		verifyEquals(myAccountPageObject.getInformationOfAddressByClassName(driver,"country"), country);
		
	}

	@Test
	public void TC_03_Change_Password() {
		log.info("TC_03_Change_Password - Step 01: Click Change password in left menu");
		myAccountPageObject.clickToPageInLeftMenuByLabelName(driver,"Change password");
		
		log.info("TC_03_Change_Password - Step 02: Enter to old password: "+ password);
		myAccountPageObject.enterToTextboxByID(driver,"OldPassword", password);
		
		newPassword="111111";
		log.info("TC_03_Change_Password - Step 03: Enter to new password: "+ newPassword);
		myAccountPageObject.enterToTextboxByID(driver,"NewPassword", newPassword);
		
		confirmNewPassword="111111";
		log.info("TC_03_Change_Password - Step 04: Enter to confirm new password: "+ confirmNewPassword);
		myAccountPageObject.enterToTextboxByID(driver,"ConfirmNewPassword", confirmNewPassword);
		
		log.info("TC_03_Change_Password - Step 05: Click button Change password");
		myAccountPageObject.clickToButtonByLabelName(driver, "Change password");	
		
		log.info("TC_03_Change_Password - Step 06: Verify messeage successfull after change password");
		verifyEquals(myAccountPageObject.getMessageSuccessfullAfterChangePassword(),"Password was changed");
		
		log.info("TC_03_Change_Password - Step 07: Click close message success");
		myAccountPageObject.clickButtonCloseMessageSuccess();
		
		log.info("TC_03_Change_Password - Step 08: Click My Account in footer");
		myAccountPageObject.clickToFooterLinkByLabelName(driver,"My account");
		
		log.info("TC_03_Change_Password - Step 09: Click Log out Link");
		myAccountPageObject.clickToHeaderLinkByLabelName(driver, "Log out");
		homePageObject = PageGeneratorManager.getHomePageObject(driver);
		
		log.info("TC_03_Change_Password - Step 10: Click Log in Link");
		homePageObject.clickToHeaderLinkByLabelName(driver, "Log in");
		loginPageObject = PageGeneratorManager.getLoginPageObject(driver);
		
		log.info("TC_03_Change_Password - Step 11: Enter to email text box with email:"+emailAddress);
		loginPageObject.enterToTextboxByID(driver, "Email", emailAddress);
		
		log.info("TC_03_Change_Password - Step 12: Enter to password text box with old password:"+password);
		loginPageObject.enterToTextboxByID(driver, "Password", password);
		
		log.info("TC_03_Change_Password - Step 13: Click Log in button");
		loginPageObject.clickToButtonByLabelName(driver, "Log in");
		
		log.info("TC_03_Change_Password - Step 14: verify error message ");
		verifyEquals(loginPageObject.getErrorMessage(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
		log.info("TC_03_Change_Password- Step 15: Click to Log in link");
		homePageObject.clickToHeaderLinkByLabelName(driver, "Log in");
		loginPageObject = PageGeneratorManager.getLoginPageObject(driver);
		
		log.info("TC_03_Change_Password - Step 15: Enter to Email: "+ emailAddress);
		loginPageObject.enterToTextboxByID(driver, "Email",emailAddress);

		
		log.info("TC_03_Change_Password - Step 16: Enter to password with new password: "+newPassword);
		loginPageObject.enterToTextboxByID(driver, "Password",newPassword);
		
		log.info("TC_03_Change_Password- Step 17: Click Log in button");
		loginPageObject.clickToButtonByLabelName(driver, "Log in");
		homePageObject = PageGeneratorManager.getHomePageObject(driver);
		
		log.info("TC_03_Change_Password: Step 18: Verfify link My account display");
		verifyTrue(homePageObject.isHeaderLinkDisplayByLabelName(driver,"My account"));
		
		log.info("TC_03_Change_Password: Step 19: Verfify link Log out display");
		verifyTrue(homePageObject.isHeaderLinkDisplayByLabelName(driver,"Log out"));
		
	}
	
	@Test
	public void TC_04_My_Product_Review() {
		log.info("TC_04_My_Product_Review: Step 01: Click menu Jewelry");
		homePageObject.clickToMenuPageByLabelName(driver,"Jewelry");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);
		
		log.info("TC_04_My_Product_Review: Step 02: Click item Flower Girl Bracelet");
		productDetailPageObject=productPageObject.clickToProductByName(driver,"Flower Girl Bracelet");
		
		log.info("TC_04_My_Product_Review: Step 03: Click to Add your review");
		productReviewPageObject=productDetailPageObject.clickLinkAddYourReview();
		
		String reviewTitle="Review product Flower Girl Bracelet" + randomNumber();
		log.info("TC_04_My_Product_Review: Step 04: Enter to Review title textbox: "+reviewTitle);
		productReviewPageObject.enterToTextboxByID(driver, "AddProductReview_Title", reviewTitle);
		
		String reviewText="Beautiful" +randomNumber();
		log.info("TC_04_My_Product_Review: Step 05: Enter to Review text textbox: "+reviewText);
		productReviewPageObject.enterToTextAreaboxByID(driver, "AddProductReview_ReviewText", reviewText);
	
		
		log.info("TC_04_My_Product_Review: Step 06: Select rating");
		productReviewPageObject.selectRating(driver,"Good");
	
		log.info("TC_04_My_Product_Review: Step 07: Click button Submit Review");
		productReviewPageObject.clickToButtonByLabelName(driver,"Submit review");
		
		log.info("TC_04_My_Product_Review: Step 08: Verify success message after submit review");
		verifyEquals(productReviewPageObject.getSuccessMessageAfterSubmitReview(), "Product review is successfully added.");

		log.info("TC_04_My_Product_Review: Step 09: Verify title of review is display");
		verifyTrue(productReviewPageObject.isTitleOfReviewDisplay(driver,reviewTitle));
		
		log.info("TC_04_My_Product_Review: Step 10: Verify text of review is display");
		verifyTrue(productReviewPageObject.isTextOfReviewDisplay(driver,reviewText));
		
		log.info("TC_04_My_Product_Review: Step 11: Verify user name of review is display");
		verifyTrue(productReviewPageObject.isUserNameOfReviewDisplay(driver,editFirstName));
		
		log.info("TC_04_My_Product_Review: Step 12: Verify date time of review is display");
		verifyTrue(productReviewPageObject.isDateTimeOfReviewDisplay(driver,getDatTimeNow()));
		

	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void afterClass(@Optional("chrome")String browserName) {
		log.info("Postcondition: Close the browser:" + browserName);
		cleanBrowserAndDriver();
	}

}
