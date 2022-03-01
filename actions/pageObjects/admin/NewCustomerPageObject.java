package pageObjects.admin;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.admin.NewCustomerPageUI;

public class NewCustomerPageObject  extends BasePage{
	private WebDriver driver;
	public NewCustomerPageObject(WebDriver driver) {
		this.driver=driver;
	}
	public static ProductDetailPageObject getProductDetailPageObject(WebDriver driver) {
	
		return new ProductDetailPageObject(driver);
	}

}
