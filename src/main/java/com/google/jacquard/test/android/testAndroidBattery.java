package com.google.jacquard.test.android;

import com.aventstack.extentreports.ExtentTest;
import com.google.jacquard.android.JacquardAndroidHelper;
import com.google.jacquard.ios.JacquardAppleHelper;
import com.google.jacquard.test.base.TestBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by user on 6/1/17.
 */
public class testAndroidBattery extends TestBase{


    public AndroidDriver androidDriver;
    public IOSDriver iosDriver;
    ExtentTest test;





    @Test
    public void testCompleteFlow() throws Exception {




        test = createTest("CompleteFlow Test");


        //iosDriver = createSimulatorInstance();

        androidDriver = createAndroidInstance();
        iosDriver = createSimulatorInstance();

        JacquardAndroidHelper androidHelper = new JacquardAndroidHelper(androidDriver,test);
        JacquardAppleHelper appleHelper = new JacquardAppleHelper(iosDriver,test);


        int initialBatteryLevel = androidHelper.logInitialBatteryLevel();
        System.out.println(initialBatteryLevel);

                //androidHelper.launchJaquard();
        androidHelper.printTheExistingScreen("Splash screen");

        androidHelper.clickOngetStated();
        androidHelper.printTheExistingScreen("Abilities screen");
        androidHelper.swipeUp();
        androidHelper.clickonEnableJacquard();
        androidHelper.clickOnTagIsReady();

androidHelper.logAndroidRelativeBattery(initialBatteryLevel);


        // appleHelper.BrushIn();
        androidHelper.clickOnAcceptAgrement();
        androidHelper.printTheExistingScreen("Activate screen");

        androidHelper.selectGoogleAccount();
        androidHelper.printTheExistingScreen("select Google account");

//        iosDriver = createSimulatorInstance();
//        JacquardAppleHelper appleHelper = new JacquardAppleHelper(iosDriver,test);

        androidHelper.clickOnILookFab();
androidHelper.printTheExistingScreen("Put on your jacket screen");
        androidHelper.allowBluetoothAccess();
        androidHelper.printTheExistingScreen("Tuck and Snap screen");

       if(androidHelper.selectAJacket()){

            androidHelper.clickTryOutNewThreads();
            androidHelper.printTheExistingScreen("Try out new threads");
            androidHelper.clickOnlearnInteractions();

            androidHelper.printTheExistingScreen("Learn interaction");
            Thread.sleep(3000);


            if (appleHelper.IsEmulatorConnected()) {


                androidHelper.clickOnTutorialBrushIn();

                Thread.sleep(3000);
                //appleHelper.BrushIn();
                appleHelper.BrushIn();
                appleHelper.BrushIn();
                androidHelper.assignBrushInGesture();

     Thread.sleep(2000);

                androidHelper.clickOnTutorialBrushOut();
               // appleHelper.BrushOut();
                appleHelper.BrushOut();
                appleHelper.BrushOut();
                androidHelper.assignBrushOutGesture();
Thread.sleep(3000);

        androidHelper.clickOnDoubleTapTutorial();
       appleHelper.performDoubleTapMultiple(2);
        androidHelper.assignDoubleTapGesture();

Thread.sleep(2000);

androidHelper.pressHome();

                boolean show = true;
                for (int j = 0; j < androidHelper.getGestureRunTime(); j++) {


                    if(j%5 == 0){

                        androidHelper.logAndroidRelativeBattery(initialBatteryLevel);

                        if(show) {

                            androidHelper.printTheExistingScreen("Interact");
                       show = false;

                        }else{
                            androidHelper.printTheExistingScreen("Reflect");
                        }


                    }

                    appleHelper.performBlushInMultiple(2);
                    //androidHelper.swipeLeft();
                    appleHelper.performBlushoutMultiple(2);
                    androidHelper.swipeRight();
                   // appleHelper.performBlushoutMultiple(2);
                    //androidHelper.swipeLeft();
                    appleHelper.performBlushInMultiple(2);
                    //androidHelper.swipeRight();
                    appleHelper.performDoubleTapMultiple(2);

               androidHelper.logAndroidRelativeBattery(initialBatteryLevel);
                }

            }else{


                System.out.println("Simulator is not connected ");
                test.fail("Simulator not created");
            }



       }


        androidHelper.logAndroidRelativeBattery(initialBatteryLevel);


    }







@AfterMethod
    public void closeInstances(){

        extentReports.flush();
        iosDriver.quit();
        androidDriver.quit();
    }










}
