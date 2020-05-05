package model.database.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Customer;
import model.database.CustomerDatabase;

class CustomerDatabaseUnitTests {

	CustomerDatabase db = new CustomerDatabase();
	
	@Test
	void mainTest() throws InvalidKeySpecException, SQLException, NoSuchAlgorithmException {
		Customer c = new Customer();
		c.setActive(true);
		c.setAddress("Råstensvägen 2");
		c.setEmail("je223gs@lnu.se");
		c.setName("Jesper Eriksson");
		c.setPhone("1234457");
		
		
		Customer b = new Customer();
		b.setActive(true);
		b.setAddress("Råstensvägen 3");
		b.setEmail("je223gs@lnu.se2");
		b.setName("Jesper Eriksson2");
		b.setPhone("12344572");
		
		db.saveCustomer(c);
		ArrayList<Customer> customers = db.getAllCustomers();
		
		// Checking that the data in the database is correct to what we entered.
		assertEquals("Jesper Eriksson", customers.get(0).getName());
		
		db.editCustomer("10", b);
		
		ArrayList<Customer> customers2 = db.getAllCustomers();
		assertEquals("Jesper Eriksson2", customers2.get(0).getName());
		
		db.deleteCustomer("10");
	}
	
	// @Test
	/* void checkThatGetCustomerByIdReturnsACustomer(Customer c) throws InvalidKeySpecException, SQLException {
		db.saveCustomer(c);
		Customer dummy = new Customer();
		assertEquals(dummy.getClass(), db.getCustomerById(c.getId()).getClass());
	}*/

}
