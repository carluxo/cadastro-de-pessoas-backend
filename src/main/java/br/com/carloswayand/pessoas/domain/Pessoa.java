package br.com.carloswayand.pessoas.domain;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import br.com.carloswayand.pessoas.core.data.Identifiable;
import br.com.carloswayand.pessoas.resources.core.validation.ValidCpf;

public class Pessoa extends Identifiable {
	private Long id;
	
	@NotEmpty(message = "Nome deve ser informado")
	protected String nome;
	protected Genero genero;
	
	@Email(message = "E-mail deve ser válido")
	protected String email;
	
	protected String naturalidade;
	protected String nacionalidade;
	
	@NotNull
	@PastOrPresent(message = "Data de nascimento inválida")
	protected Instant nascimento;
	
	@ValidCpf
	@NotBlank(message = "CPF deve ser informado")
	protected String cpf;

	protected Pessoa() {
		
	}

	public Pessoa(String nome, LocalDate nascimento, String cpf) {
		this();
		this.nome = nome;
		this.cpf = cpf;
		this.setNascimento(nascimento);
	}
	
	private void setNascimento(LocalDate nascimento) {
		if (nascimento != null) {
			this.nascimento = nascimento.atStartOfDay().toInstant(ZoneOffset.UTC);
		}
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
