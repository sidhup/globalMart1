package com.globalmart.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.globalmart.basepage.BasePage;

public class Utils extends BasePage {

	public static WebDriver driver;

	public static void takeAScreenshot() throws IOException {
		File schreenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//String currentdir = System.getProperty("user.dir");

		FileUtils.copyFile(schreenshotFile, new File("C:\\Users\\DIBYA RANJAN TRIPATH.DESKTOP-9APHSBC\\eclipse-workspace\\Global_Mart_Automatio\\SchreenShot\\schreenshpt.png"));

	}

}
