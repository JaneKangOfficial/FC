package com.example.study.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse>{

	@Autowired
	private UserRepository userRepository;
	
	// 1. request data
	// 2. user 생성 
	// 3. 생성된 데이터 -> UserApiResponse return 
	
	
	@Override
	public Header<UserApiResponse> create(Header<UserApiRequest> request) {

		// 1. request data
		UserApiRequest userApiRequest = request.getData();
		
		// 2. User 생성
		User user = User.builder()
				.account(userApiRequest.getAccount())
				.password(userApiRequest.getPassword())
				.status(UserStatus.REGISTERED)
				.phoneNumber(userApiRequest.getPhoneNumber())
				.email(userApiRequest.getEmail())
				.registeredAt(LocalDateTime.now())
				.build();
		User newUser = userRepository.save(user);
		
		// 3. 생성된 데이터 -> UserApiResponse return 
		return response(newUser);
		
		
	}

	@Override
	public Header<UserApiResponse> read(Long id) {

		// id로 repository getOne, getById
		Optional<User> optional = userRepository.findById(id);
		
		// user로 userApiResponse return
		return optional
				.map(user -> response(user))	// user가 있을 때 
				.orElseGet(() -> Header.ERROR("데이터 없음"));	// user가 없을 때
/*
 		return userRepository.findById(id)
 				.map(user -> response(user))
 				.orElseGet(() -> Header.ERROR("데이터 없음"));
 */
	}

	@Override
	public Header<UserApiResponse> update(Header<UserApiRequest> request) {

		// 1. data
		UserApiRequest userApiRequest = request.getData();
		
		// 2. id -> user 데이터를 찾고
		Optional<User> optional = userRepository.findById(userApiRequest.getId());
		
		return optional.map(user -> {
		// 3. data -> update
			user.setAccount(userApiRequest.getAccount())
				.setPassword(userApiRequest.getPassword())
				.setStatus(userApiRequest.getStatus())
				.setPhoneNumber(userApiRequest.getPhoneNumber())
				.setEmail(userApiRequest.getEmail())
				.setRegisteredAt(userApiRequest.getRegisteredAt())
				.setUnregisteredAt(userApiRequest.getUnregisteredAt())
				;
			return user;
		})
		.map(user -> userRepository.save(user))				// 3. update -> updateUser
		.map(updateUser -> response(updateUser))			// 4. userApiResponse
			
		.orElseGet(() -> Header.ERROR("데이터 없음"));
		
	}

	@Override
	public Header delete(Long id) {
		
		// 1. id -> repository -> user
		Optional<User> optional = userRepository.findById(id);
		
		// 2. repository -> delete
		return optional.map(user -> {
			userRepository.delete(user);
			return Header.OK();
		})
		.orElseGet(() -> Header.ERROR("데이터 없음"));
		
	}
	
	
	private Header<UserApiResponse> response(User user) {
		// 3. user 객체로 userApiResponse를 만들어 return 
		
		UserApiResponse userApiResponse = UserApiResponse.builder()
				.id(user.getId())
				.account(user.getAccount())
				.password(user.getPassword())	// todo 암호화, 길이반환 
				.email(user.getEmail())
				.phoneNumber(user.getPhoneNumber())
				.status(user.getStatus())
				.registeredAt(user.getRegisteredAt())
				.unregisteredAt(user.getUnregisteredAt())
				.build();
		
		// Header + data return
		return Header.OK(userApiResponse);
	}
	

}
