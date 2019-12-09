package controllerIntegracao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import controller.ControllerAgendamento;
import entidades.Agendamento;
import entidades.Cliente;

public class ControllerAgendamentoTest {

	Cliente cliente = new Cliente();

	@Test
	public void testListarAgendamentos() {
		/* Arrange */
		cliente.setCpf("984.846.848-22");
		List<Agendamento> lista = new ArrayList<Agendamento>();

		/* ACT */
		try {
			ControllerAgendamento agendamento = new ControllerAgendamento();
			
			lista = agendamento.listarAgendamentos(cliente);
			
			/* ASSERT */
			assertNotNull(lista);
		} catch (Exception e) {
			fail("Falhou Em Trazer a Lista De Agendamentos!");
		}
	}
}
