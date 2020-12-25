package com.example.study.repository;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;

public class OrderDetailRepositoryTest extends StudyApplicationTests{

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Test
	public void create() {
		OrderDetail orderDetail = new OrderDetail();
		
		orderDetail.setOrderAt(LocalDateTime.now());
		
		// 어떤 사람? 
//		orderDetail.setUserId(7L);
		
		// 어떤 상품? 
//		orderDetail.setItemId(1L);
		
		OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
		Assert.assertNotNull(newOrderDetail);
		
	}
	
}
