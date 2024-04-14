package com.AssignmentNutritap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AssignmentNutritap.entity.Order;
import com.AssignmentNutritap.entity.User;
import com.AssignmentNutritap.payload.JwtConstant;
import com.AssignmentNutritap.payload.OrderDto;
import com.AssignmentNutritap.service.OrderService;
import com.AssignmentNutritap.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	
    @PostMapping("{userId}")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDTO,@PathVariable Integer userId){
        	  	OrderDto order = orderService.createOrder(orderDTO,userId);
            return new ResponseEntity<OrderDto>(order, HttpStatus.CREATED);
    }
    
    @GetMapping("{userId}")
    public ResponseEntity<List<Order>> getOrderByUser(@PathVariable Integer userId) {
    	 return new ResponseEntity<List<Order>>(orderService.findUserOrder(userId),HttpStatus.OK);
    }
    

}
