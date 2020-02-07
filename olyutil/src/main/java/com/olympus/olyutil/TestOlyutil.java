package com.olympus.olyutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class TestOlyutil {
	
	static Statement stmt = null;
	static Connection con = null;
	static ResultSet res  = null;
	//static NodeList  node  = null;
	static String s = null;
	static private PreparedStatement statement;
	static String propFile = "C:\\Java_Dev\\props\\unidata.prop";
	static String sqlFile = "C:\\Java_Dev\\props\\sql\\onelink.sql"; 
	/****************************************************************************************************************************************************/
	public static ArrayList<String> getDbData() throws IOException {
		FileInputStream fis = null;
		FileReader fr = null;
		String s = new String();
        StringBuffer sb = new StringBuffer();
        ArrayList<String> strArr = new ArrayList<String>();
		try {
			fis = new FileInputStream(propFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties connectionProps = new Properties();
		connectionProps.load(fis);
		 
		fr = new FileReader(new File(sqlFile));
		
		// be sure to not have line starting with "--" or "/*" or any other non alphabetical character
		BufferedReader br = new BufferedReader(fr);
		while((s = br.readLine()) != null){
		      sb.append(s);
		       
		}
		br.close();
		//displayProps(connectionProps);
		String query = new String();
		query = sb.toString();	
		//System.out.println("Query=" + query);	 
		try {
			con = Olyutil.getConnection(connectionProps);
			if (con != null) {
				//System.out.println("Connected to the database");
				statement = con.prepareStatement(query);
				res = Olyutil.getResultSetPS(statement);		 	 
				strArr = Olyutil.resultSetArray(res, ":");			
			}		
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return strArr;
	}
	/****************************************************************************************************************************************************/
	/****************************************************************************************************************************************************/
	public static void main(String[] args) throws IOException { 
		/*  // Test DB connection 
		ArrayList<String> strArr = new ArrayList<String>();
		strArr = getDbData();
		Olyutil.printStrArray(strArr);
		
		String startDate = "2018-04-11";
		String endDate = "2019-04-11";
		String fmt = "yyyy-MM-dd";
		int res;
		res = Olyutil.dateCompare(startDate, endDate, fmt);
		System.out.println("startDate:" + startDate + "        endDate:" + endDate );
		//System.out.println("endDate:" + endDate );	
		System.out.println("dateCompare=" + res + "--");	
		*/
		
		String n = "1234..56";
		System.out.println("isNumeric returned:" + Olyutil.isNumeric(n) + "--");	
		
		String dateFmt = Olyutil.formatDate("yyyy-MM-dd hh:mm:ss.SSS");
		System.out.println("Date: " + dateFmt);
		
		
	}
}
