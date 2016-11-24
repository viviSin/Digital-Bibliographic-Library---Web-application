package service;

import java.util.List;

import dao.AdminDao;
import dto.Admin;
import dto.Order;
import dto.RemovedItem;
//main functions on users,logical layer, uses the dao class.
public class AdminService {
	
	public static Admin getAdminByUsername(String username){
		return AdminDao.getAdminByUsername(username);
	}
	
	public static void banUserById(int id) {
		AdminDao.banUserById(id);
	}
	
	public static void unbanUserById(int id) {
		AdminDao.unbanUserById(id);
	}
	
	public static List<RemovedItem> getRemovedHistoryByUserId(int id) {
		return AdminDao.getRemovedHistoryByUserId(id);
	}
	
	public static List<Order> getBuyHistoryByUserId(int id) {
		return AdminDao.getBuyHistoryByUserId(id);
	}
}
