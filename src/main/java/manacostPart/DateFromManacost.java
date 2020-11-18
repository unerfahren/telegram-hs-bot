package manacostPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import bot.MyBotUtil.MainStage;

public class DateFromManacost {
	String url = "http://hs-manacost.ru/top-kolody";
	Map<MainStage, String> heroName = Map.of(MainStage.DH, "/top-kolody-oxotnik-na-demonov/", MainStage.MAGE, "/top-kolody-maga/",
												MainStage.SHAMAN, "/top-kolody-shamana/", MainStage.ROGUE, "/top-kolody-razbojnika/",
												MainStage.WARLOCK, "/top-kolody-chernoknizhnika/", MainStage.PRIEST, "/top-kolody-zhreca/",
												MainStage.WARRIOR, "/top-kolody-voina/", MainStage.DRUID, "/top-koloda-druida/", 
												MainStage.HUNTER, "/top-kolody-oxotnika/", MainStage.PALADIN, "/top-kolody-paladina/");

	public String getDeckCode(MainStage stage) {
		try {
			URL adress = new URL(url + heroName.get(stage));
			HttpURLConnection con = (HttpURLConnection) adress.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(0);
			con.setReadTimeout(0);
			InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
			BufferedReader br = new BufferedReader(inputStreamReader);
			String key = "<div class=\"desc\">";
			String s = null;
			boolean flag = false;
			boolean stop = false;
			while (!stop) {
				s = br.readLine();
				if (s.contains(key)) {
					flag = true;
				}
				if (flag && s.contains("value=")) {
					stop = true;
				}
			}
			String code = s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\"") - 1).replaceAll("\"", "").replaceAll("=", "").replaceAll(" ", "");
			br.close();
			return code;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
