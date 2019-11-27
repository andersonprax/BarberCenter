package selenium;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import entidades.Cliente;
import fachada.Fachada;

public class TesteFunionalidadeCadastrarCliente {

	// Atributos de inicializa��o
	private static WebDriver driver;
	public final Cliente cliente = new Cliente();

	/**
	 * Metodo que prepara o Selenium para ser usado.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUpBeforeClass() throws Exception {
		// Setando as propriedades do driver do nagegador a ser usado. E indicando o
		// local do driver
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\silva\\Documents\\Eclipse Projects\\BarberCenter\\src\\main\\webapp\\lib\\chromedriver.exe");
		// Abrindo o navegador
		driver = new ChromeDriver();
		// Preenchendo objeto que ser� persistido pelo selenium
		cliente.setCpf("00776685458");
		cliente.setNome("Pedro Leo");
		cliente.setEmail("pedro@unibratec.com.br");
		cliente.setTelefone("81954883322");
		cliente.setSenha("123");
	}

	/**
	 * Metodo que mata o driver do selenium ap�s o final da execu��o dos testes
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if (driver != null)
			driver.quit();
	}

	/**
	 * Metodo que abre a pagina de loing, clica em cadastrar. Faz o novo cadastro do
	 * cliente.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testCadastrarCliente() throws InterruptedException {
		// Abrindo a p�gina inicial
		driver.get("localhost:8080/BarberCenter/loginCliente.xhtml");
		// Dando um tempo para que a p�gina esteja pronta para receber o click no botao.
		Thread.sleep(2000);
		// Fa�o o clique no bot�o.
		driver.findElement(By.id("j_idt3:btnCadastrar")).click();
		// Aqui dou mais um tempo para que a pr�xima pag e seu elementos sejam
		// carregados
		Thread.sleep(5000);
		// Aqui come�o a pegar os camos pelos ids
		WebElement element = driver.findElement(By.id("j_idt4:nome"));
		// Aqui prencho o campo com o valor ja determinado no objeto.
		element.sendKeys(cliente.getNome());
		// Aqui come�o a pegar os camos pelos ids
		element = driver.findElement(By.id("j_idt4:cpf"));
		// Fa�o um clique para que preencha no inicio, e n�o pule casas no campo.
		element.click();
		// Aqui prencho o campo com o valor ja determinado no objeto.
		element.sendKeys(cliente.getCpf());
		// Aqui seto o cpf com a m�scara que a tela ir� adionar ao salvar no banco
		cliente.setCpf("007.766.854-58");
		// Mesma situa��o dos coment�rios acima
		element = driver.findElement(By.id("j_idt4:telefone"));
		element.click();
		element.sendKeys(cliente.getTelefone());
		// Mesma situa��o dos coment�rios acima
		element = driver.findElement(By.id("j_idt4:email"));
		element.sendKeys(cliente.getEmail());
		// Mesma situa��o dos coment�rios acima
		element = driver.findElement(By.id("j_idt4:senha"));
		element.sendKeys(cliente.getSenha());
		// Mesma situa��o dos coment�rios acima
		element = driver.findElement(By.id("j_idt4:confirmarSenha"));
		element.sendKeys(cliente.getSenha());
		// Aqui encontro o botao cadastrar
		element = driver.findElement(By.id("j_idt4:cadastrar"));
		// Aqui fa�o o clique no bot�o
		element.click();
		Thread.sleep(5000);
		try {
			// Aqui fa�o a busca na base pelo cliente j� cadastrado acima. Se retornar null,
			// o cliente n�o foi cadastrado
			assertNotNull(new Fachada().buscarCliente(cliente, true));
			Thread.sleep(5000);
		} catch (Exception e) {
			fail("N�o houve o cadastro do cliente");
		}
	}
}
