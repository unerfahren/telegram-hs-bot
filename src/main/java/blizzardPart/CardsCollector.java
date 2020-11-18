package blizzardPart;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import blizzardPart.JsonClasses.Card;
import blizzardPart.JsonClasses.CardBack;
import blizzardPart.JsonClasses.CardBacks;
import blizzardPart.JsonClasses.Cards;
import blizzardPart.JsonClasses.CardsArray;
import bot.Bot;

public class CardsCollector {
	String page = "&page=";
	String cardsUrl = "https://eu.api.blizzard.com/hearthstone/cards?locale=ru_RU";
	String backsUrl = "https://eu.api.blizzard.com/hearthstone/cardbacks?locale=ru_RU";
	String cardsCodeUrl = "https://eu.api.blizzard.com/hearthstone/deck?locale=ru_RU";
	String codeDeck = "&code=";
	String accessToken = "&access_token=" + Bot.accessToken;
	String pageSize = "&pageSize=500";
	Integer count = 1;
	ObjectMapper mapper = new ObjectMapper();
	List<HearthstoneBean> cardList = new ArrayList<>();
	List<HearthstoneBean> backList = new ArrayList<>();

	public List<HearthstoneBean> getCards() {
		Integer pageCount = 1;
		Card[] cards;

		try {
			do {
				URL adress;
				adress = new URL(cardsUrl + accessToken + pageSize + page + pageCount);
				HttpURLConnection connection = (HttpURLConnection) adress.openConnection();
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				Cards popka = mapper.readValue(connection.getInputStream(), Cards.class);
				cards = popka.getCards();
				for (int i = 0; i < cards.length; i++) {
					cardList.add(cards[i]);
					count++;
				}

				pageCount++;

			} while (cards.length != 0);

			return cardList;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<HearthstoneBean> getCardBacks() {
		Integer pageCount = 1;
		CardBack[] backs;

		try {
			do {
				URL adress = new URL(backsUrl + accessToken + pageSize + page + pageCount);
				HttpURLConnection connection = (HttpURLConnection) adress.openConnection();
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				CardBacks popka;
				popka = mapper.readValue(connection.getInputStream(), CardBacks.class);
				backs = popka.getBacks();
				for (int i = 0; i < backs.length; i++) {
					backList.add(backs[i]);
					count++;
				}
				pageCount++;
			} while (backs.length != 0);

			return backList;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<HearthstoneBean> getCardsByCode(String code) {
		Card[] cards;

		try {
			URL adress;
			adress = new URL(cardsCodeUrl + accessToken + codeDeck + code);
			HttpURLConnection connection = (HttpURLConnection) adress.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			CardsArray popka = mapper.readValue(connection.getInputStream(), CardsArray.class);
			cards = popka.getCards();
			for (int i = 0; i < cards.length; i++) {
				cardList.add(cards[i]);
				count++;
			}

			return cardList;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
