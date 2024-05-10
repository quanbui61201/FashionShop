package vn.devpro;

import java.util.Scanner;

import vn.devpro.sale.SaleManagement;
import vn.devpro.statistic.StatisticManagement;
import vn.devpro.update.UpdateManagement;
import vn.devpro.update.category.CategoryManagement;
import vn.devpro.update.customer.CustomerManagement;
import vn.devpro.update.product.ProductManagement;

public class ShopManagement {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
        CategoryManagement.inIt();
        CustomerManagement.inIt();
        ProductManagement.inIt();

        while (true) {
            System.out.println("==================== FASHION SHOP MANAGEMENT PROGRAM ====================");
            System.out.println("\t1. Update Shop Information.");
            System.out.println("\t2. Manage Sales.");
            System.out.println("\t3. View Statistics.");
            System.out.println("\t0. Exit the Program.");
            System.out.print("Select a management function: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    UpdateManagement.execute();
                    break;
                case 2:
                    SaleManagement.execute();
                    break;
                case 3:
                    StatisticManagement.execute();
                    break;
                default: System.out.println("Invalid choice!");
            }
        }
	}

}
