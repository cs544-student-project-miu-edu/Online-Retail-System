package com.retail.ItemService.service;

import com.retail.ItemService.ResponseError.NotFoundException;
import com.retail.ItemService.domain.*;
import com.retail.ItemService.dto.OrderResponse;
import com.retail.ItemService.form.ItemLineForm;
import com.retail.ItemService.form.OrderForm;
import com.retail.ItemService.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * @TODO
 *   jms sender here
 * */
@Repository
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ModelMapper mapper;

    public OrderResponse createOrder(OrderForm form, int customerID) {
        Order order;
        Optional<Order> orderOptional = orderRepository.findOrderByCustomerWithNewStatus(customerID);
        if (orderOptional.isPresent()) {
            order = orderOptional.get();
        } else {
            Customer customer = customerService.findCustomerId(customerID);
            order = new Order();
            order.setState(OrderState.NEW);
            order.setCustomer(customer);
            order.setShippingAddress(customer.getDefaultShippingAddress());
        }
        List<Item> orderedItems = new ArrayList<>();
        for (ItemLineForm lineForm : form.getItems()) {
            Optional<ItemLine> existingLine = order.getLineItems().stream().filter(item -> item.getItem().getItemID() == lineForm.getItemID()).findFirst();
            if (existingLine.isPresent()) {
                ItemLine line = existingLine.get();
                line.getItem().decreaseQuantity(lineForm.getQuantity());
                orderedItems.add(line.getItem());
                line.setQuantity(line.getQuantity() + lineForm.getQuantity());
            } else {
                Item item = itemService.getItemById(lineForm.getItemID());
                item.decreaseQuantity(lineForm.getQuantity());
                orderedItems.add(item);
                order.getLineItems().add(new ItemLine(item, lineForm.getQuantity()));
            }
        }
        itemService.saveAllItems(orderedItems);
        return new OrderResponse(orderRepository.save(order));
    }

    public List<OrderResponse> getAllOrderByCustomerID(int customerID) {
        return orderRepository.findAllOrderByCustomer(customerID).stream().map(OrderResponse::new).toList();
    }

    public void placeOrder(int customerID) {
        Optional<Order> orderOptional = orderRepository.findOrderByCustomerWithNewStatus(customerID);
        if (!orderOptional.isPresent()) {
            throw new NotFoundException("No order to be placed");
        }
        Order order = orderOptional.get();
        order.setState(OrderState.PLACED);
        orderRepository.save(order);
    }

    public Order getOrderByID(int orderID) {
        Optional<Order> orderOptional = orderRepository.findById(orderID);
        if (!orderOptional.isPresent()) {
            throw new NotFoundException("No order found");
        }
        return orderOptional.get();
    }

    public OrderResponse getOrderByIDwithDto(int orderID) {
        return new OrderResponse(getOrderByID(orderID));
//        return mapper.map(getOrderByID(orderID), OrderResponse.class);
    }

    public List<OrderResponse> getAllOrder() {
        return orderRepository.findAll().stream().map(OrderResponse::new).toList();
    }

    public void deleteOrder(int customerID) {
        Optional<Order> orderOptional = orderRepository.findOrderByCustomerWithNewStatus(customerID);
        if (!orderOptional.isPresent()) {
            throw new NotFoundException("No order to be placed");
        }
        Order order = orderOptional.get();
        List<Item> orderedItems = new ArrayList<>();
        for (ItemLine line : order.getLineItems()) {
            line.getItem().increaseQuantity(line.getQuantity());
            orderedItems.add(line.getItem());
        }
        itemService.saveAllItems(orderedItems);
        orderRepository.delete(order);
    }
//    public void order(){
//        Order order;
//        Optional<Order> orderOptional = findOrderByState();
//        if(orderOptional.ifPresent()){
//            order = orderOptional
//        }else{
//            order = order();
//        }
//    }
}
