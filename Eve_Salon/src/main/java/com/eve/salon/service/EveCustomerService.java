package com.eve.salon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
		BeanUtils.copyProperties(customerRequestDto, eveCustomerInformation);
		eveCustomerInformation.setEveCustomerBirthday(LocalDate.parse(customerRequestDto.getEveCustomerBirthday()));
		eveCustomerInformation
				.setEveCustomerAnniversary(LocalDate.parse(customerRequestDto.getEveCustomerAnniversary()));
		eveCustomerInformation = eveCustomerRepository.save(eveCustomerInformation);
		return eveCustomerInformation;

	}

	public EveCustomerInformation updateCustomer(Integer id, EveCustomerRequestDto evecustRequestDto) {
		EveCustomerInformation evecustomer = new EveCustomerInformation();
		Optional<EveCustomerInformation> optCustomer = eveCustomerRepository.findById(id);
		if (optCustomer.isPresent()) {
			evecustomer = optCustomer.get();
		}
		BeanUtils.copyProperties(evecustRequestDto, evecustomer);
		evecustomer.setEveCustomerBirthday(LocalDate.parse(evecustRequestDto.getEveCustomerBirthday()));
		evecustomer.setEveCustomerAnniversary(LocalDate.parse(evecustRequestDto.getEveCustomerAnniversary()));
		return evecustomer = eveCustomerRepository.save(evecustomer);
	}

	public void deleteCustomerById(Integer id) {
		eveCustomerRepository.deleteById(id);
	}

	public List<EveCustomerInformation> fetchCustomersList() {
		return eveCustomerRepository.findAll();
	}

}
