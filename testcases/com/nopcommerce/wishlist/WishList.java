package com.nopcommerce.wishlist;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import payObjects.CompareProductPageObject;
import payObjects.HomePageObject;
import payObjects.LoginPageObject;
import payObjects.MyAccountPageObject;
import payObjects.PageGeneratorManager;
import payObjects.ProductDetailPageObject;
import payObjects.ProductPageObject;
import payObjects.ProductReviewPageObject;
import payObjects.RecentlyViewedProductPageObject;
import payObjects.RegisterPageObject;
import payObjects.ShoppingCartPageObject;
import payObjects.WishListPageObject;
import serverConfig.IServer;
import utilities.DataUtil;

public class WishList extends BaseTest {

	WebDriver driver;
	IServer server;
	HomePageObject homePageObject;
	RegisterPageObject registerPageObject;
	ProductPageObject productPageObject;
	ProductDetailPageObject productDetailPageObject;
	WishListPageObject wishlistPageObject;
	ShoppingCartPageObject shoppingCartPageObject;
	CompareProductPageObject compareProductPageObject;
	RecentlyViewedProductPageObject recentlyViewProductPageObject;
	DataUtil data;
	
	String firstName, lastName, fullName, emailAddress, password, confirmPassword, newPassword, confirmNewPassword;
	String productName;
	String productNameCompare_1, priceProductCompare_1;
	String productNameCompare_2, priceProductCompare_2;
	String nameProductRecentlyViewed_1, nameProductRecentlyViewed_2,
	nameProductRecentlyViewed_3,nameProductRecentlyViewed_4,nameProductRecentlyViewed_5;
	
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
		
		log.info("Precondition 10: Verfify link My account display");
		verifyTrue(registerPageObject.isHeaderLinkDisplayByLabelNameDisplay(driver,"My account"));
		
