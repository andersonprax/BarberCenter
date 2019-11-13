package controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import dao.ClienteRepositorio;
import dao.ConstantesSistema;
import entidades.Cliente;

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
						cliente.getEmail() + " " + ConstantesSistema.EMAIL_INVALIDO, "");
		}
		return message;
	}

	/**
	 * Metodo que pega o obj cliente e faz a persistencia no sgbd
	 * 
	 * @param cliente
	 */
	public void salvarCliente(Cliente cliente) {
		ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
		clienteRepositorio.salvar(cliente, true);
	}
	
	/**
	 * Metodo para atualizar um registro de um cliente na base
	 * @param cliente
	 */
	public void atualizarCliente(Cliente cliente) {
		new ClienteRepositorio().atualizar(cliente, true);
	}

	/**
	 * Metodo que faz a deleção do cliente da Base
	 * @param cliente
	 */
	public void removerCliente(Cliente cliente) {
		new ClienteRepositorio().removerCliente(cliente);
	}
	/**
	 * Metodo que lista todos os clientes cadastrado no sgbd
	 * 
	 * @return List<Cliente>
	 */
	public List<Cliente> listarClientes() {
		ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
		return clienteRepositorio.listarTodos();
	}
}
