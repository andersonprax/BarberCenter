package entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import com.sun.istack.NotNull;

@Entity
public class Barbearia implements IEntidade{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private int cnpj;
	@NotNull
	private String nome;
	@NotNull
	private String email;
	@NotNull
	private String telefone;
	@NotNull
	private String pessoaResponsavel;
	@NotNull
	private String senha;
	
	@Version
	private Date version;
	
	public Barbearia() {
		super();
	}
	
	public Barbearia(int cnpj, String nome, String email, String telefone, String pessoaResponsavel, String senha, Date version) {
		super();
		this.cnpj = cnpj;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.pessoaResponsavel = pessoaResponsavel;
		this.senha = senha;
		this.version = version;
	}

	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPessoaResponsavel() {
		return pessoaResponsavel;
	}

	public void setPessoaResponsavel(String pessoaResponsavel) {
		this.pessoaResponsavel = pessoaResponsavel;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}
	
	public Object getPrimaryKey() {
		return getCnpj();
	}	
	
}
