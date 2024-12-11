package VeevaSystems.NBA.Warriors;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import VeevaSystems.NBA.pageObjects.WarriorsShopPage;
import VeevaSystems.NBA.resources.base;
import VeevaSystems.NBA.utilities.FileUtilities;
import VeevaSystems.NBA.utilities.SeleniumUtilities;

/**
 * @author Ragunath Chilkuru
 *
 */
public class TestCase1CP extends base {
	public WebDriver driver;
	private WarriorsShopPage wsp;
	private SeleniumUtilities selUtil;
	private FileUtilities fileUtil;

	/**
	 * Initialize Driver and Page Objects
	 * 
	 * @throws IOException
	 */
	@BeforeTest
	public void initialize() throws IOException {

		driver = initializeDriver();
		driver.get(getURL());
		wsp = new WarriorsShopPage(driver);
		selUtil = new SeleniumUtilities(driver);
		fileUtil = new FileUtilities(driver);
	}

	/**
	 * 	 Test to find all the jackets from all paginated pages
	 *   Fill the each Jacket Price, Title and Top Seller message into data.csv file in the target folder
	 *  Steps outlined as below
	 * 
	 *  From the CP home page , go to >> Shop Menu >> Men’s
	 *	Find all Jackets ( from all paginated pages)
	 *	Store each Jacket Price, Title and Top Seller message to a text file
	 * 	Attach the text file to the report
	 * 
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */

	@Test

	public void fillJacketsDataForCoreProduct() throws IOException, InterruptedException {

		wsp.openMensJacketCatalogue();
		
		//Switch to new window which gets opened
		selUtil.switchtoNewWindowHandleOpened(driver.getWindowHandle());
		selUtil.waitForModalToClose(wsp.getClosePopUpButtonShoppingWarriors());
		selUtil.moveToElement(wsp.getMensShoppingWarriorsMenuItem());
		
		
		//Get the filtered items by Jacket
		selUtil.waitForElementTobeClickable(wsp.getJacketsShoppingWarriorsMenuItem(), 10, 500);
		selUtil.moveToElementAndClick(wsp.getJacketsShoppingWarriorsMenuItem());
		
		//Clearing any existing data before filling the same
		fileUtil.clearCSVFileContents();
		
		//Fill the data.csv file with the Jacket Parameters
		fillTheDataOfMerchandise();

	}

	@AfterTest
	public void teardown() {

		driver.quit();

	}

	/**
	 * Check the visibility of the lastPage of the Navigation 
	 * 
	 * @param elementLocator
	 * @return
	 */
	private boolean navigateToLastOflPaginatedPages(By elementLocator) {

		// Initialize a variable to track visibility
		boolean isVisible = false;

		try {
			WebElement element = driver.findElement(elementLocator);
			isVisible = element.isDisplayed();
		} catch (Exception e) {
			// Element is not yet visible or not found
			//System.out.println("Element is not visible yet...");
		}

		return isVisible;

	}

	/**
	 * Method to fill the data of each Merchandise Price, Title and Top Seller 
	 * in the data.csv file for all the paginated pages
	 * @throws IOException
	 */
	private void fillTheDataOfMerchandise() throws IOException {

		List<WebElement> elements;

		while (!navigateToLastOflPaginatedPages(wsp.getNavigationEndPageDisabled())) {

			elements = driver.findElements(wsp.getEachJacketParentElement());
			//System.out.println("Total number of elemnts found is " + elements.size());

			for (WebElement ele : elements) {

				selUtil.scrolltoElement(ele);
				wsp.fillDetailsOfEachMerchandise(ele);
			}

			selUtil.moveToElementAndClick(wsp.getNavigationBottomNextPage());
		}

		// Fill the data in the last page of the merchandise
		elements = driver.findElements(wsp.getEachJacketParentElement());
		for (WebElement ele : elements) {

			selUtil.scrolltoElement(ele);
			wsp.fillDetailsOfEachMerchandise(ele);
		}

	}

}
