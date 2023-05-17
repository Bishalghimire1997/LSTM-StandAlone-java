package com.example.lstmmodel.intelpolation;

import java.util.ArrayList;

public class LinearInterpolation {
	ArrayList<Double> Data = new ArrayList<Double>();

	public LinearInterpolation(ArrayList<Double> data) {

		this.Data = data;

		ArrayList<ArrayList<ArrayList<Double>>> coordinate = determineInterpolatingPoints(Data);
		for (int i = 0; i < coordinate.size(); i++) {
			ArrayList<Double> val = computeInterpolation(coordinate.get(i).get(0).get(0),
					coordinate.get(i).get(0).get(1), coordinate.get(i).get(1).get(0), coordinate.get(i).get(1).get(1));
			Data = conCat(Data, val, coordinate.get(i).get(0).get(0), coordinate.get(i).get(0).get(1));

		}

	}

	static ArrayList<ArrayList<ArrayList<Double>>> determineInterpolatingPoints(ArrayList<Double> data) {
		

			double x1 = -1;
			double x2 = -1;
			double y1 = -1.0000;
			double y2 = -1.000000;
			int flag = 0;
			boolean flag1 = false;

			ArrayList<ArrayList<ArrayList<Double>>> Coordinate = new ArrayList<ArrayList<ArrayList<Double>>>();

			for (int i = 0; i < data.size(); i++) {
				ArrayList<Double> x = new ArrayList<Double>();
				ArrayList<Double> y = new ArrayList<Double>();
				ArrayList<ArrayList<Double>> temp = new ArrayList<ArrayList<Double>>();

				if (Double.isNaN(data.get(i))) {
					flag1 = true;
				} else {
					flag1 = false;
				}

				if (flag1 == true && flag == 0) {
					x1 = i - 1;
					y1 = data.get(i - 1);
					flag = 1;

				} else if (flag1 == true && flag == 1) {

				} else if (flag1 == false && flag == 1) {
					x2 = i;
					y2 = data.get(i);
					flag = 0;
					x.add(x1);
					x.add(x2);
					y.add(y1);
					y.add(y2);

					temp.add(x);
					temp.add(y);
					Coordinate.add(temp);
					Double.isNaN(i);

				} else {
				}
			}
			return Coordinate;
		
	}

	private ArrayList<Double> computeInterpolation(double x1, double x2, double y1, double y2) {
		ArrayList<Double> val = new ArrayList<Double>();
		for (int i = 0; i < (x2 - x1); i++) {
			val.add((y1 * ((x2 - (i + x1)) / (x2 - x1)) + y2 * ((i) / (x2 - x1))));
		}
		return val;
	}

	private ArrayList<Double> conCat(ArrayList<Double> data, ArrayList<Double> val, double x1, double x2) {
		int tempX1 = (int) x1;
		int tempX2 = (int) x2;
		for (int i = 1; i < (tempX2 - tempX1); i++) {
			data.set((i + tempX1), val.get(i));
		}
		return data;
	}


}
