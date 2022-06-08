package com.eve.salon.controller;

import java.util.List;

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
		BeanUtils.copyProperties(eveCustomerInformation, eveCustomerResponseDto);
		return new ResponseEntity<EveCustomerResponseDto>(eveCustomerResponseDto, HttpStatus.OK);
	}

	@PutMapping("/updateCustomer/{id}")
	public EveCustomerInformation updateCustomer(@PathVariable Integer id,
			@RequestBody EveCustomerRequestDto evecustRequestDto) {
		return eveCustomerService.updateCustomer(id, evecustRequestDto);
	}

	@DeleteMapping("/deleteCustomer/{eveCustomerId}")
	private String deleteCustomerById(@PathVariable("eveCustomerId") int eveCustomerId) {
		eveCustomerService.deleteCustomerById(eveCustomerId);
		return "DELETED CUSTOMER";
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<EveCustomerInformation>> getAllCustomers() {
		List<EveCustomerInformation> eveCustomerInformation = eveCustomerService.fetchCustomersList();
		return new ResponseEntity<List<EveCustomerInformation>>(eveCustomerInformation, HttpStatus.OK);

	}

}
