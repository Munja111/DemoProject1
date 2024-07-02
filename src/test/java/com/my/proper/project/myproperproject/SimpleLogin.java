package com.my.proper.project.myproperproject;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SimpleLogin {
	static String Encodedpwd = "Um9oaXRAMTIz";

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://avlsb2clive.amnex.com/");

		WebElement usernameInput = driver.findElement(By.id("username"));
		WebElement passwordInput = driver.findElement(By.id("password"));
		usernameInput.sendKeys("Rohit");
		passwordInput.sendKeys(getDecodedpwd());
		WebElement loginButton = driver.findElement(By.xpath("//div[@class='form-group submit_block']"));
		loginButton.click();
		WebElement clickLink = driver.findElement(
				By.cssSelector("a[href='https://avlsb2clive.amnex.com/Dashboard/FleetOverviewDashboard_NewData']"));
		clickLink.click();

		// P&S
		// driver.findElement(By.xpath("//img[@src='/Images/icon3.svg']")).click();

		String originalHandle = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement homePageElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='lblTotalCounts']")));
		if (homePageElement.isDisplayed()) {
			System.out.println("Welcome to the homepage of AVLS");
		} else {
			System.out.println("Homepage not displayed");
		}
		
		//NEW CHANGESS

		// Get current date
        LocalDate currentDate = LocalDate.now();

        // Locate the element displaying the date and time
        WebElement dateTimeElement = driver.findElement(By.xpath("//div[@id='divtime']"));

        // Extract date and time text from the element
        String dateTimeText = dateTimeElement.getText();

        // Define the formatter for the date and time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // Parse the date string into LocalDate
        LocalDate displayedDate = LocalDate.parse(dateTimeText.split(" ")[0], formatter);

		// Compare current date with displayed date
		if (currentDate.equals(displayedDate)) {
			System.out.println("Login Successful!");
			System.out.println(displayedDate);

			// Additional actions can be performed here, like logging in
		} else {
			System.out.println("Date verification failed!");
			// Handle the case where dates don't match
		}

		/*
		 * WebElement checkLable = driver.findElement(By.
		 * xpath("//span[@class='LabelCount1' and text()=' Running: ']"));
		 * 
		 * WebElement checkValue = checkLable.findElement(By.xpath(
		 * "following-sibling::span[@class='LabelCount2']/b"));
		 */
		// WebElement count =
		// driver.findElement(By.xpath("//span[contains(text(),'Running:')]"));
		// //span[@class='LabelCount1' and text()=' Running: ']
		// String value = count.getText();
		// System.out.println("Value of the element: " + value);
		/*
		 * int value = Integer.parseInt(checkValue.getText().trim());
		 * 
		 * // Check if the value is greater than 1 if (value > 1) {
		 * System.out.println("Value is greater than 50"); } else {
		 * System.out.println("Value is not greater than 150"); }
		 */
	}

	public static String getDecodedpwd() {
		return new String(Base64.getDecoder().decode(Encodedpwd.getBytes()));
	}

}
