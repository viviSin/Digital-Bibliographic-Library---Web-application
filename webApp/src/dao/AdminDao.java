package dao;

//Anthony, you can edit this class.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Admin;
import dto.Order;
import dto.OrderItem;
import dto.RemovedItem;
import dto.ShoppingCartItem;

public class AdminDao extends BaseDao{
	public static Admin getAdminByUsername(String username){
		Admin admin = null;
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Admins where username = ?";
		try {
			dbConnection = getDBConnection(); 
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				admin = convertToAdmin(rs);
			}
			else {
				admin = new Admin();
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return admin;
	}
	
	public static Admin getAdminById(int id){
		Admin admin = null;
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Admins where id = ?";
		try {
			dbConnection = getDBConnection(); 
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				admin = convertToAdmin(rs);
			}
			else {
				admin = new Admin();
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return admin;
	}
	
	private static Admin convertToAdmin(ResultSet rs) throws SQLException {
		Admin admin = new Admin();
		admin.setId(rs.getInt("id"));
		admin.setUsername(rs.getString("username"));
		admin.setPassword(rs.getString("password"));
		admin.setEmail(rs.getString("email"));
		return admin;
	}
	
	public static void removeItemById(int id){
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String deleteSQL = "DELETE from OrderItems WHERE id = ?";
		try {
			dbConnection = getDBConnection(); 
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();
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
		
	public static void banUserById(int id){
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String updateSQL = "UPDATE Users set isBanned=0 WHERE id=";
		
		try {
			dbConnection = getDBConnection(); 
			preparedStatement = dbConnection.prepareStatement(updateSQL);
			updateSQL = updateSQL + Integer.toString(id);

			// execute select SQL statement
			preparedStatement.executeUpdate(updateSQL);
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
	
	public static void unbanUserById(int id){
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String updateSQL = "UPDATE Users set isBanned=1 WHERE id=";
		try {
			dbConnection = getDBConnection(); 
			preparedStatement = dbConnection.prepareStatement(updateSQL);
			updateSQL = updateSQL + Integer.toString(id);

			// execute select SQL statement
			preparedStatement.executeUpdate(updateSQL);
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
	
	public static List<ShoppingCartItem> getUserCartById(int id){
		ArrayList<ShoppingCartItem> userCart = new ArrayList<ShoppingCartItem>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from ShoppingCartItems WHERE Users_id = ? ";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				ShoppingCartItem item = new ShoppingCartItem();
				item.setItem(ItemDao.getItemById(rs.getInt("Items_id")));
				item.setQuantity(rs.getInt("quantity"));
				item.setUser(UserDao.getUserById(rs.getInt("Users_id")));
				item.setMdate(rs.getTimestamp("mdate"));
				item.setOnSaleItem_id(rs.getInt("OnSaleItems_id"));
				userCart.add(item);
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
		return userCart;
	}
	
	public static List<RemovedItem> getRemovedHistoryByUserId(int id){
		ArrayList<RemovedItem> removeHistory = new ArrayList<RemovedItem>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from RemovedSCItems WHERE Users_id = ? ";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				RemovedItem removedItem = new RemovedItem();
				removedItem.setId(rs.getInt("id"));
				removedItem.setItem(ItemDao.getItemById(rs.getInt("Items_id")));
				removedItem.setUser(UserDao.getUserById(rs.getInt("Users_id")));
				removedItem.setQuantity(rs.getInt("quantitiy"));
				removedItem.setAdded(rs.getTimestamp("adate"));
				removedItem.setRemoved(rs.getTimestamp("rdate"));
				removeHistory.add(removedItem);
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
		return removeHistory;
	}
	
	public static List<Order> getBuyHistoryByUserId(int id){
		ArrayList<Order> orderHistory = new ArrayList<Order>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Orders WHERE Users_id = ?";
		try {
			dbConnection = getDBConnection();
			//selectSQL = selectSQL + id;
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Order order = new Order();
				ArrayList<OrderItem> orderItems = OrderItemDao.getOrderItemsById(rs.getInt("id"));
				order.setId(rs.getInt("id"));
				order.setUser_Id(rs.getInt("Users_id"));
				order.setMdate(rs.getTimestamp("mdate"));
				order.setOrderItems(orderItems);
				orderHistory.add(order);
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
		return orderHistory;
	}
}