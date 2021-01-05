package com.example.study.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.Item;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;

@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

/*
	@Autowired
	private ItemApiLogicService itemApilogicService;

	@PostConstruct
	public void init() {
		this.baseService = itemApilogicService;
	}
*/
}


/*
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse>{

	@Autowired
	private ItemApiLogicService itemApiLogicService;
	
	
	@Override
	@PostMapping("")		// /api/item
	public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
		return itemApiLogicService.create(request);
	}

	@Override
	@GetMapping("{id}")		// /api/item/{id}
	public Header<ItemApiResponse> read(@PathVariable Long id) {
		return itemApiLogicService.read(id);
	}

	@Override
	@PutMapping("")			// /api/item
	public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
		return itemApiLogicService.update(request);
	}

	@Override
	@DeleteMapping("{id}")		// /api/item/{id}
	public Header delete(@PathVariable Long id) {
		return itemApiLogicService.delete(id);
	}
}
 */
