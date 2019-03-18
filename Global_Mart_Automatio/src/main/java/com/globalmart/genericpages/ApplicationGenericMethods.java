package com.globalmart.genericpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ApplicationGenericMethods extends GenericMethods {

	protected ApplicationGenericMethods(final WebDriver driver) {
		super(driver);
		
	}
	@Override
	 protected void click(final WebElement element) {
//       waitUntilPageLoaded();
       super.click(element);
       
	 }
	@Override
    protected void clickByJSId(final String locatorID) {
//        waitUntilPageLoaded();
        super.clickByJSId(locatorID);
    }
	@Override
	protected void clickByJSQuery(final String query) {

        super.clickByJSQuery(query);
    }
	@Override
    protected void clickByJs(WebElement element) {
//        waitUntilPageLoaded();
        super.clickByJs(element);
    }


    protected void mouseOverThenClick(final WebElement element) {
        super.mouseOverThenClick(element);
    }

    @Override
    protected void scrollAndClick(final WebElement element) {
//        waitUntilPageLoaded();
        super.scrollAndClick(element);
    }

    @Override
    protected void scrollAndCheck(final WebElement element) {
//        waitUntilPageLoaded();
        super.scrollAndCheck(element);
    }

    @Override
    protected void scrollToElement(final WebElement element) {
        waitUntilPageLoaded();
        super.scrollToElement(element);

    }

    @Override
    protected void check(final WebElement element) {
        waitUntilPageLoaded();
        super.check(element);
    }

    @Override
    protected void unCheck(final WebElement element) {
        waitUntilPageLoaded();
        super.unCheck(element);
    }


    protected void typeIn(final WebElement element, final String input) {
        waitUntilPageLoaded(1);
        super.typeIn(element, input);
    }

    @Override
    protected void typeInHitEnter(final WebElement element, final String input) {
        waitUntilPageLoaded(1);
        super.typeInHitEnter(element, input);
    }


    protected void typeIn(final WebElement element, final int input) {
        super.typeIn(element, input);
    }


    protected void typeIn(String locator, final String input) {
        super.typeIn(locator, input);
    }

    @Override
    protected void select(final WebElement element, final String toBeSelected) {
        waitUntilPageLoaded();
        super.select(element, toBeSelected);
    }

    protected void selectByIndex(WebElement element, int index){
        Select option = new Select(element);
        option.selectByIndex(index);
    }

    @Override
    protected boolean isDisplayed(final WebElement element) {
//        waitUntilPageLoaded();
        return super.isDisplayed(element);
    }

    @Override
    protected boolean isDisplayed(final String locator) {
//        waitUntilPageLoaded();
        return super.isDisplayed(locator);
    }

    @Override
    protected boolean isEnabled(final WebElement element) {
//        waitUntilPageLoaded();
        return super.isEnabled(element);
    }

    @Override
    protected String getTextOrValue(final WebElement element) {
        waitUntilPageLoaded();
        return super.getTextOrValue(element);

    }

    @Override
    protected void switchToFrame(final WebElement element) {
        waitUntilPageLoaded();
        super.switchToFrame(element);
    }

    @Override
    protected void switchFrameToDefault() {
        waitUntilPageLoaded();
        super.switchFrameToDefault();
    }


    protected void refreshPage() {
        super.refreshPage();
    }

    protected void waitUntilPageLoaded(int seconds) {
        Boolean isLoaded = false;
        while (!isLoaded) {
            isLoaded = super.isPageLoaded();
            delay(seconds);
        }
    }

    protected void waitUntilPageLoaded() {
        try {
            waitUntilPageLoaded(5);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    protected void delay(final int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }

    protected Double toDouble(String text) {
        return Double.parseDouble(text.replace(",", "").replace("$", ""));
    }
}


