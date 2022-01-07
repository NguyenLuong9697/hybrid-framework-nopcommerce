package payObjects;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.ProductDetailPageUI;

public class ProductDetailPageObject extends BasePage{
	private WebDriver driver;
	public ProductDetailPageObject(WebDriver driver) {
		this.driver=driver;
	}
	
	public ProductReviewPageObject clickLinkAddYourReview() {
		waitForElementClickable(driver, ProductDetailPageUI.ADD_YOUR_REVIEW_LINK);
		clickToElement(driver,ProductDetailPageUI.ADD_YOUR_REVIEW_LINK);
		return PageGeneratorManager.getProductReviewPageObject(driver);
	}
	
	
}
