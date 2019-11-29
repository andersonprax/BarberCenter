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

import dao.ConstantesSistema;
import entidades.Cliente;
import fachada.Fachada;

/**
 * 
 * @author pedro.silva
 *
 */
public class TesteFuncionalidadeAtualizarCliente {

	// Atributos de inicialização
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
		System.setProperty(ConstantesSistema.CHORME_DRIVER_WEB, new FindChromeDriver().getChormeDriverLocation());
		// Abrindo o navegador
		driver = new ChromeDriver();
		// Preenchendo objeto que será persistido pelo selenium
		cliente.setCpf("00776685458");
		cliente.setNome("Pedro Leonardo");
		cliente.setEmail("pedro@unibratec.com.br");
		cliente.setTelefone("81954883322");
		cliente.setSenha("123");
	}

	/**
	 * Metodo que mata o driver do selenium após o final da execução dos testes
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if (driver != null)
			driver.quit();
	}

	/**
	 * Metodo que faz o teste para atualizar os dados do cliente.
	 * Abre o navegador, faz login e na pag de perfil altera o nome e salva na base
	 * @throws InterruptedException
	 */
	@Test
	public void testarAtualizarCliente() throws InterruptedException {

		/* Arrange */
		
		// Abrindo a página inicial
		driver.get("localhost:8080/BarberCenter/loginCliente.xhtml");
		// Dando um tempo para que a página esteja pronta para receber o click no botao.
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
		
		// Aqui clico o botão para atualizar.
		element = driver.findElement(By.id("formPrincipal:atualizar"));
		Thread.sleep(3000);
		element.click();
		// Aguardo 1 seg
		Thread.sleep(5000);
		
		/* Assert */
		
		try {
			//Colocando a mascara conforme esta na tela e no banco
			cliente.setCpf("007.766.854-58");
			assertEquals(cliente.getNome(), new Fachada().buscarCliente(cliente, true).getNome());
		} catch (Exception e) {
			fail("Clienta Não Atualizado!"+ e.getMessage());
		}
	}
}
