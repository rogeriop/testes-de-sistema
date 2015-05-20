import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UsuarioLeilaoTest {

	private WebDriver driver;
	private UsuariosPage usuarios;

	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
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
		usuarios.excluiUsuarioCadastradoNesteTeste();

		assertFalse(usuarios.existeNaListagem("Adriano Xavier",
				"axavier@empresa.com.br"));
		
		
	}
	

	@After
	public void finaliza() {
		driver.close();
	}
}
