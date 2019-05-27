import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Chart {

    private ChartData data;

    public Chart(ChartData aChartData) {
        data = aChartData;
    }

//    public void start(Stage mainStage) throws Exception {
//        NumberAxis xAxis = new NumberAxis(-25,25,5);
//        NumberAxis yAxis = new NumberAxis(-25,25,5);
//
//        xAxis.setLabel("");
//        yAxis.setLabel("");
//
//        LineChart<Number,Number> chart = new LineChart<Number, Number>(yAxis,xAxis);
//        chart.setTitle("Interpolacja");
//
//        chart.getData().add(get)
//
//        if(data.getChbX() != null) {
//            XYChart.Series<Number, Number> chbSeries = new XYChart.Series();
//            chbSeries.setName("Węzły interpolowane");
//            numberOfNodes = data.getChbX().length
//            for (int i = 0; i < numberOfNodes; i++) {
//                chbSeries.getData().add(new XYChart.Data<>(data.getChbX()[i], data.getChbY()[i]));
//            }
//            chart.getData().add(chbSeries);
//        }
//
//        Scene scene  = new Scene(chart,1000,700);
//        chart.getData().add(series);
//
//
//        for (XYChart.Data<Number, Number> data : series.getData()) {
//            // this node is StackPane
//            StackPane stackPane = (StackPane) data.getNode();
//            stackPane.setVisible(false);
//        }
//        mainStage.setScene(scene);
//        mainStage.show();
//
//    }

}
