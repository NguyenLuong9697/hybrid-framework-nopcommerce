package pageObjects.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.BasePage;
import payUIs.user.ProductDetailPageUI;
import payUIs.user.ProductPageUI;

public class ProductPageObject extends BasePage {
	private WebDriver driver;

	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public ProductDetailPageObject clickToProductByName(WebDriver driver, String productName) {
		waitForElementClickable(driver, ProductPageUI.DYNAMIC_PRODUCT_BY_NAME, productName);
		clickToElement(driver, ProductPageUI.DYNAMIC_PRODUCT_BY_NAME, productName);
		return PageGeneratorManager.getProductDetailPageObject(driver);
	}

	public boolean isProductNameSortAscending() {
		isJQueryLoadSuccess(driver);
		List<String> listProductNameText = new ArrayList<String>();
		List<WebElement> products = getListWebElements(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		for (WebElement webElement : products) {
			listProductNameText.add(webElement.getText().trim());
		}
		/*System.out.println("Trc khi sort");
		for (String text : listProductNameText) {
			System.out.println(text);
		}*/
		List<String> listProductNameTextClone = new ArrayList<String>(listProductNameText);
		Collections.sort(listProductNameTextClone);		
		/*System.out.println("=======");
		System.out.println("sau khi sort");
		for (String text : listProductNameTextClone) {
			System.out.println(text);
		}*/
		return listProductNameText.equals(listProductNameTextClone);
	}

	public boolean isProductNameSortDescending() {
		isJQueryLoadSuccess(driver);
		List<String> listProductNameText = new ArrayList<String>();
		List<WebElement> products = getListWebElements(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		for (WebElement webElement : products) {
			listProductNameText.add(webElement.getText().trim());
		}
		List<String> listProductNameTextClone = new ArrayList<String>(listProductNameText);
		Collections.sort(listProductNameTextClone);
		Collections.reverse(listProductNameTextClone);
		return listProductNameText.equals(listProductNameTextClone);
	}

	public boolean isProductPriceSortAscending() {
		isJQueryLoadSuccess(driver);
		String elementText="";
		String price="";
		List<Float> listProductPriceText = new ArrayList<Float>();
		List<WebElement> products= getListWebElements(driver, ProductPageUI.PRODUCT_PRICE_TEXT);
		for (WebElement webElement : products) {
			elementText=webElement.getText().trim().replace("$","");
			price=elementText.replace(",", "");
			listProductPriceText.add(Float.parseFloat(price));
			
		}
		
		List<Float> listProductPriceTextClone= new ArrayList<Float>(listProductPriceText);
		Collections.sort(listProductPriceTextClone);			
		return listProductPriceText.equals(listProductPriceTextClone);
	}

	public boolean isProductPriceSortDescending() {
		isJQueryLoadSuccess(driver);
		String elementText="";
		String price="";
		List<Float> listProductPriceText = new ArrayList<Float>();
		List<WebElement> products= getListWebElements(driver, ProductPageUI.PRODUCT_PRICE_TEXT);
		for (WebElement webElement : products) {
			elementText=webElement.getText().trim().replace("$","");
			price=elementText.replace(",", "");
			listProductPriceText.add(Float.parseFloat(price));
		}
		
		List<Float> listProductPriceTextClone= new ArrayList<Float>(listProductPriceText);
		Collections.sort(listProductPriceTextClone);	
		Collections.reverse(listProductPriceTextClone);
		return listProductPriceText.equals(listProductPriceTextClone);
	}

	public boolean isNumberProductInPage(int number) {
		isJQueryLoadSuccess(driver);
		waitForAllElementsVisible(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		List<WebElement> products= getListWebElements(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		if(products.size()<=number) return true;
		else return false;
	}

	public void clickToPageNumber(String pageNumber) {
		waitForElementClickable(driver, ProductPageUI.DYNAMIC_PAGGING_BY_NUMBER,pageNumber);
		clickToElement(driver, ProductPageUI.DYNAMIC_PAGGING_BY_NUMBER,pageNumber);
		
	}
	public boolean isPageNumberActive(String pageNumber) {
		waitForElementVisible(driver, ProductPageUI.DYNAMIC_PAGGING_BY_NUMBER_ACTIVE,pageNumber);
		return isElementDisplay(driver, ProductPageUI.DYNAMIC_PAGGING_BY_NUMBER_ACTIVE,pageNumber);
	}

	public boolean isNextPageIconDisplay() {
		waitForElementVisible(driver, ProductPageUI.NEXT_PAGE_ICON);
		return isElementDisplay(driver, ProductPageUI.NEXT_PAGE_ICON);
	}

	public boolean isPreviousPageIconDisplay() {
		waitForElementVisible(driver, ProductPageUI.PREVIOUS_PAGE_ICON);
		return isElementDisplay(driver, ProductPageUI.PREVIOUS_PAGE_ICON);
	}

	public boolean isPaggingNotDisplay() {
		waitForElementInvisible(driver, ProductPageUI.PAGGING_PAGE);		
		return isElementUndisplayed(driver, ProductPageUI.PAGGING_PAGE);
		
	}

	public void clickButtonByTitleThroughProductName(String productName, String titleButton) {
		waitForElementClickable(driver, ProductPageUI.DYNAMIC_BUTTON_BY_TITLE_THROUGH_PRODUCT_NAME, productName,titleButton);
		clickToElement(driver,ProductPageUI.DYNAMIC_BUTTON_BY_TITLE_THROUGH_PRODUCT_NAME, productName,titleButton);
	}

	public Object getMessageSuccessfullAfterAddToComapre() {
		waitForElementVisible(driver, ProductPageUI.MESSAGE_SUCCESSFULL_AFTER_ADD_TO_COMPARE);
		return getTextElement(driver, ProductPageUI.MESSAGE_SUCCESSFULL_AFTER_ADD_TO_COMPARE );
	
	}

	public CompareProductPageObject clickLinkProductComparison() {
		waitForElementClickable(driver, ProductPageUI.LINK_PRODUCT_COMPARISON);
		clickToElement(driver,ProductPageUI.LINK_PRODUCT_COMPARISON);
		return PageGeneratorManager.getCompareProductPageObject(driver);
	}

}
