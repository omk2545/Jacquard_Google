package com.google.jacquard.ios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import com.google.jacquard.base.PageActivity;
import com.google.jacquard.ios.base.IosActivity;
import com.google.jacquard.utils.Constants;
import io.appium.java_client.*;
import io.appium.java_client.ios.IOSTouchAction;
import org.joda.time.DateTime;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.google.jacquard.utils.CommandExec;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class JacquardAppleHelper  extends IosActivity {
   // ExtentTest test;
   // IOSDriver driver;

    public JacquardAppleHelper(IOSDriver driver, ExtentTest test) {
        super(driver, test);
    }

//    public JacquardAppleHelper(AppiumDriver driver, ExtentTest test) {
//        this.driver = (IOSDriver) driver;
//        this.test = test;
//    }


    public void exitCommuterScreen(){


        Dimension dimensions = driver.manage().window().getSize();
        Double screenHeightStart = dimensions.getHeight() * 0.5;
        int x = screenHeightStart.intValue();
        Double screenHeightEnd = dimensions.getWidth() * 0.95;
        int y = screenHeightEnd.intValue();
       // driver.swipe(200, scrollEnd, 200, scrollStart, 1000);

        TouchAction action = new TouchAction(driver);
        action.tap(x,y).perform();

        Markup markup = MarkupHelper.createLabel("Performing BrushIn Gesture ", ExtentColor.CYAN);
        test.info(markup);
        System.out.println("Performing BrushIn");


    }

    public void COVER(){


        Dimension dimensions = driver.manage().window().getSize();
        Double screenHeightStart = dimensions.getWidth() * 0.5;
        int x1 = screenHeightStart.intValue();

        Double yS1 = dimensions.getHeight() * 0.6;
        int y1 = screenHeightStart.intValue();




        TouchAction touchAction = new TouchAction(driver);
       // touchAction.press(x1,y1).waitAction(Duration.ofSeconds(3)).release().press(x1+4,y1).waitAction(Duration.ofSeconds(3)).release().press(x1+5,y1).waitAction(Duration.ofSeconds(1)).release();


driver.performTouchAction(touchAction);
//        TouchAction touchAction1 = new TouchAction(driver);
//        touchAction.press(x1+3,y1).waitAction(Duration.ofSeconds(3)).release();
//
//        TouchAction touchAction2 = new TouchAction(driver);
//        touchAction.press(x1+8,y1).waitAction(Duration.ofSeconds(3)).release();

//        MultiTouchAction multiTouchAction = new MultiTouchAction(driver);
//
//        multiTouchAction.add(touchAction).add(touchAction1).add(touchAction2);

        //driver.performMultiTouchAction(multiTouchAction);
    }

    public void assignDoubleTapGesture() {



        System.out.println("Inside the double tap");


        String  assigenedAbility = "play";
        try {
            assigenedAbility = getDataFromConfig(GESTURES.DOUBLETAP);
        } catch (Exception e) {
            e.printStackTrace();
        }


        clickOnPirticularActivity(assigenedAbility);
        IOSElement assignBtn = findByAccessibility("Assign to Double Tap");
       try {
           assignBtn.click();

       }catch (Exception e){


       }
        clickOnExitButton();

        logDetails(assigenedAbility+" is assigned to the double tap gersture");

    }








    public void assignBrushOutGesture() {



        System.out.println("Inside the Assign Brush out");


        String  assigenedAbility = "play";
        try {
            assigenedAbility = getDataFromConfig(GESTURES.BRUSHOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }


        clickOnPirticularActivity(assigenedAbility);
        IOSElement assignBtn = findByAccessibility("Assign to Brush Out");
        assignBtn.click();
        clickOnExitButton();
        logDetails(assigenedAbility+" is assigned to the Brush-out gersture");


    }


    public void clickOnNavigation(){

        IOSElement navigationText = findByAccessibility("NAVIGATION");
        navigationText.click();


    }
    public void clickOnKeepTrack(){

        IOSElement keepTrack = findByAccessibility("KEEP TRACK");
        keepTrack.click();


    }



    public  enum GESTURES{

        DOUBLETAP,BRUSHIN,BRUSHOUT,GESTURERUNTIME

    }

    public void performALlGestures(int count){


        for(int i = 0;i< count;i++){

            try {

            BrushIn();

            BrushOut();

            doubleTap();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        Markup markup = MarkupHelper.createLabel("Performed Brush in, Brush out, Double Tap for " +count + " times" ,ExtentColor.CYAN);
        test.info(markup);

    }


    public void gettingStarted() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        IOSElement getingStated = (IOSElement) findByAccessibility("Get Started");
        getingStated.click();
        System.out.println("Clicked on getting started");


    }

    public void sleep(int miliseconds) {

        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep for " + miliseconds);

    }


    public void openMenu() {


        IOSElement hangerIcon = (IOSElement) driver.findElementByAccessibilityId("icMenu");
        hangerIcon.click();
        System.out.println("opening Menu");


    }


    public void clickJacquardTechnology() {

        //  IOSElement jacquardTechnologyIcon = (IOSElement) driver.findElementByAccessibilityId("Jacquard Technology");
        IOSElement jacquardTechnologyIcon = findByAccessibility("Jacquard Technology");
        jacquardTechnologyIcon.click();

    }


    public void clickOnSetUpNewJacket() {

        swipeUp();
//        IOSElement setupJacket = (IOSElement) driver.findElementByAccessibilityId("Set up new Jacket or Tag");
       // IOSElement setupJacket = (IOSElement) driver.findElementByAccessibilityId("Set up new Jacket or Tag");

        IOSElement setupJacket = findByAccessibility("Set up new Jacket or Tag");
        setupJacket.click();
        System.out.println("Set up new Jacket clicked ");

    }


    public void clickOnTagIsReady() {

        IOSElement clickOnTagIsReady = findByAccessibility("Tag is ready");
        clickOnTagIsReady.click();

        System.out.println("Set up new Jacket clicked ");
    }

    public void clickOnIlookFab() {

        IOSElement clickOnTagIsReady = findByAccessibility("I Look Fabulous");
        clickOnTagIsReady.click();


    }


    public void addinngNewJacket() {


        openMenu();
        sleep(3000);
        clickJacquardTechnology();
        clickOnSetUpNewJacket();
        clickOnTagIsReady();

        sleep(10000);
        clickOnIlookFab();

        sleep(15000);

        clickOnSearchAgain();

        sleep(10000);

        //  clickOnSearchAgain();

        //sleep(10000);

        selectJacket();

        //resetApp();
    }

    public void clickOnBrushIn() {

        sleep(1500);


        List<IOSElement> elements = driver.findElementsByAccessibilityId("Gesture Name");
        System.out.println(elements.size());

        for (IOSElement element : elements) {
            System.out.println(element.getAttribute("label"));
        }
        System.out.println("Clicking on the " + elements.get(0).getAttribute("label"));
        elements.get(0).click();


    }




    public void clickOnBrushOut() {

        sleep(1500);

        List<IOSElement> elements = driver.findElementsByAccessibilityId("Gesture Name");
        System.out.println(elements.size());

        for (IOSElement element : elements) {
            System.out.println(element.getAttribute("label"));
        }
        System.out.println("Clicking on the " + elements.get(2).getAttribute("label"));
        elements.get(2).click();

    }

    public void clickOnDoubleTap() {


        List<IOSElement> elements = driver.findElementsByAccessibilityId("Gesture Name");
        System.out.println(elements.size());

        for (IOSElement element : elements) {
            System.out.println(element.getAttribute("label"));
        }
        System.out.println("Clicking on the " + elements.get(1).getAttribute("label"));
        elements.get(1).click();

    }


//    public void assignActivity(String gesture) {
//
//
//        System.out.println(gesture);
//        switch (gesture) {
//
//            case "time":
//                swipeLeft();
//                swipeLeft();
//                //  assignabilityNo(3);
//                //log("Assigned Brushout to readout current time !!!");
//                break;
//
//            case "count":
//                swipeLeft();
//                swipeLeft();
//                swipeLeft();
//                //assignabilityNo(0);
//                //log("Assigned Brushout to count favourite things !!!");
//                break;
//
//
//            case "eta":
//                swipeLeft();
//                swipeLeft();
//               // assignability();
//                //log("Assigned BrushOut to readout ETA !!!");
//                break;
//
//
//            default:
//                // assignabilityNo(0);
//                //log("Assigned default ");
//                break;
//
//
//        }
//
//
//    }

//    public void assignability(String address) {
//
//        try {
//            BrushOut();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//        //driver.findElementByIosNsPredicate("name CONTAINS 'Assign'").click();
//
//    }


    public void assignability_Working(String abilityName) {


//        try {
//            BrushOut();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        IOSElement walking = findByAccessibility("Walking");
//        walking.click();
//
//        driver.findElementByIosNsPredicate("name CONTAINS 'Assign'").click();

    }











    public void swipeRight() {

        System.out.println("Swipe right");

        Dimension dimension = driver.manage().window().getSize();
        int startx = (int) (dimension.getWidth() * 0.3);
        int endx = (int) (dimension.getWidth() * 0.9);

        int yStable = (int) (dimension.getHeight() * 0.9);
        driver.swipe(startx, yStable, endx, yStable, 1000);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public void swipeLeft() {

        System.out.println("Inside The swipe Left");
        Dimension dimension = driver.manage().window().getSize();

        int height = dimension.getHeight();
        int width = dimension.getWidth();

        int stratx = (int) (width * 0.9);
        int endx = (int) (width * 0.1);

        int y = (int) (height * 0.8);

        driver.swipe(stratx, y, endx, y, 2000);
        System.out.println("Swipe left");
        //driver.swipe(1110, 1898, 15, 1898, 666);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public void clickOnSearchAgain() {
        swipeUp();
        IOSElement searchAgainText = findByAccessibility("Search Again");
        searchAgainText.click();
        System.out.println("Search again ");
    }


    public void selectJacket() {

        //Levi’s® Commuter™ Jacket


        IOSElement jacketId = findByAccessibility(Constants.jacket_Id_Ios);
        jacketId.click();

        System.out.println("Jacket Found ");
    }


    public void resetApp() {

        System.out.println("Reseting app...");
       driver.resetApp();

    }


    public boolean isElementPresentById(String id) {


        IOSElement element;


        try {

            element = (IOSElement) driver.findElementByAccessibilityId(id);

            if (element.isDisplayed()) {

                System.out.println("Element present by: " + id);
                return true;

            } else {

                return false;
            }

        } catch (Exception e) {

            return false;
        }

    }


    public IOSElement findByAccessibility(String id) {

        IOSElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.visibilityOf(By.));


        if (isElementPresentById(id)) {
            element = (IOSElement) driver.findElementByAccessibilityId(id);
            wait.until(ExpectedConditions.visibilityOf(element));
            System.out.println("Element Present " + id);
            return element;

        } else {
            System.out.println("Not able to find on the "+id);
            return null;
        }


    }

    public void selectAccount() {

        IOSElement account;
        account = findByAccessibility(Constants.userName);
        account.click();

    }


    public void acceptUserAgreeMent() {


        IOSElement agreeButton;
        agreeButton = findByAccessibility("Agree");
        agreeButton.click();

    }


    //
//	public void clickIlookFab() {
//
//		//I Look Fabulous
//IOSElement ilookFabButton;
//
//ilookFabButton = findByAccessibility("I Look Fabulous");
//ilookFabButton.click();
//
//
//
//
//	}
    public void takeDeviceInBackGround(int time) {

        driver.runAppInBackground(time);
    }

    public void acceptOkayPopup() {

        IOSElement ok;

        ok = findByAccessibility("Ok");
        ok.click();


    }

    public Map logInitalBatteryLevel(Map map){




        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");


        Calendar cal = Calendar.getInstance();
        System.out.println(simpleDateFormat.format(cal.getTime()));

        String timeValue = simpleDateFormat.format(cal.getTime());

        CommandExec commandExec = new CommandExec();
        String batteryLevel = commandExec.getIphoneBatteryLevel(Constants.iphone_udid);


        System.out.println("Battery Level" +batteryLevel);

        int batteryValue = Integer.valueOf(batteryLevel);

        Markup markup = MarkupHelper.createLabel("Initial battery Level: " + batteryLevel + "@ time "+timeValue, ExtentColor.GREEN);
        test.info(markup);

        map.put(timeValue,batteryValue);


return  map;

    }





    public int logInitalBatteryLevel(){


        CommandExec commandExec = new CommandExec();
        String batteryLevel = commandExec.getIphoneBatteryLevel(Constants.iphone_udid);

        System.out.println("Battery Level " +batteryLevel);

int batteryValue = Integer.valueOf(batteryLevel);

        Markup markup = MarkupHelper.createLabel("Initial battery Level: " + batteryLevel, ExtentColor.GREEN);
       // test.info(markup);

return batteryValue;


    }






    public void iosLogRelativeBatteryLevels(int Current){


        DateTime time = new DateTime();
        int hr =  time.getHourOfDay();
        int min = time.getMinuteOfHour();

        CommandExec commandExec = new CommandExec();
    String value =   commandExec.getIphoneBatteryLevel(Constants.iphone_udid);
        int integerValue = Integer.valueOf(value);
        //int integerValue =

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



    public Map iosLogRelativeBatteryLevels(Map map,int Current){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");


        Calendar cal = Calendar.getInstance();
        System.out.println(simpleDateFormat.format(cal.getTime()));

        String timeValue = simpleDateFormat.format(cal.getTime());


        CommandExec commandExec = new CommandExec();

        String value =   commandExec.getIphoneBatteryLevel(Constants.iphone_udid);
        System.out.println("Value "+value);
        value.replace(" ","");

//        int integerValue = 100;

       int integerValue = Integer.valueOf(value);

        System.out.println("Omkar "+integerValue);
//        System.out.println(integerValue);
       // map.put(100,"fff  ");
map.put(timeValue,integerValue);


        if(Current>integerValue){


            int difference  = Current-integerValue;
            System.out.println("Difference: "+difference);
            if (difference >=  5){

                Markup markup = MarkupHelper.createLabel("Battery level: " +integerValue+" Battery down by "+difference+" %" +" at "+timeValue, ExtentColor.RED);
                test.info(markup);

            }else {

                Markup markup = MarkupHelper.createLabel("Battery level: " +integerValue+" Battery down by "+difference+" %" +" at "+timeValue,  ExtentColor.ORANGE);
                test.info(markup);

            }



        }else{


            Markup markup = MarkupHelper.createLabel("Battery level: " +integerValue+" Battery Level not changed !!" +" at "+timeValue, ExtentColor.GREEN);
            test.info(markup);

        }



        return  map;
    }







    public void logBatteryStatus() {

        CommandExec commandExec = new CommandExec();
String batteryLevel = commandExec.getIphoneBatteryLevel(Constants.iphone_udid);

        System.out.println("Battery Level" +batteryLevel);


        Markup markup = MarkupHelper.createLabel("Battery Level: " + batteryLevel, ExtentColor.BLUE);
        test.info(markup);


    }





    public void logUserScreen(String screen) {

        Markup markup = MarkupHelper.createLabel("User is currently on the " + screen, ExtentColor.GREEN);
        test.info(markup);


    }


    public void logDetails(String details) {

        Markup markup = MarkupHelper.createLabel(details, ExtentColor.GREEN);
        test.info(markup);


    }
    public void performDoubleTapMultiple(int no) throws InterruptedException {

        for (int i = 0; i < no; i++) {

            doubleTap();
        }
    }

    public void performBlushInMultiple(int no) throws InterruptedException {

        for (int i = 0; i < no; i++) {

            BrushIn();
        }
    }


    public void performBlushoutMultiple(int no) throws InterruptedException {

        for (int i = 0; i < no; i++) {

            BrushOut();
        }
    }


    public boolean isUserOnBoarded(){


 boolean isUserOnBoarded = false;
 if(isIconDisplaye("Interact")){
     isUserOnBoarded = true;
     System.out.println("Displayed ");
 }else{

     isUserOnBoarded = false;

 }

return isUserOnBoarded;


    }


    public void enableJacquard() {
        try {
            BrushIn();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IOSElement enableJacquardButton;

        enableJacquardButton = findByAccessibility("Enable Jacquard");
        enableJacquardButton.click();
        System.out.println("Enable jacquard clicked");


    }



    public boolean IsEmulatorConnected() {

        try {
            IOSElement connectedText = (IOSElement) driver.findElementByName("Connected");
            if (connectedText.isDisplayed()) {
                return true;
            } else {

                return false;
            }

        } catch (Exception e) {

            return false;
        }

    }

    public void BrushIn() throws InterruptedException {

        Dimension dimensions = driver.manage().window().getSize();
        Double screenHeightStart = dimensions.getHeight() * 0.5;
        int scrollStart = screenHeightStart.intValue();
        Double screenHeightEnd = dimensions.getHeight() * 0.2;
        int scrollEnd = screenHeightEnd.intValue();
        driver.swipe(200, scrollStart, 200, scrollEnd, 100);
        Thread.sleep(2000);
        Markup markup = MarkupHelper.createLabel("Performing BrushIn Gesture ", ExtentColor.CYAN);
       // test.info(markup);
        System.out.println("Performing BrushIn");
    }

    public void swipeUp(){



        Dimension dimensions = driver.manage().window().getSize();
        Double screenHeightStart = dimensions.getHeight() * 0.2;
        int scrollStart = screenHeightStart.intValue();
        Double screenHeightEnd = dimensions.getHeight() * 0.7;
        int scrollEnd = screenHeightEnd.intValue();
        driver.swipe(200, scrollEnd, 200, scrollStart, 1000);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Markup markup = MarkupHelper.createLabel("Performing BrushIn Gesture ", ExtentColor.CYAN);
        test.info(markup);
        System.out.println("Performing BrushIn");




    }





    public void BrushOut() throws InterruptedException {

        Dimension dimensions = driver.manage().window().getSize();
        Double screenHeightStart = dimensions.getHeight() * 0.7;
        int scrollStart = screenHeightStart.intValue();
        Double screenHeightEnd = dimensions.getHeight() * 0.2;
        int scrollEnd = screenHeightEnd.intValue();
        driver.swipe(200, scrollEnd, 200, scrollStart, 100);
        Thread.sleep(2000);
        Markup markup = MarkupHelper.createLabel("Performing BrushOut Gesture ", ExtentColor.CYAN);
       // test.info(markup);
        System.out.println("Performing Brushout");
    }


    public void doubleTap() throws InterruptedException {

        Dimension dimensions = driver.manage().window().getSize();
        int ycordinate = (int) (dimensions.getWidth() * 0.5);
        int xcordinate = (int) (dimensions.getHeight() * 0.5);
        driver.context("NATIVE_APP");
        System.out.println(xcordinate + "  " + ycordinate);
        System.out.println("Performing double tap");


        IOSTouchAction action = new IOSTouchAction(driver);
        IOSElement element = (IOSElement) driver.findElementByAccessibilityId("indicator_middle.png");

        action.doubleTap(element).perform();
        Thread.sleep(2000);
        Markup markup = MarkupHelper.createLabel("Performing DoubleTap Gesture ", ExtentColor.CYAN);
       // test.info(markup);
    }

    public boolean isNewUser() {

        boolean isNewuser = false;
        try {

            IOSElement getingStated = (IOSElement) findByAccessibility("Get Started");

            if (getingStated.isDisplayed()) {
                return true;
            }
        } catch (Exception e) {

            return false;

        }

        return false;
    }

    public boolean isIconDisplaye(String id){

        boolean iselementPresent = false;
        try {

            IOSElement element = findByAccessibility(id);
            if (element.isDisplayed()) {
                iselementPresent= true;
            }
        }catch (Exception e){
            iselementPresent = false;

        }

        return iselementPresent;

    }


    public void assignPirticularAbility(String id){
        System.out.println("Inside function");

        for (int i = 0; i< 4;i++ ){

            if (isIconDisplaye(id)){

                IOSElement element = findByAccessibility(id);
                element.click();
                break;

            }else{
                System.out.println("Swipe "+i);
                swipeLeft();

            }

        }





    }




    public void assignBrushInGesture() {

        System.out.println("Inside the Assign Brush in");

        String  assigenedAbility = "play";
        try {
            assigenedAbility = getDataFromConfig(GESTURES.BRUSHIN);
        } catch (Exception e) {
            e.printStackTrace();
        }


        clickOnPirticularActivity(assigenedAbility);
        IOSElement assignBrushInIcon = findByAccessibility("Assign to Brush In");
         assignBrushInIcon.click();
       clickOnExitButton();

       logDetails(assigenedAbility+" is assigned to the brush in gersture");

    }

    public void clickOnExitButton(){

        IOSElement closeIcon = findByAccessibility("×");
        closeIcon.click();




    }



public void clickOnPirticularActivity(String activity ) {

    System.out.println(activity);

    switch (activity) {

        case "play & pause":

            assignPirticularAbility(Constants.playPauseIcon_IOS);
            break;


        case "next":
            assignPirticularAbility(Constants.nextIcon_IOS);
            break;

        case "previous":
            assignPirticularAbility(Constants.previousIcon_IOS);
            break;

        case "whats playing":

            assignPirticularAbility(Constants.whatsPlayingIcon_Ios);
            break;

        case "eta":
            clickOnNavigation();
         //   sleep(2000);

            assignPirticularAbility(Constants.etaIcon_Ios);
            try {
//                sleep(3000);
//                swipeUp();
//                sleep(2000);

                IOSElement walking = findByAccessibility(Constants.walking_icon_eta_ios);
                walking.click();

                if (isIconDisplaye(Constants.setAddress_textbox_ios)) {

                    IOSElement setAddress = findByAccessibility(Constants.setAddress_textbox_ios);
                    setAddress.click();

                    //CODE TO GET FROM CONFIGFILE

                    IOSElement destinationField = findByAccessibility(Constants.destinationField_textbox_ios);
                    destinationField.sendKeys("pune airport departure");

//                    sleep(3000);

                    IOSElement firstPlace = findByAccessibility(Constants.place_text_ios);
                    firstPlace.click();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


            break;

        case "next direction":
            clickOnNavigation();
            assignPirticularAbility(Constants.nextDirectionIcon);
           // swipeUp();
            IOSElement walking = findByAccessibility(Constants.walking_icon_eta_ios);
            walking.click();

            if (isIconDisplaye(Constants.setAddress_textbox_ios)) {

                IOSElement setAddress = findByAccessibility(Constants.setAddress_textbox_ios);
                setAddress.click();

                //CODE TO GET FROM CONFIGFILE

                IOSElement destinationField = findByAccessibility(Constants.destinationField_textbox_ios);
                destinationField.sendKeys("pune airport departure");

               sleep(3000);

                IOSElement firstPlace = findByAccessibility(Constants.place_text_ios);
                firstPlace.click();
            }

            break;


        case "current time":
            clickOnKeepTrack();
            assignPirticularAbility(Constants.currentTimeIcon_IOS);
            break;

        case "count":
            clickOnKeepTrack();
            assignPirticularAbility(Constants.countIcon_IOS);
            break;


        default:
            assignPirticularAbility(Constants.playPauseIcon_IOS);

            break;


    }
}

    public String getDataFromConfig( GESTURES gestures) throws Exception {
        String gesture = null;


        String path = System.getProperty("user.dir");
        InputStream in = getClass().getClassLoader().getResourceAsStream(path + "config.properties");
        FileInputStream fileInputStream = new FileInputStream(path + "/config.properties");

        System.out.println(fileInputStream);

        System.out.println(fileInputStream.toString());


        Properties properties = new Properties();
        properties.load(fileInputStream);

        //System.out.println(gesture);


        switch (gestures) {
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
}


















