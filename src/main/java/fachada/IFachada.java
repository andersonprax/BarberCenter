package fachada;

import entidades.Cliente;

public interface IFachada {
	
	//Inicio metodos do cliente
	public boolean validarSalvarCliente(Cliente cliente);
	
	public void salvarCliente(Cliente cliente);
	//Fim Metodos Cliente

}
