package service;

import java.sql.Timestamp;

import dao.OrderDao;

public class OrderService {
	public static int insert(int id, Timestamp date){
		return OrderDao.insert(id, date);
	}
	
}
