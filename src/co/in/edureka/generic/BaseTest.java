package co.in.edureka.generic;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public abstract class BaseTest implements AutoConst
{
	public static WebDriver driver = null;
	public WebDriverWait wait = null;
	public Logger log = Logger.getLogger(BaseTest.class);
	
	@Parameters({"browser"})
	@BeforeMethod
	public void launchApplication(String browser)
	{
		if(browser.equalsIgnoreCase("CHROME"))
		{
			System.setProperty(chrome_key, chrome_value);
			driver = new ChromeDriver();
		}
		else
			if(browser.equalsIgnoreCase("FIREFOX"))
			{
				System.setProperty(gecko_key, gecko_value);
				driver = new FirefoxDriver();
			}
			else
				Reporter.log("Invalid browser selection");
		
		wait =new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(appUrl);
		
	}
	
	@AfterMethod
	public void closeApplication()
	{
		driver.quit();
	}


}
