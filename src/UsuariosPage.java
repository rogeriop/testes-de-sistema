import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsuariosPage {

	private WebDriver driver;

	public UsuariosPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("http://localhost:8080/usuarios");
	}

	public NovoUsuarioPage novo() {
		driver.findElement(By.linkText("Novo Usu�rio")).click();
		return new NovoUsuarioPage(driver);

	}

	public boolean existeNaListagem(String nome, String email) {
		return driver.getPageSource().contains(nome)
				&& driver.getPageSource().contains(email);
	}

	public boolean achouCritica(String critica) {
		return driver.getPageSource().contains(critica);
	}

	public void excluiUsuarioCadastradoNesteTeste() {
		
		int posicao = 1; // queremos o 1o botao da pagina
		driver.findElements(By.tagName("button")).get(posicao - 1).click();
		
		// pega o alert que est� aberto
		Alert alert = driver.switchTo().alert();
		// confirma
		alert.accept();
	}
}
