package analysis.lab1;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.List;

public class FibonacciGraph {
    public static void main(String[] args) {
        int[] recursive_inputs = {5, 7, 10, 12, 15, 17, 20, 22, 25, 27, 30, 32, 35, 37, 40, 42, 45};

        // Convert inputs to List<Number>
        List<Number> rec_xData = new ArrayList<>();
        for (int n : recursive_inputs) {
            rec_xData.add(n);
        }

        int[] inputs = {500, 501, 631, 794, 1000, 1259, 1585, 1995, 2512, 3162, 3981, 5012, 6310, 7943, 10000, 12589, 15849};

        // Convert inputs to List<Number>
        List<Number> xData = new ArrayList<>();
        for (int n : inputs) {
            xData.add(n);
        }

        List<Double> recursiveTimes = new ArrayList<>();
        List<Double> dpTimes = new ArrayList<>();
        List<Double> matrixTimes = new ArrayList<>();
        List<Double> binetTimes = new ArrayList<>();
        List<Double> iterativeTimes = new ArrayList<>();
        List<Double> memoizationTimes = new ArrayList<>();

        for (int n : recursive_inputs) {
            long startTime, endTime;

            startTime = System.nanoTime();
            FibonacciMethods.fibonacciRecursive(n);
            endTime = System.nanoTime();
            recursiveTimes.add((endTime - startTime) / 1e6);
        }

        for (int n : inputs) {
            long startTime, endTime;

            startTime = System.nanoTime();
            FibonacciMethods.fibonacciDP(n);
            endTime = System.nanoTime();
            dpTimes.add((endTime - startTime) / 1e6);

            startTime = System.nanoTime();
            FibonacciMethods.fibonacciMatrix(n);
            endTime = System.nanoTime();
            matrixTimes.add((endTime - startTime) / 1e6);

            startTime = System.nanoTime();
            FibonacciMethods.fibonacciBinet(n);
            endTime = System.nanoTime();
            binetTimes.add((endTime - startTime) / 1e6);

            startTime = System.nanoTime();
            FibonacciMethods.fibonacciIterative(n);
            endTime = System.nanoTime();
            iterativeTimes.add((endTime - startTime) / 1e6);

            startTime = System.nanoTime();
            FibonacciMethods.fibonacciMemoization(n);
            endTime = System.nanoTime();
            memoizationTimes.add((endTime - startTime) / 1e6);
        }

        // Create Chart
        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Fibonacci Execution Times")
                .xAxisTitle("n-th Fibonacci Term")
                .yAxisTitle("Time (ms)")
                .build();
        XYChart chart1 = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Fibonacci Execution Times")
                .xAxisTitle("n-th Fibonacci Term")
                .yAxisTitle("Time (ms)")
                .build();
        XYChart chart2 = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Fibonacci Execution Times")
                .xAxisTitle("n-th Fibonacci Term")
                .yAxisTitle("Time (ms)")
                .build();
        XYChart chart3 = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Fibonacci Execution Times")
                .xAxisTitle("n-th Fibonacci Term")
                .yAxisTitle("Time (ms)")
                .build();
        XYChart chart4 = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Fibonacci Execution Times")
                .xAxisTitle("n-th Fibonacci Term")
                .yAxisTitle("Time (ms)")
                .build();
        XYChart chart5 = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Fibonacci Execution Times")
                .xAxisTitle("n-th Fibonacci Term")
                .yAxisTitle("Time (ms)")
                .build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        chart1.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart1.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        chart2.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart2.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        chart3.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart3.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        chart4.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart4.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        chart5.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart5.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add Series
        List<Number> xDataMeasured = xData.subList(1, xData.size());
        chart.addSeries("Recursive", rec_xData, recursiveTimes);
        chart1.addSeries("DP", xDataMeasured, dpTimes.subList(1, dpTimes.size()));
        chart2.addSeries("Matrix", xDataMeasured, matrixTimes.subList(1, matrixTimes.size()));
        chart3.addSeries("Binet", xDataMeasured, binetTimes.subList(1, binetTimes.size()));
        chart4.addSeries("Iterative", xDataMeasured, iterativeTimes.subList(1, iterativeTimes.size()));
        chart5.addSeries("Memoization", xDataMeasured, memoizationTimes.subList(1, memoizationTimes.size()));

        // Show it
        new SwingWrapper<>(chart).displayChart();
        new SwingWrapper<>(chart1).displayChart();
        new SwingWrapper<>(chart2).displayChart();
        new SwingWrapper<>(chart3).displayChart();
        new SwingWrapper<>(chart4).displayChart();
        new SwingWrapper<>(chart5).displayChart();
    }
}