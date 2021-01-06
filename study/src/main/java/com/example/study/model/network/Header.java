package com.example.study.model.network;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

	// api 통신 시간
//	@JsonProperty("transaction_time") -> Response 출력 이름 변경이 가능, 하나씩 변경할 수 없으므로 application.properties에서 설정 
	private LocalDateTime transactionTime;	// 프론트와 통신할 때는 LocalDateTime 말고 String을 사용 
	
	// api 응답 코드
	private String resultCode;
	
	// api 부가 설명 
	private String description;
	
	private T data;
	
	private Pagination pagination;
	
	
	// OK
	public static <T> Header<T> OK() {
		return (Header<T>) Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("OK")
				.description("OK")
				.build();
	}
	
	
	// DATA OK
	public static <T> Header<T> OK(T data) {
		return (Header<T>) Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("OK")
				.description("OK")
				.data(data)
				.build();
	}
	
	
	// DATA, PAGINATION OK
	public static <T> Header<T> OK(T data, Pagination pagination) {
		return (Header<T>) Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("OK")
				.description("OK")
				.data(data)
				.pagination(pagination)
				.build();
	}
	
	
	// ERROR
	public static <T> Header<T> ERROR(String description) {
		return (Header<T>) Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("ERROR")
				.description(description)
				.build();
	}
	
	
}
