package com.olympus.olyutil.excel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.olympus.olyutil.Olyutil;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

 
public class OlyExcel {
	
	/***********************************************************************************************************************************/

	public static Map<String, CellStyle> createStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap();
        CellStyle style;
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)18);
        titleFont.setBold(true);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(titleFont);
        styles.put("title", style);

        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short)11);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(monthFont);
        style.setWrapText(true);
        styles.put("header", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("cell", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put("formula", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put("formula_2", style);

        return styles;
    }
	/***********************************************************************************************************************************/
	public static XSSFSheet newWorkSheet(XSSFWorkbook workbook, String label) {

		XSSFSheet sheet = workbook.createSheet(label);
		return sheet;
	}
	/***********************************************************************************************************************************/
	public static XSSFWorkbook newWorkbook() {

		XSSFWorkbook workbook = new XSSFWorkbook();
		return workbook;
	}
	
	/***********************************************************************************************************************************/
	public static void setWorkSheetBorder(CellStyle style) {
		
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		
	}
	
	/***********************************************************************************************************************************/
	// call method: setWorkSheetFont(font, 12, "Times New Roman", true, IndexedColors.BLACK.getIndex());
	public static void setWorkSheetFont(Font font, short fontSize, String fontType, boolean fontBold, short fontColor) {
		// fontColor ->  IndexedColors.BLACK.getIndex()
		font.setFontHeightInPoints((short) fontSize);
        font.setFontName(fontType);
        font.setColor(fontColor);
        font.setBold(fontBold);
		
	}
	/***********************************************************************************************************************************/
	// call method: setWorkSheetFont(style, IndexedColors.TURQUOISE.getIndex(), FillPatternType.SOLID_FOREGROUND );
	public static void setWorkSheetFillColor(CellStyle style, short fillColor, FillPatternType fillPattern) {
		
		style.setFillForegroundColor(fillColor);  
        style.setFillPattern(fillPattern );  
		
		
	}
	/***********************************************************************************************************************************/
	public static void setWorkSheetTitleRow(XSSFSheet sheet, CellStyle style, String wbTitle) {
		//title row
	    Row titleRow = sheet.createRow(0);
	    titleRow.setHeightInPoints(45);
	    Cell titleCell = titleRow.createCell(0);
	    titleCell.setCellValue(wbTitle);
	    titleCell.setCellStyle(((Map<String, CellStyle>) style).get("title"));
	    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$L$1"));

		
	}
	/***********************************************************************************************************************************/
	public static void loadHeader(XSSFWorkbook workbook, XSSFSheet sheet, ArrayList<String> headerArr) {

		// System.out.println("************* strArr=" + headerArr.toString());
		Row row = sheet.createRow(0);
		int colNum = 0;
		for (Object field : headerArr) {
			Cell cell = row.createCell(colNum++);
			if (field instanceof String) {
				cell.setCellValue((String) field);
			}
		}

	}
	/*****************************************************************************************************************************************/
	public static void loadWorkSheet(XSSFWorkbook workbook, XSSFSheet sheet, ArrayList<String> strArr, int rowNum, String sep) {
		String[] strSplitArr = null;

		//System.out.println("************* strArr=" + strArr.toString());
		for (String str : strArr) { // iterating ArrayList
			Row row = sheet.createRow(rowNum++);
			strSplitArr = Olyutil.splitStr(str, sep);
			int colNum = 0;
			for (String token : strSplitArr) {
				Cell cell = row.createCell(colNum++);
				if (token instanceof String) {
					cell.setCellValue((String) token);
				}
			}
		}
	}
	/***********************************************************************************************************************************/

	/***********************************************************************************************************************************/

	/***********************************************************************************************************************************/

	/***********************************************************************************************************************************/

	
	 

}
