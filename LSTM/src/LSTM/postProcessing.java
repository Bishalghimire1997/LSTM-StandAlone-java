package LSTM;

import java.util.ArrayList;
import java.util.Collections;

public class postProcessing {
	// double[]scaledBackData;
	private static double scaleBack(Double valScaled, double minScaled, double maxScaled, double minOrginal,
			double maxOrginal) {
		double orginal = (valScaled * maxOrginal / (maxScaled - minScaled)) + minOrginal;
		return orginal;

	}

	public static double[] scaleBackList(ArrayList<Float> orginalData, double[] scaledData) {

		double[] scaledBackData = new double[orginalData.size()];
		ArrayList<Double> scaledData1 = convertToArrayList(scaledData);
		double minScaled = 0.2;// Collections.min(scaledData1);
		double maxScaled = 0.8;// Collections.max(scaledData1);
		double minOrginal = Collections.min(orginalData);// this value should be the minimum of training dataset
		double maxOrginal = Collections.max(orginalData);// this value should be maximum of training dataset

		for (int i = 0; i < scaledData.length; i++) {
			scaledBackData[i] = (scaleBack(scaledData1.get(i), minScaled, maxScaled, minOrginal, maxOrginal));
			//System.out.println(scaledBackData[i]);
		}

		return scaledBackData;
	}

	private static ArrayList<Double> convertToArrayList(double[] data) {
		ArrayList<Double> toReturn = new ArrayList<Double>();
		for (int i = 0; i < data.length; i++) {
			toReturn.add(data[i]);

		}
		return toReturn;
	}

	public static double[] convertToList(ArrayList<Double> arr) {
		double[] toReturn = new double[arr.size()];
		for (int i = 0; i < arr.size(); i++) {
			toReturn[i] = arr.get(i);

		}
		return toReturn;

	}

}
