package com.olympus.olyutil;

import java.io.IOException;
import java.util.Date;

import com.olympus.dateutil.DateUtil;

public class TestDateUtil {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String newDate = DateUtil.fmtDate("2019-09-22", "yyyy-MM-dd", "MM-dd-yyyy");
		System.out.println("New Date:" + newDate + "--");
		
		Date date1 = new java.util.Date();
		System.out.println("***^^^*** Date=" + date1);
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
		System.out.println("***^^^*** TS=" + timestamp);
	}

}
