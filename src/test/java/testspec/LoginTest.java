package testspec;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobject.CommonPageObject;
import pageobject.LoginPageObject;
import pageobject.ProfilePageObject;
import resources.Base;

public class LoginTest extends Base {
	public WebDriver driver;
	public LoginPageObject lpo;
	public ProfilePageObject ppo;
	public CommonPageObject cpo;
	
	@BeforeTest
	public void navigateUrl() throws IOException {
	  driver = initializeDriver();
	  driver.manage().window().maximize();
	  driver.get(prop.getProperty("loginUrl"));
	  lpo = new LoginPageObject(driver);
	  cpo = new CommonPageObject(driver);
	  ppo = new ProfilePageObject(driver);
	}
	
	/*
	 *check for user signin with invalid username and password
	 * also check for error message
	 */
	@Test(dataProvider ="signinWithInvalidCredentials")
	public void verify_user_signin_with_invalid_username_and_password(String username, String password) {

		lpo.getUsernameField().sendKeys(username);
		lpo.getPasswordField().sendKeys(password);
		WebElement loginBtn = lpo.getLoginButton();
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
		boolean errorIsDisplayed = lpo.getErrorMessage().isDisplayed();
		if(errorIsDisplayed) {
			String errorMsgVal = lpo.getErrorMessage().getText();
			Assert.assertEquals(errorMsgVal,"Invalid username or password!");
			
		}else {
			Assert.assertFalse(true);
		}
       driver.navigate().refresh();		
	}
	
	@DataProvider(name="signinWithInvalidCredentials")
	public Object[][] getData(){
		Object[][] data = new Object[6][2];
		// single character value for username and password
		data[0][0] = "t";
		data[0][1] = "t";
		// more than 30 character value for username and password
		data[1][0] = "ttttttttttttttttttttttttttdddddd";
		data[1][1] = "ttttttttttttttdddddddddddddddddd";
		// negative character value for username and passsword
		data[2][0] = "-1111111";
		data[2][1] = "-2333333";
		// special character value for username and password field
		data[3][0] = "@@!!$$%%^&*()_+:/><##&*-=;";
		data[3][1] = "@@!!$$%%^&*()_+:/><##&*-=;";
		// sql injection character value for username or password field
		data[4][0] = "'or 1=1;--";
		data[4][1] = "test@123";
		// unregistered username and password value
		data[5][0] = "testoepe";
		data[5][1] = "hheheddfgheheh";
		
		return data;
	}
	
	/*
	 * ensure user is signed in with valid credentials
	 * after signin user should also sign out
	 */
	
	@Test(dataProvider="signinWithValidCredentials")
	public void verify_user_signin_with_valid_username_and_password(String username, String password) throws InterruptedException {
		lpo.getUsernameField().sendKeys(username);
		lpo.getPasswordField().sendKeys(password);
		WebElement loginBtn = lpo.getLoginButton();
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        Thread.sleep(3000);
		String userNameVal = ppo.getProfileUsername().getText();
        Assert.assertEquals(userNameVal, username);
        cpo.getLogoutSubmit().click();
        Thread.sleep(3000);
       driver.navigate().refresh();
	}
	
	@DataProvider(name="signinWithValidCredentials")
	public Object[][] getData2(){
		Object[][] data = new Object[3][2];
		data[0][0] = "test123";
		data[0][1] = "Test@123";
		data[1][0] = "test1234";
		data[1][1] = "Test@1234";
		data[2][0] = "test12345";
		data[2][1] = "Test@12345";
		return data;
	}
	
	@AfterTest
	public void terminate() {
	  driver.quit();
	}
	
}
