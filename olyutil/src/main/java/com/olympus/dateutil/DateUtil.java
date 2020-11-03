package com.olympus.dateutil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.olympus.olyutil.Olyutil;
// USAGE: String newDate = DateUtil.fmtDate("2019-09-22", "yyyy-MM-dd", "MM-dd-yyyy");
public class DateUtil {
	
	
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

		return (rtnDate);
	}
	
	
/*********************************************************************************************************************************************/
	
	// Exec: diffDays = diff2Dates(  baseCommDate, date30 );
	public static long diff2Dates(String today, String newEffDate )  {
	
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		long diff = 0;
		long diffDate = 0;
		
		//today = "2020-10-12";

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
	
	/****************************************************************************************************************************************************/
	public static void displayData(ArrayList<String> strArr, ArrayList<String> hdrArr, String sep) {
		 
		
		int k = 0;
		for (String str : strArr) { // iterating ArrayList
	 			
				//System.out.println("**** Str=" + str);
				String[] items = str.split(sep);
				int sz = items.length;
			
				for (int i = 0; i < sz; i++) {
					
					System.out.println("**** SZ=" +  items.length + "-- Row="  + k + "--  i=" + i + "-- " + hdrArr.get(i)  + "=" + items[i] );
			
					//System.out.println(k + ";" + i + ";" + hdrArr.get(i)  + ";" + items[i]);
				}
				k++;
		}
		
	}
	/****************************************************************************************************************************************************/

	
	// String dateFmt = formatDate("yyyy-MM-dd");
	// String dateFmt = formatDate("yyyy-MM-dd hh:mm:ss.SSS");
	
	// String dateFmt = formatDate("MM/dd/yyyy");
	/********************************************************************************************************************************************************/

	public static String formatDates(String format) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return(dateFormat.format(date));
	}
	
	/********************************************************************************************************************************************************/
	// invoke: checkBetween("2020-10-04", "2020-10-01" , "2020-10-09")
		public static boolean isDateBetween(String dateToCheck, String startDate, String endDate) {
			boolean res = false;
			SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd"); // 2020-10-09
			SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd"); // 2020-10-09
			try {
				Date requestDate = fmt2.parse(dateToCheck);
				Date fromDate = fmt1.parse(startDate);
				Date toDate = fmt1.parse(endDate);
				res = requestDate.compareTo(fromDate) >= 0 && requestDate.compareTo(toDate) <= 0;
			} catch (ParseException pex) {
				pex.printStackTrace();
			}
			return res;
		}
		/********************************************************************************************************************************************************/

	// Call: 	effectiveDate = DateUtil.calculateMonthsFromDate(9);
    //			System.out.println("Effective Date=" +  effectiveDate);
	
	public static String calculateMonthsFromDate(int range){
		LocalDate specialOfferExpiryDate = LocalDate.now();
         
        if(range > 0 && range <=12) {
        	LocalDate today = LocalDate.now(); 
            specialOfferExpiryDate = today.plusMonths(range) ; 
            //System.out.println("Month=" + specialOfferExpiryDate.toString() );
         }
        return specialOfferExpiryDate.toString();

   }
	/********************************************************************************************************************************************************/
	/***********************************************************************************************************************************/
	public static void writeToFile(ArrayList<String> strArr, String fileName, String sep) throws IOException {
	    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	    String[] strSplitArr = null;
	    //System.out.println("** Write files:" + fileName );
	    for (String str : strArr) { // iterating ArrayList
	    	strSplitArr = Olyutil.splitStr(str, sep);
	    	int i = 0;
	    	int sz = strSplitArr.length;
	    	for (String token : strSplitArr) {
	    		//writer.write(token.replaceAll("null", ""));
	    		if (i < sz -1) {
	    			writer.write(token + sep);
	    		} else {
	    			writer.write(token);
	    		}
	    		
	    		i++;
	    	}
	    	writer.newLine();
	    }
	    writer.close(); 
	   
	}
	
	/********************************************************************************************************************************************************/
	// String from = "yyyy-MM-dd HH:mm:ss.SSS";
		// String to = "yyyy-MM-dd";
		// usage:  fmtDate("2019-09-22 15:11:22.123", "yyyy-MM-dd hh:mm:ss.SSS", "yyyy-MM-dd")
	// usage:  fmtDate("2019-09-22", "yyyy-MM-dd", "MM-dd-yyyy");
		public static String fmtDate(String dateVal, String from, String to  ) throws IOException {
				 
			String dateMyFormat = "";
			SimpleDateFormat fromUser = new SimpleDateFormat(from); 
	        SimpleDateFormat myFormat = new SimpleDateFormat(to);

	        try {
	            Date dateFromUser = fromUser.parse(dateVal); // Parse it to the exisitng date pattern and return Date type
	            dateMyFormat = myFormat.format(dateFromUser); // format it to the date pattern you prefer
	            //System.out.println("DF=" + dateMyFormat); // outputs : 2009-05-19
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return(dateMyFormat); 
		}
		/********************************************************************************************************************************************************/

	// Re-format long date format
	public static String formatDate(String dateVal ) throws IOException {
		
	 
			String dateMyFormat = "";
	 
			SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
	        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

	        try {
	            Date dateFromUser = fromUser.parse(dateVal); // Parse it to the exisitng date pattern and return Date type
	            dateMyFormat = myFormat.format(dateFromUser); // format it to the date pattern you prefer
	            //System.out.println("DF=" + dateMyFormat); // outputs : 2009-05-19

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			
			
			
			return(dateMyFormat);
	 
		 
		}
	/********************************************************************************************************************************************************/
	/********************************************************************************************************************************************************/
	public static String addMonthsToDate(String origDate, int mths) {
		String newDate = "";
		LocalDate date   = LocalDate.parse(origDate); 
		LocalDate returnvalue  = date.plusMonths(mths); 
		
		System.out.println("^^^!!!*** OrigDate=" +  origDate + " -- LocalDate after " + " adding months:" + mths   +  " ReturnedDate="  + returnvalue); 
		 newDate = returnvalue.toString();
		 //System.out.println("***** LocalDate after " + " adding months: " + newDate); 
		return(newDate);
	}
	
	
	/********************************************************************************************************************************************************/
	// Returns years, months and days

	public static int differenceInMonths(String effDate, String termDate) {
		int diff = 0;
		Period period = Period.between(
            LocalDate.parse(effDate).withDayOfMonth(1),
            LocalDate.parse(termDate).withDayOfMonth(1));
		//System.out.println(period); //P3M
		int mths = period.getYears() * 12;
		diff  = mths + period.getMonths();
		
		return diff;
}
	/********************************************************************************************************************************************************/
	// Returns months only
	
	
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// Date d1 = sdf.parse("2014-03-22");
		//Date d1 = sdf.parse(effDate);
			//Date d2 = sdf.parse(termDate);
		
		
		// d1 = effDate -- d2 = termDate
		public static int differenceInMonths(Date d1, Date d2) {
		    Calendar c1 = Calendar.getInstance();
		    c1.setTime(d1);
		    Calendar c2 = Calendar.getInstance();
		    c2.setTime(d2);
		    int diff = 0;
		    if (c2.after(c1)) {
		        while (c2.after(c1)) {
		            c1.add(Calendar.MONTH, 1);
		            if (c2.after(c1)) {
		                diff++;
		            }
		        }
		    } else if (c2.before(c1)) {
		        while (c2.before(c1)) {
		            c1.add(Calendar.MONTH, -1);
		            if (c1.before(c2)) {
		                diff--;
		            }
		        }
		    }
		    return diff;
		}
		/********************************************************************************************************************************************************/

		/*********************************************************************************************************************************************************/
		// int rtn = DateUtil.compareDates("2020-01-01", "2020-02-01");
		// rtn = -1 -- Effective date occurs before Commencement date
		public static int compareDates(String effDate, String commDate) {
			int rtnVal = 0;
			Date edate = null;
			
			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
			Date cdate = null;
			try {
				cdate = sdformat.parse(commDate);
				edate = sdformat.parse(effDate); 
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println("The Effective date is: " + sdformat.format(edate)  + "-- Param=" + effDate );
			//System.out.println("The Commencement date is: " + sdformat.format(cdate)  + "-- Param=" + commDate);
			
			if (cdate.compareTo(edate) > 0) {
				rtnVal = -1;
				 //System.out.println("Effective date occurs before Commencement date -- RTN=" + rtnVal);
				
			} else if (cdate.compareTo(edate) < 0) {
				rtnVal = 1;
				  //System.out.println("Effective date occurs after Commencement date -- RTN=" + rtnVal);
			} else if (cdate.compareTo(edate) == 0) {
				rtnVal = 0;
				//System.out.println("Both dates are equal");
			}	
			return(rtnVal);
		}
		/********************************************************************************************************************************************************/
		//Default pattern is yyyy-MM-dd
		public static int calculateMonthsBetweenDates(String termDate, String effDate, int monthsToAdd) {
		
			int months = 0;
			LocalDate tDate = LocalDate.parse(termDate);
			LocalDate eDate = LocalDate.parse(effDate);
			LocalDate futureTermDate = tDate.plusMonths(monthsToAdd);

			//System.out.println("T=" + tDate.toString());
			//System.out.println("FT=" + futureTermDate.toString());
			//System.out.println("E=" + eDate.toString());

			Period diff = Period.between(LocalDate.parse("2016-08-22").withDayOfMonth(1),
					LocalDate.parse("2016-09-10").withDayOfMonth(1));
			//System.out.println("Diff=" + diff); // P3M

			return months;
		}
		/*********************************************************************************************************************************************************/
		public static int compareDateDays(String effDate, String commDate) throws ParseException {
			int rtnVal = 0;
			String cDay = "";
			String eDay = "";

			String[] cdateArr = commDate.split("-");
			cDay = cdateArr[2];
			String[] edateArr = effDate.split("-");
			;
			eDay = edateArr[2];
			if (!eDay.equals(cDay)) {
				//System.out.println("E=" + eDay + "--    C=" + cDay);
				rtnVal = -2;
			}
			return (rtnVal);
		}
	
}
