package com.eve.salon.responsedto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EveCustomerResponseDto {
	
	private Integer eveCustomerId;
	private String eveCustomerName;
	private Long eveCustomerPhone;
	private Long eveCustomerAlternatePhone;
	private String eveCustomerEmail;
	private String eveCustomerGender;
	private String eveCustomerGroup;
	private LocalDate eveCustomerBirthday;
	private LocalDate eveCustomerAnniversary;
	private String eveCustomerNotes;

}
