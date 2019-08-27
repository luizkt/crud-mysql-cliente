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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PEDIDO", catalog = "pdv", uniqueConstraints = { @UniqueConstraint(columnNames = "PEDIDO_ID") })
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PEDIDO_ID", unique = true, nullable = false)
	private Integer pedidoId;
	
	@Column(name = "DESCRICAO")
	private String desc;

	@Column(name = "CODIGO")
	private String codigo;
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "PRODUTOS_PEDIDOS", catalog = "pdv", joinColumns = 
//	{@JoinColumn(name = "PEDIDO_ID", nullable = false, updatable=false)},
//	inverseJoinColumns = {@JoinColumn(name="PRODUTO_ID", nullable=false, updatable=false)})
//	private Set<Produto> produtos_pedidos = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDCLIENTE")
	private Cliente cliente;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedidoPk", cascade = CascadeType.ALL)
	private Set<Itens> itens = new HashSet<>();

	public Integer getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Itens> getItens() {
		return itens;
	}

	public void setItens(Set<Itens> itens) {
		this.itens = itens;
	}

}
