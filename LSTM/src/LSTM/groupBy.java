package LSTM;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;
import java.time.Year;
import java.util.Set;

public class groupBy {
//	/**
//	 * Here we are just Populate one year data--------> The data is in UTC datetime
//	 * format Here we generate N year data------->Starting date time=the date time
//	 * that is now Data interval = every 15 minute
//	 */
//
////	List<Instant> date = new ArrayList<>();
////	ArrayList<Double> data = new ArrayList();
//	Instant startTime = Instant.now();
//	int numberOfYear = 1;// ------------------------------------->How may year data to be generated
//
//	/**
//	 * Data grouped by year
//	 */
	ArrayList<Float> data=new ArrayList<Float>();
	ArrayList<OffsetDateTime> date=new ArrayList<OffsetDateTime>();
	ArrayList<ArrayList<OffsetDateTime>> groupedDateByYear = new ArrayList<ArrayList<OffsetDateTime>>();
	ArrayList<ArrayList<Float>> groupedDataByYear = new ArrayList<ArrayList<Float>>();
	
	ArrayList<ArrayList<OffsetDateTime>> groupedDateByMin = new ArrayList<ArrayList<OffsetDateTime>>();
	ArrayList<ArrayList<Float>> groupedDataByMin = new ArrayList<ArrayList<Float>>();
	
	ArrayList<ArrayList<OffsetDateTime>> groupedDateByHour = new ArrayList<ArrayList<OffsetDateTime>>();
	ArrayList<ArrayList<Float>> groupedDataByHour = new ArrayList<ArrayList<Float>>();


	groupBy(List<Float> data2,List<OffsetDateTime> date2) {
//		for (int i = 0; i <4 * 8760 * numberOfYear; i++) {//4 * 8760 
//			data.add(i * 100.23);
//			date.add(startTime);
//			startTime = startTime.plusSeconds(900);
//
//		}
//		System.out.println(date);
////		year();
////		minute();
//		hour();
//		

		//System.out.print(date);
		 data= (ArrayList<Float>) data2;
		 date=(ArrayList<OffsetDateTime>) date2;
		System.out.println(date);
		System.out.println(data);

	}

	private void year() {
		/**
		 * Should contain arraylist of arraylist of utc time; Inner list should contain
		 * the date grouped by year; Should contain arraylist of arraylist of double;
		 * inner list should contain corresponding data grouped by year
		 */
		/**
		 * Find out how many unique year are there in the 
		 */
		ArrayList<Integer> yearData = new ArrayList<Integer>();
		ArrayList<OffsetDateTime> groupedDateByYearTemp = new ArrayList<OffsetDateTime>();
		ArrayList<Float> groupedDataByYearTemp = new ArrayList();
		
		
		
		for (int i = 0; i < date.size(); i++) {
			//OffsetDateTime odt = date.get(i).atOffset(java.time.ZoneOffset.UTC);
			int temp = date.get(i).getYear();
			yearData.add(temp);
		}
			/**
			 * get the uniqe list of  years and sort it 
			 */
		Set<Integer> uniqueSet = new HashSet<>(yearData);
		 ArrayList<Integer> uniqueList = new ArrayList<>(uniqueSet);
		 Collections.sort(uniqueList);
		 //System.out.println(uniqueList);
		 
		 /**
		  * Chek the unique data in the date
		  * Group data and date
		  */
    for(int i=0;i<uniqueList.size();i++) {
	for(int j=0;j<date.size();j++) {
//		System.out.println(j);
//		System.out.println(date.size());
		
		if(uniqueList.get(i)==date.get(j).getYear()) {
			 groupedDateByYearTemp.add(date.get(j));
			 groupedDataByYearTemp.add(data.get(j));
//			 System.out.println(groupedDateByYearTemp);
			 
			
		}
		
		
	}
	 groupedDateByYear.add(groupedDateByYearTemp);
	 groupedDataByYear.add( groupedDataByYearTemp);
	 groupedDateByYearTemp = new ArrayList();
	 groupedDataByYearTemp = new ArrayList();
    }
//System.out.print(groupedDataByYear.size());
	}
	// static void month(ArrayList<LocalDateTime>dates) {

	static void day() {

	}

	static void dayOfWeek() {

	}

	public void hour() {
		
		ArrayList<Integer> hourData = new ArrayList<Integer>();
		ArrayList<OffsetDateTime> groupedDateByHourTemp = new ArrayList<OffsetDateTime>();
		ArrayList<Float> groupedDataByHourTemp = new ArrayList<Float>();
		
		for (int i = 0; i < date.size(); i++) {
			//OffsetDateTime odt = date.get(i).atOffset(java.time.ZoneOffset.UTC);
			int temp = date.get(i).getHour();
			hourData.add(temp);
		}
			
		Set<Integer> uniqueSet = new HashSet<>(hourData);
		 ArrayList<Integer> uniqueList = new ArrayList<>(uniqueSet);
		 Collections.sort(uniqueList);
		 System.out.println(uniqueList);
		 
		 /**
		  * Chek the unique data in the date
		  * Group data and date
		  */
    for(int i=0;i<uniqueList.size();i++) {
	for(int j=0;j<date.size();j++) {
//		System.out.println(j);
//		System.out.println(date.size());
		
		if(uniqueList.get(i)==date.get(j).getHour()) {
			 groupedDateByHourTemp.add(date.get(j));
			 groupedDataByHourTemp.add(data.get(j));
//			 System.out.println(groupedDateByYearTemp);
			 
			
		}
		
		
	}
	 groupedDateByHour.add(groupedDateByHourTemp);
	 groupedDataByHour.add( groupedDataByHourTemp);
	 groupedDateByHourTemp = new ArrayList();
	 groupedDataByHourTemp = new ArrayList();
    }
    //System.out.println(groupedDataByHour.size());
    //System.out.println(groupedDateByHour.get(0).size());

	}

	

public void minute() {
		
		ArrayList<Integer> minData = new ArrayList<Integer>();
		ArrayList<OffsetDateTime> groupedDateByMinTemp = new ArrayList<OffsetDateTime>();
		ArrayList<Float> groupedDataByMinTemp = new ArrayList<Float>();
		
		for (int i = 0; i < date.size(); i++) {
			//OffsetDateTime odt = date.get(i).atOffset(java.time.ZoneOffset.UTC);
			int temp = date.get(i).getMinute();
			minData.add(temp);
		}
			
		Set<Integer> uniqueSet = new HashSet<>(minData);
		 ArrayList<Integer> uniqueList = new ArrayList<>(uniqueSet);
		 Collections.sort(uniqueList);
		 System.out.println(uniqueList);
		 
		 /**
		  * Chek the unique data in the date
		  * Group data and date
		  */
    for(int i=0;i<uniqueList.size();i++) {
	for(int j=0;j<date.size();j++) {
//		System.out.println(j);
//		System.out.println(date.size());
		
		if(uniqueList.get(i)==date.get(j).getMinute()) {
			 groupedDateByMinTemp.add(date.get(j));
			 groupedDataByMinTemp.add(data.get(j));
//			 System.out.println(groupedDateByYearTemp);
			 
			
		}
		
		
	}
	 groupedDateByMin.add(groupedDateByMinTemp);
	 groupedDataByMin.add( groupedDataByMinTemp);
	 groupedDateByMinTemp = new ArrayList();
	 groupedDataByMinTemp = new ArrayList();
    }
    //System.out.print(groupedDataByMin.size());

	}

}
