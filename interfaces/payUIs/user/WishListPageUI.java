package payUIs.user;

public class WishListPageUI {
	public static final String DYNAMIC_ROW_VALUE_BY_SKU_PRODUCTNAME_PRICE_TOTAL="//td[@class='sku']/span[text()='%s']/parent::td/following-sibling::td[@class='product']/a[text()='%s']/parent::td/following-sibling::td[@class='unit-price']/span[text()='%s']/parent::td/following-sibling::td[@class='subtotal']/span[text()='%s']";
	public static final String LINK_SHARE_WISHLIST="//div[@class='share-info']//a";
	public static final String TITLE_OF_WISHLIST="//div[@class='page-title']/h1";
	public static final String DYNAMIC_CHECKBOX_AT_ROW_CONTAIN_PRODUCT="//a[@class='product-name' and text()='%s']/parent::td/preceding-sibling::td[@class='add-to-cart']/input";
	public static final String DYNAMIC_ICON_DELETE_AT_ROW_CONTAIN_PRODUCT="//a[@class='product-name' and text()='%s']/parent::td/following-sibling::td[@class='remove-from-cart']/button";
	public static final String MESSAGE_NO_DATA_IN_WISHLIST="//div[@class='no-data' and contains(text(),'The wishlist is empty!')]";
}
