package cardsBase;

import java.util.List;

import blizzardPart.HearthstoneBean;

public interface HearthstoneDao extends CrudDao<HearthstoneBean> {
	List<HearthstoneBean> findAllByName(String name);

}
