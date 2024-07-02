package com.my.proper.project.myproperproject;

import java.time.LocalDateTime;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LogInTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// maximize the browser window

		driver.manage().window().maximize();

		// Open the login page
		driver.get("https://avlsb2clive.amnex.com/");
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		System.out.println(currentDateTime);
		
		
				
				

	}

}