		log.info("Precondition 11: Verfify link Log out display");
		verifyTrue(registerPageObject.isHeaderLinkDisplayByLabelNameDisplay(driver,"Log out"));
	}

	@Test
	public void TC_01_Add_WishList() {
		
		log.info("TC_01_Add_WishList -Step 01: Click sub menu Cell phones");
		registerPageObject.openSubMenuByLabelName(driver, "Electronics", "Cell phones");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);
		
		productName="HTC One Mini Blue";
		log.info("TC_01_Add_WishList - Step 02: Click item : "+ productName);
		productDetailPageObject=productPageObject.clickToProductByName(driver,productName);
		
		log.info("TC_01_Add_WishList - Step 03: Click button Add to Wishlist");
		productDetailPageObject.clickButtonByLabelNameThroughProductName("Add to wishlist",productName);
		
		log.info("TC_01_Add_WishList - Step 04:Verify product added into wishlist");
		verifyEquals(productDetailPageObject.getMessageSuccessfullAfterAddWishList(),"The product has been added to your wishlist");

		log.info("TC_01_Add_WishList - Step 05: Click close message success");
		productDetailPageObject.clickButtonCloseMessageSuccess();
		
		log.info("TC_01_Add_WishList - Step 06: Click Wishlist in footer");
		productDetailPageObject.clickToFooterLinkByLabelName(driver,"Wishlist");
		wishlistPageObject=PageGeneratorManager.getWishListPageObject(driver);
		
		log.info("TC_01_Add_WishList - Step 07:verify title of wishlist before sharing");
		verifyEquals(wishlistPageObject.getTitleOfWishList(),"Wishlist");
		
		log.info("TC_01_Add_WishList - Step 08:Verify product is displayed in wishlist");
		verifyTrue(wishlistPageObject.isRowValueDisplayed("OM_HTC_BL", productName,"$100.00","$100.00"));
	
		log.info("TC_01_Add_WishList - Step 09:Click link Your wishlist URL for sharing");
		wishlistPageObject.clickLinkYourWishlistURLForSharing();
		
		log.info("TC_01_Add_WishList - Step 10:verify title of wishlist after sharing");
		verifyEquals(wishlistPageObject.getTitleOfWishList(),"Wishlist of "+firstName+" "+lastName);
		
		log.info("TC_01_Add_WishList - Step 11:Verify product is displayed in wishlist");
		verifyTrue(wishlistPageObject.isRowValueDisplayed("OM_HTC_BL",productName,"$100.00","$100.00"));
	
	}

	@Test
	public void TC_02_Add_Product_To_Cart_From_WishList() {
		log.info("TC_02_Add_Product_To_Cart_From_WishList -Step 01: Click sub menu Software");
		wishlistPageObject.openSubMenuByLabelName(driver, "Computers", "Software");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);
		
		productName="Windows 8 Pro";
		log.info("TC_02_Add_Product_To_Cart_From_WishList - Step 02: Click item : "+ productName);
		productDetailPageObject=productPageObject.clickToProductByName(driver,productName);
		
		log.info("TC_02_Add_Product_To_Cart_From_WishList - Step 03: Click button Add to Wishlist");
		productDetailPageObject.clickButtonByLabelNameThroughProductName("Add to wishlist",productName);
		
		log.info("TC_02_Add_Product_To_Cart_From_WishList - Step 04:Verify product added into wishlist");
		verifyEquals(productDetailPageObject.getMessageSuccessfullAfterAddWishList(),"The product has been added to your wishlist");

		log.info("TC_02_Add_Product_To_Cart_From_WishList - Step 05: Click close message success");
		productDetailPageObject.clickButtonCloseMessageSuccess();
		
		log.info("TC_02_Add_Product_To_Cart_From_WishList - Step 06: Click Wishlist in footer");
		productDetailPageObject.clickToFooterLinkByLabelName(driver,"Wishlist");
		wishlistPageObject=PageGeneratorManager.getWishListPageObject(driver);
		
		log.info("TC_02_Add_Product_To_Cart_From_WishList - Step 07:verify title of wishlist sharing");
		verifyEquals(wishlistPageObject.getTitleOfWishList(),"Wishlist");
		
		log.info("TC_02_Add_Product_To_Cart_From_WishList - Step 08:Verify product is displayed in wishlist");
		verifyTrue(wishlistPageObject.isRowValueDisplayed("MS_WIN_8P", productName,"$65.00","$65.00"));	
		
		log.info("TC_02_Add_Product_To_Cart_From_WishList - Step 09:Click to checkbox at row contain product:"+productName );
		wishlistPageObject.checkToCheckBoxAtRowContainProduct(productName);		
		
		log.info("TC_02_Add_Product_To_Cart_From_WishList - Step 10:Click button 'ADD TO CART'");
		wishlistPageObject.clickToButtonByLabelName(driver, "Add to cart");
		shoppingCartPageObject = PageGeneratorManager.getShoppingCartPageObject(driver);
		
		log.info("TC_02_Add_Product_To_Cart_From_WishList - Step 11:Verify product is displayed in shopping cart");
		verifyTrue(shoppingCartPageObject.isProductDisplayed(driver,"MS_WIN_8P", productName,"$65.00","$65.00"));	
		
		log.info("TC_02_Add_Product_To_Cart_From_WishList - Step 12:Click Wishlist in footer");
		shoppingCartPageObject.clickToFooterLinkByLabelName(driver, "Wishlist");
		wishlistPageObject= PageGeneratorManager.getWishListPageObject(driver);
		
		log.info("TC_02_Add_Product_To_Cart_From_WishList - Step 13:Verify product is not displayed in wishlist");
		verifyTrue(wishlistPageObject.isRowValueNotDisplayed("MS_WIN_8P", productName,"$65.00","$65.00"));
	
}
	@Test()
	public void TC_03_Remove_Product_In_WishList() {
		productName="HTC One Mini Blue";
		log.info("TC_03_Remove_Product_In_WishList -Step 01 :Click icon Delete at row contain product: "+productName);
		wishlistPageObject.clickIconDeleteAtRowContainProduct(productName);	
		
		log.info("TC_03_Remove_Product_In_WishList -Sep 02 :Verify wishlist is empty ");
		verifyTrue(wishlistPageObject.isWishListEmpty());
		
		log.info("TC_03_Remove_Product_In_WishList - Step 03:Verify product is not displayed in wishlist");
		verifyTrue(wishlistPageObject.isRowValueNotDisplayed("OM_HTC_BL",productName,"$100.00","$100.00"));
	
	}
	@Test
	public void TC_04_Add_Product_To_Compare() {

		log.info("TC_04_Add_Product_To_Compare -Step 01: Click sub menu Desktops");
		wishlistPageObject.openSubMenuByLabelName(driver, "Computers", "Desktops");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);
		
		productNameCompare_1="Build your own computer";
		priceProductCompare_1="$1,200.00";
		log.info("TC_04_Add_Product_To_Compare -Step 02: Click icon Add to compare list");
		productPageObject.clickButtonByTitleThroughProductName( productNameCompare_1,"Add to compare list");
				
		log.info("TC_04_Add_Product_To_Compare - Step 03:Verify product added into wishlist");
		verifyEquals(productPageObject.getMessageSuccessfullAfterAddToComapre(),"The product has been added to your product comparison");

		log.info("TC_04_Add_Product_To_Compare - Step 04:Click Compare products list in footer");
		productPageObject.clickToFooterLinkByLabelName(driver, "Compare products list");
		compareProductPageObject=PageGeneratorManager.getCompareProductPageObject(driver);
		
		log.info("TC_04_Add_Product_To_Compare -Step 05: Click sub menu Desktops");;
		compareProductPageObject.openSubMenuByLabelName(driver, "Computers", "Desktops");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);
		
		productNameCompare_2="Digital Storm VANQUISH 3 Custom Performance PC";
		priceProductCompare_2="$1,259.00";
		log.info("TC_04_Add_Product_To_Compare -Step 06: Click icon Add to compare list");
		productPageObject.clickButtonByTitleThroughProductName( productNameCompare_2,"Add to compare list");
		
		log.info("TC_04_Add_Product_To_Compare - Step 07:Verify product added into wishlist");
		verifyEquals(productPageObject.getMessageSuccessfullAfterAddToComapre(),"The product has been added to your product comparison");

		log.info("TC_04_Add_Product_To_Compare - Step 08:Click Compare products list in footer");
		productPageObject.clickToFooterLinkByLabelName(driver, "Compare products list");
		compareProductPageObject=PageGeneratorManager.getCompareProductPageObject(driver);
		
		log.info("TC_04_Add_Product_To_Compare - Step 09: Verify product "+priceProductCompare_1+" is display");
		verifyTrue(compareProductPageObject.isCompareProductDisplay(productNameCompare_1,priceProductCompare_1));
		
		log.info("TC_04_Add_Product_To_Compare - Step 10: Verify product "+priceProductCompare_2+" is display");
		verifyTrue(compareProductPageObject.isCompareProductDisplay(productNameCompare_2,priceProductCompare_2));
		
		log.info("TC_04_Add_Product_To_Compare - Step 11: Click button 'Clear List'");
		compareProductPageObject.clickButtonClearList();
		
		log.info("TC_04_Add_Product_To_Compare - Step 12: Verify message 'You have no items to compare.' is displayed");	
		verifyEquals(compareProductPageObject.getMessageAfterClearListProductCompare(),"You have no items to compare.");
		
		log.info("TC_04_Add_Product_To_Compare - Step 13: Verify product "+priceProductCompare_1+" is not display");
		verifyTrue(compareProductPageObject.isCompareProductNotDisplay(productNameCompare_1,priceProductCompare_1));
		
		log.info("TC_04_Add_Product_To_Compare - Step 14: Verify product "+priceProductCompare_2+" is not display");
		verifyTrue(compareProductPageObject.isCompareProductNotDisplay(productNameCompare_2,priceProductCompare_2));
		
		
	}
	
	@Test
	public void TC_05_Recently_Viewed_Product() {
		
		log.info("TC_05_Recently_Viewed_Product -Step 01: Click menu Books");
		compareProductPageObject.clickToMenuPageByLabelName(driver,"Books");
		//registerPageObject.clickToMenuPageByLabelName(driver,"Books");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);
		
		nameProductRecentlyViewed_1="Fahrenheit 451 by Ray Bradbury";
		log.info("TC_05_Recently_Viewed_Product: Step 02: Click item : "+ nameProductRecentlyViewed_1);
		productDetailPageObject=productPageObject.clickToProductByName(driver,nameProductRecentlyViewed_1);
		
		log.info("TC_05_Recently_Viewed_Product - Step 03:Click 'Recently viewed products' in footer");
		productDetailPageObject.clickToFooterLinkByLabelName(driver, "Recently viewed products");
		recentlyViewProductPageObject=PageGeneratorManager.getRecentlyViewedProductPageObject(driver);
		
		log.info("TC_05_Recently_Viewed_Product - Step 04:Verify product: "+ nameProductRecentlyViewed_1+" is displayed");
		verifyTrue(recentlyViewProductPageObject.isProductDisplayByName(nameProductRecentlyViewed_1));
		
		log.info("TC_05_Recently_Viewed_Product -Step 05: Click menu Books");
		recentlyViewProductPageObject.clickToMenuPageByLabelName(driver,"Books");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);
		
		nameProductRecentlyViewed_2="First Prize Pies";
		log.info("TC_05_Recently_Viewed_Product: Step 06: Click item : "+ nameProductRecentlyViewed_2);
		productDetailPageObject=productPageObject.clickToProductByName(driver,nameProductRecentlyViewed_2);
		
		log.info("TC_05_Recently_Viewed_Product - Step 07:Click 'Recently viewed products' in footer");
		productDetailPageObject.clickToFooterLinkByLabelName(driver, "Recently viewed products");
		recentlyViewProductPageObject=PageGeneratorManager.getRecentlyViewedProductPageObject(driver);
		
		log.info("TC_05_Recently_Viewed_Product - Step 08:Verify product: "+ nameProductRecentlyViewed_2+" is displayed");
		verifyTrue(recentlyViewProductPageObject.isProductDisplayByName(nameProductRecentlyViewed_2));
		
		log.info("TC_05_Recently_Viewed_Product -Step 09: Click menu Jewelry");
		recentlyViewProductPageObject.clickToMenuPageByLabelName(driver,"Jewelry");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);
		
		nameProductRecentlyViewed_3="Vintage Style Engagement Ring";
		log.info("TC_05_Recently_Viewed_Product: Step 10: Click item : "+ nameProductRecentlyViewed_3);
		productDetailPageObject=productPageObject.clickToProductByName(driver,nameProductRecentlyViewed_3);
		
		log.info("TC_05_Recently_Viewed_Product - Step 11:Click 'Recently viewed products' in footer");
		productDetailPageObject.clickToFooterLinkByLabelName(driver, "Recently viewed products");
		recentlyViewProductPageObject=PageGeneratorManager.getRecentlyViewedProductPageObject(driver);
		
		log.info("TC_05_Recently_Viewed_Product - Step 12:Verify product: "+ nameProductRecentlyViewed_3+" is displayed");
		verifyTrue(recentlyViewProductPageObject.isProductDisplayByName(nameProductRecentlyViewed_3));
		
		log.info("TC_05_Recently_Viewed_Product -Step 13: Click menu Gift Cards ");
		recentlyViewProductPageObject.clickToMenuPageByLabelName(driver,"Gift Cards");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);
		
		nameProductRecentlyViewed_4="$25 Virtual Gift Card";
		log.info("TC_05_Recently_Viewed_Product: Step 14: Click item : "+ nameProductRecentlyViewed_4);
		productDetailPageObject=productPageObject.clickToProductByName(driver,nameProductRecentlyViewed_4);
		
		log.info("TC_05_Recently_Viewed_Product - Step 15:Click 'Recently viewed products' in footer");
		productDetailPageObject.clickToFooterLinkByLabelName(driver, "Recently viewed products");
		recentlyViewProductPageObject=PageGeneratorManager.getRecentlyViewedProductPageObject(driver);
		
		log.info("TC_05_Recently_Viewed_Product - Step 16:Verify product: "+ nameProductRecentlyViewed_4+" is displayed");
		verifyTrue(recentlyViewProductPageObject.isProductDisplayByName(nameProductRecentlyViewed_4));
		
		log.info("TC_05_Recently_Viewed_Product -Step 17: Click menu Digital downloads ");
		recentlyViewProductPageObject.clickToMenuPageByLabelName(driver,"Digital downloads");
		productPageObject = PageGeneratorManager.getProductPageObject(driver);
		
		nameProductRecentlyViewed_5="Night Visions";
		log.info("TC_05_Recently_Viewed_Product: Step 18: Click item : "+ nameProductRecentlyViewed_5);
		productDetailPageObject=productPageObject.clickToProductByName(driver,nameProductRecentlyViewed_5);
		
		log.info("TC_05_Recently_Viewed_Product - Step 19:Click 'Recently viewed products' in footer");
		productDetailPageObject.clickToFooterLinkByLabelName(driver, "Recently viewed products");
		recentlyViewProductPageObject=PageGeneratorManager.getRecentlyViewedProductPageObject(driver);
		
		log.info("TC_05_Recently_Viewed_Product - Step 20:Verify product: "+ nameProductRecentlyViewed_5+" is displayed");
		verifyTrue(recentlyViewProductPageObject.isProductDisplayByName(nameProductRecentlyViewed_5));
				
		log.info("TC_05_Recently_Viewed_Product - Step 21:Verify only 3 product is display");
		verifyEquals(recentlyViewProductPageObject.getNumberProductRecentlyViewed(), 3);
		
		log.info("TC_05_Recently_Viewed_Product - Step 22:Verify product: "+ nameProductRecentlyViewed_5+" is displayed");
		verifyTrue(recentlyViewProductPageObject.isProductDisplayByName(nameProductRecentlyViewed_5));
		
		log.info("TC_05_Recently_Viewed_Product - Step 23:Verify product: "+ nameProductRecentlyViewed_4+" is displayed");
		verifyTrue(recentlyViewProductPageObject.isProductDisplayByName(nameProductRecentlyViewed_4));
		
		log.info("TC_05_Recently_Viewed_Product - Step 24:Verify product: "+ nameProductRecentlyViewed_3+" is displayed");
		verifyTrue(recentlyViewProductPageObject.isProductDisplayByName(nameProductRecentlyViewed_3));
		
		log.info("TC_05_Recently_Viewed_Product - Step 25:Verify product: "+ nameProductRecentlyViewed_2+" is not displayed");
		verifyTrue(recentlyViewProductPageObject.isProductNotDisplayByName(nameProductRecentlyViewed_2));
		
		log.info("TC_05_Recently_Viewed_Product - Step 26:Verify product: "+ nameProductRecentlyViewed_1+" is not displayed");
		verifyTrue(recentlyViewProductPageObject.isProductNotDisplayByName(nameProductRecentlyViewed_1));
	}
	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void afterClass(@Optional("chrome")String browserName) {
		log.info("Postcondition: Close the browser:" + browserName);
		cleanBrowserAndDriver();
	}

}

