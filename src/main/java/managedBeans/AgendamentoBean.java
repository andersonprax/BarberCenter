package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import entidades.Agendamento;
import entidades.Cliente;
import entidades.Servicos;
import fachada.Fachada;

@ManagedBean(name = "agendamentoBean")
@SessionScoped
public class AgendamentoBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Agendamento> listaAgendamentos;
	public Cliente cliente;
	public Servicos servicos;
	public Agendamento agendamento;
	
	
	public AgendamentoBean() {
		this.cliente = new Cliente();
		this.init();
		this.servicos = new Servicos();
		this.agendamento =  new Agendamento();
		this.listaAgendamentos = new ArrayList<Agendamento>();
		
	}
	
	@PostConstruct
	public void init() {
		//Aqui Recupero o Cliente salvo no cliente Bean
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		//Aqui Seto o cliente no objeto
        this.cliente = (Cliente) ec.getRequestMap().get("clienteAgendamento");     
        listaAgendamentos = Fachada.getInstancia().listarAgendamentos(cliente);
	}
	
	/**
	 * Metodo que lista todos os agendamentoe de um cliente logado no sistema;
	 * @param cliente
	 * @return List<Agendamento>
	 */
	public List<Agendamento> listarAgendamentos(){
		//Tentativa de Forçar a recupeção do cliente abaixo.
		//Aqui Recupero o Cliente salvo no cliente Bean
		ExternalContext ecContext = FacesContext.getCurrentInstance().getExternalContext();
		//Aqui Seto o cliente no objeto
        this.cliente = (Cliente) ecContext.getRequestMap().get("clienteAgendamento");
		listaAgendamentos = Fachada.getInstancia().listarAgendamentos(this.cliente);
		return listaAgendamentos;
	}


	/**
	 * @return the listaAgendamentos
	 */
	public List<Agendamento> getListaAgendamentos() {
		return listaAgendamentos;
	}


	/**
	 * @param listaAgendamentos the listaAgendamentos to set
	 */
	public void setListaAgendamentos(List<Agendamento> listaAgendamentos) {
		this.listaAgendamentos = listaAgendamentos;
	}


	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}


	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	/**
	 * @return the servicos
	 */
	public Servicos getServicos() {
		return servicos;
	}


	/**
	 * @param servicos the servicos to set
	 */
	public void setServicos(Servicos servicos) {
		this.servicos = servicos;
	}


	/**
	 * @return the agendamento
	 */
	public Agendamento getAgendamento() {
		return agendamento;
	}


	/**
	 * @param agendamento the agendamento to set
	 */
	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
}

