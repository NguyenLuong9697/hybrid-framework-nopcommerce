package pageObjects.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.user.CompareProductPageUI;
import payUIs.user.WishListPageUI;

public class CompareProductPageObject extends BasePage{
	private WebDriver driver;
	public CompareProductPageObject(WebDriver driver) {
		this.driver=driver;
	}
	
	public boolean isCompareProductDisplay(String productNameCompare_1, String priceProductCompare_1) {
		waitForElementVisible(driver, CompareProductPageUI.DYNAMIC_PRODUCT_NAME_PRICE,productNameCompare_1,priceProductCompare_1);
		return isElementDisplay(driver,CompareProductPageUI.DYNAMIC_PRODUCT_NAME_PRICE,productNameCompare_1,priceProductCompare_1);
	}

	public void clickButtonClearList() {
		waitForElementClickable(driver, CompareProductPageUI.BUTTON_CLEAR_LIST);
		clickToElement(driver, CompareProductPageUI.BUTTON_CLEAR_LIST);
		
	}

	public String getMessageAfterClearListProductCompare() {
		waitForElementVisible(driver, CompareProductPageUI.MESSAGE_AFTER_CLEAR_LIST_COMPARE_PRODUCT);
		return getTextElement(driver, CompareProductPageUI.MESSAGE_AFTER_CLEAR_LIST_COMPARE_PRODUCT);
	}

	public boolean isCompareProductNotDisplay(String productNameCompare_1, String priceProductCompare_1) {
		//waitForElementInvisible(driver, CompareProductPageUI.DYNAMIC_PRODUCT_NAME_PRICE, productNameCompare_1, priceProductCompare_1);		
		waitForElementUnDisplay(driver, CompareProductPageUI.DYNAMIC_PRODUCT_NAME_PRICE, productNameCompare_1, priceProductCompare_1);
		return isElementUndisplayed(driver, CompareProductPageUI.DYNAMIC_PRODUCT_NAME_PRICE, productNameCompare_1, priceProductCompare_1);
		
		
	}
	
	
	
}
