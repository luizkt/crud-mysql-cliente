package br.com.fiap.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProdutoJson {

	@JsonProperty("codigo")
	@NotBlank(message = "Código é obrigatório")
	private String codigo;
	
	@JsonProperty("descricao")
	@NotBlank(message = "Descrição é obrigatório")
	private String descricao;
	
	@JsonProperty("preco")
	@NotBlank(message = "Preço é obrigatório")
	private Double preco;
	
	@JsonProperty("quantidade")
	@NotBlank(message = "Quantidade é obrigatório")
	private int quantidade;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
