package com.example.study.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.User;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j	// Simple logging facade for Java -> log.info(); 사용 가능 
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User>{

/*	
	@Autowired
	private UserApiLogicService userApilogicService;
	
	@PostConstruct
	public void init() {
		this.baseService = userApilogicService;
	}
*/	
}

/*
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse>{
 
	@Autowired
	private UserApiLogicService userApiLogicService;
	
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
}
*/