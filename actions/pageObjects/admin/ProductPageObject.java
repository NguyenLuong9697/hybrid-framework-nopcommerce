package pageObjects.admin;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.BasePage;
import payUIs.admin.ProductPageUI;

public class ProductPageObject  extends BasePage{

	private WebDriver driver;
	public ProductPageObject(WebDriver driver) {
		this.driver=driver;
	}
	public void clickToExpandSearchPanel() {
		waitForElementClickable(driver,ProductPageUI.ICON_EXPAND_SEARCH_PANEL);
		String status= getAttributeElement(driver, ProductPageUI.ICON_EXPAND_SEARCH_PANEL,"class");
		if(status.contains("fa-angle-down")) {
			clickToElement(driver, ProductPageUI.ICON_EXPAND_SEARCH_PANEL);
		}
		
	}
	public int getNumberProductDisplay() {
		waitForAllElementsVisible(driver,ProductPageUI.NUMBER_PRODUCT_DISPLAY);
		List<WebElement> products= getListWebElements(driver, ProductPageUI.NUMBER_PRODUCT_DISPLAY);
		return products.size();
	}

		
}
