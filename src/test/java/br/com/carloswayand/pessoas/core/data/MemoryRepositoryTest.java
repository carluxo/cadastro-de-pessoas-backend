package br.com.carloswayand.pessoas.core.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.junit.jupiter.api.Test;

class MemoryRepositoryTest {
	protected static MemoryRepositoryObject teste = new MemoryRepositoryObject();
	protected static Repository<MemoryRepositoryObject> repository = new MemoryRepository<>(); 
	
	
	@Test
	void create() {
		Identifiable created = repository.create(teste);
		assertNotNull(created);
		assertNotNull(created.getId());
		assertEquals(LocalDate.now(ZoneId.of("UTC")), LocalDate.ofInstant(created.getCreatedAt(), ZoneId.of("UTC")));
	}
	
	@Test
	void findAll() {
		repository.create(teste);
		List<MemoryRepositoryObject> all = repository.findAll();
		
		assertNotNull(all);
		assertFalse(all.isEmpty());
	}
	
	@Test
	void findById() {
		var created = repository.create(teste);
		var byId = repository.findById(created.getId());
		
		assertNotNull(byId);
		assertEquals(created.id, byId.id);
		assertEquals(created.nome, byId.nome);
		assertEquals(created.getCreatedAt(), byId.getCreatedAt());
		assertEquals(created.getUpdatedAt(), byId.getUpdatedAt());
	}
	
	@Test
	void dontFindById() {
		assertThrows(IdentifiableNotFoundException.class, () -> repository.findById("9998989898"));
	}
	
	@Test
	void update() {
		MemoryRepositoryObject created = repository.create(teste);
		assertNotNull(created.getUpdatedAt());
		
		created.nome = "NOVO NOME NO TESTE";
		MemoryRepositoryObject updated = repository.update(created.getId(), created);
		
		assertNotNull(updated);
		assertNotNull(updated.getUpdatedAt());
		assertEquals(created.getCreatedAt(), updated.getCreatedAt());
		assertEquals("NOVO NOME NO TESTE", updated.nome);
	}
	
	@Test
	void delete() {
		MemoryRepositoryObject create = repository.create(teste);
		repository.delete(create.getId());
	}
	
	@Test
	void dontRemove() {
		assertThrows(IdentifiableNotFoundException.class, () -> repository.delete(null)); 
	}
}

class MemoryRepositoryObject extends Identifiable {
	Long id;
	public String nome = "";
	
	@Override
	public String getId() {
		return this.id == null ? null : this.id.toString();
	}

	@Override
	public void setId(String id) {
		if (id != null && !id.isBlank()) {
			this.id = this.id == null ? Long.valueOf(id) : this.id;			
		}
	}
}
