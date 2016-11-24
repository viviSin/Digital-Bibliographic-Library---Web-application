package dto;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;
public class CartItem {
	private int onSaleid;
	private Item item; 
	private Timestamp mdate; 
	private int quantity;
	private boolean ava;
	private User buyer;
	private int key;
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public CartItem() {
		super();
		
	}
	public int getOnSaleid() {
		return onSaleid;
	}
	public void setOnSaleid(int onSaleid) {
		this.onSaleid = onSaleid;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
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
	public boolean isAva() {
		return ava;
	}
	public void setAva(boolean ava) {
		this.ava = ava;
	}
	public User getBuyer() {
		return buyer;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
}
