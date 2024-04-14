package com.AssignmentNutritap.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.AssignmentNutritap.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByMobileNo(String mobileNo);
	
	

}
