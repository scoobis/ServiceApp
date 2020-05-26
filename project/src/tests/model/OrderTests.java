package tests.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Order;

class OrderTests {

	//TODO NEEDS FIXING
	double price = 300;
	Order order = new Order(1, 1, "testString", 1, "company", price, false);
	@Test
	void testCustomerIdGetter() {
		assertEquals(1, order.getCustomerId());
	}
	
	@Test
	void testCustomerIdSetter() {
		order.setCustomerId(2);
		assertEquals(2, order.getCustomerId());
	}
	
	@Test
	void testServiceIdGetter() {
		assertEquals(1, order.getServiceId());
	}
	
	@Test
	void testServiceIdSetter() {
		order.setServiceId(2);
		assertEquals(2, order.getServiceId());
	}
	
	@Test
	void testDateGetter() {
		assertEquals("testString", order.getDate());
	}
	
	@Test
	void testDateSetter() {
		order.setDate("testString2");
		assertEquals("testString2", order.getDate());
	}
	
	@Test
	void testShopIdGetter() {
		assertEquals(1, order.getShopId());
	}
	
	@Test
	void testShopIdSetter() {
		order.setcompanyName("test");
		assertEquals("test", order.getcompanyName());
	}
	
	@Test
	void testCompanyIdGetter() {
		assertEquals("test", order.getcompanyName());
	}
	
	@Test
	void testPriceGetter() {
		assertEquals(300, order.getPrice());
	}
	
	@Test
	void testPriceSetter() {
		order.setPrice(100);
		assertEquals(100, order.getPrice());
	}
	
	@Test
	void testIsCompleted() {
		order.setCompleted(true);
		assertTrue(order.getCompleted());
	}
	

}
