package com.example.study.model.network.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderInfoApiResponse {
	
	private UserApiResponse userApiResponse;
	
//	private List<OrderGroupApiResponse> orderGroupApiResponseList;	 // UserApiResponse 에 작성 
	
//	private List<ItemApiResponse> itemApiResponseList;				//  OrderGroupApiResponse에 작성 

}
