package com.globalmart.genericpages;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;

import javax.management.RuntimeErrorException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class GenericMethods {
	private WebDriver driver;
	private WebDriverWait wait;
	 private JavascriptExecutor jsExecutor;
	private final static Logger logger = Logger.getLogger(GenericMethods.class);

	public GenericMethods(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);

	}

protected void click(final WebElement element)
{
	try {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}catch(Exception e)
	{
		if(!isDisplayed(element))
		{
			logger.error(String.format("is not displayed on", element,driver.getCurrentUrl()));
			throw new NoSuchElementException(String.format("is ot displayed", element,driver.getCurrentUrl(), e));
		}
		else 
			{
			if(isEnabled(element))
			{
				logger.error(String.format("is displayed, but not enabled. on", element,driver.getCurrentUrl()));
				throw new RuntimeErrorException(null, String.format("is displayed, but not enabled. on", element,driver.getCurrentUrl()));
			}
			}
		
	}
	
	
}

	protected boolean isDisplayed(final WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			/**
			 * WebElement.isDisplayed() is supposed to always return a boolean, but it only
			 * returns a boolean if an element is found. If an element is NOT found, it
			 * should still return a boolean, instead it throws an exception. Essentially
			 * WebElement.isDisplayed never returns false.
			 **/
			return false;
		}
	}

	protected boolean isEnabled(final WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isEnabled();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
	
	protected void clickByJSId(final String locatorID) {
        String jsCommand = String.format("document.getElementById('%s').click()", locatorID);

        jsExecutor.executeScript(jsCommand);

//TODO add try-catch
    }

    /**
     * This method waits until page is loaded, then click element by javascript's querySelector.
     *
     * @param query It requires CSS-selector for the element.
     */
    protected void clickByJSQuery(final String query) {

        String jsCommand = String.format("document.querySelector(\"%s\").click()", query);

        jsExecutor.executeScript(jsCommand);
    }

    protected void clickByJs(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    

    protected void mouseOverThenClick(final WebElement element) {
        Actions act = new Actions(driver);
        act.moveToElement(element).build().perform();
        act.click();
    }
    protected void scrollAndClick(final WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        jsExecutor.executeScript("arguments[0].click();", element);

//TODO add try-catch
    }

    protected void scrollAndCheck(final WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            if (!element.isSelected()) {
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
                jsExecutor.executeScript("arguments[0].click();", element);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    protected void scrollToElement(final WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * This method waits until page is loaded, then if checkbox is unchecked, it checks,
     * if checkbox is checked, it does nothing.
     *
     * @param element UIElement that appears on the page.
     */
    protected void check(final WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            if (!element.isSelected()) {
                element.click();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * This method waits until page is loaded, then if checkbox is checked, it unchecks,
     * if checkbox is unchecked, it does nothing.
     *
     * @param element UIElement that appears on the page.
     */
    protected void unCheck(final WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            if (element.isSelected()) {
                element.click();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * This method waits until page is loaded and element is visible,
     * then it sends the input to the desired location.
     *
     * @param element UIElement that appears on the page.
     * @param input   Input that user wants to type in.
     */
    protected void typeIn(final WebElement element, final String input) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(input);
            wait.until(ExpectedConditions.textToBePresentInElementValue(element, input));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    protected void typeInHitEnter(final WebElement element, final String input) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(input + Keys.ENTER);
//this waiting command causes searching fails in Brands test scenarios.
//            wait.until(ExpectedConditions.textToBePresentInElementValue(element, input));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * This method waits until page is loaded and element is visible,
     * then it sends the input to the desired location.
     *
     * @param element UIElement that appears on the page.
     * @param input   Input that user wants to type in.
     */
    protected void typeIn(final WebElement element, final int input) {
        typeIn(element, String.valueOf(input));
    }

    /**
     * This method waits until page is loaded and element is visible,
     * then it sends the input to the desired location.
     *
     * @param locator UIElement that appears on the page.
     * @param input   Input that user wants to type in.
     */
    protected void typeIn(String locator, final String input) {
        String jsQuery = String.format("%s.value='%s'", locator, input);
        jsExecutor.executeScript(jsQuery);

//TODO add try-catch
    }

    /**
     * This method waits until page is loaded and element is visible,
     * then it selects item from the dropdown list.
     *
     * @param element      UIElement that appears on the page.
     * @param toBeSelected Item that user wants to select.
     */
    protected void select(final WebElement element, final String toBeSelected) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(toBeSelected);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * This method waits until page is loaded and element is visible,
     * then it checks whether the element is displayed.
     *
     * @param element UIElement that appears on the page.
     * @return true or false
     */
    

    /**
     * This method waits until page is loaded and element is visible,
     * then it checks whether the element is displayed.
     *
     * @param locator xpath in string.
     * @return true or false
     */
    protected boolean isDisplayed(final String locator) {
        try {
            return driver.findElement(By.xpath(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

   
    

    /**
     * This method waits until page is loaded, then it gets text value from the element.
     *
     * @param element UIElement that appears on the page.
     * @return String type value
     */
    protected String getTextOrValue(final WebElement element) {
        String result;
        result = element.getText();

        if (result != null && !result.isEmpty()) {
            return result;
        } else {
            result = element.getAttribute("value");
            return result;
        }

    }

    /**
     * This method waits until page is loaded, then it gets background color for the element.
     *
     * @param element UIElement that appears on the page.
     * @return String type value
     */
    protected String getBackGroundColor(final WebElement element) {
        waitUntilPageLoaded();
        return element.getCssValue("background-color");
    }

    /**
     * This method waits until page is loaded, then it gets text color for the element.
     * @param element UIElement that appears on the page.
     * @return String type value
     */
    protected String getTextColor(final WebElement element) {
        waitUntilPageLoaded();
        return element.getCssValue("color");
    }

    /**
     * This method waits until page is loaded, then it gets text value from hidden element.
     *
     * @param xpath xpath for UIElement that appears on the page.
     * @return String type value
     */
    protected String getTextFromHiddenElement(String xpath) {
        String result;
        result = driver.findElement(By.xpath(xpath)).getAttribute("textContent");
        if (result != null && !result.isEmpty()) {
            return result;
        } else {
            return result;
        }
    }

    protected String getValueJS(String locator) {

        String jsQuery = String.format("function getValue() {var value=%s.innerHTML;return value;};return getValue()", locator);

        return (String) jsExecutor.executeScript(jsQuery);

    }


    /**
     * This method waits until page is loaded, then it switches to another frame.
     *
     * @param element Element to another frame.
     */
    protected void switchToFrame(final WebElement element) {
        driver.switchTo().frame(element);
    }

    /**
     * This method waits until page is loaded, then it switches to default frame.
     */
    protected void switchFrameToDefault() {
        driver.switchTo().defaultContent();
    }


    protected void refreshPage() {
        driver.navigate().refresh();
        waitUntilPageLoaded();
    }

    /**
     * These are abstract methods: waits until page is loaded which should implement in subclass.
     */
    abstract protected void waitUntilPageLoaded();

    abstract protected void waitUntilPageLoaded(int seconds);

    protected Boolean isPageLoaded() {
        String jsQuery = "function pageLoaded() {var loadingStatus = (document.readyState == 'complete'); return loadingStatus; }; return pageLoaded()";
        return (Boolean) jsExecutor.executeScript(jsQuery);
    }

    public void navigateTo(String path) {
        try {
            URI uri = new URI(driver.getCurrentUrl());
            String domain = uri.getHost();
            driver.get("https://"+ domain + path);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
