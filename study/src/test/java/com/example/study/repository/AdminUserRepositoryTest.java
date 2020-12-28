package com.example.study.repository;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.AdminUser;

public class AdminUserRepositoryTest extends StudyApplicationTests{
	
	@Autowired
	private AdminUserRepository adminUserRepository;
	
	@Test
	public void create() {
		AdminUser adminUser = new AdminUser();
		adminUser.setAccount("AdminUser03");
		adminUser.setPassword("AdminUser03");
		adminUser.setStatus("REGISTERD");
		adminUser.setRole("PARTNER");
//		adminUser.setCreatedAt(LocalDateTime.now());
//		adminUser.setCreatedBy("AdminServer");
		
		AdminUser newAdminUser = adminUserRepository.save(adminUser);
		Assert.assertNotNull(newAdminUser);
		
		newAdminUser.setAccount("CHANGE");
		adminUserRepository.save(newAdminUser);
	}

}
