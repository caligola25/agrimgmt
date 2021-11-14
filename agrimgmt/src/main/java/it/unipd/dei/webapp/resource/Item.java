package it.unipd.dei.webapp.resource;

import java.util.UUID;

/**
 * Represents the data about the item.
 * @author --
 * @version 1.00
 * @since 1.00
 */
public class Item {
	
	private final UUID itemId;
	private final String itemStatus;
	private final UUID productOrderId;
	private final UUID productId;
	
	/**
     * Creates a new Item
     * @param item_id the UUID of the item
     * @param item_status the status of the item
     * @param product_order_id the UUID of the production order associated
	 * @param product_id the UUID of the product associated
     */
	public Item(final UUID item_id, String item_status, final UUID product_order_id, final UUID product_id){
		this.itemId = item_id;
		this.itemStatus = item_status;
		this.productOrderId = product_order_id;
		this.productId = product_id;
	}

	/**
	 * @return the item UUID
	 */
	public UUID getItemId() { return itemId; }

	/**
	 * @return the item status
	 */
	public String getItemStatus() { return itemStatus; }

	/**
	 * @return the production order UUID associated to this item
	 */
	public UUID getProductOrderId() { return productOrderId; }

	/**
	 * @return the product UUID associated to this item
	 */
	public UUID getProductId(){	return productId; }

}