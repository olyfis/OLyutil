package com.olympus.test;

 

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.olympus.olyutil.Olyutil;

public class DateDiff {
	
	/*******************************************************************************************************************************************/

	public static int compareDateDays(String effDate, String commDate) throws ParseException {
		int rtnVal = 0;
		String cDay = "";
		String eDay = "";
		String cMth = "";
		String eMth = "";

		String[] cdateArr = commDate.split("-");
		cMth = cdateArr[1];
		cDay = cdateArr[2];
		String[] edateArr = effDate.split("-");
		eMth = edateArr[1];
		eDay = edateArr[2];
		if (!eDay.equals(cDay)) {
			//System.out.println("E=" + eDay + "--    C=" + cDay);
			rtnVal = -2;
		}
		return (rtnVal);
	}
	/*******************************************************************************************************************************************/

	
	
	
	
/*******************************************************************************************************************************************/
	// fmt "yyyy-MM-dd"
	// Invoke: String date2 = dateShift("2020-10-20", "yyyy-MM-dd","MM-dd-yyyy", -10);
	public static String dateShift(String origDate, String fmtIn, String fmtOut, int offset) {
 
		DateTimeFormatter formatterIn = DateTimeFormatter.ofPattern(fmtIn);
		DateTimeFormatter formatterOut = DateTimeFormatter.ofPattern(fmtOut);
		LocalDate modDate = LocalDate.parse(origDate, formatterIn);
		LocalDate newDate = modDate.plusDays(offset);
		String rtnStr = formatterOut.format(newDate);
		
		return (rtnStr);
	}
	
/********************************************************************************************************************************************/

	// Exec; rtnDate = addMonths( baseCommDate, 1);
	public static String addMonths(String dateAsString, int nbMonths) throws ParseException {
        String format = "yyyy-MM-dd" ;
        SimpleDateFormat sdf = new SimpleDateFormat(format) ;
        Date dateAsObj = sdf.parse(dateAsString) ;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateAsObj);
        cal.add(Calendar.MONTH, nbMonths);
        Date dateAsObjAfterAMonth = cal.getTime() ;
        //System.out.println(sdf.format(dateAsObjAfterAMonth));
    
        String dateRtn = sdf.format(dateAsObjAfterAMonth);
    
