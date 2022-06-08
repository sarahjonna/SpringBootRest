package com.eve.salon.requestdto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EveCustomerRequestDto {
	
	private String eveCustomerName;
	private Long eveCustomerPhone;
	private Long eveCustomerAlternatePhone;
	private String eveCustomerEmail;
	private String eveCustomerGender;
	private String eveCustomerGroup;
	private String eveCustomerBirthday;
	private String eveCustomerAnniversary;
	private String eveCustomerNotes;

}
