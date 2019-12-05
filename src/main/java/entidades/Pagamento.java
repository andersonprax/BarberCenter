package entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.Set;


import com.sun.istack.NotNull;

public class Pagamento {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany
	@JoinColumn(name="barbearia_id", referencedColumnName="id", nullable=false)
	private Barbearia barbearia;
	
	@OneToMany
	@JoinColumn(name="cliente_id", referencedColumnName="id", nullable=false)
	private Cliente cliente;
	
	@ManyToOne (cascade= CascadeType.ALL)
	private Set<Produto> produto;
	
	@ManyToMany (mappedBy = "servicos", cascade= CascadeType.ALL)
	private List Servicos;
	
    @Version
    @Column (name = "date", length = 12) 
    private Date version;
    
    @NotNull
    @Column (name = "Forma de Pagamento", length = 15)
    private String formaPagamento;

    public Pagamento(int id, Barbearia barbearia, Cliente cliente, Set<Produto> produto, List servicos, Date version,
			String formaPagamento) {
		super();
		this.id = id;
		this.barbearia = barbearia;
		this.cliente = cliente;
		this.produto = produto;
		Servicos = servicos;
		this.version = version;
		this.formaPagamento = formaPagamento;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Barbearia getBarbearia() {
		return barbearia;
	}

	public void setBarbearia(Barbearia barbearia) {
		this.barbearia = barbearia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Produto> getProduto() {
		return produto;
	}

	public void setProduto(Set<Produto> produto) {
		this.produto = produto;
	}

	public List getServicos() {
		return Servicos;
	}

	public void setServicos(List servicos) {
		Servicos = servicos;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
}

	