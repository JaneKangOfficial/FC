package com.example.study.model.network.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailApiResponse {

	private Long id;
	
	private String status;
	
	private LocalDateTime arrivalDate;
	
	private int quantity;
	
	private BigDecimal totalPrice;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private Long orderGroupId;
	
	private Long itemId;
	
	
}
