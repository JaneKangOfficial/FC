package com.example.study.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;

@WebAppConfiguration
@RunWith(SpringRunner.class)
public class CategoryRepositoryTest extends StudyApplicationTests{

	@Autowired
	private CategoryRepository categoryRepository;
	
//	@Test
	public void create() {
		String type = "COMPUTER";
		String title = "컴퓨터";
		LocalDateTime createdAt = LocalDateTime.now();
		String createdBy = "AdminServer";
		
		Category category = new Category();
		category.setType(type);
		category.setTitle(title);
		category.setCreatedAt(createdAt);
		category.setCreatedBy(createdBy);
		
		Category newCategory = categoryRepository.save(category);
		
		Assert.assertNotNull(newCategory);
		Assert.assertEquals(newCategory.getType(), type);
		Assert.assertEquals(newCategory.getTitle(), title);
		
	}
	
	@Test
	public void read() {
//		Optional<Category> optionalCategory = categoryRepository.findById(1L);
		
		String type = "COMPUTER";
		Optional<Category> optionalCategory = categoryRepository.findByType(type);
		
		// SELECT * FROM category WHERE type = 'COMPUTER' 
		
		optionalCategory.ifPresent(c -> {
			
			Assert.assertEquals(c.getType(), type);
			System.out.println(c.getId());
			System.out.println(c.getType());
			System.out.println(c.getTitle());
		});
	}
	
	
}
