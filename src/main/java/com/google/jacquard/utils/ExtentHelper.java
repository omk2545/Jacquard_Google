package com.google.jacquard.utils;

import java.io.File;
import java.util.Date;

import org.joda.time.DateTime;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentHelper {
	
	private static ExtentReports extent;
	
	public ExtentHelper() {
	
	}
	
	
	public static ExtentReports getInstance(){
		
		if (extent == null) {
			
			
			
			DateTime dateTime = new DateTime();
			String todaysDate = String.valueOf(dateTime.getDayOfMonth());
			System.out.println("Day:" +todaysDate);
			

			//String reportPath = System.getProperty("user.home")+"/reports/"+todaysDate;
			String reportPath = System.getProperty("user.dir")+"/reports/"+todaysDate;


		System.out.println(reportPath);

			//String value = System.getProperty("user.home");
			String value = System.getProperty("user.home");

			System.out.println(value);
			File file = new File(reportPath);
			
			if (file.exists()) {
				
				System.out.println("File exists");
				
			}else{
				
				file.mkdirs();
				System.out.println("Folder created successfully");

			}

			
			
			Date date = new Date();
			date.toString();
			System.out.println(date.toString().replaceAll(" ", "_").replaceAll(":", "_"));
            String reportName = date.toString().replaceAll(" ", "_").replaceAll(":", "_");
			System.out.println(reportName);
			//reportName = "omkar";



			//ExtentHtmlReporter reporter = new ExtentHtmlReporter(System.getProperty("user.home")+"/reports/"+todaysDate+"/"+reportName+".html");
			ExtentHtmlReporter reporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/"+todaysDate+"/"+reportName+".html");
			reporter.config().setChartVisibilityOnOpen(true);
			reporter.config().setDocumentTitle("Jacquard-Performance-Iphone");
			reporter.config().setReportName("Iphone Battery Consumption report");
			reporter.config().setTheme(Theme.DARK);
//			reporter.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss a");
			
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			

		
			
		}
		
		
		return extent;
		
	
	
	}
	
public static void main(String[] args) {
	

	String reportPath = System.getProperty("user.home")+"/reports/"+"30";
//	System.out.println(reportPath);

	String value = System.getProperty("user.home");

	System.out.println(value);
	File file = new File(reportPath);

	if (file.exists()) {

		System.out.println("File exists");

	}else{

		file.mkdirs();
	}


	ExtentHtmlReporter reporter = new ExtentHtmlReporter(System.getProperty("user.home")+"/reports/"+"30/"+"Extent.html");
	reporter.config().setChartVisibilityOnOpen(true);
	reporter.config().setDocumentTitle("Jacquard-Performance-Iphone");
	reporter.config().setReportName("Iphone Battery Consumption report");
	reporter.config().setTheme(Theme.STANDARD);
//	reporter.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss a");

	extent = new ExtentReports();
	extent.attachReporter(reporter);


	ExtentTest test = extent.createTest("Omkar");

	test.log(Status.DEBUG,"Omkar");



	extent.flush();
	
	
//Date date = new Date();
//date.toString();
//System.out.println(date.toString().replaceAll(" ", "_").replaceAll(":", "_"));
//date.toString().replaceAll(" ", "_").replaceAll(":", "_");


	
}



}


