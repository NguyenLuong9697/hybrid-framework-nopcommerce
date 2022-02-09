package payUIs;

public class ShoppingCartPageUI {
	
	public static final String DYNAMIC_QUANTITY_BY_PRODUCT_NAME="//td[@class='product']//a[text()='%s']/parent::td/following-sibling::td[@class='quantity']/input";
	public static final String DYNAMIC_TOTAL_BY_PRODUCT_NAME="//td[@class='product']//a[text()='%s']/parent::td/following-sibling::td[@class='subtotal']/span";
	public static final String DYNAMIC_BUTTON_REMOVE_BY_PRODUCT_NAME="//a[@class = 'product-name' and text()='%s']/parent::td/following-sibling::td[@class='remove-from-cart']/button";
	public static final String LABEL_NO_DATA_AFTER_REMOVE_PRODUCT_FROM_CART="//div[@class='no-data']";
	public static final String DYNAMIC_PRODUCT_BY_NAME="//a[@class='product-name' and text()='%s']";
	public static final String DYNAMIC_SPECIFICITY_PRODUCT_BY_NAME= "//a[text()='%s']/parent::td//div[@class='attributes']";
	public static final String DYNAMIC_TEXTBOX_BY_PRODUCT_NAME_AND_ARIA_LABEL="//a[text()='%s']/parent::td/following-sibling::td//input[@aria-label='%s']";
	public static final String DYNAMIC_LINK_EDIT_AT_PRODUCT_NAME="//a[text()='%s']/parent::td//div[@class='edit-item']/a[text()='Edit']";
	public static final String CHECKBOX_AGREE_WITH_TERMS="//input[@id='termsofservice']";
	
}
