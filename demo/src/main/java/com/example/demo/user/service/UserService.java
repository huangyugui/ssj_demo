package com.example.demo.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.dao.UserRepository;
import com.example.demo.user.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void save(Boolean flag){
		User user = new User();
		user.setName("aaa");
		user.setPassword("bbbb");
		userRepository.save(user);
		if(flag){
			throw new RuntimeException();
		}
	}
	
}
