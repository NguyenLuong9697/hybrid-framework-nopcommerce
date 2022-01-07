package payObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	private PageGeneratorManager() {
		
	}
	public static HomePageObject getHomePageObject(WebDriver driver) {
		
		return new HomePageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPageObject(WebDriver driver) {
	
		return new RegisterPageObject(driver);
	}
	
	public static LoginPageObject getLoginPageObject(WebDriver driver) {
		
		return new LoginPageObject(driver);
	}
	
	public static ProductPageObject getProductPageObject(WebDriver driver) {
		
		return new ProductPageObject(driver);
	}
	
	public static MyAccountPageObject getMyAccountPageObject(WebDriver driver) {
		
		return new MyAccountPageObject(driver);
	}
	
	public static ProductDetailPageObject getProductDetailPageObject(WebDriver driver) {
		
		return new ProductDetailPageObject(driver);
	}
	
	public static ProductReviewPageObject getProductReviewPageObject(WebDriver driver) {
		
		return new ProductReviewPageObject(driver);
	}
}
