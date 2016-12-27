package com.liftoffllc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.json.simple.JSONObject;

public class Assignment {

	public static void main(String [] args) throws ParseException 
	{
		
		//JSON St. {"city":{"name":"London","id":"2643743"},"List":[{"temp":"-1.25","time":"2016-12-27 09:00:00"},{"temp":"5.87","time":"2016-12-27 12:00:00"},{"temp":"6.42","time":"2016-12-27 15:00:00"},{"temp":"0.63","time":"2016-12-27 18:00:00"},{"temp":"-1.44","time":"2016-12-27 21:00:00"},{"temp":"-1.87","time":"2016-12-27 06:00:00"},{"temp":"-1.67","time":"2016-12-27 03:00:00"}]}

		Map<String,String> city = new HashMap<>();
		city.put("id", "2643743");
		city.put("name", "London");

		Map<Object,Object> map = new HashMap<>();
		map.put("city", city);

		List<Object> list = new ArrayList<>();
		Map<String,String> temp = new HashMap<>();
		temp.put("temp", "-1.25");
		temp.put("time", "2016-12-27 09:00:00");
		list.add(temp);
		temp = new HashMap<>();
		temp.put("temp", "5.87");
		temp.put("time", "2016-12-27 12:00:00");
		list.add(temp);
		temp = new HashMap<>();
		temp.put("temp", "6.42");
		temp.put("time", "2016-12-27 15:00:00");
		list.add(temp);
		temp = new HashMap<>();
		temp.put("temp", "0.63");
		temp.put("time", "2016-12-27 18:00:00");
		list.add(temp);
		temp = new HashMap<>();
		temp.put("temp", "-1.44");
		temp.put("time", "2016-12-27 21:00:00");
		list.add(temp);
		temp = new HashMap<>();
		temp.put("temp", "-1.87");
		temp.put("time", "2016-12-27 06:00:00");
		list.add(temp);
		temp = new HashMap<>();
		temp.put("temp", "-1.67");
		temp.put("time", "2016-12-27 03:00:00");
		list.add(temp);

		map.put("List", list);

		JSONObject json = new JSONObject(map);
		System.out.println(json);


		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Object> listTemp = (List<Object>)map.get("List");
		TreeMap<Double, Integer> sorttemp= new TreeMap<>();
		for(Object obj : listTemp)
		{
			Map<String,String> tempMap = (Map<String,String>)obj;
			sorttemp.put(Double.valueOf(tempMap.get("temp")), (df.parse(tempMap.get("time")).getHours()));
		}


		System.out.println("Minimum Temp: "+sorttemp.firstEntry().getKey()+"  at "+sorttemp.firstEntry().getValue());
		System.out.println("Maximum Temp: "+sorttemp.lastEntry().getKey()+"  at "+sorttemp.lastEntry().getValue());

		//sorting map with value, so i can get the graph data as per time
		List<Entry<Double, Integer>> listmap = new ArrayList<Entry<Double, Integer>>(sorttemp.entrySet());

		Collections.sort(listmap, new Comparator<Entry<Double, Integer>>() {

			@Override
			public int compare(Entry<Double, Integer> o1,
					Entry<Double, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		LinkedHashMap<Double, Integer> result = new LinkedHashMap<>();

		for(Entry<Double, Integer> entry : listmap)
		{
			result.put(entry.getKey(), entry.getValue());
		}
		
		System.out.println("This is my Sorted Map by time : "+ result);
		
		//This is graph map in json which we will sent in response 
		JSONObject graphMap = new JSONObject(result);
		
		System.out.println(graphMap);
	}

}
