package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.sun.istack.NotNull;

@Entity
public class Endereco implements IEntidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(name = "cep",length = 45)
	private String cep;
	
	@NotNull
	@Column(name = "rua",length = 45)
	private String rua;
	
	@NotNull
	@Column(name = "bairro",length = 45)
	private String bairro;
	
	@NotNull
	@Column(name = "cidade",length = 45)
	private String cidade;
	
	@NotNull
	@Column(name = "estado",length = 45)
	private String estado;
	
	@NotNull
	@Column(name = "complemento",length = 45)
	private String complemento;
	
	@ManyToOne
	private Barbearia barbearia;
	
	@ManyToOne
	private Cliente cliente;
	
	@Version
	@NotNull
	private Date version;
	
	public Endereco() {
		super();
	}

	public Endereco(String cep, String rua, String bairro, String cidade, String estado, String complemento, Barbearia barbearia, Cliente cliente, Date version) {
		super();
		this.cep = cep;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.complemento = complemento;
		this.barbearia = barbearia;
		this.cliente = cliente;
		this.version = version;
	}
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}
	

	public Object getPrimaryKey() {
	
		return getCep();		
	}

}
