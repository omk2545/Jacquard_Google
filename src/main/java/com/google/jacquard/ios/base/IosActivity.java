package com.google.jacquard.ios.base;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.ios.IOSDriver;

/**
 * Created by user on 6/28/17.
 */
public class IosActivity {

    public  IOSDriver driver;
    public ExtentTest test ;

    public IosActivity(IOSDriver driver, ExtentTest test) {

        if (driver != null) {
            this.driver = driver;
        }
        if (test != null) {
            this.test = test;
        }

        System.out.print("Creating the new driver");





    }
}
