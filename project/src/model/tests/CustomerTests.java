package model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Customer;

class CustomerTests {

	Customer customer = new Customer("customer@email.se", "01234567", "Customer", "Customer street 1", true);
	
	@Test
	void testEmailGetter() {
		assertEquals("customer@email.se", customer.getEmail());
	}
	
	@Test
	void testEmailSetter() {
		customer.setPhone("customer2@email.se");
		assertEquals("customer2@email.se", customer.getEmail());
	}
	
	@Test
	void testPhoneGetter() {
		assertEquals("01234567", customer.getPhone());
	}
	
	@Test
	void testPhoneSetter() {
		customer.setPhone("123");
		assertEquals("123", customer.getPhone());
	}
	
	@Test
	void testNameGetter() {
		assertEquals("Customer", customer.getName());
	}
	
	@Test
	void testNameSetter() {
		customer.setPhone("Test");
		assertEquals("Test", customer.getName());
	}
	
	@Test
	void testAddressGetter() {
		assertEquals("Customer street 1", customer.getAddress());
	}
	
	@Test
	void testAddressSetter() {
		customer.setAddress("Customer street 2");
		assertEquals("Customer street 2", customer.getAddress());
	}
	
	@Test
	void testIsActive() {
		customer.setActive(false);
		assertFalse(customer.isActive());
	}
	

}
