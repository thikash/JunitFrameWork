package org.wrFile;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

public class WriteExcel {

	public static void main(String[] args) throws Throwable {
File f = new File("C:\\SeleniumwithJava\\Maven\\Excel\\PractiseFile.xlsx");
FileOutputStream fo = new FileOutputStream (f);
Workbook w = new XSSFWorkbook();
Sheet s = w.createSheet("write");


Row r = s.createRow(0);
Cell c = r.createCell(1);
c.setCellValue("Ragul");


w.write(fo);
	}

}
