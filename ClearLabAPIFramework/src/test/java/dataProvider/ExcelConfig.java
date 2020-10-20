package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelConfig {

	private static final Cell BlankRecord = null;
	Workbook wb;
	Sheet sheet;
	Cell Createcell;
	FileInputStream fs = null;
	WorkbookFactory test;
	String abPath;

	public ExcelConfig(String path) 
	{
		abPath = path;
		try 
		{
			fs = new FileInputStream(new File(path)); // Read the spreadsheet
														// that needs to be
														// updated
			wb=WorkbookFactory.create(fs);//create(FileStream);
			
		} catch (Exception e) 
		{
			System.out.println("Unable to load file " + e.getMessage());
		}
	}

	public String getData(String sheetname, int row, int column) {
		DataFormatter formatter = new DataFormatter(); // creating formatter
														// using the default
														// locale
		try
		{
			Cell cell = wb.getSheet(sheetname).getRow(row).getCell(column);
			String data = formatter.formatCellValue(cell);
			return data;
		}
		catch (Exception NullPointerException)
		{
			return null;
		}
			
	}

	public int getrows(String sheetname) {
		sheet = wb.getSheet(sheetname);
	
		try
		{
			int numberOfRows = sheet.getPhysicalNumberOfRows();
			return numberOfRows;
		}
		catch (Exception NullPointerException)
		{
			System.out.println("ExcelConfig.getrows: Exception while getting no of rows");
			return 0;				
		}
		
	}

	public int getcols(String sheetname) 
	{
		sheet = wb.getSheet(sheetname);
		try
		{
			int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
			return noOfColumns;
		}
		catch (Exception NullPointerException)
		{
			System.out.println("ExcelConfig.getcols: Exception while getting no of cols");
			return 0;				
		}
	}

	public void setData(String sheetname, int row, int column, String value) throws IOException {
 		sheet = wb.getSheet(sheetname);
		Createcell = BlankRecord;
		Createcell = sheet.getRow(row).getCell(column);
		Createcell.setCellValue(value);
		//fsIP.close();
		FileOutputStream fileOut = new FileOutputStream(new File(abPath));
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
}