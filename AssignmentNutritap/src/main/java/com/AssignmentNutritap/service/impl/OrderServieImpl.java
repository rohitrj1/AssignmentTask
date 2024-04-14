package com.AssignmentNutritap.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AssignmentNutritap.Repository.OrderItemRepository;
import com.AssignmentNutritap.Repository.OrderRepository;
import com.AssignmentNutritap.entity.Order;
import com.AssignmentNutritap.entity.OrderItem;
import com.AssignmentNutritap.entity.User;
import com.AssignmentNutritap.payload.OrderDto;
import com.AssignmentNutritap.payload.OrderItemDto;
import com.AssignmentNutritap.payload.UserDto;
import com.AssignmentNutritap.service.OrderService;
import com.AssignmentNutritap.service.UserService;

@Service
public class OrderServieImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public OrderDto createOrder(OrderDto orderDto,Integer userId) {
		UserDto userById = userService.getUserById(userId);
        Order order = new Order();
        order.setUser(mapper.map(userById, User.class));
        order.setCreatedDate(LocalDateTime.now());
        order.setModifiedDate(LocalDateTime.now());
        // Calculate the total amount from order items
        double totalAmount = calculateTotalAmount(orderDto.getItems());
        order.setTotalamount(totalAmount);
        // Save the order
        Order savedOrder = orderRepository.save(order);
        // Process and save order items
        saveOrderItems(savedOrder, orderDto.getItems());
        return this.mapper.map(savedOrder, OrderDto.class);
	
	}
	
    private double calculateTotalAmount(List<OrderItemDto> items) {
        return items.stream()
                .mapToDouble(OrderItemDto::getPrice)
                .sum();
    }
    
    private void saveOrderItems(Order order, List<OrderItemDto> items) {
        for (OrderItemDto itemDTO : items) {
            OrderItem item = new OrderItem();
            item.setItemName(itemDTO.getItemName());
            item.setPrice(itemDTO.getPrice());
            item.setOrder(order);
            orderItemRepository.save(item);
        }
    }

	@Override
	public List<Order> findUserOrder(Integer userId) {
		UserDto userById = userService.getUserById(userId);
		List<Order> byUser = orderRepository.findByUser(mapper.map(userById, User.class));
		return byUser;
	}


}
