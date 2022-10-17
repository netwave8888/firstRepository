package com.netwave;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class PerformanceTimeRunning {
	
	static String[] COUNTRY_NAMES 
    = { "China", "Australia", "India", "USA", "USSR", "UK", "China", 
    "France", "Poland", "Austria", "India", "USA", "Egypt", "China" };
	
	public static void main(String[] args) {
		// TODO Can use spring StopWatch
		Map<String, Integer> counterMap1 = new HashMap<>();
		for (String country : COUNTRY_NAMES) {
			counterMap1.compute( country, 
					(key, count) -> { if (count == null) return 1; else return ++count; }
						
						            );
		}
		System.out.println(counterMap1);
		
		int[] intArray = {1, 2, 3, 2, 3};
		Map<Integer, Integer> intMap = new HashMap<>();
		for ( Integer num : intArray) {
			intMap.compute(num, (k, v) -> v==null? 1: v+1);
		}
		System.out.println(intMap);
		
		// TODO: create an int array and find 18
		int[] searchArray = IntStream.range(1, 22).toArray();
		int index = Arrays.binarySearch(searchArray, 16);
		searchArray[index] = 18;
		System.out.println( Arrays.toString(searchArray));
		
		
		
		
		// count time:
		//measuring elapsed time using Spring StopWatch
		long startTime = System.nanoTime();
        for(int i=0; i< 1000000; i++){
            Object obj = new Object();
        }
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time to create 1000K objects in Java in millis: "
                + elapsedTime/1000000);
        
        // word frequency performance compare
        
        
        Map<String, Integer> counterMap = new HashMap<>();

        for (String country : COUNTRY_NAMES) { 
            counterMap.compute(country, (key, count) -> count == null ? 1 : count + 1); 
        }
        
        System.out.println(counterMap);
        counterMap.put("Japan", 1);
        // Map's three collection views: keyset(), values, entryset
        for ( String key : counterMap.keySet()) {
        	System.out.println( "Key: " + key);
        }
        
        for ( int num : counterMap.values()) {
        	System.out.println("Values: " + num);
        }
        
        for ( Entry<String, Integer> entry : counterMap.entrySet()) {
        	System.out.println( "Entry: " + entry);
        }
        // counterMap.put("Japan", 1);
	}

}
