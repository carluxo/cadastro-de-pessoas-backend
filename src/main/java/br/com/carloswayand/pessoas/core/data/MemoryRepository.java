package br.com.carloswayand.pessoas.core.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryRepository<T extends Identifiable> implements Repository<T> {
	protected static Long lastId = 0L;
	protected ConcurrentHashMap<String, T> database = new ConcurrentHashMap<>();

	private static Long getNextId() {
		lastId = lastId + 1L;
		return lastId;
	}

	@Override
	public T create(T obj) {
		obj.setId(getNextId().toString());
		obj.update();

		database.put(obj.getId(), obj);

		return obj;
	}

	@Override
	public List<T> findAll() {
		ArrayList<T> all = new ArrayList<>();
		all.addAll(this.database.values());

		return all;
	}

	@Override
	public T findById(String id) {
		var obj = this.database.get(id);

		if (obj == null) {
			throw new IdentifiableNotFoundException();
		}
		return obj;
	}

	@Override
	public T update(String id, T obj) {
		obj.update();
		this.database.put(id, obj);
		
		return obj;
	}

	@Override
	public void delete(String id) {
		try {
			this.database.remove(id);			
		} catch(Exception e) {
			throw new IdentifiableNotFoundException(e.getMessage());
		}
	}
}
