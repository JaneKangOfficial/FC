package com.example.study.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 모든 생성자 
@NoArgsConstructor // 기본 생성자 
@Entity

public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String status;
	
	private String name;
	
	private String title;
	
	private String content;

	private Integer price;
	
	private String brandName;
	
	private LocalDateTime registeredAt;

	private LocalDateTime unregisteredAt;
	
	private LocalDateTime createdAt;
	
	private String createdBy;
	
	private LocalDateTime updatedAt;
	
	private String updatedBy;
	
	private Long partnerId;
	
	
	
	
	
	/*	
	// 연관관계 설정 -> Item : OrderDetail -> 1 : N
	// mappedBy = "item" 에서 item 은 OrderDetail.java 의 변수명인 item 과 동일해야함  
	
	// LAZY = 지연로딩(연관관계 설정된 것은 지금 로딩 안해)
	// EAGER = 즉시로딩(연관관계 설정된 것 즉시 로딩해, 성능저하, 위험 우려, 1:1때만 추천) 
	
	// LAZY = SELECT * FROM item WHERE id = ?
	// EAGER = SELECT * FROM 
	// item_id = order_detail.item_id
	// user_id = order_detail.user_id
	// where item.id = ?
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<OrderDetail> orderDetailList;
*/
	
	
}

