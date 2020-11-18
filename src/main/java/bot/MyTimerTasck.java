package bot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import blizzardPart.JsonClasses;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyTimerTasck extends TimerTask {

	String accessUrl = "https://us.battle.net/oauth/token?client_id=ec8b25c04963444d887d40d66dd267ee&client_secret=5L90ztjdSJJv4HocxCnd10A5nOOs9WVm";

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			makeReq();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// new url - mailformedurlexception; url.opencon -IOException; "POST" -
	// ProtocolException; all out-IOException;

	public String makeToken() {
		try {
			URL url = new URL(this.accessUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setConnectTimeout(1000);
			con.setReadTimeout(1000);

			Map<String, String> parameters = new HashMap<>();

			parameters.put("grant_type", "client_credentials");

			con.setDoOutput(true);

			try (final DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
				out.writeBytes(getParamsString(parameters));
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}

			try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
				String inputLine;
				final StringBuilder content = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				return content.toString();
			} catch (final Exception ex) {
				ex.printStackTrace();
				return "";
			}
		} catch (MalformedURLException malformedURLException) {
			System.err.println("wrong client id or secret value");
			malformedURLException.printStackTrace();
			return "";
		} catch (IOException ioException) {
			// TODO Auto-generated catch block
			ioException.printStackTrace();
			return "";
		}
	}

	public String getParamsString(final Map<String, String> params) {
		final StringBuilder result = new StringBuilder();

		params.forEach((name, value) -> {
			try {
				result.append(URLEncoder.encode(name, "UTF-8"));
				result.append('=');
				result.append(URLEncoder.encode(value, "UTF-8"));
				result.append('&');
			} catch (final UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		});

		final String resultString = result.toString();
		return !resultString.isEmpty() ? resultString.substring(0, resultString.length() - 1) : resultString;
	}

	public void makeReq() throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonClasses.AccessToken accessToken = mapper.readValue(makeToken(), JsonClasses.AccessToken.class);
		Bot.accessToken = accessToken.getAccess_token();
	}
}
