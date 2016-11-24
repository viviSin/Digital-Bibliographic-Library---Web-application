package service;

import dao.OrderItemDao;
import dto.OrderItem;

public class OrderItemService {
	public static void save(OrderItem orderItem){
		OrderItemDao.save(orderItem);
	}
}
