package com.AssignmentNutritap.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AssignmentNutritap.Exception.ResourceNotFoundException;
import com.AssignmentNutritap.Repository.UserRepository;
import com.AssignmentNutritap.entity.User;
import com.AssignmentNutritap.payload.UserDto;
import com.AssignmentNutritap.security.JwtHelper;
import com.AssignmentNutritap.service.UserService;

import jakarta.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JwtHelper helper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) throws Exception {
		User user = this.dtoToUser(userDto);
		User existUser = userRepo.findByMobileNo(user.getMobileNo());
		if(existUser!=null) {
			throw new ResourceNotFoundException("User already exist !!",user.getUsername(),0);
		}
		
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User saveUser = this.userRepo.save(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " userId ", userId));
		return this.userToDto(user);
	}

	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public User findUserByJwt(String jwt) throws Exception {
        String mobileNo = helper.getUsernameFromToken(jwt);
        if(mobileNo==null){
            throw new ResourceNotFoundException("Provide valid jwt token....",mobileNo,0);
        }

     User user =  userRepo.findByMobileNo(mobileNo);
        if(user==null){
            throw new ResourceNotFoundException("user not found with email ", mobileNo,0);
        }
        return user;
	}
	
	
	
	// this function will create some dummy user for login credentials
	@PostConstruct
	public void createDummyUser() throws Exception {
		   User user1 = new User();
	        user1.setName("John Doe");
	        user1.setMobileNo("1234567890");
	        user1.setPassword("password123");

	        User user2 = new User();
	        user2.setName("Alice Smith");
	        user2.setMobileNo("9876543210");
	        user2.setPassword("password456");

	        // Save dummy users to the database
	        createUser(this.modelMapper.map(user1, UserDto.class));
	        createUser(this.modelMapper.map(user2, UserDto.class));
	}
	

}
