package model.database.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.Customer;
import model.database.CustomerDatabase;

class CustomerDatabaseUnitTests {

	CustomerDatabase db = new CustomerDatabase();;
	Customer c;
	Customer b;
	
	public void init() {
		// Use Mockito here
		c = new Customer();
		c.setActive(true);
		c.setAddress("Street 1");
		c.setEmail("tom@email.com");
		c.setName("TOM");
		c.setPhone("123");
		
		
		b = new Customer();
		b.setActive(true);
		b.setAddress("Street 2");
		b.setEmail("john@email.com");
		b.setName("JOHN");
		b.setPhone("456");
	}
	
	@Test
	void saveCustomerSuccess() throws InvalidKeySpecException, SQLException {
			this.init();
		
			db.saveCustomer(c);
			assertEquals("TOM", db.getCustomerById("1").getName()); // Checks that the retrieved name is equal to the saved one.
			db.reset();
		
	}
	
	@Test
	void saveCustomerFailure() throws InvalidKeySpecException, SQLException {
		this.init();
		
		assertThrows(MySQLIntegrityConstraintViolationException.class, () -> {
			db.saveCustomer(c);
			db.saveCustomer(c);
		});
		
		db.reset();
	}
	
	// @Test
	/* void checkThatGetCustomerByIdReturnsACustomer(Customer c) throws InvalidKeySpecException, SQLException {
		db.saveCustomer(c);
		Customer dummy = new Customer();
		assertEquals(dummy.getClass(), db.getCustomerById(c.getId()).getClass());
	}*/

}
