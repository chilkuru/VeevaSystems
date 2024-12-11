package VeevaSystems.NBA.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class base {

	public WebDriver driver;
	public Properties prop;
	public Logger log;

	public base() {
		log = LogManager.getLogger(base.class.getName());
	}

	// Initialize Driver
	public WebDriver initializeDriver() throws IOException {

		getPropertiesFile();
		//String browserName = prop.getProperty("browser");
		String browserName = getValueFromJSONFile("browser");

		if (browserName.equals("chrome")) {

			// execute in chrome driver
//			System.setProperty("webdriver.chrome.driver",
//					System.getProperty("user.dir") + "\\src\\main\\java\\VeevaSystems.NBA.resources\\chromedriver.exe");

			// Alternate solution to using the chromedriver.exe . This will execute on the
			// latest version of chromedriver
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {
//			// execute in firefox driver 
//			System.setProperty("webdriver.gecko.driver",
//					System.getProperty("user.dir") + "\\src\\main\\java\\VeevaSystems.NBA.resources\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} 

		// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		// Maximize current window
		driver.manage().window().maximize();
		return driver;
	}

	public String getURL() throws IOException {

		//String url = prop.getProperty("url");
		String url = getValueFromJSONFile("url");
		return url;

	}

	public Properties getPropertiesFile() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\VeevaSystems\\NBA\\resources\\data.properties");

		prop.load(fis);
		return prop;

	}
	
	public String getValueFromJSONFile(String key) throws IOException {
        // Specify the path to your JSON file
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\VeevaSystems\\NBA\\resources\\warriors_data.json"; // Change this to your JSON file's location

        // Initialize the ObjectMapper (Jackson library)
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File(filePath));
        JsonNode valueNode = rootNode.get(key);
        
		return valueNode.asText();

	}
	
	

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}

}
