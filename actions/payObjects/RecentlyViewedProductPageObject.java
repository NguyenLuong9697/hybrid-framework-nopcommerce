package payObjects;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import payUIs.CompareProductPageUI;
import payUIs.RecentlyViewedProductPageUI;
import payUIs.WishListPageUI;

public class RecentlyViewedProductPageObject extends BasePage{
	private WebDriver driver;
	public RecentlyViewedProductPageObject(WebDriver driver) {
		this.driver=driver;
	}
	public boolean isProductDisplayByName(String nameProductRecentlyViewed) {
		waitForElementVisible(driver, RecentlyViewedProductPageUI.DYNAMIC_PRODUCT_BY_NAME, nameProductRecentlyViewed);
		return isElementDisplay(driver, RecentlyViewedProductPageUI.DYNAMIC_PRODUCT_BY_NAME, nameProductRecentlyViewed);
	}
	public int getNumberProductRecentlyViewed() {
		waitForAllElementsVisible(driver, RecentlyViewedProductPageUI.PRODUCT_IN_RECENTLY_VIEWED);
		return getElementSize(driver, RecentlyViewedProductPageUI.PRODUCT_IN_RECENTLY_VIEWED);
			
	}
	public boolean isProductNotDisplayByName(String nameProductRecentlyViewed) {
		waitForElementInvisible(driver, RecentlyViewedProductPageUI.DYNAMIC_PRODUCT_BY_NAME, nameProductRecentlyViewed);
		return isElementUndisplayed(driver, RecentlyViewedProductPageUI.DYNAMIC_PRODUCT_BY_NAME, nameProductRecentlyViewed);
	
	}
	

}
