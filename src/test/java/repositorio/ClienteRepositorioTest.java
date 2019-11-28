package repositorio;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.ClienteRepositorio;
import entidades.Cliente;

/**
 * 
 * @author pedro.silva
 *
 */
public class ClienteRepositorioTest {

	/**
	 * Metodo que faz o test de persistencia do cliente antes dos metodos com @Test
	 */
	@BeforeClass
	public static void testSalvar() {
		Cliente c = new Cliente();
		c.setCpf("1354984651");
		c.setNome("Pedro");
		c.setEmail("silva@asda.com");
		c.setSenha("123123");
		c.setTelefone("9815619");

		try {
			ClienteRepositorio cr = new ClienteRepositorio();
			cr.salvar(c, false);
			assertEquals(c, cr.buscarCliente(c, true));
		} catch (Exception e) {
			fail("Falha ao Salvar! " + e.getMessage());
		}
	}

	/**
	 * Metodo que tenta atualizar os dados do cliente que foram
	 * persistidos atraves do metodo @BeforeClass "testSalvar"
	 */
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
			assertEquals("Fernando", cr.buscarCliente(c, true).getNome());
		} catch (Exception e) {
			fail("Falha ao Atulizar Cliente " + e.getMessage());
		}
	}

	/**
	 * Metodo que testa o find e tenta localizar os dados do cliente persistido
	 */
	@Test
	public void testEncontrar() {
		Cliente c = new Cliente();
		ClienteRepositorio cr = new ClienteRepositorio();
		c.setCpf("1354984651");

		try {
			Cliente cliente = new Cliente();
			cliente = cr.buscarCliente(c, true);
			assertNotNull(cliente);
		} catch (Exception e) {
			fail("Objeto não encontrado! " + e.getMessage());
		}
	}

	/**
	 * Metodo que e excutado depois de todos os testes. 
	 * Testa a deleação e remove o cliente passado por parametro.
	 */
	@AfterClass
	public static void testRemover() {
		Cliente c = new Cliente();
		ClienteRepositorio cr = new ClienteRepositorio();
		c.setCpf("1354984651");
		try {
			cr.removerCliente(c);
			assertNull(cr.buscarCliente(c, true));
		} catch (Exception e) {
			fail("Falha ao deletar cliente " + e.getMessage());
		}
	}
}