import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;


public class ChartController {

    public static String FXML = "chart.fxml";

    @FXML
    private LineChart<Number,Number> chart;

    @FXML
    private TextField xMin;

    @FXML
    private TextField xMax;

    @FXML
    private TextField yMin;

    @FXML
    private TextField yMax;

    @FXML
    private Button okButton;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;
    @FXML
    private Button ChbN;
    @FXML
    private Button ChbI;
    @FXML
    private Button EqlN;
    @FXML
    private Button EqlI;
    @FXML
    private Button UsrN;
    @FXML
    private Button UsrI;


    private ChartData data;

    private boolean showChb = true;
    private boolean showEql = true;
    private boolean showInterChb = true;
    private boolean showInterEql = true;
    private boolean showUsrI = true;
    private boolean showUsrN = true;

    @FXML
    private void initialize()
    {


        ChbI.setOnAction( e -> {
            showInterChb = !showInterChb;
            initChart();
        });
        EqlI.setOnAction( e -> {showInterEql = !showInterEql;
            initChart();
        });
        ChbN.setOnAction( e -> {showChb = !showChb;
            initChart();
        });

        EqlN.setOnAction( e -> {
            showEql = !showEql;
            initChart();
        });

//        UsrI.setOnAction( e-> {
//            showUsrI = !showUsrI;
//            initChart();
//        });
//
//        UsrN.setOnAction( e -> {
//            showUsrN = !showUsrN;
//            initChart();
//        });




        okButton.setOnAction(event ->{

            double xMin = Double.valueOf(this.xMin.getText());
            double xMax = Double.valueOf(this.xMax.getText());
            double yMin = Double.valueOf(this.yMin.getText());
            double yMax = Double.valueOf(this.yMax.getText());

            xAxis.setAutoRanging(false);
            yAxis.setAutoRanging(false);

            xAxis.setLowerBound(xMin);
            xAxis.setUpperBound(xMax);
            yAxis.setLowerBound(yMin);
            yAxis.setUpperBound(yMax);

        });
    }

    public void putData(ChartData aData)
    {
       data = aData;
       initChart();
     //   initData();
    }

    private void initChart()
    {
        chart.getData().clear();
        chart.setTitle("Interpolacja");


        if(data.getChbX() != null && showChb)
        chart.getData().add(getSeries(data.getChbX(),data.getChbY(),"Czebyszewa"));
        if(data.getEqlX() != null && showEql)
        chart.getData().add(getSeries(data.getEqlX(),data.getEqlY(),"Równoodległe"));
        if(data.getInterYChb() != null && showInterChb)
        chart.getData().add(getSeries(data.getInputNodes(),data.getInterYChb(),"Interpolowane chb"));
        if(data.getInterYEql() != null && showInterEql)
        {
            chart.getData().add(getSeries(data.getInputNodes(),data.getInterYChb()
                    ,"interpolowane równoodlegle"));
        }
        if(data.getUserX() != null && showUsrN )
        {
            chart.getData().add(getSeries(data.getInputNodes(),data.getInterYChb()
                    ,"Użytkownika"));
        }
     //   if(data. != null && showUsrI)

//        for (XYChart.Data<Number, Number> data : series.getData()) {
//            // this node is StackPane
//            StackPane stackPane = (StackPane) data.getNode();
//            stackPane.setVisible(false);
//        }
    }

    public void initData()
    {
        double myX[] = {-1, 0, 1};
        double myY[] = {1, 0, 1};

        double myX2[] = {-1, -0.8, -0.6, -0.4, -0.2, 0, 0.2, 0.4, 0.6, 0.8, 1};
        double myY2[] = {1, 0.8, 0.6, 0.4, 0.2, 0, 0.2, 0.4, 0.6, 0.8, 1};


        double[] interpol1 = new double[200];

        double counter = -1;
        double[] x = new double[200];
        for(int i = 0;i<200;i++)
        {
            x[i] = counter;
            interpol1[i] = Lagrange.interpolation(myX,myY,counter);
            counter += 0.01;
        }

        counter = -1;
        double[] interpol2 = new double[200];
        for(int i = 0;i<200;i++)
        {
            interpol2[i] = Lagrange.interpolation(myX2,myY2,counter);
            counter += 0.01;
        }

        XYChart.Series<Number, Number> series1 = getSeries(x,interpol1,"Interpolowane - 3");
        XYChart.Series<Number, Number> series2 = getSeries(x,interpol2,"Interpolowane - 11");

        chart.getData().add(series1);
        chart.getData().add(series2);


        for (XYChart.Data<Number, Number> data : series1.getData()) {
            // this node is StackPane
            StackPane stackPane = (StackPane) data.getNode();
            stackPane.setVisible(false);
        }

        for (XYChart.Data<Number, Number> data : series2.getData()) {
            // this node is StackPane
            StackPane stackPane = (StackPane) data.getNode();
            stackPane.setVisible(false);
        }


    }



    private XYChart.Series<Number, Number> getSeries(double[] x,double[] y,String aName)
    {
        XYChart.Series<Number, Number> series = new XYChart.Series();
        series.setName(aName);
        int numberOfNodes = x.length;
        for (int i = 0; i < numberOfNodes; i++) {
            series.getData().add(new XYChart.Data<>(x[i], y[i]));

        }
        return series;
    }

}
