package com.inetBankingClone.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public CellStyle style;

	public static int getRowCount(String xlfilepath, String xlsheet) throws IOException 
	{
		
		fis = new FileInputStream(xlfilepath);
		workbook =new XSSFWorkbook(fis);
		sheet=workbook.getSheet(xlsheet);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowcount;
		
	}
	
	public static int getCellCount(String xlfilepath,String xlsheet, int rownum) throws IOException
	{
		fis = new FileInputStream(xlfilepath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellcount;
		
	}
	
	public static String getCellData(String xlfilepath,String xlsheet, int rownum,int colnum) throws IOException
	{
		fis = new FileInputStream(xlfilepath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		DataFormatter formatter = new DataFormatter();
		String data;
		try
		{
		 data = formatter.formatCellValue(cell); // returns the formatted cell as a string regardless of the cell type. 
		}
		catch (Exception e)
		{
			data = "";
		}
		workbook.close();
		fis.close();
		return data;
		
	}
	public void setCellData(String xlfilepath,String xlsheet, int rownum,int colnum, String data) throws IOException
	{
		fis = new FileInputStream(xlfilepath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fos = new FileOutputStream(xlfilepath);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}
}
