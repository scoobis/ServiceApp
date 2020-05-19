package test.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.ServiceController;

class InputValidatorUnitTest {
	
	ServiceController serviceController;
	
	String companyName;
	String title;
	String description;
	int price;
	
	public void init() {
		serviceController = new ServiceController();
		
		companyName = "company";
		title = "title";
		description = "description";
		price = 55;
	}
	
	@Test
	void testingNewServiceValidInput() {
		
		this.init();
		
		String result = serviceController.newService(companyName, title, description, price);
		
		assertEquals("Service saved successfully", result);
	}
	
	@Test
	void testingNewServiceNoCompanyName() {
		
		this.init();
		
		companyName = "";
		
		String result = serviceController.newService(companyName, title, description, price);
		
		assertEquals("ops, something went wrong!", result);
	}
	
	@Test
	void testingNewServiceNoTitle() {
		
		this.init();
		
		title = "";
		
		String result = serviceController.newService(companyName, title, description, price);
		
		assertEquals("Title is missing!", result);
	}
	
	@Test
	void testingNewServiceNoDescription() {
		this.init();
		
		description = "";
		
		String result = serviceController.newService(companyName, title, description, price);
		
		assertEquals("Description is missing!", result);
	}
	
	@Test
	void testingNewServiceNoPrice() {
		
		this.init();
		
		price = -1;
		
		String result = serviceController.newService(companyName, title, description, price);
		
		assertEquals("Price is missing!", result);
	}

}
