package bot;

import java.util.Timer;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Bot extends TelegramLongPollingBot {

	public static String accessToken;
	public static boolean flag = true;

	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		System.out.println("update");
		if (!flag) {
			try {
				new ResponseWhenBaseUpdate(update, this).prepareRes();
			} catch (TelegramApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			Thread thread = null;
			if (update.hasCallbackQuery()) {
				CallbackQuery callbackQuery = update.getCallbackQuery();
				Message message = callbackQuery.getMessage();
				CallbackLogic logic = new CallbackLogic(callbackQuery.getData(), message.getChatId(),
						message.getMessageId(), callbackQuery.getFrom(), this);
				thread = new Thread(logic);
				thread.start();
			}
			if (update.getMessage() != null && update.getMessage().hasText()) {
				Message message = update.getMessage();
				Logic logic = new Logic(message.getFrom().getId(), message.getChatId(), message.getText(), this);
				thread = new Thread(logic);
				thread.start();
			}
			if (update.hasInlineQuery()) {
				InlineQuery inlineQuery = update.getInlineQuery();
				InlineLogic logic = new InlineLogic(inlineQuery.getQuery(), inlineQuery.getFrom().getId(),
						inlineQuery.getId(), this);
				thread = new Thread(logic);
				thread.start();
			}
			BotThreadManager.waitThreads(thread);
		}
	}

	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "HearthstoneBot";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "1497801360:AAEisdPunXU0esVf2s73PBiFkdKfPl7KMuE";
	}

	public static void main(String args[]) {
		Class.forName("org.postgresql.Driver");
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		MyTimerTasck timerTasck = new MyTimerTasck();
		UpdateDataBaseTimerTask UpdateTimerTask = new UpdateDataBaseTimerTask();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTasck, 0, 1000 * 40000);
		Timer threadTimer = new Timer();
		threadTimer.scheduleAtFixedRate(UpdateTimerTask, 1000 * 5, 1000 * 80000);

		try {
			telegramBotsApi.registerBot(new Bot());
		} catch (TelegramApiRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
