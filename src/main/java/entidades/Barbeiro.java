package entidades;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.sun.istack.NotNull;

@Entity
public class Barbeiro implements IEntidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String nome;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date horarios;
	
	@Version
	@NotNull
	private Date version;

	public Barbeiro() {
		super();
	}

	public Barbeiro(int id, String nome, Date horarios) {
		super();
		this.id = id;
		this.nome = nome;
		this.horarios = horarios;
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

	public Date getHorarios() {
		return horarios;
	}

	public void setHorarios(Date horarios) {
		this.horarios = horarios;
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
