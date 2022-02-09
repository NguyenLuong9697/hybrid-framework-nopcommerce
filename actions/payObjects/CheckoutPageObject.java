package payObjects;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.CheckoutPageUI;

public class CheckoutPageObject extends BasePage{
	private WebDriver driver;
	public CheckoutPageObject(WebDriver driver) {
		this.driver=driver;
	}
	public void clickButtonByLabelAtIDSection(String idSection, String label) {
		waitForElementClickable(driver, CheckoutPageUI.DYNAMIC_BUTTON_BY_LABEL_AT_ID_SECTION,idSection,label);
		clickToElement(driver, CheckoutPageUI.DYNAMIC_BUTTON_BY_LABEL_AT_ID_SECTION,idSection,label);
	}
	
	
	
	
	public boolean isSuccessMessageAfterConfirmOrderDisplay() {
		waitForElementVisible(driver, CheckoutPageUI.SUCCESS_MESSAGE_AFTER_CONFIRM_ORDER);
		return isElementDisplay(driver, CheckoutPageUI.SUCCESS_MESSAGE_AFTER_CONFIRM_ORDER);
	}
	public int getOrderNumber() {
		waitForElementVisible(driver, CheckoutPageUI.ORDER_NUMBER);
		int orderNumber= Integer.parseInt(getTextElement(driver,CheckoutPageUI.ORDER_NUMBER).split(":")[1].trim());				
		return orderNumber;
	}
	
	
	
}
