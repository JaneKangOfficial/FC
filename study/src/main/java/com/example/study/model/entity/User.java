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

// import 안되고 error 나오면 프로젝트명에서 오른쪽 클릭 -> gradle -> gradle refresh 눌러서 build.gradle에 적어둔 라이브러리 다운
// DB와 매칭

@Data
@AllArgsConstructor
@NoArgsConstructor // 기본생성자 
@Entity // == table
//@Table(name="user")
public class User {

	@Id // Index primary key를 명시 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key 식별키의 전략 설정 
	private Long id;
	
	private String account;
	
	private String password;
	
	private String status;
	
	private String email;
	
	private String phoneNumber;
	
	private LocalDateTime registeredAt;

	private LocalDateTime unregisteredAt;
	
	private LocalDateTime createdAt;
	
	private String createdBy;
	
	private LocalDateTime updatedAt;
	
	private String updatedBy;
	
/*
	// 연관관계 설정 -> User : OrderDetail -> 1 : N 
	// mappedBy = "user" 에서 user는 OrderDetail.java 의 변수명인 user 와 동일해야 함 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<OrderDetail> orderDetailList;
*/
	
}
