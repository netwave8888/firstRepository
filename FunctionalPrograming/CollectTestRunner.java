package com.netwave;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectTestRunner {

	public static void main(String[] args) {

		// List of Courses #############################################################
				List<Course> courses = Arrays.asList(
						new Course("Spring", "Framework", 98, 20000),
						new Course("Spring Boot", "Framework", 95, 18000),
						new Course("Microservices", "Microservices", 96, 25000),
						new Course("FullStack", "FullStack", 91, 14000),
						new Course("Azure", "Cloud", 99, 21000),
						new Course("Docker", "Cloud", 95, 20000),
						// new Course("Docker", "Cloud", 95, 20000),
						new Course("Kubernetes", "Cloud", 91, 20000)
				);
				System.out.println("For Alice: ");
				courses.stream().filter(course -> course.getReviewScore() > 95).forEach(System.out::println);
				//TODO: remove category = "cloud" and get a new list.
				List<Course> noCloudList = courses.stream().filter(course -> !"cloud".equalsIgnoreCase(course.getCategory()))
						.collect(Collectors.toList());
				System.out.println( "Remove Cloud: " + noCloudList);
				System.out.println("###########");
				// TODO: get course names
				List<String> courseNames = courses.stream().map(course -> course.getName())
						.collect(Collectors.toList());
				System.out.println(courseNames);
				
				//TODO: keep the original order: to LinkedList
				List<String> result = courseNames.stream()
						  .collect(Collectors.toCollection(LinkedList::new));
				result.stream().forEach(System.out::println);
				
				// ToMap
				Map<String, Integer> nameMap = courseNames.stream()
						 .collect(Collectors.toMap(Function.identity(), String::length, (item, identicalItem) -> item));
						
				Map<String, Integer> reviewMap = courses.stream()
						  .collect(Collectors.toMap(Course::getName, Course::getReviewScore, (item, identicalItem) -> item));
				
				System.out.println("To Map: ");
				System.out.println(nameMap);
				System.out.println(reviewMap);
			
				System.out.println("######### End of Maps ############");
				// groupBy
				courses.stream().collect(Collectors.groupingBy(Course::getCategory));
				System.out.println("######### GroupBy ############");
//				Map<String, Map<String, Long>> multipleFieldsMap = employeesList.stream()
//		                .collect(
//		                        Collectors.groupingBy(Employee::getDesignation,
//		                                Collectors.groupingBy(Employee::getGender,
//		                                        Collectors.counting())));
				System.out.println(
					courses.stream().collect(Collectors.groupingBy(Course::getCategory, 
						Collectors.counting())));
				System.out.println(
						courses.stream().collect(Collectors.groupingBy(Course::getCategory, 
							Collectors.groupingBy(Course::getName, Collectors.counting() ))));
				
				// Multiple fields groupBy with results in a set.
				System.out.println(
						courses.stream().collect(Collectors.groupingBy(Course::getCategory, 
							Collectors.groupingBy(Course::getReviewScore ))));
				
				// maxBy
				System.out.println(
						courses.stream().collect(
							Collectors.groupingBy(Course::getCategory, 
							Collectors.maxBy(Comparator.comparing(Course::getReviewScore)))
						)
				);
				
				IntSummaryStatistics stats = courses.stream()
						  .collect(Collectors.summarizingInt(Course::getReviewScore));
				
				System.out.println("min, max, ave, sum" + String.valueOf(stats.getMin()) + "@  " +
							String.valueOf(stats.getMax()) + "@  " +
							String.valueOf(stats.getAverage()) + "@  " +
							String.valueOf(stats.getSum()));
				
				// Collectors.collectingAndThen()
				
				// Collectors.joining()
				
				// Collectors.counting()
				
				// Collectors.summarizingDouble/Long/Int() or averaging
				// Collectors.summingDouble/Long/Int() 
				// Collectors.maxBy()/minBy()
				// Collectors.groupingBy(); Collectors.partitioningBy(); Collectors.teeing()
				
				// Custom Collectors hard TODO:
				/*
				Class Collectors. Implementations of Collector that implement various 
				useful reduction operations, such as accumulating elements into collections, 
				summarizing elements according to various criteria, etc.
				*/
				
				// multiple fields groupBy
//				Map<String, Map<String, Long>> multipleFieldsMap = employeesList.stream()
//		                .collect(
//		                        Collectors.groupingBy(Employee::getDesignation,
//		                                Collectors.groupingBy(Employee::getGender,
//		                                        Collectors.counting())));
				// partition by predicates:
				
				Map<Boolean, List<Course>> mapPartioned = courses.stream()
						  .collect(Collectors.partitioningBy(element -> element.getReviewScore() > 95));
				System.out.println(mapPartioned);
				
				// count time:
				//measuring elapsed time using Spring StopWatch
				long startTime = System.nanoTime();
		        for(int i=0; i< 1000000; i++){
		            Object obj = new Object();
		        }
		        long elapsedTime = System.nanoTime() - startTime;
		        System.out.println("Total execution time to create 1000K objects in Java in millis: "
		                + elapsedTime/1000000);

		// Read more: https://javarevisited.blogspot.com/2012/04/how-to-measure-elapsed-execution-time.html#ixzz7hqZeeDS9
		        // use String.join to show partitions:
		        Map<Boolean, List<Course>> classifiedElements = courses
		        	    .stream()
		        	    .collect(Collectors.partitioningBy((course) -> 
		        	      course.getName().startsWith("A")));

		        	String matching = String.join(",",
		        	  classifiedElements.get(true).toString());
		        	String nonMatching = String.join(",",
		        	  classifiedElements.get(false).toString());
		        	
		        	System.out.println(matching);
		
	}

}
