package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class Produto implements IEntidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProd;
	
	@NotNull
    private String nomeProd;
	
	@NotNull
    private String descricaoProd;
	
	@NotNull
    private int quantidadeProd;
	
	@NotNull
    private double valorProd;
	
	@ManyToOne
	private Barbearia barbearia;
	
	public Produto(){
		super ();
	}

	

	public Produto(int idProd, String nomeProd, String descricaoProd, int quantidadeProd, double valorProd,
			Barbearia barbearia) {
		super();
		this.idProd = idProd;
		this.nomeProd = nomeProd;
		this.descricaoProd = descricaoProd;
		this.quantidadeProd = quantidadeProd;
		this.valorProd = valorProd;
		this.barbearia = barbearia;
	}



	public int getIdProd() {
		return idProd;
	}



	public void setIdProd(int idProd) {
		this.idProd = idProd;
	}



	public String getNomeProd() {
		return nomeProd;
	}



	public void setNomeProd(String nomeProd) {
		this.nomeProd = nomeProd;
	}



	public String getDescricaoProd() {
		return descricaoProd;
	}



	public void setDescricaoProd(String descricaoProd) {
		this.descricaoProd = descricaoProd;
	}



	public int getQuantidadeProd() {
		return quantidadeProd;
	}



	public void setQuantidadeProd(int quantidadeProd) {
		this.quantidadeProd = quantidadeProd;
	}



	public double getValorProd() {
		return valorProd;
	}



	public void setValorProd(double valorProd) {
		this.valorProd = valorProd;
	}



	public Barbearia getBarbearia() {
		return barbearia;
	}



	public void setBarbearia(Barbearia barbearia) {
		this.barbearia = barbearia;
	}



	public Object getPrimaryKey() {
		// TODO Auto-generated method stub
		return getIdProd();
	}	
}