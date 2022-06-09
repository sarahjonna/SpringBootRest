package com.eve.salon.requestdto;



import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EveCustomerRequestDto {
	@Size(min=3 , max = 50)
	@NotNull(message = "Customername cannot be null")
	@NotEmpty(message= "Customername cannot be empty")
	private String eveCustomerName;
	@Min(value = 10)
	@Max(value = 10)
	private Long eveCustomerPhone;
	@Min(value = 10)
	@Max(value = 10)
	private Long eveCustomerAlternatePhone;
	@Email
	@NotEmpty(message= "CustomerEmail cannot be empty")
	private String eveCustomerEmail;
	@NotNull(message= "CustomerGender cannot be null")
	@NotEmpty(message= "CustomerGender cannot be empty")
	private String eveCustomerGender;
	@NotNull(message= "CustomerGroup cannot be null")
	@NotEmpty(message= "CustomerGroup cannot be empty")
	private String eveCustomerGroup;
	@NotNull(message= "CustomerBirthday cannot be null")
	@NotEmpty(message= "CustomerBirthday cannot be empty")
	private String eveCustomerBirthday;
	@NotNull(message= "CustomerAnniversary cannot be null")
	@NotEmpty(message= "CustomerAnniversary cannot be empty")
	private String eveCustomerAnniversary;
	@NotNull(message= "CustomerCustomerNotes cannot be null")
	@NotEmpty(message= "CustomerCustomerNotes cannot be empty")
	private String eveCustomerNotes;

}
