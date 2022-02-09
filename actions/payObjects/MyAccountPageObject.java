package payObjects;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.CheckoutPageUI;
import payUIs.MyAccountPageUI;
import payUIs.UserBasePageUI;

public class MyAccountPageObject extends BasePage{
	private WebDriver driver;
	public MyAccountPageObject(WebDriver driver) {
		this.driver=driver;
	}
	public String getInformationOfAddressByClassName(WebDriver driver, String classNameAdrress) {
		waitForElementVisible(driver, MyAccountPageUI.INFOR_ADDRESS_BY_CLASS_NAME , classNameAdrress);
		return getTextElement(driver, MyAccountPageUI.INFOR_ADDRESS_BY_CLASS_NAME , classNameAdrress);
	}
	public String getMessageSuccessfullAfterChangePassword() {
		waitForElementVisible(driver, MyAccountPageUI.MESSAGE_SUCCESSFULL_AFTER_CHANGE_PW );
		return getTextElement(driver, MyAccountPageUI.MESSAGE_SUCCESSFULL_AFTER_CHANGE_PW );
	
	}
	public void clickButtonCloseMessageSuccess() {
		waitForElementClickable(driver, MyAccountPageUI.BUTTON_CLOSE_MESSAGE_SUCCESS);
		clickToElement(driver,MyAccountPageUI.BUTTON_CLOSE_MESSAGE_SUCCESS);		
		//waitForElementInvisible(driver, MyAccountPageUI.MESSAGE_SUCCESSFULL_AFTER_CHANGE_PW);
		
	}
	public int getOrderNumberAtPageMyAccount() {
		waitForElementVisible(driver,MyAccountPageUI.ORDER_NUMBER);
		return Integer.parseInt(getTextElement(driver,MyAccountPageUI.ORDER_NUMBER).split(":")[1].trim());				
		
	}
	public void clickButtonDetailAtOrderNumber(int orderNumber) {
		String order = String.valueOf(orderNumber);
		waitForElementClickable(driver, MyAccountPageUI.BUTTON_DETAILS_AT_ORDER_NUMBER,order);
		clickToElement(driver, MyAccountPageUI.BUTTON_DETAILS_AT_ORDER_NUMBER,order);		
		
	}
	public String getOrderDateDisplay() {
		waitForElementVisible(driver, MyAccountPageUI.ORDER_DATE);
		return getTextElement(driver, MyAccountPageUI.ORDER_DATE).toLowerCase();
	}
	public String getOrderStatus() {
		waitForElementVisible(driver, MyAccountPageUI.ORDER_STATUS);
		return getTextElement(driver, MyAccountPageUI.ORDER_STATUS);
	}
	public String getOrderTotal() {
		waitForElementVisible(driver, MyAccountPageUI.ORDER_TOTAL);
		return getTextElement(driver, MyAccountPageUI.ORDER_TOTAL);
	}
	public boolean isProductDisplayedInMyOrder(String sku, String productName, String price, String total) {
		waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_ROW_VALUE_BY_SKU_PRODUCTNAME_PRICE_TOTAL_IN_MY_ORDER, sku,
				productName, price, total);
		return isElementDisplay(driver,MyAccountPageUI.DYNAMIC_ROW_VALUE_BY_SKU_PRODUCTNAME_PRICE_TOTAL_IN_MY_ORDER, sku,
				productName, price, total);

	}
	public String getInformationPaymentSatus() {
		waitForElementVisible(driver, MyAccountPageUI.PAYMENT_STATUS);
		return getTextElement(driver, MyAccountPageUI.PAYMENT_STATUS);
	}
	public String getInformationShippingSatus() {
		waitForElementVisible(driver, MyAccountPageUI.SHIPPING_STATUS);
		return getTextElement(driver, MyAccountPageUI.SHIPPING_STATUS);
	}
	

	
}
