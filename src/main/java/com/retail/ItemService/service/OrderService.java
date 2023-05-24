package com.retail.ItemService.service;

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
        Optional<Order> orderOptional = orderRepository.findOrderByCustomer(customerID);
        if (orderOptional.isPresent()) {
            order = orderOptional.get();
        } else {
            Customer customer = customerService.findCustomerId(customerID);
            order = new Order();
            order.setState(OrderState.NEW);
            order.setCustomer(customer);
            order.setShippingAddress(customer.getDefaultShippingAddress());
        }
        for (ItemLineForm lineForm : form.getItems()) {
            Optional<ItemLine> existingLine = order.getLineItems().stream().filter(item -> item.getId() == lineForm.getItemID()).findFirst();
            if (existingLine.isPresent()) {
                ItemLine line = existingLine.get();
                line.setQuantity(line.getQuantity() + lineForm.getQuantity());
            } else {
                Item item = itemService.getItemById(lineForm.getItemID());
                order.getLineItems().add(new ItemLine(item, lineForm.getQuantity()));
            }
        }
        return mapper.map(orderRepository.save(order), OrderResponse.class);
    }

    public List<OrderResponse> getAllOrderByCustomerID(int customerID) {
        return orderRepository.findAllOrderByCustomer(customerID).stream().map(order -> mapper.map(order, OrderResponse.class)).toList();
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
