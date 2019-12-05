package entidades;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class Servicos implements IEntidade {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column (name = "nome", length = 45)
	private String nome;

	@NotNull
	@Column (name = "descricao", length = 45)
	private String descricao;
	
	@NotNull
	@Column (name = "valor", length = 20)
	private Double valor;
	
	@ManyToOne
	private Barbearia barbearia;
	
	@ManyToOne(cascade= CascadeType.ALL)  
	private Set<Agendamento> agendamento; 
	
	@ManyToMany (mappedBy = "servicos", cascade= CascadeType.ALL)
	private List Pagamento;


	public Servicos() {
		super();
	}

	public Servicos(int id, String nome, String descricao, Double valor, Barbearia barbearia) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.barbearia = barbearia;
	}

	public Object getPrimaryKey() {
		
	    return getId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Barbearia getBarbearia() {
		return barbearia;
	}

	public void setBarbearia(Barbearia barbearia) {
		this.barbearia = barbearia;
	}


}
