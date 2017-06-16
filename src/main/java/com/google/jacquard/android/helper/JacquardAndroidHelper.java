package com.google.jacquard.android.helper;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.google.jacquard.utils.CommandExec;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.bcel.generic.SWITCH;
import org.apache.poi.util.SystemOutLogger;
import org.joda.time.DateTime;
import org.omg.CORBA.portable.ValueInputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by user on 6/1/17.
 */
public class JacquardAndroidHelper {

    AndroidDriver driver;
    ExtentTest test;

    public JacquardAndroidHelper(AppiumDriver driver, ExtentTest test) {
        this.driver = (AndroidDriver) driver;
        this.test = test;
    }

    public  enum GESTURES{

        DOUBLETAP,BRUSHIN,BRUSHOUT

    }
// public AndroidDriver driver;
    // public ExtentTest test;

    // public JaquardHelper(AndroidDriver driver, ExtentTest test) {
    //
    // this.driver = driver;
    // this.test = test;
    //
    // }
    public int gettotalJestureCount() throws InterruptedException {

//    AndroidElement interact = (AndroidElement) driver.findElementById("com.google.atap.jacquard:id/text_view_label");
//    AndroidElement reflect = (AndroidElement) driver.findElementById("com.google.atap.jacquard:id/text_view_label");


        //if ( interact.getText().contains("Interact")){
        // driver.swipe(972,899,15, 899,666);
        Thread.sleep(600);


        AndroidElement woreitBest = (AndroidElement) driver.findElementById("com.google.atap.jacquard:id/imageView_icon_sxsw");
        woreitBest.click();

        AndroidElement countText = (AndroidElement) driver.findElementById("com.google.atap.jacquard:id/ability_usage_count");
        countText.getText();


        //  test.log(LogStatus.INFO,"Count "+countText.getText());


        //  }


        return 3;
    }


    public void swipeLeft() {

        Dimension dimension = driver.manage().window().getSize();

        int height = dimension.getHeight();
        int width = dimension.getWidth();

        int stratx = (int) (width*0.9);
        int endx = (int) (width*0.1);

        int y = (int) (height * 0.8);

        driver.swipe(stratx,y,endx,y,1000);
        System.out.println("Swipe left");
        //driver.swipe(1110, 1898, 15, 1898, 666);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public int logInitialBatteryLevel() throws IOException {

//        CommandExec commandExec = new CommandExec();
//        String value =   commandExec.getBatteryDetails();
//        int integerValue = Integer.valueOf(value);
       int value = getBatteryDataAppium();
        Markup markup = MarkupHelper.createLabel("Initial Battery level: "+value, ExtentColor.GREEN);
        test.info(markup);

        return  value;

    }

