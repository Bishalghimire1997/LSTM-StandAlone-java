package LSTM;

import static LSTM.SlidingWindowSpliterator.windowed;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;

public class App {

	public static class Data2D {
		public double[][] input;
		public double[][] output;

		public Data2D(double[][] input, double[][] output) {
			this.input = input;
			this.output = output;
		}

	}

	public static class Data2D1D {
		public double[][] input;
		public double[] output;

		public Data2D1D(double[][] input, double[] output) {
			this.input = input;
			this.output = output;
		}

	}

	public static void main(String[] args) {
		//groupBy object=new groupBy();
		//object.groupedDataByYear;

		//int numberOfItems = 1000;
		//int windowsSize = 10;
 
	CsvReader val=new CsvReader();	

//CsvReader obj=new CsvReader();
/**
 * Compute Interpolation
 */
interpolationManager inter = new interpolationManager( val.data);
/**
 * Converting arraylist of double to list of float
 */
System.out.println(inter.interpolated);
List<Float>interpoatedData= new ArrayList<Float>();
for(int i=0;i<inter.interpolated.size();i++) {
	double val1=inter.interpolated.get(i);
	float val11=(float)val1;
	interpoatedData.add(val11);
}
/*
 * Grouping data by hour
 * 
 */
groupBy obj1=new groupBy(interpoatedData,val.dates);
 obj1.hour();
//  System.out.println(obj1.groupedDataByHour.get(0).get(0));
//  System.out.println(obj1.groupedDateByHour.get(0).get(0));
//  
 /**
  * Grouping data by minute
  */
 ArrayList<ArrayList<ArrayList<OffsetDateTime>>>dateGroupedByMinute=new ArrayList<ArrayList<ArrayList<OffsetDateTime>>>();
 ArrayList<ArrayList<ArrayList<Float>>>dataGroupedByMinute=new ArrayList<ArrayList<ArrayList<Float>>>();
 for(int i=0;i<obj1.groupedDataByHour.size();i++) {
	 groupBy obj2 =new groupBy (obj1.groupedDataByHour.get(i),obj1.groupedDateByHour.get(i));
	 obj2.minute();
	 dataGroupedByMinute.add(obj2.groupedDataByMin);
	 dateGroupedByMinute.add(obj2.groupedDateByMin);
 }
 /**
  * Creating model
  */

 ArrayList<ArrayList<ArrayList<Double>>>allModel=new ArrayList<ArrayList<ArrayList<Double>>>();
 for(int i=0;i<dataGroupedByMinute.size();i++) {
	 for(int j=0;j<dataGroupedByMinute.get(i).size();j++) {
 Preprocessing preprocessing = new Preprocessing(dataGroupedByMinute.get(i).get(j));
 TrainPredict model = new TrainPredict(preprocessing.TrainData1, preprocessing.TrainTarget1,preprocessing.ValidateData1,preprocessing.ValidateTarget1);
 ArrayList<ArrayList<Double>> value = model.train();
 allModel.add(value);
		 
	 }
	 
 }
 //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 
 /**
  * Saving the model as .txt file
  */
 String path="C:\\Users\\bishal.ghimire\\Desktop\\data\\model.txt";
 try {
     FileWriter fw = new FileWriter(path);
     BufferedWriter bw = new BufferedWriter(fw);

     for (ArrayList<ArrayList<Double>> innerList :allModel) {
         for (ArrayList<Double> innerInnerList : innerList) {
             for (Double value : innerInnerList) {
                 bw.write(value.toString() + " ");
             }
             bw.newLine();
         }
         bw.newLine();
     }

     bw.close();
 } catch (IOException e) {
     e.printStackTrace();
 }

//
// 
// 
// 
 //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 
// System.out.print(allModel);
// for(int i=0;i<allModel.size();i++) {
//	 System.out.println(allModel.get(i));
//	 
// }
 
 // 
//System.out.println(dataGroupedByMinute.get(0).get(0));
//System.out.println(dateGroupedByMinute.get(0).get(0));



//ArrayList<Float> dataBeforeInterpolation= (ArrayList<Float>) obj.data;
//System.out.print(dataBeforeInterpolation);
//interpolationManager inter =new interpolationManager( dataBeforeInterpolation);



		//cubicalInterpolation temp=new cubicalInterpolation();
		//System.out.println(temp.InterpolatingInterval);
		//System.out.println(temp.data);
		//System.out.println(temp.temp01);
		
		//cubicalInterpolation obj = new cubicalInterpolation();
		
		//Data2D1D data = generateData2D1D(numberOfItems, windowsSize);
		
//		Preprocessing preprocessing = new Preprocessing();
//		System.out.println("Training the model");
//		
//		TrainPredict model = new TrainPredict(preprocessing.TrainData1, preprocessing.TrainTarget1,preprocessing.ValidateData1,preprocessing.ValidateTarget1);
//		//System.out.println(preprocessing.TrainData1[0][0]);
		//System.out.println(preprocessing.TrainTarget1[1]);
		
		
//		ArrayList<ArrayList<Double>> value = model.train();
//		System.out.println(value);
//		ArrayList<ArrayList<Double>> value=[Arrays.asList([1.0001, 1.0000707930192247, 1.000057812525169, 1.0000500746495398]), [1.0, 1.0, 1.0, 1.0], [1.0, 1.0, 1.0, 1.0], [1.0, 1.0, 1.0, 1.0], [40.182290000213555, 28.734442561947393, 23.672560692610446, 20.601966588225352], [-2.621570000000876, -1.4711466927252606, -1.0920090355728949, -0.9397036432259607], [0.11077647065408125, 0.21399037794769543, 0.2554455546542761, 0.2818279320844675], [2.61315193422762E-6])]
//		ArrayList<Double> x1 = new ArrayList<Double>(Arrays.asList(1.0001, 1.0000707930192247, 1.000057812525169, 1.0000500746495398));
//		ArrayList<Double> x2 = new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 1.0));
//		ArrayList<Double> x3 = new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 1.0));
//		ArrayList<Double> x4 = new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 1.0));
//		ArrayList<Double> x5 = new ArrayList<Double>(Arrays.asList(40.182290000213555, 28.734442561947393, 23.672560692610446, 20.601966588225352));
//		ArrayList<Double> x6 = new ArrayList<Double>(Arrays.asList(-2.621570000000876, -1.4711466927252606, -1.0920090355728949, -0.9397036432259607));
//		ArrayList<Double> x7 = new ArrayList<Double>(Arrays.asList(0.11077647065408125, 0.21399037794769543, 0.2554455546542761, 0.2818279320844675));
//		ArrayList<Double> x8 = new ArrayList<Double>(Arrays.asList(2.61315193422762E-6));
//		ArrayList<ArrayList<Double>> value=new ArrayList<ArrayList<Double>>();
//		value.add(x1);
//		value.add(x2);
//		value.add(x3);
//		value.add(x4);
//		value.add(x5);
//		value.add(x6);
//		value.add(x7);
//		value.add(x8);
		
		
		
		
		//---------------------------------------------------------Make Prediction-----------------------------------------------------------------------
		//ouble[] dataTrain=model.Predict(preprocessing.TrainData1, preprocessing.TrainTarget1, value);
		//double[] dataValidate=model.Predict(preprocessing.ValidateData1, preprocessing.ValidateTarget1, value);
		//double[] dataTest=model.Predict(preprocessing.TestData1,  preprocessing.TestTarget1, value);
		//-------------------------------------------------------------Scale the data back--------------------------------------------------------------
//		postProcessing obj1=new postProcessing();
//		double[] dataTest1=obj1.scaleBackList(preprocessing.data, dataTest);
//		//System.out.print(dataTest);
//		
//		
//		
//		//------------------------------------------------------------Preparing to plot data------------------------------------------------------------
//		ArrayList<double[]> toPlot1=new ArrayList<double[]>();
//		ArrayList<double[]> toPlot2=new ArrayList<double[]>();
//		ArrayList<double[]> toPlot3=new ArrayList<double[]>();
//		
//		ArrayList<String> plotName1=new ArrayList<String> ();
//		ArrayList<String> plotName2=new ArrayList<String> ();
//		ArrayList<String> plotName3=new ArrayList<String> ();
//		
//		
//		toPlot1.add(obj1.scaleBackList(preprocessing.data, dataTrain));
//		toPlot1.add(obj1.scaleBackList(preprocessing.data, preprocessing.TrainTarget1));
//		
//		toPlot2.add(obj1.scaleBackList(preprocessing.data, dataTest));
//		toPlot2.add(obj1.scaleBackList(preprocessing.data, preprocessing.TestTarget1));
//		
//		toPlot3.add(obj1.scaleBackList(preprocessing.data, dataValidate));
//		toPlot3.add(obj1.scaleBackList(preprocessing.data, preprocessing.ValidateTarget1));
//		
//		plotName1.add("Train Predicted");
//		plotName1.add("Train Orginal");
//		
//		plotName2.add("Test Predicted");
//		plotName2.add("Test Orginal");
//		
//		plotName3.add("Validate Predicted");
//		plotName3.add("Validate Orginal");
//		
////----------------------------------------------------------------------------Calling plot class------------------------------------
//		plot linePlots =new plot();
//		linePlots.linePlot(toPlot1,plotName1);
//		linePlots.linePlot(toPlot1,plotName2);
//		linePlots.linePlot(toPlot1,plotName3);
//		
////----------------------------------------------------------------------------End---------------------------------------------------
		
		
		
		
		
		
		
		//double[][] testedData = { {0.0010, 0.011, 0.012, 0.013, 0.014,0.015,0.016,0.017,0.018,0.019} };
		//double[] testedActualData = { 0.020 };

		//System.out.println(value);
		//model.Predict(testedData, testedActualData, value);
		//Interpolation inter=new Interpolation();

	}

