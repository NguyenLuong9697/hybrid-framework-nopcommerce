package pageObjects.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.user.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver=driver;
	}
	public String getErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.ERROR_MESSAGE);
		return getTextElement(driver, LoginPageUI.ERROR_MESSAGE);
	}
	
	
	
	
}
