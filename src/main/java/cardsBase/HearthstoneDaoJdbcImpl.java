package cardsBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import blizzardPart.HearthstoneBean;
import bot.DefaultCard;
import bot.DefaultHearthstoneBean;
import bot.MyBotUtil.MainStage;

public class HearthstoneDaoJdbcImpl implements HearthstoneDao {
	private MainStage currentStage;
	final Map<MainStage, String> tablesMap = Map.of(MainStage.CARDS, "cards", MainStage.BACKS, "backs");
	Properties properties;
	Connection con;

	public HearthstoneDaoJdbcImpl(MainStage currentStage) {
		// TODO Auto-generated constructor stub
		this.currentStage = currentStage;
		properties = loadProperties();
		con = getConnection();
	}

	public Properties loadProperties() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("src/main/resources/db.properties"));
			return properties;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {

			String dbUrl = properties.getProperty("db.url");
			String dbUser = properties.getProperty("db.user");
			String dbPassword = properties.getProperty("db.password");

			Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			return connection;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<HearthstoneBean> findAllByName(String name) {
		List<HearthstoneBean> list = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = con.prepareStatement(
					"select * from " + tablesMap.get(currentStage) + " where name similar to ? order by name;");
			statement.setString(1, "(" + name + "%)|(% " + name + "%)");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next() && list.size() < 30) {
				DefaultHearthstoneBean bean = new DefaultHearthstoneBean();
				bean.setId(resultSet.getInt("id"));
				bean.setName(resultSet.getString("name"));
				bean.setImage(resultSet.getString("image"));
				bean.setText(resultSet.getString("text"));
				list.add(bean);
			}
			resultSet.close();
			statement.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeConnection();
		}
		return genDefalultBean();
	}

	private List<HearthstoneBean> genDefalultBean() {
		List<HearthstoneBean> list = new ArrayList<>();
		list.add(new DefaultCard());
		return list;
	}

	public void loadAll(List<HearthstoneBean> hearthstoneBeanList) {
		for (HearthstoneBean bean : hearthstoneBeanList) {

			if (find(bean.getId()) != null) {
				update(bean);
			} else
				load(bean);
		}
	}

	@Override
	public HearthstoneBean find(Integer id) {
		// TODO Auto-generated method stub
		try {
			ResultSet result = null;

			PreparedStatement selectStatement = con
					.prepareStatement("select * from " + tablesMap.get(currentStage) + " where id = ?");
			selectStatement.setInt(1, id);

			result = selectStatement.executeQuery();

			if (result.next()) {
				HearthstoneBean bean = new DefaultHearthstoneBean();
				Integer cardId = result.getInt("id");
				String cardName = result.getString("name");
				String cardImage = result.getString("image");
				String cardText = result.getString("text");

				bean.setId(cardId);
				bean.setName(cardName);
				bean.setImage(cardImage);
				bean.setText(cardText);
				selectStatement.close();
				return bean;
			} else
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeConnection();
		}

		return null;
	}

	@Override
	public void update(HearthstoneBean bean) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement updateStatement = con.prepareStatement(
					"update " + tablesMap.get(currentStage) + " set name = ?, image = ?, text = ? where id = ?");

			updateStatement.setString(1, bean.getName().toLowerCase());
			updateStatement.setString(2, bean.getImage());
			updateStatement.setString(3, bean.getText().replaceAll("\\<.*?\\>", "").replaceAll("&nbsp;", ""));
			updateStatement.setInt(4, bean.getId());
			updateStatement.execute();

			updateStatement.close();

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeConnection();
		}
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<HearthstoneBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load(HearthstoneBean bean) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement insertStatement = con.prepareStatement(
					"insert into " + tablesMap.get(currentStage) + "(id, name, image, text) values(?, ?, ?, ?)");

			insertStatement.setInt(1, bean.getId());
			insertStatement.setString(2, bean.getName().toLowerCase());
			insertStatement.setString(3, bean.getImage());
			insertStatement.setString(4, bean.getText().replaceAll("\\<.*?\\>", "").replaceAll("&nbsp;", ""));
			insertStatement.execute();

			insertStatement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeConnection();
		}

	}

}
