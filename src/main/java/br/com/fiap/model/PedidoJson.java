package br.com.fiap.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PedidoJson {
	
	@JsonProperty("codigo")
	@NotBlank(message = "Código é obrigatório")
	private String codigo;
	
	@JsonProperty("descricao")
	@NotBlank(message = "Descrição é obrigatório")
	private String descricao;
	
	@JsonProperty("rg")
	@NotBlank(message = "RG é obrigatório")
	private String rg;
	
	@JsonProperty("produtos")
	@NotBlank(message = "Produtos são obrigatórios")
	List<ProdutoJson> produtos;

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

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public List<ProdutoJson> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoJson> produtos) {
		this.produtos = produtos;
	}

}
