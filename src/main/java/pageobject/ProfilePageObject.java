package pageobject;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class ProfilePageObject {

	public WebDriver driver;
	
	CommonPageObject cpo;
	ProfilePageObject ppo;
	
	private static By usernameLabel = By.id("userName-value");
	private static By go_to_store_button = By.cssSelector("button#gotoStore");
	private static By delete_account_button = By.cssSelector("div.text-center> button#submit");
	private static By delete_all_books_button = By.cssSelector("div.di> button#submit");
	private static By button_list = By.cssSelector("div.profile-wrapper > div.buttonWrap > div");
	private static By add_to_collection = By.cssSelector("div.text-right > #addNewRecordButton");
	
	public ProfilePageObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebElement getProfileUsername() {
		return driver.findElement(usernameLabel);
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
	
	public WebElement getAddToCollectionButton() {
		return driver.findElement(add_to_collection);
	}
	
	public boolean getBookStatus(List<WebElement> tableList, String bookName) throws InterruptedException {
		for(int i=0; i < tableList.size(); i++) {
			String bookVal = tableList.get(i).getText().replace("\n","").replace("\t","").toString().trim();
			System.out.println(bookVal);
			if(bookVal.contains(bookName)) {
				return true;
		}
	}
		return false;
  }
	
   public void getRemoveBook(List<WebElement> tableList, String bookName) {
	  for(int i=0; i < tableList.size();i++) {
		 String bookVal =  tableList.get(i).getText().replace("\n","").replace("\t","").toString().trim();
			if(bookVal.contains(bookName)) {
				int rowIndex = tableList.size() - 4;
				driver.findElement(By.cssSelector("div.ReactTable > div.rt-table > div.rt-tbody > div.rt-tr-group:nth-Child("+rowIndex+") > div >  div:nth-Child(5) > div > span ")).click();		
		}
	  }
	}
	
   public void getAddToCollection(String bookname) throws InterruptedException {
	   driver.findElement(By.cssSelector("div.ReactTable > div.rt-table > div.rt-tbody > div.rt-tr-group:nth-Child(1)  > div > div:nth-Child(2)  > div > span")).click(); 
   }
}
