package model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Service;

class ServiceTests {

	Service service = new Service("LNU", "Workshop", "A workshop to help students", 12.09f);
	
	@Test
	void testCompanyGetter() {
		assertEquals("LNU", service.getCompany());
	}
	
	@Test
	void testCompanySetter() {
		service.setCompany("LNU2");
		assertEquals("LNU2", service.getCompany());
	}
	
	@Test
	void testTitleGetter() {
		assertEquals("Workshop", service.getTitle());
	}

	@Test
	void testTitleSetter() {
		service.setTitle("Workshop2");
		assertEquals("Workshop2", service.getTitle());
	}
	
	@Test
	void testDescGetter() {
		assertEquals("A workshop to help students", service.getDescription());
	}

	@Test
	void testDescSetter() {
		service.setDescription("Test");
		assertEquals("Test", service.getDescription());
	}
	
	@Test
	void testPriceGetter() {
		assertEquals(12.09f, service.getPrice());
	}

	@Test
	void testPriceSetter() {
		service.setPrice(12.04f);
		assertEquals(12.04f, service.getPrice());
	}
	
}
