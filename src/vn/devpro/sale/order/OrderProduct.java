package vn.devpro.sale.order;

import vn.devpro.update.product.Product;
import vn.devpro.update.product.ProductManagement;

public class OrderProduct {

	private int id;
	private int productId;
	private int orderId;
	private int quantity;

    public void display() {
        Product product = ProductManagement.getById(this.productId);
        System.out.printf("%-30s %8d %,7.1f%s%n", product.getName(), this.quantity, amount(), "$");
    }

    public double amount() {
        Product product = ProductManagement.getById(this.productId);
        return product.getPrice() * this.quantity;
    }

	public OrderProduct() {
	}
	
	public OrderProduct(int id, int productId, int orderId, int quantity) {
		this.id = id;
		this.productId = productId;
		this.orderId = orderId;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
