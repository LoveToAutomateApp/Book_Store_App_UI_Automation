package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonPageObject {
  public WebDriver driver;
  private static By searchbox = By.cssSelector("input#searchBox");
  private static By searchsubmit = By.cssSelector("span#basic-addon2");
  private static By profile_table_list = By.cssSelector("div.ReactTable > div.rt-table > div.rt-tbody > div.rt-tr-group");
  private static By alertDeleteRecordText = By.cssSelector("div.modal-dialog > div.modal-content > div.modal-body");
  private static By alertAccept = By.cssSelector("div.modal-footer > button#closeSmallModal-ok");
  private static By alertCancel = By.cssSelector("div.modal-footer > button#closeSmallModal-cancel");
  private static By logout_submit_button = By.cssSelector("button#submit");
	
	
	public CommonPageObject(WebDriver driver) {
	// TODO Auto-generated constructor stub
		this.driver = driver;
}

	public List<WebElement> getTableList(){
		return driver.findElements(profile_table_list);
	}

	public WebElement getSearchField() {
		return driver.findElement(searchbox);
	}
	
	public WebElement getSearchSubmit() {
		return driver.findElement(searchsubmit);
	}
	
	public void getRecordDelete() {
		String alertText = driver.findElement(alertDeleteRecordText).getText();
		System.out.println(alertText);
		if(alertText.contains("Do you want to delete this book?")) {
			driver.findElement(alertAccept).click();
		}
	}
	
	public void getScreenScrollDown() {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,350)", "");
	}
	
	public WebElement getLogoutSubmit() {
		return driver.findElement(logout_submit_button);
	}
}
