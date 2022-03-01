package payUIs.admin;

public class CustomerDetailPageUI {
	public static final String COLUMN_INDEX_BY_NAME="//th[text()='%s']/preceding-sibling::th";
	public static final String CELL_BY_INDEX_ROW_AND_COLUMN="//table[@id='customer-addresses-grid']//tr[%s]//td[%s]";
	public static final String ICON_EDIT_AT_EMAIL_ADDRESS="//table[@id='customer-addresses-grid']//td[text()='%s']/following-sibling::td//a[contains(text(),'Edit')]";
	public static final String ICON_DELETE_AT_EMAIL_ADDRESS="//table[@id='customer-addresses-grid']//td[text()='%s']/following-sibling::td//a[contains(text(),'Delete')]";
	public static final String MESSAGE_NO_ADDRESS="//table[@id='customer-addresses-grid']//td[text()='No data available in table']";
}
