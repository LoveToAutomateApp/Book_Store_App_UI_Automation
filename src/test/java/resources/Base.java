package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
  public WebDriver driver;
  public Properties prop;
  public WebDriver initializeDriver() throws IOException {
	  String file = System.getProperty("user.dir")+"/src/test/java/resources/data.properties";
	  FileInputStream fis = new FileInputStream(file);
	  prop = new Properties();
	  prop.load(fis);
      String browserName = prop.getProperty("browser");	  
      if(browserName.equalsIgnoreCase("chrome")) {
    	  driver = new ChromeDriver();
      }else if(browserName.equalsIgnoreCase("firefox")) {
    	  driver = new FirefoxDriver();
      }else if(browserName.equalsIgnoreCase("edge")) {
    	  driver = new EdgeDriver();
      }else {
    	  System.out.println("no driver found");
      }
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      return driver;
  }
}
