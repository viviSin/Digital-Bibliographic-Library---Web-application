package service;

import java.util.ArrayList;
import java.util.List;

import dao.AuthorDao;
import dao.ItemDao;
import dto.Author;
import dto.Item;
import dto.User;

public class AuthorService {

	// public static Item getItemsById(String itemId){
	// return ItemDao.getItemById(Integer.parseInt(itemId));
	// }

	public static List<Author> getAuthorByNames(String authorStr) {
		String authorNames[] = authorStr.split(",");
		List<Author> resultList = new ArrayList<Author>();
		for (String authorName : authorNames) {
			Author author = AuthorDao.getAuthorByName(authorName);
			if (author == null) {
				author = new Author();
				author.setName(authorName);
				AuthorDao.saveOrUpdate(author);
			}
			resultList.add(author);
		}
		return resultList;
	}
	
	public static boolean authorExist(String author){
		List<Author> allAuthors = AuthorDao.getAllAuthor();
		for (Author curAuthor : allAuthors){
			if(curAuthor.getName().equals(author)){
				return true;
			}
		}
		
		return false;
	}
	
	public static Author getAuthorByName(String name) {
		return AuthorDao.getAuthorByName(name);
	}
	
	public static void save(Author author){
		AuthorDao.save(author);
	}

	public static void update(Author author){
		AuthorDao.update(author);
	}
}
