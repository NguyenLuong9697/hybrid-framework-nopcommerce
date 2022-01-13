package payObjects;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.MyAccountPageUI;
import payUIs.ProductDetailPageUI;
import payUIs.ProductPageUI;

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

	public void clickButtonByLabelNameThroughProductName(String labelButton, String productName) {
		waitForElementClickable(driver, ProductDetailPageUI.DYNAMIC_BUTTON_BY_LABEL_NAME_THROUGH_PRODUCT_NAME, productName,labelButton);
		clickToElement(driver,ProductDetailPageUI.DYNAMIC_BUTTON_BY_LABEL_NAME_THROUGH_PRODUCT_NAME, productName,labelButton);
	}

	public String getMessageSuccessfullAfterAddWishList() {
		waitForElementVisible(driver, ProductDetailPageUI.MESSAGE_SUCCESSFULL_AFTER_ADD_WISH_LIST);
		return getTextElement(driver, ProductDetailPageUI.MESSAGE_SUCCESSFULL_AFTER_ADD_WISH_LIST );
	
	}

	public void clickButtonCloseMessageSuccess() {
		waitForElementClickable(driver, ProductDetailPageUI.BUTTON_CLOSE_MESSAGE_SUCCESS);
		clickToElement(driver,ProductDetailPageUI.BUTTON_CLOSE_MESSAGE_SUCCESS);
		
	}


	
	
	
}
