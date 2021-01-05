package com.example.study.controller.api;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.service.OrderGroupApiLogicService;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

/*
	@Autowired
	private OrderGroupApiLogicService orderGroupApiLogicService;
	
	@PostConstruct
	public void init() {
		this.baseService = orderGroupApiLogicService;
	}
*/	
}



/*
public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse>{

	@Autowired
	private OrderGroupApiLogicService orderGroupApiLogicService;
	
	
	@Override
	@PostMapping("")
	public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {
		return orderGroupApiLogicService.create(request);
	}

	@Override
	@GetMapping("{id}")
	public Header<OrderGroupApiResponse> read(@PathVariable Long id) {
		return orderGroupApiLogicService.read(id);
	}

	@Override
	@PutMapping("")
	public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
		return orderGroupApiLogicService.update(request);
	}

	@Override
	@DeleteMapping("{id}")
	public Header delete(@PathVariable Long id) {
		return orderGroupApiLogicService.delete(id);
	}
}
*/