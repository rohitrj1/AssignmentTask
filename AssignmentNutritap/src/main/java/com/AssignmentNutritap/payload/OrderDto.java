package com.AssignmentNutritap.payload;

import java.time.LocalDateTime;
import java.util.List;

import com.AssignmentNutritap.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class OrderDto {
	private Integer orderId;
    private Double totalamount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private User user;
    private List<OrderItemDto> items;


}
