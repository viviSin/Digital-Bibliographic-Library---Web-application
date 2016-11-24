package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.Item;
import dto.ShoppingCartItem;
import dto.User;

public class ShoppingCartItemDao extends BaseDao{
	public static List<ShoppingCartItem> getAllShoppingCartItem() {

		List<ShoppingCartItem> resultList = new ArrayList<ShoppingCartItem>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from ShoppingCartItems";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToShoppingCartItem(rs));
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
		return resultList;
	}
	public static void delItemByKey(String key) {
		if (key ==null)
			return;
//		List<ShoppingCartItem> resultList = new ArrayList<ShoppingCartItem>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;
		System.out.println("key: " +key);
		String deleteSQL = "DELETE from ShoppingCartItems WHERE id=";
		try {
			dbConnection = getDBConnection(); 
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			deleteSQL = deleteSQL + key;
//			preparedStatement.setInt(1, Integer.parseInt(key));
			// execute select SQL statement
			System.out.println("deleteSQL: " +deleteSQL.toString());
			 preparedStatement.executeUpdate(deleteSQL);

//			while (rs.next()) {
//				result = (convertToShoppingCartItem(rs));
//			}
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
	private static ShoppingCartItem convertToShoppingCartItem(ResultSet rs) throws SQLException {

		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();

		Item item = ItemDao.getItemById(rs.getInt("Items_id"));
		shoppingCartItem.setItem(item);
		shoppingCartItem.setKey(rs.getString("id"));
//		System.out.println("key : " +rs.getInt("key"));
		shoppingCartItem.setMdate(rs.getTimestamp("mdate"));
		shoppingCartItem.setOnSaleItem_id(rs.getInt("OnSaleItems_id"));
		shoppingCartItem.setQuantity(rs.getInt("quantity"));
		User user = UserDao.getUserById(rs.getInt("Users_id"));
		shoppingCartItem.setUser(user);//in the page, when user is updated, please refectch all user-related objects from database;
		
		return shoppingCartItem;
	}

	/**
	 * no pkey for this table, just use userid and itemid
	 * @param uid
	 * @param iid
	 * @return
	 */
	public static ShoppingCartItem getSCItemByUserAndItemIds(int uid, int iid) {
		ShoppingCartItem shoppingCartItem = null;
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from ShoppingCartItems where Items_id = ? and Users_id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, iid);
			preparedStatement.setInt(2, uid);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				shoppingCartItem = convertToShoppingCartItem(rs);
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
		return shoppingCartItem;
	}
	
	public static ShoppingCartItem getItemByKey(int id) {
		ShoppingCartItem shoppingCartItem = null;
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from ShoppingCartItems where id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				shoppingCartItem = convertToShoppingCartItem(rs);
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
		return shoppingCartItem;
	}
	
	/**
	*Get all shoppingcartitems of a user. 
	*/
	public static List<ShoppingCartItem> getSCItemByUserId(int uid) {
		List<ShoppingCartItem> resultList = new ArrayList<ShoppingCartItem>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from ShoppingCartItems where Users_id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, uid);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToShoppingCartItem(rs));
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
//		for(ShoppingCartItem k : resultList)
//			System.out.println("cartItem in dao:"+ k.getQuantity());
		return resultList;
	}
	
	 
	public static void save(ShoppingCartItem oitem) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		// INSERT INTO table_name
		// (id,publtype,publyear,title,venues,mdate,stock,User_id,isPaused)
		// VALUES (value1,value2,value3,...);
		String selectSQL = "insert into ShoppingCartItems (Items_id,quantity,Users_id,mdate,OnSaleItems_id)"
				+ " values(?,?,?,?,?)";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, oitem.getItem().getId());
			preparedStatement.setInt(2,oitem.getQuantity());
			preparedStatement.setInt(3,oitem.getUser().getId());
			preparedStatement.setTimestamp(4, oitem.getMdate());
			preparedStatement.setInt(5, oitem.getOnSaleItem_id());

			// execute select SQL statement
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

	public static void update(ShoppingCartItem oitem) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;
		// String selectSQL = "update Items set title = ? where id = ?";
		
		String selectSQL = "update OnSaleItems Items_id = ?,quantity = ? , mdate = ?"+ 
		" where OnSaleItems_id = ? and Users_id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1,oitem.getItem().getId());
			preparedStatement.setInt(2,oitem.getQuantity());
			preparedStatement.setTimestamp(3, oitem.getMdate());
			preparedStatement.setInt(4, oitem.getOnSaleItem_id());
			preparedStatement.setInt(5,oitem.getUser().getId());

			// execute select SQL statement
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

	
	public static void delete(ShoppingCartItem oitem) {

		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "delele from ShoppingCartItems where Users_id = ? and OnSaleItems_id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, oitem.getUser().getId());
			preparedStatement.setInt(2, oitem.getOnSaleItem_id());

			// execute select SQL stetement
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
	
}
