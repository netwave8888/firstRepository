package netwave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TerminalOperationRunner {

	public static void main(String[] args) {
		
//		List<Product> productList = Arrays.asList(new Product(23, "potatoes"),
//				  new Product(14, "orange"), new Product(13, "lemon"),
//				  new Product(23, "bread"), new Product(13, "sugar"));
		
		// ########### Code interview questions ##########################
		int[] list1 = {1, 3, 5, 9, 16, 7, 21};
		
		Arrays.sort(list1);
		System.out.println(
		Arrays.binarySearch(list1, 9));
		
		List<Integer> myList = Arrays.asList(10, 15, 8, 49, 25, 98, 32, 19);
		
		List<Integer> myList1 = myList.stream().sorted().collect(Collectors.toList());
		System.out.println(myList1);
		System.out.println("binarySearch: " +
		Collections.binarySearch(myList1, 49));
		
		//myList.stream().filter(num -> num%2 ==0).collect(Collectors.toList()).forEach(System.out::println);
		
		// TODO: find out all the numbers start with 1
		myList.stream().filter(num -> String.valueOf(num).startsWith("1"))
		.forEach(System.out::println);
		
		System.out.println(
		myList.stream()
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
		Set<Integer> set1 = new HashSet<>();
		myList.stream().filter(n -> !set1.add(n)).forEach(System.out::println);
		
		myList.stream().filter(n -> !set1.add(n)).count(); // max();
		myList.stream().filter(n -> !set1.add(n)).max(Integer::compare);
		
		// Give a string, find the first non-repeated charactor in it
		String str = "ihggaabbccmeeffghijj";
		List<String> charList = Arrays.asList(str.split(""));
		charList.stream().forEach(System.out::println);
		System.out.println("NoRepeat: " +
		charList.stream()
		.collect(Collectors.groupingBy(Function.identity(), 
		Collectors.counting())).entrySet().stream()
		.filter(entry -> entry.getValue()==1L)
		.map(entry -> entry.getKey())
		.findFirst().get());
		Set<String> charSet = new HashSet();
		// Give a String, find the first repeatable char
		System.out.println(
		charList.stream().filter( c -> !charSet .add(c)).findFirst().get());
		
		// ################ End of Code interview ############### //
		List<Course> courses = Arrays.asList(
				new Course("Spring", "Framework", 98, 20000),
				new Course("Spring Boot", "Framework", 95, 18000),
				new Course("Microservices", "Microservices", 94, 25000),
				new Course("FullStack", "FullStack", 91, 14000),
				new Course("Azure", "Cloud", 99, 21000),
				new Course("Docker", "Cloud", 95, 20000),
				new Course("Kubernetes", "Cloud", 96, 25000)
		);
		Course keyForCompare = new Course("J2ee", "Framework", 94, 25000);
		
		List<Course> newList = courses.stream().collect(Collectors.toList());
		
		newList.add(4, keyForCompare);
		
		// newList.stream().forEach(System.out::println);
		// newList.removeIf(course -> course.getCategory().equalsIgnoreCase("fullstack"));
		
		
		System.out.println("My Index: " +  newList.indexOf(keyForCompare));
		
		List<String> words = new ArrayList<String>();
		Collections.reverseOrder();
		
		// allMatch; anyMatch; noneMatch
		boolean allMatch = courses.stream()
				.map(course -> course.getNoOfStudents())
				.noneMatch(n -> n%13 == 0);
		System.out.println(allMatch);
		
		// ###### terminal operation: skip, limit, 
		courses.stream().skip(2).limit(3).forEach(System.out::println);
		// ###### terminal operation: takeWhile, dropWhile Not in java 8. 
		// take all the course with review score high than 93
		System.out.println("Reviews better than 95: ");
		courses.stream().filter(course -> course.getReviewScore() >= 93)
			.forEach(System.out::println);
		// finding top, max, min   ##### find a course with max number of students
		Course maxCourse = courses.stream().
				sorted(Comparator.comparing(Course::getNoOfStudents).reversed()).findFirst().get();
		
		Course maxCourse1 = courses.stream()
				// .sorted(Comparator.comparing(Course::getReviewScore))
				.max(Comparator.comparing(Course::getNoOfStudents)
						.thenComparing(Comparator.comparing(Course::getReviewScore))).get();
		System.out.println("Max: " + maxCourse1);
		
		// get top 2 courses
		System.out.println("Limit Top 2: ");
		courses.stream()
		// .sorted(Comparator.comparing(Course::getReviewScore))
		.sorted(Comparator.comparing(Course::getNoOfStudents)
				.thenComparing(Comparator.comparing(Course::getReviewScore)).reversed())
		.limit(2).forEach(System.out::println);;
		
		// sum, average, count
		System.out.println(
				courses.stream().map(course -> course.getReviewScore()).filter(n -> n > 95).count());
		System.out.println(
				courses.stream().mapToInt(Course::getReviewScore).sum());
		System.out.printf("%,.3f",
				courses.stream().mapToInt(Course::getReviewScore).average().getAsDouble());
		
		int[] numbers = {1, 4, 7, 5, 2, 9};
		IntStream intStream = Arrays.stream(numbers);
		
		System.out.println("\nReduce: " + intStream.reduce((a,b)->a+b).getAsInt());
		
		System.out.println("\nReduce: " + IntStream.range(1, 10).reduce(10, (a,b)->(a+b)));
		
		courses.stream().sorted(Comparator.comparing(Course::getName).thenComparing(Comparator.comparing(Course::getName)))
				.map(course -> course.getName()).forEach(System.out::println);
		courses.stream().map(course -> { if (course.getReviewScore() > 95) return course.getName() + ": A"; else return course.getName() + ": B";}).collect(Collectors.toList()).forEach(System.out::println);
		// sort by category then course name length
		System.out.println(
		courses.stream()
			.sorted(Comparator.comparing(Course::getCategory)
					.thenComparing((o1, o2) -> {
						if ( o1.getName().length() == o2.getName().length())
							return 0;
						else if ( o1.getName().length() > o2.getName().length())
							return 1;
						else return -1;
					}))
					.collect(Collectors.toList()));
		
		// [Course [name=Azure, category=Cloud, reviewScore=99, noOfStudents=21000], Course [name=Docker, category=Cloud, reviewScore=95, noOfStudents=20000], Course [name=Kubernetes, category=Cloud, reviewScore=96, noOfStudents=25000], Course [name=Spring, category=Framework, reviewScore=98, noOfStudents=20000], Course [name=Spring Boot, category=Framework, reviewScore=95, noOfStudents=18000], Course [name=FullStack, category=FullStack, reviewScore=91, noOfStudents=14000], Course [name=Microservices, category=Microservices, reviewScore=94, noOfStudents=25000]]
		// getting predicate programming way
		courses.stream().filter(reviewScoreHigherThan95(95)).sorted().forEach(System.out::println);
		
		System.out.println("\nMultiple fields Comparator ");
		Comparator<Course> compareByName = Comparator
                .comparing(Course::getCategory)
                .thenComparing(Course::getName);
		courses.stream().sorted(compareByName).forEach(System.out::println);
		
		System.out.println("\nMultiple fields Comparator Review: ");
		courses.stream().sorted(Comparator
                .comparing(Course::getCategory)
                .thenComparing(Course::getReviewScore).reversed()).forEach(System.out::println);
	}

	private static Predicate<? super Course> reviewScoreHigherThan95(int key) {
		return course -> course.getReviewScore() > key;
	}
}