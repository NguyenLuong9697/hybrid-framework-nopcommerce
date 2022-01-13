package payObjects;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.MyAccountPageUI;

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
	
	

	
	
	
	
	
}
