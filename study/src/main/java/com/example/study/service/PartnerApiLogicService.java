package com.example.study.service;

import org.springframework.stereotype.Service;

import com.example.study.model.entity.Partner;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.PartnerApiRequest;
import com.example.study.model.network.response.PartnerApiResponse;

@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner>{

	@Override
	public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Header<PartnerApiResponse> read(Long id) {
		return baseRepository.findById(id)
				.map(partner -> response(partner))
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Header delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private Header<PartnerApiResponse> response(Partner partner) {
		
		PartnerApiResponse body = PartnerApiResponse.builder()
				.id(partner.getId())
				.name(partner.getName())
				.status(partner.getStatus())
				.address(partner.getAddress())
				.callConter(partner.getCallCenter())
				.partnerNumber(partner.getPartnerNumber())
				.businessNumber(partner.getBusinessNumber())
				.ceoName(partner.getCeoName())
				.registeredAt(partner.getRegisteredAt())
				.unregisteredAt(partner.getUnregisteredAt())
				.categoryId(partner.getCategory().getId())
				.build();
				
		return Header.OK(body);
				
	}
	
}
