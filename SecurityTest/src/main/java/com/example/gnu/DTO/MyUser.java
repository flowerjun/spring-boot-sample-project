package com.example.gnu.DTO;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MyUser implements UserDetails{ 
	private static final long serialVersionUID = 1798998709204672720L;
	private String userid;
	private String password;
	private String email;
	private Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
	
	public void addAuthorities(String authority) {
		SimpleGrantedAuthority grant = new SimpleGrantedAuthority(authority);
		authorities.add(grant);
	}
	
	@Override
	public Collection<SimpleGrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userid;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "MyUser [username=" + userid + ", password=" + password + ", email=" + email
				+ ", authorities=" + authorities + "]";
	}
}
