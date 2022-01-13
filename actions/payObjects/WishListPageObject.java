package payObjects;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.ProductPageUI;
import payUIs.WishListPageUI;

public class WishListPageObject extends BasePage{
	private WebDriver driver;
	public WishListPageObject(WebDriver driver) {
		this.driver=driver;
	}
	public boolean isRowValueDisplayed(String sku, String productName, String price, String total) {
		waitForElementVisible(driver, WishListPageUI.DYNAMIC_ROW_VALUE_BY_SKU_PRODUCTNAME_PRICE_TOTAL, sku, productName, price, total);
		return isElementDisplay(driver, WishListPageUI.DYNAMIC_ROW_VALUE_BY_SKU_PRODUCTNAME_PRICE_TOTAL, sku, productName, price, total);
	}
	public void clickLinkYourWishlistURLForSharing() {
		waitForElementClickable(driver,WishListPageUI.LINK_SHARE_WISHLIST);
		clickToElement(driver, WishListPageUI.LINK_SHARE_WISHLIST);
		
	}
	public String getTitleOfWishList() {
		waitForElementVisible(driver, WishListPageUI.TITLE_OF_WISHLIST);
		return getTextElement(driver, WishListPageUI.TITLE_OF_WISHLIST);
	}
	public void checkToCheckBoxAtRowContainProduct(String productName) {
		waitForElementClickable(driver,WishListPageUI.DYNAMIC_CHECKBOX_AT_ROW_CONTAIN_PRODUCT,productName);
		checkToDefaultCheckboxOrDefaultRadio(driver, WishListPageUI.DYNAMIC_CHECKBOX_AT_ROW_CONTAIN_PRODUCT,productName);
		
	}
	public boolean isRowValueNotDisplayed(String sku, String productName, String price, String total) {
		waitForElementInvisible(driver, WishListPageUI.DYNAMIC_ROW_VALUE_BY_SKU_PRODUCTNAME_PRICE_TOTAL,sku, productName, price, total);		
		return isElementUndisplayed(driver,WishListPageUI.DYNAMIC_ROW_VALUE_BY_SKU_PRODUCTNAME_PRICE_TOTAL,sku, productName, price, total);
		
	}
	public void clickIconDeleteAtRowContainProduct(String productName) {
		waitForElementClickable(driver,WishListPageUI.DYNAMIC_ICON_DELETE_AT_ROW_CONTAIN_PRODUCT,productName);
		clickToElement(driver, WishListPageUI.DYNAMIC_ICON_DELETE_AT_ROW_CONTAIN_PRODUCT,productName);
	}
	public boolean isWishListEmpty() {
		waitForElementVisible(driver, WishListPageUI.MESSAGE_NO_DATA_IN_WISHLIST);
		return isElementDisplay(driver, WishListPageUI.MESSAGE_NO_DATA_IN_WISHLIST);
	}
	
	
	
}