    //return dateAsObjAfterAMonth ;
    return(dateRtn);
}
/***********************************************************************************************************************************************/
	public static String getNewEffectiveDate(String effDate) throws ParseException  {
		String rtnDate = "";

		LocalDate today = LocalDate.now();
		long diffDays = 0;
		long diffDays2 = 0;

		String todayDate = today.toString();
		// System.out.println("* Today=" + todayDate);

		String[] edateArr = effDate.split("-");

		String eYr = edateArr[0];
		String eMth = edateArr[1];
		String eDay = edateArr[2]; // commencement day to match

		//String todayTest = "2020-11-02";
		String date30 = dateShift(today.toString(), "yyyy-MM-dd", "yyyy-MM-dd", +30);
		// String date30 = dateShift(todayTest, "yyyy-MM-dd","yyyy-MM-dd", +30);
		String[] date30Arr = date30.split("-");

		String nYr = date30Arr[0];
		String nMth = date30Arr[1];
		String nDay = date30Arr[2];

		String[] todayDateArr = todayDate.split("-");
		String todayYr = todayDateArr[0];
		String todayMth = todayDateArr[1];
		

		String baseCommDate = todayYr + "-" + todayMth + "-" + eDay;
		// System.out.println("*** baseCommDate:" + baseCommDate + "-- Today+30:" + date30);
		String[] baseCommArr = baseCommDate.split("-");
		
		
		String baseCommDay = baseCommArr[2];
		String todayDay = todayDateArr[2];

		//String[] todayDateArr = day.split("-");
		int todayDayValue = Olyutil.strToInt(todayDateArr[2]);
		int baseCommDayValue = Olyutil.strToInt(baseCommDay);
		int addMth = 0;
		
		if (todayDayValue <= baseCommDayValue) {
			addMth = 1;
		} else {
			addMth = 2;
		}
		
		rtnDate = addMonths( baseCommDate, addMth);
		
		/*************************************************************************************************************************/
	
		
/*  TESTING Code		
		
		String[] dateArr2 = date30.split("-");

		String day = "";
		for (int i = 1; i < 31; i++) {
			
			
			
			if (i < 10) {
				day = "2020-11-0" + String.valueOf(i);
			} else {
				day = "2020-11-" + String.valueOf(i);
			}
			String[] todayDateArr2 = day.split("-");
			int todayDayValue2 = Olyutil.strToInt(todayDateArr2[2]);
			int baseCommDayValue2 = Olyutil.strToInt(baseCommDay);
			int addMth2 = 0;
			
			if (todayDayValue2 <= baseCommDayValue2) {
				addMth2 = 1;
			} else {
				addMth2 = 2;
			}
			
			rtnDate = addMonths( baseCommDate, addMth2);
			// System.out.println(day + "--");
			String d30 = dateShift(day, "yyyy-MM-dd", "yyyy-MM-dd", +30);
			System.out.println(day + " -- " + baseCommDate   + " -- " + d30 + "-- nMth:" + dateArr2[1]   + 
					"-- addMth:" + addMth2 + "-- TDV:" + todayDayValue2  + "-- BCV:" + baseCommDayValue2   + "-- NewDate:" + rtnDate);
		}
		
		
		*/
		/*************************************************************************************************************************/


		return (rtnDate);
	}
	
	/*********************************************************************************************************************************************/
	
	// Exec: diffDays = diff2Dates(  baseCommDate, date30 );
	public static long diff2Dates(String today, String newEffDate )  {
	
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		long diff = 0;
		long diffDate = 0;
		
		

		try {
			//Date date1 = myFormat.parse(today);
			Date date1 = myFormat.parse(today);
			Date date2 = myFormat.parse(newEffDate);
			diff = date2.getTime() - date1.getTime();
			
			diffDate = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			//System.out.println("***---*** Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
	return(diffDate);
	}
/*********************************************************************************************************************************************/
	// Exec: 
	public static String[] getDateParts(String date)  {
		String[] dateArr = date.split("-");
	 
		return(dateArr);
	}
/*********************************************************************************************************************************************/
	
	
	public static void main(String[] args) throws ParseException {
		
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		
		String commDate = "2020-03-01";
		//System.out.println("LocalDate : " + today); 
		//String date30 = dateShift(today.toString(), "yyyy-MM-dd","yyyy-MM-dd", +30);
		//System.out.println("30 days after today will be " + date30);
		
		String newDate = getNewEffectiveDate(commDate);
		
		System.out.println("*** NewEffectiveDate=" + newDate );
		//System.out.println("*** Added 2 months:" + addMonths( commDate, 2));
		
		
		
		
		
/*
		
		String date2 = dateShift("2020-10-20", "yyyy-MM-dd","MM-dd-yyyy", -2);
		System.out.println("\n *****Date2=" + date2);
		// convert String to LocalDate
		LocalDate effDate = LocalDate.parse(date, formatter);

		System.out.println("\nCurrent Date: " + today);
		System.out.println("10 days before today will be " + today.plusDays(-10));
		System.out.println("10 days after today will be " + today.plusDays(10) + "\n");

		System.out.println("\nEffective Date: " + effDate);
		System.out.println("10 days before Effective Date will be " + effDate.plusDays(-10));

		System.out.println("1 days before Effective Date will be " + effDate.plusDays(-1));
		System.out.println("15 days before Effective Date will be " + effDate.plusDays(-15));

		LocalDate effDate15 = effDate.plusDays(-15);

		System.out.println("\n**LD=" + formatter2.format(effDate15));
		String d1 = formatter2.format(effDate15);

		System.out.println("\n**LD=" + d1);
*/
		
	}
}