package com.olympus.test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.olympus.dateutil.DateUtil;

public class TestDate {

	public static void main(String[] args) throws ParseException {
		
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		
		String commDate = "2020-03-01";
		//System.out.println("LocalDate : " + today); 
		//String date30 = dateShift(today.toString(), "yyyy-MM-dd","yyyy-MM-dd", +30);
		//System.out.println("30 days after today will be " + date30);
		
		String newDate = DateUtil.getNewEffectiveDate(commDate);
		
		System.out.println("*** NewEffectiveDate=" + newDate );
		//System.out.println("*** Added 2 months:" + addMonths( commDate, 2));
 
		
		/*
		String fromDate = "2020-10-01";
		String toDate = "2020-10-08";

		// String requestDate = "17/02/2018";
		String requestDate = "2020-10-05";

		System.out.println(com.olympus.dateutil.DateUtil.isDateBetween(requestDate, fromDate, toDate));


	*/
	}

}
