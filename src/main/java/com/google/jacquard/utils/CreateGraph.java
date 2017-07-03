
package com.google.jacquard.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.joda.time.DateTime;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 6/30/17.
 */
public class CreateGraph {


public void lineChart(Map map )  {

    DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
    System.out.println(map);


    Set<Map.Entry<Integer, String>> mapValues = map.entrySet();



   // final Set<Entry<Integer, String>> mapValues = orderMap.entrySet();
    final int maplength = mapValues.size();
    final Map.Entry<String,String>[] test = new Map.Entry[maplength];
    mapValues.toArray(test);

    //System.out.print("First Key:"+);

String firstKey =test[0].getKey();
    System.out.println(" First Value:"+map.get(firstKey));

    int initialBattery = (int) map.get(firstKey);



  //  int initialValue = Integer.valueOf(test[0].getValue());
    //int finalValue = Integer.value(test[maplength-1].getValue());


//    System.out.println("initial "+initialValue+" Final value "+finalValue);

    System.out.print("Last Key:"+test[maplength-1].getKey());
   String lastKey = test[maplength-1].getKey();

    System.out.println(map.get(lastKey));

    int FinalBattery  = (int) map.get(lastKey);


    int difference = initialBattery-FinalBattery;
    System.out.println("difference is: " +difference);
    // System.out.println(" Last Value:"+test[maplength-1].getValue());

    Iterator entries = map.entrySet().iterator();
    while (entries.hasNext()) {
        Map.Entry entry = (Map.Entry) entries.next();
      String  key = (String) entry.getKey();

        //int key = (Integer) entry.getKey();
        int value = (Integer) entry.getValue();
        System.out.println("Key = " + key + ", Value = " + value);


        line_chart_dataset.addValue( value , "Battery Level" , key );


    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    System.out.println(simpleDateFormat.format(cal.getTime()));

    String timeValue = simpleDateFormat.format(cal.getTime());

 String chartName =   timeValue.replace(" ","_").replace(":","_");
chartName = chartName+".jpg";
    System.out.println("Time value "+chartName);


//    line_chart_dataset.addValue( 83 , "Battery Level" , " 2" );
//    line_chart_dataset.addValue( 80 , "Battery Level" , " 4" );
//    line_chart_dataset.addValue( 78 , "Battery Level" , " 6" );
//    line_chart_dataset.addValue( 74  , "Battery Level" , "gesture count 2" );
//    line_chart_dataset.addValue( 70 , "Battery Level" , " level" );
//

    JFreeChart lineChartObject = ChartFactory.createLineChart3D(
            "Battery Difference: "+difference," " ,
            " Battery level ",
            line_chart_dataset,PlotOrientation.VERTICAL,
            true,true,false);

    CategoryPlot plot = lineChartObject.getCategoryPlot();

    LineAndShapeRenderer renderer = new LineAndShapeRenderer();
plot.setRenderer(renderer);
    renderer.setSeriesPaint(0, Color.BLUE);

    renderer.setSeriesStroke(0, new BasicStroke(4.0f));

    int width = 640;    /* Width of the image */
    int height = 480;   /* Height of the image */

    ///reports/
    System.out.println(Constants.userDir+"/charts/");

    String repoPath = Constants.userDir +"/charts/";
    File lineChart = new File( repoPath+chartName);
    try {
        ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
    } catch (IOException e) {
        e.printStackTrace();
    }



}





    public void BarGraphType(){

        final String fiat = "FIAT";
//        final String audi = "AUDI";
//        final String ford = "FORD";
        final String speed = "Speed";
//        final String millage = "Millage";
//        final String userrating = "User Rating";
//        final String safety = "safety";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        dataset.addValue( 84 , "1" , speed );
//        dataset.addValue( 3.0 , fiat , userrating );
//        dataset.addValue( 5.0 , fiat , millage );
//        dataset.addValue( 5.0 , fiat , safety );

        dataset.addValue( 83 , "2" , speed );
        dataset.addValue( 82 , "3" , speed );
        dataset.addValue( 78 , "4" , speed );
        dataset.addValue( 76 , "5" , speed );
//        dataset.addValue( 6.0 , audi , userrating );
//        dataset.addValue( 10.0 , audi , millage );
//        dataset.addValue( 4.0 , audi , safety );

        dataset.addValue( 82 , fiat , speed );
//        dataset.addValue( 2.0 , ford , userrating );
//        dataset.addValue( 3.0 , ford , millage );
//        dataset.addValue( 6.0 , ford , safety );

        JFreeChart barChart = ChartFactory.createBarChart(
                "Battery Levels",
                "Battery", "Level",
                dataset, PlotOrientation.VERTICAL,
                true, true, false);

        int width = 640;    /* Width of the image */
        int height = 480;   /* Height of the image */
        File BarChart = new File( "BarChart.jpeg" );
        try {
            ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }












    public void pieChartReport()
    {


        // Creating a simple pie chart with
        int initial = 84;
        int difference  = 50 ;

        double  val  =  difference * (initial/10);


        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("Total Battery: "+initial, new Integer(100));
     //   pieDataset.setValue("AfterExecution", new Integer(80));
        pieDataset.setValue("UsedBattery percentage: "+difference, new Integer((int) val));







        JFreeChart piechart = ChartFactory.createPieChart("Test Case Execution Status", pieDataset, true, true, false);

//JFreeChart some = ChartFactory.createHistogram("TestOmkar","Battery",)
        try {
           // ChartUtilities.saveChartAsJPEG(new File("D:\\simplePiechart.jpg"), piechart, 400, 400);
            ChartUtilities.saveChartAsJPEG(new File("test.jpg"), piechart, 400, 400);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CreateGraph get = new CreateGraph();
//       get.pieChartReport();


        Map<Integer, String> sortedMap = new TreeMap<Integer, String>();



        //get.BarGraphType();
        Map<String, Integer> map= new TreeMap<>();
        map.put("10:28:10",90);
        map.put("10:29:10",88);
        map.put("10:38:10",86);

       // NavigableMap  <Integer,String> testMap = testMap.descendingMap();

        for(Map.Entry m:map.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");


        Calendar cal = Calendar.getInstance();
        System.out.println(simpleDateFormat.format(cal.getTime()));

        DateTime time = new DateTime();

        time.toDateTime().toString();

        System.out.println(time);

          get.lineChart(map);
    }
}
