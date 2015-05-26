import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UsuarioLeilaoTest {

	private WebDriver driver;
	private UsuariosPage usuarios;

	@Before
	public void inicializa() {

		System.setProperty("webdriver.chrome.driver",
				"C:/Users/rogeriop/ALURA/chromedriver.exe");
		this.driver = new ChromeDriver();

		
//		this.driver = new FirefoxDriver();
		driver.get("http://localhost:8080/apenas-teste/limpa");
		
		this.usuarios = new UsuariosPage(driver);
	}


	@Test
	public void deveAdicionarUmUsuario() {

		usuarios.visita();
		usuarios.novo().cadastra("Adriano Xavier", "axavier@empresa.com.br");

		assertTrue(usuarios.existeNaListagem("Adriano Xavier",
				"axavier@empresa.com.br"));

	}

	@Test
	public void deveRejeitarUmUsuarioComNomeNaoPreenchido() {

		usuarios.visita();
		usuarios.novo().cadastra("", "axavier@empresa.com.br");

		assertTrue(usuarios.achouCritica("Nome obrigatorio!"));

	}

	@Test
	public void deveRejeitarUmUsuarioComNomeNaoPreenchidoEEmailNaoPreenchido() {

		usuarios.visita();
		usuarios.novo().cadastra("", "");

		assertTrue(usuarios.achouCritica("Nome obrigatorio!"));
		assertTrue(usuarios.achouCritica("E-mail obrigatorio!"));

	}
	
	@Test
	public void deveExcluirOUsuarioAdicionadoNesteTeste() {
		
		usuarios.visita();
		usuarios.novo().cadastra("Adriano Xavier", "axavier@empresa.com.br");
		usuarios.excluiUsuarioCadastradoNesteTeste();

		assertFalse(usuarios.existeNaListagem("Adriano Xavier",
				"axavier@empresa.com.br"));
		
		
	}
	
	@Test
	public void deveAlterarUmUsuario() {
		
		usuarios.visita();
		usuarios.novo().cadastra("Adriano Xavier", "axavier@empresa.com.br");
		usuarios.altera().altera("Denise Formosa", "dformosa@gmail.com");
		

		assertTrue(usuarios.existeNaListagem("Denise Formosa",
				"dformosa@gmail.com"));

	}
	

	@After
	public void finaliza() {
		driver.close();
	}
}
