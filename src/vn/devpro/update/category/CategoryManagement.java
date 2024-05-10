package vn.devpro.update.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class CategoryManagement {

    public static int autoId = 1;
    private static ArrayList<Category> categories = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void inIt() {
        categories.add(new Category(autoId, "CA" + autoId++, "Office Clothing"));
        categories.add(new Category(autoId, "CA" + autoId++, "Sportwear"));
        categories.add(new Category(autoId, "CA" + autoId++, "Fashion Accessories"));
        categories.add(new Category(autoId, "CA" + autoId++, "Wedding Clothing"));
        categories.add(new Category(autoId, "CA" + autoId++, "Footwear"));
    }

    public static void execute() {
        while (true) {
            System.out.println("---------------------- UPDATE CATEGORY INFORMATION ----------------------");
            System.out.println("\t1. Display the list of categories.");
            System.out.println("\t2. Add new category.");
            System.out.println("\t3. Edit category information.");
            System.out.println("\t4. Delete a category.");
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
        System.out.println("--------------------------- LIST OF CATEGORIES --------------------------");
        System.out.printf("%-5s %-10s %-30s%n", "   ID", "CODE", "CATEGORY");
        for (Category category: categories) {
            category.display();
        }
    }

    public static void add() {
        System.out.println("---------------------------- ADD NEW CATEGORY ---------------------------");

        System.out.print("\tCategory Name: ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name is invalid!");
            return;
        }
        if (findByName(name) != -1) {
            System.out.println("Category already exits!");
            return;
        }

        categories.add(new Category(autoId, "CA" + autoId++, name));
        System.out.println("Add new category successfully!");
    }

    public static void edit() {
        System.out.println("----------------------- EDIT CATEGORY INFOMATIONS -----------------------");
        System.out.print("\tCategory ID: ");
        int id = Integer.parseInt(sc.nextLine());
        int index = findById(id);
        if (index == -1) {
            System.out.println("Category does not exist!");
            return;
        }

        System.out.print("\tNew category name: ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name is invalid!");
            return;
        }
        if (findByName(name) != -1) {
            System.out.println("Category already exits!");
            return;
        }

        categories.get(index).setName(name);
        System.out.println("Edit category infomation successfully!");
    }

    public static void remove() {
        System.out.println("---------------------------- DELETE CATEGORY ----------------------------");
        System.out.print("\tCategory ID: ");
        int id = Integer.parseInt(sc.nextLine());
        int index = findById(id);
        if (index == -1) {
            System.out.println("Category does not exist!");
            return;
        }

        categories.remove(index);
        System.out.println("Delete category successfully!");
    }

    public static void sortByName() {
        Collections.sort(categories, new Comparator<Category>() {
            @Override
            public int compare(Category c1, Category c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
    }

    public static int findById(int id) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public static int findByCode(String code) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getCode().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    public static int findByName(String name) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public static Category getById(int id) {
        for (Category category: categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

}
