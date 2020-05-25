package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.paypal.api.payments.BillingInfo;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.Invoice;
import com.paypal.api.payments.InvoiceItem;
import com.paypal.api.payments.Invoices;
import com.paypal.api.payments.ShippingInfo;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.JSONFormatter;
import com.paypal.base.rest.PayPalRESTException;

import model.database.CustomerDatabase;
import model.database.OrderDatabase;
import model.database.ServiceDatabase;
import secretStuff.PaypalSecrets;

public class PaymentInvoice {
	private PaypalSecrets secret = new PaypalSecrets();
	private Invoice instance;
	private APIContext context = new APIContext(secret.getClientID(),secret.getClientSecret(),"sandbox");
	private ShippingInfo ship = new ShippingInfo();
	private BillingInfo billInfo = new BillingInfo();
	Currency cur = new Currency();
	InvoiceItem item = new InvoiceItem();
	private OrderDatabase data = new OrderDatabase();
	private CustomerDatabase customerData = new CustomerDatabase();
	private ServiceDatabase serviceData = new ServiceDatabase();
	private int customerID;
	private int serviceID;
	
	public Invoice create(int orderId){
		//TODO getService by id
		instance = loadInvoice();
		customerID = data.getOrderById(orderId).getCustomerId();
		serviceID = data.getOrderById(orderId).getServiceId();
		
		try {
			item.setName(serviceData.getServiceById(serviceID).getTitle());
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage() + "\nError Code: " + e.getErrorCode());
		}
		item.setQuantity(1);
		cur.setCurrency("USD");
		try {
			cur.setValue("" + serviceData.getServiceById(serviceID).getPrice());
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage() + "\nError Code: " + e.getErrorCode());
		}
		item.setUnitPrice(cur);

		ArrayList<BillingInfo> billing = new ArrayList<BillingInfo>();
		ArrayList<InvoiceItem> items = new ArrayList<InvoiceItem>();
		items.add(item);
		ship.setFirstName(customerData.getCustomerById(customerID).getName());
		ship.setLastName("");
		System.out.println(customerData.getCustomerById(customerID).getName());
		
		billInfo.setFirstName(customerData.getCustomerById(customerID).getName());
		billInfo.setLastName("");
		billInfo.setEmail("sb-433qpa1863507@personal.example.com");
		billing.add(billInfo);
		
		instance.setShippingInfo(ship);
		instance.setBillingInfo(billing);
		instance.setItems(items);
		instance.setMerchantMemo("" + orderId);
		try {
			instance = instance.create(context);
		} catch (PayPalRESTException e) {
			System.out.println("Error: " + e.getMessage());
		}
		System.out.println("create response:\n" + Invoice.getLastResponse());
		return instance;
	}
	
	public void send(){
		try {
			instance.send(context);
		} catch (PayPalRESTException e) {
			System.out.println("Error: " + e.getMessage());
		}
		System.out.println("send response:\n" + Invoice.getLastResponse());
	}
	
	public Invoice retrive(int id){
		String invID = "";
		for(Invoice i : getMerchantInvoices().getInvoices()) {
			if(i.getMerchantMemo().equals("" + id)) {
				invID = i.getId();
			}
		}
		
		try {
			instance = Invoice.get(context, invID);
		} catch (PayPalRESTException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return instance;
	}
	
	public Invoices getMerchantInvoices(){
		Map<String,String> options = new HashMap<String,String>(){{
			put("total_count_required", "true");
		}};
		try {
			return Invoice.getAll(context, options);
		} catch (PayPalRESTException e) {
			System.out.println("Error: " + e.getMessage() + "\nNothing returned");
			return null;
		}
	}
	
	public Map<Integer,String> getStatus(Invoices invoices, ArrayList<Order> orders){
		Map<Integer,String> statuses = new HashMap<Integer,String>();
		for(Invoice i : invoices.getInvoices()) {
			for(Order o : orders) {
				if(i.getMerchantMemo().equals("" + o.getId())) {
					statuses.put(o.getId(), i.getStatus());
				}
			}
		}
		return statuses;
	}
	
	
	private Invoice loadInvoice(){
		BufferedReader br = null;
		try {
			File file = new File("C:\\Users\\Stoffe\\Documents\\invoice_create.json").getAbsoluteFile();
			br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.getProperty("line.separator"));
				line = br.readLine();
			}
			return JSONFormatter.fromJSON(sb.toString(), Invoice.class);
		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
