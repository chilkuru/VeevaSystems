package VeevaSystems.NBA.utilities;

import java.time.Duration;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import VeevaSystems.NBA.resources.base;

public class SeleniumUtilities extends base {

	public WebDriver driver;
	private Actions act;

	public SeleniumUtilities(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;
		this.act = new Actions(driver);
	}

	public void switchtoNewWindowHandleOpened(String originalWindow) {

		Set<String> allWindowHandles = driver.getWindowHandles();

		// Switch to the new window which is newly opened
		for (String windowHandle : allWindowHandles) {
			// String title = driver.getTitle();
			// System.out.println("Window Handle: " + windowHandle + " | Title: " + title);
			// log.info("Window Handle: " + windowHandle + " | Title: " + title);

			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

	}

	public void waitForElementTobeClickable(final By element, int maxWaitTime, int pollingInterval) {
		// Define a FluentWait instance
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)) // Maximum wait
																										// time
				.pollingEvery(Duration.ofMillis(500)) // Polling interval
				.ignoring(Exception.class); // Ignore exceptions during polling

		// Wait for the element to be clickable
		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement ele = driver.findElement(element); // Replace with your element locator
				if (ele.isDisplayed() && ele.isEnabled()) {
					return ele;
				}
				return null;
			}
		});
	}

	public void waitForElementTobeLoaded(final By element, int timeOutInSeconds) {
		// Use WebDriverWait to wait for a specific element to load
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds); // Timeout in seconds

		wait.until(ExpectedConditions.visibilityOfElementLocated(element));

	}

	public void waitForModalToClose(final By element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
//	        boolean isModalGone = wait.until(
//	                ExpectedConditions.invisibilityOfElementLocated(wsp.ModalPopupShoppingWarriors));

			boolean isModalGone = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();

			if (!isModalGone) {
				log.info("Modal disappeared.");
			} else {
				log.info("Modal is still visible.");
			}

		} catch (Exception e) {
			log.info("An error occurred: " + e.getMessage());
		}
	}

	public void moveToElement(final By element) {

		act.moveToElement(driver.findElement(element)).build().perform();

	}

	public void moveToElementAndClick(final By element) {

		act.moveToElement(driver.findElement(element)).click().build().perform();

	}

	public void waitForPageLoad(WebDriver driver) {

		// Create WebDriverWait instance
		WebDriverWait wait = new WebDriverWait(driver, 10); // 10-second timeout

		// Wait for the page to load completely using ExpectedCondition
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver wd) {
				return ((JavascriptExecutor) wd).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		});

		((JavascriptExecutor) driver).executeScript("return document.readyState");
		// Object state = ((JavascriptExecutor) driver).executeScript("return
		// document.readyState");
		// System.out.println("Document Ready State: " + state);
	}

	public String getElementTextParentChild(WebDriver driver, WebElement parentElement, By locatorChild) {
		try {
			WebElement element = parentElement.findElement(locatorChild);
			return element.getText();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// Element not found, return an empty string
			return "";
		}
	}

	public void scrolltoElement(WebElement ele) {

		try {
			// Scroll to the element
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);

			// System.out.println("Element scrolled into view!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
