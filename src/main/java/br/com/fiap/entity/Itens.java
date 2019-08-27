package br.com.fiap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "ITENS", catalog = "pdv")
//@AssociationOverrides({ @AssociationOverride(name = "pedido", joinColumns = @JoinColumn(name = "PEDIDO_ID")),
//		@AssociationOverride(name = "produto", joinColumns = @JoinColumn(name = "PRODUTO_ID")) })
public class Itens implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItensPK id = new ItensPK();

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("pedidoPk")
	@JoinColumn(name = "pedidoPk", nullable=false, insertable=false, updatable=false)
	private Pedido pedidoPk;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("produtoPk")
	@JoinColumn(name = "produtoPk", nullable=false, insertable=false, updatable=false)
	private Produto produtoPk;

	@Column(name = "QUANTIDADE")
	private int quantidade;

	public ItensPK getId() {
		return id;
	}

	public void setId(ItensPK id) {
		this.id = id;
	}

	public Pedido getPedidoPk() {
		return pedidoPk;
	}

	public void setPedidoPk(Pedido pedidoPk) {
		this.pedidoPk = pedidoPk;
	}

	public Produto getProdutoPk() {
		return produtoPk;
	}

	public void setProdutoPk(Produto produtoPk) {
		this.produtoPk = produtoPk;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
