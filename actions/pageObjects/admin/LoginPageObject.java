package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import common.BasePage;

public class LoginPageObject  extends BasePage{
	private WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver=driver;
	}
}
