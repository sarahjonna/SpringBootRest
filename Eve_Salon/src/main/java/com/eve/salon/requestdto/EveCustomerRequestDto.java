package com.eve.salon.requestdto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EveCustomerRequestDto {
	
	private String eveCustomerName;
	private Integer eveCustomerPhone;
	private Integer eveCustomerAlternatePhone;
	private String eveCustomerEmail;
	private String eveCustomerGender;
	private String eveCustomerGroup;
	private LocalDate eveCustomerBirthday;
	private LocalDate eveCustomerAnniversary;
	private String eveCustomerNotes;

}
