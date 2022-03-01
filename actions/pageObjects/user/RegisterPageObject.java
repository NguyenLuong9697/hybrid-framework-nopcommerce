package pageObjects.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.user.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;
	public RegisterPageObject(WebDriver driver) {
		this.driver=driver;
	}
	public String getRegisterMessageSuccessfull() {
		waitForAllElementsVisible(driver,RegisterPageUI.REGISTER_MESSAGE_SUCCESSFULL);
		return getTextElement(driver,RegisterPageUI.REGISTER_MESSAGE_SUCCESSFULL);
	}
	public String getErrorMessageWithExistedEmail() {
		waitForAllElementsVisible(driver,RegisterPageUI.ERROR_MESSAGE_WITH_EXISTED_EMAIL);
		return getTextElement(driver,RegisterPageUI.ERROR_MESSAGE_WITH_EXISTED_EMAIL);
	}
	
	
}
