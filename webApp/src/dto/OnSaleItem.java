package dto;
//done
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;

public class OnSaleItem {
	private int id;
	private Item item; // primary key
	private float price;
	private Timestamp mdate; //last modified date by seller,format is 	//2011-11-12 00:00:00
	private int quantity;// number of stock left
	private int isPaused;
	private User seller;// id of seller
	
	
	public int getId() {
		return id;
	}

	/**
	 * be very careful with this, only used by Dao convert method.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * for normal creation of instance. timestamp,isPaused is auto-setted.
	 * @param item
	 * @param price
	 * @param quantity
	 * @param isPaused
	 * @param seller
	 */
	public OnSaleItem(Item item, float price, int quantity, User seller){
		this.item = item;
		this.price = price;
		this.quantity = quantity;
		this.seller = seller;
		
		this.isPaused = 1;
		Date date = new Date();
		mdate = (new Timestamp(date.getTime()));
		
	}
	
	/**
	 * for dao convert,please use setters.
	 */
	public OnSaleItem(){
		
	}
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Timestamp getMdate() {
		return mdate;
	}
	public void setMdate(Timestamp mdate) {
		this.mdate = mdate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * debug function
	 * if you want to know if this is paused, use isPaused().
	 * @return
	 */
	public int getIsPaused() {
		return isPaused;
	}
	
	/**
	 * debug function
	 * if you want to pause  use pause().
	 * @return
	 */
	public void setIsPaused(int isPaused) {
		this.isPaused = isPaused;
	}
	
	public User getSeller() {
		return seller;
	}
	public void setSeller(User seller) {
		this.seller = seller;
	}
	public boolean isPaused() {
		if(isPaused == 0){
			return true;
		}
		return false;
	}

	public void pause() {
		isPaused = 0;
	}
	
	public void unPause() {
		isPaused = 1;
	}

}
