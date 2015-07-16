import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;


public class LanceSystemTest {

	
	private FirefoxDriver driver;
	private LeiloesPage leiloes;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		leiloes = new LeiloesPage(driver);
		
		// cen�rio padr�o
		driver.get("http://localhost:8080/apenas-teste/limpa");
		
		UsuariosPage usuarios = new UsuariosPage(driver);
		usuarios.visita();
		usuarios.novo().cadastra("Paulo Henrique", "paulo@henrique.com");
		usuarios.novo().cadastra("Jose Eduardo", "jose@eduardo.com");
		
		LeiloesPage leiloes = new LeiloesPage(driver);
		leiloes.visita();
		leiloes.novo().preenche("Geladeira", 100, false, "Paulo Henrique");
	}
	@Test
	public void deveFazerUmLance() {
		DetalhesDoLeilaoPage lances = leiloes.detalhes(1);
		
		lances.lance("Jos� Eduardo", 150);
		
		assertTrue(lances.existeLance("Jos� Eduardo", 150));
		
	}
	
	
}
