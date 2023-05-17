package LSTM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;

public class CsvReader {
	public ArrayList<Float> data = new ArrayList<Float>();
	public ArrayList<OffsetDateTime> dates = new ArrayList<OffsetDateTime>();

	public CsvReader() {
		getData();
	}
	void getData() {
		try {

			String filename = "\\testResults\\loadValues.csv";
			String path = new File(".").getCanonicalPath() + filename;

			// String
			// filename="/io.openems.edge.predictor.lstmmodel/testResults/loadValues.csv";

			// ArrayList<OffsetDateTime> dates = new ArrayList<OffsetDateTime>();
			// ArrayList<Float> data = new ArrayList<Float>();

			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\bishal.ghimire\\Desktop\\data\\loadValues.csv"));

			String line = reader.readLine();

			while (line != null) {
				String[] parts = line.split(",");
				OffsetDateTime date = OffsetDateTime.parse(parts[0]);
				// OffsetDateTime temp1;
				Float temp2 = (float) 0;

				for (int i = 1; i < parts.length; i++) {
					if (parts[i].equals("")) {
						temp2 = Float.NaN;
					} else {
						temp2 = (Float.parseFloat(parts[i]));
					}
				}

				dates.add(date);
				data.add(temp2);

				line = reader.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
