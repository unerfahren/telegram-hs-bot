package bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import blizzardPart.CardsCollector;
import blizzardPart.HearthstoneBean;
import bot.MyBotUtil.MainStage;
import cardsBase.HearthstoneDaoJdbcImpl;
import manacostPart.DateFromManacost;

public class InlineLogic implements Runnable {
	private TelegramLongPollingBot bot;
	private String messageText;
	private Integer userId;
	private String queryId;

	public InlineLogic(String messageText, Integer userId, String queryId, TelegramLongPollingBot bot) {
		// TODO Auto-generated constructor stub
		this.bot = bot;
		this.messageText = messageText;
		this.userId = userId;
		this.queryId = queryId;
	}

	@Override
	public void run() {

		List<InlineQueryResult> result = new ArrayList<InlineQueryResult>();
		MainStage currentStage = UsersBase.users.get(userId);
		AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery();

		switch (currentStage) {
		case CARDS, BACKS:
			HearthstoneDaoJdbcImpl impl = new HearthstoneDaoJdbcImpl(currentStage);
			List<HearthstoneBean> list = impl.findAllByName(messageText);
			impl.closeConnection();

			for (int i = 0; i < list.size(); i++)
				result.add(new InlineQueryResultArticle()
						.setDescription(list.get(i).getText())
						.setId(Integer.toString(i)).setTitle(list.get(i).getName()).setHideUrl(true)
						.setUrl(list.get(i).getImage()).setThumbUrl(list.get(i).getImage())
						.setInputMessageContent(new InputTextMessageContent().setParseMode("MARKDOWN")
								.setMessageText("[" + list.get(i).getName() + "](" + list.get(i).getImage() + ")")));
			
			answerInlineQuery.setInlineQueryId(queryId).setResults(result).setCacheTime(0);
			break;
		case DH, WARRIOR, DRUID, PRIEST, MAGE, HUNTER, PALADIN, ROGUE, WARLOCK, SHAMAN:
			
			Map<HearthstoneBean, String> map = new HashMap<HearthstoneBean, String>();
			
			String code = new DateFromManacost().getDeckCode(currentStage);
			List<HearthstoneBean> cards = new CardsCollector().getCardsByCode(code);
			
			
			
			for(int i = 0; i < cards.size(); i++) {
				HearthstoneBean bean = cards.get(i);
				Integer count = 1;
				for(int j = i + 1; j < cards.size(); j++) {
					if(bean.getId() == cards.get(j).getId()) {
						count++;
						cards.remove(j);
					}
				}
				map.put(bean, count.toString());
			}
		
			Set<HearthstoneBean> set = map.keySet();			
			
			for (HearthstoneBean card : set)
				result.add(new InlineQueryResultArticle()
						.setDescription(card.getText().replaceAll("\\<.*?\\>", "").replaceAll("&nbsp;", ""))
						.setId(Integer.toString(card.getId())).setTitle(card.getName() + " x" + map.get(card))
						.setHideUrl(true).setUrl(card.getImage()).setThumbUrl(card.getImage())
						.setInputMessageContent(new InputTextMessageContent().setParseMode("MARKDOWN")
								.setMessageText("[" + card.getName() + "](" + card.getImage() + ")")));
				
			answerInlineQuery.setInlineQueryId(queryId).setResults(result).setCacheTime(0);
			break;
		default:
			DefaultCard card = new DefaultCard();
			result.add(new InlineQueryResultArticle()
					.setDescription(card.getText())
					.setId(Integer.toString(card.getId())).setTitle(card.getName())
					.setHideUrl(true).setUrl(card.getImage()).setThumbUrl(card.getImage())
					.setInputMessageContent(new InputTextMessageContent().setParseMode("MARKDOWN")
							.setMessageText("[" + card.getName() + "](" + card.getImage() + ")")));
			
			answerInlineQuery.setInlineQueryId(queryId).setResults(result).setCacheTime(0);
			break;
		}
		
		try {
			bot.execute(answerInlineQuery);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
