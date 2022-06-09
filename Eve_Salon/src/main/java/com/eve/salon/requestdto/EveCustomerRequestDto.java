package com.eve.salon.requestdto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EveCustomerRequestDto {
	
	@Size(min=3 , max = 50)
	@NotNull(message = "Customername cannot be null")
	@NotEmpty(message= "Customername cannot be empty")
	private String eveCustomerName;
	@NotNull(message = "provide mobile no ,only digits")
	@Pattern(regexp = "[0-9]{10}", message = "provide valid mobile no")
	private String eveCustomerPhone;
	@NotNull(message = "provide mobile no ,only digits")
	@Pattern(regexp = "[0-9]{10}", message = "provide valid mobile no")
	private String eveCustomerAlternatePhone;
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
