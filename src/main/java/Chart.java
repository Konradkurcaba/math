import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Chart {

    double[] xData;
    double[] yData;

    double[] interpolationX;
    double[] interpolationY;

    public Chart(double[] aXData,double[] aYData,double[] interpolationX, double[] interpolationY) {
        xData = aXData;
        yData = aYData;

        this.interpolationX = interpolationX;
        this.interpolationY = interpolationY;
    }

    public void start(Stage mainStage) throws Exception {
        NumberAxis xAxis = new NumberAxis(-25,25,5);
        NumberAxis yAxis = new NumberAxis(-25,25,5);


        xAxis.setLabel("");
        yAxis.setLabel("");

        LineChart<Number,Number> chart = new LineChart<Number, Number>(yAxis,xAxis);
        chart.setTitle("Interpolacja");
        XYChart.Series<Number,Number> series = new XYChart.Series();
        series.setName("Węzły obliczone");
        int numberOfNodes = xData.length;
        for(int i = 0;i<numberOfNodes;i++)
        {
            series.getData().add(new XYChart.Data<>(xData[i],yData[i]));
        }

        XYChart.Series<Number,Number> interpolationSeries = new XYChart.Series();
        interpolationSeries.setName("Węzły interpolowane");
        numberOfNodes = interpolationX.length;
        for(int i = 0;i<numberOfNodes;i++)
        {
            interpolationSeries.getData().add(new XYChart.Data<>(interpolationX[i],interpolationY[i]));
        }


        Scene scene  = new Scene(chart,1000,700);
        chart.getData().add(series);
        chart.getData().add(interpolationSeries);


        for (XYChart.Data<Number, Number> data : series.getData()) {
            // this node is StackPane
            StackPane stackPane = (StackPane) data.getNode();
            stackPane.setVisible(false);
        }
        mainStage.setScene(scene);
        mainStage.show();

    }

}
