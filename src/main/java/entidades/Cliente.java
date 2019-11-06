package entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import com.sun.istack.NotNull;

@Entity
public class Cliente implements IEntidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String cpf;
	@NotNull
	private String nome;
	@NotNull
	private String email;
	@NotNull
	private String telefone;
	@NotNull
	private String senha;
	
	@Version
	@NotNull
	private Date version;

	public Cliente() {
		super();
	}

	public Cliente(String cpf, String nome, String email, String telefone, String senha, Date version) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.version = version;
	}

	

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the version
	 */
	public Date getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Date version) {
		this.version = version;
	}

	public Object getPrimaryKey() {
		return getCpf();
	}

	

}
