package vn.devpro.update.product;

import vn.devpro.update.category.CategoryManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ProductManagement {

    public static int autoId = 1;
    private static ArrayList<Product> products = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void inIt() {
        products.add(new Product(autoId++, 1, "OC001", "Men's Suit", 149.99));
        products.add(new Product(autoId++, 2, "SW002", "Sports Jacket", 59.99));
        products.add(new Product(autoId++, 1, "OC002", "Women's Blouse", 49.99));
        products.add(new Product(autoId++, 3, "FA002", "Sunglasses", 29.99));
        products.add(new Product(autoId++, 5, "FW001", "Sneakers", 69.99));
        products.add(new Product(autoId++, 5, "FW002", "High Heels", 59.99));
        products.add(new Product(autoId++, 4, "WC001", "Bridal Gown", 299.99));
        products.add(new Product(autoId++, 3, "FA001", "Leather Belt", 19.99));
        products.add(new Product(autoId++, 4, "WC002", "Groom's Tuxedo", 199.99));
        products.add(new Product(autoId++, 2, "SW001", "Running Shoes", 79.99));
    }

    public static void execute() {
        while (true) {
            System.out.println("---------------------- UPDATE PRODUCTS INFORMATION ----------------------");
            System.out.println("\t1. Display the list of products.");
            System.out.println("\t2. Add new product.");
            System.out.println("\t3. Edit product information.");
            System.out.println("\t4. Delete a product.");
            System.out.println("\t5. Sort the list.");
            System.out.println("\t6. Find by category.");
            System.out.println("\t7. Find by name.");
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
                case 6:
                    displayByCategory();
                    break;
                case 7:
                    displayByName();
                    break;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    public static void display() {
        System.out.println("---------------------------- LIST OF PRODUCT ----------------------------");
        System.out.printf("%-5s %-30s %-10s %-20s %-7s%n", "   ID", "CATEGORY", "CODE", "PRODUCT", "  PRICE");
        for (Product product : products) {
            product.display();
        }
    }

    public static void displayByCategory() {
        System.out.print("\tCategory ID: ");
        int categoryId = Integer.parseInt(sc.nextLine());
        if (CategoryManagement.findById(categoryId) == -1) {
            System.out.println("Category does not exist!");
            return;
        }

        System.out.println("---------------------------- LIST OF PRODUCT ----------------------------");
        System.out.printf("%-5s %-30s %-10s %-20s %-7s%n", "   ID", "CATEGORY", "CODE", "PRODUCT", "  PRICE");
        for (Product product : products) {
            if (product.getCategoryId() == categoryId) {
                product.display();
            }
        }
    }

    public static void displayByName() {
        System.out.print("\tProduct Name: ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name is invalid!");
            return;
        }

        System.out.println("---------------------------- LIST OF PRODUCT ----------------------------");
        System.out.printf("%-5s %-30s %-10s %-20s %-7s%n", "   ID", "CATEGORY", "CODE", "PRODUCT", "  PRICE");
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                product.display();
            }
        }
    }

    public static void add() {
        System.out.println("---------------------------- ADD NEW PRODUCT ----------------------------");
        System.out.print("\tCategory ID: ");
        int categoryId = Integer.parseInt(sc.nextLine());
        if (CategoryManagement.findById(categoryId) == -1) {
            System.out.println("Category does not exist!");
            return;
        }

        System.out.print("\tCode: ");
        String code = sc.nextLine();
        if (code.isEmpty()) {
            System.out.println("Code is invalid!");
            return;
        }
        if (findByCode(code) != -1) {
            System.out.println("Product already exist!");
            return;
        }

        System.out.print("\tProduct Name: ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name is invalid!");
            return;
        }
        if (findByName(name) != -1) {
            System.out.println("Product already exist!");
            return;
        }

        System.out.print("\tPrice: ");
        double price = Double.parseDouble(sc.nextLine());
        if (price < 0) {
            System.out.println("Price is invalid!");
            return;
        }

        products.add(new Product(autoId++, categoryId, code, name, price));
        System.out.println("Add new product successfully!");
    }

    public static void edit() {
        System.out.println("------------------------ EDIT PRODUCT INFOMATION ------------------------");
        System.out.print("\tProduct ID: ");
        int id = Integer.parseInt(sc.nextLine());
        int index = findById(id);
        if (index == -1) {
            System.out.println("Product does not exist!");
            return;
        }

        products.get(index).edit();
        System.out.println("Edit product infomation successfully!");
    }

    public static void remove() {
        System.out.println("----------------------------- DELETE PRODUCT ----------------------------");
        System.out.print("\tProduct ID: ");
        int id = Integer.parseInt(sc.nextLine());
        int index = findById(id);
        if (index == -1) {
            System.out.println("Product does not exist!");
            return;
        }

        products.remove(index);
        System.out.println("Delete product successfully!");
    }

    public static void sortByName() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
    }

    public static int findById(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public static int findByCode(String code) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getCode().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    public static int findByName(String name) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public static Product getById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

}
