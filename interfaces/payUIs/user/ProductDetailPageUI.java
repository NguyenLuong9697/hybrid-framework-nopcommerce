package payUIs.user;

public class ProductDetailPageUI {
	public static final String ADD_YOUR_REVIEW_LINK="//a[text()='Add your review']";
	public static final String DYNAMIC_BUTTON_BY_LABEL_NAME_THROUGH_PRODUCT_NAME="//h1[text()='%s']/parent::div/following-sibling::div[@class='overview-buttons']//button[text()='%s']";
	public static final String MESSAGE_SUCCESSFULL_AFTER_ADD_WISH_LIST="//div[@class='bar-notification success']//p[@class='content']";
	public static final String BUTTON_CLOSE_MESSAGE_SUCCESS="//div[@class='bar-notification success']//span[@class='close']";
	public static final String DROPDOWN_BY_SPECIFICITY_PRODUCT="//label[contains(text(),'%s')]/parent::dt/following-sibling::dd[1]/select";
	public static final String DYNAMIC_RADIO_BUTTON_BY_SPECIFICITY_LABEL="//label[contains(text(),'%s')]/parent::dt/following-sibling::dd//label[text()='%s']/preceding-sibling::input";
}
