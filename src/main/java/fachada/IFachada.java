package fachada;

import javax.faces.application.FacesMessage;

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
	
	public FacesMessage validarEmail(Cliente cliente);
	//Fim Metodos Cliente

}
