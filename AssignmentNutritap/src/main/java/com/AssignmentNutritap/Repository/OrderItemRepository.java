package com.AssignmentNutritap.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AssignmentNutritap.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
