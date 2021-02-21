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
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = ToDoListApplication.class)
//@Sql(scripts = { "classpath:application-schema.sql", "classpath:application-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//@ActiveProfiles("test")
public class SiteListTests {
	
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
	public void testCreateList() throws InterruptedException {
		driver.get("http://127.0.0.1:5500/src/main/resources/static/index.html");
		targ = driver.findElement(By.xpath("/html/body/main/button[1]"));
		targ.click();
		targ = driver.findElement(By.xpath("//*[@id=\"listname-name\"]"));
		Thread.sleep(5000);
		targ.sendKeys("Monday");
		targ = driver.findElement(By.xpath("//*[@id=\"createListModal\"]/div/div/button[2]"));
		targ.click();
		Thread.sleep(5000);
	}
	
	@Test
	public void testReadList() throws InterruptedException {
		driver.get("http://127.0.0.1:5500/src/main/resources/static/index.html");
		targ = driver.findElement(By.xpath("/html/body/main/button[2]"));
		targ.click();
		targ = driver.findElement(By.xpath("//*[@id=\"listname-read-id\"]"));
		Thread.sleep(5000);
		targ.sendKeys("1");
		targ = driver.findElement(By.xpath("//*[@id=\"readListModal\"]/div/div/button[2]"));
		targ.click();
		Thread.sleep(5000);
	}
	
	@Test
	public void testUpdateList() throws InterruptedException {
		driver.get("http://127.0.0.1:5500/src/main/resources/static/index.html");
		targ = driver.findElement(By.xpath("/html/body/main/button[3]"));
		targ.click();
		targ = driver.findElement(By.xpath("//*[@id=\"listname-update-id\"]"));
		Thread.sleep(2000);
		targ.sendKeys("1");
		targ = driver.findElement(By.xpath("//*[@id=\"listname-rename\"]"));
		Thread.sleep(2000);
		targ.sendKeys("Monday Extended");
		targ = driver.findElement(By.xpath("//*[@id=\"updateListModal\"]/div/div/button[2]"));
		targ.click();
		Thread.sleep(2000);
	}
	
	@Test
	public void testDeleteList() throws InterruptedException {
		driver.get("http://127.0.0.1:5500/src/main/resources/static/index.html");
		targ = driver.findElement(By.xpath("/html/body/main/button[4]"));
		targ.click();
		targ = driver.findElement(By.xpath("//*[@id=\"listname-delete-id\"]"));
		Thread.sleep(2000);
		targ.sendKeys("1");
		targ = driver.findElement(By.xpath("//*[@id=\"deleteListModal\"]/div/div/button[2]"));
		targ.click();
		Thread.sleep(2000);
	}

}
