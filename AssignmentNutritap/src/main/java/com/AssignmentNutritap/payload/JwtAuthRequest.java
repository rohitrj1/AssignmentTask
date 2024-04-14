package com.AssignmentNutritap.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthRequest {
	
	private String mobileNo;
	
	private String password;

}
