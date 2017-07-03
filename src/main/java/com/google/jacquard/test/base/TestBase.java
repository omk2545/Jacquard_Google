package com.google.jacquard.test.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import com.google.jacquard.utils.CommandExec;
import com.google.jacquard.utils.Constants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.jacquard.utils.ExtentHelper;

import io.appium.java_client.ios.IOSDriver;

public class TestBase {

	public ExtentReports extentReports;
	public ExtentTest test;
    public AppiumDriver driver;
    public ExtentTest createTest(String testName) {
		extentReports = ExtentHelper.getInstance();
		test = extentReports.createTest(testName);
		return test;
	}

    public void exitExtent(){

        extentReports.flush();

    }

	public AndroidDriver createAndroidInstance() throws MalformedURLException {

		System.out.println("--Starting  android Application--");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", Constants.deviceName);
		capabilities.setCapability("platformName",Constants.platformName);
		capabilities.setCapability("platformVersion",Constants.platformVersion);
		//capabilities.setCapability("appPackage",Constants.appPackage);
		//capabilities.setCapability("appActivity",Constants.appActivity);
	capabilities.setCapability("newCommandTimeout",Constants.newCommandTimeout);
	capabilities.setCapability("unicodeKeyboard",false);

	capabilities.setCapability("resetKeyboard",false);



//		capabilities.setCapability("appPackage","com.google.atap.jacquard");
capabilities.setCapability("appPackage",Constants.appPackage);
//		capabilities.setCapability("appActivity","com.google.atap.jacquard.application.activities.EntrypointActivity");
		capabilities.setCapability("appActivity",Constants.appActivity);

		driver = new AndroidDriver(new URL("http://127.0.0.1:4678/wd/hub"),capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

return (AndroidDriver) driver;



	}


    
	public IOSDriver createDeviceInstance() throws MalformedURLException {



        System.out.println(System.getProperty("user.dir"));
            String path  = System.getProperty("user.dir" ) +"/application/ios_simulator/Payload/Jacquard QA.app";
        //System.out.println("/Users/user/OmkarAutomation/application/ios_simulator/Payload/JacquardSimulator.app");

        System.out.println("Path: " +path);





        System.out.println("started");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "User’s iPhone");
	//working	//capabilities.setCapability("udid", "4b6bf00abd0a863ce873675dda8612fe0f8874c0");
		//capabilities.setCapability("udid", "91e586f3b0297c51ad326d4313200fb4b86354e7");
		capabilities.setCapability("udid", Constants.iphone_udid);
		// capabilities.setCapability("udid",
		 //"b9bad8c8321ce8e1e28ea5b99d14ca167b5672d4"); //b9bad8c8321ce8e1e28ea5b99d14ca167b5672d4
		capabilities.setCapability("platformVersion", "10.2.1");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("automationName", "XCUITEST");
		capabilities.setCapability("xcodeOrgID", "EQHXZ8M8AV");
		capabilities.setCapability("useNewWDA", true);
		capabilities.setCapability("wdaLocalPort", "8102");
		capabilities.setCapability("autoAcceptAlerts ",true);
		// capabilities.setCapability("xcodeOrgID", "EQHXZ8M8AV");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("xcodeSigninID", "iPhone Developer");
		// capabilities.setCapability("app","/Users/user/Documents/Jacquard
		// 2017-05-04 13-26-44/Payload/JacquardSimulator.app");

		//capabilities.setCapability("app", "settings");

        capabilities.setCapability("newCommandTimeout",Constants.newCommandTimeout);
		 capabilities.setCapability("app","/Users/user/iphoneApps/Payload_2/Jacquard QA.app");
		// capabilities.setCapability("app","/Users/user/Library/Developer/Xcode/DerivedData/Jacquard-grdyftppfxmznqaxmeeeeguahavj/Build/Products/Debug-iphoneos/Jacquard Dev.app");
		//capabilities.setCapability("app","/Users/user/Library/Developer/Xcode/DerivedData/Jacquard-grdyftppfxmznqaxmeeeeguahavj/Build/Products/Debug-iphoneos/Jacquard Dev.app");
		capabilities.setCapability("showIOSLog",Constants.showIOSLog_ipad);
		//  capabilities.setCapability("bundleid", "com.mobiliya.Jacquard.alpha");
		//capabilities.setCapability("showIOSLog", true);
		// capabilities.setCapability("xcodeConfigfile","/Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/Config.xcconfig");
		// capabilities.setCapability("xcodeConfigfile","/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/Config.xcconfig");

		driver = new IOSDriver(new URL("http://0.0.0.0:4727/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("---Launching app ---");

        return (IOSDriver) driver;

	}

	
	
	
	
	public IOSDriver createSimulatorInstance() throws MalformedURLException{

       // System.out.println(Constants.app_Simulator);
        System.out.println(System.getProperty("user.dir"));
        String path  = System.getProperty("user.dir" ) +"/application/ios_simulator/Payload/JacquardSimulator.app";
        //System.out.println("/Users/user/OmkarAutomation/application/ios_simulator/Payload/JacquardSimulator.app");

        System.out.println("Path: " +path);


        System.out.println("Starting iphone simulator application");
		  DesiredCapabilities capabilities = new DesiredCapabilities();
		    capabilities.setCapability("deviceName",Constants.deviceName_ipad);
	      // capabilities.setCapability("udid","4b6bf00abd0a863ce873675dda8612fe0f8874c0");
	       capabilities.setCapability("udid", Constants.udid_ipad);
	        capabilities.setCapability("platformVersion",Constants.platformVersion_ipad);
	        capabilities.setCapability("platformName", Constants.platformName_ipad);
	        capabilities.setCapability("automationName", Constants.automationName_ipad);
	        capabilities.setCapability("xcodeOrgID",Constants.xcodeOrgID_ipad);
	        capabilities.setCapability("useNewWDA",Constants.useNewWDA_ipad);
	        capabilities.setCapability("wdaLocalPort",Constants.wdaLocalPort_ipad);
	        capabilities.setCapability("newCommandTimeout",Constants.newCommandTimeout);
	        capabilities.setCapability("noReset", Constants.noReset_ipad);
	        capabilities.setCapability("xcodeSigninID",Constants.xcodeSigninID_ipad);
	  	  //  capabilities.setCapability("app",Constants.app_Simulator);
	  	  capabilities.setCapability("app",path);


	        capabilities.setCapability("showIOSLog",Constants.showIOSLog_ipad);
		    //capabilities.setCapability("xcodeConfigfile","/Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/Config.xcconfig");
		   // capabilities.setCapability("xcodeConfigfile","/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/Config.xcconfig");


		  driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);    
		  System.out.println("---App Simulotor luanch ---");
		 

		return (IOSDriver) driver;
		
		
	}

public void killAllAppiumInstances(){


    List<String> values = new ArrayList<String>();

    Runtime runtime = Runtime.getRuntime();
    try {

        //String[] cm = { "/bin/bash", "-c", "echo omkar" };

//            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c",
//                    "/usr/local/Cellar/libimobiledevice/HEAD-53fede0/bin/ideviceinfo -u "+Udid+" -q com.apple.mobile.battery -k BatteryCurrentCapacity");
// ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c",  "/usr/local/bin/appium -a 0.0.0.0 -p 4723 -U b9bad8c8321ce8e1e28ea5b99d14ca167b5672d4 ");
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c",  "killall node");
//appium -a 0.0.0.0 -p 4723 -U b9bad8c8321ce8e1e28ea5b99d14ca167b5672d4
        Process process = pb.start();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);

            if (line != null) {
                values.add(line);
            }

        }

        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        String line1;
        while ((line1 = bufferedReader2.readLine()) != null) {

            System.out.println(line1);

        }

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }






}


public  void startAppiumProcess(String port, String id){


    List<String> values = new ArrayList<String>();
    try {


        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c",  "/usr/local/bin/appium -a 0.0.0.0 -p "+port );
        Process process = pb.start();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);

            if (line != null) {
                values.add(line);
            }

        }

        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        String line1;
        while ((line1 = bufferedReader2.readLine()) != null) {

            System.out.println(line1);

        }

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }








}




	public  void startAppiumServers(){



        new Thread(new Runnable() {
            public void run() {
                System.out.println("Starting Iphone server");
                startAppiumProcess("4727",Constants.iphone_udid );
            }
        }).start();


        new Thread(new Runnable() {
            public void run() {
                System.out.println("Starting simulator server");
                startAppiumProcess("4723",Constants.udid_ipad);
            }
        }).start();


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //startAppiumProcess("4727",Constants.iphone_udid );

       // startAppiumProcess("4723",Constants.udid_ipad);
    }

}










	

