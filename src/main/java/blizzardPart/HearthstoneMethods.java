package blizzardPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import blizzardPart.JsonClasses.CardBacks;
import blizzardPart.JsonClasses.Cards;
import bot.Bot;
import bot.DefaultCard;
import bot.DefaultCardBacks;
import bot.DefaultCards;
import bot.MyBotUtil;
import bot.UsersBase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HearthstoneMethods {
	String urlCards = "https://eu.api.blizzard.com/hearthstone/cards?locale=ru_RU&access_token=";// US360ncI5I3hIwH6iLnQk01bzDiCEwtzBY&sort=name&name=";
	String urlBacks = "https://eu.api.blizzard.com/hearthstone/cardbacks?locale=ru_RU&access_token=";// US360ncI5I3hIwH6iLnQk01bzDiCEwtzBY&textFilter=";
	String accessToken;

	public HearthstoneMethods() {
		// TODO Auto-generated constructor stub
		this.accessToken = Bot.accessToken;
	}

	public List<HearthstoneBean> getData(int id, String name) {
		MyBotUtil.MainStage stage = UsersBase.users.get(id);
		switch (stage) {
		case CARDS:
			Cards cards = getCardsByName(name);
			List<HearthstoneBean> cardList = new ArrayList<HearthstoneBean>(Arrays.asList(cards.getCards()));
			return cardList;
		case BACKS:
			CardBacks backs = getBacks(name);
			List<HearthstoneBean> backList = new ArrayList<HearthstoneBean>(Arrays.asList(backs.getBacks()));
			return backList;
		default:
			return genDefalultBean();
		}
	}

	public CardBacks getBacks(String name) {
		try {
			StringBuilder stringBuilder = new StringBuilder(urlBacks);
			stringBuilder.append(URLEncoder.encode(accessToken, "UTF-8"));
			stringBuilder.append("&textFilter=");
			stringBuilder.append(URLEncoder.encode(name, "UTF-8"));
			URL adress = new URL(stringBuilder.toString());
			HttpURLConnection connection = (HttpURLConnection) adress.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(0);
			connection.setReadTimeout(0);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(getContent(connection.getInputStream()), JsonClasses.CardBacks.class);
		} catch (MalformedURLException malEx) {
			System.err.println("wrong access token or unsuported url parametrs");
			malEx.printStackTrace();
			return null;
		} catch (JsonProcessingException jsonEx) {
			System.err.println("Json answer from blizzard's api is changed");
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
			return new DefaultCardBacks();
		}
	}

	public Cards getCardsByName(String name) {
		try {
			StringBuilder stringBuilder = new StringBuilder(urlCards);
			stringBuilder.append(URLEncoder.encode(accessToken, "UTF-8"));
			stringBuilder.append("&sort=name&name=");
			stringBuilder.append(URLEncoder.encode(name, "UTF-8"));
			URL adress = new URL(stringBuilder.toString());
			HttpURLConnection connection = (HttpURLConnection) adress.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(0);
			connection.setReadTimeout(0);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(getContent(connection.getInputStream()), JsonClasses.Cards.class);
		} catch (MalformedURLException malEx) {
			System.err.println("wrong access token or unsuported url parametrs");
			malEx.printStackTrace();
			return null;
		} catch (JsonProcessingException jsonEx) {
			System.err.println("Json answer from blizzard's api is changed");
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
			return new DefaultCards();
		}
	}

	private String getContent(InputStream inputStream) {
		try (final BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
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
	}
	
	private List<HearthstoneBean> genDefalultBean() {
		List<HearthstoneBean> list = new ArrayList<>();
		list.add(new DefaultCard());
		return list;
	}
}
