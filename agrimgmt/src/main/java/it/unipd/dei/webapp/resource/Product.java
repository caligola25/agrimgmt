package it.unipd.dei.webapp.resource;

import java.util.UUID;

/**
 * Represents the data about the product.
 * @author --
 * @version 1.00
 * @since 1.00
 */
public class Product {
	
	private final UUID productId;
	private final String productName;
	private final float price;
	private final boolean available;
	
	/**
     * Creates a new Product
	 *
     * @param productId the UUID of the product
     * @param productName the name of the product
     * @param price the price of the product
	 * @param available the availability status of the product
	 *
     */
	public Product(final UUID productId, final String productName, final float price, final boolean available){
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.available = available;
	}

	/**
	 * @return the UUID of the product
	 */
	public UUID getProductId() {
		return productId;
	}

	/**
	 * @return the product name of the product
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @return the price of the product
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @return the availability status of the product
	 */
	public boolean isAvailable(){ return available; }

}