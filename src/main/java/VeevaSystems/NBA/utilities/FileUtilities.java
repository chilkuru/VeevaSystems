package VeevaSystems.NBA.utilities;

import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import VeevaSystems.NBA.resources.base;

public class FileUtilities extends base {

	public WebDriver driver;

	public FileUtilities(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;

	}

	/**
	 * Append the CSV file with String @param dataInput
	 * @param dataInput
	 * @throws IOException
	 */
	public void writeCSVFile(String dataInput) throws IOException {

		String csvFilePath = System.getProperty("user.dir") + "\\target\\data.csv";
		try (FileWriter writer = new FileWriter(csvFilePath, true)) { // true enables append mode
			writer.append(dataInput);
			// System.out.println("Data successfully written to " + csvFilePath);
		} catch (IOException e) {
			System.err.println("Error while writing to CSV file: " + e.getMessage());
		}
	}

	/**
	 * Clear the contents of the CSV file
	 * @throws IOException
	 */
	public void clearCSVFileContents() throws IOException {

		String csvFilePath = System.getProperty("user.dir") + "\\target\\data.csv";
		try (FileWriter writer = new FileWriter(csvFilePath, false)) {
			// Opening the file in non-append mode clears its content
			// System.out.println("File contents cleared successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred while clearing the file: " + e.getMessage());
		}
	}

	/**
	 * 
	 * Append new line into the CSV file
	 * @throws IOException
	 */
	public void writeNewLineCSVFile() throws IOException {

		String csvFilePath = System.getProperty("user.dir") + "\\target\\data.csv";
		try (FileWriter writer = new FileWriter(csvFilePath, true)) { // true enables append mode
			writer.append("\n");
			// System.out.println("Data successfully written to " + csvFilePath);
		} catch (IOException e) {
			System.err.println("Error while writing to CSV file: " + e.getMessage());
		}
	}

}
