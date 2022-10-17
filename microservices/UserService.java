package com.netxwave.springbootwebangular;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired 
	UserRepository userRepository;
	
	User addUser(User user) {
		return userRepository.save(user);
	}
	
	List<User> getAllUsers() {
		Iterable<User> users = userRepository.findAll();
		List<User> userList = new ArrayList<User>();
		
		for ( User user : users) {
			userList.add(user);
		}
		return userList;	
	}
	
	User getUserById(Long id) {
		Optional<User> optional = userRepository.findById(id);
		return optional.isPresent()? optional.get(): null;
	}
	
	User deleteUserById(Long id) {
		Optional<User> optional = userRepository.findById(id);
		userRepository.deleteById(id);
		return optional.isPresent()? optional.get():null;
	}

	public User getUserByEmail(String email) {
		Optional<User> optional = userRepository.findByEmail(email);
		return optional.isPresent()? optional.get(): null;
	}
	
	public User getUserByRole(String role) {
		Optional<User> optional = userRepository.findByEmail(role);
		return optional.isPresent()? optional.get(): null;
	}
	
	
}
