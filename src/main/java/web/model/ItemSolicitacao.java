package web.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ItemSolicitacao implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@NotNull
	private Solicitacao solicitacao;
	
	@Id
	@ManyToOne
	@NotNull
	private Item item;
	
	@NotNull
	private int qtd;

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	@Override
	public String toString() {
		return "ItemSolicitacao [solicitacao=" + solicitacao + ", item=" + item + ", qtd=" + qtd + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(item, qtd, solicitacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemSolicitacao other = (ItemSolicitacao) obj;
		return Objects.equals(item, other.item) && qtd == other.qtd && Objects.equals(solicitacao, other.solicitacao);
	}



}
