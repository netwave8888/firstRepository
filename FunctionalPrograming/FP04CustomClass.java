package netwave;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FP04CustomClass {

	public static void main(String[] args) throws IOException {
		
		List<Course> courses = Arrays.asList(
				new Course("Spring", "Framework", 98, 20000),
				new Course("Spring Boot", "Framework", 95, 18000),
				new Course("Microservices", "Microservices", 96, 25000),
				new Course("FullStack", "FullStack", 91, 14000),
				new Course("Azure", "Cloud", 99, 21000),
				new Course("Docker", "Cloud", 95, 20000),
				new Course("Kubernetes", "Cloud", 91, 20000)
		);
		
		// ####### Good example for peek #################
//		Stream<User> userStream = Stream.of(new User("Alice"), new User("Bob"), new User("Chuck"));
//		userStream.peek(u -> u.setName(u.getName().toLowerCase()))
//		  .forEach(System.out::println);
		
//		for ( Course item : courses) {
//			System.out.println(item.toString());
//		}
		
//		System.out.println(
//		courses.stream().allMatch(course -> course.getReviewScore()>90));
		
		Comparator<Course> comparingByNoOfStudentsAndNoOfReviews = 
				Comparator.comparingInt(Course::getNoOfStudents).thenComparing(Course::getReviewScore)
				.reversed();
		
		Comparator<Course> comparingByCourseLengthAndLetterOrder = 
				Comparator.comparing(Course::getName).thenComparing(Course::getReviewScore)
				.reversed();
		
		// need to implement Caparable interface to get the order on custom classes.
		// Comparator<Course> courseComparator = Comparator.<Course> naturalOrder();
		Comparator<Course> courseComparator = Comparator.<Course> reverseOrder();
		
		// System.out.println(courses.stream().sorted(courseComparator).collect(Collectors.toList()));
		
		// [Course [name=Spring Boot, category=Framework, reviewScore=95, noOfStudents=18000], Course [name=Spring, category=Framework, reviewScore=98, noOfStudents=20000], Course [name=Microservices, category=Microservices, reviewScore=96, noOfStudents=25000], Course [name=Kubernetes, category=Cloud, reviewScore=91, noOfStudents=20000], Course [name=FullStack, category=FullStack, reviewScore=91, noOfStudents=14000], Course [name=Docker, category=Cloud, reviewScore=95, noOfStudents=20000], Course [name=Azure, category=Cloud, reviewScore=99, noOfStudents=21000]]
//		System.out.println(
//		courses.stream().collect(Collectors.groupingBy(Course::getCategory)));
		// Map<String, List<Course>> categoryMap = courses.stream().collect(Collectors.groupingBy(Course::getCategory));
//		for ( String key : categoryMap.keySet()) {
//			System.out.println("Category: " + key + "\n");
//			System.out.println(categoryMap.get(key));
//		}
		
		// courses.stream().peek(e->System.out.println(e.toString())).forEach(System.out::println);
		System.out.println(
		courses.stream().filter(course -> course.getName().length() > 11).findFirst());
		
		 IntStream.iterate(1, e->e+2).limit(10).peek(System.out::println).forEach(System.out::println);
		 // ############################ Playing with files ################
		Files.lines(Paths.get("C:\\Eclipse2022-9\\workspace\\JavaInterviewProject\\src\\file1.txt"))
		.map(str -> str.split(" "))
		.flatMap(Arrays::stream)
		.distinct().sorted()
		.forEach(System.out::println);
		
		// print out all in current directory
		Files.list(Paths.get("."))
		.forEach(System.out::println);
		
	}

}
