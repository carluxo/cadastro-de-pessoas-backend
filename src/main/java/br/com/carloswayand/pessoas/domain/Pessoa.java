package br.com.carloswayand.pessoas.domain;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import br.com.carloswayand.pessoas.core.data.Identifiable;

public class Pessoa extends Identifiable {
	private Long id;
	protected Instant nascimento;
	protected String nome;

	protected Pessoa() {
	}

	public Pessoa(String nome, LocalDate nascimento) {
		this();
		this.nome = nome;
		this.nascimento = nascimento.atStartOfDay().toInstant(ZoneOffset.UTC);
	}
	
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

	public String getNome() {
		return this.nome;
	}

	public Instant getNascimento() {
		return nascimento;
	}	
}
