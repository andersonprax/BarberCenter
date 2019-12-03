package fachadaDao;

import java.util.List;

import entidades.Cliente;
/**
 * 
 * @author pedro.silva
 *
 */
public interface IFachadaDao {

	/* Inicio Metodos Cliente */
	
	public void salvarCliente(Cliente cliente);
	
	public void atualizarCliente(Cliente cliente);
	
	public void removerCliente(Cliente cliente);
	
	public Cliente buscarCliente(Cliente cliente, boolean b);
	
	public Cliente loginCliente(Cliente cliente, boolean b);
	
	public List<Cliente> listarClientes();
	
	/* Fim Metodos Cliente */
}
