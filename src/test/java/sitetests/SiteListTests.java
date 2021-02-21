package sitetests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
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
	private static ExtentReports report;
	private static ExtentTest test;
	private WebElement targ;

	@BeforeAll
	public static void setup() {
		
		report = new ExtentReports("C:\\Users\\P\\Documents\\spring-workspace\\TDL-Project\\target\\reports\\extent-reports\\extentReport-ListName.html", true);
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterAll
	public static void cleanup() {
		driver.quit();
		report.flush();
		report.close();
	}
	
	@AfterEach
	public void afterEachTest() {
		report.endTest(test);
	}

	@Test
	public void testCreateList() throws InterruptedException {
		test = report.startTest("Test for Create List");
		test.log(LogStatus.INFO, "List Created");
		
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
		test = report.startTest("Test for Read List");
		test.log(LogStatus.INFO, "List Read");
		
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
		test = report.startTest("Test for Update List");
		test.log(LogStatus.INFO, "List Updated");
		
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
		test = report.startTest("Test for Delete List");
		test.log(LogStatus.INFO, "List Deleted");
		
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
