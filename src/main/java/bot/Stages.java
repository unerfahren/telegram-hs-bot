package bot;

import java.util.Map;

import bot.MyBotUtil.MainStage;

public class Stages {
	private static Map<String, MainStage> menu = Map.of("Поиск", MainStage.SEARCH, "Актуальные Колоды", MainStage.DECKS);
	private static Map<String, MainStage> search = Map.of("Карты", MainStage.CARDS, "Назад", MainStage.MENU, "/start wtf", MainStage.MENU, "test", MainStage.CARDS, "Рубашки", MainStage.BACKS);
	private static Map<String, MainStage> cards = Map.of("Назад", MainStage.SEARCH);
	private static Map<String, MainStage> backs = Map.of("Назад", MainStage.SEARCH);
	private static Map<String, MainStage> decks = Map.ofEntries(Map.entry("Назад", MainStage.MENU), Map.entry("dh", MainStage.DH), Map.entry("warrior", MainStage.WARRIOR),
															Map.entry("druid", MainStage.DRUID), Map.entry("priest", MainStage.PRIEST),Map.entry("mage", MainStage.MAGE),
															Map.entry("hunter", MainStage.HUNTER), Map.entry("paladin", MainStage.PALADIN),Map.entry("rogue", MainStage.ROGUE), 
															Map.entry("warlock", MainStage.WARLOCK), Map.entry("shaman", MainStage.SHAMAN));
	private static Map<String, MainStage> dh = Map.of("Назад", MainStage.DECKS);
	private static Map<String, MainStage> warrior = Map.of("Назад", MainStage.DECKS);
	private static Map<String, MainStage> druid = Map.of("Назад", MainStage.DECKS);
	private static Map<String, MainStage> priest = Map.of("Назад", MainStage.DECKS);
	private static Map<String, MainStage> mage = Map.of("Назад", MainStage.DECKS);
	private static Map<String, MainStage> hunter = Map.of("Назад", MainStage.DECKS);
	private static Map<String, MainStage> paladin = Map.of("Назад", MainStage.DECKS);
	private static Map<String, MainStage> warlock = Map.of("Назад", MainStage.DECKS);  //WARRIOR, DH, DRUID, PRIEST, MAGE, HUNTER, PALADIN, ROGUE, WARLOCK, SHAMAN:
	private static Map<String, MainStage> shaman = Map.of("Назад", MainStage.DECKS);
	private static Map<String, MainStage> rogue = Map.of("Назад", MainStage.DECKS);

	
	private static Map<MainStage, Map<String, MainStage>> map = Map.ofEntries(
			Map.entry(MainStage.MENU, menu), 
			Map.entry(MainStage.SEARCH, search),
			Map.entry(MainStage.CARDS, cards),
			Map.entry(MainStage.BACKS, backs),
			Map.entry(MainStage.DECKS, decks),
			Map.entry(MainStage.DH, dh), Map.entry(MainStage.WARRIOR, warrior), Map.entry(MainStage.DRUID, druid), Map.entry(MainStage.PRIEST, priest), Map.entry(MainStage.MAGE, mage),
			Map.entry(MainStage.HUNTER, hunter), Map.entry(MainStage.ROGUE, rogue),
			Map.entry(MainStage.PALADIN, paladin), Map.entry(MainStage.WARLOCK, warlock), Map.entry(MainStage.SHAMAN, shaman));

	public static MainStage getStage(Integer id, String message) {
		MainStage currentStage = UsersBase.users.get(id);
		MainStage next = map.get(currentStage).get(message);
		if(next == null) return currentStage;
		return next;
	
	}
}
