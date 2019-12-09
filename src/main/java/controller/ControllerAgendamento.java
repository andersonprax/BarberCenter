package controller;

import java.util.List;

import entidades.Agendamento;
import entidades.Cliente;
import fachadaDao.FachadaDaoImpl;

public class ControllerAgendamento {

	/**
	 * Metodo que recebe o obj cliente por paramento e retona a lista de
	 * agendamentos desse cliente
	 * 
	 * @param cliente
	 * @return List<Agendamento>
	 */
	public List<Agendamento> listarAgendamentos(Cliente cliente) {
		if (cliente != null)
			return FachadaDaoImpl.getInstacia().listarAgendamentosPorCliente(cliente.getCpf());
		else
			return null;
	}
}
