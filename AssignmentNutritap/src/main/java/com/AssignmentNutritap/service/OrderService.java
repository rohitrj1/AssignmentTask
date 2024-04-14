package com.AssignmentNutritap.service;


import java.util.List;

import com.AssignmentNutritap.entity.Order;
import com.AssignmentNutritap.payload.OrderDto;

public interface OrderService {
	
	OrderDto createOrder(OrderDto orderDto,Integer userId);
	
	List<Order> findUserOrder(Integer userId);

}
