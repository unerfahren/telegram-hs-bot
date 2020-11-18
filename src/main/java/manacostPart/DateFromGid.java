package manacostPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import blizzardPart.HearthstoneBean;
import bot.DefaultCard;
import bot.MyBotUtil.MainStage;
import cardsBase.HearthstoneDaoJdbcImpl;

public class DateFromGid {
	String url = "https://hearthgid.com";// deck?deck_name=&mode=all&cards=&class=dh&page=1";

	Map<HearthstoneBean, String> cardMap = new HashMap<>();
	Set<HearthstoneBean> set;

	private static final Map<MainStage, String> stageMap = Map.of(MainStage.DH, "dh", MainStage.WARRIOR, "warrior",
			MainStage.DRUID, "druid", MainStage.PRIEST, "priest", MainStage.MAGE, "mage", MainStage.HUNTER, "hunter",
			MainStage.PALADIN, "paladin", MainStage.ROGUE, "rogue", MainStage.WARLOCK, "warlock", MainStage.SHAMAN,
			"shaman");

	public DateFromGid() {
		// TODO Auto-generated constructor stub
	}

	public Map<HearthstoneBean, String> getData(MainStage mainStage) {
		HttpURLConnection con = null;
		try {
			URL adress = new URL(url.concat("/deck?mode=all&class=" + stageMap.get(mainStage)));
			con = (HttpURLConnection) adress.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(0);
			con.setReadTimeout(0);
		} catch (MalformedURLException malEx) {
			System.err.println("invalid parameters for hearthgid.com");
			malEx.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
				BufferedReader br = new BufferedReader(inputStreamReader)) {
			String s;

			while (!(s = br.readLine()).contains("<div class=\"row main_item\">")) {
			}

			s = br.readLine();
			String result = s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\""));
			System.out.println(result);
			br.close();
			inputStreamReader.close();
			getDeckCode(result);
			set = new HashSet<HearthstoneBean>(cardMap.keySet());
			for (HearthstoneBean card : set) {
				System.out.println(card.getName());
			}
			return cardMap;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genErrorMap();
	}

	public void getDeckCode(String deckAdress) throws IOException, SQLException {
		URL adress = new URL(url.concat(deckAdress));
		HttpURLConnection con = (HttpURLConnection) adress.openConnection();
		con.setRequestMethod("GET");
		con.setConnectTimeout(0);
		con.setReadTimeout(0);
		InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
		BufferedReader br = new BufferedReader(inputStreamReader);
		String key = "";
		String s;
		HearthstoneDaoJdbcImpl dataBaseInteract = new HearthstoneDaoJdbcImpl(MainStage.CARDS);
		while (!(s = br.readLine()).contains("<div class=\"col-md-6 copy-container\">")) {
			if (s.contains("<span class=\"card-name\">"))
				key = s.substring(s.indexOf(">") + 1, s.lastIndexOf("<"));
			if (s.contains("<span class=\"card-count\">"))
				cardMap.put(dataBaseInteract.findAllByName(key.toLowerCase()).get(0),
						s.substring(s.indexOf("</span>") + 7, s.lastIndexOf("</span>")));
		}
		System.out.println(cardMap.toString());
		System.out.println(cardMap.size());
		br.close();
		dataBaseInteract.closeConnection();

	}

	private Map<HearthstoneBean, String> genErrorMap() {
		Map<HearthstoneBean, String> map = new HashMap<>();
		map.put(new DefaultCard(), "kek");
		return map;
	}
}
