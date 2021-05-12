package gsa;

import java.util.ArrayList;

public class ArrayListExample {
	
	
	public static void printArrayValues(ArrayList<String> arr) {
		for(int i=0; i<arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}
	
	public static void main(String[] args) {
		
		//Creating and Initializing an ArrayList of String
		ArrayList<String> strArray = new ArrayList<String>();
		
		//adding an item to arrayList
		strArray.add("Item 1");
		strArray.add("Item 2");
		strArray.add("Item 3");
		strArray.add("Item 4");
		strArray.add("Item 5");
		printArrayValues(strArray);
		
		System.out.println("\n\nAfter deleting two items");
		strArray.remove(1);
		strArray.remove(3);
		printArrayValues(strArray);
		
		
		
	}

}
