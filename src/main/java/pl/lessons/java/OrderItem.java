package pl.lessons.java;

import java.math.BigDecimal;

public class OrderItem {

	public BigDecimal getPrice() {

		return price;
	}

	public void setPrice(BigDecimal price) {

		this.price = price;
	}

	public OrderItem(BigDecimal price) {

		this.price = price;
	}

	private BigDecimal price;
}
