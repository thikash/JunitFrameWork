package com.broken;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks {
	static WebDriver driver;

	@BeforeClass
	public void url() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("https://www.ajio.com/");
		driver.manage().window().maximize();

	}

	@Test
	public void lk() {
		// get the total links in the ajio page
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Number of links in the Ajio WebPage:" + links.size());

		int i = 0;
		int j = 0;
		int k = 0;
		// iterate the links
		for (WebElement link : links) {
//store the link in String
			String url = link.getAttribute("href");

//check the url is null or url is empty. if any one is true the condition is true
			if (url == null || url.isEmpty()) {

				// increment the i value
				i++;

				continue;
			}
			try {

				HttpURLConnection huc = (HttpURLConnection) (new URL(url).openConnection());
				huc.connect();
				// check the getResponse code condition above equal to 400 is bad connection
				if (huc.getResponseCode() >= 400) {
					System.out.println("I'm Broken");
					// getResponse code
					int code = huc.getResponseCode();
					// getResponseMessage
					String message = huc.getResponseMessage();

					System.out.println(code + '\t' + url + '\t' + message);
					j++;

				} else {

					k++;
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		System.out.println("valid url count :" + i);
		System.out.println("empty url count :" + k);
		System.out.println("broken url count :" + j);

	}

	@AfterClass
	public void close() {
		driver.close();
	}

}
