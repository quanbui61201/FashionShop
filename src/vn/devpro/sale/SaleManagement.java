package vn.devpro.sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.devpro.sale.order.Order;
import vn.devpro.sale.order.OrderProduct;
import vn.devpro.statistic.OrderManagement;
import vn.devpro.statistic.OrderProductManagement;
import vn.devpro.update.customer.Customer;
import vn.devpro.update.customer.CustomerManagement;
import vn.devpro.update.product.ProductManagement;

public class SaleManagement {

    private static List<OrderProduct> products = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);
    
    public static void execute() {
        while (true) {
            System.out.println("------------------------------ MANAGE SALE ------------------------------");
            System.out.println("\t1. View shopping cart.");
            System.out.println("\t2. Add a product to the shopping cart.");
            System.out.println("\t3. Edit product information in the shopping cart.");
            System.out.println("\t4. Remove a product from the shopping cart.");
            System.out.println("\t5. Cancel the shopping cart.");
            System.out.println("\t6. Payment.");
            System.out.println("\t0. Return.");
            System.out.print("Select a management function: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    return;
                case 1:
                    view();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    editProduct();
                    break;
                case 4:
                    removeProduct();
                    break;
                case 5:
                    products = new ArrayList<>();
                    break;
                case 6:
                    payment();
                    break;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
    
    public static void view() {
        System.out.println("----------------------------- SHOPPING CART -----------------------------");
        if (products.size() == 0) {
            System.out.println("No product in the shopping cart.");
        } else {
        	System.out.printf("%-30s %-8s %-7s%n", "PRODUCT", "QUANTITY", "AMOUNT");
            for (OrderProduct product : products) {
            	product.display();
            }
        }
    }
    
    public static void addProduct() {
        System.out.println("---------------------- ADD PRODUCT TO SHOPPING CART ---------------------");
    	System.out.print("\tProduct ID: ");
        int productId = Integer.parseInt(sc.nextLine());
        int pIndex = ProductManagement.findById(productId);
        if (pIndex == -1) {
            System.out.println("Product does not exist.");
            return;
        }

        System.out.print("\tQuantity: ");
        int quantity = Integer.parseInt(sc.nextLine());
        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        int cpIndex = findOrderProductById(productId);

        if (cpIndex != -1) {
            quantity += products.get(cpIndex).getQuantity();
        }

        if (cpIndex == -1) {
        	products.add(new OrderProduct(OrderProductManagement.autoId++, productId,
        			OrderManagement.autoId, quantity));
        } else {
        	products.get(cpIndex).setQuantity(quantity);
        }

        System.out.println("Order Success!");
    }

    public static void editProduct() {
        System.out.println("--------------------- EDIT PRODUCT IN SHOPPING CART ---------------------");
        System.out.print("\tProduct ID: ");
        int productId = Integer.parseInt(sc.nextLine());
        int cpIndex = findOrderProductById(productId);
        if (cpIndex == -1) {
            System.out.println("Product does not exist!");
            return;
        }

        System.out.print("\tQuantity (add(+) / minus(-)): ");
        int quantity = Integer.parseInt(sc.nextLine());
        quantity += products.get(cpIndex).getQuantity();

        if (quantity < 0) {
            System.out.println("Invalid quantity!");
            return;
        }
    	if (quantity == 0) {
    		products.remove(cpIndex);
            System.out.println("Remove product successfull!");
            return;
    	}
    	
        products.get(cpIndex).setQuantity(quantity);
        System.out.println("Edit product in shopping cart successfull!");
    }

    public static void removeProduct() {
        System.out.println("------------------- REMOVE PRODUCT FROM SHOPPING CART -------------------");
        System.out.print("\tProduct ID: ");
        int productId = Integer.parseInt(sc.nextLine());
        int index = findOrderProductById(productId);
        if (index == -1) {
            System.out.println("Product does not exist!");
            return;
        }
        products.remove(index);
        System.out.println("Remove product successfull!");
    }

    public static void payment() {
        System.out.println("-------------------------------- PAYMENT --------------------------------");

        OrderProductManagement.getOrderProducts().addAll(products);

        int customerId;
        
        String code = "";
        String name = "";
        String mobile = "";
        
        double total = 0;
        for (OrderProduct product : products) {
        	total += product.amount();
        }

        String ans;
        while(true) {
            System.out.print("\tDo you already have an account (Y/N)?: ");
        	ans = sc.nextLine();
        	if (ans.equalsIgnoreCase("Y") || ans.equalsIgnoreCase("N")) break;
        }
        if (ans.equalsIgnoreCase("Y")) {
        	System.out.print("\tCustomer ID: ");
            customerId = Integer.parseInt(sc.nextLine());
            int cIndex = CustomerManagement.findById(customerId);
            if (cIndex != -1) {
            	name = CustomerManagement.getById(customerId).getName();
            	mobile = CustomerManagement.getById(customerId).getMobile();
            	code = "O" + OrderManagement.autoId;
            	Order order = new Order(OrderManagement.autoId++, customerId, code, total);
            	OrderManagement.getOrders().add(order);
            	order.display();
            } else {
            	System.out.print("Account does not exist! Please create a new account!");
            }
        } else {
        	System.out.print("\tCustomer name: ");
        	name = sc.nextLine();
            if (name.isEmpty()) {
                System.out.println("Name is invalid!");
                return;
            }
            
            System.out.print("\tMobile: ");
            mobile = sc.nextLine();
            if (mobile.isEmpty()) {
                System.out.println("Mobile is invalid!");
                return;
            }
            if (CustomerManagement.findByMobile(mobile) != -1) {
                System.out.println("Mobile already exists!");
                return;
            }
            
        	customerId = CustomerManagement.autoId;
        	CustomerManagement.getCustomers().add(
        			new Customer(customerId, "C" + CustomerManagement.autoId++, name, mobile));
        	
        	code = "O" + OrderManagement.autoId;
        	Order order = new Order(OrderManagement.autoId++, customerId, code, total);
        	OrderManagement.getOrders().add(order);
        	order.display();
        }
        
        products = new ArrayList<>();
    }

    public static int findOrderProductById(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == id) {
                return i;
            }
        }
        return -1;
    }
    
}
