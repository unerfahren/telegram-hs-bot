package bot;

import java.util.Random;

import blizzardPart.JsonClasses.Card;

public class DefaultCard extends Card {
	public DefaultCard() {
		// TODO Auto-generated constructor stub
		setId(new Random().nextInt());
		setName("Ошибка!!!");
		setText("Попробуй повторить запрос. Если не помогло, я выезжаю");
		setImage("https://lh3.googleusercontent.com/proxy/K3SBpnwrF2Z7PwHXdW7xZC4etbQNpOgVk_GwnDKXVSycrgLETJjxmBaahARJECXAF68M7ZAOKX7dhKzZP3jOXgE903E");
	}
}
