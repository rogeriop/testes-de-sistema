import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAutomatizado {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/rogeriop/ALURA/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		// firefoxDriver.get("http://www.google.com.br");
		driver.get("http://www.bing.com");
		WebElement findElement = driver.findElement(By.name("q"));
		findElement.sendKeys("Caelum");
		findElement.submit();
	}
}
