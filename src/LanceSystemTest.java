import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class LanceSystemTest {

	
//	private FirefoxDriver driver;
	private WebDriver driver;
	private LeiloesPage leiloes;
	
	@Before
	public void inicializa() {
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/rogeriop/ALURA/chromedriver.exe");
		this.driver = new ChromeDriver();
		
		leiloes = new LeiloesPage(driver);
		
		// cenário padrão
		driver.get("http://localhost:8080/apenas-teste/limpa");
		
		new CriadorDeCenarios(driver).umUsuario("Jose Eduardo", "jose@eduardo.com");
		new CriadorDeCenarios(driver).umUsuario("Paulo Henrique", "paulo@henrique.com");
		
		new CriadorDeCenarios(driver).umLeilao("Paulo Henrique", "Geladeira", 100, false);
		
	}
	
	@Test
	public void deveFazerUmLance() {
		DetalhesDoLeilaoPage lances = leiloes.detalhes(1);
		
		lances.lance("Jose Eduardo", 150);
		
		assertTrue(lances.existeLance("Jose Eduardo", 150));
		
	}
	
	
}
