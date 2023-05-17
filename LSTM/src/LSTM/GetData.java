package LSTM;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.time.ZoneOffset;

public class GetData {
	public  List<String> all_data = new ArrayList<String>();

	public  List<OffsetDateTime> date = new ArrayList<OffsetDateTime>();
	public  List<Float> data = new ArrayList<Float>();

	//public int interval = 100;
	public GetData() {
		this.read_csv();
		this.get_date_and_data();
	}

	public void read_csv() {
		try {
			//String pathFile = "C:\\Users\\bishal.ghimire\\Desktop\\data\\Neuer Ordner\\Consumption\\generalconsumption.csv";
			String pathFile="C:\\Users\\bishal.ghimire\\Desktop\\data\\loadValues.csv";
			Scanner sc = new Scanner(new File(pathFile));

			// parsing a CSV file into the constructor of Scanner class
			sc.useDelimiter(",");

			// setting comma as delimiter pattern
			while (sc.hasNext()) {
				// System.out.println(sc.next());
				all_data.add(sc.next());
			}
			//System.out.println(all_data);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * it creates the array of date time rather than reading from CSV
	 */

	public void get_date_and_data() {
		try {

			/*
			 * "2018-05-01T00:00:00+02:00" is the starting date in csv
			 */

			//String startingTime="2018-05-01T00:00:00+02:00";//2018-05-01T00:00:00+02:579//2018-05-01T00:00:00+02:00
			//OffsetDateTime temp = OffsetDateTime.parse(startingTime);
			
			
			for (int i = 0; i < all_data.size() -1; i++) {
//				String val =all_data.get(i).toString();
//				int lastColonIndex = val.lastIndexOf("+");
//				String result = val.substring(lastColonIndex + 1);
//				String dateFormat="yyyy-MM-dd'T'HH:mm:ss"+"+"+result;
//				dateFormat=dateFormat.toString();
//				System.out.println("-------------------------------"+dateFormat);
			System.out.println(all_data.get(i).toString());
				
				//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ROOT);
				
			//LocalDateTime dateTime = LocalDateTime.parse(all_data.get(i).toString(),formatter);

				String b = all_data.get(i + 1).toString();
				//OffsetDateTime dateTime = OffsetDateTime.parse(temp, formatter);
//				OffsetDateTime utcDateTime = temp.withOffsetSameInstant(ZoneOffset.UTC);
				//LocalDateTime dateTime = LocalDateTime.parse(temp, formatter);
				//OffsetDateTime utcDateTime = offsetDateTime.withOffsetSameInstant(ZoneOffset.UTC);
				

				//date.add(utcDateTime);
				data.add(Float.parseFloat(b));
				//date.add(temp);
				i = i + 1;
				//temp= temp.plusMinutes(1);

			}
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}


}
