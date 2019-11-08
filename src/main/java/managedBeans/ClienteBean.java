package managedBeans;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import dao.ConstantesSistema;
import entidades.Cliente;
import fachada.Fachada;

/**
 * 
 * @author pedro.silva
 *
 */
@ManagedBean
public class ClienteBean {

	private Cliente cliente;
	private List<Cliente> listaClientes;
	private String confirmarSenha;

	public ClienteBean() {
		this.cliente = new Cliente();
	}

	public void validarSalvar() {

		if (Fachada.getInstancia().validarSalvarCliente(cliente) == true)
			salvarCliente(cliente);
		else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,ConstantesSistema.ERROR,""));
	}

	public void salvarCliente(Cliente cliente) {
		try {
			Fachada.getInstancia().salvarCliente(cliente);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,ConstantesSistema.SALVO_SUCESSO,""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public List<Cliente> listarClientes(){
		return Fachada.getInstancia().listarClientes();
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
	 * @return the listaClientes
	 */
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	/**
	 * @param listaClientes the listaClientes to set
	 */
	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	/**
	 * @return the confirmarSenha
	 */
	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	/**
	 * @param confirmarSenha the confirmarSenha to set
	 */
	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

}
