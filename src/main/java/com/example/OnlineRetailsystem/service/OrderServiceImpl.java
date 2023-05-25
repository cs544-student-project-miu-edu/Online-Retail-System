package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.Item;
import com.example.OnlineRetailsystem.domain.ItemLine;
import com.example.OnlineRetailsystem.domain.Order;
import com.example.OnlineRetailsystem.domain.OrderState;
import com.example.OnlineRetailsystem.dto.CustomMessageType;
import com.example.OnlineRetailsystem.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemLineService itemLineService;

    @Autowired
    ItemService itemService;

    @Override
    public Order getOrderByID(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomMessageType("Order not found with ID: " + orderId));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<ItemLine> getOrderItemLines(int orderId) {
        Order order = getOrderByID(orderId);
        return order.getLineItems();

    }

    @Override
    public Order updateState(int orderId, OrderState orderState) {
        Order order = getOrderByID(orderId);
        order.setState(orderState);
        orderRepository.save(order);
        return order;
    }

    @Override
    public ItemLine addItemToOrder(int orderId, int itemID) {
        Order order = getOrderByID(orderId);

        if(order.getState() != OrderState.NEW){
            System.out.println("Cannot place an order");
            return null;
        }
        Item item = itemService.getItemById(itemID);
        List<ItemLine> itemLines = order.getLineItems();

        if(itemLines.size() == 0){
            ItemLine newItemLine = new ItemLine(item);
            newItemLine.setQuantity(1);
            newItemLine.setDiscountValue(0);
            itemLines.add(newItemLine);
            order.setLineItems(itemLines);
            orderRepository.save(order);
            return newItemLine;
        }else {
            ItemLine returnItemLine = new ItemLine(item);
            for (ItemLine itemLine : itemLines) {
                if (itemLine.getItem().getItemID() == itemID) {

                    itemLine.setQuantity(itemLine.getQuantity() + 1);

                    returnItemLine.setId(itemLine.getId());
                    returnItemLine.setQuantity(itemLine.getQuantity());
                    returnItemLine.setItem(itemLine.getItem());
                    returnItemLine.setDiscountValue(itemLine.getDiscountValue());
                }
                else{
                    returnItemLine.setQuantity(1);
                    returnItemLine.setItem(itemLine.getItem());
                    returnItemLine.setDiscountValue(0);

                    itemLines.add(returnItemLine);
                }
            }
            order.setLineItems(itemLines);
            orderRepository.save(order);
            return returnItemLine;
        }
    }

    @Override
    public ItemLine updateItemLineToOrder(int orderId, ItemLine itemLine) {
        Order order = getOrderByID(orderId);
        List<ItemLine> itemLines = order.getLineItems();

        for (ItemLine iL : itemLines){
            if (iL.getId() == itemLine.getId()) {
                itemLineService.updateItemLine(iL.getId(),itemLine);
            }
        }
        order.setLineItems(itemLines);
        orderRepository.save(order);
        return itemLine;

    }

    @Override
    public void getItemLineOfOrder(int orderId, int itemLineID) {

    }

    @Override
    public void deleteItemLineFromOrder(int orderID, int itemLineID) {
        Order order = getOrderByID(orderID);
        List<ItemLine> itemLines = order.getLineItems();

        for (ItemLine itemLine: itemLines){
            if(itemLine.getId() == itemLineID){
                itemLines.remove(itemLineService.getItemLineById(itemLineID));
            }
        }
        orderRepository.save(order);
    }
}
