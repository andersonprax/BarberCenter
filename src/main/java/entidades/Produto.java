package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class Produto implements IEntidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
    private String nome;
	
	@NotNull
    private String descricao;
	
	@NotNull
    private int quantidade;
	
	@NotNull
    private double valor;
	
	@OneToMany
	private Barbearia barbearia;
	
	@OneToMany
	private Pagamento pagamento;
	
	public Produto(){
		super ();
	}

	

	public Produto(int id, String nome, String descricao, int quantidade, double valor,
			Barbearia barbearia) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valor = valor;
		this.barbearia = barbearia;
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



	public int getQuantidade() {
		return quantidade;
	}



	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}



	public double getValor() {
		return valor;
	}



	public void setValor(double valor) {
		this.valor = valor;
	}



	public Barbearia getBarbearia() {
		return barbearia;
	}



	public void setBarbearia(Barbearia barbearia) {
		this.barbearia = barbearia;
	}



	public Object getPrimaryKey() {
		// TODO Auto-generated method stub
		return getId();
	}	
}