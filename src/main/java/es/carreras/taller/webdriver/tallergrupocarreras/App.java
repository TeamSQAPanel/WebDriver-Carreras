package es.carreras.taller.webdriver.tallergrupocarreras;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\ENVIRONMENT\\selenium\\chromedriver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		driver.get("https://www.panel.es");

		System.out.println("URL actual: " + driver.getCurrentUrl());
		System.out.println("Titulo: " + driver.getTitle());
		// System.out.println("Source: " + driver.getPageSource());

		WebDriverWait espera = new WebDriverWait(driver, 10);
		espera.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@alt,'Logotipo')]")));

		WebElement logo = driver.findElement(By.xpath("//img[contains(@alt,'Logotipo')]"));
		logo.click();

		WebElement menuPrincipal = driver.findElement(By.xpath("//*[@id='menu-menu-principal-1']"));
		WebElement menuServicios = menuPrincipal.findElement(By.xpath(".//a[contains(text(),'SERVICIOS')]"));
		WebElement opcionAseguramiento = menuPrincipal.findElement(By.xpath(".//a[contains(@href,'aseguramiento')]"));
		WebElement opcionAutomatizacion = menuPrincipal.findElement(By.xpath(".//a[contains(@href,'zahori')]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(menuServicios).moveToElement(opcionAseguramiento).moveToElement(opcionAutomatizacion).click().build().perform();

		Thread.sleep(5000);

		driver.quit();
	}
}
