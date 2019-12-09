package fachada;

import java.util.List;

import javax.faces.application.FacesMessage;

import entidades.Agendamento;
import entidades.Cliente;

/**
 * 
 * @author pedro.silva
 *
 */
public interface IFachada {
	//Metodos de Segurança
	
	//Metodo para criptografar Senha
	public String criptografarSenha(String senha);
	
	//Fim Metodos de Segurança
	
	//Inicio metodos do cliente
	public boolean validarSalvarCliente(Cliente cliente);
	
	public void salvarCliente(Cliente cliente);
	
	public void atulializarCliente(Cliente cliente);
	
	public void removerCliente(Cliente cliente);
	
	public Cliente buscarCliente(Cliente cliente, boolean b);
	
	public Cliente login(Cliente cliente, boolean b);
	
	public FacesMessage validarEmail(Cliente cliente);
	//Fim Metodos Cliente

	/* Inicio Metodos de Agendamento */
	
	public List<Agendamento> listarAgendamentos(Cliente cliente);
	
	/* Fim Metodos de Agendamento */
}
