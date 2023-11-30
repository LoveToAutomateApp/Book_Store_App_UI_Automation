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

import pageobject.LoginPageObject;
import resources.Base;

public class LoginTest extends Base {
	public WebDriver driver;
	public LoginPageObject lpo;
	
	@BeforeTest
	public void navigateUrl() throws IOException {
	  driver = initializeDriver();
	  driver.manage().window().maximize();
	  driver.get(prop.getProperty("loginUrl"));
	}
	
	/*
	 *check for user signin with invalid username and password
	 * also check for error message
	 */
	@Test(dataProvider ="signinWithInvalidCredentials")
	public void verify_user_signin_with_invalid_username_and_password(String username, String password) {
		lpo = new LoginPageObject(driver);
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
	
	@AfterTest
	public void terminate() {
	  driver.quit();
	}
	
}
