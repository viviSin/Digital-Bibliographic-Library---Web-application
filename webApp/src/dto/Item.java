package dto;

import java.util.List;

import dao.AuthorDao;
import dao.ItemDao;
import dao.OnSaleItemDao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
//done
public class Item {
	private int id; // primary key
	private String publtype;
	private String venues;
	private String title;
	private int publyear;
	private ArrayList<OnSaleItem> onSaleItems;//list of OnsaleItems where item == this
	private ArrayList<Author> authors;
	
	public void addOnSaleItem(OnSaleItem o) {
		onSaleItems.add(o);
	}
	
	public void removeOnSaleItem(OnSaleItem o) {
		onSaleItems.remove(o);
	}
	
	public ArrayList<OnSaleItem> getOnSaleItems() {
		if (this.onSaleItems == null) {
			this.onSaleItems = OnSaleItemDao.getOnSaleItemByItemId(id);
		}
		return onSaleItems;
	}
	
	public void setOnSaleItems(ArrayList<OnSaleItem> onSaleItems) {
		this.onSaleItems = onSaleItems;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPubltype() {
		return publtype;
	}

	public void setPubltype(String pultype) {
		this.publtype = pultype;
	}

	public String getVenues() {
		return venues;
	}

	public void setVenues(String venues) {
		this.venues = venues;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPublyear() {
		return publyear;
	}

	public void setPublyear(int pulyear) {
		this.publyear = pulyear;
	}

	public ArrayList<Author> getAuthors() {
		if (this.authors == null ) {
			this.authors = AuthorDao.getAuthorsByItemId(id);
		}
		return authors;
	}

	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}

}
