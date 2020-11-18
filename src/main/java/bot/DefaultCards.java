package bot;

import blizzardPart.JsonClasses.Cards;

public class DefaultCards extends Cards {
	
	public DefaultCards() {
		// TODO Auto-generated constructor stub
		DefaultCard[] cards = new DefaultCard[1];
		cards[0] = new DefaultCard();
		setCards(cards);
	}
}
