package com.example.study.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;

import lombok.experimental.Accessors;

@WebAppConfiguration
@RunWith(SpringRunner.class)
public class UserRepositoryTest extends StudyApplicationTests{
	
	// Dependency Injection (DI)
	@Autowired
	// = new UserRepository 생성 안해도 자동으로 해줌 , 한번 생성해서 공통으로 사용하는 것이라 @Autowired
	private UserRepository userRepository;
	
	// error 발생 시 : build path -> add library -> junit4
	// no tests found with test runner JUnit5 error 발생 시 : run configurations -> JUnit -> Test -> TestRunner를 Junit4로 변경

/*
//	@Test
	public void create() {
		// String sql = insert into user (%s, %s, %d) value (account, email, age);
		// User 는 매번 값이 다를 수 있기 때문에 @Autowired로 관리하지 않는다 
		User user = new User();
		user.setAccount("TestUser02");
		user.setEmail("TestUser02@gmail.com");
		user.setPhoneNumber("010-1111-2222");
		user.setCreatedAt(LocalDateTime.now());
		user.setCreatedBy("TestUser02");
		
		//userRepository.save(user)에서 error는 userRepository가 null이라서 -> @RunWith(SpringRunner.class)를 써줘야 함 
		User newUser = userRepository.save(user);
		System.out.println("newUser : " + newUser);
	}
*/

//	@Test
	public void create() {
		String account = "Test03";
		String password = "Test03";
		String status = "REGISTERED";
		String email = "Test03@gmail.com";
		String phoneNumber = "010-1111-3333";
		LocalDateTime registeredAt = LocalDateTime.now();
//		LocalDateTime createdAt = LocalDateTime.now();
//		String createdBy = "AdminServer";
		
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		user.setStatus(status);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setRegisteredAt(registeredAt);
//		user.setCreatedAt(createdAt);
//		user.setCreatedBy(createdBy);
		
		// User.java에서 @Builder 이 있으면 생성자 만들지 않고도 생성 가능
		User u = User.builder()
				.account(account)
				.password(password)
				.status(status)
				.email(email)
				.build();
		
		User newUser = userRepository.save(user);
		Assert.assertNotNull(newUser);
	}
		
	
/*	
	@Test
	public User read(@RequestParam Long id) {
		Optional<User> user = userRepository.findById(id);
		
		user.ifPresent(selectUser -> {
			System.out.println("user : " + selectUser);
			System.out.println("email : " + selectUser.getEmail());
		});
		
		return user.get();
	}
*/

	/*		
	@Test
	@Transactional
	public void read() {
		// SELECT * FROM user WHERE id = ? 
//		Optional<User> user = userRepository.findById(7L);
		Optional<User> user = userRepository.findByAccount("TestUser01");
		
		user.ifPresent(selectUser -> {
//			System.out.println("user : " + selectUser);
//			System.out.println("email : " + selectUser.getEmail());
			
			selectUser.getOrderDetailList().stream().forEach(detail -> {
//				System.out.println(detail.getItemId());
				
				// 이제 ItemId뿐만 아니라 연관관계를 이용해 사용자가 어떠한 Item을 갖고 있는지 알수있음
				Item item = detail.getItem();
				System.out.println(item);  
			});
		});
	}
*/
	
	@Test
	@Transactional
	public void read() {
		User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");
		
		/////////////////////////////////////////////////////////////////////////////////
		// User.java에서 @Accessors(chain = true)이 있으면 생성자 만들지 않고도 수정 가능 
		user
			.setEmail("")
			.setPhoneNumber("")
			.setStatus("");
		
		User u = new User().setAccount("").setEmail("").setPassword("");
		/////////////////////////////////////////////////////////////////////////////////
		
		if(user != null) {
			user.getOrderGroupList().stream().forEach(orderGroup -> {
				
				System.out.println("----------------------주문묶음----------------------");
				System.out.println("수령인 : " + orderGroup.getRevName());
				System.out.println("수령지 : " + orderGroup.getRevAddress());
				System.out.println("총금액 : " + orderGroup.getTotalPrice());
				System.out.println("총수량 : " + orderGroup.getTotalQuantity());

				System.out.println("----------------------주문상세----------------------");
				
				orderGroup.getOrderDetailList().forEach(orderDetail -> {
					System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
					System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
					System.out.println("주문 상품 : " + orderDetail.getItem().getName());
					System.out.println("고개센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
					System.out.println("주문의 상태 : " + orderDetail.getStatus());
					System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());
					
				});
			
				System.out.println("----------------------------------------------------");
				
			});
		}
		
		Assert.assertNotNull(user);
	}
	
	
	
	
//	@Test
	public void update() {
		Optional<User> user = userRepository.findById(2L);
		
		user.ifPresent(selectUser -> {
			selectUser.setAccount("PPPP");
			selectUser.setUpdatedAt(LocalDateTime.now());
			selectUser.setUpdatedBy("update method()");
			
			userRepository.save(selectUser);
			
		});
	}
	
	
//	@Test
	@Transactional // 삭제 되지 않고 원래상태로 rollback 시켜줌 
//	@DeleteMapping("/api/user")
//	public void delete(@RequestParam Long id) {
	public void delete() {
		Optional<User> user = userRepository.findById(1L);
		
		// 반드시 데이터가 존재해야 하기 때문에 
		Assert.assertTrue(user.isPresent()); // true
		
		user.ifPresent(selectUser -> {
			userRepository.delete(selectUser);
		});
		
		// 삭제가 됐는지 확인 
		Optional<User> deleteUser = userRepository.findById(1L);
		
		Assert.assertFalse(deleteUser.isPresent()); // false
		
/*
		if(deleteUser.isPresent()) {
			System.out.println("데이터 존재 : " + deleteUser.get());
		}else {
			System.out.println("데이터 삭제, 데이터 없음");
		}
*/
		
		
	}
}
