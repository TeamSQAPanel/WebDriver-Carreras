package es.carreras.taller.webdriver.tallergrupocarreras;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBooking {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\ENVIRONMENT\\selenium\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(new ChromeOptions());

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

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

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort_by")));

		// COMPROBAR PRECIOS
		List<WebElement> precios = driver.findElements(By.xpath(
				"//div[@data-hotelid]//span[contains(@class,'bui-u-sr-only') and contains(text(),'€')]/preceding-sibling::div[starts-with(@class,'bui-price-display') and not(contains(@class,'original'))]"));

		int minPrecio = -1;
		String hotelMenorPrecio = "";
		for (WebElement currentPrice : precios) {
			JavascriptExecutor jsx = (JavascriptExecutor) driver;
			jsx.executeScript("arguments[0].scrollIntoView(true);", currentPrice);

			System.out.print("Precio actual = " + currentPrice.getText());
			WebElement nombreHotel = currentPrice
					.findElement(By.xpath("./ancestor::div[@data-hotelid]//a[@class='hotel_name_link url']/span[contains(@class,'name')]"));
			System.out.println(" - " + nombreHotel.getText());

			int precioHotelActual = filtrarPrecio(currentPrice.getText());
			if (minPrecio == -1 || (precioHotelActual < minPrecio && precioHotelActual > 0)) {
				minPrecio = precioHotelActual;
				hotelMenorPrecio = nombreHotel.getText();
			}
		}

		System.out.println("\nMe voy de vacaciones a " + hotelMenorPrecio + " por " + minPrecio + "€");

		driver.quit();
	}

	private static int filtrarPrecio(String text) {
		int precio;
		try {
			precio = Integer.parseInt(text.replace("€ ", ""));
		} catch (Exception e) {
			precio = -1;
		}
		return precio;
	}

}
