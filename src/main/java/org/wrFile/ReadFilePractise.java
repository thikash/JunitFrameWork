package org.wrFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFilePractise {

	public static void main(String[] args) throws Throwable {
		File f = new File("C:\\SeleniumwithJava\\Maven\\Excel\\ReadFile.xlsx");
		FileInputStream fi = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fi);
		Sheet s = w.getSheet("Sheet1");
		for (int i = 0; i < s.getPhysicalNumberOfRows(); i++) {
			Row r = s.getRow(i);
			for (int j = 0; j < r.getPhysicalNumberOfCells(); j++) {
				Cell c = r.getCell(j);
				int cellType = c.getCellType();
				if (cellType == 1) {
					String value = c.getStringCellValue();
					System.out.println(value);
					
				} else if (cellType == 0) {
					if (DateUtil.isCellDateFormatted(c)) {
						Date d = c.getDateCellValue();
						SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
						String format = sd.format(d);
						System.out.println(format);
					} else {
						double d = c.getNumericCellValue();
						long l = (long) d;
						String valueOf = String.valueOf(l);
						System.out.println(valueOf);
						
					}
					

				}
			}

		}
	}

}
