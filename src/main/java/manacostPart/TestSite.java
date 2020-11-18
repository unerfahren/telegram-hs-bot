package manacostPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestSite {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//new DateFromGid().getData();

		URL adress = new URL("http://hs-manacost.ru/top-kolody/top-kolody-oxotnik-na-demonov/");
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
			if(s.contains(key)) {
				flag = true;
			}
			if(flag && s.contains("value=")) {
				stop = true;
			}
			System.out.println(s);
		}
		System.out.println(s);
		String k = s.substring(s.indexOf("=") + 1, s.lastIndexOf("=") - 1).replaceAll("\"", "");
		System.out.println(k);

		br.close();
		
	}

}
