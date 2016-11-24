package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Author;

//many to many, author | item
//not sure if we need this.
public class WriteDao extends BaseDao {
	/*
	 * since it is a relationship table, we dont need need getall method. add it
	 * if u think there is a need. -Leon
	 */

	public static List<Integer> getAuthorsIdByItemId(int itemid) {
		System.out.println("\n\n\n\n\n"+itemid);
		List<Integer> resultList = new ArrayList<Integer>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT Authors_id from _write where Items_id=?";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);

			// execute select SQL statement
			preparedStatement.setInt(1, itemid);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {// No convert method here because it is just
								// integer id
				resultList.add(rs.getInt("Authors_id"));
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

	public static List<Integer> getItemIdIdByAuthorId(int authorId) {

		List<Integer> resultList = new ArrayList<Integer>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT Items_id from _write where Authors_id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			preparedStatement.setInt(1, authorId);
			preparedStatement.executeUpdate();

			while (rs.next()) {// No convert method here because it is just
								// integer id
				resultList.add(rs.getInt("Items_id"));
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

	// suppose user changes the item's author, then the author might be a new
	// author or just other author
	// if user change item or author, just delete and save. save time, safe
	// code.
	// therefore no update method. -Leon
	public static void saveOrUpdate(int olditemid, int oldauthorid, int newitemid, int newauthorid) {
		delete(olditemid, oldauthorid);
		save(newitemid, newauthorid);
	}

	public static void save(int itemid, int authorid) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "insert into _write values(?, ?);";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, itemid);
			preparedStatement.setInt(2, authorid);

			// execute select SQL stetement
			preparedStatement.executeUpdate();

			// no read object for this table, so not object setting clause.

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

	// is there any situation where we need delete write? -Leon
	public static void delete(int itemid, int authorid) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "delele from _write where Items_id = ? and Authors_id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, itemid);
			preparedStatement.setInt(2, authorid);

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

	public static void deleteItemAuthors(int itemid) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "delete from _write where Items_id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, itemid);

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
