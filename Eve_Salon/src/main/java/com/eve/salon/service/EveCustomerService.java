package com.eve.salon.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eve.salon.entity.EveCustomerInformation;
import com.eve.salon.entity.exceptions.CustomerAlreayExists;
import com.eve.salon.entity.exceptions.CustomerNotFoundException;
import com.eve.salon.repository.EveCustomerRepository;
import com.eve.salon.requestdto.EveCustomerRequestDto;
import com.eve.salon.responsedto.EveCustomerResponseDto;

@Service
public class EveCustomerService {

	@Autowired
	EveCustomerRepository eveCustomerRepository;

	public EveCustomerInformation addCustomer(EveCustomerRequestDto customerRequestDto) throws CustomerAlreayExists {
		EveCustomerInformation eveCustomerInformation = new EveCustomerInformation();
		Optional<EveCustomerInformation> optcustomer = eveCustomerRepository.findByEveCustomerPhoneOrEveCustomerEmail(
				customerRequestDto.getEveCustomerPhone(), customerRequestDto.getEveCustomerEmail());
		if (optcustomer.isPresent()) {

			throw new CustomerAlreayExists("Customer Already Exists");
		}

		BeanUtils.copyProperties(customerRequestDto, eveCustomerInformation);
		eveCustomerInformation.setEveCustomerBirthday(LocalDate.parse(customerRequestDto.getEveCustomerBirthday()));
		eveCustomerInformation
				.setEveCustomerAnniversary(LocalDate.parse(customerRequestDto.getEveCustomerAnniversary()));
		eveCustomerInformation = eveCustomerRepository.save(eveCustomerInformation);
		return eveCustomerInformation;

	}

	public EveCustomerInformation updateCustomer(Integer id, EveCustomerRequestDto evecustRequestDto)
			throws CustomerNotFoundException, CustomerAlreayExists {
		EveCustomerInformation eveCustomerInformation = new EveCustomerInformation();
		Optional<EveCustomerInformation> optCustomer = eveCustomerRepository.findById(id);
		if (optCustomer.isPresent()) {
			eveCustomerInformation = optCustomer.get();
		} else {
			throw new CustomerNotFoundException("Customer not found");
		}
		Optional<EveCustomerInformation> optcustomer = eveCustomerRepository.findByEveCustomerPhoneOrEveCustomerEmail(
				evecustRequestDto.getEveCustomerPhone(), evecustRequestDto.getEveCustomerEmail());
		if (optcustomer.isPresent()) {
			throw new CustomerAlreayExists("Customer Already Exists");
		}
		BeanUtils.copyProperties(evecustRequestDto, eveCustomerInformation);
		eveCustomerInformation.setEveCustomerBirthday(LocalDate.parse(evecustRequestDto.getEveCustomerBirthday()));
		eveCustomerInformation
				.setEveCustomerAnniversary(LocalDate.parse(evecustRequestDto.getEveCustomerAnniversary()));
		return eveCustomerInformation = eveCustomerRepository.save(eveCustomerInformation);
	}

	public void deleteCustomerById(Integer eveCustomerId) throws CustomerNotFoundException {
		EveCustomerInformation eveCustomerInformation = new EveCustomerInformation();
		Optional<EveCustomerInformation> optCustomerId = eveCustomerRepository.findById(eveCustomerId);
		if (!optCustomerId.isPresent()) {

			throw new CustomerNotFoundException("Customer doesnt exists");
		} else {
			eveCustomerRepository.deleteById(eveCustomerId);
		}
	}

	public List<EveCustomerResponseDto> fetchCustomersList() {
		List<EveCustomerResponseDto> eveCustomerResponseDtoList = new ArrayList<EveCustomerResponseDto>();
		List<EveCustomerInformation> eveCustomerInformationList = eveCustomerRepository.findAll();
		for (EveCustomerInformation eveCustInfo : eveCustomerInformationList) {
			EveCustomerResponseDto eveCustomerResponseDto = new EveCustomerResponseDto();
			BeanUtils.copyProperties(eveCustInfo, eveCustomerResponseDto);
			eveCustomerResponseDtoList.add(eveCustomerResponseDto);
		}
		return eveCustomerResponseDtoList;
	}

}
