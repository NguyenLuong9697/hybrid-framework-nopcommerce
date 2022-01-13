package payObjects;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.ShoppingCartPageUI;
import payUIs.WishListPageUI;

public class ShoppingCartPageObject extends BasePage{
	private WebDriver driver;
	public ShoppingCartPageObject(WebDriver driver) {
		this.driver=driver;
	}
	public boolean isRowValueDisplayed(String sku, String productName, String price, String total) {
		waitForElementVisible(driver, ShoppingCartPageUI.DYNAMIC_ROW_VALUE_BY_SKU_PRODUCTNAME_PRICE_TOTAL, sku, productName, price, total);
		return isElementDisplay(driver, ShoppingCartPageUI.DYNAMIC_ROW_VALUE_BY_SKU_PRODUCTNAME_PRICE_TOTAL, sku, productName, price, total);
	
	}
	
	
}
