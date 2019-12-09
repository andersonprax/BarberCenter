package fachadaDao;

import java.util.List;
import dao.AgendamentoRepositorio;
import dao.ClienteRepositorio;
import entidades.Agendamento;
import entidades.Cliente;

/**
 * Fachada do padrao MVC entre o Controller e o Repositorio DAO.
 * @author pedro.silva
 *
 */
public class FachadaDaoImpl implements IFachadaDao{
	
	private static FachadaDaoImpl instancia = new FachadaDaoImpl();
	/**
	 * @return instancia
	 */
	public static FachadaDaoImpl getInstacia(){
		if(instancia==null)
			instancia = new FachadaDaoImpl();
		return instancia;
	}

	/* Inicio Metodos Cliente */
	
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
	 * Metodo que busca um unico registro de um cliente na base
	 * @param cliente
	 * @param b True para fechar a conexao e false para deixar aberta
	 * @return Cliente
	 */
	public Cliente buscarCliente(Cliente cliente, boolean b) {
		return new ClienteRepositorio().buscarCliente(cliente, b);
	}

	/**
	 * Metodo que retorna o cliente a partir do email e senha fornecia na tela de login
	 * @param cliente
	 * @param b True para fechar conexao e false para nao
	 * @return Cliente
	 */
	public Cliente loginCliente(Cliente cliente, boolean b) {
		return new ClienteRepositorio().login(cliente, b);
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
	
	/* Fim Metodos Cliente */
	
	/* Inicio metodos de Agendamentos */
	
	/**
	 * Metodo que lista todos os agendamentos de um determinado cliente.
	 * 
	 * @return List<Agendamento>
	 */
	public List<Agendamento> listarAgendamentosPorCliente(String cpf){
		return new AgendamentoRepositorio().consultarPorCliente(cpf);
	}
	
	
	
	/* Fim Metodos de Agendamentos*/
}
