package payObjects;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.ProductPageUI;

public class ProductPageObject extends BasePage{
	private WebDriver driver;
	public ProductPageObject(WebDriver driver) {
		this.driver=driver;
	}
	public ProductDetailPageObject clickToProductByName(WebDriver driver, String productName) {
		waitForElementClickable(driver,ProductPageUI.DYNAMIC_PRODUCT_BY_NAME, productName);
		clickToElement(driver, ProductPageUI.DYNAMIC_PRODUCT_BY_NAME, productName);
		return PageGeneratorManager.getProductDetailPageObject(driver);
	}
	
	
}
