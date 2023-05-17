package LSTM;

import java.util.ArrayList;
import java.util.List;

public class TrainPredict {
	private double[][] inputMatrix;
	private double[] targetVector;
	private double[][] validateData;
	private double[] validateTarget;
	private ArrayList<ArrayList<ArrayList<Double>>> allWeight;
	private ArrayList<ArrayList<ArrayList<Double>>> allWeightFinal;

	public TrainPredict(double[][] inputMatrix, double[] targetVector, double[][] validateData,
			double[] validateTarget) {
		this.inputMatrix = inputMatrix;
		this.targetVector = targetVector;
		this.validateData = validateData;
		this.validateTarget = validateTarget;
		this.allWeight = new ArrayList<ArrayList<ArrayList<Double>>>();
		this.allWeightFinal = new ArrayList<ArrayList<ArrayList<Double>>>();
	}

	public ArrayList<ArrayList<Double>> train() {
		double perc = 0.00;
		ArrayList<ArrayList<Double>> val = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < inputMatrix.length; i++) {
			
			//System.out.println();
			perc = ((double)(i + 1) / inputMatrix.length) * 100.00;
			//System.out.println(perc);

			adativeLearningRate rate = new adativeLearningRate();
			double learningRate = rate.scheduler(perc);

			if (i == 0) {
				Lstm1 ls;
				ls = new Lstm1(inputMatrix[i], targetVector[i], learningRate);
				ls.initilizeCells();
				val = ls.trainEpoc();
				allWeight.add(val);
			} else {
				Lstm1 ls = new Lstm1(inputMatrix[i], targetVector[i], learningRate);
				ls.initilizeCells();
				for (int j = 0; j < ls.cells.size(); j++) {
					ls.cells.get(j).wi = (val.get(0)).get(j);
					ls.cells.get(j).wo = (val.get(1)).get(j);
					ls.cells.get(j).wz = (val.get(2)).get(j);
					ls.cells.get(j).Ri = (val.get(3)).get(j);
					ls.cells.get(j).Ro = (val.get(4)).get(j);
					ls.cells.get(j).Rz = (val.get(5)).get(j);
				}
				ls.cells.get(0).yt = (val.get(6)).get((val.get(6).size() - 1));

				val = ls.trainEpoc();
				allWeight.add(val);
			}

			if (allWeight.size() == 50) {
				int ind = selectWeight(allWeight, perc);

				// System.out.println("Indes from allweightis : " + ind);
				val = allWeight.get(ind);//------------------------------------------------------------------> Check this line
				//System.out.print(val);
				allWeightFinal.add(val);
				//System.out.println(ind);
				allWeight.clear();
			} else {
				// double error = val.get(7).get(0);
				// System.out.println("AllWeight=" + allWeight.size() + " error=" + error + " %
				// completed = " + perc);
			}
		}
		int ind = selectWeight(allWeightFinal, perc);
		System.out.println("--------------------------------------------------Indes from allWeightFinal : " + ind+"---------------------------------------------");
		val = allWeightFinal.get(ind);

		return val;
	}

	public double[] Predict(double[][] input_data, double[] Target, ArrayList<ArrayList<Double>> val) {
		ArrayList<Double> wi = val.get(0);
		ArrayList<Double> wo = val.get(1);
		ArrayList<Double> wz = val.get(2);
		ArrayList<Double> Ri = val.get(3);
		ArrayList<Double> Ro = val.get(4);
		ArrayList<Double> Rz = val.get(5);
		double[] result = new double[input_data.length];
		for (int i = 0; i < input_data.length; i++) {

			result[i] = predict(input_data[i], wi, wo, wz, Ri, Ro, Rz);
		}

		return result;
	}

	public static double predict(double[] input_data, ArrayList<Double> wi, ArrayList<Double> wo, ArrayList<Double> wz,
			ArrayList<Double> Ri, ArrayList<Double> Ro, ArrayList<Double> Rz) {
		double ct = 0;

		double yt = 0;
		Calculations maths = new Calculations();

		for (int i = 0; i < wi.size(); i++) {
			double it = maths.sigmoid(wi.get(i) * input_data[i] + Ri.get(i) * yt);
			double ot = maths.sigmoid(wo.get(i) * input_data[i] + Ro.get(i) * yt);
			double zt = maths.tanh(wz.get(i) * input_data[i] + Rz.get(i) * yt);
			ct = ct + it * zt;
			yt = ot * maths.tanh(ct);
		}
		return yt;
	}

	public int selectWeight(ArrayList<ArrayList<ArrayList<Double>>> allWeights, double perc) {
		// System.out.println("***************************Validating**************************");
		double[] rms = new double[allWeights.size()];
		for (int k = 0; k < allWeights.size(); k++) {
			// System.out.print(i + "/" + allWeights.size() + "\r");
			ArrayList<ArrayList<Double>> val = allWeights.get(k);
			double[] pre = this.Predict(this.validateData, this.validateTarget, val);
			rms[k] = this.computeRMS(this.validateTarget, pre);
		}
		int minInd = getMinIndex(rms);
		System.out.println(".........................Training percent....................." + perc + " %");
		return minInd;
	}

	public int getMinIndex(double[] arr) {
		int minInd = 0;
		double minValue = arr[0];
		for (int l = 1; l < arr.length; l++) {
			if (arr[l] < minValue) {
				minValue = arr[l];
				minInd = l;
			}
		}
		System.out.println("RMS error=" + arr[minInd]);
		return minInd;
	}

	public double computeRMS(double[] original, double[] computed) {
		double[] diff = new double[original.length];
		double sum = 0;
		for (int i = 0; i < original.length; i++) {
			diff[i] = Math.pow(original[i] - computed[i], 2);
		}
		for (int i = 0; i < diff.length; i++) {
			sum += diff[i];
		}
		//System.out.println(diff.length);
		double mean = sum / diff.length;
		return Math.sqrt(mean);
	}
}
