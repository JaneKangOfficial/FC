package com.example.study.controller.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;

@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface{

	@Override
	@PostMapping("")	// /api/user
	public Header create() {
		return null;
	}

	@Override
	@GetMapping("{id}")	//	/api/user/{id}
	public Header read(@PathVariable(name="id") Long id) {
		return null;
	}

	@Override
	@PutMapping("")		// /api/user
	public Header update() {
		return null;
	}

	@Override
	@DeleteMapping("{id}")	// /api/user/{id}
	public Header delete(@PathVariable Long id) {
		return null;
	}


	
	
}
