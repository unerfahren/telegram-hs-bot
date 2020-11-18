package bot;

import java.util.TimerTask;

import blizzardPart.CardsCollector;
import bot.MyBotUtil.MainStage;
import cardsBase.HearthstoneDaoJdbcImpl;

public class UpdateDataBaseTimerTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Bot.flag = false;
		while(BotThreadManager.threadList.size() != 0) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		CardsCollector cardsCollector = new CardsCollector();
		HearthstoneDaoJdbcImpl cardsBaseConnect = new HearthstoneDaoJdbcImpl(MainStage.CARDS);
		HearthstoneDaoJdbcImpl backsBaseConnect = new HearthstoneDaoJdbcImpl(MainStage.BACKS);
		
		cardsBaseConnect.loadAll(cardsCollector.getCards());
		backsBaseConnect.loadAll(cardsCollector.getCardBacks());
		
		Bot.flag = true;
	}

}
