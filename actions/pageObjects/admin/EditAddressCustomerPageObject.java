package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import common.BasePage;

public class EditAddressCustomerPageObject  extends BasePage{
	private WebDriver driver;
	public EditAddressCustomerPageObject(WebDriver driver) {
		this.driver=driver;
	}
	public String getAddress(String fax) {
		//String s=address_1+" \n"+city+","+ zipCode;
		String locator ="//td[text()='%s']/following-sibling::td/div";
		
		locator = getDynamiLocator(locator, fax);
		
		//System.out.println(locator);
		return getTextElement(driver, locator);
	}
	
}
