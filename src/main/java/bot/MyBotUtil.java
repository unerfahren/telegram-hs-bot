package bot;

public class MyBotUtil {
	public static enum MainStage {
		MENU,
		DECKS, DH, WARRIOR, DRUID, PRIEST, MAGE, HUNTER, PALADIN, ROGUE, WARLOCK, SHAMAN,
		SEARCH,
		CARDS,
		BACKS
	}
	
	public static void nextStage(Integer id, String message) {
		MainStage next = Stages.getStage(id, message);
		UsersBase.users.put(id, next);
	}
	
}	
	
	
	
	

