package service;

import java.util.ArrayList;

import dao.OnSaleItemDao;
import dto.OnSaleItem;

public class OnSaleItemService {
	
	public static ArrayList<OnSaleItem> getOnSaleItemByUserId(int id) {
		return OnSaleItemDao.getOnSaleItemByUserId(id);
	}
	
	public static void deleteById(int id) {
		OnSaleItemDao.delete(OnSaleItemDao.getOnSaleItemById(id));
	}
	
	public static void pauseById(int id){
		OnSaleItemDao.pauseById(id);
	}
	
	public static void unpauseById(int id){
		OnSaleItemDao.unpauseById(id);
	}
	
	public static void save(OnSaleItem oitem){
		OnSaleItemDao.save(oitem);
	}
	
	public static void update(OnSaleItem oitem){
		OnSaleItemDao.update(oitem);
	}
	
	public static OnSaleItem getOnSaleItemById(int id) {
		return OnSaleItemDao.getOnSaleItemById(id);
	}
}
