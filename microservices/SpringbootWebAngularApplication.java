package com.netxwave.springbootwebangular;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootWebAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebAngularApplication.class, args);
	}

	// @Autowired
	// UserRepository userRepository;
	@Autowired
	UserService userService;
	
	@Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            Stream.of("Marvin", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                User user = new User(name, name.toLowerCase() + "@netwave.com", "admin");
                userService.addUser(user);
            });
            userService.getAllUsers().forEach(System.out::println);
        };
    }
}
