package com.example.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.study.ifs.CrudInterface;

@Service
@Component	// @Autowired 를 받기 위해 @Component 지정해줌 
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res>{

	@Autowired(required = false)	// 필수는 아니다 -> false
	protected JpaRepository<Entity, Long> baseRepository;
	
	
}
