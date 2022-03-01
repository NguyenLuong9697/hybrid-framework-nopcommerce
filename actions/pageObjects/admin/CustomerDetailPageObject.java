package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.admin.CustomerDetailPageUI;

public class CustomerDetailPageObject  extends BasePage{
	private WebDriver driver;
	public CustomerDetailPageObject(WebDriver driver) {
		this.driver=driver;
	}
	public static ProductDetailPageObject getProductDetailPageObject(WebDriver driver) {
	
		return new ProductDetailPageObject(driver);
	}
	public String getRowValueByColumn(String rowIndex,String columnName) {
		int columnIndex= getElementSize(driver, CustomerDetailPageUI.COLUMN_INDEX_BY_NAME, columnName)+1;
		waitForElementVisible(driver, CustomerDetailPageUI.CELL_BY_INDEX_ROW_AND_COLUMN, rowIndex, String.valueOf(columnIndex));
		return getTextElement(driver,  CustomerDetailPageUI.CELL_BY_INDEX_ROW_AND_COLUMN, rowIndex, String.valueOf(columnIndex));
	}
	public EditAddressCustomerPageObject clickIconEditAtEmailAddress(String addressEmail) {
		waitForElementClickable(driver,CustomerDetailPageUI.ICON_EDIT_AT_EMAIL_ADDRESS, addressEmail);
		clickToElement(driver, CustomerDetailPageUI.ICON_EDIT_AT_EMAIL_ADDRESS, addressEmail);
		return PageGeneratorManager.getEditAddressCustomerPageObject(driver);
	}
	public void clickIconDeletAtEmailAddress(String addressEmail) {
		waitForElementClickable(driver,CustomerDetailPageUI.ICON_DELETE_AT_EMAIL_ADDRESS, addressEmail);
		clickToElement(driver, CustomerDetailPageUI.ICON_DELETE_AT_EMAIL_ADDRESS, addressEmail);
	}

	public boolean isMessageNoDataInPanelAddressDisplay() {
		waitForElementVisible(driver, CustomerDetailPageUI.MESSAGE_NO_ADDRESS);
		return isElementDisplay(driver, CustomerDetailPageUI.MESSAGE_NO_ADDRESS);
	}
	

}
