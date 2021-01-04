package com.example.study.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.study.model.enumclass.ItemStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor // 모든 생성자 
@NoArgsConstructor // 기본 생성자 
@Entity
@ToString(exclude = {"orderDetailList", "partner"})
@EntityListeners(AuditingEntityListener.class)	// 이 감시자에 의해 @CreatedBy, @LastModifiedBy가 LoginUserAuditorAware.java의 설정값으로 설정됨
@Builder	// 생성자 만들지 않고 객체 생성 -> ItemRepositoryTest.java create()
@Accessors(chain = true)	// 생성자 만들지 않고 객체 수정 -> ItemRepositoryTest.java read()
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private ItemStatus status;		// 등록 / 해지 / 검수중(등록대기중)
	
	private String name;
	
	private String title;
	
	private String content;

	private BigDecimal price;
	
	private String brandName;
	
	private LocalDateTime registeredAt;

	private LocalDateTime unregisteredAt;
	
	@CreatedDate
	private LocalDateTime createdAt;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	@LastModifiedBy
	private String updatedBy;
	
	
//	private Long partnerId;
	// Item : Partner -> N : 1
	@ManyToOne
	private Partner partner;
	
	
	// Item : OrderDetail -> 1 : N 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<OrderDetail> orderDetailList;

	
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
 */
	
	
	
}

