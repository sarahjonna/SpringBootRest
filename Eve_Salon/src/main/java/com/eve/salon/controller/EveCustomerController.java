package com.eve.salon.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eve.salon.entity.EveCustomerInformation;
import com.eve.salon.entity.exceptions.CustomerAlreayExists;
import com.eve.salon.entity.exceptions.CustomerNotFoundException;
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
			@Valid @RequestBody EveCustomerRequestDto eveCustomerRequestDto) throws CustomerAlreayExists {
		EveCustomerResponseDto eveCustomerResponseDto = new EveCustomerResponseDto();
		EveCustomerInformation eveCustomerInformation = eveCustomerService.addCustomer(eveCustomerRequestDto);
		BeanUtils.copyProperties(eveCustomerInformation, eveCustomerResponseDto);
		return new ResponseEntity<EveCustomerResponseDto>(eveCustomerResponseDto, HttpStatus.OK);
	}

	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<EveCustomerResponseDto> updateCustomer(@PathVariable Integer id,
			@Valid @RequestBody EveCustomerRequestDto evecustRequestDto)
			throws CustomerNotFoundException, CustomerAlreayExists {
		EveCustomerResponseDto eveCustomerResponseDto = new EveCustomerResponseDto();
		EveCustomerInformation eveCustomerInformation = eveCustomerService.updateCustomer(id, evecustRequestDto);
		BeanUtils.copyProperties(eveCustomerInformation, eveCustomerResponseDto);
		return new ResponseEntity<EveCustomerResponseDto>(eveCustomerResponseDto, HttpStatus.OK);

	}

	@DeleteMapping("/deleteCustomer/{eveCustomerId}")
	private ResponseEntity<String> deleteCustomerById(@PathVariable("eveCustomerId") int eveCustomerId)
			throws CustomerNotFoundException {
		eveCustomerService.deleteCustomerById(eveCustomerId);
		return new ResponseEntity<String>("Customer Deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<EveCustomerResponseDto>> getAllCustomers() {
		List<EveCustomerResponseDto> eveCustomerResponseDtoList = eveCustomerService.fetchCustomersList();
		return new ResponseEntity<List<EveCustomerResponseDto>>(eveCustomerResponseDtoList, HttpStatus.OK);

	}

}
