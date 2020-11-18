package cardsBase;

import java.util.List;

public interface CrudDao<T> {
	void load(T t);
	T find(Integer id);
	void update(T t);
	void delete(Integer id);
	List<T> findAll();
	

}
