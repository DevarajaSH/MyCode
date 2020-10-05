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
	//HSSFWorkbook wb;
	//HSSFSheet sheet;
	Workbook wb;
	Sheet sheet;
	Workbook Row;
	Cell Createcell;
	FileInputStream fsIP;
	String abPath;

	public ExcelConfig(String path) 
	{
		abPath = path;
		try 
		{
			fsIP = new FileInputStream(new File(path)); // Read the spreadsheet
														// that needs to be
														// updated
			wb=WorkbookFactory.create(fsIP);
			
		} catch (Exception e) 
		{
			System.out.println("Unale to load file " + e.getMessage());
		}
	}

	public String getData(String sheetname, int row, int column) {
		DataFormatter formatter = new DataFormatter(); // creating formatter
														// using the default
														// locale
		// Cell cell = wb.getSheetAt(sheetnumber).getRow(row).getCell(column);
		
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
      //  System.out.println("In ExcelConfig.SetData");
		//System.out.println("Sheet Name" + sheetname);
		///System.out.println("Row" + row);
		//System.out.println("column" + column);
		//System.out.println("Value" + value);
		// cell=null;
		// Cell cell = null;
		sheet = wb.getSheet(sheetname);
		Createcell = BlankRecord;
		Createcell = sheet.getRow(row).getCell(column);
		Createcell.setCellValue(value);
		fsIP.close();
		FileOutputStream fileOut = new FileOutputStream(new File(abPath));
		// write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
}