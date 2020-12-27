package com.example.study.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;

public class OrderDetailRepositoryTest extends StudyApplicationTests{

	@Autowired
	private OrderDetailRepository orderDetailRepository;

/*		
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
*/
	
	@Test
	public void create() {
		OrderDetail orderDetail = new OrderDetail();
		
		orderDetail.setStatus("WAITING");
		orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
		orderDetail.setQuantity(1);
		orderDetail.setTotalPrice(BigDecimal.valueOf(900000));
		orderDetail.setCreatedAt(LocalDateTime.now());
		orderDetail.setCreatedBy("AdminServer");
		
		
//		orderDetail.setOrderGroupId(2L);	// 연관관계 설정으로 변경 : Long -> OrderGroup 	// 어떠한 장바구니에 
//		orderDetail.setItemId(1L);			// 어떠한 상품 
		
		OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
		Assert.assertNotNull(newOrderDetail);
		
	}
}
