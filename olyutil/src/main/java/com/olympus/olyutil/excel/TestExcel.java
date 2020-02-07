package com.olympus.olyutil.excel;

import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.xssf.usermodel.*;

import com.olympus.olyutil.Olyutil;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Map;
import java.util.HashMap;

/***********************************************************************************************************************************/
/***********************************************************************************************************************************/

public class TestExcel {

	public static void main(String[] args) {
		String headerFile = "C:\\Java_Dev\\props\\headers\\NBVA_AssetHrd.txt";
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		Date date = Olyutil.getCurrentDate();
		String dateStamp = date.toString();
		ArrayList<String> assetHeaderArr = new ArrayList<String>();
		assetHeaderArr = Olyutil.readInputFile(headerFile);
		String FILE_NAME = "NBVA_Asset_List_Report_" + dateStamp + ".xlsx";

		workbook = OlyExcel.newWorkbook();

		sheet = OlyExcel.newWorkSheet(workbook, "Asset List Report");
		Map<String, CellStyle> styles = OlyExcel.createStyles(workbook);

	}
	/***********************************************************************************************************************************/

}
