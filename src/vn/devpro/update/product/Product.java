package vn.devpro.update.product;

import vn.devpro.update.category.Category;
import vn.devpro.update.category.CategoryManagement;

import java.util.Scanner;

public class Product {

    private int id;
    private int categoryId;
    private String code;
    private String name;
    private double price;

    Scanner sc = new Scanner(System.in);

    public void display() {
        Category category = CategoryManagement.getById(this.categoryId);
        System.out.printf("%5d %-30s %-10s %-20s %,7.1f%s%n",
                this.id, category.getName(), this.code, this.name, this.price, "$");
    }

    public void edit() {
        System.out.println("----------------------- EDIT PRODUCTS INFORMATION -----------------------");
        System.out.println("\t1. Edit category.");
        System.out.println("\t2. Edit name.");
        System.out.println("\t3. Edit price.");
        System.out.println("\t0. Return.");
        while (true) {
            System.out.print("Select the option to edit: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    return;
                case 1:
                    editCategoryId();
                    break;
                case 2:
                    editName();
                    break;
                case 3:
                    editPrice();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void editCategoryId() {
        System.out.print("\tNew category ID: ");
        int categoryId = Integer.parseInt(sc.nextLine());
        if (CategoryManagement.findById(categoryId) == -1) {
            System.out.println("Category does not exist!");
            return;
        }

        this.setCategoryId(categoryId);
    }

    public void editName() {
        System.out.print("\tNew name: ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name is invalid!");
            return;
        }
        if (ProductManagement.findByName(name) != -1) {
            System.out.println("Product already exists!");
            return;
        }

        this.setName(name);
    }

    public void editPrice() {
        System.out.print("\tNew price: ");
        double price = Double.parseDouble(sc.nextLine());
        if (price < 0) {
            System.out.println("Price is invalid!");
            return;
        }

        this.setPrice(price);
    }

    public Product() {
    }

    public Product(int id, int categoryId, String code, String name, double price) {
        this.id = id;
        this.categoryId = categoryId;
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
