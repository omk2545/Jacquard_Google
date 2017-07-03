
package rough;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Created by user on 6/30/17.
 */
public class CreateGraph {









    private CategoryDataset createDataset( ) {
//        final String fiat = "FIAT";
//        final String audi = "AUDI";
//        final String ford = "FORD";
//        final String speed = "Speed";
//        final String millage = "Millage";
//        final String userrating = "User Rating";
//        final String safety = "safety";

        final String BatteryLevel = "battery Level";

        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset( );

        dataset.addValue(84,BatteryLevel, "value");

        dataset.addValue(80,BatteryLevel, "value");

        dataset.addValue(56,BatteryLevel,"value");

//        dataset.addValue( 1.0 , fiat , speed );
//        dataset.addValue( 3.0 , fiat , userrating );
//        dataset.addValue( 5.0 , fiat , millage );
//        dataset.addValue( 5.0 , fiat , safety );
//
//        dataset.addValue( 5.0 , audi , speed );
//        dataset.addValue( 6.0 , audi , userrating );
//        dataset.addValue( 10.0 , audi , millage );
//        dataset.addValue( 4.0 , audi , safety );
//
//        dataset.addValue( 4.0 , ford , speed );
//        dataset.addValue( 2.0 , ford , userrating );
//        dataset.addValue( 3.0 , ford , millage );
//        dataset.addValue( 6.0 , ford , safety );

        return dataset;
    }

public void lineChart()  {


    DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();


    line_chart_dataset.addValue( 90 , "Battery Level" , "Onboarding" );
    line_chart_dataset.addValue( 83 , "Battery Level" , " 2" );
    line_chart_dataset.addValue( 80 , "Battery Level" , " 4" );
    line_chart_dataset.addValue( 78 , "Battery Level" , " 6" );
    line_chart_dataset.addValue( 74  , "Battery Level" , "gesture count 2" );
    line_chart_dataset.addValue( 70 , "Battery Level" , " level" );


    JFreeChart lineChartObject = ChartFactory.createLineChart3D(
            "Battery Difference: 6"," " ,
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
    File lineChart = new File( "LineChart.jpeg" );
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
       // get.pieChartReport();

        get.BarGraphType();
        get.lineChart();
    }
}
