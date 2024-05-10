package vn.devpro.update;

import vn.devpro.update.category.CategoryManagement;
import vn.devpro.update.customer.CustomerManagement;
import vn.devpro.update.product.ProductManagement;

import java.util.Scanner;

public class UpdateManagement {
	
    static Scanner sc = new Scanner(System.in);

    public static void execute() {
        while (true) {
            System.out.println("------------------------ UPDATE SHOP INFORMATION ------------------------");
            System.out.println("\t1. Update Category Information.");
            System.out.println("\t2. Update Product Information.");
            System.out.println("\t3. Update Customer Information.");
            System.out.println("\t0. Return.");
            System.out.print("Select a management function: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    return;
                case 1:
                    CategoryManagement.execute();
                    break;
                case 2:
                    ProductManagement.execute();
                    break;
                case 3:
                    CustomerManagement.execute();
                    break;
                default: System.out.println("Invalid choice!");
            }
        }
    }

}
