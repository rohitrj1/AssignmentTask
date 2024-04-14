package com.AssignmentNutritap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AssignmentNutritap.entity.User;
import com.AssignmentNutritap.payload.UserDto;
import com.AssignmentNutritap.service.UserService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Get single users
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	


}
