package com.ing.kristoffer.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.ing.kristoffer.model.Classification;
import com.ing.kristoffer.model.Customer;

public class INGControllerIT {

	private RestTemplate restTemplate;
	private String url;

	@Before
	public void setup() throws Exception {
		restTemplate = new RestTemplate();
		url = "http://localhost:8080/";
	}

	@Test
	public void getCustomer() {
		String yearMonthString = "2018-05";
		String customerID = "12345";
		Customer customer = this.restTemplate.getForObject(url + "customer/" + customerID + "/" + yearMonthString,
				Customer.class);
		assertEquals(Classification.MorningPerson, customer.getClassification());
/*
		customerID = "12346";
		customer = this.restTemplate.getForObject(url + "customer/" + customerID + "/" + yearMonthString,
				Customer.class);
		assertEquals(Classification.AfternoonPerson, customer.getClassification());

		customerID = "12347";
		customer = this.restTemplate.getForObject(url + "customer/" + customerID + "/" + yearMonthString,
				Customer.class);
		assertEquals(Classification.BigSpender, customer.getClassification());

		customerID = "12348";
		customer = this.restTemplate.getForObject(url + "customer/" + customerID + "/" + yearMonthString,
				Customer.class);
		assertEquals(Classification.PotentialSaver, customer.getClassification());

		customerID = "12349";
		customer = this.restTemplate.getForObject(url + "customer/" + customerID + "/" + yearMonthString,
				Customer.class);
		assertEquals(Classification.BigTicketSpender, customer.getClassification());
*/
		customerID = "12350";
		customer = this.restTemplate.getForObject(url + "customer/" + customerID + "/" + yearMonthString,
				Customer.class);
		assertEquals(Classification.FastSpender, customer.getClassification());

		customerID = "12351";
		customer = this.restTemplate.getForObject(url + "customer/" + customerID + "/" + yearMonthString,
				Customer.class);
		assertEquals(Classification.PotentialLoan, customer.getClassification());

	}

}