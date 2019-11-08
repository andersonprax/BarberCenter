package controller;

import java.util.List;

import dao.ClienteRepositorio;
import entidades.Cliente;

/**
 * 
 * @author pedro.silva
 *
 */
public class ControllerCliente {

	public boolean validarSalvarCliente(Cliente cliente) {
		boolean verificador = false;
		if (cliente.getCpf() != null 
				|| cliente.getNome() != null 
				|| cliente.getTelefone() != null
				|| cliente.getSenha() != null 
				|| cliente.getEmail() != null) 
			verificador = true;
		return verificador;
	}
	
	public void salvarCliente(Cliente cliente) {
		ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
		clienteRepositorio.salvar(cliente, true);
	}
	
	public List<Cliente> listarClientes(){
		ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
		return clienteRepositorio.listarTodos();
	}
}
