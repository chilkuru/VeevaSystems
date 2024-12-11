package VeevaSystems.NBA.pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import VeevaSystems.NBA.resources.base;
import VeevaSystems.NBA.utilities.FileUtilities;
import VeevaSystems.NBA.utilities.SeleniumUtilities;

public class WarriorsShopPage extends base {

	public WebDriver driver;
	private Actions act;
	private SeleniumUtilities selUtil;
	private FileUtilities fileUtil;
	private By ShopMenuItem = By.xpath(
			"//li[@data-testid[contains(., 'shop.warriors.com')] and @role='menuitem']//span[contains(text(),'Shop')]");
	private By MensMenuItem = By.xpath(
			"//li[@data-testid[contains(., 'shop.warriors.com')] and @role='menuitem']//span[contains(text(),'Shop')]/parent::a/following-sibling::nav//a[text()=\"Men's\"]");
	private By WarriorsModalCloseButton = By.xpath("//div[@role=\"dialog\"]//div[text()='x']");
	private By WarriorsCaptchaFrame = By.xpath("//iframe[@title='reCAPTCHA']");
	private By WarriorsCaptchaElement = By.xpath("//div[@id='rc-anchor-container']");
	private By MensShoppingWarriorsMenuItem = By.xpath("//a[@aria-label='Men' and text()='Men']");
	private By JacketsShoppingWarriorsMenuItem = By.xpath(
			"//a[@aria-label='Men' and text()='Men']/parent::li//div[@class='dropdown-link-text' and text()='Jackets']");
	private By ClosePopUpButtonShoppingWarriors = By.xpath("//a[@aria-label='Close Pop-Up' and @role='button']");
	private By ModalPopupShoppingWarriors = By.xpath("//img[@loading='lazy' and @alt='30% off Sitewide']");
	private By EachJacketParentElement = By.xpath("//div[@class=\"product-card row\"]");
	private By priceValue = By.xpath(".//span[@class='sr-only']");
	private By titleOfMerchandise = By.xpath(".//a[@title]");
	private By topSellerMessage = By.xpath(".//div[@class='product-vibrancy-container']");
	private By navigationEndPageDisabled = By
			.xpath("//div[@class='product-grid-bottom-area']//li[@class='next-page disabled']");
	private By navigationBottomNextPage = By
			.xpath("//div[@class='product-grid-bottom-area']//i[@class='icon icon-right-arrow']");

	public WarriorsShopPage(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;
		act = new Actions(this.driver);
		selUtil = new SeleniumUtilities(this.driver);
		fileUtil = new FileUtilities(driver);
	}

	/**
	 * Method to capture the detail of each Jacket attributes and fill into the CSV file
	 * 
	 * @param parentMerchandiseElement
	 * @throws IOException
	 */
	public void fillDetailsOfEachMerchandise(WebElement parentMerchandiseElement) throws IOException {
		String price = parentMerchandiseElement.findElement(priceValue).getText();
		String title = parentMerchandiseElement.findElement(titleOfMerchandise).getText();

		// Since TopSeller Message Detail is optional, return Empty String if not
		// available

		String topSellerMessageDetail = selUtil.getElementTextParentChild(driver, parentMerchandiseElement,
				topSellerMessage);

		fileUtil.writeCSVFile(price + ",");
		fileUtil.writeCSVFile(title + ",");
		fileUtil.writeCSVFile(topSellerMessageDetail);
		fileUtil.writeNewLineCSVFile();
		
//	    System.out.println(price);
//		System.out.println(title);
//		System.out.println(topSellerMessageDetail);
	}

