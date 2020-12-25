package com.example.study.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.model.SearchParam;

@RestController
@RequestMapping("/api")
public class PostController {

	// HTML <Form>
	// ajax 검색
	// http post body -> data 
	// json, xml, multipart-form / text-plain
	
//	@PostMapping(value = "/postMethod", produces = {"application-json"})
	@PostMapping("/postMethod")
//	public String postMethod(@RequestBody SearchParam searchParam) {
//		return "OK";
	public SearchParam postMethod(@RequestBody SearchParam searchParam) {
		return searchParam;
	}
	
	@PutMapping("/putMethod")
	public void put() {
	}
	
	@PatchMapping("/patchMethod")
	public void patch() {
	} 
}
