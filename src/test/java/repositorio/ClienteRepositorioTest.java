package repositorio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import dao.ClienteRepositorio;
import entidades.Cliente;

public class ClienteRepositorioTest {

	@Test
	@Ignore
	public void testSalvar() {
		Cliente c = new Cliente();
		c.setCpf("1354984651");
		c.setNome("Pedro");
		c.setEmail("silva@asda.com");
		c.setSenha("123123");
		c.setTelefone("9815619");

		try {
			ClienteRepositorio cr = new ClienteRepositorio();
			cr.salvar(c, false);

			assertEquals(c, cr.buscarCliente(c,true));
		} catch (Exception e) {
			fail("Falha ao Salvar! "+ e.getMessage());
		}
	}

	@Test
	public void testAtualizar() {
		Cliente c = new Cliente();
		ClienteRepositorio cr = new ClienteRepositorio();
		c.setCpf("1354984651");
		c = cr.buscarCliente(c, true);
		c.setNome("Fernando");
		c.setEmail("Fernando@asda.com");
		c.setSenha("1654823");
		c.setTelefone("9654861");
		
		try {
			cr.atualizar(c, true);
			
			assertEquals("Fernando",cr.buscarCliente(c, true).getNome());
		} catch (Exception e) {
			fail("Falha ao Atulizar Cliente "+ e.getMessage() );
		}
	}

	@Test
	@Ignore
	public void testEncontrar() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testRemover() {
		fail("Not yet implemented");
	}

}
