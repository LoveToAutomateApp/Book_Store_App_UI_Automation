package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePageObject {

	public WebDriver driver;
	private static By usernameLabel = By.id("userName-value");
	private static By logout_submit_button = By.cssSelector("div#books-wrapper > div.text-right > button#submit");
	private static By go_to_store_button = By.id("gotoStore");
	private static By delete_account_button = By.cssSelector("div.text-center> button#submit");
	private static By delete_all_books_button = By.cssSelector("div.di> button#submit");
	private static By button_list = By.cssSelector("div.profile-wrapper > div.buttonWrap > div");
	
	
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
	
	public WebElement getGoToStoreButton() {
		return driver.findElement(go_to_store_button);
	}
	
	public WebElement getDeleteAccountButton() {
		return driver.findElement(delete_account_button);
	}
	
	public WebElement getDeleteAllBooksButton() {
		return driver.findElement(delete_all_books_button);
	}
	
	public List<WebElement> getButtonList(){
		return driver.findElements(button_list);
	}
}
