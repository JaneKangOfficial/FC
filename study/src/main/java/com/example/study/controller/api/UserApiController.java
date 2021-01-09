package com.example.study.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.service.UserApiLogicService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j	// Simple logging facade for Java -> log.info(); 사용 가능 
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User>{

	
/*	
	@Autowired
	private UserApiLogicService userApilogicService;
	
	@PostConstruct
	public void init() {
		this.baseService = userApilogicService;
	}
}
*/	

///*
//public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse>{
 
	@Autowired
	private UserApiLogicService userApiLogicService;
	
	@GetMapping("/{id}/orderInfo")
	public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id) {
		return userApiLogicService.orderInfo(id);
	}
	
	
	@GetMapping("")	// /api/user 		//전체 정보 조회 
	public Header<List<UserApiResponse>> search(@PageableDefault(sort = "id", direction = Direction.ASC, size = 10) Pageable pageable) {
		log.info("{}", pageable);
		return userApiLogicService.search(pageable);
	}
	

/*	
	@Override
	@PostMapping("")	// /api/user
	public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
		log.info("{}", request);
		return userApiLogicService.create(request);
	}

	@Override
	@GetMapping("{id}")	//	/api/user/{id}
	public Header<UserApiResponse> read(@PathVariable(name="id") Long id) {
		log.info("read id : {}", id);
		return userApiLogicService.read(id);
	}

	@Override
	@PutMapping("")		// /api/user
	public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
		log.info("update : {}", request);
		return userApiLogicService.update(request);
	}

	@Override
	@DeleteMapping("{id}")	// /api/user/{id}
	public Header delete(@PathVariable Long id) {
		log.info("delete : {}", id);
		return userApiLogicService.delete(id);
	}
 */
}
