import java.util.Arrays;
import java.util.Scanner;

public class main {


    public static void main(String... args)
    {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj szukany węzeł: ");

        String buffer = scanner.nextLine();
        double p = Double.valueOf(buffer);

        System.out.println("Podaj liczbę węzłów: ");
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


        System.out.println("Rozkład węzłów: 1 - równoodległe, 2 - różne odległości, 3 - Czebyszewa");
        buffer = scanner.nextLine();
        int mode = Integer.valueOf(buffer);
        double[] calculatedX = new double[numberOfNodes];
        switch (mode)
        {
            case 1:
                System.out.println("Podaj początek przedziału");
                buffer = scanner.nextLine();
                Double begin = Double.valueOf(buffer);

                System.out.println("Podaj koniec przedziału");
                buffer = scanner.nextLine();
                Double end = Double.valueOf(buffer);

                calculatedX = Lagrange.node(numberOfNodes,begin,end);
                break;

            case 2:
                System.out.println("Wymien wezly:");

                for (int i =0;i<numberOfNodes;i++)
                {
                    buffer = scanner.nextLine();
                    calculatedX[i] = Double.valueOf(buffer);
                }
                break;

            case 3:
                System.out.println("Podaj początek przedziału");
                buffer = scanner.nextLine();
                begin = Double.valueOf(buffer);

                System.out.println("Podaj koniec przedziału");5
                buffer = scanner.nextLine();
                end = Double.valueOf(buffer);

                calculatedX = Lagrange.nodesChb(numberOfNodes,begin,end);
                break;
        }

        double[] calculatedY = new double[calculatedX.length];
        for(int i = 0;i<calculatedX.length;i++)
        {
            calculatedY[i] = Lagrange.valueOfY(parsed,calculatedX[i]);
        }

        System.out.println("Interpolacja dla węzła: " + p + ":" );
        System.out.println(Lagrange.interpolation(calculatedX,calculatedY,p) );

        System.out.println("Błąd bezwzględny: ");
        System.out.println(Lagrange.lapse(Lagrange.interpolation(calculatedX,calculatedY,p)
                ,Lagrange.valueOfY(parsed,p)));




//
//        double[] x = {2.0,3.0,4.0};
//        double[] y = {-1.119,-0.9792,-0.7662};
//        double expectedX = 5;
//
//        double interpolatedY = Lagrange.interpolation(x,y,expectedX);
//
//        System.out.println(interpolatedY);
//
//        double results[] = Lagrange.nodesChb(4,-1,1);
//
//        System.out.println(Arrays.toString(results));
//
//
//        double[] ddd = {5.0,1.0,2.0,3.0};
//        double[] eeee = {3.13,2.0,1.0,5.0};
//        System.out.println(Lagrange.valueOfY(ddd,6));
//
//        System.out.println("Error:");
//        System.out.println(Lagrange.lapse(Lagrange.valueOfY(ddd,5),interpolatedY));
//




    }


}
