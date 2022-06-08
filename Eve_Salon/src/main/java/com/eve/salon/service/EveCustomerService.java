package com.eve.salon.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eve.salon.entity.EveCustomerInformation;
import com.eve.salon.repository.EveCustomerRepository;
import com.eve.salon.requestdto.EveCustomerRequestDto;

@Service
public class EveCustomerService {

	@Autowired
	EveCustomerRepository eveCustomerRepository;

	public EveCustomerInformation addCustomer(EveCustomerRequestDto customerRequestDto) {
		EveCustomerInformation eveCustomerInformation = new EveCustomerInformation();
		BeanUtils.copyProperties(eveCustomerInformation, customerRequestDto);
		eveCustomerInformation = eveCustomerRepository.save(eveCustomerInformation);
		return eveCustomerInformation;

	}

}
