package com.AssignmentNutritap.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 4 ,message ="Username must be min of 4 characters")
	private String name;
	
	@NotEmpty
	@Size(min = 10, message = "mobile must be 10 digit")
	private String mobileNo;
	
	@NotEmpty
	@Size(min = 3,max = 10 ,message = "password must be 3 chars and max of 10 chars !!")
	private String password;

}
