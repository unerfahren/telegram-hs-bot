package bot;

import blizzardPart.HearthstoneBean;

public class DefaultHearthstoneBean implements HearthstoneBean {
	int id;
	String name;
	String image;
	String text;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public void setImage(String image) {
		// TODO Auto-generated method stub
		this.image = image;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		this.text = text;
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

}
