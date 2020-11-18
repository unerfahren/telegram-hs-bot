package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Logic implements Runnable {
	TelegramLongPollingBot bot;
	Integer userId;
	long chatId;
	String messageText;

	public Logic(Integer userId, long chatId, String messageText, TelegramLongPollingBot bot) {
		// TODO Auto-generated constructor stub
		this.bot = bot;
		this.userId = userId;
		this.chatId = chatId;
		this.messageText = messageText;
	}

	public void run() {
		// TODO Auto-generated method stub

		if (!UsersBase.users.containsKey(userId))
			UsersBase.users.put(userId, MyBotUtil.MainStage.MENU);

		MyBotUtil.nextStage(userId, messageText);

		MessageTuner messageTuner = new MessageTuner(chatId);

		messageTuner.setMessage(UsersBase.users.get(userId));

		try {
			if (messageTuner.sendMessage != null)
				bot.execute(messageTuner.sendMessage);
			if (messageTuner.inlineSendMessage != null)
				bot.execute(messageTuner.inlineSendMessage);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
