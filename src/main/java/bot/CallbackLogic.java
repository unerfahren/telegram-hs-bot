package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CallbackLogic implements Runnable {
	String message;
	long chatId;
	Integer messageId;
	User user;
	TelegramLongPollingBot bot;
	
	public CallbackLogic(String message, long chatId, Integer messageId, User user, TelegramLongPollingBot bot) {
		// TODO Auto-generated constructor stub
		this.message = message;
		this.chatId = chatId;
		this.messageId = messageId;
		this.user = user;
		this.bot = bot;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(!UsersBase.users.containsKey(user.getId()))
			UsersBase.users.put(user.getId(), MyBotUtil.MainStage.SEARCH);
		
		MyBotUtil.nextStage(user.getId(), message);
		
		
		
		MessageTuner messageTuner = new MessageTuner(chatId, messageId, message);
		messageTuner.setMessage(UsersBase.users.get(user.getId()));
		
		try {
			if(messageTuner.editMessageText != null)
				bot.execute(messageTuner.editMessageText);
			if(messageTuner.inlineSendMessage != null)
				bot.execute(messageTuner.inlineSendMessage);
		} catch (TelegramApiException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
