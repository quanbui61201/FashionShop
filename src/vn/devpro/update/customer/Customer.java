package vn.devpro.update.customer;

import java.util.Scanner;

public class Customer {

    private int id;
    private String code;
    private String name;
    private String mobile;

    Scanner sc = new Scanner(System.in);

    public void display() {
        System.out.printf("%5d %-10s %-20s %-12s%n", this.id, this.code, this.name, this.mobile);
    }

    public void edit() {
        System.out.println("----------------------- EDIT PRODUCTS INFORMATION -----------------------");
        System.out.println("\t1. Edit name.");
        System.out.println("\t2. Edit mobile.");
        System.out.println("\t0. Return.");
        while (true) {
            System.out.print("Select the option to edit: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    return;
                case 1:
                    editName();
                    break;
                case 3:
                    editMobile();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void editName() {
        System.out.print("\tNew name: ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name is invalid!");
            return;
        }

        this.setName(name);
    }

    public void editMobile() {
        System.out.print("\tNew mobile: ");
        String mobile = sc.nextLine();
        if (mobile.isEmpty()) {
            System.out.println("Name is invalid!");
            return;
        }
        if (CustomerManagement.findByMobile(mobile) != -1) {
            System.out.println("Mobile already exists!");
            return;
        }

        this.setMobile(mobile);
    }

    public Customer() {
    }

    public Customer(int id, String code, String name, String mobile) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
