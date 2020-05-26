package tests.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Admin;

class AdminTests {
	
	// Use mockito to generate a password since the password hasher is already tested.

	Admin admin = new Admin("0760721684", "je223gs@student.lnu.se", "Jesper", "LNU", 1, "password");
	
	@Test
	void testPhoneGetter() {
		assertEquals("0760721684", admin.getPhone());
	}
	
	@Test
	void testPhoneSetter() {
		admin.setPhone("123");
		assertEquals("123", admin.getPhone());
	}
	
	@Test
	void testEmailGetter() {
		assertEquals("je223gs@student.lnu.se", admin.getEmail());
	}
	
	@Test
	void testEmailSetter() {
		System.out.println(admin.getEmail());
		admin.setEmail("test@email.com");
		System.out.println(admin.getEmail());
		assertEquals("test@email.com", admin.getEmail());
	}
	
	@Test
	void testCompanyGetter() {
		assertEquals("LNU", admin.getCompanyName());
	}
	
	@Test
	void testCompanySetter() {
		admin.setCompanyName("Linné");
		assertEquals("Linné", admin.getCompanyName());
	}
	
	@Test
	void testShopIdGetter() {
		assertEquals(1, admin.getShopId());
	}
	
	@Test
	void testShopIdSetter() {
		admin.setShopId(2);
		assertEquals(2, admin.getShopId());
	}
	
	@Test
	void testPasswordGetter() {
		assertEquals("password", admin.getPassword());
	}
	
	@Test
	void testPasswordSetter() {
		admin.setPassword("password1");
		assertEquals("password1", admin.getPassword());
	}

}
