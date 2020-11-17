package pl.lessons.java;

import com.google.common.collect.ImmutableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    private final LocalDate date;

    private final List<OrderItem> items;

    public Order(LocalDate date, List<OrderItem> items) {
        this.date = date;
        this.items = items;
    }

    public static Order of(LocalDate date, List<OrderItem> items) {
        Objects.requireNonNull(date);
        return new Order(LocalDate.now(), new ArrayList<>(items));
    }

    public void addItem(OrderItem item) {

    }

    public List<OrderItem> getItems() {
        return ImmutableList.copyOf(items);
    }

}
