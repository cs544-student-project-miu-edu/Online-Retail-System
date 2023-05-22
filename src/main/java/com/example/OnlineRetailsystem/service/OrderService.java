package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.ItemLine;
import com.example.OnlineRetailsystem.domain.Order;
import com.example.OnlineRetailsystem.domain.OrderState;

import java.util.List;


public interface OrderService {
    public Order createOrder(Order order);
    public void deleteOrder(int orderID);

    public void updateOrder(int orderId, Order order);
    public Order getOrder(int orderId);
    public List<Order> getAllOrders();
    List<ItemLine> getOrderItemLines(int orderId);

    Order updateState(int orderId, OrderState orderState);
}
