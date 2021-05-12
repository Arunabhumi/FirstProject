package gsa;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.TreeMap;

public class TreeMapExample {
	
	
	public static void printArrayValues(ArrayList<TreeMap<String, String>> arr) {
		for(int i=0; i<arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}
	
	public static void main(String[] args) {
		
		ArrayList<TreeMap<String, String>> personList = new ArrayList<TreeMap<String, String>>();
		
		TreeMap<String, String> mapList = new TreeMap<String, String>();
		mapList.put("firstName", "Tony");
		mapList.put("lastName", "Stark");
		mapList.put("age", "50");
		
		
		
		personList.add(mapList);
		System.out.println(personList);
		
		printArrayValues(personList);
		
		
		System.out.println("After adding second person");
		mapList = new TreeMap<String, String>();
		mapList.put("firstName", "Steve");
		mapList.put("lastName", "Rogers");
		mapList.put("age", "70");
		
		personList.add(mapList);
		System.out.println(personList);
		
		printArrayValues(personList);
		
		
		
	}

}
