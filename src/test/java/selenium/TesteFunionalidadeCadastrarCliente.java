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
	 * Metodo que abre a pagina de loing, clica em cadastrar. Faz o novo cadastro do
	 * cliente.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testCadastrarCliente() throws InterruptedException {
		// Abrindo a página inicial
		driver.get("localhost:8080/BarberCenter/loginCliente.xhtml");
		// Dando um tempo para que a página esteja pronta para receber o click no botao.
		Thread.sleep(2000);
		// Faço o clique no botão.
		driver.findElement(By.id("j_idt3:btnCadastrar")).click();
		// Aqui dou mais um tempo para que a próxima pag e seu elementos sejam
		// carregados
		Thread.sleep(5000);
		// Aqui começo a pegar os camos pelos ids
		WebElement element = driver.findElement(By.id("j_idt4:nome"));
		// Aqui prencho o campo com o valor ja determinado no objeto.
		element.sendKeys(cliente.getNome());
		// Aqui começo a pegar os camos pelos ids
		element = driver.findElement(By.id("j_idt4:cpf"));
		// Faço um clique para que preencha no inicio, e não pule casas no campo.
		element.click();
		// Aqui prencho o campo com o valor ja determinado no objeto.
		element.sendKeys(cliente.getCpf());
		// Aqui seto o cpf com a máscara que a tela irá adionar ao salvar no banco
		cliente.setCpf("007.766.854-58");
		// Mesma situação dos comentários acima
		element = driver.findElement(By.id("j_idt4:telefone"));
		element.click();
		element.sendKeys(cliente.getTelefone());
		// Mesma situação dos comentários acima
		element = driver.findElement(By.id("j_idt4:email"));
		element.sendKeys(cliente.getEmail());
		// Mesma situação dos comentários acima
		element = driver.findElement(By.id("j_idt4:senha"));
		element.sendKeys(cliente.getSenha());
		// Mesma situação dos comentários acima
		element = driver.findElement(By.id("j_idt4:confirmarSenha"));
		element.sendKeys(cliente.getSenha());
		// Aqui encontro o botao cadastrar
		element = driver.findElement(By.id("j_idt4:cadastrar"));
		// Aqui faço o clique no botão
		element.click();
		Thread.sleep(5000);
		try {
			// Aqui faço a busca na base pelo cliente já cadastrado acima. Se retornar null,
			// o cliente não foi cadastrado
			assertNotNull(new Fachada().buscarCliente(cliente, true));
			Thread.sleep(5000);
		} catch (Exception e) {
			fail("Não houve o cadastro do cliente");
		}
	}
}
