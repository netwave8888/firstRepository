package com.netwave;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ArranysTestRunner {

	public static void main(String[] args) {
		String[] intro = new String[] {"once", "upon", "a", "time" };
		String[] abridgement = Arrays.copyOfRange(intro, 0, 3);
		List<String> strList = Arrays.asList(intro);
		
		List<String> shortList = Arrays.asList(abridgement);
		
		strList.stream().forEach(System.out::println);
		shortList.stream().sorted().forEach(System.out::println);
		
		// Binary Search
		int[] intArray = {10, 20, 15, 22, 35};
		
		Arrays.sort(intArray);
		
		System.out.println(
				Arrays.binarySearch(intArray, 22));
		
		// Binary Search( array, fromIndex, toIndex, key, Comparator) method
		Arrays.sort(intro);
		System.out.println("Sorted Intro: ");
		for ( String str : intro) {
			System.out.println(str);
		}
		System.out.println(
		Arrays.binarySearch(intro, 0, 4, "a", stringComparator()));
		
		// Arrays.stream(intro).findFirst()
		
		// find the day with the most number of letters in it. 
		String[] weekdays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		
		// get the day with most letters
		System.out.println(
				Arrays.stream(weekdays).max(Comparator.comparingInt(String::length)).get());
		// reverse the order
		System.out.println( 
				Arrays.stream(weekdays).collect(Collectors.collectingAndThen(Collectors.toList(), l -> {Collections.reverse(l); return l;})));
		
		

	}

	private static Comparator<String> stringComparator() {
		return (str1, str2) -> str1.compareTo(str2);
	}

}
