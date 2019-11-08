package fachada;

import java.util.List;

import controller.ControllerCliente;
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
	
	//Inicio metodos Clinte
	public boolean validarSalvarCliente(Cliente cliente) {
		return new ControllerCliente().validarSalvarCliente(cliente);
	}
	
	public void salvarCliente(Cliente cliente) {
		new ControllerCliente().salvarCliente(cliente);
	}
	
	public List<Cliente> listarClientes(){
		return new ControllerCliente().listarClientes();
	}
	
	//Fim Metodos Cliente
}
