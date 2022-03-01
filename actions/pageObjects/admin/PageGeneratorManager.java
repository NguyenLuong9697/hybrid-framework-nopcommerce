package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import pageObjects.admin.LoginPageObject;

public class PageGeneratorManager {

	private PageGeneratorManager() {

	}
	public static LoginPageObject getLoginPageObject(WebDriver driver) {
		
		return new LoginPageObject(driver);
	}
	public static DashboardPageObject getDashboardObject(WebDriver driver) {
		
		return new DashboardPageObject(driver);
	}

	public static ProductPageObject getProductPageObject(WebDriver driver) {

		return new ProductPageObject(driver);
	}	
	public static ProductDetailPageObject getProductDetailPageObject(WebDriver driver) {

		return new ProductDetailPageObject(driver);
	}	
	public static CustomerPageObject getCustomerPageObject(WebDriver driver) {

		return new CustomerPageObject(driver);
	}	
	public static NewCustomerPageObject getNewCustomerPageObject(WebDriver driver) {

		return new NewCustomerPageObject(driver);
	}
	
	public static CustomerDetailPageObject getCustomerDetailPageObject(WebDriver driver) {

		return new CustomerDetailPageObject(driver);
	}
	
	public static NewAddressCustomerPageObject getNewAddressCustomerPageObject(WebDriver driver) {

		return new NewAddressCustomerPageObject(driver);
	}
	
	public static EditAddressCustomerPageObject getEditAddressCustomerPageObject(WebDriver driver) {

		return new EditAddressCustomerPageObject(driver);
	}
}

