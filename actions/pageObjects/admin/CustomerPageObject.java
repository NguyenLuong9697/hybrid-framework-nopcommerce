package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.admin.CustomerPageUI;

public class CustomerPageObject  extends BasePage{
	private WebDriver driver;
	public CustomerPageObject(WebDriver driver) {
		this.driver=driver;
	}
	public static ProductDetailPageObject getProductDetailPageObject(WebDriver driver) {
	
		return new ProductDetailPageObject(driver);
	}
	public int getNumberCustomer() {	
		return getElementSize(driver, CustomerPageUI.NUMBER_CUSTOMER);
	}
	public CustomerDetailPageObject clickIconEditAtCustomerName(String customerFullName) {
		waitForElementClickable(driver, CustomerPageUI.ICON_EDIT_AT_CUSTOMER_NAME,customerFullName);
		clickToElement(driver, CustomerPageUI.ICON_EDIT_AT_CUSTOMER_NAME,customerFullName);
		return PageGeneratorManager.getCustomerDetailPageObject(driver);
	}
	
	

}
