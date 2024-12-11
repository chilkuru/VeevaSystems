package VeevaSystems.NBA.Warriors;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import VeevaSystems.NBA.pageObjects.NewsAndFeaturesPage;
import VeevaSystems.NBA.resources.base;
import VeevaSystems.NBA.utilities.SeleniumUtilities;
/**
 * @author Ragunath Chilkuru
 *
 */
public class TestCase2CP extends base {
	public WebDriver driver;
	private NewsAndFeaturesPage nfp;
	private SeleniumUtilities selUtil;

	/**
	 * Initialize Driver and Page Objects
	 * 
	 * @throws IOException
	 */
	@BeforeTest
	public void initialize() throws IOException {

		driver = initializeDriver();
		driver.get(getURL());
		nfp = new NewsAndFeaturesPage(driver);
		selUtil = new SeleniumUtilities(driver);

	}

	/**
	 * 
	 * Test to find total number of videos and find videos older than 3 days Test
	 * Steps outlined as below
	 * 
	 * From the CP home page , hover on menu Item >> click on New & Features Count
	 * total number of Videos Feeds and count the videos feeds those are present in
	 * the page >= 3d
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test

	public void findVideosCountAndVideosAgingMoreThan3Days() throws IOException, InterruptedException {

		nfp.openNewsAndFeaturesPage();
		selUtil.waitForElementTobeLoaded(nfp.getVideosAvailableInThePage(), 10);
		List<WebElement> elements = driver.findElements(nfp.getVideosAvailableInThePage());
		System.out.println("Total number of videos found is " + elements.size());

		// Get the Videos Count older than 3 days
		// Method is parameterized to get videos older than x days
		getVideosOlderThanOrEqualToAgeInDays(3);

	}

	@AfterTest
	public void teardown() {

		driver.quit();

	}

	/**
	 * 
	 * Method to get the videos which are older than @param ageInDays
	 *
	 * @param ageInDays
	 */
	private void getVideosOlderThanOrEqualToAgeInDays(int ageInDays) {
		// TODO Auto-generated method stub
		List<WebElement> elements = driver.findElements(nfp.getVideosTimeStamp());
		//System.out.println("Total number of videos are " +elements.size());
		int count = 0;
		for (WebElement ele : elements) {

			selUtil.scrolltoElement(ele);
			String ariaLabel = ele.getAttribute("aria-label");

			if (ariaLabel != null && (ariaLabel.contains("days ago") || ariaLabel.contains("day ago"))) {
				// Extract the number of days from the text
				int daysAgo = Integer.parseInt(ariaLabel.split(" ")[0]); // Assuming format "X days ago"

				// Check if older than ageInDays days
				if (daysAgo >= ageInDays) {
//					System.out.println(
//							"Found a video older than " + ageInDays + " days: " + ele.getAttribute("aria-label"));
					count++;
				}
			}
		}

		System.out.println("Total number of videos older than " + ageInDays + " days is : " + count);

	}

}
