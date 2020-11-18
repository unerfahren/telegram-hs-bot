package bot;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class MessageTuner {
	Long chatId;
	Integer messageId;
	String message;
	
	EditMessageText editMessageText = null;
	SendMessage sendMessage = null;
	SendMessage inlineSendMessage = null;
	
	public MessageTuner(long chatId, Integer messageId, String message) {
		this.chatId = chatId;
		this.messageId = messageId;
		this.message = message;
	}
	public MessageTuner(long chatId) {
		this.chatId = chatId;
	}
	
	public void setMessage(MyBotUtil.MainStage stage) {
		switch(stage) {
		case MENU:
			setButtonsMenu();
			setText("Меню");
		break;
		case SEARCH:
			setInlineSearch();
			setButtonsSearch();
			setText("Поиск");
		break;
		case DECKS:
			setButtonsDecks();
			setButtonsSearch();
			setText("Колоды");
			break;
		case WARRIOR, DH, DRUID, PRIEST, MAGE, HUNTER, PALADIN, ROGUE, WARLOCK, SHAMAN:
			setInlineButtons(messageId);
			break;
		case CARDS:
			setInlineButtons(messageId);
			break;
		case BACKS:
			setInlineButtons(messageId);
		default:
			break;
		}
	}
	
	public void setText(String s) {
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(chatId);
		sendMessage.setText(s);
	}
	
	public void setButtonsMenu() {
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage = new SendMessage().setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        
        List<KeyboardRow> keyboard = new ArrayList<>();
        
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Поиск"));
        
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("Актуальные Колоды"));
        
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        
        replyKeyboardMarkup.setKeyboard(keyboard);
	}
	
	public void setButtonsSearch() {
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage = new SendMessage().setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        
        List<KeyboardRow> keyboard = new ArrayList<>();
        
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Назад"));
        
        keyboard.add(keyboardFirstRow);
        
        replyKeyboardMarkup.setKeyboard(keyboard);
	}
	
	public void setInlineSearch() {
		InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
		if(messageId == null)
			inlineSendMessage = new SendMessage().setReplyMarkup(inlineKeyboardMarkup).setChatId(chatId).setText("Что искать?");
		else
			editMessageText = new EditMessageText().setChatId(chatId).setMessageId(messageId).setReplyMarkup(inlineKeyboardMarkup).setText("Что искать?");
	
		List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
		List<InlineKeyboardButton> inlineKeyboardButtonList1 = new ArrayList<>();
		List<InlineKeyboardButton> inlineKeyboardButtonList2 = new ArrayList<>();
		
		InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
		inlineKeyboardButton.setText("Карты").setCallbackData("Карты");
		InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
		inlineKeyboardButton2.setText("Рубашки").setCallbackData("Рубашки");

		
		inlineKeyboardButtonList1.add(inlineKeyboardButton);
		inlineKeyboardButtonList2.add(inlineKeyboardButton2);
		
		keyboard.add(inlineKeyboardButtonList1);
		keyboard.add(inlineKeyboardButtonList2);

		inlineKeyboardMarkup.setKeyboard(keyboard);
		
	}
	
	
	
	public void setInlineButtons(Integer s) {
		InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

		editMessageText = new EditMessageText();
		editMessageText.setChatId(chatId).setReplyMarkup(inlineKeyboardMarkup).setMessageId(s).setText("Нажми на поиск или напиши имя бота");
		List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
		List<InlineKeyboardButton> inlineKeyboardButtonList1 = new ArrayList<>();
		List<InlineKeyboardButton> inlineKeyboardButtonList2 = new ArrayList<>();
		
		InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
		inlineKeyboardButton1.setText("Поиск").setSwitchInlineQueryCurrentChat("");
		InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
		inlineKeyboardButton2.setText("Назад").setCallbackData("Назад");

		
		inlineKeyboardButtonList1.add(inlineKeyboardButton1);
		inlineKeyboardButtonList2.add(inlineKeyboardButton2);
		
		keyboard.add(inlineKeyboardButtonList1);
		keyboard.add(inlineKeyboardButtonList2);

		inlineKeyboardMarkup.setKeyboard(keyboard);
		
	}
	
	public void setButtonsDecks() {
		InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
		if(messageId == null)
			inlineSendMessage = new SendMessage().setReplyMarkup(inlineKeyboardMarkup).setChatId(chatId).setText("Выбери нужный класс");
		else
			editMessageText = new EditMessageText().setChatId(chatId).setMessageId(messageId).setReplyMarkup(inlineKeyboardMarkup).setText("Выбери нужный класс");
	
		List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
		List<InlineKeyboardButton> inlineKeyboardButtonList1 = new ArrayList<>();
		List<InlineKeyboardButton> inlineKeyboardButtonList2 = new ArrayList<>();
		List<InlineKeyboardButton> inlineKeyboardButtonList3 = new ArrayList<>();
		List<InlineKeyboardButton> inlineKeyboardButtonList4 = new ArrayList<>();
		List<InlineKeyboardButton> inlineKeyboardButtonList5 = new ArrayList<>();
		List<InlineKeyboardButton> inlineKeyboardButtonList6 = new ArrayList<>();
		List<InlineKeyboardButton> inlineKeyboardButtonList7 = new ArrayList<>();
		
		InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
		inlineKeyboardButton1.setText("Охотник на демонов").setCallbackData("dh");
		InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
		inlineKeyboardButton2.setText("Воин").setCallbackData("warrior");
		InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton().setText("Жрец").setCallbackData("priest");
		InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton().setText("Чернокнижник").setCallbackData("warlock");
		InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton().setText("Маг").setCallbackData("mage");
		InlineKeyboardButton inlineKeyboardButton6 = new InlineKeyboardButton().setText("Шаман").setCallbackData("shaman");
		InlineKeyboardButton inlineKeyboardButton7 = new InlineKeyboardButton().setText("Разбойник").setCallbackData("rogue");
		InlineKeyboardButton inlineKeyboardButton8 = new InlineKeyboardButton().setText("Друид").setCallbackData("druid");
		InlineKeyboardButton inlineKeyboardButton9 = new InlineKeyboardButton().setText("Паладин").setCallbackData("paladin");
		InlineKeyboardButton inlineKeyboardButton0 = new InlineKeyboardButton().setText("Охотник").setCallbackData("hunter");

		inlineKeyboardButtonList1.add(inlineKeyboardButton1);
		inlineKeyboardButtonList2.add(inlineKeyboardButton2);
		inlineKeyboardButtonList2.add(inlineKeyboardButton3);
		inlineKeyboardButtonList3.add(inlineKeyboardButton4);
		inlineKeyboardButtonList4.add(inlineKeyboardButton5);
		inlineKeyboardButtonList4.add(inlineKeyboardButton6);
		inlineKeyboardButtonList5.add(inlineKeyboardButton7);
		inlineKeyboardButtonList6.add(inlineKeyboardButton8);
		inlineKeyboardButtonList6.add(inlineKeyboardButton9);
		inlineKeyboardButtonList7.add(inlineKeyboardButton0);
		
		keyboard.add(inlineKeyboardButtonList1);
		keyboard.add(inlineKeyboardButtonList2);
		keyboard.add(inlineKeyboardButtonList3);
		keyboard.add(inlineKeyboardButtonList4);
		keyboard.add(inlineKeyboardButtonList5);
		keyboard.add(inlineKeyboardButtonList6);
		keyboard.add(inlineKeyboardButtonList7);

		inlineKeyboardMarkup.setKeyboard(keyboard);
	}

	
}
