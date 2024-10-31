package com.example.webtech.service.impl;

import com.example.webtech.dto.OrderDto;
import com.example.webtech.entity.CartItem;
import com.example.webtech.entity.OrderItem;
import com.example.webtech.entity.Orders;
import com.example.webtech.mapper.OrderMapper;
import com.example.webtech.repository.CartRepository;
import com.example.webtech.repository.OrderItemRepository;
import com.example.webtech.repository.OrderRepository;
import com.example.webtech.repository.UserRepository;
import com.example.webtech.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImplement implements OrdersService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderMapper mapper;

    @Override
    public void checkOut() {
        Orders orders=new Orders();
        String code= UUID.randomUUID().toString();
        while (orderRepository.findAllByCode(code).size()!=0){
            code=UUID.randomUUID().toString();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        orders.setCode(code);
        orders.setUser(userRepository.findByPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName()));
        orders.setCreatedDate(formatter.format(currentDate));
        orders.setStatus("Not checked yet");
        int total=0;
        for (CartItem item:cartRepository.findCartItemByUser_PhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName())){
            total+=item.getQuantity()*item.getProduct().getPrice();
        }
        orders.setTotal(total);
        orderRepository.save(orders);
        for (CartItem item:cartRepository.findCartItemByUser_PhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName())){
            OrderItem orderItem =new OrderItem();
            orderItem.setOrder_code(orders.getCode());
            orderItem.setSize_id((int) item.getSize().getSizeId());
            orderItem.setProduct_id((int) item.getProduct().getProductId());
            orderItem.setColor_id((int) item.getColor().getColorId());
            orderItem.setQuantity((int) item.getQuantity());
            orderItemRepository.save(orderItem);
            cartRepository.delete(item);
        }
    }

    @Override
    public List<Orders> findAllByUserPhoneNumber(String phoneNumber) {
        return orderRepository.findAllByUserPhoneNumber(phoneNumber);
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream().map(x->mapper.toDto(x)).collect(Collectors.toList());
    }

    @Override
    public void check(String code) {
        Orders orders=orderRepository.findById(code).get();
        orders.setStatus("Checked");
        orderRepository.save(orders);
    }
}
