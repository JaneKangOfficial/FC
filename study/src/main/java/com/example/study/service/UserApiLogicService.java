package com.example.study.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
//public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse>{
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User>{

	@Autowired
	private OrderGroupApiLogicService orderGroupApiLogicService;
	
	@Autowired
	private ItemApiLogicService itemApiLogicService;
	
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
		User newUser = baseRepository.save(user);
		
		// 3. 생성된 데이터 -> UserApiResponse return 
//		return response(newUser);
		return Header.OK(response(newUser));
		
		
	}

	@Override
	public Header<UserApiResponse> read(Long id) {

		// id로 repository getOne, getById
		Optional<User> optional = baseRepository.findById(id);
		
		// user로 userApiResponse return
		return optional
				.map(user -> response(user))	// user가 있을 때 
//				.map(UserApiResponse -> Header.OK(userApiResponse))	// map(Header::OK) 와 같음 
				.map(Header::OK)
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
		Optional<User> optional = baseRepository.findById(userApiRequest.getId());
		
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
		.map(user -> baseRepository.save(user))				// 3. update -> updateUser
		.map(updateUser -> response(updateUser))			// 4. userApiResponse
		.map(Header::OK)
		.orElseGet(() -> Header.ERROR("데이터 없음"));
		
	}

	@Override
	public Header delete(Long id) {
		
		// 1. id -> repository -> user
		Optional<User> optional = baseRepository.findById(id);
		
		// 2. repository -> delete
		return optional.map(user -> {
			baseRepository.delete(user);
			return Header.OK();
		})
		.orElseGet(() -> Header.ERROR("데이터 없음"));
		
	}

	public Header<List<UserApiResponse>> search(Pageable pageable) {
		
//		log.info("{}", pageable);
//		Page<User> users = (Page<User>) userRepository.findAll((Sort) pageable);
		Page<User> users = baseRepository.findAll(pageable);
		
		List<UserApiResponse> userApiResponseList = users.stream()
				.map(user -> response(user))
				.collect(Collectors.toList());
		
		// List<UserApiResponse>
		// Header<List<UserApiResponse>>
		
		Pagination pagination = Pagination.builder()
				.totalPages(users.getTotalPages())
				.totalElements(users.getTotalElements())
				.currentPage(users.getNumber())
				.currnetElements(users.getNumberOfElements())
				.build();
		
		return Header.OK(userApiResponseList, pagination);
	}
	
	private UserApiResponse response(User user) {
		
//	private Header<UserApiResponse> response(User user) {
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
//		return Header.OK(userApiResponse);
		return userApiResponse;
	}
	
	public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
		
		// user
		User user = baseRepository.getOne(id);
		UserApiResponse userApiResponse = response(user);
		
		// orderGroup
		List<OrderGroup> orderGroupList = user.getOrderGroupList();
		List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
				.map(orderGroup -> {
					OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();
					
					// item api response
					List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
							.map(detail -> detail.getItem())
							.map(item -> itemApiLogicService.response(item).getData())
							.collect(Collectors.toList());
					
					orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
					return orderGroupApiResponse;
				})
				.collect(Collectors.toList());
		
		userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);
		UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
				.userApiResponse(userApiResponse)
				.build();
		
		return Header.OK(userOrderInfoApiResponse);
		
	}
	

}
