package org.frame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Junitj {
	static WebDriver driver;

	@BeforeClass
	public static void test1() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("before class");
	}

	@Before
	public void test2() {
		System.out.println("before");
	}

	@AfterClass
	public static void test3() throws InterruptedException {
		System.out.println("before class");
		Thread.sleep(3000);
		driver.quit();
	}

	@After
	public void test4() {
		System.out.println("After");
	}

	@Test
	public void test5() {
		System.out.println("Login FlipKart");
		WebElement UserName = driver.findElement(By.xpath("(//input[@type='text'])[2]"));

		UserName.sendKeys("8148143905");

		WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys("Thikash@905");
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();

	}

	@Test
	public void test6() {
		System.out.println("search product");
		driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).sendKeys("oppo mobile",
				Keys.ENTER);
	}

	@Test
	public void test7() throws Throwable {
		System.out.println("Excel File--write");
		File f = new File("C:\\SeleniumwithJava\\Maven\\Excel\\flipwrite.xlsx");
		FileOutputStream fo = new FileOutputStream(f);
		Workbook w = new XSSFWorkbook();
		Sheet s = w.createSheet("oppo");
		List<WebElement> mobName = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		for (int i = 0; i < mobName.size(); i++) {
			WebElement m = mobName.get(i);
			String mobText = m.getText();
			System.out.println("Mobile " + (i) + ":" + mobText);
			
			Row r = s.createRow(i);
			Cell c = r.createCell(0);
			c.setCellValue(mobText);
		}
		w.write(fo);
	}

	@Test
	public void test8() throws Throwable {
		System.out.println("notepad---write");
		FileWriter fw = new FileWriter("C:\\SeleniumwithJava\\Maven\\Excel\\fliptxt.txt");
		driver.findElement(By.xpath("//div[text()='OPPO K10 (Blue Flame, 128 GB)']")).click();

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> child = new ArrayList<String>(windowHandles);

		driver.switchTo().window(child.get(1));

		String oppo = driver.findElement(By.xpath("//span[@class='B_NuCI']")).getText();
		System.out.println("Child window, mobile text is:" + oppo);
		fw.write(oppo);
		fw.close();

	}

	@Test
	public void test9() throws Throwable {
		System.out.println("Excel File--read");
		File f = new File("C:\\SeleniumwithJava\\Maven\\Excel\\flipwrite.xlsx");
		FileInputStream fi = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fi);
		Sheet s = w.getSheet("oppo");
		Row r = s.getRow(2);
		Cell c = r.getCell(0);
		String scv = c.getStringCellValue();
		System.out.println("get the 3rd mobile:" + scv);
		System.out.println("notepad---read");
		FileReader fr = new FileReader("C:\\SeleniumwithJava\\Maven\\Excel\\fliptxt.txt");
		BufferedReader buff = new BufferedReader(fr);
		String t;
		while ((t = buff.readLine()) != null) {
			System.out.println(t);
			Assert.assertEquals(scv, t);
		}

	}

}
