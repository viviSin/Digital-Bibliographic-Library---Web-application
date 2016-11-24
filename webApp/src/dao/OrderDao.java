package dao;


import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.Order;

//single type of item with quantity
public class OrderDao extends BaseDao {
	
	//get all orders from the buyer with id, "userid"
	public static ArrayList<Order> getOrderByUsers_id(int userid) {
		ArrayList<Order> orders = new ArrayList<Order>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;
		
		String query = "SELECT * from Orders where Users_id = ?";
		
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(query);
			preparedStatement.setInt(1, userid);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				orders.add(convertToOrder(rs));
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
		
		return orders;
	}
	
	//get's the order given the primary key which is the order id, "id"
	public static Order getOrderById(int id) {
		Order order = null;
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String query = "SELECT * from Orders where id = ?";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(query);
			preparedStatement.setInt(1, id);
	
			ResultSet rs = preparedStatement.executeQuery();
	
			if (rs.next()) {
				order = convertToOrder(rs);
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
		return order;
	}
	//save order
		public static int insert(int id, Timestamp date) {
			PreparedStatement preparedStatement = null;
			Connection dbConnection = null;
			int result=-1;
			String query = "insert into Orders(Users_id, mdate) values(?, ?);";
			try {
				dbConnection = getDBConnection();
				preparedStatement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, id);
				preparedStatement.setTimestamp(2, date);
		

				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					result =rs.getInt(1);
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
			return result;
		}
	
	//save order
	public static void save(Order order) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String query = "insert into Orders(id, Users_id) values(?, ?);";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, order.getId());
			preparedStatement.setInt(2, order.getUer_Id());

			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				order.setId(rs.getInt(1));
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
	}
	
	
	//Converts a given result set into an order
	private static Order convertToOrder(ResultSet rs) throws SQLException {		
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setUser_Id(rs.getInt("Users_id"));
		order.setMdate(rs.getTimestamp("mdate"));
		return order;
	}
	
	// assuming we don't need delete and update for orders once they are placed.
}