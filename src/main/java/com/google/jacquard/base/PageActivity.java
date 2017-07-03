package com.google.jacquard.base;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class PageActivity {


	public AndroidDriver driver;
	public 	ExtentTest test;
	
	public PageActivity(AppiumDriver driver, ExtentTest test) {
		if (driver != null && test != null) {
			this.driver = (AndroidDriver) driver;
			this.test = test;
			System.out.println("Inside the constructor");
		}

	}



}
