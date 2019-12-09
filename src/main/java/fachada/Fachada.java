package fachada;

import java.util.List;

import javax.faces.application.FacesMessage;

import controller.ControllerAgendamento;
import controller.ControllerCliente;
import controller.ControllerSeguranca;
import entidades.Agendamento;
import entidades.Cliente;

/**
 * 
 * @author pedro.silva
 *
 */
public class Fachada implements IFachada {

	private static Fachada instancia = new Fachada();
	/**
	 * @return the instancia
	 */
	public static Fachada getInstancia() {
		if(instancia == null)
			instancia = new Fachada();
		return instancia;
	}
	/**
	 * @param instancia the instancia to set
	 */
	public static void setInstancia(Fachada instancia) {
		Fachada.instancia = instancia;
	}
	//Metodos de segurança
	
	public String criptografarSenha(String senha) {
		return new ControllerSeguranca().criptografarSenha(senha);
	}
	
	//Fim metodos de segurança	
	
	//Inicio metodos Clinte
	
	public boolean validarSalvarCliente(Cliente cliente) {
		return new ControllerCliente().validarSalvarCliente(cliente);
	}
	
	public void salvarCliente(Cliente cliente) {
		new ControllerCliente().salvarCliente(cliente);
	}
	
	public void atulializarCliente(Cliente cliente) {
		new ControllerCliente().atualizarCliente(cliente);
	}
	
	public void removerCliente(Cliente cliente) {
		new ControllerCliente().removerCliente(cliente);
	}
	
	public Cliente buscarCliente(Cliente cliente, boolean b) {
		return new ControllerCliente().buscarCliente(cliente, b);
	}
	
	public Cliente login(Cliente cliente, boolean b) {
		return new ControllerCliente().login(cliente, b);
	}
	
	public List<Cliente> listarClientes(){
		return new ControllerCliente().listarClientes();
	}
	
	public FacesMessage validarEmail(Cliente cliente) {
		return new ControllerCliente().validarEmail(cliente);
	}
	//Fim Metodos Cliente
	
	/* Inicio Metodos de Agendamento */
	
	public List<Agendamento> listarAgendamentos(Cliente cliente){
		return new ControllerAgendamento().listarAgendamentos(cliente);
	}
	
	/* Fim Metodos de Agendamento */
}
