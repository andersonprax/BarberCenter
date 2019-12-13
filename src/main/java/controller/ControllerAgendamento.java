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
	
	public boolean validarSalvarAgendamento(Agendamento agendamento) {
		boolean verificador = false;
		
		if(!agendamento.getBarbearia().equals(null) && !agendamento.getCliente().equals(null) && !agendamento.getServicos().equals(null)
				&& !agendamento.getDate().equals(null)) {
			verificador = true;
		}
		return verificador;
	}
	
	public void salvarAgendamento(Agendamento agendamento) {
		FachadaDaoImpl.getInstacia().salvarAgendamento(agendamento);
	}
}

