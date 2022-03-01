package com.nopcommerce.order;

import java.time.LocalDateTime;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import pageObjects.user.CheckoutPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.MyAccountPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.ProductDetailPageObject;
import pageObjects.user.ProductPageObject;
import pageObjects.user.ProductReviewPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.ShoppingCartPageObject;
import serverConfig.IServer;
import utilities.DataUtil;

public class Order extends BaseTest {

	WebDriver driver;
	IServer server;
	HomePageObject homePageObject;
	RegisterPageObject registerPageObject;
	ProductDetailPageObject productDetailPageObject;
	ProductPageObject productPageObject;
	ShoppingCartPageObject shoppingCartPageObject;
	CheckoutPageObject checkoutPageObject;
	MyAccountPageObject myAccountPageObject;
	DataUtil data;
	String firstName, lastName, fullName, emailAddress, password, confirmPassword, newPassword, confirmNewPassword;
	String nameProductBuy, ram, hdd, osname, software, processor;
	int orderNumber;
	String country,editNumberNeedBuy, state, city, address_1, zipCode, phoneNumber;
	String dayOfWeek, month, year, dayOfMonth;
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

		firstName = "John";
		log.info("Precondition 03: Enter to First Name: " + firstName);
		registerPageObject.enterToTextboxByID(driver, "FirstName", firstName);
		
		lastName= "Week"+ randomNumber();
		log.info("Precondition 04: Enter to Last Name: " + lastName);
		registerPageObject.enterToTextboxByID(driver, "LastName", lastName);
		
		emailAddress = data.getEmailAddress();
		log.info("Precondition 05: Enter to Email: " + emailAddress);
		registerPageObject.enterToTextboxByID(driver, "Email", emailAddress);

		password = "123456";
		log.info("Precondition 06: Enter to Password: " + password);
		registerPageObject.enterToTextboxByID(driver, "Password", password);

		confirmPassword = "123456";
		log.info("Precondition 07: Enter to Confirm password: " + confirmPassword);
		registerPageObject.enterToTextboxByID(driver, "ConfirmPassword", confirmPassword);

		log.info("Precondition 08: Click to Register button");
		registerPageObject.clickToButtonByLabelName(driver, "Register");

