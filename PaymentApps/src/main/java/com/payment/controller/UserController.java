package com.payment.controller;

import com.payment.model.Users;
import com.payment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
   private UserService userService;


    /**
     * Handler for testing the controller.
     *
     * @return A welcome message.
     */
    @GetMapping("/users/hello")
    public String testHandler() {
        return "Welcome to Spring Security";
    }


    	/*

	  {
        "username": "aman",
        "password": "1234",
        "email": "aman@gmail.com",
        "phone": "1234567890",
        "address": "kanpur",
        "role": "admin"

    }

	 */


    /**
     * Registers a new customer.
     * Encrypts the user's password and appends "ROLE_" to the provided role.
     * Registers the user using the UserService.
     *
     * @param user The user to be registered.
     * @return The registered user details.
     */
    @PostMapping("/users")
    public ResponseEntity<Users> saveCustomerHandler(@RequestBody Users user){


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole("ROLE_"+user.getRole().toUpperCase());


        Users users= userService.registerCustomer(user);

        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);

    }

    /**
     * Retrieves user details by email.
     *
     * @param email The email of the user to retrieve.
     * @return The user details.
     */
    @GetMapping("/users/{email}")
    public ResponseEntity<Users> getUserByEmailHandler(@PathVariable("email") String email){


        Users user= userService.getUserDetailsByEmail(email);

        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);

    }

    /**
     * Retrieves details of all users.
     *
     * @return List of all users' details.
     */
    @GetMapping("/admin/users")
    public ResponseEntity<List<Users>> getAllUsersHandler(){


        List<Users> users= userService.getAllUsersDetails();

        return new ResponseEntity<>(users,HttpStatus.ACCEPTED);

    }

    /**
     * Updates user details by email.
     *
     * @param email The email of the user to update.
     * @param users The updated user details.
     * @return The updated user details.
     */
    @PatchMapping("/users/{email}")
    public ResponseEntity<Users> updateUserDetailsByEmailHandler(@PathVariable("email") String email, @RequestBody Users users){

        Users user= userService.updateUserDetailsByEmail(email,users);

        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }


    /**
     * Deletes a user by email.
     *
     * @param email The email of the user to delete.
     * @return The deleted user details.
     */
    @DeleteMapping("/users/{email}")
    public ResponseEntity<Users> deleteUserByEmailHandler(@PathVariable("email") String email){

        Users user= userService.deleteUserEmail(email);

        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    /**
     * Retrieves details of all users by their role.
     *
     * @param role The role for which to retrieve users.
     * @return List of users' details with the specified role.
     */
    @GetMapping("/admin/role/{role}")
    public ResponseEntity<List<Users>> getAllUsersDetailsByRoleHandler(@PathVariable("role") String role){

        List<Users> users= userService.getAllUsersDetailsByRole(role);

        return new ResponseEntity<>(users,HttpStatus.ACCEPTED);
    }

    /**
     * Retrieves details of the currently logged-in customer.
     * Logs the email of the logged-in user.
     *
     * @param auth The authentication object for the logged-in user.
     * @return The details of the logged-in user.
     */
    @GetMapping("/signIn")
    public ResponseEntity<Users> getLoggedInCustomerDetailsHandler(Authentication auth){


        log.info(auth.getName()); // this will print the email of the logged in user

        Users users= userService.getUserDetailsByEmail(auth.getName());

        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }

}
