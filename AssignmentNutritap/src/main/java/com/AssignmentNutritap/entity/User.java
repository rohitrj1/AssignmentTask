package com.AssignmentNutritap.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(name = "user_name", nullable = false, length = 100)
	private String name;
	private String mobileNo;
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	

	
	@Override
	 @JsonIgnore
	public String getUsername() {
		return this.mobileNo;
	}

	@Override
	 @JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	 @JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	 @JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	 @JsonIgnore
	public boolean isEnabled() {
		return true;
	}

	@Override
	 @JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
