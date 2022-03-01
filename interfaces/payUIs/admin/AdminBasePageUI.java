package payUIs.admin;

public class AdminBasePageUI {
	public static final String MENU_LINK_BY_NAME="//ul[@role='menu']/li/a/p[contains(text(),'%s')]";
	public static final String SUB_MENU_LINK_BY_NAME="//ul[@style]/li/a/p[contains(text(),'%s')]";
	public static final String DYNAMIC_ROW_VALUE_BY_PRODUCTNAME_SKU_IN_PAGE_ADMIN="//td[text()='%s']/following-sibling::td[text()='%s']";
	public static final String MESSAGE_IN_TABLE_BY_ID_TABLE="//table[@id='%s']//td[@class='dataTables_empty' and text()='%s']";
	public static final String DYNAMIC_HEADER_LINK_BY_TEXT="//div[contains(@class,'content-header')]//a[contains(string(),'%s')]";
	public static final String TOOGLE_ICON_BY_CARD_NAME ="//div[@class='card-title' and contains(string(),'%s')]/following-sibling::div//i";
	public static final String DYNAMIC_SUCCESS_MESSAGE = "//div[contains(@class,'alert-success') and contains( string(),'%s')]";
	public static final String ICON_DELTE_BY_NAME_CUSTOMER_ROLE="//span[text()='%s']/parent::li//span[@title='delete']/span";
	public static final String LISTBOX_BY_TEXT="//label[text()='%s']/parent::div/parent::div/following-sibling::div//div[@role='listbox']/parent::div";
	public static final String DYNAMIC_CUSTOMER_ROLE_BY_TEXT="//ul[@id='SelectedCustomerRoleIds_listbox']//li[text()='%s']";
	public static final String SELECT_VALUE_IN_CUSTOMER_ROLE = "//ul[@id='SelectedCustomerRoleIds_taglist']//span[text()='%s']";
	public static final String INFORMATION_CUSTOMER_BY_EMAIL_FULLNAME_ROLE_COMPANY_ACTIVE="//tbody//td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td//i[@class='fas fa-check true-icon']/parent::td/following-sibling::td//a[contains(string(),'Edit')]";
	public static final String DYNAMIC_LINK_BY_TEXT="//a[text()='%s']";
	
}
