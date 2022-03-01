package payUIs.user;

public class UserBasePageUI {
	public static final String DYNAMIC_LINK_HEADER_BY_LABEL_NAME="//div[@class='header']//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_ID="//input[@id='%s']";
	public static final String DYNAMIC_BUTTON_BY_LABEL_NAME="//button[contains(text(),'%s')]";
	public static final String DYNAMIC_BUTTON_BY_ID="//button[@id='%s']";
	public static final String DYNAMIC_BUTTON_BY_ATTRIBUTE_NAME="//button[@name='%s']";
	public static final String DYNAMIC_ERROR_MESSAGE="//span[@id='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_LABEL_NAME="//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_DROPDROWN_BY_NAME="//select[@name='%s']";
	public static final String DYNAMIC_PAGE_IN_LEFT_MENU="//div[@class='listbox']//a[text()='%s']";
	public static final String DYNAMIC_LINK_FOOTER_BY_LABEL_NAME="//div[@class='footer']//a[text()='%s']";
	public static final String DYNAMIC_MENU_PAGE_BY_LABEL_NAME="//div[@class='header-menu']/ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
	public static final String DYNAMIC_SUB_MENU_PAGE_BY_LABEL_NAME="//div[@class='header-menu']/ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
	public static final String DYNAMIC_TEXTAREA_BY_ID="//textarea[@id='%s']";
	public static final String DYNAMIC_DROPDROWN_BY_LABEL_NAME="//label[text()='%s']/following-sibling::select";
	public static final String DYNAMIC_ROW_VALUE_BY_SKU_PRODUCTNAME_PRICE_TOTAL="//td[@class='sku']//span[text()='%s']/parent::td/following-sibling::td[@class='product']//a[text()='%s']/parent::td/following-sibling::td[@class='unit-price']//span[text()='%s']/parent::td/following-sibling::td[@class='subtotal']//span[text()='%s']";
	//public static final String INFORMATION_AT_BILLING_ADRRESS="//div[@class='%s']//li[@class='%s' and contains(text(),'%s')]";
	public static final String INFORMATION_AT_BILLING_ADRRESS="//div[@class='%s']//li[@class='%s' and contains(string(),'%s')]";
	public static final String PAYMENT_OR_SHIPPING_METHOD="//li[@class='%s']/span[@class='value' and contains(text(),'%s')]";
	public static final String DYNAMIC_CHECKBOX_BY_ID="//input[@id='%s']";
	public static final String SHIPPING_METHOD="//label[text()='Shipping:']/following-sibling::span[ text()='%s']";
	public static final String INFORMATION_AT_BILL_BY_LABEL_NAME="//label[ text()='%s']/parent::td/following-sibling::td/span";
	public static final String GIFT_WRAPPING="//div[@class='%s']/div[contains(text(),'Gift wrapping: No')] ";
	public static final String DYNAMIC_RADIO_BUTTON_BY_ID ="//input[@id='%s']";
}

