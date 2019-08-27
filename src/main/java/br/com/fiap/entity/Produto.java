package br.com.fiap.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PRODUTO", catalog = "pdv", uniqueConstraints = { @UniqueConstraint(columnNames = "PRODUTO_ID") })
@NamedQuery(name = "Produto.findAll", query = "select p from Produto p")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUTO_ID", unique = true, nullable = false)
	private Integer produtoId;

	@Column(name = "CODIGO")
	private String codigo;

	@Column(name = "DESCRICAO")
	private String desc;

	@Column(name = "QUANTIDADE")
	private int quantidadeEstoque;

	@Column(name = "PRECO")
	private double preco;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produtoPk", cascade = CascadeType.ALL)
	private Set<Itens> itens = new HashSet<>();
	
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "produtos_pedidos")
//	private Set<Pedido> pedidos = new HashSet<>();

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Set<Itens> getItens() {
		return itens;
	}

	public void setItens(Set<Itens> itens) {
		this.itens = itens;
	}
}
