package payUIs;

public class MyAccountPageUI {
	public static String INFOR_ADDRESS_BY_CLASS_NAME="//div[@class='address-list']//ul[@class='info']//li[@class='%s']";
	public static String MESSAGE_SUCCESSFULL_AFTER_CHANGE_PW="//div[@class='bar-notification success']//p[@class='content']";
	public static String BUTTON_CLOSE_MESSAGE_SUCCESS="//div[@class='bar-notification success']//span[@class='close']";
	public static String ORDER_NUMBER="//div[@class='order-list']//strong";
	public static String BUTTON_DETAILS_AT_ORDER_NUMBER="//strong[text()='Order Number: %s']/parent::div/following-sibling::div[@class='buttons']/button[text()='Details']";
	public static String ORDER_DATE="//li[@class='order-date']";
	public static String ORDER_STATUS="//li[@class='order-status']";
	public static String ORDER_TOTAL="//li[@class='order-total']";
	public static String DYNAMIC_ROW_VALUE_BY_SKU_PRODUCTNAME_PRICE_TOTAL_IN_MY_ORDER="//td[@class='sku']//span[text()='%s']/parent::td/following-sibling::td[@class='product']//a[text()='%s']/parent::em/parent::td/following-sibling::td[@class='unit-price']//span[text()='%s']/parent::td/following-sibling::td[@class='total']//span[text()='%s']";
	public static String PAYMENT_STATUS="//li[@class='payment-method-status']//span[@class ='value']";
	public static String SHIPPING_STATUS="//li[@class='shipping-status']//span[@class ='value']";
}
