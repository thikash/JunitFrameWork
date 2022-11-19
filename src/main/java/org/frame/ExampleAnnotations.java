package org.testNg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExampleAnnotations {
	@DataProvider(name = "aathi")
	public Object[][] name() {
		return new Object[][] { { "https://www.flipkart.com/" } };

	}

	static WebDriver driver;

	@Test(priority = -5, dataProvider = "aathi",groups="aathi")
	public static void Url(String n) {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get(n);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@DataProvider(name = "product")
	public Object[][] mobile() {
		return new Object[][] { { "oppo k10" } };
	}

	@BeforeMethod
	public void before() {
		System.out.println("BeforeMethod");

	}

	@AfterMethod
	public void after() {
		System.out.println("AfterMethod");
	}

	@Test(priority = -1, invocationCount = 5,groups="ragul")
	public void smart() {
		System.out.println("smart Phone");
		System.out.println("mobile");
	}

	@Test(priority = 0,groups="aathi")
	@Parameters({ "user", "p" })
	public void login(String uName, String pass) {
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(uName);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(pass);
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
	}

	@Test(priority = 3, dataProvider = "product",groups="aathi")
	public void search(String a) {
		System.out.println("Search product");
		driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).sendKeys(a, Keys.ENTER);

	}

	@Test(priority = 4,groups="aathi")
	public void fileWrite() throws Throwable {
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
		fo.close();
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

	@Test(priority = 9,groups="ragul")
	public void fileRead() throws Throwable {
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
