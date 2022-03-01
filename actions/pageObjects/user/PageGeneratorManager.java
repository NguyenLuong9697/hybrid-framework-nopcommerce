package pageObjects.user;

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
	
	public static SearchPageObject getSearchPageObject(WebDriver driver) {
		
		return new SearchPageObject(driver);
	}
	
	public static WishListPageObject getWishListPageObject(WebDriver driver) {
		
		return new WishListPageObject(driver);
	}
	public static ShoppingCartPageObject getShoppingCartPageObject(WebDriver driver) {
		
		return new ShoppingCartPageObject(driver);
	}
	public static CompareProductPageObject getCompareProductPageObject(WebDriver driver) {
		
		return new CompareProductPageObject(driver);
	}
	
	public static RecentlyViewedProductPageObject getRecentlyViewedProductPageObject(WebDriver driver) {
		
		return new RecentlyViewedProductPageObject(driver);
	}
	
	
	public static CheckoutPageObject getCheckoutPageObject(WebDriver driver) {
		
		return new CheckoutPageObject(driver);
	}
}
