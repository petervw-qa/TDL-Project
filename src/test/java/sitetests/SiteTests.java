package sitetests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import site.SiteLandingPage;

public class SiteTests {
	
	private static WebDriver driver;
	
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
	public void test() {
		driver.get("http://127.0.0.1:5500/src/main/resources/static/index.html");
		SiteLandingPage website = PageFactory.initElements(driver, SiteLandingPage.class);
		
		website.openCreateListModal();
	}

}
