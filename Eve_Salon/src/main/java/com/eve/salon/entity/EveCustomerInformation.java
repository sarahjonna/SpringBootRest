package com.eve.salon.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "eve_customer_information")
public class EveCustomerInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eve_customer_id")
	private Integer eveCustomerId;
	@Column(name = "eve_customer_name")
	private String eveCustomerName;
	@Column(name = "eve_customer_phone")
	private String eveCustomerPhone;
	@Column(name = "eve_customer_alternate_phone")
	private String eveCustomerAlternatePhone;
	@Column(name = "eve_customer_email")
	private String eveCustomerEmail;
	@Column(name = "eve_customer_gender")
	private String eveCustomerGender;
	@Column(name = "eve_customer_group")
	private String eveCustomerGroup;
	@Column(name = "eve_customer_birthday")
	private LocalDate eveCustomerBirthday;
	@Column(name = "eve_customer_anniversary")
	private LocalDate eveCustomerAnniversary;
	@Column(name = "eve_customer_notes")
	private String eveCustomerNotes;
}
