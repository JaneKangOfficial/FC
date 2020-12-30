package com.example.study.controller.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;

@RestController
@RequestMapping("/api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse>{

	@Override
	@PostMapping("")		// /api/item
	public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
		return null;
	}

	@Override
	@GetMapping("{id]")		// /api/item/{id}
	public Header<ItemApiResponse> read(@PathVariable Long id) {
		return null;
	}

	@Override
	@PutMapping("")			// /api/item
	public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
		return null;
	}

	@Override
	@DeleteMapping("{id}")		// /api/item/{id}
	public Header delete(@PathVariable Long id) {
		return null;
	}

}
