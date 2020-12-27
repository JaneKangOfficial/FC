package com.example.study.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;

@WebAppConfiguration
@RunWith(SpringRunner.class)
public class ItemRepositoryTest extends StudyApplicationTests{

	@Autowired
	private ItemRepository itemRepository;
	
	// 오류도 없는데 DB에 안 들어가면 JUnit 버전 확인할 것 
/*	
//	@Test
//	@Transactional
	public void create() {
		
		Item item = new Item();
		item.setName("노트북");
		item.setPrice(100000);
		item.setContent("삼성 노트북");
		
		System.out.println("item1 :" + item);
		Item newItem = itemRepository.save(item);
		System.out.println("newItem1 :" + newItem);
		Assert.assertNotNull(newItem);
	}
*/
	
	@Test
	public void create() {
		Item item = new Item();
		item.setStatus("UNREGISTERED");
		item.setName("삼성 노트북");
		item.setTitle("삼성 노트북 A100");
		item.setContent("2020년형 노트북입니다.");
		item.setPrice(900000);
		item.setBrandName("삼성");
		item.setRegisteredAt(LocalDateTime.now());
		item.setCreatedAt(LocalDateTime.now());
		item.setCreatedBy("Partner01");
//		item.setPartnerId(1L);	// Long -> Partner
		
		Item newItem = itemRepository.save(item);
		Assert.assertNotNull(newItem);
		
	}
	
	
//	@Test
	public void read() {
		Long id = 1L;
	
		Optional<Item> item = itemRepository.findById(id);
		
		Assert.assertTrue(item.isPresent());
/*
		item.ifPresent(i -> {
			System.out.println(i);
		});
*/
	}
}
