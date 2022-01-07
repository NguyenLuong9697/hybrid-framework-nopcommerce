package payObjects;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.ProductReviewPageUI;
import payUIs.RegisterPageUI;

public class ProductReviewPageObject extends BasePage{
	private WebDriver driver;
	public ProductReviewPageObject(WebDriver driver) {
		this.driver=driver;
	}
	
	public void selectRating(WebDriver driver, String ariaLabel) {
		waitForElementClickable(driver,ProductReviewPageUI.DYNAMIC_RADIOBUTTON_RATING, ariaLabel);
		clickToElement(driver, ProductReviewPageUI.DYNAMIC_RADIOBUTTON_RATING, ariaLabel);
	}

	public String getSuccessMessageAfterSubmitReview() {
		waitForAllElementsVisible(driver,ProductReviewPageUI.SUCCESS_MESSAFE_AFTER_SUBMIT_REVIEW);
		return getTextElement(driver,ProductReviewPageUI.SUCCESS_MESSAFE_AFTER_SUBMIT_REVIEW);
	}

	

	public boolean isTitleOfReviewDisplay(WebDriver driver, String reviewTitle) {
		waitForElementVisible(driver, ProductReviewPageUI.DYNAMIC_TITLE_OF_REVIEW, reviewTitle);
		return isElementDisplay(driver, ProductReviewPageUI.DYNAMIC_TITLE_OF_REVIEW, reviewTitle);
	}

	public boolean isTextOfReviewDisplay(WebDriver driver2, String reviewText) {
		waitForElementVisible(driver, ProductReviewPageUI.DYNAMIC_TEXT_OF_REVIEW, reviewText);
		return isElementDisplay(driver, ProductReviewPageUI.DYNAMIC_TEXT_OF_REVIEW, reviewText);
	}

	public boolean isUserNameOfReviewDisplay(WebDriver driver2, String userNameReview) {
		waitForElementVisible(driver, ProductReviewPageUI.DYNAMIC_USER_NAME_OF_REVIEW, userNameReview);
		return isElementDisplay(driver, ProductReviewPageUI.DYNAMIC_USER_NAME_OF_REVIEW, userNameReview);
	}
	
	public boolean isDateTimeOfReviewDisplay(WebDriver driver, String datetimeReview) {
		waitForElementVisible(driver, ProductReviewPageUI.DYNAMIC_DATE_TIME_OF_REVIEW, datetimeReview);
		return isElementDisplay(driver, ProductReviewPageUI.DYNAMIC_DATE_TIME_OF_REVIEW, datetimeReview);
	}
	
}
