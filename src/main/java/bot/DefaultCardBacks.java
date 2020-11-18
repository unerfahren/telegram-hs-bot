package bot;

import blizzardPart.JsonClasses.CardBacks;

public class DefaultCardBacks extends CardBacks {

	public DefaultCardBacks() {
		// TODO Auto-generated constructor stub
		DefaultCardBack[] backs = new DefaultCardBack[1];
		backs[0] = new DefaultCardBack();
		setCardBacks(backs);
	}
}
