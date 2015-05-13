import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UsuarioLeilaoTest {
	
	private WebDriver driver;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
	}
	
	@Test
	public void verificaFuncionamentoDoLinkNovoUsuario() {
		
		driver.get("http://localhost:8080/usuarios");
		
		WebElement linkNovoUsuario = driver.findElement(By.linkText("Novo Usuário"));
		linkNovoUsuario.click();

		WebElement nome = driver.findElement(By.name("usuario.nome"));
		WebElement email = driver.findElement(By.name("usuario.email"));
		
		nome.sendKeys("Adriano Xavier");
		email.sendKeys("axavier@empresa.com.br");
		
		WebElement botaoSalvar = driver.findElement(By.id("btnSalvar"));
		botaoSalvar.click();

		boolean achouNome = driver.getPageSource().contains("Adriano Xavier");
		boolean achouEmail = driver.getPageSource().contains("axavier@empresa.com.br");
		
		assertTrue(achouNome);
		assertTrue(achouEmail);

	}
	
	@Test
	public void deveAdicionarUmUsuario() {
		
		driver.get("http://localhost:8080/usuarios/new");
		
		WebElement nome = driver.findElement(By.name("usuario.nome"));
		WebElement email = driver.findElement(By.name("usuario.email"));
		
		nome.sendKeys("Adriano Xavier");
		email.sendKeys("axavier@empresa.com.br");
		
		WebElement botaoSalvar = driver.findElement(By.id("btnSalvar"));
		botaoSalvar.click();
		
		boolean achouNome = driver.getPageSource().contains("Adriano Xavier");
		boolean achouEmail = driver.getPageSource().contains("axavier@empresa.com.br");
		
		assertTrue(achouNome);
		assertTrue(achouEmail);
		
	}

	@Test
	public void deveRejeitarUmUsuarioComNomeNaoPreenchido() {
		
		driver.get("http://localhost:8080/usuarios/new");
		
		WebElement nome = driver.findElement(By.name("usuario.nome"));
		WebElement email = driver.findElement(By.name("usuario.email"));
		
		nome.sendKeys("");
		email.sendKeys("axavier@empresa.com.br");
		
		WebElement botaoSalvar = driver.findElement(By.id("btnSalvar"));
		botaoSalvar.click();
		
		boolean naoAchouNome = driver.getPageSource().contains("Nome obrigatorio!");
		
		assertTrue(naoAchouNome);

	}

	@Test
	public void deveRejeitarUmUsuarioComNomeNaoPreenchidoEEmailNaoPreenchido() {
		
		driver.get("http://localhost:8080/usuarios/new");
		
		WebElement nome = driver.findElement(By.name("usuario.nome"));
		WebElement email = driver.findElement(By.name("usuario.email"));
		
		nome.sendKeys("");
		email.sendKeys("");
		
		WebElement botaoSalvar = driver.findElement(By.id("btnSalvar"));
		botaoSalvar.click();
		
		boolean naoAchouNome = driver.getPageSource().contains("Nome obrigatorio!");
		boolean naoAchouEmail = driver.getPageSource().contains("E-mail obrigatorio!");
		
		assertTrue(naoAchouNome);
		assertTrue(naoAchouEmail);
		
	}
	
	@After
	public void finaliza() {
		driver.close();
	}
}
