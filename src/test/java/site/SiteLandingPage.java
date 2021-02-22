package site;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SiteLandingPage {

	@FindBy(xpath = "/html/body/main/button[1]")
	private WebElement createListModal;
	
	@FindBy(xpath = "/html/body/main/button[2]")
	private WebElement readListModal;
	
	@FindBy(xpath = "/html/body/main/button[3]")
	private WebElement updateListModal;
	
	@FindBy(xpath = "/html/body/main/button[4]")
	private WebElement deleteListModal;

	public SiteLandingPage(WebDriver webDriver) {
	}
	
	public void openCreateListModal() {
		createListModal.click();
	}
	
	public void openReadListModal() {
		readListModal.click();
	}
	
	public void openUpdateListModal() {
		updateListModal.click();
	}
	
	public void openDeleteListModal() {
		deleteListModal.click();
	}
	
}
