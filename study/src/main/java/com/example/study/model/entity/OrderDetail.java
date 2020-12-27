package com.example.study.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // order_detail
// Lombok에서 ToString이 자동으로 됨 
//@ToString(exclude = {"user", "item"}) // user와 item이 서로 연관관계 설정이 돼있으면 overflow error가 발생함 -> 제외시켜줘야함
@ToString(exclude = {"orderGroupe", "item"})
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String status;
	
	private LocalDateTime arrivalDate;
	
	private Integer quantity;
	
	private BigDecimal totalPrice;
	
	private LocalDateTime createdAt;

	private String createdBy;
	
	private LocalDateTime updatedAt;
	
	private String updatedBy;
	
//	private Long itemId;
	// OrderDetail : Item -> N : 1
	@ManyToOne
	private Item item;
	
//	private Long orderGroupId;	// -> private OrderGroup orderGroup
	// OrderDetail : OrderGroup -> N : 1
	@ManyToOne
	private OrderGroup orderGroup;
	
	
	
	
	
/*
	// 연관관계 설정 -> OrderDetail : User -> N : 1
	@ManyToOne
	private User user; // user_id
//	private Long userId; // 연관관계 설정에서는 User user로 사용한다 
	
	// 연관관계 설정 -> OrderDetail : Item -> N : 1
	@ManyToOne
	private Item item; // item_id
//	private Long itemId; // 연관관계 설정에서는 Item item 으로 사용한다 
*/
	
	


}
