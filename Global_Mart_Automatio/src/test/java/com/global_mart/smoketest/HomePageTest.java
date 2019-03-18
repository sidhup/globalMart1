package com.global_mart.smoketest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.globalmart.basepage.BasePage;
import com.globalmart.genericpages.ApplicatioPageFactory;



public class HomePageTest extends BasePage {
	ApplicatioPageFactory ap;

	@Test(priority=3)
	public void clickOnJustinDropDown()
	{
		ap.homePageDesktop().clickOnJustInDropdown();
	}

	@Test(priority = 2)
	public void clickOnJustinWomenTest() {
		ap = new ApplicatioPageFactory(driver);
		ap.homePageDesktop().clickOnJustIn_Women();
	}
	@Test(priority=1)
	public void verifyLogo()
	{
	
		Assert.assertTrue(ap.homePageDesktop().verifyLogo());
	}
}
