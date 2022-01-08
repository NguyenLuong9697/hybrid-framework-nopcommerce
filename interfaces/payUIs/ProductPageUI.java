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
}
