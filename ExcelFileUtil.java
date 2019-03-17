package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ExcelFileUtil
{
	Workbook wb;

	//It will load all the sheets
	public ExcelFileUtil() throws Throwable
	{
		FileInputStream fis = new FileInputStream("./TestInputs/InputSheet.xlsx");
	
	wb= WorkbookFactory.create(fis);
	}
	
	public int rowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum();
		
	}
	public int colCount(String sheetName,int rowNo)
	{
		return wb.getSheet(sheetName).getRow(rowNo).getLastCellNum();		
	}

	public String getData(String sheetName,int rowNo,int colNo)
	{
		String data=" ";
		if(wb.getSheet(sheetName).getRow(rowNo).getCell(colNo).getCellType()==org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC)
		{
			int celldata= (int)(wb.getSheet(sheetName).getRow(rowNo).getCell(colNo).getNumericCellValue());
			
			data=String.valueOf(celldata);
		}
		else
		{
			data= wb.getSheet(sheetName).getRow(rowNo).getCell(colNo).getStringCellValue();
		}
		return data;
	
	}

	public void setData(String sheetname,int row,int col,String status) throws Throwable
	{
		Sheet sh=wb.getSheet(sheetname);
		Row rowNum=sh.getRow(row);
		
		Cell cell= rowNum.createCell(col);
		
		cell.setCellValue(status);
		
		if(status.equalsIgnoreCase("Pass"))
		{
			//create cell style
			
			CellStyle style=wb.createCellStyle();
			
			//create font
			Font font=wb.createFont();
			
			//apply color to font
			font.setColor(IndexedColors.GREEN.getIndex());
			
			//apply bold to text
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			
			//set font style
			
			style.setFont(font);
			rowNum.getCell(col).setCellStyle(style);
			
			
		}
		else
		if(status.equalsIgnoreCase("Fail"))
		{
			//create cell style
			
			CellStyle style=wb.createCellStyle();
			
			//create font
			Font font=wb.createFont();
			
			//apply color to font
			font.setColor(IndexedColors.RED.getIndex());
			
			//apply bold to text
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			
			//set font style
			
			style.setFont(font);
			rowNum.getCell(col).setCellStyle(style);
			
			
		}
		else
			
			if(status.equalsIgnoreCase("Not Executed"))
			{
				//create cell style
				
				CellStyle style=wb.createCellStyle();
				
				//create font
				Font font=wb.createFont();
				
				//apply color to font
				font.setColor(IndexedColors.BLUE.getIndex());
				
				//apply bold to text
				font.setBoldweight(font.BOLDWEIGHT_BOLD);
				
				//set font style
				
				style.setFont(font);
				rowNum.getCell(col).setCellStyle(style);
				
				
			}
		FileOutputStream fos= new FileOutputStream("./TestOutputs/Outputsheet.xlsx");
		wb.write(fos);
		fos.close();
	}
	
		
}
