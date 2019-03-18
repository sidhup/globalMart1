package com.globalmart.basepage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.globalmart.utilities.WebEventListener;

public class BasePage {
	public WebDriver driver;
	public WebDriverWait wait;
	public Properties prop;
	public static WebEventListener eventlistener;
	public static EventFiringWebDriver e_driver;
	public BasePage() {
		try {
			FileInputStream fis = new FileInputStream("./src/data/commondata.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException f) {
			f.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	

	@BeforeMethod
	public void openBrowser() {
		String BrowserName = prop.getProperty("browser");
		String chromepath = "D:\\project req\\chromedriver_win32\\chromedriver.exe";
		String gechodriverpath = "";
		if (BrowserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromepath);
			driver = new ChromeDriver();
		} else if (BrowserName.equals("firefox")) {
			System.setProperty("webDriver.gecko.driver", gechodriverpath);
			driver = new FirefoxDriver();
		}
		e_driver = new EventFiringWebDriver(driver);
		eventlistener = new WebEventListener();
		e_driver.register(eventlistener);
		driver = e_driver;
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		
	}

	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	

	
		
	
}
