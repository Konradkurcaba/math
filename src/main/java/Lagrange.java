import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

public class Lagrange {


    public static double interpolation(double[] xs, double[] ys, double x) {
        double t;
        double y = 0.0;

        for (int k = 0; k < xs.length; k++) {
            t = 1.0;
            for (int j = 0; j < xs.length; j++) {
                if (j != k) {
                    t = t * ((x - xs[j]) / (xs[k] - xs[j]));
                }
            }
            y += t * ys[k];
        }
        return y;
    }

    //węzły rónoodległe - wyliczanie X
    public static double[] node(int numberOfNodes, double begin, double end) {
        double[] resultX = new double[numberOfNodes];
        resultX[0] = begin;

        double distance = (end - begin) / numberOfNodes;
        for (int i = 1; i < numberOfNodes - 1; i++) {
            resultX[i] = resultX[i - 1] + distance;
        }
        resultX[numberOfNodes - 1] = end;

        return resultX;
    }

    // chebyszew
    public static double[] nodesChb(int numberOfNodes, double begin, double end) {
        double[] resultsX = new double[numberOfNodes];
        for (int i = 1; i <= numberOfNodes; i++) {
            resultsX[i-1] = 0.5 * (begin + end) + 0.5 * (end - begin) *
                    Math.cos(((2.0 * i - 1) / (2.0 * numberOfNodes)) * Math.PI);
        }
        return  resultsX;

    }

    // wartosci podajemy od konca np 2x+1 = {1.0,2.0}
    public static double valueOfY(double[] coefficients,double X)
    {
        PolynomialFunction function = new PolynomialFunction(coefficients);
        return function.value(X);
    }

    public static double lapse(double aCalculatedResult,double interpolatedResult)
    {
        double result = aCalculatedResult - interpolatedResult;
        if(result < 0) result *= -1;
        return result;
    }


}
