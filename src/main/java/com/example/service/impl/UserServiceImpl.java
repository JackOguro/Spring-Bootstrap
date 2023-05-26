package com.example.service.impl;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.model.MUser;
import com.example.repository.SpringMySQLSampleMapper;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final SpringMySQLSampleMapper mapper;
	
	@Override
	public void signup(MUser user) {
		mapper.insertOne(user);
	}

	@Override
	public Optional<MUser> getLoginUser(String username) {
		return mapper.findLoginUser(username);
	}

	@Override
	public List<MUser> getAllUser() {
		return mapper.findAllUser();
	}
	

}
