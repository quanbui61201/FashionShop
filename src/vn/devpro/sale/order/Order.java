package vn.devpro.sale.order;

import java.util.List;

import vn.devpro.statistic.OrderProductManagement;
import vn.devpro.update.customer.Customer;
import vn.devpro.update.customer.CustomerManagement;

public class Order {

	private int id;
	private int customerId;
	private String code;
	private double total;
	
	public void display() {
		Customer customer = CustomerManagement.getById(this.customerId);
		List<OrderProduct> products = OrderProductManagement.getOrderProducts();
		
		System.out.println("---------------------------------- BILL ---------------------------------");
        System.out.println("\tCode : " + this.code);
        System.out.println("\tCustomer : " + customer.getName());
        System.out.println("\tMobile : " + customer.getMobile());
        System.out.println("List of Products");
        System.out.printf("%-30s %-8s %-7s%n", "PRODUCT", "QUANTITY", "AMOUNT");
        for (OrderProduct product : products) {
        	if (product.getOrderId() == this.id) {
        		product.display();
        	}
        }
        System.out.printf("Total : %,7.1f%s%n", this.total, "$");
	}
	
	public Customer getCustomer() {
		return CustomerManagement.getById(customerId);
	}
	
 	public Order() {
	}

	public Order(int id, int customerId, String code, double total) {
		this.id = id;
		this.customerId = customerId;
		this.code = code;
		this.total = total;
	}
	

	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}
	

	public int getCustomerId() {
		return customerId;
	}
	

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	

	public String getCode() {
		return code;
	}
	

	public void setCode(String code) {
		this.code = code;
	}
	

	public double getTotal() {
		return total;
	}
	

	public void setTotal(double total) {
		this.total = total;
	}
	
}
