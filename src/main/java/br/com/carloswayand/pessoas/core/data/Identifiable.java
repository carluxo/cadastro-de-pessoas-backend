package br.com.carloswayand.pessoas.core.data;

import java.time.Instant;

public abstract class Identifiable {
	protected Instant createdAt;
	protected Instant updatedAt;

	public abstract String getId();

	public abstract void setId(String id);

	public void update() {
		if (this.createdAt == null) {
			this.createdAt = Instant.now();
		} else {
			this.updatedAt = Instant.now();
		}
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}
	
	public Instant getCreatedAt() {
		return createdAt;
	}
}
