package vn.devpro.statistic;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.sale.order.Order;

public class OrderManagement {

	public static int autoId = 1;

	private static List<Order> orders = new ArrayList<>();
	
	public static List<Order> getOrders() {
		return orders;
	}

    public static int findById(int id) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

}
