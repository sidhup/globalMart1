package com.globalmart.objectrepository;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class OnePage {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		String chromepath = "D:\\project req\\chromedriver_win32\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromepath);
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.fabindia.com");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//explicit wait
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		//implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		Actions act = new Actions(driver);
WebElement wb=driver.findElement(By.linkText("Just In!"));
act.moveToElement(wb).build().perform();
Thread.sleep(5000);

driver.findElement(By.xpath("//a[@class='style4']/span[text()='Women']")).click();

		
		

	}

}
