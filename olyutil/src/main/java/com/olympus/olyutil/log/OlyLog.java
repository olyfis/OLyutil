package com.olympus.olyutil.log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.olympus.olyutil.Olyutil;

public class OlyLog {
	/**********************************************************************************************************************************/ 
	// Invoke: OlyLog.logSetup(logger, fileHandler);
	public static void logSetup(Logger logger, Handler fileHandler) throws IOException {

        SimpleFormatter simple = new SimpleFormatter();
		fileHandler.setFormatter(simple);
		logger.addHandler(fileHandler);//adding Handler for file

    } // End method
	/**********************************************************************************************************************************/ 
	public static Handler setAppendLog(Logger logger, String fileName  ) throws IOException {
		Handler fileHandler = new FileHandler(fileName, true);
		logSetup(logger, fileHandler);	
		return(fileHandler);
	}
	
	/**********************************************************************************************************************************/ 
	
	public static Handler setAppendLog(Logger logger, String fileName, String logDate  ) throws IOException {
		 
		Handler fileHandler = new FileHandler(fileName + logDate + ".log", true);
		logSetup(logger, fileHandler);	
		return(fileHandler);
	}
	
	/**********************************************************************************************************************************/ 
	// Invoke: Handler fileHandler =  OlyLog.setAppendLog(directoryName, fileName, logger );
		public static Handler setAppendLog(String directoryName, String fileName, Logger logger ) throws IOException {
			
			File directory = new File(directoryName);
		    if (! directory.exists()){
		        directory.mkdirs(); // If you require it to make the entire directory path including parents,  use directory.mkdirs(); 
		    }   
		    String filePath = directoryName + "/" + fileName;
			Handler fileHandler = new FileHandler(filePath, true);
			logSetup(logger, fileHandler);	
			return(fileHandler);
		}
		/**********************************************************************************************************************************/ 

	
} // End  class
