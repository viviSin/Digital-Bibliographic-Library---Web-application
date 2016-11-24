package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.User;
//done
public class UserDao extends BaseDao {

	public static List<User> getAllUsers() {

		List<User> resultList = new ArrayList<User>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Users";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToUser(rs));
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

	private static User convertToUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));
		user.setCreditcard(rs.getString("creditcard"));
		user.setPassword(rs.getString("password"));
		user.setHash(rs.getString("hash"));
		user.setYearOfBirth(rs.getInt("yearOfBirth"));
		user.setIsVerified(rs.getInt("isVerified"));
		user.setIsBanned(rs.getInt("isBanned"));
		return user;
	}

	public static User getUserById(int id) {
		User user = null;
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Users where id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				user = convertToUser(rs);
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
		return user;
	}

	public static User getUserByUsername(String username) {
		User user = null;
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Users where username = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				user = convertToUser(rs);
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
		return user;
	}

	public static void saveOrUpdate(User user) {
		if (user.getId() == 0) {
			save(user);
		} else {
			update(user);
		}
	}

	public static void save(User user) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "insert into Users (id,username,password,email,yearOfBirth,address,creditcard,hash,isVerified,isBanned) values(?, ?, ?,   ?, ?, ?,    ?, ?, ?,?)";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, user.getId());//although it is generated, we do need a placeholder here.
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setInt(5, user.getYearOfBirth());
			preparedStatement.setString(6, user.getAddress());
			preparedStatement.setString(7, user.getCreditcard());
			preparedStatement.setString(8, user.getHash());
			preparedStatement.setInt(9, user.getIsVerified());
			preparedStatement.setInt(10, user.getIsBanned());

			// execute select SQL stetement
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();//this will set id right.
			if (rs.next()) {
				user.setId(rs.getInt(1));
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

	public static void update(User user) {//username is not able to be changed
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;
		
		//some attributes are not supposed to be updated, ever. eg username and hash
		String selectSQL = "update Users set password = ?, email = ?, yearOfBirth = ?, "
				+ " address = ?, creditcard = ?, isVerified = ?, isBanned = ? , username = ? where id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setInt(3, user.getYearOfBirth());
			preparedStatement.setString(4, user.getAddress());
			preparedStatement.setString(5, user.getCreditcard());
			preparedStatement.setInt(6, user.getIsVerified());
			preparedStatement.setInt(7, user.getIsBanned());
			preparedStatement.setString(8, user.getUsername());
			preparedStatement.setInt(9, user.getId());

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

	public static void delete(User user) {

		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "delete from Users where id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, user.getId());

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
