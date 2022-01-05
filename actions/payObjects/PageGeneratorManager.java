package payObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	private PageGeneratorManager() {
		
	}
	public static HomePageObject getHomePageObject(WebDriver driver) {
		
		return new HomePageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPageObject(WebDriver driver) {
	
		return new RegisterPageObject(driver);
	}
	
	public static LoginPageObject getLoginPageObject(WebDriver driver) {
		
		return new LoginPageObject(driver);
	}
	
}
