package org.wrFile;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Brows {
	static WebDriver driver;
public static void bro(String url) {
	WebDriverManager.chromedriver().setup();
	 driver = new ChromeDriver();
	 driver.get(url);
    driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

}
	

}
