package dto;

import java.sql.Timestamp;

public class RemovedItem {
	private int id;
	private Item item;
	private User user;
	private int quantity;
	private Timestamp added;
	private Timestamp removed;
	
	public void setId(int id){
		this.id = id;
	}
	public void setItem(Item item){
		this.item = item;
	}
	public void setUser(User user){
		this.user = user;
	}
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	public void setAdded(Timestamp added){
		this.added = added;
	}
	public void setRemoved(Timestamp removed){
		this.removed = removed;
	}
	public int getId(){
		return this.id;
	}
	public Item getItem(){
		return this.item;
	}
	public User getUser(){
		return this.user;
	}
	public int getQuantity(){
		return this.quantity;
	}
	public Timestamp getAdded(){
		return this.added;
	}
	public Timestamp getRemoved(){
		return this.removed;
	}
	
}