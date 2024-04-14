package com.AssignmentNutritap.service;

import com.AssignmentNutritap.entity.User;
import com.AssignmentNutritap.payload.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto) throws Exception;
	
	UserDto getUserById(Integer id);
	
	public User findUserByJwt(String jwt) throws Exception;

}