	public static Data2D1D generateData2D1D(int numberOfItems, int windowsSize) {

		Random rnd = new Random();
		List<Double> dataGenerated = DoubleStream.iterate(0.1, i -> i + 0.0001) //
				.limit(numberOfItems) //
				.boxed() //
				.collect(Collectors.toList());

		List<List<Double>> XList = windowed(dataGenerated, windowsSize) //
				.map(s -> s.collect(Collectors.toList())) //
				.collect(Collectors.toList());

		XList.remove(XList.size() - 1);

		System.out.println(XList);

		long seed = new Random().nextLong();

		Collections.shuffle(XList, new Random(seed));
		double[][] Xarray = XList.stream() //
				.map(l -> l.stream() //
						.mapToDouble(Double::doubleValue) //
						.toArray()) //
				.toArray(double[][]::new);

		List<Double> YList = dataGenerated.subList(windowsSize, dataGenerated.size());

		System.out.println(YList);
		Collections.shuffle(YList, new Random(seed));

		double[] yArray = YList.stream().mapToDouble(d -> d).toArray();

		return new Data2D1D(Xarray, yArray);

	}

	public static Data2D generateData2D(int numberOfItems, int windowsSize) {

		Random rnd = new Random();
		List<Double> dataGenerated = DoubleStream.iterate(10, i -> i + 10) //
				.limit(numberOfItems) //
				.boxed() //
				.collect(Collectors.toList());

		List<List<Double>> XList = windowed(dataGenerated, windowsSize) //
				.map(s -> s.collect(Collectors.toList())) //
				.collect(Collectors.toList());

		XList.remove(XList.size() - 1);

		long seed = new Random().nextLong();

		Collections.shuffle(XList, new Random(seed));
		double[][] Xarray = XList.stream() //
				.map(l -> l.stream() //
						.mapToDouble(Double::doubleValue) //
						.toArray()) //
				.toArray(double[][]::new);

		List<Double> YList = dataGenerated.subList(windowsSize, dataGenerated.size());

		System.out.println(YList);

		Collections.shuffle(YList, new Random(seed));

		double[] yArray = YList.stream().mapToDouble(d -> d).toArray();

		double[][] actualYArray = new double[yArray.length][1];

		for (int i = 0; i < yArray.length; i++) {
			actualYArray[i] = new double[] { yArray[i] };
		}
		return new Data2D(Xarray, actualYArray);

	}

//	public static void main(String[] args) {
//
////		double[] b = { 0.5, 0.6 };
////		double[][] a = { { 0.1, 0.2, 0.3, 0.4 }, { 0.2, 0.3, 0.4, 0.5 } };
//		Preprocessing Datas=new Preprocessing();
//		
//        
//		//System.out.println(Datas.TestData);
//		
//		//System.out.println(Datas.ValidateData);
//		
//		//System.out.println(Datas.data.size());
//
//		int numberOfItems = 10000;
//		int windowsSize = 5;
//		Data2D1D data = generateData2D1D(numberOfItems, windowsSize);
//
//		TrainPredict model = new TrainPredict();
//		ArrayList<ArrayList<Double>> value = model.train(Datas.TrainData1, Datas.TrainTarget1);
//		model.Predict(Datas.TrainData1,Datas.TrainTarget1,value);
//
//		;
//
//	}

}
