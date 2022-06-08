package com.eve.salon.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eve.salon.entity.EveCustomerInformation;
import com.eve.salon.repository.EveCustomerRepository;
import com.eve.salon.requestdto.EveCustomerRequestDto;
import com.eve.salon.responsedto.EveCustomerResponseDto;

@Service
public class EveCustomerService {

	@Autowired
	EveCustomerRepository eveCustomerRepository;

	public EveCustomerInformation addCustomer(EveCustomerRequestDto customerRequestDto) {
		EveCustomerInformation eveCustomerInformation = new EveCustomerInformation();
		BeanUtils.copyProperties(customerRequestDto, eveCustomerInformation);
		eveCustomerInformation.setEveCustomerBirthday(LocalDate.parse(customerRequestDto.getEveCustomerBirthday()));
		eveCustomerInformation
				.setEveCustomerAnniversary(LocalDate.parse(customerRequestDto.getEveCustomerAnniversary()));
		eveCustomerInformation = eveCustomerRepository.save(eveCustomerInformation);
		return eveCustomerInformation;

	}

	public EveCustomerInformation updateCustomer(Integer id, EveCustomerRequestDto evecustRequestDto) {
		EveCustomerInformation eveCustomerInformation = new EveCustomerInformation();
		Optional<EveCustomerInformation> optCustomer = eveCustomerRepository.findById(id);
		if (optCustomer.isPresent()) {
			eveCustomerInformation = optCustomer.get();
		}
		BeanUtils.copyProperties(evecustRequestDto, eveCustomerInformation);
		eveCustomerInformation.setEveCustomerBirthday(LocalDate.parse(evecustRequestDto.getEveCustomerBirthday()));
		eveCustomerInformation.setEveCustomerAnniversary(LocalDate.parse(evecustRequestDto.getEveCustomerAnniversary()));
		return eveCustomerInformation = eveCustomerRepository.save(eveCustomerInformation);
	}

	public void deleteCustomerById(Integer eveCustomerId) {
		eveCustomerRepository.deleteById(eveCustomerId);
	}
    
	
	public List<EveCustomerResponseDto> fetchCustomersList() {
		List<EveCustomerResponseDto> eveCustomerResponseDtoList = new ArrayList<EveCustomerResponseDto>();
		List<EveCustomerInformation> eveCustomerInformationList=eveCustomerRepository.findAll();
		for(EveCustomerInformation eveCustInfo:eveCustomerInformationList) {
			EveCustomerResponseDto eveCustomerResponseDto = new EveCustomerResponseDto();
			BeanUtils.copyProperties( eveCustInfo,eveCustomerResponseDto);
			eveCustomerResponseDtoList.add(eveCustomerResponseDto);
		}
		return eveCustomerResponseDtoList;
	}

}
