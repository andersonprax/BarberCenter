package managedBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.AgendamentoRepositorio;
import dao.BarbeariaRepositorio;
import dao.ServicoRepositorio;
import entidades.Agendamento;
import entidades.Barbearia;
import entidades.Cliente;
import entidades.Servicos;

@ManagedBean
@ViewScoped
public class BarbeariaBeans implements Serializable{
	private static final long serialVersionUID = 1L;
	
	int idBarberSelected;
	Date data;
	Barbearia barbearia = new Barbearia();
	BarbeariaRepositorio barbeariaRepositorio = new BarbeariaRepositorio();
	ServicoRepositorio servicoRepositorio = new ServicoRepositorio();
	AgendamentoRepositorio agendamentoRepositorio = new AgendamentoRepositorio();
	Servicos servico = new Servicos();
	List<Barbearia> barbearias;
	List<Servicos> servicos, servicosSelecionados;
	
	public BarbeariaBeans() {
	
	}
	
	@PostConstruct
	public void init() {
		try {
			barbearias = barbeariaRepositorio.consultarTodasBarbearias();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void carregarServicosPorBarbearia(Barbearia barbearia) {
		idBarberSelected = (Integer) barbearia.getPrimaryKey();
		this.barbearia = barbearia;
		try {
			servicos = servicoRepositorio.consultarServicosPorBarbearia(idBarberSelected);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void agendar(Cliente cliente) {
		
		try {
			Date d = new Date();
			Agendamento agendamento = new Agendamento();
			agendamento.setBarbearia(this.barbearia);
			agendamento.setCliente(cliente);
			agendamento.setServicos(servicosSelecionados);
			agendamento.setStatus(10);
			agendamento.setVersion(d);
			agendamento.setDate(data);
			
			agendamentoRepositorio.salvar(agendamento, false);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Barbearia> getListaBarbearias(){
		return barbearias;
	}

	public Barbearia getBarbearia() {
		return barbearia;
	}

	public void setBarbearia(Barbearia barbearia) {
		this.barbearia = barbearia;
	}

	public BarbeariaRepositorio getBarbeariaRepositorio() {
		return barbeariaRepositorio;
	}

	public void setBarbeariaRepositorio(BarbeariaRepositorio barbeariaRepositorio) {
		this.barbeariaRepositorio = barbeariaRepositorio;
	}

	public List<Barbearia> getBarbearias() {
		return barbearias;
	}

	public void setBarbearias(List<Barbearia> barbearias) {
		this.barbearias = barbearias;
	}

	public ServicoRepositorio getServicoRepositorio() {
		return servicoRepositorio;
	}

	public void setServicoRepositorio(ServicoRepositorio servicoRepositorio) {
		this.servicoRepositorio = servicoRepositorio;
	}

	public List<Servicos> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servicos> servicos) {
		this.servicos = servicos;
	}

	public Servicos getServico() {
		return servico;
	}

	public void setServico(Servicos servico) {
		this.servico = servico;
	}

	public int getIdBarberSelected() {
		return idBarberSelected;
	}

	public void setIdBarberSelected(int idBarberSelected) {
		this.idBarberSelected = idBarberSelected;
	}

	public List<Servicos> getServicosSelecionados() {
		return servicosSelecionados;
	}

	public void setServicosSelecionados(List<Servicos> servicosSelecionados) {
		this.servicosSelecionados = servicosSelecionados;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}
