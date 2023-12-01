package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePageObject {

	public WebDriver driver;
	private static By usernameLabel = By.id("userName-value");
	private static By logout_submit_button = By.cssSelector("div#books-wrapper > div.text-right > button#submit");
	
	
	public ProfilePageObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebElement getProfileUsername() {
		return driver.findElement(usernameLabel);
	}
	
	public WebElement getLogoutSubmit() {
		return driver.findElement(logout_submit_button);
	}
}
