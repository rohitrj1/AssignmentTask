package com.AssignmentNutritap.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.AssignmentNutritap.Exception.ResourceNotFoundException;
import com.AssignmentNutritap.Repository.UserRepository;
import com.AssignmentNutritap.entity.User;



@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading data from user database from username
		User user = userRepo.findByMobileNo(username);
		if(user == null) {
			throw new ResourceNotFoundException("User mobile : " , username, 0);
		}
		return user;
	}

}