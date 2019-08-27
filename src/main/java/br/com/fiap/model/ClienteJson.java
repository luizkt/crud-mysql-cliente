package br.com.fiap.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteJson {

	@JsonProperty("cpf")
	@NotNull
	private String cpf;

	@JsonProperty("nome")
	@NotNull
	private String nome;

	@JsonProperty("sobrenome")
	@NotNull
	private String sobrenome;

	@JsonProperty("email")
	@NotNull
	private String email;

	@JsonProperty("dataNascimento")
	private String dataNascimento;

	@JsonProperty("endereco")
	private EnderecoJson endereco;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EnderecoJson getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoJson endereco) {
		this.endereco = endereco;
	}

}
