package bot;

import org.telegram.telegrambots.meta.api.objects.User;

import bot.MyBotUtil.MainStage;

public class MyUser {
	private User user;
	private MainStage mainStage;
	
	public MyUser(User user, MainStage mainStage) {
		// TODO Auto-generated constructor stub
		this.user = user;
		this.mainStage = mainStage;
	}
	
	public User getUser() {
		return user;
	}
	public MainStage getMainStage() {
		return mainStage;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public void setMainStage(MainStage mainStage) {
		this.mainStage = mainStage;
	}

	
}
