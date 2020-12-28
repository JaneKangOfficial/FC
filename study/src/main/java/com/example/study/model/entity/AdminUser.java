package com.example.study.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)	// 이 감시자에 의해 @CreatedBy, @LastModifiedBy가 LoginUserAuditorAware.java의 설정값으로 설정됨 
@Builder	// 생성자 만들지 않고 객체 생성 -> AdminUserRepositoryTest.java create()
@Accessors(chain = true)	// 생성자 만들지 않고 객체 수정 -> AdminUserRepositoryTest.java read()
public class AdminUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String account;
	
	private String password;
	
	private String status;
	
	private String role;
	
	private LocalDateTime lastLoginAt;
	
	private LocalDateTime passwordUpdatedAt;
	
	private Integer loginFailCount;
	
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
}
