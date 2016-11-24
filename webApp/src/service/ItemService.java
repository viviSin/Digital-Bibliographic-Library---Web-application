package service;

import java.util.ArrayList;
import java.util.List;

import dao.AuthorDao;
import dao.ItemDao;
import dto.Author;
import dto.Item;
import dto.OnSaleItem;
import dto.User;

public class ItemService {

	public static Item getItemsById(String itemId) {
		return ItemDao.getItemById(Integer.parseInt(itemId));
	}

	public static ArrayList<Item> getItemByTitle(String title){
		return ItemDao.getItemByTitle(title);
	}
	
	public static boolean userListingItem(User user, Item item){
		ArrayList<OnSaleItem> listings = item.getOnSaleItems();
		for (OnSaleItem listing : listings){
			if(listing.getSeller().getId() == user.getId()){
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean itemMatchesString(Item item, String searchString){
		boolean matched = false;
		searchString = searchString.toLowerCase();
		String searchPattern = "(.*)"+searchString+"(.*)";
		
		for (Author author : item.getAuthors()){
			if(author.getName().toLowerCase().matches(searchPattern)){
				matched = true;
				break;
			}
		}
		
		if (item.getPubltype().toLowerCase().matches(searchPattern)){
			matched = true;
		}
		
		else if (Integer.toString(item.getPublyear()).equals(searchString)){
			matched = true;
		}
		
		else if (item.getTitle().toLowerCase().matches(searchPattern)){
			matched = true;
		}
		
		else if (item.getVenues().toLowerCase().matches(searchPattern)){
			matched = true;
		}
		
		return matched;
	}
	
	public static List<Item> getItemSearchResults (String searchString){
		List<Item> results = new ArrayList<Item>();
		List<Item> allItems = ItemDao.getAllItem();
		boolean matched = false;
		searchString = searchString.toLowerCase();
		String searchPattern = "(.*)"+searchString+"(.*)";
		
		for (Item item : allItems){
			matched = false;
			for (Author author : item.getAuthors()){
				if(author.getName().toLowerCase().matches(searchPattern)){
					matched = true;
					break;
				}
			}
			
			if (item.getPubltype().toLowerCase().matches(searchPattern)){
				matched = true;
			}
			
			else if (Integer.toString(item.getPublyear()).equals(searchString)){
				matched = true;
			}
			
			else if (item.getTitle().toLowerCase().matches(searchPattern)){
				matched = true;
			}
			
			else if (item.getVenues().toLowerCase().matches(searchPattern)){
				matched = true;
			}
			
			if (matched == true){
				results.add(item);
			}
		}
		
		return results;
	}
	
	public static void save(Item item){
		ItemDao.save(item);
	}
	
	public static Item getItemByTitleAndAuthor(String title, String authorName){
		List<Item> matchedTitle = ItemDao.getItemByTitle(title);
		for (Item item : matchedTitle){
			for (Author author : item.getAuthors()){
				if (author.getName().equals(authorName)){
					return item;
				}
			}
		}
		return null;
	}
}
