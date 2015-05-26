import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesSystemTest {
	private WebDriver driver;
	private LeiloesPage leiloes;

	@Before
	public void inicializa() {
//		driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver",
				"C:/Users/rogeriop/ALURA/chromedriver.exe");
		this.driver = new ChromeDriver();

		driver.get("http://localhost:8080/apenas-teste/limpa");
		leiloes = new LeiloesPage(driver);

		UsuariosPage usuarios = new UsuariosPage(driver);
		usuarios.visita();
		usuarios.novo().cadastra("Paulo Henrique", "paulop@gmail.com");

	}

	@Test
	public void deveCadstrarUmLeilao() {
		leiloes.visita();
		NovoLeilaoPage novoLeilao = leiloes.novo();
		novoLeilao.preenche("Geladeira", 123, true, "Paulo Henrique");

		assertTrue(leiloes.existe("Geladeira", 123, true, "Paulo Henrique"));
	}

	@Test
	public void naoDeveCadastrarUmLeilaoSemNome() {
		leiloes.visita();
		NovoLeilaoPage novoLeilao = leiloes.novo();
		novoLeilao.preenche("", 123, false, "Paulo Henrique");

		assertTrue(novoLeilao.existeCriticaDeNome());
	}

	@Test
	public void naoDeveCadastrarUmLeilaoSemValor() {
		leiloes.visita();
		NovoLeilaoPage novoLeilao = leiloes.novo();
		novoLeilao.preenche("Geladeira Consul", 0, false, "Paulo Henrique");

		assertTrue(novoLeilao.existeCriticaDeValor());
	}
	

	@After
	public void finaliza() {
		driver.close();
	}
}
