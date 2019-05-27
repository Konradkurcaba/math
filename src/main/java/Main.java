import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    private static ChartData chartData = new ChartData();

    public static void main(String... args)
    {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbę szukanych węzłów: ");
        String buffer = scanner.nextLine();
        int numberOfInputNodes = Integer.valueOf(buffer);

        double inputNodes[] = new double[numberOfInputNodes];
        for(int i =0;i<numberOfInputNodes;i++)
        {
            System.out.println("Podaj szukany węzeł: ");
            buffer = scanner.nextLine();
            double p = Double.valueOf(buffer);
            inputNodes[i] = p;
        }

        System.out.println("Podaj liczbę znanych węzłów: ");
        buffer = scanner.nextLine();
        int numberOfNodes = Integer.valueOf(buffer);

        System.out.println("Podaj wzór funkcji: ");
        buffer = scanner.nextLine();
        String[] splited = buffer.split(" ");
        double[] parsed = new double[splited.length];
        for(int ii = 0;ii<splited.length;ii++)
        {
            parsed[ii] = Double.valueOf(splited[ii]);
        }

        System.out.println("Czy chcesz podać własne węzły ? [y/n]");
        buffer = scanner.nextLine();
        double[] userX = null;
        boolean userNodes = false;
        if(buffer.equals("y"))
        {
            userNodes = true;
            System.out.println("Wymien wezly:");
            userX = new double[numberOfNodes];
            for (int i =0;i<numberOfNodes;i++)
            {
                buffer = scanner.nextLine();
                userX[i] = Double.valueOf(buffer);
            }
        }
        double[] chbNodes = new double[numberOfNodes];
        double[] eqlNodes = new double[numberOfNodes];
        System.out.println("Podaj początek przedziału");
        buffer = scanner.nextLine();
        Double begin = Double.valueOf(buffer);

        System.out.println("Podaj koniec przedziału");
        buffer = scanner.nextLine();
        Double end = Double.valueOf(buffer);

        chbNodes = Lagrange.nodesChb(numberOfNodes,begin,end);
        eqlNodes = Lagrange.node(numberOfNodes,begin,end);

        double[] chbY = calculateY(chbNodes,parsed);
        double[] eqlY = calculateY(eqlNodes,parsed);
        double[] userY = null;

        if(userNodes)
        {
            userY = calculateY(userX,parsed);
        }

        double[] interpolationChb = new double[numberOfInputNodes];
        double[] interpolationEql = new double[numberOfInputNodes];
        double[] interpolationUsr = null;

        System.out.println("Interpolacja na podstawie węzłów Czebyszewa");

        for(int i = 0;i<numberOfInputNodes;i++) {

            interpolationChb[i] = Lagrange.interpolation(chbNodes, chbY,inputNodes[i]);
            System.out.print("Interpolacja dla węzła: " + inputNodes[i] + ": ");
            System.out.println(Lagrange.interpolation(chbNodes, chbY ,inputNodes[i]));
            System.out.println("Błąd bezwzględny: ");
            System.out.println(Lagrange.lapse(Lagrange.interpolation(chbNodes,chbY,inputNodes[i])
                ,Lagrange.valueOfY(parsed,inputNodes[i])));
        }

        System.out.println("Interpolacja na podstawie węzłów równoodległych");
        for(int i = 0;i<numberOfInputNodes;i++) {

            interpolationEql[i] = Lagrange.interpolation(eqlNodes, eqlY,inputNodes[i]);
            System.out.print("Interpolacja dla węzła: " + inputNodes[i] + ": ");
            System.out.println(Lagrange.interpolation(eqlNodes, eqlY ,inputNodes[i]));
            System.out.println("Błąd bezwzględny: ");
            System.out.println(Lagrange.lapse(Lagrange.interpolation(eqlNodes,eqlY,inputNodes[i])
                    ,Lagrange.valueOfY(parsed,inputNodes[i])));
        }

        if(userNodes)
        {
            interpolationUsr = new double[numberOfNodes];
            System.out.println("Interpolacja na podstawie węzłów użytkownika");
            for(int i = 0;i<numberOfInputNodes;i++) {

                interpolationUsr[i] = Lagrange.interpolation(userX, userY,inputNodes[i]);
                System.out.print("Interpolacja dla węzła: " + inputNodes[i] + ": ");
                System.out.println(Lagrange.interpolation(userX, userY ,inputNodes[i]));
                System.out.println("Błąd bezwzględny: ");
                System.out.println(Lagrange.lapse(Lagrange.interpolation(userX,userY,inputNodes[i])
                        ,Lagrange.valueOfY(parsed,inputNodes[i])));
            }


        }

        chartData.setChbX(chbNodes);
        chartData.setChbY(chbY);
        chartData.setEqlX(eqlNodes);
        chartData.setEqlY(eqlY);
        chartData.setUserX(userX);
        chartData.setUserY(userY);
        chartData.setInputNodes(inputNodes);
        chartData.setInterYEql(interpolationEql);
        chartData.setInterYChb(interpolationChb);
        chartData.setInterYUsr(interpolationUsr);

        System.out.println("Największy błąd w przedziale - równoodległe " + inputNodes[0] + " - "
                + inputNodes[inputNodes.length-1] + " :" );

        double node = inputNodes[0];
        double maxLapse = 0;
        while(node < inputNodes[inputNodes.length-1])
        {
            if(maxLapse <
            Lagrange.lapse(Lagrange.interpolation(eqlNodes,eqlY,node)
                    ,Lagrange.valueOfY(parsed,node)))
            {
                maxLapse =  Lagrange.lapse(Lagrange.interpolation(eqlNodes,eqlY,node)
                        ,Lagrange.valueOfY(parsed,node));
            }
            node +=0.1;
        }
        System.out.println(maxLapse);

        System.out.println("Największy błąd w przedziale - Czebyszewa " + inputNodes[0] + " - "
                + inputNodes[inputNodes.length-1] + " :" );

        node = inputNodes[0];
        maxLapse = 0;
        while(node < inputNodes[inputNodes.length-1])
        {
            if(maxLapse <
                    Lagrange.lapse(Lagrange.interpolation(chbNodes,chbY,node)
                            ,Lagrange.valueOfY(parsed,node)))
            {
                maxLapse =  Lagrange.lapse(Lagrange.interpolation(chbNodes,chbY,node)
                        ,Lagrange.valueOfY(parsed,node));
            }
            node +=0.1;
        }
        System.out.println(maxLapse);
        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("chart.fxml"));
        Parent root = loader.load();
        ChartController controller = loader.getController();
        controller.putData(chartData);
        primaryStage.setTitle("Interpolacja");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    private static double[] calculateY(double[] x,double[] polynomial)
    {
        double[] calculatedY = new double[x.length];
        for(int i = 0;i<x.length;i++)
        {
            calculatedY[i] = Lagrange.valueOfY(polynomial,x[i]);
        }
        return calculatedY;
    }


}
