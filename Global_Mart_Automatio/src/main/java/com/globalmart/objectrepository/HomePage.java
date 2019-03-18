package com.globalmart.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.globalmart.genericpages.ApplicationGenericMethods;

public class HomePage extends ApplicationGenericMethods {
	private WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}
	
	

	/**
	 * other xpath fabindia="//div[@class='hidden-xs
	 * logo_picker']/div[@class='row']/div[@class='logo
	 * clearfix']/a[@id='CC-header-logoImageLink']/img[@title='Fabindia Logo']
	 * 
	 */
	@FindBy(xpath = "//div[@class='hidden-xs logo_picker']/div/div/a[@id='CC-header-logoImageLink']/img[@title='Fabindia Logo']")
	@CacheLookup
	WebElement Logo;

	@FindBy(linkText = "Just In!")
	WebElement justInDropdown;

	@FindBy(xpath = "//a[@class='style4']/span[text()='Women']")
	WebElement justIn_Women;

	@FindBy(xpath = "//a[@class='style4']/span[text()='Men']")
	WebElement justIn_Men;
	

	public boolean verifyLogo() {
		return Logo.isDisplayed();
	}

	public String verifyDimensionOfLogo() {
		int height = Logo.getSize().getHeight();
		int width = Logo.getSize().getWidth();
		String dimensionOfLogo = height + "," + width;
		return dimensionOfLogo;
	}

	public void clickOnJustInDropdown() {
		click(justInDropdown);
	}

	public void clickOnJustIn_Women() {
		click(justInDropdown);
		mouseOverThenClick(justIn_Women);
	}

	public void clickOnJustIn_Men() {
		clickOnJustInDropdown();
		mouseOverThenClick(justIn_Men);
	}

}
