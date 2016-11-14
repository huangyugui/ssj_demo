package com.example.demo.user.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.demo.base.model.CommonBean;

@Entity
@Table(name = "keys_pub_user")
public class User extends CommonBean{

	private String name;
	
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
