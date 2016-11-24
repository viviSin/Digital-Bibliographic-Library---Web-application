package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.Author;
//done
public class AuthorDao extends BaseDao{

	public static List<Author> getAllAuthor() {

		List<Author> resultList = new ArrayList<Author>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Authors";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultList.add(convertToAuthor(rs));
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
		return resultList;
	}

	private static Author convertToAuthor(ResultSet rs) throws SQLException {
		Author author = new Author();
		author.setId(rs.getInt("id"));
		author.setName(rs.getString("name"));
		return author;
	}

	public static Author getAuthorById(int id) {
		Author author = null;
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Authors where id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				author = convertToAuthor(rs);
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
		return author;
	}

	public static Author getAuthorByName(String name) {
		Author author = null;
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT * from Authors where name = ?";
		try {
			dbConnection = getDBConnection(); 
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, name);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				author = convertToAuthor(rs);
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
		return author;
	}
	
	public static ArrayList<Author> getAuthorsByItemId(int iid) {
		ArrayList<Author> authors = new ArrayList<Author>();
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "SELECT distinct a.id, a.name from Authors a, _write w, Items i where i.id = w.Items_id and w.Authors_id = a.id and i.id = ? ";
		try {
			dbConnection = getDBConnection(); 
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, iid);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				System.out.println("I ve got something");
				authors.add(convertToAuthor(rs));
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
		return authors;
	}
	

	public static void saveOrUpdate(Author author) {
		if (author.getId() == 0) {
			save(author);
		} else {
			update(author);
		}
	}

	public static void save(Author author) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "insert into Authors(name) values(?);";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, author.getName());

			// execute select SQL stetement
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				author.setId(rs.getInt(1));
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

	public static void update(Author author) {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "update Authors set name = ? where id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, author.getName());
			preparedStatement.setInt(2, author.getId());

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

	public static void delete(Author author) {

		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;

		String selectSQL = "delete from Authors where id = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, author.getId());

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
