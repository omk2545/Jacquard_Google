package com.google.jacquard.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandExec {

	public  String getIphoneBatteryLevel(String Udid ) {

		List<String> values = new ArrayList<String>();

		Runtime runtime = Runtime.getRuntime();
		try {

			//String[] cm = { "/bin/bash", "-c", "echo omkar" };


            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c",
                    "/usr/local/Cellar/libimobiledevice/HEAD-53fede0/bin/ideviceinfo -u "+Udid+" -q com.apple.mobile.battery -k BatteryCurrentCapacity");
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
		if (values.size() != 0) {
			return values.get(0);
		} else {
			return "Not able to fetch the battery information";
		}

		
		
	}

	public String getBatteryDetails() throws IOException {


        List<String> values = new ArrayList<String>();

        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c",
                "-l","adb shell dumpsys battery| grep level");

        Process process= pb.start();


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            if (line != null) {
                values.add(line);
            }

        }


        String batteryLevel = values.get(0);

     String val[] =   batteryLevel.split(":");

        System.out.println("Values"+val[0]);
        System.out.println(val[1]);
        String exactVal = val[1];
        exactVal = exactVal.replace(" ","");

        System.out.println(val[1]);
        ;

        System.out.println("ExactVal"+exactVal);
        return exactVal;

    }

    public void cleaAllData() throws IOException {

        List<String> values = new ArrayList<String>();

        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c",
                "-l","adb shell dumpsys battery| grep level");

        Process process= pb.start();


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            if (line != null) {
                values.add(line);
            }

        }


        String batteryLevel = values.get(0);

        String val[] =   batteryLevel.split(":");

        System.out.println("Values"+val[0]);

        String exactVal = val[0];
        exactVal = exactVal.replace(" ","");

        System.out.println(val[1]);



    }



	public static void main(String[] args) throws InterruptedException, IOException {

//		Runtime runtime = Runtime.getRuntime();
//		try {
//
//			String[] cm = { "/bin/bash", "-c", "echo mithul" };
//
//			// String [] cm ={"/bin/bash","-c" ,"ideviceinfo -u
//			// 4b6bf00abd0a863ce873675dda8612fe0f8874c0 -q
//			// com.apple.mobile.battery"};
//
//			// String [] cm
//			// ={"/usr/local/Cellar/libimobiledevice/HEAD-53fede0/bin/ideviceinfo",
//			// " -q com.apple.mobile.battery"};
//
//			ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c",
//					"/usr/local/Cellar/libimobiledevice/HEAD-53fede0/bin/ideviceinfo -u 4b6bf00abd0a863ce873675dda8612fe0f8874c0 -q com.apple.mobile.battery -k BatteryCurrentCapacity");
//
//			Process process = pb.start();
//
//			List<String> values = new ArrayList<String>();
//
//			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//			String line;
//			while ((line = bufferedReader.readLine()) != null) {
//				System.out.println(line);
//				if (line != null) {
//					values.add(line);
//				}
//
//			}
//
//			BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//
//			String line1;
//			while ((line1 = bufferedReader2.readLine()) != null) {
//
//				System.out.println(line1);
//
//			}
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//System.out.println(getBatteryLevel());

//			List<String> values = new ArrayList<String>();
//
//		ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c",
//					"-l","adb shell dumpsys battery| grep level");
//
//		Process process= pb.start();
//
//
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//			String line;
//			while ((line = bufferedReader.readLine()) != null) {
//				System.out.println(line);
//				if (line != null) {
//					values.add(line);
//				}
//
//			}
//
//
//		System.out.println(values);
//

CommandExec commandExec = new CommandExec();
        System.out.println(commandExec.getBatteryDetails());









	}







}
