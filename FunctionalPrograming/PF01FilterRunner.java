package com.netwave;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.netwave.Course;

public class PF01FilterRunner {
	
	public static void main(String[] args) {
		
		Stream<String> emptyStream = Stream.empty();
		// add elements with stream builder
		emptyStream =
				  Stream.<String>builder().add("a").add("b").add("c").build();
		// emptyStream.forEach(System.out::println);
		// primitive streams IntStream, LongStream, DoubleStream
		IntStream intStream = IntStream.range(1, 3);
		LongStream longStream = LongStream.rangeClosed(1, 3);
		Random random = new Random();
		DoubleStream doubleStream = random.doubles(3);
		doubleStream.forEach(System.out::println);
		//get a stream from a file
//		Path path = Paths.get("C:\\file.txt");
//		try {
//			Stream<String> streamOfStrings = Files.lines(path);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		// intermediate operations are lazy
		System.out.println("Filter Test: ");
		List<String> names = Arrays.asList("Tao", "Alice", "Michelle", "Tom");
		String[] list1 = {"Tao", "Alice"};
		Stream<String> list1Stream = Stream.of(list1);
		
		names.stream()
			.filter(name -> 
				{ System.out.println("filter called"); 
				return name.equals("Alice");})
			.collect(Collectors.toList());
		
		// put the data in the collection to use stream API
		Collection<String> collection = Arrays.asList("a", "b", "c");
		Stream<String> streamOfCollection = collection.stream();
		// put the data in array to use stream API
		String[] strArray = { "Tao", "Alice", "Michelle" };
		Stream<String> fullArray = Stream.of(strArray);
		fullArray = Arrays.stream(strArray);
		
		
		List<Integer> numbers = Arrays.asList(12, 9, 13, 4, 6, 2, 4, 12, 15);
		numbers.stream().filter(e -> e%2 == 0).forEach(System.out::println);
		System.out.println("###########");
		numbers.stream().filter(e -> e%2!=0).forEach(System.out::println);
		
		// Throwing Exception example
//		numbers.stream()
//			    .filter(a -> {
//			            try {
//			                return a.someMethodThrowingCheckedException();
//			            } catch (IOException e) {
//			                throw new UncheckedException(e);
//			            }
//			        })
//			    .collect(Collectors.toList());
		
		// list of strings
		List<String> items = Arrays.asList("Java", "JavaScript", "Spring", "spring Boot", "Docker", "Kubernetes", "Microservices");
		
		// print courses contain spring
		items.stream().filter(c -> !(c.toLowerCase()).contains("spring")).forEach(System.out::println);
		// print courses with 4 letters
		items.stream().filter(c -> (c.length() == 4)).forEach(System.out::println);
		
		// List of Courses #############################################################
		List<Course> courses = Arrays.asList(
				new Course("Spring", "Framework", 98, 20000),
				new Course("Spring Boot", "Framework", 95, 18000),
				new Course("Microservices", "Microservices", 96, 25000),
				new Course("FullStack", "FullStack", 91, 14000),
				new Course("Azure", "Cloud", 99, 21000),
				new Course("Docker", "Cloud", 95, 20000),
				new Course("Kubernetes", "Cloud", 91, 20000)
		);
		System.out.println("###########");
		String key = "cloud";
		courses.stream().filter(course -> course.getCategory().equalsIgnoreCase("framework")).map(course -> course.getName()).forEach(System.out::println);
		
		// ##################  use custom predicate ###############################
		System.out.println("###########Custom Predicate: ");
		courses.stream().filter(matchCourseName(key)).map(course -> course.getName()).forEach(System.out::println);
		
		// find first occurance of the course
		
		Course first = courses.stream() .filter(s -> s.getCategory().contains("F")) .findFirst().get(); System.out.println("First Search: " + first);
		
		// String sort with natural and reverse order
		Comparator<Course> courseNameComparator = Comparator.comparing(Course::getName);
		Comparator<Course> courseNameByLengthComparator = Comparator.comparing(course -> course.getName().length());
		// handle null
		Comparator<Course> courseNameComparator_nullFirst
	      = Comparator.nullsFirst(courseNameComparator); // nullsLast
	    // ########### provisioning multiple sort keys ##################
		Comparator<Course> compareCourseByCategoryThenName = 
				Comparator.comparing(Course::getCategory).thenComparing(Course::getName);
		Comparator<Course> compareCourseByCategoryThenNameThenReviewScore = 
				Comparator.comparing(Course::getCategory)
				.thenComparing(Course::getName)
				.thenComparing(Course::getReviewScore);
		
		System.out.println("########### List of Course name Sort: ");
		courses.stream().map(course -> course.getName()).sorted(Comparator.reverseOrder()).forEach(System.out::println);
		System.out.println("########### List of Course name Sort by length: ");
		courses.stream()
		// .map(course -> course.getName())
		.sorted(courseNameByLengthComparator.reversed())
		.forEach(System.out::println);
		
		System.out.println("########### List of Course name Sort by review score: ");
		Comparator<Course> compareByReviewScore = Comparator.comparingInt(Course::getReviewScore);
		courses.stream()
		.sorted(compareByReviewScore.reversed())
		.forEach(System.out::println);
		
		System.out.println("########### List of Course Sort by Category then name: ");
		courses.stream()
		.sorted(compareCourseByCategoryThenNameThenReviewScore)
		.forEach(System.out::println);
		// Can create a natural order in custom class by implement Comparable<Course> interface. 
		
		// Can use Collections.sort 
//		PlayerRankingComparator playerComparator = new PlayerRankingComparator();
//		Collections.sort(footballTeam, playerComparator);
		
		/*
		So why use a Comparator if we already have Comparable?
		There are several reasons why:

		Sometimes we can't modify the source code of the class whose objects we want to sort, thus making the use of Comparable impossible
		Using Comparators allows us to avoid adding additional code to our domain classes
		We can define multiple different comparison strategies, which isn't possible when using Comparable
		*/
		
	}

	public Stream<String> streamOf(List<String> list) {
	    return list == null || list.isEmpty() ? Stream.empty() : list.stream();
	}
	
	private static Predicate<Course> matchCourseName(String key) {
		return course -> course.getCategory().equalsIgnoreCase(key);
	}
	
		
	
	

}
