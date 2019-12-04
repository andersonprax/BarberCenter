package entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.sun.istack.NotNull;

@Entity
public class Agendamento implements IEntidade{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column (name = "date", length = 12)
	private Date date;
	
	@NotNull
	@Column (name = "status", length = 45)
	private int status;
	
	@OneToMany  
	private List<Servicos> servicos;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Barbearia barbearia;
	
	@Version
	@NotNull
	private Date version;
	
	public Agendamento() {
		super();
	}
	
	public Agendamento(int id, Date date, int status, List<Servicos> servicos, Cliente cliente, Barbearia barbearia) {
		super();
		this.id = id;
		this.date = date;
		this.status = status;
		this.servicos = servicos;
		this.cliente = cliente;
		this.barbearia = barbearia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Servicos> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servicos> servicos) {
		this.servicos = servicos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Barbearia getBarbearia() {
		return barbearia;
	}

	public void setBarbearia(Barbearia barbearia) {
		this.barbearia = barbearia;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	public Object getPrimaryKey() {
		return getId();
	}
}