    public int getBatteryDataAppium(){

        int value = 0;
        try {
            List<List<Object>> batteryInfoArray = driver.getPerformanceData("com.google.atap.jacquard","batteryinfo",50);

            System.out.println(batteryInfoArray);
            String val = batteryInfoArray.get(1).toString().replace("[","").replace("]","");
            System.out.println("BatteryLevel Appium"+val);
             value = Integer.valueOf(val);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return value;

    }





    public void logAndroidRelativeBattery(int Current) throws IOException {
        DateTime time = new DateTime();
      int hr =  time.getHourOfDay();
      int min = time.getMinuteOfHour();

//        CommandExec commandExec = new CommandExec();
//     String value =   commandExec.getBatteryDetails();
//        int integerValue = Integer.valueOf(value);
       int integerValue = getBatteryDataAppium();

        if(Current>integerValue){


            int difference  = Current-integerValue;
            System.out.println("Difference: "+difference);
            if (difference >=  5){

                Markup markup = MarkupHelper.createLabel("Battery level: " +integerValue+" Battery down by "+difference+" %" +" at "+hr+":"+min, ExtentColor.RED);
                test.info(markup);

            }else {

                Markup markup = MarkupHelper.createLabel("Battery level: " +integerValue+" Battery down by "+difference+" %" +" at "+hr+":"+min,  ExtentColor.ORANGE);
                test.info(markup);

            }



        }else{


            Markup markup = MarkupHelper.createLabel("Battery level: " +integerValue+" Battery Level not changed !!" +" at "+hr+":"+min, ExtentColor.GREEN);
            test.info(markup);



        }




    }







   public boolean isUserOnBoarded(){

        try {

            AndroidElement InteractScreen = (AndroidElement) driver.findElementByAndroidUIAutomator("new UiSelector().text(\"BRUSH IN\")");
            if (InteractScreen.isDisplayed()){
return  true;

            }
return  false;


        }catch (Exception e){

return false;
        }

    }



    public void log(String text) {

        Markup markup = MarkupHelper.createLabel(" " + text, ExtentColor.INDIGO);
        test.info(markup);


    }


    public void logAndroidBatteryLevel() {
        CommandExec commandExec = new CommandExec();

        String level = null;
        try {
            level = commandExec.getBatteryDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Markup markup = MarkupHelper.createLabel("Current BatteryLevel: " + level, ExtentColor.BLUE);
        test.info(markup);

    }

    public int getGestureRunTime() throws IOException {

        String path = System.getProperty("user.dir");
        InputStream in = getClass().getClassLoader().getResourceAsStream(path + "config.properties");
        FileInputStream fileInputStream = new FileInputStream(path + "/config.properties");

        System.out.println(fileInputStream);

        System.out.println(fileInputStream.toString());


        Properties properties = new Properties();
        properties.load(fileInputStream);
        String gesture = properties.getProperty("gestureRunTime");
        System.out.println(gesture);

      return   Integer.parseInt(gesture);


    }


    public void swipeRight() {

        System.out.println("Swipe right");

        Dimension dimension = driver.manage().window().getSize();
        int startx  = (int) (dimension.getWidth() * 0.1);
        int endx  = (int) (dimension.getWidth() * 0.6);

        int yStable = (int)(dimension.getHeight() * 0.9);
        driver.swipe(startx,yStable,endx,yStable, 1000);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void swipeinandout() {

        driver.swipe(972, 899, 15, 899, 666);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.swipe(455, 899, 972, 899, 666);

//        try{
//	        test.log(LogStatus.INFO,"finding the element" );
//
//	        AndroidElement interact = (AndroidElement) driver.findElementById("com.google.atap.jacquard:id/text_view_label");
//
//
//	        if ( interact.getText().contains("Interact"))
//	       {
//               driver.swipe(972,899,15, 899,666);
//           }
//
//        }catch (NoSuchElementException e){
//
//            driver.swipe(455,899,972, 899,666);
//        }


//        Dimension size = driver.manage().window().getSize();
//        int anchor = (int) (size.height * anchorPercentage);
//        int startPoint = (int) (size.width * startPercentage);
//        int endPoint = (int) (size.width * finalPercentage);
//        new TouchAction(driver).press(startPoint, anchor).waitAction(duration).moveTo(endPoint, anchor).release().perform();

        //In documentation they mention moveTo coordinates are relative to initial ones, but thats not happening. When it does we need to use the function below
        //new TouchAction(driver).press(startPoint, anchor).waitAction(duration).moveTo(endPoint-startPoint,0).release().perform();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        driver.swipe(972,899,15, 899,666);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        //   TouchAction action = new TouchAction(driver);

        // Tomorrow's work
        //
        //    action.waitAction(   Duration.ofSeconds(66));
    }

    public boolean isAppConnectedToJacket() {

        driver.manage().timeouts().implicitlyWait(15, SECONDS);

        // (AndroidDriver) driver.openNotifications();


        return true;


    }


    public boolean isMusicPlaying() {

        driver.manage().timeouts().implicitlyWait(15, SECONDS);

        //  driver.openNotifications();

        return true;


    }

    public boolean isbluetoothEnabled() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(15, SECONDS);

        driver.openNotifications();

//		  AndroidElement launcher = (AndroidElement) driver.findElement(By.id("com.google.android.apps.nexuslauncher:id/page_indicator"));
//      launcher.click();
//
//		AndroidElement searchTxt = (AndroidElement) driver
//				.findElement(By.id("com.google.android.apps.nexuslauncher:id/search_box_input"));
//		searchTxt.sendKeys("Settings");
//
//		AndroidElement settingsApp = (AndroidElement) driver
//				.findElement(By.xpath("//android.widget.TextView[@content-desc='Settings']"));
//
//
//		settingsApp.click();

        TouchAction action = new TouchAction(driver);


        AndroidElement openSettings = (AndroidElement) driver.findElementByAccessibilityId("Open quick settings.");

        openSettings.click();

        //AndroidElement bluetoothIcon = (AndroidElement) driver.findElement(By.xpath(Constants.bluetoothIcon));
        //AndroidElement bluetoothIcon = (AndroidElement) driver.findElementByXPath(Constants.bluetoothIcon);
        AndroidElement bluetoothIcon = (AndroidElement) driver.findElementByXPath("//android.widget.TextView[@text='Bluetooth']");


        action.longPress(bluetoothIcon).perform();

        AndroidElement bluetoothStatus = (AndroidElement) driver.findElementById("com.android.settings:id/switch_bar");
        String status = bluetoothStatus.getAttribute("text");
        if (status.contains("On")) {


            bluetoothStatus.click();

            Thread.sleep(1000);

            bluetoothStatus.click();

            Thread.sleep(5000);

            return true;

        } else {

            bluetoothStatus.click();

            return true;
        }


    }


//    public  WebElement fluentwait(AndroidDriver driver, final By byType){
//        Wait wait = new FluentWait(driver)
//                .withTimeout(45, TimeUnit.SECONDS)
//                .pollingEvery(5, TimeUnit.SECONDS)
//                .ignoring(NoSuchElementException.class);
//        WebElement foo = wait.until(new Function() {
//            public WebElement apply(AppiumDriver driver) {
//                return driver.findElement(byType);
//            }
//        });
//        wait.until(ExpectedConditions.elementToBeClickable(byType));
//        return foo;
//    }


    public boolean isElementPresentById(String id) {


        AndroidElement androidElement = null;
        boolean isElementPresent = false;


        try {
            //com.google.atap.jacquard:id/button_introducing_jacquard
            androidElement = (AndroidElement) driver.findElementById(id);

            // Waiting 30 seconds for an element to be present on the page, checking

            // for its presence once every 5 seconds.


            if (androidElement.isDisplayed()) {

                isElementPresent = true;
            }
        } catch (Exception e) {
            isElementPresent = false;
            //		test.log(LogStatus.INFO,"Element not found "+id);
        }

        return isElementPresent;


    }


    public AndroidElement findByid(String id) {
        AndroidElement androidElement = null;

        try {
            //com.google.atap.jacquard:id/button_introducing_jacquard
            androidElement = (AndroidElement) driver.findElementById(id);
        } catch (Exception e) {
            //		test.log(LogStatus.INFO,"Element not found by "+id);
            System.out.println("Element not found by " + id);
        }

        return androidElement;


    }


    public void launchJaquard() {

        driver.manage().timeouts().implicitlyWait(15, SECONDS);

        AndroidElement launcher = (AndroidElement) driver.findElement(By.id("com.google.android.apps.nexuslauncher:id/page_indicator"));
        launcher.click();


        //test.log(LogStatus.INFO, "searching for the app Jaquard");
        AndroidElement searchTxt = (AndroidElement) driver
                .findElement(By.id("com.google.android.apps.nexuslauncher:id/search_box_input"));
        searchTxt.sendKeys("Ja");

        driver.manage().timeouts().implicitlyWait(15, SECONDS);


        AndroidElement jaquardApp = (AndroidElement) driver
                .findElementByAccessibilityId("Jacquard");

        if (jaquardApp.isDisplayed()) {
            jaquardApp.click();
        } else {
            //test.log(LogStatus.FAIL, "could not app using search");
        }


    }

    public void clickOnWhatIsJaquard() {

        if (isElementPresentById("com.google.atap.jacquard:id/button_introducing_jacquard")) ;
        {
            AndroidElement whatisJaquard_button = findByid("com.google.atap.jacquard:id/button_introducing_jacquard");
            whatisJaquard_button.click();
        }
    }


    public void pressHome() {

        driver.pressKeyCode(AndroidKeyCode.HOME);
    }


    public void clickOngetStated() {


        if (isElementPresentById("com.google.atap.jacquard:id/button_start_jacquard")) {

            // 	test.log(LogStatus.INFO,"User on splash screen activity");
            System.out.println("Getting started screen displayed on foreground");
            AndroidElement getstartedButton = findByid("com.google.atap.jacquard:id/button_start_jacquard");
            getstartedButton.click();
            System.out.println("Getting started passed");
        }


    }


    public void keepApplicationInTheForeGround() {

        //System.out.println(driver.currentActivity());

        Dimension dimension = driver.manage().window().getSize();

        double x = dimension.getHeight() * 0.5;
        double y = dimension.getWidth() * 0.5;


        for (int i = 1; i <= 25; i++) {
            //  System.out.println(driver.currentActivity());
            TouchAction action = new TouchAction(driver);
            action.tap(((int) x), ((int) y)).perform();
        }

    }


    public void clickOnlearnInteractions() {
        if (isElementPresentById("com.google.atap.jacquard:id/learnInteractionsButton")) {

            //test.log(LogStatus.PASS,"Step : Getting started passed");
            AndroidElement learnInteraction = findByid("com.google.atap.jacquard:id/learnInteractionsButton");
            learnInteraction.click();


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void clickOnAcceptAgrement() {
        if (isElementPresentById("com.google.atap.jacquard:id/button_accept")) {
            AndroidElement accept = findByid("com.google.atap.jacquard:id/button_accept");
            accept.click();
            // test.log(LogStatus.PASS,"Clicked on tag is ready");
            System.out.println("Clicked on tag is ready");
        }


    }


    public void selectGoogleAccount() {

//		com.google.atap.jacquard:id/button_tag_ready

        if (isElementPresentById("com.google.android.gms:id/account_name")) {

            AndroidElement accountName = findByid("com.google.android.gms:id/account_name");
            accountName.click();

        }

    }

    public void clickOnILookFab() {


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (isElementPresentById("com.google.atap.jacquard:id/fabulous_button")) {

            AndroidElement accountName = findByid("com.google.atap.jacquard:id/fabulous_button");
            accountName.click();

        }

    }


    public void allowBluetoothAccess() {

        //		com.google.atap.jacquard:id/button_tag_ready

        if (isElementPresentById("com.android.packageinstaller:id/permission_allow_button")) {

            AndroidElement allowButton = findByid("com.android.packageinstaller:id/permission_allow_button");
            allowButton.click();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public boolean selectAJacket() {

        //		com.google.atap.jacquard:id/button_tag_ready
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (isElementPresentById("com.google.atap.jacquard:id/garment_identifier_textview")) {

            AndroidElement jacket = findByid("com.google.atap.jacquard:id/garment_identifier_textview");
            jacket.click();
            //test.log(LogStatus.PASS,"Jacket Found");
            System.out.println("Jacket found");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        } else {

            //	test.log(LogStatus.PASS,"Jacket not  Found");
            System.out.println("Not found");
            return false;
        }

    }

    public boolean clickTryOutNewThreads() {

        //		com.google.atap.jacquard:id/button_tag_ready

        if (isElementPresentById("com.google.atap.jacquard:id/try_threads_button")) {

            AndroidElement tryNewThreads = findByid("com.google.atap.jacquard:id/try_threads_button");
            tryNewThreads.click();
            return true;

        } else {

            return false;
        }

    }


    public void pressBackOn() {

        if (isElementPresentById("com.google.atap.jacquard:id/back_button")) {
            AndroidElement backButton = findByid("com.google.atap.jacquard:id/back_button");
            backButton.click();
        } else {

            System.out.println("Back button not found");
            //   takeScreenShot();

        }
    }

    public void clickOnTagIsReady() {

//		com.google.atap.jacquard:id/button_tag_ready

        if (isElementPresentById("com.google.atap.jacquard:id/button_tag_ready")) {

            AndroidElement tagIsReady = findByid("com.google.atap.jacquard:id/button_tag_ready");
            tagIsReady.click();
            //test.log(LogStatus.PASS,"Clicked on tag is ready");
        }

    }

    public void clickonEnableJacquard() {
        //driver.runAppInBackground(Duration.ofSeconds(66));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (isElementPresentById("com.google.atap.jacquard:id/button_enable_jacquard")) {

            AndroidElement enableJacquard = findByid("com.google.atap.jacquard:id/button_enable_jacquard");
            enableJacquard.click();
            //	test.log(LogStatus.PASS,"Step : Enable jaquardNow clicked");
        }


    }


    //com.google.atap.jacquard:id/button_enable_jacquard
    public void playVideosOnWhatIsJaquard() {

        if (isElementPresentById("com.google.atap.jacquard:id/intro_video_button") && isElementPresentById("com.google.atap.jacquard:id/welcome_video_button"))
            ;
        {
            AndroidElement firstVideo = findByid("com.google.atap.jacquard:id/intro_video_button");
            firstVideo.click();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            // driver.pressKeyCode(AndroidKeyCode.BACK);

            AndroidElement secondVideo = findByid("com.google.atap.jacquard:id/welcome_video_button");
            secondVideo.click();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //   driver.pressKeyCode(AndroidKeyCode.BACK);

            isElementPresentById("com.google.atap.jacquard:id/back_button");
            AndroidElement backButton = findByid("com.google.atap.jacquard:id/back_button");
            backButton.click();
        }
    }

    public void clickOnBlushinAndBack() {


    }


    public void takeApplicationinBackground(double minutes) {


    }

    public void takeApplicationinBackground(double minutes, String Screen) {

        //  test.log(LogStatus.INFO,"Application will be background for "+minutes*60 +" seconds");

//            commadLineExec = new CommadLineExec(test);

//            test.log(LogStatus.INFO,"The user is on the ##### "+Screen+"#####");
//            String cpuusage =  commadLineExec.getcurrenCPUUsage();
//            System.out.println(cpuusage);
//            pressHome();
//            test.log(LogStatus.PASS, "** Battery " + commadLineExec.getcurrentBatteryLevel() + " ** Battery " + commadLineExec.getcurrentBatteryTemperature() + "** CPU usage:   " +cpuusage);
//            //   test.log(LogStatus.INFO,"** Battery "+commadLineExec.getcurrentBatteryLevel()+" While the app in foreground");
//            try {
//
//                Thread.sleep((long) (minutes*60000));
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            test.log(LogStatus.PASS, "** Battery " + commadLineExec.getcurrentBatteryLevel() + " ** Battery " + commadLineExec.getcurrentBatteryTemperature() + "** CPU usage:   " + commadLineExec.getcurrenCPUUsage());
//            // test.log(LogStatus.INFO,"** Battery "+commadLineExec.getcurrentBatteryLevel()+" While the app in background");
//            launchJaquard();
//

    }

    public void printTheExistingScreen(String screen) {
        Markup markup = MarkupHelper.createLabel("User is on: " + screen, ExtentColor.INDIGO);


        test.info(markup);


    }


    public void printTheExistingScreen() {

        String activity = driver.currentActivity();
        System.out.println(activity);

        String[] values = activity.split(".");

        //  System.out.println(values[2]);
        System.out.println(values);

        for (String value : values) {
            System.out.println(value);
        }
        Markup markup = MarkupHelper.createLabel("User is on: " + activity, ExtentColor.INDIGO);
        test.info(markup);


    }


    public void clickonGotIt() {
        //com.google.atap.jacquard:id/positive_button
        driver.manage().timeouts().implicitlyWait(20, SECONDS);
        ;
        if (isElementPresentById("com.google.atap.jacquard:id/positive_button")) {
            AndroidElement gotItButton = findByid("com.google.atap.jacquard:id/positive_button");
            gotItButton.click();
        } else {
//                takeScreenShot();
            // TakesScreenshot();
            //test.log();

        }


    }

    public void clickOnTutorialBrushIn() throws InterruptedException {
        try {

            AndroidElement brushIn = (AndroidElement) driver.findElementByAndroidUIAutomator("new UiSelector().text(\"BRUSH IN\")");
            brushIn.click();

        } catch (Exception e) {

            System.out.println("Brush in button not found");
        }
    }


    public void perfromRandomGestures(){




    }







    public void assignBrushOutGesture() throws Exception {

        String gesture = getGesture(GESTURES.DOUBLETAP);
        System.out.println("'Double Tap"+ gesture );
        assignPirtucularGesture(gesture);







//        String path = System.getProperty("user.dir");
//        InputStream in = getClass().getClassLoader().getResourceAsStream(path + "config.properties");
//        FileInputStream fileInputStream = new FileInputStream(path + "/config.properties");
//
//        System.out.println(fileInputStream);
//
//        System.out.println(fileInputStream.toString());
//
//
//        Properties properties = new Properties();
//        properties.load(fileInputStream);
//        String gesture = properties.getProperty("assignBrushoutGesture");
//        System.out.println(gesture);
//        switch (gesture) {
//
//            case "time":
//                swipeLeft();
//                swipeLeft();
//                log("Assigned Brushout to readout current time !!!");
//                assignabilityNo(3);
//                break;
//
//            case "count":
//                swipeLeft();
//                swipeLeft();
//                swipeLeft();
//                assignabilityNo(0);
//                log("Assigned Brushout to count favourite things !!!");
//                break;
//
//
//            case "eta":
//                swipeLeft();
//                swipeLeft();
//                assignabilityNo(0, "");
//                log("Assigned BrushOut to readout ETA !!!");
//                break;
//
//
//            default:
//                assignabilityNo(0);
//                log("Assigned default ");
//                break;
//
//        }
    }

    public void assignDoubleTapGesture() throws Exception {


        String gesture = getGesture(GESTURES.DOUBLETAP);
        System.out.println("'Double Tap"+ gesture );
        assignPirtucularGesture(gesture);

    }


    public String getGesture(GESTURES gestures) throws IOException {

        String gesture = null;


        String path = System.getProperty("user.dir");
        InputStream in = getClass().getClassLoader().getResourceAsStream(path + "config.properties");
        FileInputStream fileInputStream = new FileInputStream(path + "/config.properties");

        System.out.println(fileInputStream);

        System.out.println(fileInputStream.toString());


        Properties properties = new Properties();
        properties.load(fileInputStream);

        //System.out.println(gesture);


        switch (gestures){
            case BRUSHIN:
                gesture = properties.getProperty("assignBrushinGesture");
            break;
            case BRUSHOUT:
                gesture = properties.getProperty("assignBrushoutGesture");
            break;
            case DOUBLETAP:
                gesture = properties.getProperty("assignDoubleTapGesture");
             break;
        }

        return gesture;

    }


    public void assignPirtucularGesture(String gesture) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(gesture);
        switch (gesture) {

            case "time":
                swipeLeft();
                swipeLeft();
                assignabilityNo(3);
                log("Assigned Brushout to readout current time !!!");
                break;

            case "count":
                swipeLeft();
                swipeLeft();
                swipeLeft();
                assignabilityNo(0);
                log("Assigned Brushout to count favourite things !!!");
                break;


            case "eta":
                swipeLeft();
                swipeLeft();
                assignabilityNo(0, "");
                log("Assigned BrushOut to readout ETA !!!");
                break;


            default:
                assignabilityNo(0);
                log("Assigned default ");
                break;


        }


    }


    public void swipeUp() {

        Dimension dimensions = driver.manage().window().getSize();

        Double cordinateX = dimensions.getHeight() * 0.5;
        int xvalue = cordinateX.intValue();

        Double screenHeightStart = dimensions.getHeight() * 0.2;
        int scrollStart = screenHeightStart.intValue();
        System.out.println(scrollStart);
        Double screenHeightEnd = dimensions.getHeight() * 0.9;

        int scrollEnd = screenHeightEnd.intValue();
        System.out.println(scrollEnd);

        driver.swipe(xvalue, scrollEnd, xvalue, scrollStart, 1000);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Scroll Up");
    }


    public void assignBrushInGesture() throws Exception {


        String path = System.getProperty("user.dir");
        InputStream in = getClass().getClassLoader().getResourceAsStream(path + "config.properties");
        FileInputStream fileInputStream = new FileInputStream(path + "/config.properties");

        System.out.println(fileInputStream);

        System.out.println(fileInputStream.toString());


        Properties properties = new Properties();
        properties.load(fileInputStream);
        String gesture = properties.getProperty("assignBrushinGesture");
        System.out.println(gesture);
        switch (gesture) {

            case "time":
                swipeLeft();
                swipeLeft();
                assignabilityNo(3);
                log("Assigned Brushin to readout current time !!!");
                break;

            case "count":
                swipeLeft();
                swipeLeft();
                swipeLeft();
                swipeLeft();
                assignabilityNo(0);
                log("Assigned Brushin to count favourite things !!!");
                break;


            case "eta":
                swipeLeft();
                swipeLeft();
                assignabilityNo(0,"");
                log("Assigned Brushin to readout ETA !!!");
                break;


            default:

                assignabilityNo(0);
                break;


        }


    }


    public void clickOnTutorialBrushOut() throws InterruptedException {
        try {
           Thread.sleep(2000);
            AndroidElement brushIn = (AndroidElement) driver.findElementByAndroidUIAutomator("new UiSelector().text(\"BRUSH OUT\")");
            brushIn.click();

        } catch (Exception e) {

            System.out.println("Brush in button not found");
        }
    }



    public void clickOnDoubleTapTutorial() throws InterruptedException {
        try {
            Thread.sleep(2000);
            AndroidElement doubleTap = (AndroidElement) driver.findElementByAndroidUIAutomator("new UiSelector().text(\"DOUBLE TAP\")");
            doubleTap.click();
            System.out.println("Double tap clicked");

        } catch (Exception e) {

            System.out.println("double tap tutorial did not found");
        }
    }






    public void assignabilityNo(int ability, String address) {

        try {


            AndroidElement abilityToBeAssigned = findByid("com.google.atap.jacquard:id/ability_collection_cell_" + ability);
            abilityToBeAssigned.click();

            AndroidElement setAddress = findByid("com.google.atap.jacquard:id/destination_text");
            setAddress.click();


            Thread.sleep(2000);
            setAddress.click();

            Thread.sleep(3000);

            AndroidElement setAddressValue = findByid("com.google.atap.jacquard:id/destination_edit_text");

//setAddressValue.clear();

            setAddressValue.sendKeys("pune airport");

            Thread.sleep(3000);

            AndroidElement value  = (AndroidElement) driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Pune Airport\")");
            value.click();

            AndroidElement walkingImage = findByid("com.google.atap.jacquard:id/walking_image");
            walkingImage.click();




            AndroidElement assignButton = findByid("com.google.atap.jacquard:id/assign_button");
            assignButton.click();

            Thread.sleep(2000);
            AndroidElement exit = findByid("com.google.atap.jacquard:id/back_button");
            exit.click();


        } catch (Exception e) {

            System.out.println("Already assigned");
        }
//        AndroidElement exit = findByid("com.google.atap.jacquard:id/back_button");
//        exit.click();

    }












    public void assignabilityNo(int ability) {

          try {


              AndroidElement abilityToBeAssigned  =  findByid("com.google.atap.jacquard:id/ability_collection_cell_"+ability);
    abilityToBeAssigned.click();


    AndroidElement assignButton = findByid("com.google.atap.jacquard:id/assign_button");
    assignButton.click();

}catch (Exception e){

    System.out.println("Already assigned");
}
        AndroidElement exit  =  findByid("com.google.atap.jacquard:id/back_button");
        exit.click();

    }
}









