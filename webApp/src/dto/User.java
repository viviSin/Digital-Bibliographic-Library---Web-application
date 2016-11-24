package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private int yearOfBirth;
	private String address;
	private String creditcard;
	private String hash;
	private int isVerified;
	private int isBanned;
	private ArrayList<ShoppingCartItem> shoppingCartItems;
	private ArrayList<OnSaleItem> onSaleItems;

	public User() {
		this.hash = UUID.randomUUID().toString();
		shoppingCartItems = new ArrayList<ShoppingCartItem>();
		onSaleItems = new ArrayList<OnSaleItem>();
		this.isVerified = 1;
		this.isBanned = 1;
	}
	
	public  ArrayList<OnSaleItem> getOnSaleItems() {
		return onSaleItems;
	}
	
	public void addOnSaleItems(List<OnSaleItem> items){
		onSaleItems.addAll(items);
	}
	
	public  ArrayList<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}
	
	public void addShoppingCartItems(List<ShoppingCartItem> items){
		shoppingCartItems.addAll(items);
		
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}

	public boolean isVerified() {
		return this.isVerified == 0;
	}
	
	public boolean isBanned() {
		return this.isBanned == 0;
	}
	
	public int getIsBanned() {
		return isBanned;
	}

	public void setIsBanned(int isBanned) {
		this.isBanned = isBanned;
	}

	public void ban() {
		 isBanned = 0;
	}

	public void unBan() {
		isBanned = 1;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

}
