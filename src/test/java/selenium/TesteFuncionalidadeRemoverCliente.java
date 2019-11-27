package selenium;

import static org.junit.Assert.assertNull;
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

public class TesteFuncionalidadeRemoverCliente {

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
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\silva\\Documents\\Eclipse Projects\\BarberCenter\\src\\main\\webapp\\lib\\chromedriver.exe");
		// Abrindo o navegador
		driver = new ChromeDriver();
		// Preenchendo objeto que será persistido pelo selenium
		cliente.setCpf("00776685458");
		cliente.setNome("Pedro Leo");
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
	 * metodo que faz a deleção do cliente cadastrado Este teste dará erro pois o
	 * JSF não está reconhecendo o metodo REMOVER no managedBean Cliente
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testRemoverCliente() throws InterruptedException, NullPointerException {
		
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
		
		// Aqui Preencho os campos de Senha
		element = driver.findElement(By.id("formPrincipal:senha"));
		element.sendKeys(cliente.getSenha());
		// Aqui preencho os campos de Senha
		element = driver.findElement(By.id("formPrincipal:confirmarSenha"));
		element.sendKeys(cliente.getSenha());
		//Aqui clico no botao Excluir
		element = driver.findElement(By.id("formPrincipal:j_idt19"));
		element.click();
		// Aqui pego o botão SIM para excluir
		element = driver.findElement(By.id("formPrincipal:j_idt21"));
		//Aqui dou um tempo
		Thread.sleep(2500);		
		// Aqui faço o clique no botão
		element.click();
		Thread.sleep(3000);
		try {
			assertNull(new Fachada().buscarCliente(cliente, true));
			Thread.sleep(2000);
		} catch (NullPointerException e) {
			fail("Cliente Não Removido!");
		}
	}
}