		log.info("Precondition 09: verify register message successful");
		verifyEquals(registerPageObject.getRegisterMessageSuccessfull(), "Your registration completed");

	}

	@Test
	public void TC_01_Add_Product_To_Cart() {
		log.info("TC_01_Add_Product_To_Cart -Step 01: Click sub menu  Desktops ");
		registerPageObject.openSubMenuByLabelName(driver, "Computers", "Desktops");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);

		nameProductBuy = "Build your own computer";
		log.info("TC_01_Add_Product_To_Cart: Step 02: Click product: " + nameProductBuy);
		productDetailPageObject = productPageObject.clickToProductByName(driver, nameProductBuy);

		ram = "4GB [+$20.00]";
		log.info("TC_01_Add_Product_To_Cart: Step 03: Select RAM: " + ram);
		productDetailPageObject.selectItemInDropDownBySpecificity("RAM", ram);

		log.info("TC_01_Add_Product_To_Cart: Step 04: verify RAM is " + ram);
		verifyEquals(productDetailPageObject.getSelectedValueInDropdrownBySpecificityProduct("RAM"), ram);

		hdd = "320 GB";
		log.info("TC_01_Add_Product_To_Cart: Step 05: Select HDD: " + hdd);
		productDetailPageObject.selectSpecificityOfProduct("HDD", hdd);

		log.info("TC_01_Add_Product_To_Cart: Step 06: verify hdd is " + hdd + " is selected");
		verifyTrue(productDetailPageObject.isRadioButtonOrCheckBoxSelectedBySpecificityProduct("HDD", hdd));

		log.info("TC_01_Add_Product_To_Cart: Step 07: Click to Add to cart");
		productDetailPageObject.clickToButtonByLabelName(driver, "Add to cart");

		log.info("TC_01_Add_Product_To_Cart - Step 08:Verify product added into cart");
		verifyEquals(productDetailPageObject.getMessageSuccessfullAfterAddToCart(),
				"The product has been added to your shopping cart");

		log.info("TC_01_Add_Product_To_Cart - Step 09:Click Shopping cart in footer");
		productDetailPageObject.clickToFooterLinkByLabelName(driver, "Shopping cart");
		shoppingCartPageObject = PageGeneratorManager.getShoppingCartPageObject(driver);

		log.info("TC_01_Add_Product_To_Cart - Step 10:Verify product:" + nameProductBuy
				+ " is displayed in shopping cart");
		verifyTrue(shoppingCartPageObject.isProductDisplayed(driver,"COMP_CUST", nameProductBuy, "$1,335.00", "$1,335.00"));

		log.info(
				"TC_01_Add_Product_To_Cart - Step 11:Verify specificity (processor, ram, hdd, os , software) of product:"
						+ nameProductBuy + " is displayed");
		verifyEquals(shoppingCartPageObject.isSpecificityDisplay(nameProductBuy),
				"Processor: 2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]\nRAM: 4GB [+$20.00]\nHDD: 320 GB\nOS: Vista Home [+$50.00]\nSoftware: Microsoft Office [+$50.00]");
	}

	@Test
	public void TC_02_Update_Product_In_Shopping_Cart() {

		log.info("TC_02_Update_Product_In_Shopping_Cart - Step 01:Click to Link Edit");
		productDetailPageObject = shoppingCartPageObject.clickLinkEditAtProduct(nameProductBuy);

		processor = "2.2 GHz Intel Pentium Dual-Core E2200";
		log.info("TC_02_Update_Product_In_Shopping_Cart: Step 02: Select processor: " + processor);
		productDetailPageObject.selectItemInDropDownBySpecificity("Processor", processor);

		log.info("TC_02_Update_Product_In_Shopping_Cart: Step 03: verify processsor is " + processor);
		verifyEquals(productDetailPageObject.getSelectedValueInDropdrownBySpecificityProduct("Processor"), processor);

		ram = "2 GB";
		log.info("TC_02_Update_Product_In_Shopping_Cart: Step 04: Select RAM: " + ram);
		productDetailPageObject.selectItemInDropDownBySpecificity("RAM", ram);

		log.info("TC_02_Update_Product_In_Shopping_Cart: Step 05: verify RAM is " + ram);
		verifyEquals(productDetailPageObject.getSelectedValueInDropdrownBySpecificityProduct("RAM"), ram);

		hdd = "400 GB [+$100.00]";
		log.info("TC_02_Update_Product_In_Shopping_Cart: Step 06: Select HDD: " + hdd);
		productDetailPageObject.selectSpecificityOfProduct("HDD", hdd);

		log.info("TC_02_Update_Product_In_Shopping_Cart: Step 07: verify hdd is " + hdd + " is selected");
		verifyTrue(productDetailPageObject.isRadioButtonOrCheckBoxSelectedBySpecificityProduct("HDD", hdd));

		osname = "Vista Premium [+$60.00]";
		log.info("TC_02_Update_Product_In_Shopping_Cart: Step 08: Select OS: " + osname);
		productDetailPageObject.selectSpecificityOfProduct("OS", osname);

		log.info("TC_02_Update_Product_In_Shopping_Cart: Step 09: verify os is " + osname + " is selected");
		verifyTrue(productDetailPageObject.isRadioButtonOrCheckBoxSelectedBySpecificityProduct("OS", osname));

		log.info("TC_02_Update_Product_In_Shopping_Cart: Step 10: Uncheck software: Microsoft Office [+$50.00]");
		productDetailPageObject.selectSpecificityOfProduct("Software", "Microsoft Office [+$50.00]");

		log.info("TC_02_Update_Product_In_Shopping_Cart: Step 11: verify software is Microsoft Office [+$50.00] "
				+ " is not selected");
		verifyFalse(productDetailPageObject.isRadioButtonOrCheckBoxSelectedBySpecificityProduct("Software",
				"Microsoft Office [+$50.00]"));

		software = "Total Commander [+$5.00]";
		log.info("TC_02_Update_Product_In_Shopping_Cart: Step 12: Select software: " + software);
		productDetailPageObject.selectSpecificityOfProduct("Software", software);

		log.info("TC_02_Update_Product_In_Shopping_Cart: Step 13: verify softare: " + software + " is selected");
		verifyTrue(productDetailPageObject.isRadioButtonOrCheckBoxSelectedBySpecificityProduct("Software", software));

		editNumberNeedBuy = "2";
		log.info("TC_02_Update_Product_In_Shopping_Cart - Step 14:Enter: '" + editNumberNeedBuy + "' into textbox");
		productDetailPageObject.enterToTextboxByID(driver, "product_enteredQuantity_1", editNumberNeedBuy);

		log.info("TC_02_Update_Product_In_Shopping_Cart: Step 15: Click to Update button");
		productDetailPageObject.clickToButtonByLabelName(driver, "Update");

		log.info("TC_02_Update_Product_In_Shopping_Cart - Step 16:Verify product added into cart");
		verifyEquals(productDetailPageObject.getMessageSuccessfullAfterAddToCart(),
				"The product has been added to your shopping cart");

		log.info("TC_02_Update_Product_In_Shopping_Cart - Step 17:Click Shopping cart in footer");
		productDetailPageObject.clickToFooterLinkByLabelName(driver, "Shopping cart");
		shoppingCartPageObject = PageGeneratorManager.getShoppingCartPageObject(driver);

		log.info("TC_02_Update_Product_In_Shopping_Cart - Step 18:Verify product:" + nameProductBuy
				+ " is displayed in shopping cart");
		verifyTrue(shoppingCartPageObject.isProductDisplayed(driver,"COMP_CUST", nameProductBuy, "$1,365.00", "$2,730.00"));

		log.info(
				"TC_02_Update_Product_In_Shopping_Cart - Step 19:Verify specificity (processor, ram, hdd, os , software) of product:"
						+ nameProductBuy + " is displayed");
		verifyEquals(shoppingCartPageObject.isSpecificityDisplay(nameProductBuy),
				"Processor: 2.2 GHz Intel Pentium Dual-Core E2200\nRAM: 2 GB\nHDD: 400 GB [+$100.00]\nOS: Vista Premium [+$60.00]\nSoftware: Total Commander [+$5.00]");

	}

	@Test
	public void TC_03_Remove_From_Cart() {
		log.info("TC_03_Remove_From_Cart - Step 01:Click icon 'Remove' at row contain product:" + "'" + nameProductBuy
				+ "'");
		shoppingCartPageObject.clickIconRemoveThroughProductName(nameProductBuy);

		log.info("TC_03_Remove_From_Cart - Step 02 : Verify message 'Your Shopping Cart is empty!' is displayed");
		verifyEquals(shoppingCartPageObject.getMessageAfterRemoveProuductFromCart(), "Your Shopping Cart is empty!");

		log.info("TC_03_Remove_From_Cart - Step 03 : Verify product " + nameProductBuy
				+ " is not display in shopping cart");
		verifyTrue(shoppingCartPageObject.isProductInCartNotDisplay(nameProductBuy));
	}

	@Test
	public void TC_04_Update_Shopping_Cart() {
		log.info("TC_04_Update_Shopping_Cart -Step 01: Click sub menu  Notebooks ");
		shoppingCartPageObject.openSubMenuByLabelName(driver, "Computers", "Notebooks");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);

		nameProductBuy = "Asus N551JK-XO076H Laptop";
		log.info("TC_04_Update_Shopping_Cart: Step 02: Click product: " + nameProductBuy);
		productDetailPageObject = productPageObject.clickToProductByName(driver, nameProductBuy);

		log.info("TC_04_Update_Shopping_Cart: Step 03: Click to Add to cart");
		productDetailPageObject.clickToButtonByLabelName(driver, "Add to cart");

		log.info("TC_04_Update_Shopping_Cart - Step 04:Verify product added into cart");
		verifyEquals(productDetailPageObject.getMessageSuccessfullAfterAddToCart(),
				"The product has been added to your shopping cart");

		log.info("TC_04_Update_Shopping_Cart - Step 05:Click Shopping cart in footer");
		productDetailPageObject.clickToFooterLinkByLabelName(driver, "Shopping cart");
		shoppingCartPageObject = PageGeneratorManager.getShoppingCartPageObject(driver);

		log.info("TC_04_Update_Shopping_Cart - Step 06:Verify product is displayed in shopping cart");
		verifyTrue(shoppingCartPageObject.isProductDisplayed(driver,"AS_551_LP", nameProductBuy, "$1,500.00", "$1,500.00"));

		log.info("TC_04_Update_Shopping_Cart - Step 07:Enter '" + editNumberNeedBuy + "' to textbox Qty. at product: "
				+ nameProductBuy);
		shoppingCartPageObject.enterToTextboxByAriaLabelThroughProductName(nameProductBuy, editNumberNeedBuy, "Qty.");

		log.info("TC_04_Update_Shopping_Cart- Step 08:Click button 'Update shopping cart' ");
		shoppingCartPageObject.clickToButtonByLabelName(driver, "Update shopping cart");

		//log.info("TC_04_Update_Shopping_Cart - Step 09:Click button 'Update shopping cart' ");
		//shoppingCartPageObject.clickToButtonByLabelName(driver, "Update shopping cart");

		log.info("TC_04_Update_Shopping_Cart - Step 10: verify quantity of product: " + nameProductBuy + " is "
				+ editNumberNeedBuy);
		verifyEquals(shoppingCartPageObject.getQuantityInShoppingCartByProductName(nameProductBuy), editNumberNeedBuy);

		log.info("TC_04_Update_Shopping_Cart - Step 11: verify total of product: " + nameProductBuy + " is $3,000.00");
		verifyEquals(shoppingCartPageObject.getTotalInShoppingCartByProductName(nameProductBuy), "$3,000.00");

	}

	@Test
	public void TC_05_Payment_By_Cheque() {
		log.info(
				"TC_05_Payment_By_Cheque - Step 01: Check to checkbox 'I agree with the terms of service and I adhere to them unconditionally (read)'");
		shoppingCartPageObject.clickCheckBoxAgreeWithTerms();

		log.info("TC_05_Payment_By_Cheque - Step 02: Click button 'Checkout'");
		shoppingCartPageObject.clickToButtonByID(driver, "checkout");
		checkoutPageObject = PageGeneratorManager.getCheckoutPageObject(driver);

		country = "United States";
		log.info("TC_05_Payment_By_Cheque - Step 03: Slect country in dropdown");
		checkoutPageObject.selectItemInDropDownByName(driver, "BillingNewAddress.CountryId", country);

		state = "Alabama";
		log.info("TC_05_Payment_By_Cheque - Step 04: Slect state in dropdown");
		checkoutPageObject.selectItemInDropDownByName(driver, "BillingNewAddress.StateProvinceId", state);

		city = "Washington";
		log.info("TC_05_Payment_By_Cheque - Step 05: Enter to City:  " + city);
		checkoutPageObject.enterToTextboxByID(driver, "BillingNewAddress_City", city);

		address_1 = "DC";
		log.info("TC_05_Payment_By_Cheque - Step 06: Enter to Address 1:  " + address_1);
		checkoutPageObject.enterToTextboxByID(driver, "BillingNewAddress_Address1", address_1);

		zipCode = data.getZipCode();
		log.info("TC_05_Payment_By_Cheque - Step 07: Enter to Zip/ postal code:  " + zipCode);
		checkoutPageObject.enterToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", zipCode);

		phoneNumber = data.getPhoneNumber();
		log.info("TC_05_Payment_By_Cheque - Step 08: Enter to phone number:  " + phoneNumber);
		checkoutPageObject.enterToTextboxByID(driver, "BillingNewAddress_PhoneNumber", phoneNumber);
		
		log.info("TC_05_Payment_By_Cheque - Step 09: Click button 'Continue' at section 'Billing address'");
		checkoutPageObject.clickButtonByLabelAtIDSection("billing-buttons-container","Continue");
	
		log.info("TC_05_Payment_By_Cheque - Step 10: verify shipping method is Ground ($0.00) is selected");
		verifyTrue(checkoutPageObject.isRadioButtonSelectedByLabelName(driver, "Ground ($0.00)"));	
		
		log.info("TC_05_Payment_By_Cheque - Step 11: Click button 'Continue' at section 'Shipping method'");
		checkoutPageObject.clickButtonByLabelAtIDSection("shipping-method-buttons-container","Continue");
			
		log.info("TC_05_Payment_By_Cheque - Step 12: verify payment method is Check / Money Order is selected");
		verifyTrue(checkoutPageObject.isRadioButtonSelectedByLabelName(driver, "Check / Money Order"));				
		
		log.info("TC_05_Payment_By_Cheque - Step 13: Click button 'Continue' at section 'Payment method'");
		checkoutPageObject.clickButtonByLabelAtIDSection("payment-method-buttons-container","Continue");
		
		log.info("TC_05_Payment_By_Cheque - Step 14: Click button 'Continue' at section 'Payment information'");
		checkoutPageObject.clickButtonByLabelAtIDSection("payment-info-buttons-container","Continue");
		
		fullName = firstName+ " "+ lastName;
		log.info("TC_05_Payment_By_Cheque - Step 15: verify information Billing Address");
		verifyTrue(checkoutPageObject.isInformationAtAddressDisplayed (driver, "billing-info",fullName, emailAddress, phoneNumber, address_1,city+","+ state+","+ zipCode, country,"Check / Money Order"));
		
		log.info("TC_05_Payment_By_Cheque - Step 16: verify information Shipping Address");
		verifyTrue(checkoutPageObject.isInformationAtAddressDisplayed (driver,"shipping-info",fullName, emailAddress, phoneNumber, address_1,city+","+ state+","+ zipCode, country,"Ground"));
		
		log.info("TC_05_Payment_By_Cheque - Step 17: verify product: "+ nameProductBuy+"is displayed");
		verifyTrue(checkoutPageObject.isProductDisplayed(driver,"AS_551_LP", nameProductBuy, "$1,500.00", "$3,000.00"));
				
		log.info("TC_05_Payment_By_Cheque - Step 18: verify information about Gift wrapping:No is display");
		verifyTrue(checkoutPageObject.isInformationAboutGiftWrappingNoDisplay(driver,"cart-options"));
		
		log.info("TC_05_Payment_By_Cheque - Step 19: verify information about sub total is:$3,000.00 " );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver,"Sub-Total:"),"$3,000.00");
		
		log.info("TC_05_Payment_By_Cheque - Step 20: verify information about shipping method: Ground is display " );
		verifyTrue(checkoutPageObject.isInformationAboutShippingMethodDisplay(driver,"(Ground)"));
		
		log.info("TC_05_Payment_By_Cheque - Step 21: verify information about shipping is:$0.00 " );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver,"Shipping:"),"$0.00");
		
		log.info("TC_05_Payment_By_Cheque - Step 22: verify information about tax is:$0.00 " );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver,"Tax:"),"$0.00");
		
		log.info("TC_05_Payment_By_Cheque - Step 23: verify information about total is:$3,000.00 " );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver,"Total:"),"$3,000.00");
		
		log.info("TC_05_Payment_By_Cheque - Step 24: verify information about You will earn :300 points" );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver, "You will earn:"),"300 points");
		
		log.info("TC_05_Payment_By_Cheque - Step 25: Click button 'Confirm' at section 'Confirm order'");
		checkoutPageObject.clickButtonByLabelAtIDSection("confirm-order-buttons-container","Confirm");
		
		log.info("TC_05_Payment_By_Cheque - Step 26: Verify successage message is display");
		verifyTrue(checkoutPageObject.isSuccessMessageAfterConfirmOrderDisplay());
		
		log.info("TC_05_Payment_By_Cheque - Step 27: Verify order number is display");
		orderNumber= checkoutPageObject.getOrderNumber();
		verifyTrue(orderNumber >0);
		
		log.info("TC_05_Payment_By_Cheque - Step 28: Click menu My Account");
		checkoutPageObject.clickToHeaderLinkByLabelName(driver,"My account");
		myAccountPageObject= PageGeneratorManager.getMyAccountPageObject(driver);
		
		log.info("TC_05_Payment_By_Cheque - Step 29: Click Order page");
		myAccountPageObject.clickToPageInLeftMenuByLabelName(driver, "Orders");
		
		log.info("TC_05_Payment_By_Cheque - Step 30: Verify order number is:" + orderNumber);
		verifyEquals(myAccountPageObject.getOrderNumberAtPageMyAccount(), orderNumber);
		
		log.info("TC_05_Payment_By_Cheque - Step 31: Click button Details to view order" );
		myAccountPageObject.clickButtonDetailAtOrderNumber(orderNumber);
				
		log.info("TC_05_Payment_By_Cheque - Step 32: Verify Order Date is display" );		
		dayOfWeek=String.valueOf(LocalDateTime.now().getDayOfWeek().toString().toLowerCase());
		month=String.valueOf(LocalDateTime.now().getMonth().toString().toLowerCase());
		dayOfMonth=String.valueOf(LocalDateTime.now().getDayOfMonth());
		if(dayOfMonth.length()==1) dayOfMonth="0"+dayOfMonth;
		year=String.valueOf(LocalDateTime.now().getYear());		
		verifyEquals(myAccountPageObject.getOrderDateDisplay(),"order date: "+dayOfWeek+", "+month+" "+dayOfMonth+", "+year);
	
		log.info("TC_05_Payment_By_Cheque - Step 33: Verify Order Status is Pending" );
		verifyEquals(myAccountPageObject.getOrderStatus(),"Order Status: Pending");
		
		log.info("TC_05_Payment_By_Cheque - Step 34: Verify Order Total is $3,000.00" );
		verifyEquals(myAccountPageObject.getOrderTotal(),"Order Total: $3,000.00");

		log.info("TC_05_Payment_By_Cheque - Step 35: verify information Billing Address");
		verifyTrue(myAccountPageObject.isInformationAtAddressDisplayed (driver, "billing-info",fullName, emailAddress, phoneNumber, address_1,city+","+ state+","+ zipCode, country,"Check / Money Order"));
		
		log.info("TC_05_Payment_By_Cheque - Step 36: verify information Shipping Address");
		verifyTrue(myAccountPageObject.isInformationAtAddressDisplayed (driver,"shipping-info",fullName, emailAddress, phoneNumber, address_1,city+","+ state+","+ zipCode, country,"Ground"));
		
		log.info("TC_05_Payment_By_Cheque - Step 37: verify product: "+ nameProductBuy+"is displayed");
		verifyTrue(myAccountPageObject.isProductDisplayedInMyOrder("AS_551_LP", nameProductBuy, "$1,500.00", "$3,000.00"));
				
		log.info("TC_05_Payment_By_Cheque - Step 38: verify information about Gift wrapping:No is display");
		verifyTrue(myAccountPageObject.isInformationAboutGiftWrappingNoDisplay(driver,"section options"));
		
		log.info("TC_05_Payment_By_Cheque - Step 39: verify information about sub total is:$3,000.00 " );
		verifyEquals(myAccountPageObject.getInformationAtBillByLabel(driver,"Sub-Total:"),"$3,000.00");
				
		log.info("TC_05_Payment_By_Cheque - Step 40: verify information about shipping is:$0.00 " );
		verifyEquals(myAccountPageObject.getInformationAtBillByLabel(driver,"Shipping:"),"$0.00");
		
		log.info("TC_05_Payment_By_Cheque - Step 41: verify information about tax is:$0.00 " );
		verifyEquals(myAccountPageObject.getInformationAtBillByLabel(driver,"Tax:"),"$0.00");
		
		log.info("TC_05_Payment_By_Cheque - Step 42: verify information about total is:$3,000.00 " );
		verifyEquals(myAccountPageObject.getInformationAtBillByLabel(driver,"Order Total:"),"$3,000.00");
				
		log.info("TC_05_Payment_By_Cheque - Step 43: verify information about payment status is: Pending " );
		verifyEquals(myAccountPageObject.getInformationPaymentSatus(),"Pending");
			
		log.info("TC_05_Payment_By_Cheque - Step 44: verify information about shipping status is: Not yet shipped  " );
		verifyEquals(myAccountPageObject.getInformationShippingSatus(),"Not yet shipped");
	}

	@Test
	public void TC_06_Payment_By_Visa() {
		
		log.info("TC_06_Payment_By_Visa -Step 01: Click sub menu  Notebooks ");
		myAccountPageObject.openSubMenuByLabelName(driver, "Computers", "Notebooks");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);
		
		nameProductBuy = "HP Envy 6-1180ca 15.6-Inch Sleekbook";
		log.info("TC_06_Payment_By_Visa: Step 02: Click product: " + nameProductBuy);
		productDetailPageObject = productPageObject.clickToProductByName(driver, nameProductBuy);

		log.info("TC_06_Payment_By_Visa: Step 03: Click to Add to cart");
		productDetailPageObject.clickToButtonByLabelName(driver, "Add to cart");

		log.info("TC_06_Payment_By_Visa - Step 04:Verify product added into cart");
		verifyEquals(productDetailPageObject.getMessageSuccessfullAfterAddToCart(),	"The product has been added to your shopping cart");

		log.info("TC_06_Payment_By_Visa - Step 05:Click Shopping cart in footer");
		productDetailPageObject.clickToFooterLinkByLabelName(driver, "Shopping cart");
		shoppingCartPageObject = PageGeneratorManager.getShoppingCartPageObject(driver);

		log.info("TC_06_Payment_By_Visa - Step 06:Verify product is displayed in shopping cart");
		verifyTrue(shoppingCartPageObject.isProductDisplayed(driver,"HP_ESB_15", nameProductBuy, "$1,460.00", "$1,460.00"));

		log.info("TC_06_Payment_By_Visa - Step 07: Check to checkbox 'I agree with the terms of service and I adhere to them unconditionally (read)'");
		shoppingCartPageObject.clickCheckBoxAgreeWithTerms();

		log.info("TC_06_Payment_By_Visa - Step 08: Click button 'Checkout'");
		shoppingCartPageObject.clickToButtonByID(driver, "checkout");
		checkoutPageObject = PageGeneratorManager.getCheckoutPageObject(driver);
		
		log.info("TC_06_Payment_By_Visa - Step 09: Click button 'Continue' at section 'Billing address'");
		checkoutPageObject.clickButtonByLabelAtIDSection("billing-buttons-container","Continue");
	
		log.info("TC_06_Payment_By_Visa - Step 10: verify shipping method is Ground ($0.00) is selected");
		verifyTrue(checkoutPageObject.isRadioButtonSelectedByLabelName(driver, "Ground ($0.00)"));	
		
		log.info("TC_06_Payment_By_Visa - Step 11: Click button 'Continue' at section 'Shipping method'");
		checkoutPageObject.clickButtonByLabelAtIDSection("shipping-method-buttons-container","Continue");
			
		log.info("TC_06_Payment_By_Visa - Step 12: Click raditon button 'Credit Card' in section 'payment method'");
		checkoutPageObject.clickToRadioButtonByLabelName(driver, "Credit Card");
		
		log.info("TC_06_Payment_By_Visa - Step 13: verify payment method is 'Credit Card' is selected");
		verifyTrue(checkoutPageObject.isRadioButtonSelectedByLabelName(driver, "Credit Card"));				
		
		log.info("TC_06_Payment_By_Visa - Step 14: Click button 'Continue' at section 'Payment method'");
		checkoutPageObject.clickButtonByLabelAtIDSection("payment-method-buttons-container","Continue");
		
		log.info("TC_06_Payment_By_Visa - Step 15: Select credit card is Master card");
		checkoutPageObject.selectItemInDropDownByName(driver,"CreditCardType", "Master card");
		
		log.info("TC_06_Payment_By_Visa - Step 16: verify Master card is selected");
		verifyEquals(checkoutPageObject.getSelectedValueInDropdrownByName(driver,"CreditCardType"), "Master card");
		
		log.info("TC_06_Payment_By_Visa - Step 17: Input to text box 'Cardholder name:'");
		checkoutPageObject.enterToTextboxByID(driver, "CardholderName","Joey Schaefer");
		
		log.info("TC_06_Payment_By_Visa - Step 18: Input to text box 'Cardholder number:'");
		checkoutPageObject.enterToTextboxByID(driver, "CardNumber","5543989781562829");
		
		log.info("TC_06_Payment_By_Visa - Step 19: Select expire month is 9");
		checkoutPageObject.selectItemInDropDownByName(driver,"ExpireMonth", "09");
		
		log.info("TC_06_Payment_By_Visa - Step 20: verify expire month  9 is selected");
		verifyEquals(checkoutPageObject.getSelectedValueInDropdrownByName(driver,"ExpireMonth"),"09");
		
		log.info("TC_06_Payment_By_Visa - Step 21: Select expire year is 2027");
		checkoutPageObject.selectItemInDropDownByName(driver,"ExpireYear", "2027");
		
		log.info("TC_06_Payment_By_Visa - Step 22:verify expire month 2027 is selected");
		verifyEquals(checkoutPageObject.getSelectedValueInDropdrownByName(driver,"ExpireYear"), "2027");
		
		log.info("TC_06_Payment_By_Visa - Step 23: Input to text box 'Card code:'");
		checkoutPageObject.enterToTextboxByID(driver, "CardCode","625");
		
		log.info("TC_06_Payment_By_Visa - Step 24: Click button 'Continue' at section 'Payment information'");
		checkoutPageObject.clickButtonByLabelAtIDSection("payment-info-buttons-container","Continue");
		
		log.info("TC_06_Payment_By_Visa - Step 25: verify information Billing Address");
		verifyTrue(checkoutPageObject.isInformationAtAddressDisplayed (driver, "billing-info",fullName, emailAddress, phoneNumber, address_1,city+","+ state+","+ zipCode, country,"Credit Card"));
		
		log.info("TC_06_Payment_By_Visa - Step 26: verify information Shipping Address");
		verifyTrue(checkoutPageObject.isInformationAtAddressDisplayed (driver,"shipping-info",fullName, emailAddress, phoneNumber, address_1,city+","+ state+","+ zipCode, country,"Ground"));
		
		log.info("TC_06_Payment_By_Visa - Step 27: verify product: "+ nameProductBuy+"is displayed");
		verifyTrue(checkoutPageObject.isProductDisplayed(driver,"HP_ESB_15", nameProductBuy, "$1,460.00", "$1,460.00"));
				
		log.info("TC_06_Payment_By_Visa - Step 28: verify information about Gift wrapping:No is display");
		verifyTrue(checkoutPageObject.isInformationAboutGiftWrappingNoDisplay(driver,"cart-options"));
		
		log.info("TC_06_Payment_By_Visa - Step 29: verify information about sub total is:$1,460.00 " );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver,"Sub-Total:"),"$1,460.00");
		
		log.info("TC_06_Payment_By_Visa - Step 30: verify information about shipping method: Ground is display " );
		verifyTrue(checkoutPageObject.isInformationAboutShippingMethodDisplay(driver,"(Ground)"));
		
		log.info("TC_06_Payment_By_Visa - Step 31: verify information about shipping is:$0.00 " );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver,"Shipping:"),"$0.00");
		
		log.info("TC_06_Payment_By_Visa - Step 32: verify information about tax is:$0.00 " );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver,"Tax:"),"$0.00");
		
		log.info("TC_06_Payment_By_Visa - Step 33: verify information about total is:$1,460.00 " );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver,"Total:"),"$1,460.00");
		
		log.info("TC_06_Payment_By_Visa - Step 34: verify information about You will earn :146 points" );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver, "You will earn:"),"146 points");
		
		log.info("TC_06_Payment_By_Visa - Step 35: Click button 'Confirm' at section 'Confirm order'");
		checkoutPageObject.clickButtonByLabelAtIDSection("confirm-order-buttons-container","Confirm");
		
		log.info("TC_06_Payment_By_Visa - Step 36:Accept Alert");
		checkoutPageObject.acceptAlert(driver);
		
		log.info("TC_06_Payment_By_Visa - Step 37:Sleep 60s");
		checkoutPageObject.sleepInSecond(60);
		
		
		log.info("TC_06_Payment_By_Visa - Step 38: Click button 'Confirm' at section 'Confirm order'");
		checkoutPageObject.clickButtonByLabelAtIDSection("confirm-order-buttons-container","Confirm");
		
		log.info("TC_06_Payment_By_Visa - Step 39: Verify successage message is display");
		verifyTrue(checkoutPageObject.isSuccessMessageAfterConfirmOrderDisplay());
		
		log.info("TC_06_Payment_By_Visa - Step 40: Verify order number is display");
		orderNumber= checkoutPageObject.getOrderNumber();
		verifyTrue(orderNumber >0);
		
		log.info("TC_06_Payment_By_Visa - Step 41: Click menu My Account");
		checkoutPageObject.clickToHeaderLinkByLabelName(driver,"My account");
		myAccountPageObject= PageGeneratorManager.getMyAccountPageObject(driver);
		
		log.info("TC_06_Payment_By_Visa - Step 42: Click Order page");
		myAccountPageObject.clickToPageInLeftMenuByLabelName(driver, "Orders");
		
		log.info("TC_06_Payment_By_Visa - Step 43: Verify order number is:" + orderNumber);
		verifyEquals(myAccountPageObject.getOrderNumberAtPageMyAccount(), orderNumber);
		
		log.info("TC_06_Payment_By_Visa - Step 44: Click button Details to view order" );
		myAccountPageObject.clickButtonDetailAtOrderNumber(orderNumber);
				
		log.info("TC_06_Payment_By_Visa - Step 45: Verify Order Date is display" );		
		dayOfWeek=String.valueOf(LocalDateTime.now().getDayOfWeek().toString().toLowerCase());
		month=String.valueOf(LocalDateTime.now().getMonth().toString().toLowerCase());
		dayOfMonth=String.valueOf(LocalDateTime.now().getDayOfMonth());
		if(dayOfMonth.length()==1) dayOfMonth="0"+dayOfMonth;
		year=String.valueOf(LocalDateTime.now().getYear());		
		verifyEquals(myAccountPageObject.getOrderDateDisplay(),"order date: "+dayOfWeek+", "+month+" "+dayOfMonth+", "+year);
	
		log.info("TC_06_Payment_By_Visa - Step 46: Verify Order Status is Pending" );
		verifyEquals(myAccountPageObject.getOrderStatus(),"Order Status: Pending");
		
		log.info("TC_06_Payment_By_Visa - Step 47: Verify Order Total is $1,460.00" );
		verifyEquals(myAccountPageObject.getOrderTotal(),"Order Total: $1,460.00");

		log.info("TC_06_Payment_By_Visa - Step 48: verify information Billing Address");
		verifyTrue(myAccountPageObject.isInformationAtAddressDisplayed (driver, "billing-info",fullName, emailAddress, phoneNumber, address_1,city+","+ state+","+ zipCode, country,"Credit Card"));
		
		log.info("TC_06_Payment_By_Visa - Step 49: verify information Shipping Address");
		verifyTrue(myAccountPageObject.isInformationAtAddressDisplayed (driver,"shipping-info",fullName, emailAddress, phoneNumber, address_1,city+","+ state+","+ zipCode, country,"Ground"));
		
		log.info("TC_06_Payment_By_Visa - Step 50: verify product: "+ nameProductBuy+"is displayed");
		verifyTrue(myAccountPageObject.isProductDisplayedInMyOrder("HP_ESB_15", nameProductBuy, "$1,460.00", "$1,460.00"));
				
		log.info("TC_06_Payment_By_Visa - Step 51: verify information about Gift wrapping:No is display");
		verifyTrue(myAccountPageObject.isInformationAboutGiftWrappingNoDisplay(driver,"section options"));
		
		log.info("TC_06_Payment_By_Visa - Step 52: verify information about sub total is:$1,460.00 " );
		verifyEquals(myAccountPageObject.getInformationAtBillByLabel(driver,"Sub-Total:"),"$1,460.00");
				
		log.info("TC_06_Payment_By_Visa - Step 53: verify information about shipping is:$0.00 " );
		verifyEquals(myAccountPageObject.getInformationAtBillByLabel(driver,"Shipping:"),"$0.00");
		
		log.info("TC_06_Payment_By_Visa - Step 54: verify information about tax is:$0.00 " );
		verifyEquals(myAccountPageObject.getInformationAtBillByLabel(driver,"Tax:"),"$0.00");
		
		log.info("TC_06_Payment_By_Visa - Step 55: verify information about total is:$1,460.00 " );
		verifyEquals(myAccountPageObject.getInformationAtBillByLabel(driver,"Order Total:"),"$1,460.00");
			
		log.info("TC_06_Payment_By_Visa - Step 56: verify information about payment status is: Pending " );
		verifyEquals(myAccountPageObject.getInformationPaymentSatus(),"Pending");
			
		log.info("TC_06_Payment_By_Visa - Step 57: verify information about shipping status is: Not yet shipped  " );
		verifyEquals(myAccountPageObject.getInformationShippingSatus(),"Not yet shipped");
	}
	
	@Test
	public void TC_07_Re_Order() {
		log.info("TC_07_Re_Order - Step 01: Click button Re Order " );
		myAccountPageObject.clickToButtonByLabelName(driver, "Re-order");
		shoppingCartPageObject = PageGeneratorManager.getShoppingCartPageObject(driver);
		
		editNumberNeedBuy = "10";
		log.info("TC_07_Re_Order - Step 02:Enter '" + editNumberNeedBuy + "' to textbox Qty. at product: "
				+ nameProductBuy);
		shoppingCartPageObject.enterToTextboxByAriaLabelThroughProductName(nameProductBuy, editNumberNeedBuy, "Qty.");

		log.info("TC_07_Re_Order - Step 03:Click button 'Update shopping cart' ");
		shoppingCartPageObject.clickToButtonByLabelName(driver, "Update shopping cart");

		
		log.info("TC_07_Re_Order - Step 04: verify quantity of product: " + nameProductBuy + " is "
				+ editNumberNeedBuy);
		verifyEquals(shoppingCartPageObject.getQuantityInShoppingCartByProductName(nameProductBuy), editNumberNeedBuy);

		log.info("TC_07_Re_Order - Step 05:Verify product is displayed in shopping cart");
		verifyTrue(shoppingCartPageObject.isProductDisplayed(driver,"HP_ESB_15", nameProductBuy, "$1,460.00", "$14,600.00"));

		log.info("TC_07_Re_Order - Step 06: Check to checkbox 'I agree with the terms of service and I adhere to them unconditionally (read)'");
		shoppingCartPageObject.clickCheckBoxAgreeWithTerms();

		log.info("TC_07_Re_Order - Step 07: Click button 'Checkout'");
		shoppingCartPageObject.clickToButtonByID(driver, "checkout");
		checkoutPageObject = PageGeneratorManager.getCheckoutPageObject(driver);
		
		log.info("TC_07_Re_Order - Step 08: Click button 'Continue' at section 'Billing address'");
		checkoutPageObject.clickButtonByLabelAtIDSection("billing-buttons-container","Continue");
	
		log.info("TC_07_Re_Order - Step 09: select shipping method is Next Day Air ($0.00)");
		checkoutPageObject.clickToRadioButtonByLabelName(driver, "Next Day Air ($0.00)");
		
		log.info("TC_07_Re_Order - Step 10: verify shipping method is Next Day Air ($0.00) is selected");
		verifyTrue(checkoutPageObject.isRadioButtonSelectedByLabelName(driver, "Next Day Air ($0.00)"));	
		
		log.info("TC_07_Re_Order - Step 11: Click button 'Continue' at section 'Shipping method'");
		checkoutPageObject.clickButtonByLabelAtIDSection("shipping-method-buttons-container","Continue");
	
		log.info("TC_07_Re_Order- Step 12: Click raditon button 'Credit Card' in section 'payment method'");
		checkoutPageObject.clickToRadioButtonByLabelName(driver, "Credit Card");
		
		log.info("TC_07_Re_Order - Step 13: verify payment method is 'Credit Card' is selected");
		verifyTrue(checkoutPageObject.isRadioButtonSelectedByLabelName(driver, "Credit Card"));				
		
		log.info("TC_07_Re_Order - Step 14: Click button 'Continue' at section 'Payment method'");
		checkoutPageObject.clickButtonByLabelAtIDSection("payment-method-buttons-container","Continue");
		
		log.info("TC_07_Re_Order - Step 15: Select credit card is Master card");
		checkoutPageObject.selectItemInDropDownByName(driver,"CreditCardType", "Master card");
		
		log.info("TC_07_Re_Order - Step 16: verify Master card is selected");
		verifyEquals(checkoutPageObject.getSelectedValueInDropdrownByName(driver,"CreditCardType"), "Master card");
		
		log.info("TC_07_Re_Order - Step 17: Input to text box 'Cardholder name:'");
		checkoutPageObject.enterToTextboxByID(driver, "CardholderName","Joey Schaefer");
		
		log.info("TC_07_Re_Order - Step 18: Input to text box 'Cardholder number:'");
		checkoutPageObject.enterToTextboxByID(driver, "CardNumber","5543989781562829");
		
		log.info("TC_07_Re_Order - Step 19: Select expire month is 9");
		checkoutPageObject.selectItemInDropDownByName(driver,"ExpireMonth", "09");
		
		log.info("TC_07_Re_Order - Step 20: verify expire month  9 is selected");
		verifyEquals(checkoutPageObject.getSelectedValueInDropdrownByName(driver,"ExpireMonth"),"09");
		
		log.info("TC_07_Re_Order - Step 21: Select expire year is 2027");
		checkoutPageObject.selectItemInDropDownByName(driver,"ExpireYear", "2027");
		
		log.info("TC_07_Re_Order - Step 22:verify expire month 2027 is selected");
		verifyEquals(checkoutPageObject.getSelectedValueInDropdrownByName(driver,"ExpireYear"), "2027");
		
		log.info("TC_07_Re_Order - Step 23: Input to text box 'Card code:'");
		checkoutPageObject.enterToTextboxByID(driver, "CardCode","625");
		
		log.info("TC_07_Re_Order - Step 24: Click button 'Continue' at section 'Payment information'");
		checkoutPageObject.clickButtonByLabelAtIDSection("payment-info-buttons-container","Continue");
		
		log.info("TC_07_Re_Order - Step 25: verify information Billing Address");
		verifyTrue(checkoutPageObject.isInformationAtAddressDisplayed (driver, "billing-info",fullName, emailAddress, phoneNumber, address_1,city+","+ state+","+ zipCode, country,"Credit Card"));
		
		log.info("TC_07_Re_Order - Step 26: verify information Shipping Address");
		verifyTrue(checkoutPageObject.isInformationAtAddressDisplayed (driver,"shipping-info",fullName, emailAddress, phoneNumber, address_1,city+","+ state+","+ zipCode, country,"Next Day Air"));
		
		log.info("TC_07_Re_Order - Step 27: verify product: "+ nameProductBuy+"is displayed");
		verifyTrue(checkoutPageObject.isProductDisplayed(driver,"HP_ESB_15", nameProductBuy, "$1,460.00", "$14,600.00"));
				
		log.info("TC_07_Re_Order - Step 28: verify information about Gift wrapping:No is display");
		verifyTrue(checkoutPageObject.isInformationAboutGiftWrappingNoDisplay(driver,"cart-options"));
		
		log.info("TC_07_Re_Order - Step 29: verify information about sub total is:$14,600.00 " );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver,"Sub-Total:"),"$14,600.00");
		
		log.info("TC_07_Re_Order - Step 30: verify information about shipping method: Next Day Air is display " );
		verifyTrue(checkoutPageObject.isInformationAboutShippingMethodDisplay(driver,"(Next Day Air)"));
		
		log.info("TC_07_Re_Order - Step 31: verify information about shipping is:$0.00 " );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver,"Shipping:"),"$0.00");
		
		log.info("TC_07_Re_Order - Step 32: verify information about tax is:$0.00 " );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver,"Tax:"),"$0.00");
		
		log.info("TC_07_Re_Order - Step 33: verify information about total is:$14,600.00 " );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver,"Total:"),"$14,600.00");
		
		log.info("TC_07_Re_Order - Step 34: verify information about You will earn :1460 points" );
		verifyEquals(checkoutPageObject.getInformationAtBillByLabel(driver, "You will earn:"),"1460 points");
		
		log.info("TC_07_Re_Order - Step 35: Click button 'Confirm' at section 'Confirm order'");
		checkoutPageObject.clickButtonByLabelAtIDSection("confirm-order-buttons-container","Confirm");
		
		log.info("TC_07_Re_Order - Step 36:Accept Alert");
		checkoutPageObject.acceptAlert(driver);
		
		log.info("TC_07_Re_Order - Step 37:Sleep 60s");
		checkoutPageObject.sleepInSecond(60);		
		
		log.info("TC_07_Re_Order - Step 38: Click button 'Confirm' at section 'Confirm order'");
		checkoutPageObject.clickButtonByLabelAtIDSection("confirm-order-buttons-container","Confirm");
		
		log.info("TC_07_Re_Order - Step 39: Verify successage message is display");
		verifyTrue(checkoutPageObject.isSuccessMessageAfterConfirmOrderDisplay());
		
		log.info("TC_07_Re_Order - Step 40: Verify order number is display");
		orderNumber= checkoutPageObject.getOrderNumber();
		verifyTrue(orderNumber >0);
		
		log.info("TC_07_Re_Order - Step 41: Click menu My Account");
		checkoutPageObject.clickToHeaderLinkByLabelName(driver,"My account");
		myAccountPageObject= PageGeneratorManager.getMyAccountPageObject(driver);
		
		log.info("TC_07_Re_Order - Step 42: Click Order page");
		myAccountPageObject.clickToPageInLeftMenuByLabelName(driver, "Orders");
		
		log.info("TC_07_Re_Order - Step 43: Verify order number is:" + orderNumber);
		verifyEquals(myAccountPageObject.getOrderNumberAtPageMyAccount(), orderNumber);
		
		log.info("TC_07_Re_Order - Step 44: Click button Details to view order" );
		myAccountPageObject.clickButtonDetailAtOrderNumber(orderNumber);
				
		log.info("TC_07_Re_Order - Step 45: Verify Order Date is display" );		
		dayOfWeek=String.valueOf(LocalDateTime.now().getDayOfWeek().toString().toLowerCase());
		month=String.valueOf(LocalDateTime.now().getMonth().toString().toLowerCase());
		dayOfMonth=String.valueOf(LocalDateTime.now().getDayOfMonth());
		if(dayOfMonth.length()==1) dayOfMonth="0"+dayOfMonth;
		year=String.valueOf(LocalDateTime.now().getYear());		
		verifyEquals(myAccountPageObject.getOrderDateDisplay(),"order date: "+dayOfWeek+", "+month+" "+dayOfMonth+", "+year);
	
		log.info("TC_07_Re_Order - Step 46: Verify Order Status is Pending" );
		verifyEquals(myAccountPageObject.getOrderStatus(),"Order Status: Pending");
		
		log.info("TC_07_Re_Order - Step 47: Verify Order Total is $14,600.00" );
		verifyEquals(myAccountPageObject.getOrderTotal(),"Order Total: $14,600.00");

		log.info("TC_07_Re_Order - Step 48: verify information Billing Address");
		verifyTrue(myAccountPageObject.isInformationAtAddressDisplayed (driver, "billing-info",fullName, emailAddress, phoneNumber, address_1,city+","+ state+","+ zipCode, country,"Credit Card"));
		
		log.info("TC_07_Re_Order - Step 49: verify information Shipping Address");
		verifyTrue(myAccountPageObject.isInformationAtAddressDisplayed (driver,"shipping-info",fullName, emailAddress, phoneNumber, address_1,city+","+ state+","+ zipCode, country,"Next Day Air"));
		
		log.info("TC_07_Re_Order - Step 50: verify product: "+ nameProductBuy+"is displayed");
		verifyTrue(myAccountPageObject.isProductDisplayedInMyOrder("HP_ESB_15", nameProductBuy, "$1,460.00", "$14,600.00"));
				
		log.info("TC_07_Re_Order - Step 51: verify information about Gift wrapping:No is display");
		verifyTrue(myAccountPageObject.isInformationAboutGiftWrappingNoDisplay(driver,"section options"));
		
		log.info("TC_07_Re_Order - Step 52: verify information about sub total is:$14,600.00 " );
		verifyEquals(myAccountPageObject.getInformationAtBillByLabel(driver,"Sub-Total:"),"$14,600.00");
				
		log.info("TC_07_Re_Order - Step 53: verify information about shipping is:$0.00 " );
		verifyEquals(myAccountPageObject.getInformationAtBillByLabel(driver,"Shipping:"),"$0.00");
		
		log.info("TC_07_Re_Order - Step 54: verify information about tax is:$0.00 " );
		verifyEquals(myAccountPageObject.getInformationAtBillByLabel(driver,"Tax:"),"$0.00");
		
		log.info("TC_07_Re_Order - Step 55: verify information about total is:$14,600.00 " );
		verifyEquals(myAccountPageObject.getInformationAtBillByLabel(driver,"Order Total:"),"$14,600.00");
		
		log.info("TC_07_Re_Order - Step 56: verify information about payment status is: Pending " );
		verifyEquals(myAccountPageObject.getInformationPaymentSatus(),"Pending");
			
		log.info("TC_07_Re_Order - Step 57: verify information about shipping status is: Not yet shipped  " );
		verifyEquals(myAccountPageObject.getInformationShippingSatus(),"Not yet shipped");
		
	}
	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void afterClass(@Optional("chrome") String browserName) {
		log.info("Postcondition: Close the browser:" + browserName);
		 cleanBrowserAndDriver();
	}

}
