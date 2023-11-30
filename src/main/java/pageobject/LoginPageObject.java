package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageObject {

	public WebDriver driver;
	
	private static By username = By.id("userName");
	private static By password = By.id("password");
	private static By login_button = By.xpath("//button[@id='login']");
	private static By register_button = By.xpath("//button[@id='newUser']");
	private static By error_message = By.xpath("//p[@id='name']");
	
	public LoginPageObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebElement getUsernameField() {
		return driver.findElement(username);
	}
	
	public WebElement getPasswordField() {
		return driver.findElement(password);
	}
	
	public WebElement getLoginButton() {
		return driver.findElement(login_button);
	}
	
	public WebElement getRegisterButton() {
		return driver.findElement(register_button);
	}
	
	public WebElement getErrorMessage() {
		return driver.findElement(error_message);
	}
}
