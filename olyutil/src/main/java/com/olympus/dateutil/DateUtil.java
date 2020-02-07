package com.olympus.dateutil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
// USAGE: String newDate = DateUtil.fmtDate("2019-09-22", "yyyy-MM-dd", "MM-dd-yyyy");
public class DateUtil {
	
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
	//Default pattern is yyyy-MM-dd
	public static int calculateMonthsBetweenDates(String termDate, String effDate, int monthsToAdd) {
		int months = 0;
		LocalDate tDate = LocalDate.parse(termDate);
		LocalDate eDate = LocalDate.parse(effDate);
		LocalDate futureTermDate = tDate.plusMonths(monthsToAdd);

		System.out.println("T=" + tDate.toString());
		System.out.println("FT=" + futureTermDate.toString());
		System.out.println("E=" + eDate.toString());

		Period diff = Period.between(LocalDate.parse("2016-08-22").withDayOfMonth(1),
				LocalDate.parse("2016-09-10").withDayOfMonth(1));
		System.out.println("Diff=" + diff); // P3M

		return months;
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

}
