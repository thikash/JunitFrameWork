package org.wrFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlipKart extends Brows {

	public static void main(String[] args) throws Throwable {
		bro("https://www.flipkart.com/");

		driver.findElement(By.xpath("//button[text()='✕']")).click();
		driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).sendKeys("oppo mobile",
				Keys.ENTER);
		
		// Take a screenshot from parent page
		TakesScreenshot snap = (TakesScreenshot) driver;
		File sr = snap.getScreenshotAs(OutputType.FILE);
		File ds = new File("C:\\SeleniumwithJava\\Maven\\snapshot\\filpkartParent.png");
		FileUtils.copyFile(sr, ds);
		
		System.out.println("******************************************************");
		
		// get the text all mobile text in the parent page write in the excel file
		File f = new File("C:\\SeleniumwithJava\\Maven\\Excel\\flipwrite.xlsx");
		FileOutputStream fo = new FileOutputStream(f);
		Workbook w = new XSSFWorkbook();
		Sheet s = w.createSheet("oppo");
		List<WebElement> mobName = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		for (int i = 0; i < mobName.size(); i++) {
			WebElement m = mobName.get(i);
			String mobText = m.getText();
			System.out.println("Mobile " + (i) + ":" + mobText);
			Thread.sleep(3000);
			Row r = s.createRow(i);
			Cell c = r.createCell(0);
			c.setCellValue(mobText);
		}
		w.write(fo);
        
		System.out.println("*******************************************************");
		//read the excel file in the filpwrite take the 3rd text in the file 
		FileInputStream fos = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fos);
		Sheet sh = wb.getSheet("oppo");
		Row ro = s.getRow(2);
		Cell getCell = ro.getCell(0);
		String stringCellValue = getCell.getStringCellValue();
		
		// take the 3rd mobile text in the excel file
		System.out.println("3rd mobile is:" + stringCellValue);
        
		//parent window
		String parent = driver.getWindowHandle();
        
		//click the 3rd mobile in the parent page
		driver.findElement(By.xpath("//div[text()='OPPO K10 (Blue Flame, 128 GB)']")).click();

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> child = new ArrayList<String>(windowHandles);
        
		//get the child window next to the parent page
		WebDriver childWindow = driver.switchTo().window(child.get(1));
		
		// take the screen shot child window
		TakesScreenshot shot = (TakesScreenshot) driver;
		File src = shot.getScreenshotAs(OutputType.FILE);
		File dst = new File("C:\\SeleniumwithJava\\Maven\\snapshot\\filpkartChild.png");
		FileUtils.copyFile(src, dst);
		
		// get the oppo mobile
		System.out.println("***********************************************************");
		String oppo = driver.findElement(By.xpath("//span[@class='B_NuCI']")).getText();
		System.out.println("Child window, mobile text is:" + oppo);
		System.out.println("**********************************************************");
		
		// compare 'oppo' text and 'StringCellValue' text
		if (oppo.equals(stringCellValue)) {
			System.out.println("both string are same");
		} else {
			System.out.println("sorry, please check your both string value");
		}
		System.out.println("****************The End********************");
		driver.quit();
	}

}
