package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import dto.Author;
import dto.Item;
import dto.OnSaleItem;


//done
public class ItemDao extends BaseDao {
	public static List<Item> getAllItem() {

		List<Item> resultList = new ArrayList<Item>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Items";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToItem(rs));
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

	private static Item convertToItem(ResultSet rs) throws SQLException {

		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setPubltype(rs.getString("publtype"));
		item.setVenues(rs.getString("venues"));
		item.setPublyear(rs.getInt("publyear"));
		item.setTitle(rs.getString("title"));
	//	item.setAuthors(AuthorDao.getAuthorsByItemId(item.getId()));
	//	ArrayList<OnSaleItem> onSaleItems = new ArrayList<OnSaleItem>();
	//	int iid = item.getId();
	//	onSaleItems.addAll(OnSaleItemDao.getOnSaleItemByItemId(iid));
		item.setAuthors(null);
		item.setOnSaleItems(null);

		return item;
	}

	public static Item getItemById(int id) {
		Item Item = null;
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Items where id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				Item = convertToItem(rs);
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
		return Item;
	}


	public static ArrayList<Item> getItemByPultype(String pultype) {
		ArrayList<Item> resultList = new ArrayList<Item>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Items where pultype = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, pultype);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToItem(rs));
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

	public static ArrayList<Item> getItemByPultyearRange(int lowerBound, int upperBound) {
		ArrayList<Item> resultList = new ArrayList<Item>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Items where pultyear between ? and ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, lowerBound);
			preparedStatement.setInt(2, upperBound);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToItem(rs));
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

	public static ArrayList<Item> getItemByTitle(String title) {
		ArrayList<Item> resultList = new ArrayList<Item>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Items where title = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, title);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToItem(rs));
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
				resultList.add(convertToItem(rs));
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

	public static void save(Item item) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		// INSERT INTO table_name
		// (id,publtype,publyear,title,venues,mdate,stock,User_id,isPaused)
		// VALUES (value1,value2,value3,...);
		String selectSQL = "insert into Items (id,publtype,publyear,title,venues)"
				+ " values(?,?,?,?,?)";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, item.getId());
			preparedStatement.setString(2, item.getPubltype());
			preparedStatement.setInt(3, item.getPublyear());
			preparedStatement.setString(4, item.getTitle());
			preparedStatement.setString(5, item.getVenues());

			// execute select SQL statement
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				item.setId(rs.getInt(1));
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
		
		updateAuthors(item);
	}

	public static void update(Item item) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		// String selectSQL = "update Items set title = ? where id = ?";
		String selectSQL = "update Items set publtype = ?,publyear = ?,title = ?,venues = ?"
				+ " where id = ?";
		try {// mdate is auto
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, item.getPubltype());
			preparedStatement.setInt(2, item.getPublyear());
			preparedStatement.setString(3, item.getTitle());
			preparedStatement.setString(4, item.getVenues());
			preparedStatement.setInt(5, item.getId());
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
		
		updateAuthors(item);
	}

	public static void delete(Item item) {

		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "delele from Items where id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, item.getId());

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

	public static void updateAuthors(Item item) {
		if (item.getAuthors() == null) {
			return;
		}
		
		// Delete all existing relation for item and authors
		WriteDao.deleteItemAuthors(item.getId());

		// insert authors relation
		for (Author author : item.getAuthors()) {
			WriteDao.save(item.getId(), author.getId());
		}
	}

}
