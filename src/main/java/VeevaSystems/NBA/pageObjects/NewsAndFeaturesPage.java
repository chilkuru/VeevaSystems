package VeevaSystems.NBA.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import VeevaSystems.NBA.resources.base;
import VeevaSystems.NBA.utilities.SeleniumUtilities;

public class NewsAndFeaturesPage extends base {

	public WebDriver driver;
	private Actions act;
	private SeleniumUtilities selUtil;
	private By ThreeDotsItem = By.xpath("//li[@class=\"menu-item\"]//span[text()='...']");
	private By NewsAndFeaturesMenuItem = By.xpath(
			"//nav[@aria-label=\"header-secondary-menu\"]//a[@title=\"News & Features\" and text()='News & Features']");
	private By WarriorsModalCloseButton = By.xpath("//div[@role=\"dialog\"]//div[text()='x']");
	private By WarriorsCaptchaFrame = By.xpath("//iframe[@title='reCAPTCHA']");
	private By WarriorsCaptchaElement = By.xpath("//div[@id='rc-anchor-container']");
	private By videosAvailableInThePage = By.xpath(
			"//a[@href[contains(., '/warriors/videos/')] and @class='' or @data-testid='tile-featured-article-link']");
	private By videosMoreThan3DaysOld = By.xpath(
			"//a[@href[contains(., '/warriors/videos/')] and @class='' or @data-testid='tile-featured-article-link']/parent::div/following-sibling::div//time[@aria-label[contains(., '3 days ago')]]");
	private By videosTimeStamp = By.xpath(
			"//a[@href[contains(., '/warriors/videos/')] and @class='' or @data-testid='tile-featured-article-link']/parent::div/following-sibling::div//time");

	public NewsAndFeaturesPage(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;
		act = new Actions(this.driver);
		selUtil = new SeleniumUtilities(this.driver);

	}

	public void openNewsAndFeaturesPage() throws InterruptedException {
		selUtil.waitForPageLoad(driver);
		// Thread.sleep(5000);

		selUtil.waitForElementTobeLoaded(WarriorsCaptchaFrame, 30);
		selUtil.waitForElementTobeLoaded(WarriorsModalCloseButton, 10);
		selUtil.waitForElementTobeClickable(WarriorsModalCloseButton, 10, 500);
		driver.findElement(WarriorsModalCloseButton).click();

		// selUtil.waitForPageLoad(driver);
		selUtil.waitForElementTobeLoaded(ThreeDotsItem, 10);
		selUtil.waitForElementTobeClickable(ThreeDotsItem, 10, 500);
		act.moveToElement(driver.findElement(ThreeDotsItem)).build().perform();
		// Thread.sleep(2000);
		selUtil.waitForElementTobeLoaded(NewsAndFeaturesMenuItem, 10);
		act.moveToElement(driver.findElement(NewsAndFeaturesMenuItem)).click().build().perform();
		// selUtil.waitForPageLoad(driver);
	}

	public By getVideosAvailableInThePage() {
		return videosAvailableInThePage;
	}

	public void setVideosAvailableInThePage(By videosAvailableInThePage) {
		this.videosAvailableInThePage = videosAvailableInThePage;
	}

	public By getVideosMoreThan3DaysOld() {
		return videosMoreThan3DaysOld;
	}

	public void setVideosMoreThan3DaysOld(By videosMoreThan3DaysOld) {
		this.videosMoreThan3DaysOld = videosMoreThan3DaysOld;
	}

	public By getVideosTimeStamp() {
		return videosTimeStamp;
	}

	public void setVideosTimeStamp(By videosTimeStamp) {
		this.videosTimeStamp = videosTimeStamp;
	}

	public By getThreeDotsItem() {
		return ThreeDotsItem;
	}

	public void setThreeDotsItem(By threeDotsItem) {
		ThreeDotsItem = threeDotsItem;
	}

	public By getNewsAndFeaturesMenuItem() {
		return NewsAndFeaturesMenuItem;
	}

	public void setNewsAndFeaturesMenuItem(By newsAndFeaturesMenuItem) {
		NewsAndFeaturesMenuItem = newsAndFeaturesMenuItem;
	}

	public By getWarriorsModalCloseButton() {
		return WarriorsModalCloseButton;
	}

	public void setWarriorsModalCloseButton(By warriorsModalCloseButton) {
		WarriorsModalCloseButton = warriorsModalCloseButton;
	}

	public By getWarriorsCaptchaFrame() {
		return WarriorsCaptchaFrame;
	}

	public void setWarriorsCaptchaFrame(By warriorsCaptchaFrame) {
		WarriorsCaptchaFrame = warriorsCaptchaFrame;
	}

	public By getWarriorsCaptchaElement() {
		return WarriorsCaptchaElement;
	}

	public void setWarriorsCaptchaElement(By warriorsCaptchaElement) {
		WarriorsCaptchaElement = warriorsCaptchaElement;
	}

}
