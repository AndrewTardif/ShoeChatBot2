package core.app;

/**
 * An immutable shoe object that contains information such as
 * the display name, imageURL, review rating, review count, product id, url, price and sale price.
 * Subject to change in the future.
 * 
 * @author Raghav Jhavar
 */
public class Shoe {
	
	private final String displayName, imageURL, productid, url;
	private final int price, salePrice;
	public Shoe(
			String displayName, 
			String imageUrl, 
			String productId, 
			String productUrl,
			int price,
			int salePrice) {
		this.displayName = displayName;
		this.imageURL = imageUrl;
		this.productid = productId;
		this.url = "https://www.adidas.ca/en" + productUrl;
		this.price = price;
		this.salePrice = salePrice;
	}
	public String getDisplayName() {
		return displayName;
	}
	public String getImageUrl() {
		return imageURL;
	}
	public String getProductId() {
		return productid;
	}
	public String getProductUrl() {
		return url;
	}
	public int getPrice() {
		return price;
	}
	public int getSalePrice() {
		return salePrice;
	}
	@Override
	public String toString() {
		return "Shoe [displayName=" + displayName + ", imageURL=" + imageURL + ", productid=" + productid + ", url="
				+ url + ", price=" + price + ", salePrice=" + salePrice + "]";
	}
}
