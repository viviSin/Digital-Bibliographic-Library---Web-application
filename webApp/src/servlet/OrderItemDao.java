package servlet;

import java.util.ArrayList;

import dao.BaseDao;
import dao.ItemDao;
import dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dto.OrderItem;

public class OrderItemDao extends BaseDao{

	//get all items
	public ArrayList<OrderItem> getAllOrderItems() {
		ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;
		
		String query = "SELECT * from OrderItems";
		
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(query);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				orderItems.add(convertToOrderItem(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
		
		return orderItems;
	}

	//get all the orderItems for a buyer given their userid
	public static ArrayList<OrderItem> getUserOrderItems(int userid) {
		ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;
		
		String query = "SELECT * from OrderItems where Users_id = ?";
		
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(query);
			preparedStatement.setInt(1, userid);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				orderItems.add(convertToOrderItem(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
		
		return orderItems;
	}
	
	//get all the orderItems given orderid
	/**
	 * the id here is the order id, can be more than 1 result.
	 * @param orderid
	 * @return
	 */
	public static ArrayList<OrderItem> getOrderItemsById(int orderid) {
		ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;
		
		String query = "SELECT * from OrderItems where Orders_id = ?";
		
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(query);
			preparedStatement.setInt(1, orderid);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				orderItems.add(convertToOrderItem(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
		
		return orderItems;
	}
	
	
	//save
	public static void save(OrderItem orderItem) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String query = "insert into OrderItems(quantity, Items_id, Orders_id, Seller_id, Users_id, price) values(?, ?, ?, ?, ?, ?);";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, orderItem.getQuantity());
			preparedStatement.setInt(2, orderItem.getItem().getId());
			preparedStatement.setInt(3,  orderItem.getOrders_id());
			preparedStatement.setInt(4,  orderItem.getSeller().getId());
			preparedStatement.setInt(5,  orderItem.getUser().getId());
			preparedStatement.setInt(6,  orderItem.getPrice());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	//Converts a given result statement to an OrderItem
	private static OrderItem convertToOrderItem(ResultSet rs) throws SQLException {
		OrderItem orderItem = new OrderItem();
		orderItem.setUser(UserDao.getUserById(rs.getInt("Users_id")));
		orderItem.setSeller(UserDao.getUserById(rs.getInt("Seller_id")));
		orderItem.setItem(ItemDao.getItemById(rs.getInt("Items_id")));
		orderItem.setOrders_id(rs.getInt("Orders_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		orderItem.setPrice(rs.getInt("price"));
		return orderItem;
	}
	
	// Assuming that we've created an instance of the order items (when viewing the order) 
	// we have enough information to display singular order items.
	
	
}