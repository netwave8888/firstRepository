package com.netxwave.springbootwebangular;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    // standard constructors
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userService.getAllUsers();
    }
    
    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }
    
    @GetMapping("user/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
        return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
    }    

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
    	userService.addUser(user);
        // userRepository.save(user);
    }
    
    @DeleteMapping("user/{id}")
    public User deleteUser(@PathVariable("id") Long id) {
    
    	User deletedUser = userService.deleteUserById(id);
    	
    	return deletedUser;
    }
    
    
}