	/**
	 * 
	 * Navigate to the Shop -> Men's Page
	 * Filter the merchandise with only Jacket items
	 * 
	 * @throws InterruptedException
	 */
	public void openMensJacketCatalogue() throws InterruptedException {
		selUtil.waitForPageLoad(driver);
		//Thread.sleep(5000);
		selUtil.waitForElementTobeLoaded(WarriorsCaptchaFrame, 30);
		selUtil.waitForElementTobeLoaded(WarriorsModalCloseButton, 10);
		selUtil.waitForElementTobeClickable(WarriorsModalCloseButton, 10, 500);
		driver.findElement(WarriorsModalCloseButton).click();

		// selUtil.waitForPageLoad(driver);
		selUtil.waitForElementTobeLoaded(getShopMenuItem(), 10);
		act.moveToElement(driver.findElement(ShopMenuItem)).build().perform();
		// Thread.sleep(2000);
		selUtil.waitForElementTobeLoaded(getMensMenuItem(), 10);
		act.moveToElement(driver.findElement(MensMenuItem)).click().build().perform();
		// selUtil.waitForPageLoad(driver);
	}

	public By getShopMenuItem() {
		return ShopMenuItem;
	}

	public void setShopMenuItem(By shopMenuItem) {
		ShopMenuItem = shopMenuItem;
	}

	public By getMensMenuItem() {
		return MensMenuItem;
	}

	public void setMensMenuItem(By mensMenuItem) {
		MensMenuItem = mensMenuItem;
	}

	public By getWarriorsModalCloseButton() {
		return WarriorsModalCloseButton;
	}

	public void setWarriorsModalCloseButton(By warriorsModalCloseButton) {
		WarriorsModalCloseButton = warriorsModalCloseButton;
	}

	public By getMensShoppingWarriorsMenuItem() {
		return MensShoppingWarriorsMenuItem;
	}

	public void setMensShoppingWarriorsMenuItem(By mensShoppingWarriorsMenuItem) {
		MensShoppingWarriorsMenuItem = mensShoppingWarriorsMenuItem;
	}

	public By getJacketsShoppingWarriorsMenuItem() {
		return JacketsShoppingWarriorsMenuItem;
	}

	public void setJacketsShoppingWarriorsMenuItem(By jacketsShoppingWarriorsMenuItem) {
		JacketsShoppingWarriorsMenuItem = jacketsShoppingWarriorsMenuItem;
	}

	public By getClosePopUpButtonShoppingWarriors() {
		return ClosePopUpButtonShoppingWarriors;
	}

	public void setClosePopUpButtonShoppingWarriors(By closePopUpButtonShoppingWarriors) {
		ClosePopUpButtonShoppingWarriors = closePopUpButtonShoppingWarriors;
	}

	public By getModalPopupShoppingWarriors() {
		return ModalPopupShoppingWarriors;
	}

	public void setModalPopupShoppingWarriors(By modalPopupShoppingWarriors) {
		ModalPopupShoppingWarriors = modalPopupShoppingWarriors;
	}

	public By getEachJacketParentElement() {
		return EachJacketParentElement;
	}

	public void setEachJacketParentElement(By eachJacketParentElement) {
		EachJacketParentElement = eachJacketParentElement;
	}

	public By getPriceValue() {
		return priceValue;
	}

	public void setPriceValue(By priceValue) {
		this.priceValue = priceValue;
	}

	public By getTitleOfMerchandise() {
		return titleOfMerchandise;
	}

	public void setTitleOfMerchandise(By titleOfMerchandise) {
		this.titleOfMerchandise = titleOfMerchandise;
	}

	public By getTopSellerMessage() {
		return topSellerMessage;
	}

	public void setTopSellerMessage(By topSellerMessage) {
		this.topSellerMessage = topSellerMessage;
	}

	public By getNavigationEndPageDisabled() {
		return navigationEndPageDisabled;
	}

	public void setNavigationEndPageDisabled(By navigationEndPageDisabled) {
		this.navigationEndPageDisabled = navigationEndPageDisabled;
	}

	public By getNavigationBottomNextPage() {
		return navigationBottomNextPage;
	}

	public void setNavigationBottomNextPage(By navigationBottomNextPage) {
		this.navigationBottomNextPage = navigationBottomNextPage;
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
