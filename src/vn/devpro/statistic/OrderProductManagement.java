package vn.devpro.statistic;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.sale.order.OrderProduct;

public class OrderProductManagement {
	
	public static int autoId = 1;

	private static List<OrderProduct> orderProducts = new ArrayList<>();
	
	public static List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public static void removeByOrderId(int id) {
		for (OrderProduct product : orderProducts) {
			if (product.getOrderId() == id) {
				orderProducts.remove(product);
			}
		}
	}
	
}
