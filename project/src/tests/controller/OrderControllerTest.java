package tests.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.OrderController;

class OrderControllerTest {
	
	OrderController oc = new OrderController();

	@Test
	void createOrderSuccessful() {
		assertEquals(oc.newOrder(1, 1, "May 24", 1, "Saab", 300), "Order saved successfully!");
	}
	
	@Test
	void createOrderFailDueToEmptyCompanyName() {
		assertEquals(oc.newOrder(1, 1, "May 24", 1, "", 300), "ops, something went wrong!");
	}
	
	@Test
	void createOrderFailDueToMulitpleErrors() {
		assertEquals(oc.newOrder(1, 1, "", 1, "", 0), "ops, something went wrong!"); // Maybe return a array with all the errors?
	}
	
	@Test
	void editOrderSuccessful() {
		assertEquals(oc.editOrder(1, 1, 1, 300), "Order Edited!");
	}
	
}
