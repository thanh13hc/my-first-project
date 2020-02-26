package com.Mysite.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user",catalog="dhxd")
public class User implements Serializable{
	
	@Id
	@Column(name="username",unique=true,nullable=false,length=30)
	private String username;
	
	@Column(name="password",nullable=false,length=30)
	private String password;
	
	@Column(name="role",nullable=false,length=10)
	private String role;

	public User() {
	}
	
	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
