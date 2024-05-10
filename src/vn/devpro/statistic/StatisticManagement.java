package vn.devpro.statistic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.devpro.sale.order.Order;
import vn.devpro.sale.order.OrderProduct;
import vn.devpro.update.customer.CustomerManagement;
import vn.devpro.update.product.ProductManagement;


public class StatisticManagement {

	static Scanner sc = new Scanner(System.in);
	
    public static void execute() {
    	while (true) {
            System.out.println("---------------------------- VIEW STATISTICS ----------------------------");
            System.out.println("\t1. Display the list of bills.");
            System.out.println("\t2. Delete a bill.");
            System.out.println("\t3. Total revenue.");
            System.out.println("\t4. Revenue by customer.");
            System.out.println("\t5. Revenue by product.");
            System.out.println("\t0. Return.");
            System.out.print("Select a management function: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    return;
                case 1:
                    displayAll();
                    break;
                case 2:
                    removeBill();
                    break;
                case 3:
                    totalRevenue();
                    break;
                case 4:
                    revenueByCustomer();
                    break;
                case 5:
                    revenueByProduct();
                    break;
                default: System.out.println("Invalid choice!");
            }
        }
    }
    
    public static void displayAll() {
    	for (Order order : OrderManagement.getOrders()) {
    		order.display();
    	}
    }
    
    public static void removeBill() {
        System.out.println("------------------------------ REMOVE BILL ------------------------------");
    	System.out.print("\tOrder ID: ");
        int id = Integer.parseInt(sc.nextLine());
    	int index = OrderManagement.findById(id);
    	if (index == -1) {
            System.out.println("Order does not exist!");
            return;
        }
    	
    	OrderManagement.getOrders().remove(index);
    	OrderProductManagement.removeByOrderId(id);
        System.out.println("Remove bill successull");
    }
    
    public static void totalRevenue() {
        System.out.println("----------------------------- TOTAL REVENUE -----------------------------");
    	double total = 0;
    	for (Order order : OrderManagement.getOrders()) {
    		total += order.getTotal();
    	}
    	
    	System.out.printf("Total Revenue : %,7.1f%s%n", total, "$");
    }
    
    public static void revenueByCustomer() {
        System.out.println("-------------------------- REVENUE BY CUSTOMER --------------------------");
        List<Order> orders = OrderManagement.getOrders();
    	List<Integer> customerIds = new ArrayList<>();
        System.out.printf("%-20s : %-7s%n", "CUSTOMER", "REVENUE");
    	
    	for (int i = 0; i < orders.size(); i++) {
            int customerId = orders.get(i).getCustomerId();
            if (!customerIds.contains(customerId)) {
                double total = orders.get(i).getTotal();
                for (int j = i + 1; j < orders.size(); j++) {
                	if (orders.get(j).getCustomerId() == customerId) {
                    	total += orders.get(j).getTotal();
                    }
                }
                customerIds.add(customerId);
                System.out.printf("%-20s : %,7.1f%s%n", CustomerManagement.getById(customerId).getName(), total, "$");
            }
    	}
    }
    
    public static void revenueByProduct() {
        System.out.println("-------------------------- REVENUE BY PRODUCTS --------------------------");
        List<OrderProduct> products = OrderProductManagement.getOrderProducts();
    	List<Integer> productIds = new ArrayList<>();
        System.out.printf("%-20s %-8s %-7s%n", "PRODUCT", "QUANTITY", "REVENUE");
    	
    	for (int i = 0; i < products.size(); i++) {
            int productId = products.get(i).getProductId();
            if (!productIds.contains(productId)) {
                double total = products.get(i).amount();
                int quantity = products.get(i).getQuantity();
                for (int j = i + 1; j < products.size(); j++) {
                	if (products.get(j).getProductId() == productId) {
                    	total += products.get(j).amount();
                    	quantity += products.get(j).getQuantity();
                    }
                }
                productIds.add(productId);
                System.out.printf("%-20s %8d %,7.1f%s%n",
                		ProductManagement.getById(productId).getName(), quantity, total, "$");
            }
    	}
    }
    
}
