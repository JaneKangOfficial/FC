package com.example.study.model.entity;

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
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.study.model.enumclass.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

// import 안되고 error 나오면 프로젝트명에서 오른쪽 클릭 -> gradle -> gradle refresh 눌러서 build.gradle에 적어둔 라이브러리 다운
// DB와 매칭

@Data
@AllArgsConstructor
@NoArgsConstructor // 기본생성자 
@Entity // == table
//@Table(name="user")
@ToString(exclude = {"orderGroup"})
@EntityListeners(AuditingEntityListener.class)	// 이 감시자에 의해 @CreatedBy, @LastModifiedBy가 LoginUserAuditorAware.java의 설정값으로 설정됨
@Builder	// 생성자 만들지 않고 객체 생성 -> UserRepositoryTest.java create()
@Accessors(chain = true)	// 생성자 만들지 않고 객체 수정 -> UserRepositoryTest.java read()
public class User {

	@Id // Index primary key를 명시 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key 식별키의 전략 설정 
	private Long id;
	
	private String account;
	
	private String password;
	
	@Enumerated(EnumType.STRING)	// request, response, service 도 수정하기 
	private UserStatus status;		// REGISTERED / UNREGISTERED / WAITING /
	
	private String email;
	
	private String phoneNumber;
	
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
	
/*
	// 연관관계 설정 -> User : OrderDetail -> 1 : N 
	// mappedBy = "user" 에서 user는 OrderDetail.java 의 변수명인 user 와 동일해야 함 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<OrderDetail> orderDetailList;
*/
	
	// User : OrderGroup -> 1 : N 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<OrderGroup> orderGroupList;
}
