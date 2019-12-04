package controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import dao.ConstantesSistema;
import entidades.Cliente;
import fachadaDao.FachadaDaoImpl;

/**
 * 
 * @author pedro.silva
 *
 */
public class ControllerCliente {

	/**
	 * Metodos que pega o obj cliente e faz validação
	 * 
	 * @param cliente
	 * @return True or False
	 */
	public boolean validarSalvarCliente(Cliente cliente) {
		boolean verificador = false;
		if (cliente.getCpf() != null || cliente.getNome() != null || cliente.getTelefone() != null
				|| cliente.getSenha() != null || cliente.getEmail() != null) {
			verificador = true;
		}
		return verificador;
	}

	/**
	 * Metodo para validar o endereço de email digitado pelo usuário.
	 * @param cliente
	 * @return FacesMessage
	 */
	public FacesMessage validarEmail(Cliente cliente) {
		FacesMessage message = null;

		if (cliente.getEmail() != null && cliente.getEmail().length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(cliente.getEmail());
			if (matcher.matches())
				message = null;
			else
				message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						cliente.getEmail() + " " + ConstantesSistema.EMAIL_OU_SENHA_INVALIDO, "");
		}
		return message;
	}

	/**
	 * Metodo que pega o obj cliente e faz a persistencia no sgbd
	 * 
	 * @param cliente
	 */
	public void salvarCliente(Cliente cliente) {
		FachadaDaoImpl.getInstacia().salvarCliente(cliente);
	}
	
	/**
	 * Metodo para atualizar um registro de um cliente na base
	 * @param cliente
	 */
	public void atualizarCliente(Cliente cliente) {
		FachadaDaoImpl.getInstacia().atualizarCliente(cliente);
	}

	/**
	 * Metodo que faz a deleção do cliente da Base
	 * @param cliente
	 */
	public void removerCliente(Cliente cliente) {
		FachadaDaoImpl.getInstacia().removerCliente(cliente);
	}
	
	/**
	 * Metodo que busca um unico registro de um cliente na base
	 * @param cliente
	 * @param b True para fechar a conexao e false para deixar aberta
	 * @return Cliente
	 */
	public Cliente buscarCliente(Cliente cliente, boolean b) {
		return FachadaDaoImpl.getInstacia().buscarCliente(cliente, b);
	}
	
	/**
	 * Metodo que retorna o cliente a partir do email e senha fornecia na tela de login
	 * @param cliente
	 * @param b True para fechar conexao e false para nao
	 * @return Cliente
	 */
	public Cliente login(Cliente cliente, boolean b) {
		return FachadaDaoImpl.getInstacia().loginCliente(cliente, b);
	}
	/**
	 * Metodo que lista todos os clientes cadastrado no sgbd
	 * 
	 * @return List<Cliente>
	 */
	public List<Cliente> listarClientes() {
		return FachadaDaoImpl.getInstacia().listarClientes();
	}
}
