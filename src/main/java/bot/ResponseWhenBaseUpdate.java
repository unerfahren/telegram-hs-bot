package bot;

import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ResponseWhenBaseUpdate {
	Update update;
	Bot bot;

	public ResponseWhenBaseUpdate(Update update, Bot bot) {
		// TODO Auto-generated constructor stub
		this.update = update;
		this.bot = bot;
	}
	
	public void prepareRes() throws TelegramApiException {
		
		
		if(update.hasMessage()) {
			SendMessage message = new SendMessage();
			message.setText("База карт обновляется, повторите запрос позже");
			message.setChatId(update.getMessage().getChatId());
			bot.execute(message);
		}
		
		if(update.hasCallbackQuery()) {
			SendMessage message = new SendMessage();
			message.setText("База карт обновляется, повторите запрос позже");
			message.setChatId(update.getCallbackQuery().getMessage().getChatId());
			bot.execute(message);
		}
		
		if(update.hasInlineQuery()) {
			String mem = "https://lh3.googleusercontent.com/proxy/K3SBpnwrF2Z7PwHXdW7xZC4etbQNpOgVk_GwnDKXVSycrgLETJjxmBaahARJECXAF68M7ZAOKX7dhKzZP3jOXgE903E";
			AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery();
			InlineQueryResultArticle result = new InlineQueryResultArticle();
			
			InputTextMessageContent inputMessageContent = new InputTextMessageContent();
			inputMessageContent.setMessageText("[База карт обновляется, повторите запрос позже](" + mem + ")").setParseMode("MARKDOWN");
			
			result.setDescription("База карт обновляется, повторите запрос позже")
					.setHideUrl(true)
					.setId("0")
					.setInputMessageContent(inputMessageContent)
					.setThumbUrl(mem)
					.setTitle("Ошибка!!!")
					.setUrl(mem);
			
			answerInlineQuery.setInlineQueryId(update.getInlineQuery().getId()).setResults(result).setCacheTime(0);
			
			bot.execute(answerInlineQuery);

		}
		
	}
}
