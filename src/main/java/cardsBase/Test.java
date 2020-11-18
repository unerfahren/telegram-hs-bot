package cardsBase;

import java.io.IOException;
import java.sql.SQLException;

import blizzardPart.CardsCollector;

public class Test {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
	//	CardsCollector cardsCollector = new CardsCollector();
	//	cardsCollector.nextPage();
	//	DataBaseConnect baseConnect = new DataBaseConnect();
	//	Connection con = baseConnect.getConnection();
	//	System.out.println(con.getCatalog());
	//	Statement statement = con.createStatement();
	//	statement.execute("insert into cards(name, text) values('"+null+"','testText');");
	//	statement.close();
	//	con.close();
		
		CardsCollector cardsCollector = new CardsCollector();
		DataBaseUpdater baseConnect = new DataBaseUpdater();
		baseConnect.loadData(cardsCollector.getCards(), "cards");
		baseConnect.loadData(cardsCollector.getCardBacks(), "backs");
		
	}

}
