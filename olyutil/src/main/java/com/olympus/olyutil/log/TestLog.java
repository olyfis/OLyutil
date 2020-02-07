package com.olympus.olyutil.log;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import com.olympus.olyutil.Olyutil;

public class TestLog {
	 private static final Logger logger = Logger.getLogger(TestLog.class
	            .getClass().getName());
	 
	/**********************************************************************************************************************************/ 
	public static void main(String[] args) throws IOException {
		String directoryName = "C:/temp/jb1/jb2";
		String fileName = "jbLog.log";
		Date logDate = Olyutil.getCurrentDate();
		String dateFmt = Olyutil.formatDate("yyyy-MM-dd hh:mm:ss.SSS");
	
		
		Handler fileHandler =  OlyLog.setAppendLog(directoryName, fileName, logger );
	    logger.info(dateFmt + ": " +  "------------------START--------------------");
	    logger.info(dateFmt + ": " +  "Test Data");
	    fileHandler.flush();
	    fileHandler.close();
	    

	}
	/**********************************************************************************************************************************/ 

}
