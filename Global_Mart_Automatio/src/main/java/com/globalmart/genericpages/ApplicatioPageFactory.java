package com.globalmart.genericpages;

import org.openqa.selenium.WebDriver;

import com.globalmart.objectrepository.HomePage;
import com.globalmart.objectrepository.JustInPage;

public class ApplicatioPageFactory {
	private WebDriver driver;
	private HomePage homePageDesktop;
	private JustInPage jp;

	public ApplicatioPageFactory(WebDriver driver) {
		this.driver = driver;
	}


	public HomePage homePageDesktop() {
		if (homePageDesktop == null) {
			homePageDesktop = new HomePage(driver);
		}
		return homePageDesktop;
	}

	public JustInPage jp() {
		if (jp == null) {
			jp = new JustInPage(driver);
		}
		return jp;
	}
}
