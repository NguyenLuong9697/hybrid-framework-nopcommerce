package pageObjects.user;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import common.BasePage;
import payUIs.user.SearchPageUI;

public class SearchPageObject extends BasePage{
	private WebDriver driver;
	public SearchPageObject(WebDriver driver) {
		this.driver=driver;
	}
	
	public void clickSearchButton() {
		waitForElementClickable(driver, SearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, SearchPageUI.SEARCH_BUTTON);
	}

	public Object getErrorMessageWhenSearchWithEmptyData() {
		waitForElementVisible(driver,SearchPageUI.ERROR_MESSAGE_SEARCH_WITH_EMPTY);
		return getTextElement(driver, SearchPageUI.ERROR_MESSAGE_SEARCH_WITH_EMPTY);
	}

	public boolean verifyInformationAfterSearchWithRelativeData(String searchText) {
	//	scrollToElement(driver, SearchPageUI.PRODUCT_NAME_TEXT);
		boolean result=true;
		waitForAllElementsVisible(driver, SearchPageUI.PRODUCT_NAME_TEXT);
		List<WebElement> products= getListWebElements(driver, SearchPageUI.PRODUCT_NAME_TEXT);
		for (WebElement webElement : products) {
			if(!webElement.getText().contains(searchText)) {
				result=false;
				break;
			}
		}
		
		return result;
	}

	public String getErrorMessageWhenSearchWithDataNotExist() {
		waitForElementVisible(driver,SearchPageUI.ERROR_MESSAGE_SEARCH_DATA_NOT_EXIST);
		return getTextElement(driver, SearchPageUI.ERROR_MESSAGE_SEARCH_DATA_NOT_EXIST);
	}

	public boolean verifyInformationAfterSearchWithAbsoluteData(String searchKey) {
	
		waitForAllElementsVisible(driver, SearchPageUI.PRODUCT_NAME_TEXT);		
		List<WebElement> products= getListWebElements(driver, SearchPageUI.PRODUCT_NAME_TEXT);
		if(products.size()!=1) {
			return false;
		}
		if(!getTextElement(driver, SearchPageUI.PRODUCT_NAME_TEXT).equals(searchKey))
		 {
			
			return false;
		 }
		
		return true;
	}

	public void clickToCheckBoxByLabelName(String labelCheckbox) {
		waitForElementClickable(driver, SearchPageUI.DYNAMIC_CHECKBOX_BY_LABEL_NAME, labelCheckbox);
		checkToDefaultCheckboxOrDefaultRadio(driver, SearchPageUI.DYNAMIC_CHECKBOX_BY_LABEL_NAME, labelCheckbox);
	}

	public boolean verifyInformationAfterSearchSubCategories(String searchKey) {
		waitForAllElementsVisible(driver, SearchPageUI.PRODUCT_NAME_TEXT);		
		List<WebElement> products= getListWebElements(driver, SearchPageUI.PRODUCT_NAME_TEXT);
		if(products.size()!=1) {
			
			return false;
		}
		
		if(!getTextElement(driver, SearchPageUI.PRODUCT_NAME_TEXT).contains(searchKey))
		 {
			
			return false;
		 }
		return true;
	}

	public boolean verifyInformationAfterSearchCorrectManufacturer(String searchKey) {
		waitForAllElementsVisible(driver, SearchPageUI.PRODUCT_NAME_TEXT);		
		List<WebElement> products= getListWebElements(driver, SearchPageUI.PRODUCT_NAME_TEXT);
		if(products.size()!=1) {
			
			return false;
		}
		
		if(!getTextElement(driver, SearchPageUI.PRODUCT_NAME_TEXT).contains(searchKey))
		 {
			
			return false;
		 }
		return true;
	}

	
	
}
