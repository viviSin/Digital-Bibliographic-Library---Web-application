package dto;

import java.sql.Timestamp;

//done
public class ShoppingCartItem {
	private Item item;//the publication
	private int quantity;
	private User user;
	private Timestamp mdate;
	private int onSaleItem_id;//the copy of publication which is onsale, since it can be unavailable, we just keep the id.
	private String key;
	private boolean ava;
	
	public ShoppingCartItem() {
		
	}

	public boolean isAva() {
		return ava;
	}

	public void setAva(boolean ava) {
		this.ava = ava;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getOnSaleItem_id() {
		return onSaleItem_id;
	}

	public void setOnSaleItem_id(int onSaleItem_id) {
		this.onSaleItem_id = onSaleItem_id;
	}
	
	public Timestamp getMdate() {
		return mdate;
	}

	public void setMdate(Timestamp mdate) {
		this.mdate = mdate;
	}

	public boolean isSameOnsaleItem(Item item){
		return (item.getId() == this.item.getId());
	}
	
	/**
	 * does not check quantity value. use a negative number if minus.
	 * @param q
	 */
	public void addQuantity(int q){
		quantity += q; 
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	/**
	 * does not check quantity value
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
