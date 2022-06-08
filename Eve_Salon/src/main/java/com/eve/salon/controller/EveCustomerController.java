package com.eve.salon.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eve.salon.entity.EveCustomerInformation;
import com.eve.salon.requestdto.EveCustomerRequestDto;
import com.eve.salon.responsedto.EveCustomerResponseDto;
import com.eve.salon.service.EveCustomerService;

@RestController
@RequestMapping("/eveCustomer")
public class EveCustomerController {

	@Autowired
	EveCustomerService eveCustomerService;

	@PostMapping("/addCustomer")
	public ResponseEntity<EveCustomerResponseDto> addCustomer(
			@RequestBody EveCustomerRequestDto eveCustomerRequestDto) {
		EveCustomerResponseDto eveCustomerResponseDto = new EveCustomerResponseDto();
		EveCustomerInformation eveCustomerInformation = eveCustomerService.addCustomer(eveCustomerRequestDto);
		BeanUtils.copyProperties(eveCustomerResponseDto, eveCustomerInformation);
		return new ResponseEntity<EveCustomerResponseDto>(eveCustomerResponseDto, HttpStatus.OK);

	}

}
