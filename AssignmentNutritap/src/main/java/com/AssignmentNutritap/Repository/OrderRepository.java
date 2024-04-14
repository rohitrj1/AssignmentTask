package com.AssignmentNutritap.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AssignmentNutritap.entity.Order;
import com.AssignmentNutritap.entity.User;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	List<Order> findByUser(User user);

}
