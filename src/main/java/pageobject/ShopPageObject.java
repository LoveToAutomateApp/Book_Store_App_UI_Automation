package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShopPageObject {
	
	public WebDriver driver;
	public By searchbox = By.cssSelector("input#searchBox");
	public By tablelist = By.cssSelector("div.books-wrapper > div.ReactTable > div > div.rt-tbody > div");
	public By searchsubmit = By.cssSelector("div.input-group-append > span#basic-addon2");
	
	public ShopPageObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebElement getSearchField() {
		return driver.findElement(searchbox);
	}
	
	public WebElement getSearchSubmit() {
		return driver.findElement(searchsubmit);
	}
	public List<WebElement> getTableList(){
		return driver.findElements(tablelist);
	}

}
