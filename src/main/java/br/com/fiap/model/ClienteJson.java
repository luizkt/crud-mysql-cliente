package br.com.fiap.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteJson {

	@JsonProperty("nome")
	@NotNull
	private String nome;
	
	@JsonProperty("rg")
	@NotNull
	private String rg;
	
	@JsonProperty("endereco")
	private EnderecoJson endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public EnderecoJson getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoJson endereco) {
		this.endereco = endereco;
	}
	
}
