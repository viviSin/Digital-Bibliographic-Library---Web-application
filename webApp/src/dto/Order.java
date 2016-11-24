package dto;

import java.util.ArrayList;
import java.sql.Timestamp;

//single type of item with quantity
public class Order {
	private int id;
	private int user_Id;
    private Timestamp mdate;
	private ArrayList<OrderItem> orderItems;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUer_Id() {
		return user_Id;
	}
	public void setUser_Id(int uer_Id) {
		this.user_Id = uer_Id;
	}
    public Timestamp getMdate() {
        return mdate;
    }
    public void setMdate(Timestamp mdate){
        this.mdate = mdate;
    }
	public ArrayList<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(ArrayList<OrderItem> orderItems){
		this.orderItems = orderItems;
	}
	
	/*
	public OrderItem getOrderItem(int sellerID, int itemID) {
		return null;
	}*/
	
	public void addOrderItem(OrderItem orderItem) {
		this.orderItems.add(orderItem);
	}
}