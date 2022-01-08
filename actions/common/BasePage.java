package common;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import payUIs.UserBasePageUI;



public class BasePage {
	private long shortTimeOut= GlobalConstants.SHORT_TIMEOUT;
	private long longTimeOut=GlobalConstants.LONG_TIMEOUT;
	private Alert alert;
	private WebDriverWait explicit;
	private Select select;
	private JavascriptExecutor jsExecutor;
	private Actions action;
	public static BasePage getBasePage() {
		return new BasePage();
	}
	
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	public void setAllCookies(WebDriver driver,Set<Cookie> cookies ) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
	}
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();;
	}
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void overrideGlobalTimeOut(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	public Alert waitAlertPresence(WebDriver driver) {
		explicit=new WebDriverWait(driver, longTimeOut);
		return alert=explicit.until(ExpectedConditions.alertIsPresent());
	}
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeOut(driver, shortTimeOut);
		List<WebElement> listElement= getListWebElements(driver, locator);
		overrideGlobalTimeOut(driver, longTimeOut);
		if(listElement.size()==0) {
			System.out.println("Element not in DOM");
			return true;
		}else if(listElement.size()>0 && !listElement.get(0).isDisplayed()) {
			System.out.println("Element in DOM but undisplay");
			return true;
		}else {
			System.out.println("Element in DOM and display");
			return false;
		}
	}
	public void acceptAlert(WebDriver driver) {
		alert=waitAlertPresence(driver);
		alert.accept();
	}
	public void dismissAlert(WebDriver driver) {
		alert=waitAlertPresence(driver);
		alert.dismiss();
	}
	public String getTextAlert(WebDriver driver) {
		alert=waitAlertPresence(driver);
		return alert.getText();
	}
	public void sendKeysToAlert(WebDriver driver, String value) {
		alert=waitAlertPresence(driver);
		alert.sendKeys(value);
	}
	public void switchWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows=driver.getWindowHandles();
		for (String id : allWindows) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows=driver.getWindowHandles();
		for (String id : allWindows) {
			driver.switchTo().window(id);
			if(driver.getTitle().equals(title)) {
				break;
			}
		}
	}
	public void closeTabWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows=driver.getWindowHandles();
		for (String id : allWindows) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		
	}
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public By getXPathLocator(String locator) {
		return By.xpath(locator);
	}
	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getXPathLocator(locator));
	}
	public WebElement getWebElement(WebDriver driver, String locator, String...params) {
		locator=getDynamiLocator(locator, params);
		return driver.findElement(getXPathLocator(locator));
	}
	public List<WebElement> getListWebElements(WebDriver driver, String locator) {
		return driver.findElements(getXPathLocator(locator));
	}
	public List<WebElement> getListWebElements(WebDriver driver, String locator, String...params) {
		return driver.findElements(getXPathLocator(getDynamiLocator(locator, params)));
	}
	public int getElementSize(WebDriver driver, String locator) {
		return getListWebElements(driver, locator).size();
	}
	public int getElementSize(WebDriver driver, String locator,String...params) {
		return getListWebElements(driver, locator,params).size();
	}
	public void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}
	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		getWebElement(driver, locator).clear();
		getWebElement(driver, locator).sendKeys(value);
	}
	public void sendKeyToElement(WebDriver driver, String locator, String value,String...params) {
		locator=getDynamiLocator(locator, params);
		getWebElement(driver, locator).clear();
		getWebElement(driver, locator).sendKeys(value);
	}
	public void selectItemInDefaultDropdownByText(WebDriver driver, String locator, String itemText) {
		select=new Select(getWebElement(driver, locator));
		select.selectByVisibleText(itemText);
	}
	public void selectItemInDefaultDropdownByText(WebDriver driver, String locator, String itemText, String... params) {
		locator=getDynamiLocator(locator, params);
		select=new Select(getWebElement(driver, locator));
		select.selectByVisibleText(itemText);
	}
	public String getSelectedItemInDefaultDropdown(WebDriver driver, String locator) {
		select=new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	public String getSelectedItemInDefaultDropdown(WebDriver driver, String locator, String... params) {
		locator=getDynamiLocator(locator, params);
		select=new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		select=new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}
	public void selectItemInCustomDropdown(WebDriver driver, String xPathParent, String xPathChild, String expectedItem) {
		explicit=new WebDriverWait(driver, longTimeOut);
		jsExecutor=(JavascriptExecutor)driver;
		clickToElement(driver, xPathParent);		
		sleepInSecond(1);
		List<WebElement> listItems= explicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getXPathLocator(xPathChild)));
			for (WebElement item : listItems) {
			if(item.getText().trim().equals(expectedItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				sleepInSecond(1);
				break;
			}
			
		}
	}
	public String getAttributeElement(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}
	public String getAttributeElement(WebDriver driver, String locator, String attributeName, String...params ) {
		locator=getDynamiLocator(locator, params);
		return getWebElement(driver, locator).getAttribute(attributeName);
	}
	public String getTextElement(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}
	public String getTextElement(WebDriver driver, String locator,String...params) {
		locator=getDynamiLocator(locator, params);
		return getWebElement(driver, locator).getText();
	}
	public String convertRgbaToHex(String rgba) {
		String color = Color.fromString(rgba).asHex();
		return color;
	}
	public String getCssValueElement(WebDriver driver, String locator, String cssAttribute) {
		return getWebElement(driver, locator).getCssValue(cssAttribute);
	}
	public void checkToDefaultCheckboxOrDefaultRadio(WebDriver driver , String locator) {
		
		if(!isElementSelected(driver, locator)) {			
			clickToElement(driver, locator);
		}
	}
	public void checkToDefaultCheckboxOrDefaultRadio(WebDriver driver , String locator,  String...params) {
		if(!isElementSelected(driver, locator, params)) {
			clickToElement(driver, locator, params);
		}
		
	}
	public void uncheckToDefaultCheckbox(WebDriver driver , String locator) {
		
		if(isElementSelected(driver, locator)) {
			clickToElement(driver, locator);
		}
	}
	public boolean isElementDisplay(WebDriver driver, String locator) {
		try {
			return getWebElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean isElementDisplay(WebDriver driver, String locator, String... params) {
		locator=getDynamiLocator(locator, params);
		return getWebElement(driver, locator).isDisplayed();
	}
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}
	public boolean isElementSelected(WebDriver driver, String locator, String...params ) {
		locator=getDynamiLocator(locator, params);
		return getWebElement(driver, locator).isSelected();
	}
	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}
	public void switchToFrameOrIFrameByElement(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}
	public void switchToDefaultContent(WebDriver driver, String locator) {
		driver.switchTo().defaultContent();
	}
	public void doubleClickToElement(WebDriver driver, String locator) {
		action= new Actions(driver);
		action.doubleClick(getWebElement(driver, locator)).perform();
	}
	public void hoverMouseToElement(WebDriver driver, String locator) {
		action= new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}
	public void hoverMouseToElement(WebDriver driver, String locator, String...params) {
		locator=getDynamiLocator(locator, params);
		action= new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}
	public void rightCLickToElement(WebDriver driver, String locator) {
		action= new Actions(driver);
		action.contextClick(getWebElement(driver, locator)).perform();
	}
	public void dragAndDrop(WebDriver driver, String locatorSource, String locatorTarget) {
		action= new Actions(driver);
		WebElement source = getWebElement(driver, locatorSource);
		WebElement target = getWebElement(driver, locatorTarget);
		action.dragAndDrop(source, target).perform();
	}
	public void pressKeyboardToElemntByActions(WebDriver driver, String locator, Keys key) {
		action= new Actions(driver);
		action.sendKeys(getWebElement(driver, locator), key).perform();
	}
	public void pressKeyboardToElemnt(WebDriver driver, String locator, Keys key) {
		getWebElement(driver, locator).sendKeys(key);
	}
	public void pressKeyboardToElemnt(WebDriver driver, String locator, Keys key, String...params) {
		locator = getDynamiLocator(locator, params);
		getWebElement(driver, locator).sendKeys(key);
	}
	public Object executeForBrowser(WebDriver driver,String javaScript) {
		jsExecutor=(JavascriptExecutor)driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor=(JavascriptExecutor)driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean isExpectedTextInInnerText(WebDriver driver,String textExpected) {
		jsExecutor=(JavascriptExecutor)driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver,String url) {
		jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver,String locator) {
		jsExecutor=(JavascriptExecutor)driver;
		WebElement element = getWebElement(driver,locator);
		String originalStyle = element.getAttribute("style");
		
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver,String locator) {
		jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
	}
	public void clickToElementByJS(WebDriver driver,String locator, String...params) {
		locator= getDynamiLocator(locator, params);
		jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver,String locator) {
		jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver,locator));
	}

	public void sendkeyToElementByJS(WebDriver driver,String locator, String value) {
		jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver,locator));
	}

	public void removeAttributeInDOM(WebDriver driver,String locator, String attributeRemove) {
		jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver,locator));
	}

	public String getTextElementByJS(WebDriver driver,String locator,String ...params) {
		locator = getDynamiLocator(locator, params);
		jsExecutor=(JavascriptExecutor)driver;
		String script=" return $(document.evaluate(\"//input[@id='Address_FirstName']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()";
		//String s="$(document.evaluate("//input[@id='Address_FirstName']", document, nul"l, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()
		//String script="$(document.evaluate(""+locator+ ", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()";
		//System.out.println(s);
		return (String) jsExecutor.executeScript(script);
	}
	public String getElementValidationMessage(WebDriver driver,String locator) {
		jsExecutor=(JavascriptExecutor)driver;
		
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver,locator));
	}

	public boolean isImageLoaded(WebDriver driver,String locator) {
		jsExecutor=(JavascriptExecutor)driver;
		return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver,locator));
		
	}
	public boolean isJQueryLoadSuccess(WebDriver driver) {
		explicit=new WebDriverWait(driver,longTimeOut);
		jsExecutor=(JavascriptExecutor)driver;
		ExpectedCondition<Boolean> jQueryLoad= new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return (Boolean) jsExecutor.executeScript("return (window.jQuery!=null) && (jQuery.active===0);");
				
			}
			
		};
		return explicit.until(jQueryLoad);
	}
	public boolean isjQueryAndPageLoadSuccess(WebDriver driver) {
		explicit=new WebDriverWait(driver,longTimeOut);
		jsExecutor=(JavascriptExecutor)driver;
		ExpectedCondition<Boolean> jQueryLoad= new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				try {
					return  ((Long) jsExecutor.executeScript("return jQuery.active;")==0);
				}catch (Exception e) {
					return true;
				}
				
			}
			
		};
		ExpectedCondition<Boolean> PageLoadSuccess= new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return  (Boolean) jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
			
		};
		return explicit.until(jQueryLoad) && explicit.until(PageLoadSuccess);
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		explicit=new WebDriverWait(driver, longTimeOut);
		explicit.until(ExpectedConditions.visibilityOfElementLocated(getXPathLocator(locator)));
	}
	public void waitForElementVisible(WebDriver driver, String locator, String...params) {
		explicit=new WebDriverWait(driver, longTimeOut);
		explicit.until(ExpectedConditions.visibilityOfElementLocated(getXPathLocator(getDynamiLocator(locator, params))));
	}
	public void waitForAllElementsVisible(WebDriver driver, String locator) {
		explicit=new WebDriverWait(driver, longTimeOut);
		explicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getXPathLocator(locator)));
	}
	public void waitForAllElementsVisible(WebDriver driver, String locator, String...params) {
		explicit=new WebDriverWait(driver, longTimeOut);
		locator=getDynamiLocator(locator, params);
		explicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getXPathLocator(locator)));
	}
	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicit=new WebDriverWait(driver, longTimeOut);
		explicit.until(ExpectedConditions.invisibilityOfElementLocated(getXPathLocator(locator)));
		
	}
	public void waitForElementClickable(WebDriver driver, String locator) {
		explicit=new WebDriverWait(driver, longTimeOut);
		explicit.until(ExpectedConditions.elementToBeClickable(getXPathLocator(locator)));
		
	}
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		explicit=new WebDriverWait(driver, longTimeOut);
		explicit.until(ExpectedConditions.elementToBeClickable(element));
	
	}
	public void waitForAllElementsInvisible(WebDriver driver, String locator) {
		explicit=new WebDriverWait(driver, longTimeOut);
		explicit.until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, locator)));
	}

	public String getDynamiLocator(String locator, String... params) {
		return String.format(locator,(Object[]) params);
	}
	public void waitForElementClickable(WebDriver driver, String locator, String... params) {
		explicit=new WebDriverWait(driver, longTimeOut);
		explicit.until(ExpectedConditions.elementToBeClickable(getXPathLocator(getDynamiLocator(locator, params))));

	}
	public void clickToElement(WebDriver driver, String locator, String... params) {
		getWebElement(driver, getDynamiLocator(locator, params)).click();
	}

	//Nop-commerce
	public void clickToHeaderLinkByLabelName(WebDriver driver, String headerLinkText) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_LINK_HEADER_BY_LABEL_NAME, headerLinkText);
		clickToElement(driver, UserBasePageUI.DYNAMIC_LINK_HEADER_BY_LABEL_NAME, headerLinkText);
		
	}

	public void enterToTextboxByID(WebDriver driver, String idTextbox, String value) {
		waitForElementVisible(driver,UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, idTextbox);
		sendKeyToElement(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, idTextbox);
	}
	
	public void clickToButtonByLabelName(WebDriver driver, String buttonLabelName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_LABEL_NAME, buttonLabelName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_LABEL_NAME, buttonLabelName);
	}

	public String getErrorMessageByID(WebDriver driver, String idErrorMessage) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_ERROR_MESSAGE, idErrorMessage);
		return getTextElement(driver, UserBasePageUI.DYNAMIC_ERROR_MESSAGE, idErrorMessage);
		
	}
	
	public boolean isHeaderLinkDisplayByLabelNameDisplay(WebDriver driver, String headerLinkText) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_LINK_HEADER_BY_LABEL_NAME, headerLinkText);
		return isElementDisplay(driver, UserBasePageUI.DYNAMIC_LINK_HEADER_BY_LABEL_NAME, headerLinkText);
	}
	
	public String getDataInTextboxByID(WebDriver driver, String idTextbox) {
		waitForElementVisible(driver,UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID ,idTextbox);
		return getAttributeElement(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, "value", idTextbox);
	}
	
	public void clickToRadioButtonByLabelName(WebDriver driver, String radioButtonLabelName) {
		waitForElementClickable(driver,UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL_NAME , radioButtonLabelName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL_NAME , radioButtonLabelName);
		
	}
	
	public void selectItemInDropDownByName(WebDriver driver, String dropdownName, String value) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_DROPDROWN_BY_NAME, dropdownName);
		selectItemInDefaultDropdownByText(driver, UserBasePageUI.DYNAMIC_DROPDROWN_BY_NAME, value, dropdownName);
		
	}
	
	public boolean isRadioButtonSelectedByLabelName(WebDriver driver, String radioButtonLabelName) {
		waitForElementVisible(driver,UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL_NAME , radioButtonLabelName);
		return  isElementDisplay(driver,UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL_NAME , radioButtonLabelName);
		
	}
	
	public String getSelectedValueInDropdrownByName(WebDriver driver, String dropdownName) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_DROPDROWN_BY_NAME, dropdownName);
		return getSelectedItemInDefaultDropdown(driver, UserBasePageUI.DYNAMIC_DROPDROWN_BY_NAME, dropdownName);
		
	}
	public void clickToPageInLeftMenuByLabelName(WebDriver driver, String pageNameInLeftMenu) {
		waitForElementClickable(driver,UserBasePageUI.DYNAMIC_PAGE_IN_LEFT_MENU , pageNameInLeftMenu);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_IN_LEFT_MENU , pageNameInLeftMenu);
		
	}
	
	public void clickToFooterLinkByLabelName(WebDriver driver, String footerLinkByLabelName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_LINK_FOOTER_BY_LABEL_NAME, footerLinkByLabelName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_LINK_FOOTER_BY_LABEL_NAME, footerLinkByLabelName);
	}

	public void clickToMenuPageByLabelName(WebDriver driver, String menuPageLabelName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_MENU_PAGE_BY_LABEL_NAME, menuPageLabelName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_MENU_PAGE_BY_LABEL_NAME, menuPageLabelName);
	}
	
	public void enterToTextAreaboxByID(WebDriver driver, String idTextArea, String value) {
		waitForElementVisible(driver,UserBasePageUI.DYNAMIC_TEXTAREA_BY_ID, idTextArea);
		sendKeyToElement(driver, UserBasePageUI.DYNAMIC_TEXTAREA_BY_ID, value, idTextArea);
	}
	
	public void selectItemInDropDownByLabelName(WebDriver driver, String dropdownLabelName, String value) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_DROPDROWN_BY_LABEL_NAME, dropdownLabelName);
		selectItemInDefaultDropdownByText(driver, UserBasePageUI.DYNAMIC_DROPDROWN_BY_LABEL_NAME, value, dropdownLabelName);
		
	}
	
	public void openSubMenuByLabelName(WebDriver driver, String menuLabel, String subMenuLabel) {
		waitForElementClickable(driver,UserBasePageUI.DYNAMIC_MENU_PAGE_BY_LABEL_NAME ,menuLabel);
		hoverMouseToElement(driver,UserBasePageUI.DYNAMIC_MENU_PAGE_BY_LABEL_NAME,menuLabel);
		waitForElementClickable(driver,UserBasePageUI.DYNAMIC_SUB_MENU_PAGE_BY_LABEL_NAME ,subMenuLabel);
		clickToElement(driver, UserBasePageUI.DYNAMIC_SUB_MENU_PAGE_BY_LABEL_NAME ,subMenuLabel);
		
	}

}
