package sitetests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
//
//import com.qa.application.ToDoListApplication;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = ToDoListApplication.class)
//@Sql(scripts = { "classpath:application-schema.sql", "classpath:application-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//@ActiveProfiles("test")
public class SiteItemTests {
	
	private static RemoteWebDriver driver;
	private WebElement targ;

	@BeforeAll
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterAll
	public static void cleanup() {
		driver.quit();
	}

	@Test
	public void testCreateItem() throws InterruptedException {
		driver.get("http://127.0.0.1:5500/src/main/resources/static/index.html");
		targ = driver.findElement(By.xpath("/html/body/main/button[5]")); // target modal
		targ.click(); // open modal
		targ = driver.findElement(By.xpath("//*[@id=\"items-name\"]")); // target name 
		Thread.sleep(5000); // wait
		targ.sendKeys("Exercise"); // send name
		targ = driver.findElement(By.xpath("//*[@id=\"items-description\"]")); // target desc
		Thread.sleep(5000); // wait
		targ.sendKeys("Chest"); // send desc
		targ = driver.findElement(By.xpath("//*[@id=\"items-completed\"]")); // target bool
		Thread.sleep(5000); // wait
		targ.sendKeys("true"); // send bool
		targ = driver.findElement(By.xpath("//*[@id=\"items-listname-Id\"]")); // target ID
		Thread.sleep(5000); // wait
		targ.sendKeys("1"); // send ID
		targ = driver.findElement(By.xpath("//*[@id=\"createItemModal\"]/div/div/button[2]")); // target button
		targ.click();
		Thread.sleep(5000);
	}
	
	@Test
	public void testReadItem() throws InterruptedException {
		driver.get("http://127.0.0.1:5500/src/main/resources/static/index.html");
		targ = driver.findElement(By.xpath("/html/body/main/button[6]")); // target modal
		targ.click(); // open modal
		targ = driver.findElement(By.xpath("//*[@id=\"items-id\"]")); // target ID
		Thread.sleep(5000); // wait
		targ.sendKeys("1"); // send ID
		targ = driver.findElement(By.xpath("//*[@id=\"readItemModal\"]/div/div/button[3]")); // target ID
	}
	
	@Test
	public void testUpdateItem() throws InterruptedException {
		driver.get("http://127.0.0.1:5500/src/main/resources/static/index.html");
		targ = driver.findElement(By.xpath("/html/body/main/button[7]")); // target modal
		targ.click(); // open modal
		Thread.sleep(5000); // wait
		targ = driver.findElement(By.xpath("//*[@id=\"item-new-id\"]")); // target 
		Thread.sleep(5000); // wait
		targ.sendKeys("1"); // send 
		targ = driver.findElement(By.xpath("//*[@id=\"item-new-name\"]")); // target 
		Thread.sleep(5000); // wait
		targ.sendKeys("Exercise Loads!"); // send 
		targ = driver.findElement(By.xpath("//*[@id=\"item-new-desc\"]")); // target 
		Thread.sleep(5000); // wait
		targ.sendKeys("Chest and Legs!"); // send 
		targ = driver.findElement(By.xpath("//*[@id=\"item-new-comp\"]")); // target 
		Thread.sleep(5000); // wait
		targ.sendKeys("true"); // send 
		targ = driver.findElement(By.xpath("//*[@id=\"updateItemModal\"]/div/div/button[2]"));
		targ.click();
	}
	
	@Test
	public void testDeleteItem() throws InterruptedException {
		driver.get("http://127.0.0.1:5500/src/main/resources/static/index.html");
		targ = driver.findElement(By.xpath("/html/body/main/button[8]")); // target modal
		targ.click();
		targ = driver.findElement(By.xpath("//*[@id=\"listitems-delete\"]"));
		Thread.sleep(5000); // wait
		targ.sendKeys("1");
		targ = driver.findElement(By.xpath("//*[@id=\"deleteItemModal\"]/div/div/button[2]"));
		targ.click();
	}

}
