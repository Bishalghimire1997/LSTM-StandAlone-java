package LSTM;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class plot {
	public static void linePlot(ArrayList<double[]> data, ArrayList plotNames) {
		ArrayList<XYSeries> series =new ArrayList<XYSeries> (); 
		for(int i=0;i<data.size();i++)
		{ 
			XYSeries ser=new XYSeries((Comparable) plotNames.get(i));
			series.add(ser);
		}
		for(int i=0; i< data.size();i++) {
			for(int j=0; j<data.get(i).length;j++) {
				 series.get(i).add(j,data.get(i)[j]);
			}
		}
		
		XYSeriesCollection plotData = new XYSeriesCollection();
		for(int i=0;i<series.size();i++) {
			plotData.addSeries(series.get(i));
			
			
		}
		JFreeChart chart =ChartFactory.createXYLineChart("predicted Vs Actual", "Index",  "Kw", plotData, PlotOrientation.VERTICAL, true, true, false);
		ChartFrame frame1=new ChartFrame("XYLine Chart",chart);
		frame1.setVisible(true);
		frame1.setSize(300,300);
	}
	
	public static void linePlot(ArrayList<double[]> data, ArrayList plotNames,int range) {
		ArrayList<XYSeries> series =new ArrayList<XYSeries> (); 
		for(int i=0;i<data.size();i++)
		{ 
			XYSeries ser=new XYSeries((Comparable) plotNames.get(i));
			series.add(ser);
		}
		for(int i=0; i< data.size();i++) {
			for(int j=0; j<range;j++) {
				 series.get(i).add(j,data.get(i)[j]);
			}
		}
		
		XYSeriesCollection plotData = new XYSeriesCollection();
		for(int i=0;i<series.size();i++) {
			plotData.addSeries(series.get(i));
			
			
		}
		JFreeChart chart =ChartFactory.createXYLineChart("predicted Vs Actual", "Index",  "Kw", plotData, PlotOrientation.VERTICAL, true, true, false);
		ChartFrame frame1=new ChartFrame("XYLine Chart",chart);
		frame1.setVisible(true);
		frame1.setSize(300,300);
	}
//	public void linePlot()
//	{
//		XYSeries series=new XYSeries("Dummy Plot1");
//		XYSeries series1=new XYSeries("Dummy Plot1");
//		
//		
//		series.add(0,0);
//		series.add(1,1);
//		series.add(2,2);
//		series.add(3,3);
//		series.add(4,4);
//		
//		series1.add(0,0);
//		series1.add(1,1);
//		series1.add(2,4);
//		series1.add(3,9);
//		series1.add(4,16);
//		
//		XYSeriesCollection data = new XYSeriesCollection();
//		 data.addSeries(series);
//		 data.addSeries(series1);
//		 
//		//XYDataset xyDataset=new XYSeriesCollection(series);		
//		JFreeChart chart =ChartFactory.createXYLineChart("XYLine Chart using JFreeChart", "Age", "Weight", data, PlotOrientation.VERTICAL, true, true, false);
//		
//		ChartFrame frame1=new ChartFrame("XYLine Chart",chart);
//		frame1.setVisible(true);
//		frame1.setSize(300,300);
		
	
//}
}
