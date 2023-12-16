package testspec;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobject.CommonPageObject;
import pageobject.LoginPageObject;
import pageobject.ProfilePageObject;
import pageobject.ShopPageObject;
import resources.Base;

public class ProfileTest extends Base {

	public WebDriver driver;
	public LoginPageObject lpo;
	public ProfilePageObject ppo;
	public CommonPageObject cpo;
	public ShopPageObject spo;
	
	@BeforeTest
	public void navigateUrl() throws IOException {
		driver = initializeDriver();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("loginUrl"));
		lpo = new LoginPageObject(driver);
		ppo = new ProfilePageObject(driver);
		cpo = new CommonPageObject(driver);
		spo = new ShopPageObject(driver);
	}
	
	/*
	 * check there are GoToBookStore, Delete Account, Delete All Books are displayed
	 *  
	 */
	
	@Test
	public void verify_functional_buttons_are_displayed() throws InterruptedException {
		lpo.getUserSignedIn("test123", "Test@123");
	    Thread.sleep(2000); 
	    cpo.getScreenScrollDown();
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
	    
	    cpo.getLogoutSubmit().click();
	}
	
	/*
	 *  ensure bookname Speaking Javascript is added to collection
	 */
	@Test(dataProvider="book")
	public void verify_book_is_added_to_collection(String username, String password, String bookname) throws InterruptedException {
		lpo.getUserSignedIn(username,password);
	    cpo.getSearchField().sendKeys(bookname);
	    cpo.getSearchSubmit().click();
	    
	    List<WebElement> tableList = cpo.getTableList();
	   
	    boolean bookStatus = ppo.getBookStatus(tableList,bookname);
	    if(bookStatus == false) {
	 	   // Add book to collection
	       cpo.getScreenScrollDown();
	 	   ppo.getGoToStoreButton().click();
	 	   cpo.getSearchField().sendKeys(bookname);
	 	   cpo.getSearchSubmit().click();
           ppo.getAddToCollection(bookname);
    	   cpo.getScreenScrollDown();
    	   ppo.getAddToCollectionButton().click();
    	   Thread.sleep(3000);
    	   driver.switchTo().alert().accept();
    	 
	    }else {
	    	ppo.getRemoveBook(tableList,bookname);
	    	cpo.getRecordDelete();
	    	Thread.sleep(3000);
	    	driver.switchTo().alert().accept();
	    }
	    Thread.sleep(2000);
	    cpo.getLogoutSubmit().click();
	}
	
	@DataProvider(name="book")
	public Object[][] getData(){
		Object[][] data = new Object[1][3];
		data[0][0] = "test123";
		data[0][1] = "Test@123";
		data[0][2] = "Speaking JavaScript";
		return data;
	}
	
	@AfterTest
	public void terminate() {
	  driver.quit();
	}
}
