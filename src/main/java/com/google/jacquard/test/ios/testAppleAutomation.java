package com.google.jacquard.test.ios;

import com.aventstack.extentreports.ExtentTest;
import com.google.jacquard.ios.JacquardAppleHelper;
import com.google.jacquard.test.base.TestBase;
import com.google.jacquard.utils.CreateGraph;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by user on 6/16/17.
 */
public class testAppleAutomation extends TestBase{
    public IOSDriver iphone;
    public IOSDriver simulator;
    ExtentTest test;


    @BeforeMethod
    public void init(){

        startAppiumServers();

        test = createTest("Apple Test");


    }
  public  Map<String, Integer> sortedMap;
    @Test
    public void testOnboardingApple() throws MalformedURLException, InterruptedException {

      //  simulator = createSimulatorInstance();

         sortedMap = new TreeMap<String, Integer>();

        iphone = createDeviceInstance();
        simulator=createSimulatorInstance();
        JacquardAppleHelper appleHelper = new JacquardAppleHelper(iphone, test);
        JacquardAppleHelper sim = new JacquardAppleHelper(simulator,test);

        sortedMap = appleHelper.logInitalBatteryLevel(sortedMap);
        System.out.println(sortedMap);

int initialBatteryLevel = appleHelper.logInitalBatteryLevel();

        if (!appleHelper.isUserOnBoarded()) {

            appleHelper.gettingStarted();
            appleHelper.logUserScreen("Enable Jacquard Screen");
            appleHelper.enableJacquard();
            appleHelper.logUserScreen("Tag is Ready");
            appleHelper.clickOnTagIsReady();
            appleHelper.acceptUserAgreeMent();
            appleHelper.selectAccount();
            appleHelper.logUserScreen("Select a google account ");
//            appleHelper.sleep(5000);
            appleHelper.clickOnIlookFab();
        //    appleHelper.sleep(5000);

            appleHelper.clickOnSearchAgain();
appleHelper.logDetails("Finding the Jacket(Emulator)");
            //appleHelper.sleep(5000);


            appleHelper.selectJacket();

           // appleHelper.sleep(5000);

            if(sim.IsEmulatorConnected()) {
                appleHelper.logDetails("Jacket is connected to the app");
                appleHelper.resetApp();
            }
        } else{

            appleHelper.logDetails("User is already on-boarded ");
            System.out.println("User is onboarded ");

        }


//        if(!sim.IsEmulatorConnected()) {
//
//            appleHelper.addinngNewJacket();
//            appleHelper.sleep(10000);
//            if(sim.IsEmulatorConnected()) {
//                appleHelper.resetApp();
//            }
//
//        }


        appleHelper.addinngNewJacket();
            appleHelper.sleep(10000);
            if(sim.IsEmulatorConnected()) {
                appleHelper.resetApp();
            }
        appleHelper.sleep(9000);


        appleHelper.clickOnBrushIn();

        sim.BrushIn();
        sim.BrushIn();

appleHelper.assignBrushInGesture();

//sim.performBlushInMultiple(4);

appleHelper.clickOnBrushOut();
sim.BrushOut();
sim.BrushOut();
appleHelper.assignBrushOutGesture();



appleHelper.clickOnDoubleTap();
sim.performDoubleTapMultiple(2);

appleHelper.assignDoubleTapGesture();
//appleHelper.driver.closeApp();

sortedMap = appleHelper.iosLogRelativeBatteryLevels(sortedMap,initialBatteryLevel);

for (int i  = 0;i<50;i++)

{
    System.out.println("Loop "+i);
    sim.performALlGestures(5);//5*3  // 15*count
appleHelper.swipeLeft();
appleHelper.swipeRight();
    //appleHelper.iosLogRelativeBatteryLevels(initialBatteryLevel);
    sortedMap = appleHelper.iosLogRelativeBatteryLevels(sortedMap,initialBatteryLevel);

}

        for(Map.Entry m:sortedMap.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
        System.out.println(sortedMap);

//        CreateGraph createGraph = new CreateGraph();
//        createGraph.lineChart(sortedMap);

    }



    @Test
    public void testRough() throws MalformedURLException, InterruptedException {

        iphone = createDeviceInstance();
      //  simulator=createSimulatorInstance();
        JacquardAppleHelper appleHelper = new JacquardAppleHelper(iphone, test);
        //JacquardAppleHelper sim = new JacquardAppleHelper(simulator,test);

appleHelper.openMenu();
appleHelper.swipeLeft();

appleHelper.sleep(3000);

//appleHelper.swipeUp();
  //      System.out.println(appleHelper.isUserOnBoarded());

        //
            appleHelper.exitCommuterScreen();
//            appleHelper.exitCommuterScreen();
//            appleHelper.exitCommuterScreen();



//sim.BrushIn();







    }




    @AfterMethod
    public void tearDown(){

        CreateGraph createGraph = new CreateGraph();
        createGraph.lineChart(sortedMap);

     extentReports.flush();

        if (iphone != null) {
            iphone.quit();
        }
        if (simulator != null) {
            simulator.quit();
        }

        killAllAppiumInstances();
    }


}
