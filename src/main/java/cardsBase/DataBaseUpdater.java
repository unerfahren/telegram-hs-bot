package cardsBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import blizzardPart.HearthstoneBean;

public class DataBaseUpdater {
	static Properties properties = new Properties();
	List<String> dbTableNames = Arrays.asList("cards", "backs");

	public static void loadProperties() {
		try {
			properties.load(new FileInputStream("src/main/resources/db.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {

			loadProperties();
			String dbUrl = properties.getProperty("db.url");
			String dbUser = properties.getProperty("db.user");
			String dbPassword = properties.getProperty("db.password");
			String dbDriverClassName = properties.getProperty("db.driverClassName");
			System.out.println(dbUrl + dbUser + dbPassword + dbDriverClassName);

			Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			return connection;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void loadData(List<HearthstoneBean> HearthstoneBeanList, String dataBaseTableName) throws SQLException {
		if(!dbTableNames.contains(dataBaseTableName)) throw new SQLException();
		Connection con = getConnection();
		ResultSet result = null;
		PreparedStatement selectStatement = con.prepareStatement("select * from " + dataBaseTableName + " where id = ?");

		PreparedStatement updateStatement = con
				.prepareStatement("update " + dataBaseTableName + " set name = ?, image = ?, text = ? where id = ?");

		PreparedStatement insertStatement = con
				.prepareStatement("insert into " + dataBaseTableName + "(id, name, image, text) values(?, ?, ?, ?)");
		for (HearthstoneBean bean : HearthstoneBeanList) {
			selectStatement.setInt(1, bean.getId());
			result = selectStatement.executeQuery();
			if (result.next()) {
				updateStatement.setString(1, bean.getName().toLowerCase());
				updateStatement.setString(2, bean.getImage());
				updateStatement.setString(3, bean.getText().replaceAll("\\<.*?\\>", "").replaceAll("&nbsp;", ""));
				updateStatement.setInt(4, bean.getId());
				updateStatement.execute();
			} else {
				insertStatement.setInt(1, bean.getId());
				insertStatement.setString(2, bean.getName().toLowerCase());
				insertStatement.setString(3, bean.getImage());
				insertStatement.setString(4, bean.getText().replaceAll("\\<.*?\\>", "").replaceAll("&nbsp;", ""));
				insertStatement.execute();
			}
		}
		updateStatement.close();
		insertStatement.close();
		selectStatement.close();
		con.close();
	}
}
