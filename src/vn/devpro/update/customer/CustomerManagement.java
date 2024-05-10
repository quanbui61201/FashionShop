package vn.devpro.update.customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class CustomerManagement {

    public static int autoId = 1;
    private static ArrayList<Customer> customers = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static ArrayList<Customer> getCustomers() {
		return customers;
	}

	public static void inIt() {
        customers.add(new Customer(autoId, "C" + autoId++, "Naruto Uzumaki", "0985214666"));
        customers.add(new Customer(autoId, "C" + autoId++, "Eren Yeager", "0963521210"));
        customers.add(new Customer(autoId, "C" + autoId++, "Lelouch Lamperouge", "0365896633"));
        customers.add(new Customer(autoId, "C" + autoId++, "Dio Brando", "03814781256"));
        customers.add(new Customer(autoId, "C" + autoId++, "Levi Ackerman", "01899846551"));
    }

    public static void execute() {
        while (true) {
            System.out.println("---------------------- UPDATE CUSTOMER INFORMATION ----------------------");
            System.out.println("\t1. Display the list of customers.");
            System.out.println("\t2. Add a new customer.");
            System.out.println("\t3. Edit customer information.");
            System.out.println("\t4. Delete a customer.");
            System.out.println("\t5. Sort the list.");
            System.out.println("\t0. Return.");
            System.out.print("Select a management function: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    return;
                case 1:
                    display();
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    edit();
                    break;
                case 4:
                    remove();
                    break;
                case 5:
                    sortByName();
                    break;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    public static void display() {
        System.out.println("--------------------------- LIST OF CUSTOMERS ---------------------------");
        System.out.printf("%-5s %-10s %-20s %-12s%n", "   ID", "CODE", "NAME", "MOBILE");
        for (Customer customer : customers) {
            customer.display();
        }
    }

    public static void add() {
        System.out.println("---------------------------- ADD NEW CUSTOMER ---------------------------");

        System.out.print("\tName: ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name is invalid!");
            return;
        }

        System.out.print("\tMobile: ");
        String mobile = sc.nextLine();
        if (mobile.isEmpty()) {
            System.out.println("Mobile is invalid!");
            return;
        }
        if (CustomerManagement.findByMobile(mobile) != -1) {
            System.out.println("Mobile already exists!");
            return;
        }
        
        customers.add(new Customer(autoId, "C" + autoId++, name, mobile));
        System.out.println("Add new customer successfully!");
    }

    public static void edit() {
        System.out.println("----------------------- EDIT CUSTOMER INFOMATIONS -----------------------");
        System.out.print("\tCustomer ID: ");
        int id = Integer.parseInt(sc.nextLine());
        int index = findById(id);
        if (index == -1) {
            System.out.println("Customer does not exist!");
            return;
        }

        customers.get(index).edit();
        System.out.println("Edit customer infomation successfully!");
    }

    public static void remove() {
        System.out.println("---------------------------- DELETE CUSTOMER ----------------------------");
        System.out.print("\tCustomer ID: ");
        int id = Integer.parseInt(sc.nextLine());
        int index = findById(id);
        if (index == -1) {
            System.out.println("Customer does not exist!");
            return;
        }

        customers.remove(index);
        System.out.println("Delete customer successfully!");
    }

    public static void sortByName() {
        Collections.sort(customers, new Comparator<Customer>() {
            @Override
            public int compare(Customer c1, Customer c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
    }

    public static int findById(int id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public static int findByCode(String code) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCode().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    public static int findByName(String name) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public static int findByMobile(String mobile) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getMobile().equals(mobile)) {
                return i;
            }
        }
        return -1;
    }

    public static Customer getById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

}
