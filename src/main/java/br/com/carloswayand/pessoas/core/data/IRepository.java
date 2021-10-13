package br.com.carloswayand.pessoas.core.data;

import java.util.List;

public interface IRepository<T extends Identifiable> {
	T create(T pessoa);
	List<T> findAll();
	T findById(String id);
	T update(String id, T obj);
	void delete(String id);
}
