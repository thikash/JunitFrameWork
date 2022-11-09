package org.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumWaits {

	public static void main(String[] args) throws InterruptedException {
//static wait--script execution will be more so it affect the performance
		Thread.sleep(3000);
		//Dynamic wait
		//implict wait-it will be wait all the elements present in a page 
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
		//Explict wait--we can give wait for the particular elements
	WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
	w.until(ExpectedConditions.elementToBeClickable(By.xpath("//tagname[@attributeName='attributeValue']")));
	//w.until(ExpectedConditions.invisibilityOf((WebElement) By.xpath("")));
	w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("")));
	
	//Fluent wait
	FluentWait<WebDriver> f = new FluentWait<WebDriver> (driver).withTimeout(Duration.ofSeconds(100)).pollingEvery(Duration.ofSeconds(5)).ignoring(Throwable.class);
	
	
	}

}
