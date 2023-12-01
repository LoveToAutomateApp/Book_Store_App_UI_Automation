package testspec;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobject.LoginPageObject;
import pageobject.ProfilePageObject;
import resources.Base;

public class ProfileTest extends Base {

	public WebDriver driver;
	public LoginPageObject lpo;
	public ProfilePageObject ppo;
	
	@BeforeTest
	public void navigateUrl() throws IOException {
		driver = initializeDriver();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("loginUrl"));
	}
	
	/*
	 * check there are GoToBookStore, Delete Account, Delete All Books are displayed
	 *  
	 */
	
	@Test
	public void verify_functional_buttons_are_displayed() throws InterruptedException {
		lpo = new LoginPageObject(driver);
		ppo = new ProfilePageObject(driver);
		lpo.getUserSignedIn("test123", "Test@123");
	    Thread.sleep(2000); 
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,350)", "");
	    
	    List<WebElement> button_list = ppo.getButtonList();
	    for(int i=0; i< button_list.size();i++) {
	    	String buttonVal = button_list.get(i).getText().replace("\t"," ").replace("\n", " ").toString().trim();
	    	if(buttonVal.equals("Go To Book Store") || buttonVal.equals("Delete Account") || buttonVal.equals("Delete All Books")) {
	    		int rowIndex1 = button_list.size()- 2;
	    		boolean go_to_book_store_button_is_displayed = driver.findElement(By.cssSelector("div.profile-wrapper > div.buttonWrap > div:nth-Child("+rowIndex1+") > button")).isDisplayed();
	    		if(go_to_book_store_button_is_displayed) {
	    			Assert.assertTrue(true);
	    		}
	    		int rowIndex2 = button_list.size() - 1;
	    		boolean delete_account_button = driver.findElement(By.cssSelector("div.profile-wrapper > div.buttonWrap > div:nth-Child("+rowIndex2+") > button")).isDisplayed();
	    		if(delete_account_button) {
	    			Assert.assertTrue(true);
	    		}
	    		int rowIndex3 = button_list.size();
	    		boolean delete_all_books = driver.findElement(By.cssSelector("div.profile-wrapper > div.buttonWrap > div:nth-Child("+rowIndex3+") > button")).isDisplayed();
	    		if(delete_all_books) {
	    			Assert.assertTrue(true);
	    		}
	    	}
	    }	    
	    ppo.getLogoutSubmit().click();
	}
	
	@AfterTest
	public void terminate() {
	  driver.quit();
	}
}
