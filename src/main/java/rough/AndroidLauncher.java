package rough;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.joda.time.DateTime;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AndroidLauncher {

static     AndroidDriver driver;
    public static void main(String[] args) throws MalformedURLException {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("deviceName","ZY223RD67W");
//        capabilities.setCapability("platformName","Android");

//        capabilities.setCapability("platformVersion","7.0");
//        capabilities.setCapability("appPackage","com.google.atap.jacquard");
//        capabilities.setCapability("appActivity","com.google.atap.jacquard.application.activities.EntrypointActivity");
//        driver = new AndroidDriver(new URL("http://127.0.0.1:4678/wd/hub"),capabilities);
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


        DateTime time = new DateTime();
        int hr =  time.getHourOfDay();
        int min = time.getMinuteOfHour();
        System.out.println(hr + ":"+min);

final AndroidLauncher androidLauncher = new AndroidLauncher();



        new Thread(new Runnable() {
            public void run() {
                androidLauncher.startProcess("4747");
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                androidLauncher.startProcess("4748");
            }
        }).start();




//androidLauncher.createInstances("4266");
//
//
//
//androidLauncher.createInstances("4277");
//
//androidLauncher.stopServerProcess();
//androidLauncher.closeInstances();
    }

    public void createInstances(String port){


        CommandLine command = new CommandLine("/usr/local/bin/node");
        command.addArgument("/usr/local/bin/appium",false);
        // CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
        //command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium",false);
        command.addArgument("--address", false);
        command.addArgument("127.0.0.1");
        command.addArgument("--port", false);
        command.addArgument(port);
       // command.addArgument("-bp", false);
        //command.addArgument("5008");
        //command.addArgument("--full-reset", true);
        command.addArgument("--session-override", true);
        command.addArgument("--no-reset", true);
//        command.addArgument(">",true);
//        command.addArgument("iphone.log");
        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        try {executor.execute(command, resultHandler);
            Thread.sleep(5000);System.out.println("Appium server started first");}
        catch (IOException e)
        {
            e.printStackTrace();

        }
        catch (InterruptedException e)
        {

        }







    }


    private void startProcess(String s) {


        List<String> values = new ArrayList<String>();
        try {

            //String[] cm = { "/bin/bash", "-c", "echo omkar" };

//            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c",
//                    "/usr/local/Cellar/libimobiledevice/HEAD-53fede0/bin/ideviceinfo -u "+Udid+" -q com.apple.mobile.battery -k BatteryCurrentCapacity");
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c",  "/usr/local/bin/appium -a 0.0.0.0 -p "+s+" -U b9bad8c8321ce8e1e28ea5b99d14ca167b5672d4 ");
            //   ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c",  "killall node");
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

    public void stopServerProcess(){



        Runtime runtime = Runtime.getRuntime();
        List<String> values = new ArrayList<String>();
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


        System.out.println();


    }

    public void killAllNodes(){

        CommandLine command = new CommandLine("/usr/bin/");
        command.addArgument("killall node");

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        try {executor.execute(command, resultHandler);
           // executor.execute(command1, resultHandler);
           // executor.execute(command2, resultHandler);
            Thread.sleep(5000);System.out.println("Appium server closeing..");}
        catch (IOException e)
        {//e.printStackTrace();

        }
        catch (InterruptedException e)
        {//e.printStackTrace();

        }




    }






    public void closeInstances(){

        CommandLine command = new CommandLine("/usr/bin/");
        CommandLine command1 = new CommandLine("/usr/bin/");
        CommandLine command2 = new CommandLine("/usr/bin/");
        CommandLine command3 = new CommandLine("/usr/bin/");
        command.addArgument("killall node",false);
        command1.addArgument("ps -A|grep -i\"iproxy\"|grep -v grep|awk '{print \"kill -9 \" $1}'|sh",false);
        command2.addArgument("ps -A|grep -i\"appium\"|grep -v grep|awk '{print \"kill -9 \" $1}'|sh",false);
        command3.addArgument("ps -ax|grep -i \"node\"|grep -v grep| awk '{print \"kill -9 \" $1}'|sh",false);

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        try {executor.execute(command, resultHandler);
            executor.execute(command1, resultHandler);
            executor.execute(command2, resultHandler);
            Thread.sleep(5000);System.out.println("Appium server closeing..");}
        catch (IOException e)
        {//e.printStackTrace();

        }
        catch (InterruptedException e)
        {//e.printStackTrace();

        }







    }










}
