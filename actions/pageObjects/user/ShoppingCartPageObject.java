package pageObjects.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.user.CompareProductPageUI;
import payUIs.user.ShoppingCartPageUI;
import payUIs.user.UserBasePageUI;
import payUIs.user.WishListPageUI;

public class ShoppingCartPageObject extends BasePage {
	private WebDriver driver;

	public ShoppingCartPageObject(WebDriver driver) {
		this.driver = driver;
	}



	public String getQuantityInShoppingCartByProductName(String nameProductBuy) {
		waitForElementVisible(driver, ShoppingCartPageUI.DYNAMIC_QUANTITY_BY_PRODUCT_NAME, nameProductBuy);
		return getAttributeElement(driver, ShoppingCartPageUI.DYNAMIC_QUANTITY_BY_PRODUCT_NAME, "value",
				nameProductBuy);
	}

	public String getTotalInShoppingCartByProductName(String nameProductBuy) {
		waitForElementVisible(driver, ShoppingCartPageUI.DYNAMIC_TOTAL_BY_PRODUCT_NAME, nameProductBuy);
		return getTextElement(driver, ShoppingCartPageUI.DYNAMIC_TOTAL_BY_PRODUCT_NAME, nameProductBuy);

	}

	public void clickIconRemoveThroughProductName(String nameProductBuy) {
		waitForElementClickable(driver, ShoppingCartPageUI.DYNAMIC_BUTTON_REMOVE_BY_PRODUCT_NAME , nameProductBuy);
		clickToElement(driver, ShoppingCartPageUI.DYNAMIC_BUTTON_REMOVE_BY_PRODUCT_NAME , nameProductBuy);
	}

	public String getMessageAfterRemoveProuductFromCart() {
		waitForElementVisible(driver, ShoppingCartPageUI.LABEL_NO_DATA_AFTER_REMOVE_PRODUCT_FROM_CART);
		return getTextElement(driver, ShoppingCartPageUI.LABEL_NO_DATA_AFTER_REMOVE_PRODUCT_FROM_CART);
	}

	public boolean isProductInCartNotDisplay(String nameProductBuy) {
		//waitForElementInvisible(driver, ShoppingCartPageUI.DYNAMIC_PRODUCT_BY_NAME, nameProductBuy);		
		waitForElementUnDisplay(driver, ShoppingCartPageUI.DYNAMIC_PRODUCT_BY_NAME, nameProductBuy);		
		return isElementUndisplayed(driver, ShoppingCartPageUI.DYNAMIC_PRODUCT_BY_NAME, nameProductBuy);
		
	}

	public String isSpecificityDisplay(String productName) {
		waitForElementVisible(driver, ShoppingCartPageUI.DYNAMIC_SPECIFICITY_PRODUCT_BY_NAME, productName);
		return getTextElement(driver, ShoppingCartPageUI.DYNAMIC_SPECIFICITY_PRODUCT_BY_NAME, productName);
	}

	public void enterToTextboxByAriaLabelThroughProductName(String productname, String value,
			String arialabelinput) {
		waitForElementVisible(driver,ShoppingCartPageUI.DYNAMIC_TEXTBOX_BY_PRODUCT_NAME_AND_ARIA_LABEL,productname, arialabelinput);		
		sendKeyToElement(driver, ShoppingCartPageUI.DYNAMIC_TEXTBOX_BY_PRODUCT_NAME_AND_ARIA_LABEL,value ,productname, arialabelinput);

	}

	public ProductDetailPageObject clickLinkEditAtProduct(String nameProductBuy) {
		waitForElementClickable(driver, ShoppingCartPageUI.DYNAMIC_LINK_EDIT_AT_PRODUCT_NAME , nameProductBuy);
		clickToElement(driver, ShoppingCartPageUI.DYNAMIC_LINK_EDIT_AT_PRODUCT_NAME , nameProductBuy);
		return PageGeneratorManager.getProductDetailPageObject(driver);
	}

	public void clickCheckBoxAgreeWithTerms() {
		waitForElementClickable(driver, ShoppingCartPageUI.CHECKBOX_AGREE_WITH_TERMS);
		checkToDefaultCheckboxOrDefaultRadio(driver, ShoppingCartPageUI.CHECKBOX_AGREE_WITH_TERMS);
		
	}

}
