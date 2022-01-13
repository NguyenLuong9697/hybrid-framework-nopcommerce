package payUIs;

public class ProductPageUI {
	public static final String DYNAMIC_PRODUCT_BY_NAME="//div[@class='item-box']//a[text()='%s']";
	//public static final String PRODUCT_NAME_TEXT="//div[@class='details']//a";	
	public static final String PRODUCT_NAME_TEXT="//h2[@class='product-title']/a";	
	public static final String PRODUCT_PRICE_TEXT="//div[@class='prices']/span";
	public static final String DYNAMIC_PAGGING_BY_NUMBER_ACTIVE="//li[@class='current-page']/span[text()='%s']";
	public static final String DYNAMIC_PAGGING_BY_NUMBER="//div[@class='pager']//a[text()='%s']";
	public static final String PREVIOUS_PAGE_ICON="//li[@class='previous-page']/a";
	public static final String NEXT_PAGE_ICON="//li[@class='next-page']/a";
	public static final String PAGGING_PAGE="//div[@class='pager']";
	public static final String DYNAMIC_BUTTON_BY_TITLE_THROUGH_PRODUCT_NAME="//h2[@class='product-title']/a[text()='%s']/parent::h2/following-sibling::div[@class='add-info']//button[@title='%s']";
	public static final String MESSAGE_SUCCESSFULL_AFTER_ADD_TO_COMPARE="//div[@class='bar-notification success']//p[@class='content']";
	public static final String LINK_PRODUCT_COMPARISON="//a[text()='product comparison']";
}
