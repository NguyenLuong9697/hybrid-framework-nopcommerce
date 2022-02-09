package payObjects;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.MyAccountPageUI;
import payUIs.ProductDetailPageUI;
import payUIs.ProductPageUI;
import payUIs.UserBasePageUI;

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

	public String getMessageSuccessfullAfterAddToCart() {
		waitForElementVisible(driver, ProductDetailPageUI.MESSAGE_SUCCESSFULL_AFTER_ADD_WISH_LIST);
		return getTextElement(driver,ProductDetailPageUI.MESSAGE_SUCCESSFULL_AFTER_ADD_WISH_LIST );
	
	}

	public void selectItemInDropDownBySpecificity(String specifictyName,String value) {
		waitForElementClickable(driver, ProductDetailPageUI.DROPDOWN_BY_SPECIFICITY_PRODUCT, specifictyName);
		selectItemInDefaultDropdownByText(driver, ProductDetailPageUI.DROPDOWN_BY_SPECIFICITY_PRODUCT, value, specifictyName);
		
	}

	public void selectSpecificityOfProduct(String labelspecificity, String value) {
		waitForElementClickable(driver, ProductDetailPageUI.DYNAMIC_RADIO_BUTTON_BY_SPECIFICITY_LABEL, labelspecificity,value);
		clickToElement(driver, ProductDetailPageUI.DYNAMIC_RADIO_BUTTON_BY_SPECIFICITY_LABEL, labelspecificity,value);
		
	}

	public String getSelectedValueInDropdrownBySpecificityProduct(String specicityProduct) {
		waitForElementVisible(driver, ProductDetailPageUI.DROPDOWN_BY_SPECIFICITY_PRODUCT, specicityProduct);
		return getSelectedItemInDefaultDropdown(driver, ProductDetailPageUI.DROPDOWN_BY_SPECIFICITY_PRODUCT,specicityProduct);
		
	}

	public boolean isRadioButtonOrCheckBoxSelectedBySpecificityProduct(String specicityProduct, String hdd) {
		waitForElementClickable(driver, ProductDetailPageUI.DYNAMIC_RADIO_BUTTON_BY_SPECIFICITY_LABEL, specicityProduct,hdd);
		return isElementSelected(driver, ProductDetailPageUI.DYNAMIC_RADIO_BUTTON_BY_SPECIFICITY_LABEL, specicityProduct,hdd);
	}


	
	
	
}
