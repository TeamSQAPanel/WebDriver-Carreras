package es.carreras.taller.webdriver.tallergrupocarreras;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Unit test for simple App.
 */
public class AppTest {

	WebDriver driver;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\ENVIRONMENT\\selenium\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver(new ChromeOptions());

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}

	@Test
	public void shouldAnswerWithTrue() {
		driver.get("http://booking.com");

		// BUSCAR HOTELES
		WebElement inputDestino = driver.findElement(By.xpath("//input[@type='search']"));
		inputDestino.sendKeys("Zaragoza");

		WebElement botonCalendario = driver.findElement(By.xpath("//div[contains(@class,'dates')]"));
		botonCalendario.click();

		WebElement fechaEntrada = driver.findElement(By.xpath("//td[@data-date='2020-08-24']"));
		fechaEntrada.click();

		WebElement fechaSalida = driver.findElement(By.xpath("//td[@data-date='2020-08-30']"));
		fechaSalida.click();

		driver.findElement(By.xpath("//button[@type='submit']")).click();

//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort_by")));

		assertTrue(!driver.findElement(By.id("sort_by")).isDisplayed());
	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
