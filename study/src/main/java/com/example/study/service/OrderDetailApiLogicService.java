package com.example.study.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderDetailApiRequest;
import com.example.study.model.network.response.OrderDetailApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.OrderDetailRepository;
import com.example.study.repository.OrderGroupRepository;

@Service
public class OrderDetailApiLogicService implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse>{

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private OrderGroupRepository orderGroupRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {

		OrderDetailApiRequest body = request.getData();
		
		OrderDetail orderDetail = OrderDetail.builder()
				.status(body.getStatus())
				.arrivalDate(LocalDateTime.now())
				.quantity(body.getQuantity())
				.totalPrice(body.getTotalPrice())
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.orderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
				.item(itemRepository.getOne(body.getItemId()))
				.build();
		
		OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
		
		return response(newOrderDetail);
	}

	@Override
	public Header<OrderDetailApiResponse> read(Long id) {
		return orderDetailRepository.findById(id)
				.map(orderDetail -> response(orderDetail))
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Header delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private Header<OrderDetailApiResponse> response(OrderDetail orderDetail) {
		
		OrderDetailApiResponse body = OrderDetailApiResponse.builder()
				.id(orderDetail.getId())
				.status(orderDetail.getStatus())
				.arrivalDate(orderDetail.getArrivalDate())
				.quantity(orderDetail.getQuantity())
				.totalPrice(orderDetail.getTotalPrice())
				.createdAt(orderDetail.getCreatedAt())
				.updatedAt(orderDetail.getUpdatedAt())
				.orderGroupId(orderDetail.getOrderGroup().getId())
				.itemId(orderDetail.getItem().getId())
				.build();
		
		return Header.OK(body);
	}
}
