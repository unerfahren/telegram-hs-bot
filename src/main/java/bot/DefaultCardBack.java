package bot;

import blizzardPart.JsonClasses.CardBack;

public class DefaultCardBack extends CardBack {
	
	public DefaultCardBack() {
		// TODO Auto-generated constructor stub
		setId(0);
		setImage("/home/bogdan/eclipse-workspace/resources/memas.jpg");
		setName("Ошибка!!! Близард не хочет показывать карты");
		setText("Попробуй повторить запрос. Если не помогло, я выезжаю");
	}

}
