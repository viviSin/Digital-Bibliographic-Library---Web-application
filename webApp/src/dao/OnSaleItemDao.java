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

import dto.Author;
import dto.Item;
import dto.OnSaleItem;
import dto.User;

//done
public class OnSaleItemDao extends BaseDao {
	public static List<OnSaleItem> getAllOnSaleItem() {

		List<OnSaleItem> resultList = new ArrayList<OnSaleItem>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from OnSaleItems";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToOnSaleItem(rs));
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

	private static OnSaleItem convertToOnSaleItem(ResultSet rs) throws SQLException {

		OnSaleItem onSaleItem = new OnSaleItem();
		
		onSaleItem.setId(rs.getInt("id"));		
		
		Item item = ItemDao.getItemById(rs.getInt("Items_id"));
		onSaleItem.setItem(item);
		
		onSaleItem.setPrice(rs.getFloat("price"));
		
		onSaleItem.setMdate(rs.getTimestamp("mdate"));
		
		onSaleItem.setQuantity(rs.getInt("quantity"));
		
		User user = UserDao.getUserById(rs.getInt("Users_id"));
		onSaleItem.setSeller(user);//in the page, when user is updated, please refectch all user-related objects from database;
		
		onSaleItem.setIsPaused(rs.getInt("isPaused"));

		return onSaleItem;
	}
	
	public static List<OnSaleItem> getUnPausedOnSaleItem() {

		List<OnSaleItem> resultList = new ArrayList<OnSaleItem>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from OnSaleItems where isPaused = 1";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToOnSaleItem(rs));
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
	
	public static OnSaleItem getOnSaleItemById(int id) {
		OnSaleItem onSaleItem = null;
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from OnSaleItems where id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				onSaleItem = convertToOnSaleItem(rs);
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
		return onSaleItem;
	}

	public static ArrayList<OnSaleItem> getOnSaleItemByTitle(String title) {
		ArrayList<OnSaleItem> resultList = new ArrayList<OnSaleItem>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from OnSaleItems where title = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, title);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToOnSaleItem(rs));
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
	
	public static ArrayList<OnSaleItem> getOnSaleItemByItemId(int iid) {
		ArrayList<OnSaleItem> resultList = new ArrayList<OnSaleItem>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from OnSaleItems where Items_id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, iid);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToOnSaleItem(rs));
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
/*
	public static ArrayList<Item> getItemByVenues(String venues) {
		ArrayList<Item> resultList = new ArrayList<Item>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Items where venues = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, venues);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToOnSaleItem(rs));
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

	public static ArrayList<Item> getItemByPriceRange(float lowerBound, float upperBound) {
		ArrayList<Item> resultList = new ArrayList<Item>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Items where mdate between ? and ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setFloat(1, lowerBound);
			preparedStatement.setFloat(2, upperBound);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToOnSaleItem(rs));
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

	public static ArrayList<Item> getItemByStockRange(int lowerBound, int upperBound) {
		ArrayList<Item> resultList = new ArrayList<Item>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Items where stock between ? and ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, lowerBound);
			preparedStatement.setInt(2, upperBound);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToOnSaleItem(rs));
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

	public static ArrayList<Item> getItemByUser_Id(int User_id) {
		ArrayList<Item> resultList = new ArrayList<Item>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Items where  Users_id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, User_id);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToOnSaleItem(rs));
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

	public static ArrayList<Item> getItemByIsPaulsed(int isPaused) {
		ArrayList<Item> resultList = new ArrayList<Item>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Items where  isPaused = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, isPaused);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToOnSaleItem(rs));
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

	public static void saveOrUpdate(Item item) {
		if (item.getId() == 0) {
			save(item);
		} else {
			update(item);
		}

		updateAuthors(item);
	}
*/
	
	public static void save(OnSaleItem oitem) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		// INSERT INTO table_name
		// (id,publtype,publyear,title,venues,mdate,stock,User_id,isPaused)
		// VALUES (value1,value2,value3,...);
		String selectSQL = "insert into OnSaleItems (id,Items_id,quantity,mdate,Users_id,isPaused,price)"
				+ " values(?,?,?,?,?,?,?)";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, oitem.getId());
			preparedStatement.setInt(2,oitem.getItem().getId());
			preparedStatement.setInt(3,oitem.getQuantity());
			Date date = new Date();
			preparedStatement.setTimestamp(4, new Timestamp(date.getTime()));
			preparedStatement.setInt(5, oitem.getSeller().getId());
			preparedStatement.setInt(6,oitem.getIsPaused());
			preparedStatement.setFloat(7, oitem.getPrice());
			// execute select SQL statement
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				oitem.setId(rs.getInt(1));
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

	public static void update(OnSaleItem oitem) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		// String selectSQL = "update Items set title = ? where id = ?";
		
		String selectSQL = "update OnSaleItems set Items_id = ?,quantity = ? ,mdate = ?,Users_id = ?,isPaused = ?,price = ?"
				+ " where id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1,oitem.getItem().getId());
			preparedStatement.setInt(2,oitem.getQuantity());
			preparedStatement.setTimestamp(3, oitem.getMdate());
			preparedStatement.setInt(4, oitem.getSeller().getId());
			preparedStatement.setInt(5,oitem.getIsPaused());
			preparedStatement.setFloat(6, oitem.getPrice());
			preparedStatement.setInt(7, oitem.getId());
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

	public static void delete(OnSaleItem oitem) {

		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String deleteSQL = "delete from OnSaleItems where id =";
		try {
			dbConnection = getDBConnection();
			deleteSQL = deleteSQL + oitem.getId();
			preparedStatement = dbConnection.prepareStatement(deleteSQL);

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
	
	public static ArrayList<OnSaleItem> getOnSaleItemByUserId(int id) {
		ArrayList<OnSaleItem> resultList = new ArrayList<OnSaleItem>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from OnSaleItems where Users_id = ";
		try {
			dbConnection = getDBConnection();
			selectSQL = selectSQL+Integer.toString(id);
			preparedStatement = dbConnection.prepareStatement(selectSQL);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToOnSaleItem(rs));
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
	
	public static void pauseById(int id) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String updateSQL = "UPDATE OnSaleItems SET isPaused=0 WHERE id = ";
		try {
			dbConnection = getDBConnection();
			updateSQL = updateSQL+Integer.toString(id);
			preparedStatement = dbConnection.prepareStatement(updateSQL);

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
	
	public static void unpauseById(int id) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String updateSQL = "UPDATE OnSaleItems SET isPaused=1 WHERE id = ";
		try {
			dbConnection = getDBConnection();
			updateSQL = updateSQL+Integer.toString(id);
			preparedStatement = dbConnection.prepareStatement(updateSQL);

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
}
