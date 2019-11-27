package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import entidades.Cliente;
import fachada.Fachada;

public class TesteFuncionalidadeAtualizarCliente {

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
		cliente.setNome("Pedro Leonardo");
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

	@Test
	public void testarAtualizarCliente() throws InterruptedException {

		/* Arrange */
		// Abrindo a p�gina inicial
		driver.get("localhost:8080/BarberCenter/loginCliente.xhtml");
		// Dando um tempo para que a p�gina esteja pronta para receber o click no botao.
		Thread.sleep(2000);
		// Aqui Preencho Login e Senha
		WebElement element = driver.findElement(By.id("j_idt3:email"));
		element.sendKeys(cliente.getEmail());
		// Aqui Preencho os campos de Senha
		element = driver.findElement(By.id("j_idt3:senha"));
		element.sendKeys(cliente.getSenha());
		Thread.sleep(2000);
		// Aqui clino no botao logar
		element = driver.findElement(By.id("j_idt3:logar"));
		element.click();
		// Aqui Dou um tempo
		Thread.sleep(3000);
		//Atualizo o nome
		element = driver.findElement(By.id("formPrincipal:nome"));
		element.clear();
		element.sendKeys(cliente.getNome());
		// Aqui Preencho os campos de Senha
		element = driver.findElement(By.id("formPrincipal:senha"));
		element.sendKeys(cliente.getSenha());
		// Aqui preencho os campos de Senha
		element = driver.findElement(By.id("formPrincipal:confirmarSenha"));
		element.sendKeys(cliente.getSenha());
	
		/* ACT */
		// Aqui clico o bot�o para atualizar.
		element = driver.findElement(By.id("formPrincipal:atualizar"));
		Thread.sleep(3000);
		element.click();
		// Aguardo 1 seg
		Thread.sleep(5000);
		try {
			//Colocando a mascara conforme esta na tela e no banco
			cliente.setCpf("007.766.854-58");
			/* Assert */
			assertEquals(cliente.getNome(), new Fachada().buscarCliente(cliente, true).getNome());
		} catch (Exception e) {
			fail("Clienta N�o Atualizado!"+ e.getMessage());
		}

	}
}
